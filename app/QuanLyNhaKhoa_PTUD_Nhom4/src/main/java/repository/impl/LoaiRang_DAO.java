package repository.impl;

import entity.LoaiRang;
import i_repository.LoaiRang_IDAO;
import java.util.List;
import mapper.LoaiRangMapper;


public class LoaiRang_DAO extends AbstractDAO<LoaiRang> implements LoaiRang_IDAO {

    @Override
    public List<LoaiRang> findAllLoaiRang() {
        String sql = "SELECT * FROM LoaiRang";
        return findAll(sql, new LoaiRangMapper());
    }

    @Override
    public LoaiRang findById(String maLoaiRang) {
        String sql = "SELECT * FROM LoaiRang WHERE maLoaiRang = ?";
        List<LoaiRang> ds = findAll(sql, new LoaiRangMapper(), maLoaiRang);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(LoaiRang entity) {
        String sql = "INSERT INTO LoaiRang(maLoaiRang,tenLoaiRang) VALUES(?,?)";
        return add(sql, entity.getMaLoaiRang(), entity.getTenLoaiRang());
    }

    @Override
    public void update(LoaiRang entity) {
        String sql = "UPDATE LoaiRang SET tenLoaiRang = ? WHERE maLoaiRang = ?";
        update(sql, entity.getTenLoaiRang(), entity.getMaLoaiRang());
    }

    @Override
    public void delete(String maLoaiRang) {
        String sql = "DELETE FROM LoaiRang WHERE maLoaiRang = ?";
        delete(sql, maLoaiRang);
    }
}
