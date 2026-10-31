package repository.impl;

import entity.PhieuNhapKho;
import i_repository.PhieuNhapKho_IDAO;
import java.util.List;
import mapper.PhieuNhapKhoMapper;


public class PhieuNhapKho_DAO extends AbstractDAO<PhieuNhapKho> implements PhieuNhapKho_IDAO {

    @Override
    public List<PhieuNhapKho> findAllPhieuNhapKho() {
        String sql = "SELECT * FROM PhieuNhapKho";
        return findAll(sql, new PhieuNhapKhoMapper());
    }

    @Override
    public PhieuNhapKho findById(String maPhieuNhap) {
        String sql = "SELECT * FROM PhieuNhapKho WHERE maPhieuNhap = ?";
        List<PhieuNhapKho> ds = findAll(sql, new PhieuNhapKhoMapper(), maPhieuNhap);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(PhieuNhapKho entity) {
        String sql = "INSERT INTO PhieuNhapKho(maPhieuNhap,ngayNhap,loaiVatTu,maNhaCungCap,lyDo,tongTien,fileExcel,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaPhieuNhap(), entity.getNgayNhap(), entity.getLoaiVatTu(), entity.getMaNhaCungCap(), entity.getLyDo(), entity.getTongTien(), entity.getFileExcel(), entity.getNgayTao(), entity.getNguoiTao());
    }

    @Override
    public void update(PhieuNhapKho entity) {
        String sql = "UPDATE PhieuNhapKho SET ngayNhap = ?, loaiVatTu = ?, maNhaCungCap = ?, lyDo = ?, tongTien = ?, fileExcel = ?, ngayTao = ?, nguoiTao = ? WHERE maPhieuNhap = ?";
        update(sql, entity.getNgayNhap(), entity.getLoaiVatTu(), entity.getMaNhaCungCap(), entity.getLyDo(), entity.getTongTien(), entity.getFileExcel(), entity.getNgayTao(), entity.getNguoiTao(), entity.getMaPhieuNhap());
    }

    @Override
    public void delete(String maPhieuNhap) {
        String sql = "DELETE FROM PhieuNhapKho WHERE maPhieuNhap = ?";
        delete(sql, maPhieuNhap);
    }
}
