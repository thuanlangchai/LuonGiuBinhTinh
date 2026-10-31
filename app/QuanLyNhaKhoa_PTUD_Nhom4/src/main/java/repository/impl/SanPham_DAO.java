package repository.impl;

import entity.SanPham;
import i_repository.SanPham_IDAO;
import java.util.List;
import mapper.SanPhamMapper;

public class SanPham_DAO extends AbstractDAO<SanPham> implements SanPham_IDAO {

    @Override
    public List<SanPham> findAllSanPham() {
        String sql = "SELECT * FROM SanPham";
        return findAll(sql, new SanPhamMapper());
    }

    @Override
    public SanPham findById(String maSanPham) {
        String sql = "SELECT * FROM SanPham WHERE maSanPham = ?";
        List<SanPham> ds = findAll(sql, new SanPhamMapper(), maSanPham);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(SanPham entity) {
        String sql = "INSERT INTO SanPham(maSanPham,tenSanPham,maLoai,soLuong,donGia) VALUES(?,?,?,?,?)";
        return add(sql, entity.getMaSanPham(), entity.getTenSanPham(), entity.getMaLoai(), entity.getSoLuong(), entity.getDonGia());
    }

    @Override
    public void update(SanPham entity) {
        String sql = "UPDATE SanPham SET tenSanPham = ?, maLoai = ?, soLuong = ?, donGia = ? WHERE maSanPham = ?";
        update(sql, entity.getTenSanPham(), entity.getMaLoai(), entity.getSoLuong(), entity.getDonGia(), entity.getMaSanPham());
    }

    @Override
    public void delete(String maSanPham) {
        String sql = "DELETE FROM SanPham WHERE maSanPham = ?";
        delete(sql, maSanPham);
    }
}
