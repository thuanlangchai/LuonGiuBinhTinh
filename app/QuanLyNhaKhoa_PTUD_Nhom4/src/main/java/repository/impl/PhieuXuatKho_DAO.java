package repository.impl;

import entity.PhieuXuatKho;
import entity.Refs;
import java.util.List;
import mapper.PhieuXuatKhoMapper;
import repository.PhieuXuatKho_IDAO;

public class PhieuXuatKho_DAO extends AbstractDAO<PhieuXuatKho> implements PhieuXuatKho_IDAO {

    private static final String SELECT = """
            SELECT px.maPhieuXuat, px.ngayXuat, px.loaiVatTu, px.lyDo, px.ngayTao, px.nguoiTao,
                   ISNULL(ct.tongTien, 0) AS tongTien,
                   ncc.maNhaCungCap, ncc.tenNhaCungCap
            FROM PhieuXuatKho px
            LEFT JOIN NhaCungCap ncc ON ncc.maNhaCungCap = px.maNhaCungCap
            LEFT JOIN (
                SELECT maPhieuXuat, SUM(CAST(soLuong AS BIGINT) * donGia) AS tongTien
                FROM ChiTietXuatKho
                GROUP BY maPhieuXuat
            ) ct ON ct.maPhieuXuat = px.maPhieuXuat
            """;

    private final PhieuXuatKhoMapper mapper = new PhieuXuatKhoMapper();

    @Override
    public List<PhieuXuatKho> findAllPhieuXuatKho() {
        return query(SELECT + " ORDER BY px.ngayXuat DESC, px.maPhieuXuat", mapper);
    }

    @Override
    public PhieuXuatKho findById(String maPhieuXuat) {
        return one(SELECT + " WHERE px.maPhieuXuat = ?", mapper, maPhieuXuat);
    }

    @Override
    public Integer add(PhieuXuatKho entity) {
        return execute("INSERT INTO PhieuXuatKho(maPhieuXuat,ngayXuat,loaiVatTu,maNhaCungCap,lyDo,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?)",
                entity.getMaPhieuXuat(), entity.getNgayXuat(), entity.getLoaiVatTu(), Refs.maNcc(entity.getNhaCungCap()),
                entity.getLyDo(), entity.getNgayTao(), Refs.maNv(entity.getNguoiTao()));
    }

    @Override
    public void update(PhieuXuatKho entity) {
        execute("UPDATE PhieuXuatKho SET ngayXuat = ?, loaiVatTu = ?, maNhaCungCap = ?, lyDo = ?, ngayTao = ?, nguoiTao = ? WHERE maPhieuXuat = ?",
                entity.getNgayXuat(), entity.getLoaiVatTu(), Refs.maNcc(entity.getNhaCungCap()), entity.getLyDo(),
                entity.getNgayTao(), Refs.maNv(entity.getNguoiTao()), entity.getMaPhieuXuat());
    }

    @Override
    public void delete(String maPhieuXuat) {
        execute("DELETE FROM PhieuXuatKho WHERE maPhieuXuat = ?", maPhieuXuat);
    }
}
