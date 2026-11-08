package repository.impl;

import dto.HoaDonDong;
import entity.HoaDon;
import entity.Refs;
import java.util.List;
import mapper.HoaDonDongMapper;
import mapper.HoaDonMapper;
import repository.HoaDon_IDAO;

public class HoaDon_DAO extends AbstractDAO<HoaDon> implements HoaDon_IDAO {

    private static final String SELECT = """
            SELECT hd.maHoaDon, hd.ngayLap, hd.chietKhau, hd.ghiChu, hd.ngayTao, hd.nguoiTao,
                   kh.maKH, kh.hoTen AS tenKhachHang,
                   kdt.maKeHoach, kdt.lyDoDieuTri AS lyDoKeHoach,
                   bs.maNhanVien AS maBacSi, bs.hoTen AS tenBacSi,
                   nt.hoTen AS tenNguoiTao
            FROM HoaDon hd
            JOIN KhachHang kh ON kh.maKH = hd.maKH
            LEFT JOIN KeHoachDieuTri kdt ON kdt.maKeHoach = hd.maKeHoach
            LEFT JOIN NhanVien bs ON bs.maNhanVien = hd.maBacSi
            LEFT JOIN NhanVien nt ON nt.maNhanVien = hd.nguoiTao
            """;

    private static final String SELECT_DONG = """
            SELECT hd.maHoaDon, hd.ngayLap, hd.chietKhau, hd.ghiChu, hd.ngayTao, hd.nguoiTao,
                   ISNULL(ctt.tongTien, 0) AS tongTien,
                   ISNULL(ptt.daThu, 0) AS daThu,
                   kh.maKH, kh.hoTen AS tenKhachHang,
                   kdt.maKeHoach, kdt.lyDoDieuTri AS lyDoKeHoach,
                   bs.maNhanVien AS maBacSi, bs.hoTen AS tenBacSi,
                   nt.hoTen AS tenNguoiTao
            FROM HoaDon hd
            JOIN KhachHang kh ON kh.maKH = hd.maKH
            LEFT JOIN KeHoachDieuTri kdt ON kdt.maKeHoach = hd.maKeHoach
            LEFT JOIN NhanVien bs ON bs.maNhanVien = hd.maBacSi
            LEFT JOIN NhanVien nt ON nt.maNhanVien = hd.nguoiTao
            LEFT JOIN (
                SELECT maHoaDon, SUM(CAST(soLuong AS BIGINT) * donGia) AS tongTien
                FROM ChiTietHoaDon
                GROUP BY maHoaDon
            ) ctt ON ctt.maHoaDon = hd.maHoaDon
            LEFT JOIN (
                SELECT maHoaDon, SUM(soTien) AS daThu
                FROM PhieuThu
                WHERE maHoaDon IS NOT NULL AND trangThai = N'Đã chi trả'
                GROUP BY maHoaDon
            ) ptt ON ptt.maHoaDon = hd.maHoaDon
            """;

    private final HoaDonMapper mapper = new HoaDonMapper();
    private final HoaDonDongMapper dongMapper = new HoaDonDongMapper();

    @Override
    public List<HoaDon> findAllHoaDon() {
        return query(SELECT + " ORDER BY hd.ngayLap DESC, hd.maHoaDon", mapper);
    }

    public List<HoaDonDong> findAllDong() {
        return query(SELECT_DONG + " ORDER BY hd.ngayLap DESC, hd.maHoaDon", dongMapper);
    }

    @Override
    public HoaDon findById(String maHoaDon) {
        return one(SELECT + " WHERE hd.maHoaDon = ?", mapper, maHoaDon);
    }

    public HoaDonDong findDongById(String maHoaDon) {
        return one(SELECT_DONG + " WHERE hd.maHoaDon = ?", dongMapper, maHoaDon);
    }

    public HoaDonDong lockDongById(String maHoaDon) {
        String sql = SELECT_DONG.replace("FROM HoaDon hd", "FROM HoaDon hd WITH (UPDLOCK, ROWLOCK)")
                + " WHERE hd.maHoaDon = ?";
        return one(sql, dongMapper, maHoaDon);
    }

    @Override
    public List<HoaDon> findByMaKH(String maKH) {
        return query(SELECT + " WHERE hd.maKH = ? ORDER BY hd.ngayLap DESC, hd.maHoaDon", mapper, maKH);
    }

    public List<HoaDonDong> findDongByMaKH(String maKH) {
        return query(SELECT_DONG + " WHERE hd.maKH = ? ORDER BY hd.ngayLap DESC, hd.maHoaDon", dongMapper, maKH);
    }

    public List<HoaDon> findPage(int page, int size) {
        int offset = Math.max(page, 0) * Math.max(size, 1);
        return query(SELECT + " ORDER BY hd.ngayLap DESC, hd.maHoaDon OFFSET ? ROWS FETCH NEXT ? ROWS ONLY",
                mapper, offset, Math.max(size, 1));
    }

    public int nextSo() {
        return (int) queryLong("SELECT ISNULL(MAX(TRY_CAST(maHoaDon AS INT)), 0) FROM HoaDon");
    }

    @Override
    public Integer add(HoaDon entity) {
        String sql = "INSERT INTO HoaDon(maHoaDon,ngayLap,maKH,maKeHoach,maBacSi,chietKhau,ghiChu,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?,?)";
        return execute(sql, entity.getMaHoaDon(), entity.getNgayLap(), Refs.maKh(entity.getKhachHang()),
                Refs.maKhdt(entity.getKeHoachDieuTri()), Refs.maNv(entity.getBacSi()),
                entity.getChietKhau(), entity.getGhiChu(), entity.getNgayTao(), Refs.maNv(entity.getNguoiTao()));
    }

    @Override
    public void update(HoaDon entity) {
        String sql = "UPDATE HoaDon SET ngayLap = ?, maKH = ?, maKeHoach = ?, maBacSi = ?, chietKhau = ?, ghiChu = ?, ngayTao = ?, nguoiTao = ? WHERE maHoaDon = ?";
        execute(sql, entity.getNgayLap(), Refs.maKh(entity.getKhachHang()), Refs.maKhdt(entity.getKeHoachDieuTri()),
                Refs.maNv(entity.getBacSi()), entity.getChietKhau(), entity.getGhiChu(), entity.getNgayTao(),
                Refs.maNv(entity.getNguoiTao()), entity.getMaHoaDon());
    }

    @Override
    public void delete(String maHoaDon) {
        execute("DELETE FROM HoaDon WHERE maHoaDon = ?", maHoaDon);
    }
}
