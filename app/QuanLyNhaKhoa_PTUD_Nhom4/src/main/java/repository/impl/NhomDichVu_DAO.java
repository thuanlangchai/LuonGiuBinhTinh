package repository.impl;

import entity.NhomDichVu;
import i_repository.NhomDichVu_IDAO;
import java.util.List;
import mapper.NhomDichVuMapper;


public class NhomDichVu_DAO extends AbstractDAO<NhomDichVu> implements NhomDichVu_IDAO {

    @Override
    public List<NhomDichVu> findAllNhomDichVu() {
        String sql = "SELECT * FROM NhomDichVu";
        return findAll(sql, new NhomDichVuMapper());
    }

    @Override
    public NhomDichVu findById(String maNhom) {
        String sql = "SELECT * FROM NhomDichVu WHERE maNhom = ?";
        List<NhomDichVu> ds = findAll(sql, new NhomDichVuMapper(), maNhom);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(NhomDichVu entity) {
        String sql = "INSERT INTO NhomDichVu(maNhom,tenNhomDichVu,moTa) VALUES(?,?,?)";
        return add(sql, entity.getMaNhom(), entity.getTenNhomDichVu(), entity.getMoTa());
    }

    @Override
    public void update(NhomDichVu entity) {
        String sql = "UPDATE NhomDichVu SET tenNhomDichVu = ?, moTa = ? WHERE maNhom = ?";
        update(sql, entity.getTenNhomDichVu(), entity.getMoTa(), entity.getMaNhom());
    }

    @Override
    public void delete(String maNhom) {
        String sql = "DELETE FROM NhomDichVu WHERE maNhom = ?";
        delete(sql, maNhom);
    }
}
