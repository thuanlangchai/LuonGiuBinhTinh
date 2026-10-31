package repository.impl;

import entity.PhieuChi;
import i_repository.PhieuChi_IDAO;
import java.util.List;
import mapper.PhieuChiMapper;


public class PhieuChi_DAO extends AbstractDAO<PhieuChi> implements PhieuChi_IDAO {

    @Override
    public List<PhieuChi> findAllPhieuChi() {
        String sql = "SELECT * FROM PhieuChi";
        return findAll(sql, new PhieuChiMapper());
    }

    @Override
    public PhieuChi findById(String maPhieuChi) {
        String sql = "SELECT * FROM PhieuChi WHERE maPhieuChi = ?";
        List<PhieuChi> ds = findAll(sql, new PhieuChiMapper(), maPhieuChi);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(PhieuChi entity) {
        String sql = "INSERT INTO PhieuChi(maPhieuChi,maNhaCungCap,loaiChiTieu,lyDoChi,soTien,ngayChi,hinhThucThanhToan,trangThai,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaPhieuChi(), entity.getMaNhaCungCap(), entity.getLoaiChiTieu(), entity.getLyDoChi(), entity.getSoTien(), entity.getNgayChi(), entity.getHinhThucThanhToan(), entity.getTrangThai(), entity.getNgayTao(), entity.getNguoiTao());
    }

    @Override
    public void update(PhieuChi entity) {
        String sql = "UPDATE PhieuChi SET maNhaCungCap = ?, loaiChiTieu = ?, lyDoChi = ?, soTien = ?, ngayChi = ?, hinhThucThanhToan = ?, trangThai = ?, ngayTao = ?, nguoiTao = ? WHERE maPhieuChi = ?";
        update(sql, entity.getMaNhaCungCap(), entity.getLoaiChiTieu(), entity.getLyDoChi(), entity.getSoTien(), entity.getNgayChi(), entity.getHinhThucThanhToan(), entity.getTrangThai(), entity.getNgayTao(), entity.getNguoiTao(), entity.getMaPhieuChi());
    }

    @Override
    public void delete(String maPhieuChi) {
        String sql = "DELETE FROM PhieuChi WHERE maPhieuChi = ?";
        delete(sql, maPhieuChi);
    }
}
