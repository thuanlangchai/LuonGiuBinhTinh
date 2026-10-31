package repository.impl;

import entity.KhachHang;
import i_repository.KhachHang_IDAO;
import java.util.List;
import mapper.KhachHangMapper;


public class KhachHang_DAO extends AbstractDAO<KhachHang> implements KhachHang_IDAO {

    @Override
    public List<KhachHang> findAllKhachHang() {
        String sql = "SELECT * FROM KhachHang";
        return findAll(sql, new KhachHangMapper());
    }

    @Override
    public KhachHang findById(String maKH) {
        String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
        List<KhachHang> ds = findAll(sql, new KhachHangMapper(), maKH);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(KhachHang entity) {
        String sql = "INSERT INTO KhachHang(maKH,hoTen,gioiTinh,ngaySinh,soDienThoai,email,diaChi,cccd,ghiChu,maNhom,maChiNhanh,lyDoKham,tienSuBenh,diUng,maBacSi,maTuVan,soLanKham,lanKhamGanNhat,ngayTao) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaKH(), entity.getHoTen(), entity.getGioiTinh(), entity.getNgaySinh(), entity.getSoDienThoai(), entity.getEmail(), entity.getDiaChi(), entity.getCccd(), entity.getGhiChu(), entity.getMaNhom(), entity.getMaChiNhanh(), entity.getLyDoKham(), entity.getTienSuBenh(), entity.getDiUng(), entity.getMaBacSi(), entity.getMaTuVan(), entity.getSoLanKham(), entity.getLanKhamGanNhat(), entity.getNgayTao());
    }

    @Override
    public void update(KhachHang entity) {
        String sql = "UPDATE KhachHang SET hoTen = ?, gioiTinh = ?, ngaySinh = ?, soDienThoai = ?, email = ?, diaChi = ?, cccd = ?, ghiChu = ?, maNhom = ?, maChiNhanh = ?, lyDoKham = ?, tienSuBenh = ?, diUng = ?, maBacSi = ?, maTuVan = ?, soLanKham = ?, lanKhamGanNhat = ?, ngayTao = ? WHERE maKH = ?";
        update(sql, entity.getHoTen(), entity.getGioiTinh(), entity.getNgaySinh(), entity.getSoDienThoai(), entity.getEmail(), entity.getDiaChi(), entity.getCccd(), entity.getGhiChu(), entity.getMaNhom(), entity.getMaChiNhanh(), entity.getLyDoKham(), entity.getTienSuBenh(), entity.getDiUng(), entity.getMaBacSi(), entity.getMaTuVan(), entity.getSoLanKham(), entity.getLanKhamGanNhat(), entity.getNgayTao(), entity.getMaKH());
    }

    @Override
    public void delete(String maKH) {
        String sql = "DELETE FROM KhachHang WHERE maKH = ?";
        delete(sql, maKH);
    }
    @Override
    public KhachHang findBySdt(String sdt) {
        String sql = "SELECT * FROM KhachHang WHERE soDienThoai = ?";
        List<KhachHang> ds = findAll(sql, new KhachHangMapper(), sdt);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }
}
