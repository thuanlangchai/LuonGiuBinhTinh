package repository.impl;

import entity.NhanVien;
import i_repository.NhanVien_IDAO;
import java.util.List;
import mapper.NhanVienMapper;


public class NhanVien_DAO extends AbstractDAO<NhanVien> implements NhanVien_IDAO {

    @Override
    public List<NhanVien> findAllNhanVien() {
        String sql = "SELECT * FROM NhanVien";
        return findAll(sql, new NhanVienMapper());
    }

    @Override
    public NhanVien findById(String maNhanVien) {
        String sql = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
        List<NhanVien> ds = findAll(sql, new NhanVienMapper(), maNhanVien);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(NhanVien entity) {
        String sql = "INSERT INTO NhanVien(maNhanVien,chucDanh,hoTen,email,soDienThoai,vaiTro,kichHoat,maChiNhanh) VALUES(?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaNhanVien(), entity.getChucDanh(), entity.getHoTen(), entity.getEmail(), entity.getSoDienThoai(), entity.getVaiTro(), entity.isKichHoat(), entity.getMaChiNhanh());
    }

    @Override
    public void update(NhanVien entity) {
        String sql = "UPDATE NhanVien SET chucDanh = ?, hoTen = ?, email = ?, soDienThoai = ?, vaiTro = ?, kichHoat = ?, maChiNhanh = ? WHERE maNhanVien = ?";
        update(sql, entity.getChucDanh(), entity.getHoTen(), entity.getEmail(), entity.getSoDienThoai(), entity.getVaiTro(), entity.isKichHoat(), entity.getMaChiNhanh(), entity.getMaNhanVien());
    }

    @Override
    public void delete(String maNhanVien) {
        String sql = "DELETE FROM NhanVien WHERE maNhanVien = ?";
        delete(sql, maNhanVien);
    }
}
