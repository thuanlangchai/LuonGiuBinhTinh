package repository.impl;

import entity.HoaDon;
import entity.Refs;
import java.util.List;
import mapper.HoaDonMapper;
import repository.HoaDon_IDAO;

public class HoaDon_DAO extends AbstractDAO<HoaDon> implements HoaDon_IDAO {

    static final String SELECT = """
            SELECT hd.maHoaDon, hd.ngayLap, hd.tongTien, hd.chietKhau, hd.thanhTien, hd.daThu, hd.conNo,
                   hd.trangThai, hd.ghiChu, hd.ngayTao, hd.nguoiTao,
                   kh.maKH, kh.hoTen AS tenKhachHang,
                   kdt.maKeHoach, kdt.lyDoDieuTri AS lyDoKeHoach,
                   bs.maNhanVien AS maBacSi, bs.hoTen AS tenBacSi,
                   nt.hoTen AS tenNguoiTao              
            FROM HoaDon hd
            JOIN KhachHang kh ON kh.maKH = hd.maKH
            LEFT JOIN KeHoachDieuTri kdt ON kdt.maKeHoach = hd.maKeHoach
            LEFT JOIN NhanVien bs ON bs.maNhanVien = hd.maBacSi
            LEFT JOIN NhanVien nt ON bs.maNhanVien = hd.nguoiTao                     
            """;

    private final HoaDonMapper mapper = new HoaDonMapper();

    @Override
    public List<HoaDon> findAllHoaDon() {
        return query(SELECT + " ORDER BY hd.ngayLap DESC, hd.maHoaDon", mapper);
    }

    @Override
    public HoaDon findById(String maHoaDon) {
        return one(SELECT + " WHERE hd.maHoaDon = ?", mapper, maHoaDon);
    }

    public HoaDon lockById(String maHoaDon) {
        String sql = SELECT.replace("FROM HoaDon hd", "FROM HoaDon hd WITH (UPDLOCK, ROWLOCK)")
                + " WHERE hd.maHoaDon = ?";
        return one(sql, mapper, maHoaDon);
    }

    @Override
    public List<HoaDon> findByMaKH(String maKH) {
        return query(SELECT + " WHERE hd.maKH = ? ORDER BY hd.ngayLap DESC, hd.maHoaDon", mapper, maKH);
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
        String sql = "INSERT INTO HoaDon(maHoaDon,ngayLap,maKH,maKeHoach,maBacSi,tongTien,chietKhau,thanhTien,daThu,conNo,trangThai,ghiChu,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return execute(sql, entity.getMaHoaDon(), entity.getNgayLap(), Refs.maKh(entity.getKhachHang()),
                Refs.maKhdt(entity.getKeHoachDieuTri()), Refs.maNv(entity.getBacSi()), entity.getTongTien(),
                entity.getChietKhau(), entity.getThanhTien(), entity.getDaThu(), entity.getConNo(),
                entity.getTrangThai(), entity.getGhiChu(), entity.getNgayTao(), entity.getNguoiTao());
    }

    @Override
    public void update(HoaDon entity) {
        String sql = "UPDATE HoaDon SET ngayLap = ?, maKH = ?, maKeHoach = ?, maBacSi = ?, tongTien = ?, chietKhau = ?, thanhTien = ?, daThu = ?, conNo = ?, trangThai = ?, ghiChu = ?, ngayTao = ?, nguoiTao = ? WHERE maHoaDon = ?";
        execute(sql, entity.getNgayLap(), Refs.maKh(entity.getKhachHang()), Refs.maKhdt(entity.getKeHoachDieuTri()),
                Refs.maNv(entity.getBacSi()), entity.getTongTien(), entity.getChietKhau(), entity.getThanhTien(),
                entity.getDaThu(), entity.getConNo(), entity.getTrangThai(), entity.getGhiChu(), entity.getNgayTao(),
                entity.getNguoiTao(), entity.getMaHoaDon());
    }

    @Override
    public void delete(String maHoaDon) {
        execute("DELETE FROM HoaDon WHERE maHoaDon = ?", maHoaDon);
    }
}
