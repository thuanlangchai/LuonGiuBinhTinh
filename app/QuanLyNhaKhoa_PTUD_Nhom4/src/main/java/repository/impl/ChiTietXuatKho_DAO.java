package repository.impl;

import entity.ChiTietXuatKho;
import i_repository.ChiTietXuatKho_IDAO;
import java.util.List;
import mapper.ChiTietXuatKhoMapper;


public class ChiTietXuatKho_DAO extends AbstractDAO<ChiTietXuatKho> implements ChiTietXuatKho_IDAO {

    @Override
    public List<ChiTietXuatKho> findAllChiTietXuatKho() {
        String sql = "SELECT * FROM ChiTietXuatKho";
        return findAll(sql, new ChiTietXuatKhoMapper());
    }

    @Override
    public ChiTietXuatKho findById(String maChiTiet) {
        String sql = "SELECT * FROM ChiTietXuatKho WHERE maChiTiet = ?";
        List<ChiTietXuatKho> ds = findAll(sql, new ChiTietXuatKhoMapper(), maChiTiet);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(ChiTietXuatKho entity) {
        String sql = "INSERT INTO ChiTietXuatKho(maChiTiet,maPhieuXuat,maSanPham,soLuong,donGia,thanhTien) VALUES(?,?,?,?,?,?)";
        return add(sql, entity.getMaChiTiet(), entity.getMaPhieuXuat(), entity.getMaSanPham(), entity.getSoLuong(), entity.getDonGia(), entity.getThanhTien());
    }

    @Override
    public void update(ChiTietXuatKho entity) {
        String sql = "UPDATE ChiTietXuatKho SET maPhieuXuat = ?, maSanPham = ?, soLuong = ?, donGia = ?, thanhTien = ? WHERE maChiTiet = ?";
        update(sql, entity.getMaPhieuXuat(), entity.getMaSanPham(), entity.getSoLuong(), entity.getDonGia(), entity.getThanhTien(), entity.getMaChiTiet());
    }

    @Override
    public void delete(String maChiTiet) {
        String sql = "DELETE FROM ChiTietXuatKho WHERE maChiTiet = ?";
        delete(sql, maChiTiet);
    }
    @Override
    public List<ChiTietXuatKho> findByMaPhieuXuat(String maPhieuXuat) {
        String sql = "SELECT * FROM ChiTietXuatKho WHERE maPhieuXuat = ?";
        return findAll(sql, new ChiTietXuatKhoMapper(), maPhieuXuat);
    }
}
