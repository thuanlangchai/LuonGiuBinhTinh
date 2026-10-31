package repository.impl;

import entity.ChiNhanh;
import i_repository.ChiNhanh_IDAO;
import java.util.List;
import mapper.ChiNhanhMapper;


public class ChiNhanh_DAO extends AbstractDAO<ChiNhanh> implements ChiNhanh_IDAO {

    @Override
    public List<ChiNhanh> findAllChiNhanh() {
        String sql = "SELECT * FROM ChiNhanh";
        return findAll(sql, new ChiNhanhMapper());
    }

    @Override
    public ChiNhanh findById(String maChiNhanh) {
        String sql = "SELECT * FROM ChiNhanh WHERE maChiNhanh = ?";
        List<ChiNhanh> ds = findAll(sql, new ChiNhanhMapper(), maChiNhanh);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(ChiNhanh entity) {
        String sql = "INSERT INTO ChiNhanh(maChiNhanh,tenChiNhanh,diaChi,dienThoai) VALUES(?,?,?,?)";
        return add(sql, entity.getMaChiNhanh(), entity.getTenChiNhanh(), entity.getDiaChi(), entity.getDienThoai());
    }

    @Override
    public void update(ChiNhanh entity) {
        String sql = "UPDATE ChiNhanh SET tenChiNhanh = ?, diaChi = ?, dienThoai = ? WHERE maChiNhanh = ?";
        update(sql, entity.getTenChiNhanh(), entity.getDiaChi(), entity.getDienThoai(), entity.getMaChiNhanh());
    }

    @Override
    public void delete(String maChiNhanh) {
        String sql = "DELETE FROM ChiNhanh WHERE maChiNhanh = ?";
        delete(sql, maChiNhanh);
    }
}
