package repository.impl;

import entity.DieuTri;
import i_repository.DieuTri_IDAO;
import java.util.List;
import mapper.DieuTriMapper;


public class DieuTri_DAO extends AbstractDAO<DieuTri> implements DieuTri_IDAO {

    @Override
    public List<DieuTri> findAllDieuTri() {
        String sql = "SELECT * FROM DieuTri";
        return findAll(sql, new DieuTriMapper());
    }

    @Override
    public DieuTri findById(String maDieuTri) {
        String sql = "SELECT * FROM DieuTri WHERE maDieuTri = ?";
        List<DieuTri> ds = findAll(sql, new DieuTriMapper(), maDieuTri);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(DieuTri entity) {
        String sql = "INSERT INTO DieuTri(maDieuTri,tenDieuTri) VALUES(?,?)";
        return add(sql, entity.getMaDieuTri(), entity.getTenDieuTri());
    }

    @Override
    public void update(DieuTri entity) {
        String sql = "UPDATE DieuTri SET tenDieuTri = ? WHERE maDieuTri = ?";
        update(sql, entity.getTenDieuTri(), entity.getMaDieuTri());
    }

    @Override
    public void delete(String maDieuTri) {
        String sql = "DELETE FROM DieuTri WHERE maDieuTri = ?";
        delete(sql, maDieuTri);
    }
}
