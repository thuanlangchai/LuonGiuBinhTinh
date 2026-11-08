package repository.impl;

import entity.Refs;
import entity.ToaThuoc;
import java.util.List;
import mapper.ToaThuocMapper;
import repository.ToaThuoc_IDAO;

public class ToaThuoc_DAO extends AbstractDAO<ToaThuoc> implements ToaThuoc_IDAO {

    private static final String SELECT = """
            SELECT tt.maToaThuoc, tt.maChanDoan, tt.hinhThucThanhToan, tt.nhanTaiPhongKham, tt.ghiChu, tt.thanhTien, tt.ngayTao,
                   kh.maKH, kh.hoTen AS tenKhachHang,
                   bs.maNhanVien AS maBacSi, bs.hoTen AS tenBacSi
            FROM ToaThuoc tt
            JOIN KhachHang kh ON kh.maKH = tt.maKH
            LEFT JOIN NhanVien bs ON bs.maNhanVien = tt.maBacSi
            """;

    private final ToaThuocMapper mapper = new ToaThuocMapper();

    @Override
    public List<ToaThuoc> findAllToaThuoc() {
        return query(SELECT + " ORDER BY tt.ngayTao DESC, tt.maToaThuoc", mapper);
    }

    @Override
    public ToaThuoc findById(String maToaThuoc) {
        return one(SELECT + " WHERE tt.maToaThuoc = ?", mapper, maToaThuoc);
    }

    @Override
    public List<ToaThuoc> findByMaKH(String maKH) {
        return query(SELECT + " WHERE tt.maKH = ? ORDER BY tt.ngayTao DESC", mapper, maKH);
    }

    public List<ToaThuoc> findPage(int page, int size) {
        int offset = Math.max(page, 0) * Math.max(size, 1);
        return query(SELECT + " ORDER BY tt.ngayTao DESC, tt.maToaThuoc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY",
                mapper, offset, Math.max(size, 1));
    }

    public int nextSo() {
        return (int) queryLong("SELECT ISNULL(MAX(TRY_CAST(SUBSTRING(maToaThuoc, 3, 20) AS INT)), 0) FROM ToaThuoc WHERE maToaThuoc LIKE N'TT%'");
    }

    @Override
    public Integer add(ToaThuoc entity) {
        String sql = "INSERT INTO ToaThuoc(maToaThuoc,maKH,maChanDoan,maBacSi,hinhThucThanhToan,nhanTaiPhongKham,ghiChu,thanhTien,ngayTao) VALUES(?,?,?,?,?,?,?,?,?)";
        return execute(sql, entity.getMaToaThuoc(), Refs.maKh(entity.getKhachHang()), entity.getMaChanDoan(),
                Refs.maNv(entity.getBacSi()), entity.getHinhThucThanhToan(), entity.isNhanTaiPhongKham(),
                entity.getGhiChu(), entity.getThanhTien(), entity.getNgayTao());
    }

    @Override
    public void update(ToaThuoc entity) {
        String sql = "UPDATE ToaThuoc SET maKH = ?, maChanDoan = ?, maBacSi = ?, hinhThucThanhToan = ?, nhanTaiPhongKham = ?, ghiChu = ?, thanhTien = ?, ngayTao = ? WHERE maToaThuoc = ?";
        execute(sql, Refs.maKh(entity.getKhachHang()), entity.getMaChanDoan(), Refs.maNv(entity.getBacSi()),
                entity.getHinhThucThanhToan(), entity.isNhanTaiPhongKham(), entity.getGhiChu(), entity.getThanhTien(),
                entity.getNgayTao(), entity.getMaToaThuoc());
    }

    @Override
    public void delete(String maToaThuoc) {
        execute("DELETE FROM ToaThuoc WHERE maToaThuoc = ?", maToaThuoc);
    }
}
