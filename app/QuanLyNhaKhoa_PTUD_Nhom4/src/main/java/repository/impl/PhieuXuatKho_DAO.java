package repository.impl;

import entity.PhieuXuatKho;
import entity.Refs;
import java.util.List;
import mapper.PhieuXuatKhoMapper;
import repository.PhieuXuatKho_IDAO;

public class PhieuXuatKho_DAO extends AbstractDAO<PhieuXuatKho> implements PhieuXuatKho_IDAO {

    private static final String SELECT = """
            SELECT px.maPhieuXuat, px.ngayXuat, px.loaiVatTu, px.lyDo, px.tongTien, px.ngayTao, px.nguoiTao,
                   ncc.maNhaCungCap, ncc.tenNhaCungCap
            FROM PhieuXuatKho px
            LEFT JOIN NhaCungCap ncc ON ncc.maNhaCungCap = px.maNhaCungCap
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
        return execute("INSERT INTO PhieuXuatKho(maPhieuXuat,ngayXuat,loaiVatTu,maNhaCungCap,lyDo,tongTien,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?)",
                entity.getMaPhieuXuat(), entity.getNgayXuat(), entity.getLoaiVatTu(), Refs.maNcc(entity.getNhaCungCap()),
                entity.getLyDo(), entity.getTongTien(), entity.getNgayTao(), entity.getNguoiTao());
    }

    @Override
    public void update(PhieuXuatKho entity) {
        execute("UPDATE PhieuXuatKho SET ngayXuat = ?, loaiVatTu = ?, maNhaCungCap = ?, lyDo = ?, tongTien = ?, ngayTao = ?, nguoiTao = ? WHERE maPhieuXuat = ?",
                entity.getNgayXuat(), entity.getLoaiVatTu(), Refs.maNcc(entity.getNhaCungCap()), entity.getLyDo(),
                entity.getTongTien(), entity.getNgayTao(), entity.getNguoiTao(), entity.getMaPhieuXuat());
    }

    @Override
    public void delete(String maPhieuXuat) {
        execute("DELETE FROM PhieuXuatKho WHERE maPhieuXuat = ?", maPhieuXuat);
    }
}
