package mapper;

import entity.PhieuThu;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhieuThuMapper implements RowMapper<PhieuThu> {

    @Override
    public PhieuThu mapper(ResultSet rs) throws SQLException {
        PhieuThu entity = new PhieuThu();
        entity.setMaPhieuThu(rs.getString("maPhieuThu"));
        entity.setNgayThu(Rs.date(rs, "ngayThu"));
        entity.setLyDo(rs.getString("lyDo"));
        entity.setSoTien(rs.getLong("soTien"));
        entity.setHinhThucThanhToan(rs.getString("hinhThucThanhToan"));
        entity.setTrangThai(rs.getString("trangThai"));
        entity.setNguoiGioiThieu(rs.getString("nguoiGioiThieu"));
        entity.setNgayTao(Rs.dateTime(rs, "ngayTao"));
        entity.setNguoiTao(Maps.nhanVien(rs, "nguoiTao", "tenNguoiTao"));
        entity.setKhachHang(Maps.khachHang(rs, "maKH", "tenKhachHang", null));
        entity.setHoaDon(Maps.hoaDon(rs, "maHoaDon"));
        entity.setBacSi(Maps.nhanVien(rs, "maBacSi", "tenBacSi"));
        entity.setPhuTa(Maps.nhanVien(rs, "maPhuTa", "tenPhuTa"));
        return entity;
    }
}
