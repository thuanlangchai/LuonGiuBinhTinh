package repository.impl;

import entity.NhomKhachHang;
import i_repository.NhomKhachHang_IDAO;
import java.util.List;
import mapper.NhomKhachHangMapper;

public class NhomKhachHang_DAO extends AbstractDAO<NhomKhachHang> implements NhomKhachHang_IDAO {

    @Override
    public List<NhomKhachHang> findAllNhomKhachHang() {
        String sql = "SELECT * FROM NhomKhachHang";
        return findAll(sql, new NhomKhachHangMapper());
    }

    @Override
    public NhomKhachHang findById(String maNhom) {
        String sql = "SELECT * FROM NhomKhachHang WHERE maNhom = ?";
        List<NhomKhachHang> ds = findAll(sql, new NhomKhachHangMapper(), maNhom);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(NhomKhachHang entity) {
        String sql = "INSERT INTO NhomKhachHang(maNhom,tenNhom) VALUES(?,?)";
        return add(sql, entity.getMaNhom(), entity.getTenNhom());
    }

    @Override
    public void update(NhomKhachHang entity) {
        String sql = "UPDATE NhomKhachHang SET tenNhom = ? WHERE maNhom = ?";
        update(sql, entity.getTenNhom(), entity.getMaNhom());
    }

    @Override
    public void delete(String maNhom) {
        String sql = "DELETE FROM NhomKhachHang WHERE maNhom = ?";
        delete(sql, maNhom);
    }
}
