package repository.impl;

import entity.ChiTietToaThuoc;
import i_repository.ChiTietToaThuoc_IDAO;
import java.util.List;
import mapper.ChiTietToaThuocMapper;


public class ChiTietToaThuoc_DAO extends AbstractDAO<ChiTietToaThuoc> implements ChiTietToaThuoc_IDAO {

    @Override
    public List<ChiTietToaThuoc> findAllChiTietToaThuoc() {
        String sql = "SELECT * FROM ChiTietToaThuoc";
        return findAll(sql, new ChiTietToaThuocMapper());
    }

    @Override
    public ChiTietToaThuoc findById(String maChiTiet) {
        String sql = "SELECT * FROM ChiTietToaThuoc WHERE maChiTiet = ?";
        List<ChiTietToaThuoc> ds = findAll(sql, new ChiTietToaThuocMapper(), maChiTiet);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(ChiTietToaThuoc entity) {
        String sql = "INSERT INTO ChiTietToaThuoc(maChiTiet,maToaThuoc,maThuoc,soNgay,cachSuDung,tongSo,donGia,thanhTien) VALUES(?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaChiTiet(), entity.getMaToaThuoc(), entity.getMaThuoc(), entity.getSoNgay(), entity.getCachSuDung(), entity.getTongSo(), entity.getDonGia(), entity.getThanhTien());
    }

    @Override
    public void update(ChiTietToaThuoc entity) {
        String sql = "UPDATE ChiTietToaThuoc SET maToaThuoc = ?, maThuoc = ?, soNgay = ?, cachSuDung = ?, tongSo = ?, donGia = ?, thanhTien = ? WHERE maChiTiet = ?";
        update(sql, entity.getMaToaThuoc(), entity.getMaThuoc(), entity.getSoNgay(), entity.getCachSuDung(), entity.getTongSo(), entity.getDonGia(), entity.getThanhTien(), entity.getMaChiTiet());
    }

    @Override
    public void delete(String maChiTiet) {
        String sql = "DELETE FROM ChiTietToaThuoc WHERE maChiTiet = ?";
        delete(sql, maChiTiet);
    }
    @Override
    public List<ChiTietToaThuoc> findByMaToaThuoc(String maToaThuoc) {
        String sql = "SELECT * FROM ChiTietToaThuoc WHERE maToaThuoc = ?";
        return findAll(sql, new ChiTietToaThuocMapper(), maToaThuoc);
    }
}
