package repository.impl;

import entity.PhieuChi;
import entity.Refs;
import java.util.List;
import mapper.PhieuChiMapper;
import repository.PhieuChi_IDAO;

public class PhieuChi_DAO extends AbstractDAO<PhieuChi> implements PhieuChi_IDAO {

    private static final String SELECT = """
            SELECT pc.maPhieuChi, pc.loaiChiTieu, pc.lyDoChi, pc.soTien, pc.ngayChi, pc.hinhThucThanhToan,
                   pc.trangThai, pc.ngayTao, pc.nguoiTao,
                   ncc.maNhaCungCap, ncc.tenNhaCungCap,
                   nt.hoTen AS tenNguoiTao                       
            FROM PhieuChi pc
            LEFT JOIN NhaCungCap ncc ON ncc.maNhaCungCap = pc.maNhaCungCap
            LEFT JOIN NhanVien nt ON nt.maNhanVien = pc.nguoiTao                             
            """;

    private final PhieuChiMapper mapper = new PhieuChiMapper();

    @Override
    public List<PhieuChi> findAllPhieuChi() {
        return query(SELECT + " ORDER BY pc.ngayChi DESC, pc.maPhieuChi", mapper);
    }

    @Override
    public PhieuChi findById(String maPhieuChi) {
        return one(SELECT + " WHERE pc.maPhieuChi = ?", mapper, maPhieuChi);
    }

    public List<PhieuChi> findPage(int page, int size) {
        int offset = Math.max(page, 0) * Math.max(size, 1);
        return query(SELECT + " ORDER BY pc.ngayChi DESC, pc.maPhieuChi OFFSET ? ROWS FETCH NEXT ? ROWS ONLY",
                mapper, offset, Math.max(size, 1));
    }

    public int nextSo() {
        return (int) queryLong("SELECT ISNULL(MAX(TRY_CAST(SUBSTRING(maPhieuChi, 3, 20) AS INT)), 0) FROM PhieuChi WHERE maPhieuChi LIKE N'PC%'");
    }

    @Override
    public Integer add(PhieuChi entity) {
        String sql = "INSERT INTO PhieuChi(maPhieuChi,maNhaCungCap,loaiChiTieu,lyDoChi,soTien,ngayChi,hinhThucThanhToan,trangThai,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?,?,?)";
        return execute(sql, entity.getMaPhieuChi(), Refs.maNcc(entity.getNhaCungCap()), entity.getLoaiChiTieu(),
                entity.getLyDoChi(), entity.getSoTien(), entity.getNgayChi(), entity.getHinhThucThanhToan(),
                entity.getTrangThai(), entity.getNgayTao(), entity.getNguoiTao());
    }

    @Override
    public void update(PhieuChi entity) {
        String sql = "UPDATE PhieuChi SET maNhaCungCap = ?, loaiChiTieu = ?, lyDoChi = ?, soTien = ?, ngayChi = ?, hinhThucThanhToan = ?, trangThai = ?, ngayTao = ?, nguoiTao = ? WHERE maPhieuChi = ?";
        execute(sql, Refs.maNcc(entity.getNhaCungCap()), entity.getLoaiChiTieu(), entity.getLyDoChi(), entity.getSoTien(),
                entity.getNgayChi(), entity.getHinhThucThanhToan(), entity.getTrangThai(), entity.getNgayTao(),
                entity.getNguoiTao(), entity.getMaPhieuChi());
    }

    @Override
    public void delete(String maPhieuChi) {
        execute("DELETE FROM PhieuChi WHERE maPhieuChi = ?", maPhieuChi);
    }
}
