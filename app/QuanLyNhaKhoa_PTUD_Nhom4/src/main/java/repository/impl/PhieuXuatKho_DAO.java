package repository.impl;

import entity.PhieuXuatKho;
import i_repository.PhieuXuatKho_IDAO;
import java.util.List;
import mapper.PhieuXuatKhoMapper;


public class PhieuXuatKho_DAO extends AbstractDAO<PhieuXuatKho> implements PhieuXuatKho_IDAO {

    @Override
    public List<PhieuXuatKho> findAllPhieuXuatKho() {
        String sql = "SELECT * FROM PhieuXuatKho";
        return findAll(sql, new PhieuXuatKhoMapper());
    }

    @Override
    public PhieuXuatKho findById(String maPhieuXuat) {
        String sql = "SELECT * FROM PhieuXuatKho WHERE maPhieuXuat = ?";
        List<PhieuXuatKho> ds = findAll(sql, new PhieuXuatKhoMapper(), maPhieuXuat);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(PhieuXuatKho entity) {
        String sql = "INSERT INTO PhieuXuatKho(maPhieuXuat,ngayXuat,loaiVatTu,maNhaCungCap,lyDo,tongTien,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaPhieuXuat(), entity.getNgayXuat(), entity.getLoaiVatTu(), entity.getMaNhaCungCap(), entity.getLyDo(), entity.getTongTien(), entity.getNgayTao(), entity.getNguoiTao());
    }

    @Override
    public void update(PhieuXuatKho entity) {
        String sql = "UPDATE PhieuXuatKho SET ngayXuat = ?, loaiVatTu = ?, maNhaCungCap = ?, lyDo = ?, tongTien = ?, ngayTao = ?, nguoiTao = ? WHERE maPhieuXuat = ?";
        update(sql, entity.getNgayXuat(), entity.getLoaiVatTu(), entity.getMaNhaCungCap(), entity.getLyDo(), entity.getTongTien(), entity.getNgayTao(), entity.getNguoiTao(), entity.getMaPhieuXuat());
    }

    @Override
    public void delete(String maPhieuXuat) {
        String sql = "DELETE FROM PhieuXuatKho WHERE maPhieuXuat = ?";
        delete(sql, maPhieuXuat);
    }
}
