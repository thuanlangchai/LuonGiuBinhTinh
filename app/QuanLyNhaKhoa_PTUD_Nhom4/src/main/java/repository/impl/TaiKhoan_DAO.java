package repository.impl;

import entity.TaiKhoan;
import i_repository.TaiKhoan_IDAO;
import java.util.List;
import mapper.TaiKhoanMapper;


public class TaiKhoan_DAO extends AbstractDAO<TaiKhoan> implements TaiKhoan_IDAO {

    @Override
    public List<TaiKhoan> findAllTaiKhoan() {
        String sql = "SELECT * FROM TaiKhoan";
        return findAll(sql, new TaiKhoanMapper());
    }

    @Override
    public TaiKhoan findById(String tenDangNhap) {
        String sql = "SELECT * FROM TaiKhoan WHERE tenDangNhap = ?";
        List<TaiKhoan> ds = findAll(sql, new TaiKhoanMapper(), tenDangNhap);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(TaiKhoan entity) {
        String sql = "INSERT INTO TaiKhoan(tenDangNhap,matKhau,email,vaiTro,maNhanVien,kichHoat,ngayTao) VALUES(?,?,?,?,?,?,?)";
        return add(sql, entity.getTenDangNhap(), entity.getMatKhau(), entity.getEmail(), entity.getVaiTro(), entity.getMaNhanVien(), entity.isKichHoat(), entity.getNgayTao());
    }

    @Override
    public void update(TaiKhoan entity) {
        String sql = "UPDATE TaiKhoan SET matKhau = ?, email = ?, vaiTro = ?, maNhanVien = ?, kichHoat = ?, ngayTao = ? WHERE tenDangNhap = ?";
        update(sql, entity.getMatKhau(), entity.getEmail(), entity.getVaiTro(), entity.getMaNhanVien(), entity.isKichHoat(), entity.getNgayTao(), entity.getTenDangNhap());
    }

    @Override
    public void delete(String tenDangNhap) {
        String sql = "DELETE FROM TaiKhoan WHERE tenDangNhap = ?";
        delete(sql, tenDangNhap);
    }
    @Override
    public TaiKhoan findByTenDangNhap(String tenDangNhap) {
        return findById(tenDangNhap);
    }
}
