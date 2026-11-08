package repository.impl;

import entity.KeHoachDieuTri;
import entity.Refs;
import java.util.List;
import mapper.KeHoachDieuTriMapper;
import repository.KeHoachDieuTri_IDAO;

public class KeHoachDieuTri_DAO extends AbstractDAO<KeHoachDieuTri> implements KeHoachDieuTri_IDAO {

    private static final String SELECT = """
            SELECT khdt.maKeHoach, khdt.ngayKeHoach, khdt.lyDoDieuTri, khdt.ghiChu, khdt.trangThai, khdt.ngayTao, khdt.nguoiTao,
                   kh.maKH, kh.hoTen AS tenKhachHang,
                   bs.maNhanVien AS maBacSi, bs.hoTen AS tenBacSi,
                   nt.hoTen AS tenNguoiTao                      
            FROM KeHoachDieuTri khdt
            JOIN KhachHang kh ON kh.maKH = khdt.maKH
            LEFT JOIN NhanVien bs ON bs.maNhanVien = khdt.maBacSi
            LEFT JOIN NhanVien nt ON bs.maNhanVien = khdt.nguoiTao                           
            """;

    private final KeHoachDieuTriMapper mapper = new KeHoachDieuTriMapper();

    @Override
    public List<KeHoachDieuTri> findAllKeHoachDieuTri() {
        return query(SELECT + " ORDER BY khdt.ngayKeHoach DESC, khdt.maKeHoach", mapper);
    }

    @Override
    public KeHoachDieuTri findById(String maKeHoach) {
        return one(SELECT + " WHERE khdt.maKeHoach = ?", mapper, maKeHoach);
    }

    @Override
    public List<KeHoachDieuTri> findByMaKH(String maKH) {
        return query(SELECT + " WHERE khdt.maKH = ? ORDER BY khdt.ngayKeHoach DESC", mapper, maKH);
    }

    public List<KeHoachDieuTri> findPage(int page, int size) {
        int offset = Math.max(page, 0) * Math.max(size, 1);
        return query(SELECT + " ORDER BY khdt.ngayKeHoach DESC, khdt.maKeHoach OFFSET ? ROWS FETCH NEXT ? ROWS ONLY",
                mapper, offset, Math.max(size, 1));
    }

    public int nextSo() {
        return (int) queryLong("SELECT ISNULL(MAX(TRY_CAST(SUBSTRING(maKeHoach, 3, 20) AS INT)), 0) FROM KeHoachDieuTri WHERE maKeHoach LIKE N'KH%'");
    }

    @Override
    public Integer add(KeHoachDieuTri entity) {
        String sql = "INSERT INTO KeHoachDieuTri(maKeHoach,maKH,ngayKeHoach,maBacSi,lyDoDieuTri,ghiChu,trangThai,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?,?)";
        return execute(sql, entity.getMaKeHoach(), Refs.maKh(entity.getKhachHang()), entity.getNgayKeHoach(),
                Refs.maNv(entity.getBacSi()), entity.getLyDoDieuTri(), entity.getGhiChu(), entity.getTrangThai(),
                entity.getNgayTao(), entity.getNguoiTao());
    }

    @Override
    public void update(KeHoachDieuTri entity) {
        String sql = "UPDATE KeHoachDieuTri SET maKH = ?, ngayKeHoach = ?, maBacSi = ?, lyDoDieuTri = ?, ghiChu = ?, trangThai = ?, ngayTao = ?, nguoiTao = ? WHERE maKeHoach = ?";
        execute(sql, Refs.maKh(entity.getKhachHang()), entity.getNgayKeHoach(), Refs.maNv(entity.getBacSi()),
                entity.getLyDoDieuTri(), entity.getGhiChu(), entity.getTrangThai(), entity.getNgayTao(),
                entity.getNguoiTao(), entity.getMaKeHoach());
    }

    @Override
    public void delete(String maKeHoach) {
        execute("DELETE FROM KeHoachDieuTri WHERE maKeHoach = ?", maKeHoach);
    }
}
