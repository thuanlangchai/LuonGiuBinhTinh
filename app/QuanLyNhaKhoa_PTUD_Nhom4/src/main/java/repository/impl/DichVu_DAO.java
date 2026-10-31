package repository.impl;

import entity.DichVu;
import i_repository.DichVu_IDAO;
import java.util.List;
import mapper.DichVuMapper;


public class DichVu_DAO extends AbstractDAO<DichVu> implements DichVu_IDAO {

    @Override
    public List<DichVu> findAllDichVu() {
        String sql = "SELECT * FROM DichVu";
        return findAll(sql, new DichVuMapper());
    }

    @Override
    public DichVu findById(String maDichVu) {
        String sql = "SELECT * FROM DichVu WHERE maDichVu = ?";
        List<DichVu> ds = findAll(sql, new DichVuMapper(), maDichVu);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(DichVu entity) {
        String sql = "INSERT INTO DichVu(maDichVu,maNhomDichVu,tenDichVu,trangThai) VALUES(?,?,?,?)";
        return add(sql, entity.getMaDichVu(), entity.getMaNhomDichVu(), entity.getTenDichVu(), entity.getGia(), entity.getTrangThai());
    }

    @Override
    public void update(DichVu entity) {
        String sql = "UPDATE DichVu SET maNhomDichVu = ?, tenDichVu = ?, gia = ?, trangThai = ? WHERE maDichVu = ?";
        update(sql, entity.getMaNhomDichVu(), entity.getTenDichVu(), entity.getGia(), entity.getTrangThai(), entity.getMaDichVu());
    }

    @Override
    public void delete(String maDichVu) {
        String sql = "DELETE FROM DichVu WHERE maDichVu = ?";
        delete(sql, maDichVu);
    }
}
