package repository.impl;

import entity.DonVi;
import i_repository.DonVi_IDAO;
import java.util.List;
import mapper.DonViMapper;


public class DonVi_DAO extends AbstractDAO<DonVi> implements DonVi_IDAO {

    @Override
    public List<DonVi> findAllDonVi() {
        String sql = "SELECT * FROM DonVi";
        return findAll(sql, new DonViMapper());
    }

    @Override
    public DonVi findById(String maDonVi) {
        String sql = "SELECT * FROM DonVi WHERE maDonVi = ?";
        List<DonVi> ds = findAll(sql, new DonViMapper(), maDonVi);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(DonVi entity) {
        String sql = "INSERT INTO DonVi(maDonVi,tenDonVi) VALUES(?,?)";
        return add(sql, entity.getMaDonVi(), entity.getTenDonVi());
    }

    @Override
    public void update(DonVi entity) {
        String sql = "UPDATE DonVi SET tenDonVi = ? WHERE maDonVi = ?";
        update(sql, entity.getTenDonVi(), entity.getMaDonVi());
    }

    @Override
    public void delete(String maDonVi) {
        String sql = "DELETE FROM DonVi WHERE maDonVi = ?";
        delete(sql, maDonVi);
    }
}
