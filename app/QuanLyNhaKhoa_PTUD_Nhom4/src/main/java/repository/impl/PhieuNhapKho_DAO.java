package repository.impl;

import entity.PhieuNhapKho;
import entity.Refs;
import java.util.List;
import mapper.PhieuNhapKhoMapper;
import repository.PhieuNhapKho_IDAO;

public class PhieuNhapKho_DAO extends AbstractDAO<PhieuNhapKho> implements PhieuNhapKho_IDAO {

    private static final String SELECT = """
            SELECT pn.maPhieuNhap, pn.ngayNhap, pn.loaiVatTu, pn.lyDo, pn.fileExcel, pn.ngayTao, pn.nguoiTao,
                   ISNULL(ct.tongTien, 0) AS tongTien,
                   ncc.maNhaCungCap, ncc.tenNhaCungCap
            FROM PhieuNhapKho pn
            LEFT JOIN NhaCungCap ncc ON ncc.maNhaCungCap = pn.maNhaCungCap
            LEFT JOIN (
                SELECT maPhieuNhap, SUM(CAST(soLuong AS BIGINT) * donGia) AS tongTien
                FROM ChiTietNhapKho
                GROUP BY maPhieuNhap
            ) ct ON ct.maPhieuNhap = pn.maPhieuNhap
            """;

    private final PhieuNhapKhoMapper mapper = new PhieuNhapKhoMapper();

    @Override
    public List<PhieuNhapKho> findAllPhieuNhapKho() {
        return query(SELECT + " ORDER BY pn.ngayNhap DESC, pn.maPhieuNhap", mapper);
    }

    @Override
    public PhieuNhapKho findById(String maPhieuNhap) {
        return one(SELECT + " WHERE pn.maPhieuNhap = ?", mapper, maPhieuNhap);
    }

    @Override
    public Integer add(PhieuNhapKho entity) {
        return execute("INSERT INTO PhieuNhapKho(maPhieuNhap,ngayNhap,loaiVatTu,maNhaCungCap,lyDo,fileExcel,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?)",
                entity.getMaPhieuNhap(), entity.getNgayNhap(), entity.getLoaiVatTu(), Refs.maNcc(entity.getNhaCungCap()),
                entity.getLyDo(), entity.getFileExcel(), entity.getNgayTao(), Refs.maNv(entity.getNguoiTao()));
    }

    @Override
    public void update(PhieuNhapKho entity) {
        execute("UPDATE PhieuNhapKho SET ngayNhap = ?, loaiVatTu = ?, maNhaCungCap = ?, lyDo = ?, fileExcel = ?, ngayTao = ?, nguoiTao = ? WHERE maPhieuNhap = ?",
                entity.getNgayNhap(), entity.getLoaiVatTu(), Refs.maNcc(entity.getNhaCungCap()), entity.getLyDo(),
                entity.getFileExcel(), entity.getNgayTao(), Refs.maNv(entity.getNguoiTao()), entity.getMaPhieuNhap());
    }

    @Override
    public void delete(String maPhieuNhap) {
        execute("DELETE FROM PhieuNhapKho WHERE maPhieuNhap = ?", maPhieuNhap);
    }
}
