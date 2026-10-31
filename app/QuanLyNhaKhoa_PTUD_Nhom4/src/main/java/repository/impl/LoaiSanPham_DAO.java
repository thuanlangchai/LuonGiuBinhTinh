package repository.impl;

import entity.LoaiSanPham;
import i_repository.LoaiSanPham_IDAO;
import java.util.List;
import mapper.LoaiSanPhamMapper;


public class LoaiSanPham_DAO extends AbstractDAO<LoaiSanPham> implements LoaiSanPham_IDAO {

    @Override
    public List<LoaiSanPham> findAllLoaiSanPham() {
        String sql = "SELECT * FROM LoaiSanPham";
        return findAll(sql, new LoaiSanPhamMapper());
    }

    @Override
    public LoaiSanPham findById(String maLoai) {
        String sql = "SELECT * FROM LoaiSanPham WHERE maLoai = ?";
        List<LoaiSanPham> ds = findAll(sql, new LoaiSanPhamMapper(), maLoai);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(LoaiSanPham entity) {
        String sql = "INSERT INTO LoaiSanPham(maLoai,tenLoaiSanPham) VALUES(?,?)";
        return add(sql, entity.getMaLoai(), entity.getTenLoaiSanPham());
    }

    @Override
    public void update(LoaiSanPham entity) {
        String sql = "UPDATE LoaiSanPham SET tenLoaiSanPham = ? WHERE maLoai = ?";
        update(sql, entity.getTenLoaiSanPham(), entity.getMaLoai());
    }

    @Override
    public void delete(String maLoai) {
        String sql = "DELETE FROM LoaiSanPham WHERE maLoai = ?";
        delete(sql, maLoai);
    }
}
