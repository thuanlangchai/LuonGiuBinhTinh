package repository.impl;

import entity.Thuoc;
import i_repository.Thuoc_IDAO;
import java.util.List;
import mapper.ThuocMapper;


public class Thuoc_DAO extends AbstractDAO<Thuoc> implements Thuoc_IDAO {

    @Override
    public List<Thuoc> findAllThuoc() {
        String sql = "SELECT * FROM Thuoc";
        return findAll(sql, new ThuocMapper());
    }

    @Override
    public Thuoc findById(String maThuoc) {
        String sql = "SELECT * FROM Thuoc WHERE maThuoc = ?";
        List<Thuoc> ds = findAll(sql, new ThuocMapper(), maThuoc);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(Thuoc entity) {
        String sql = "INSERT INTO Thuoc(maThuoc,tenThuoc,maDonVi,gia,cachDung) VALUES(?,?,?,?,?)";
        return add(sql, entity.getMaThuoc(), entity.getTenThuoc(), entity.getMaDonVi(), entity.getGia(), entity.getCachDung());
    }

    @Override
    public void update(Thuoc entity) {
        String sql = "UPDATE Thuoc SET tenThuoc = ?, maDonVi = ?, gia = ?, cachDung = ? WHERE maThuoc = ?";
        update(sql, entity.getTenThuoc(), entity.getMaDonVi(), entity.getGia(), entity.getCachDung(), entity.getMaThuoc());
    }

    @Override
    public void delete(String maThuoc) {
        String sql = "DELETE FROM Thuoc WHERE maThuoc = ?";
        delete(sql, maThuoc);
    }
}
