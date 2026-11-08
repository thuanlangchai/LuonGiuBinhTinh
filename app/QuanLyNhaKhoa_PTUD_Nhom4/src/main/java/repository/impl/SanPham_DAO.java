package repository.impl;

import entity.Refs;
import entity.SanPham;
import java.util.List;
import mapper.MaMapper;
import mapper.SanPhamMapper;
import repository.SanPham_IDAO;

public class SanPham_DAO extends AbstractDAO<SanPham> implements SanPham_IDAO {

    private static final String SELECT = """
            SELECT sp.maSanPham, sp.tenSanPham, sp.soLuong, sp.donGia,
                   lsp.maLoai, lsp.tenLoaiSanPham
            FROM SanPham sp
            LEFT JOIN LoaiSanPham lsp ON lsp.maLoai = sp.maLoai
            """;

    private final SanPhamMapper mapper = new SanPhamMapper();

    @Override
    public List<SanPham> findAllSanPham() {
        return query(SELECT + " ORDER BY sp.maSanPham", mapper);
    }

    @Override
    public SanPham findById(String maSanPham) {
        return one(SELECT + " WHERE sp.maSanPham = ?", mapper, maSanPham);
    }

    public List<String> findExisting(List<String> ids) {
        return queryIn("SELECT maSanPham FROM SanPham WHERE maSanPham", ids, new MaMapper("maSanPham"));
    }

    public int congTon(String maSanPham, int soLuong) {
        return execute("UPDATE SanPham SET soLuong = soLuong + ? WHERE maSanPham = ?", soLuong, maSanPham);
    }

    public int truTon(String maSanPham, int soLuong) {
        return execute("UPDATE SanPham SET soLuong = soLuong - ? WHERE maSanPham = ? AND soLuong >= ?", soLuong, maSanPham, soLuong);
    }

    @Override
    public Integer add(SanPham entity) {
        return execute("INSERT INTO SanPham(maSanPham,tenSanPham,maLoai,soLuong,donGia) VALUES(?,?,?,?,?)",
                entity.getMaSanPham(), entity.getTenSanPham(), Refs.maLoaiSp(entity.getLoaiSanPham()),
                entity.getSoLuong(), entity.getDonGia());
    }

    @Override
    public void update(SanPham entity) {
        execute("UPDATE SanPham SET tenSanPham = ?, maLoai = ?, soLuong = ?, donGia = ? WHERE maSanPham = ?",
                entity.getTenSanPham(), Refs.maLoaiSp(entity.getLoaiSanPham()), entity.getSoLuong(),
                entity.getDonGia(), entity.getMaSanPham());
    }

    @Override
    public void delete(String maSanPham) {
        execute("DELETE FROM SanPham WHERE maSanPham = ?", maSanPham);
    }
}
