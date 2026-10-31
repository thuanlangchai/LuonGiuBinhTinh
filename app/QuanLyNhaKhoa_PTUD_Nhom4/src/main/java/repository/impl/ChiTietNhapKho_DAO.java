package repository.impl;

import entity.ChiTietNhapKho;
import i_repository.ChiTietNhapKho_IDAO;
import java.util.List;
import mapper.ChiTietNhapKhoMapper;


public class ChiTietNhapKho_DAO extends AbstractDAO<ChiTietNhapKho> implements ChiTietNhapKho_IDAO {

    @Override
    public List<ChiTietNhapKho> findAllChiTietNhapKho() {
        String sql = "SELECT * FROM ChiTietNhapKho";
        return findAll(sql, new ChiTietNhapKhoMapper());
    }

    @Override
    public ChiTietNhapKho findById(String maChiTiet) {
        String sql = "SELECT * FROM ChiTietNhapKho WHERE maChiTiet = ?";
        List<ChiTietNhapKho> ds = findAll(sql, new ChiTietNhapKhoMapper(), maChiTiet);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(ChiTietNhapKho entity) {
        String sql = "INSERT INTO ChiTietNhapKho(maChiTiet,maPhieuNhap,maSanPham,soLuong,donGia,thanhTien) VALUES(?,?,?,?,?,?)";
        return add(sql, entity.getMaChiTiet(), entity.getMaPhieuNhap(), entity.getMaSanPham(), entity.getSoLuong(), entity.getDonGia(), entity.getThanhTien());
    }

    @Override
    public void update(ChiTietNhapKho entity) {
        String sql = "UPDATE ChiTietNhapKho SET maPhieuNhap = ?, maSanPham = ?, soLuong = ?, donGia = ?, thanhTien = ? WHERE maChiTiet = ?";
        update(sql, entity.getMaPhieuNhap(), entity.getMaSanPham(), entity.getSoLuong(), entity.getDonGia(), entity.getThanhTien(), entity.getMaChiTiet());
    }

    @Override
    public void delete(String maChiTiet) {
        String sql = "DELETE FROM ChiTietNhapKho WHERE maChiTiet = ?";
        delete(sql, maChiTiet);
    }
    @Override
    public List<ChiTietNhapKho> findByMaPhieuNhap(String maPhieuNhap) {
        String sql = "SELECT * FROM ChiTietNhapKho WHERE maPhieuNhap = ?";
        return findAll(sql, new ChiTietNhapKhoMapper(), maPhieuNhap);
    }
}
