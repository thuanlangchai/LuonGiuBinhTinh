package mapper;

import entity.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDonMapper implements RowMapper<HoaDon> {

    @Override
    public HoaDon mapper(ResultSet rs) throws SQLException {
        HoaDon entity = new HoaDon();
        entity.setMaHoaDon(rs.getString("maHoaDon"));
        entity.setNgayLap(Rs.date(rs, "ngayLap"));
        entity.setTongTien(rs.getLong("tongTien"));
        entity.setChietKhau(rs.getLong("chietKhau"));
        entity.setThanhTien(rs.getLong("thanhTien"));
        entity.setDaThu(rs.getLong("daThu"));
        entity.setConNo(rs.getLong("conNo"));
        entity.setTrangThai(rs.getString("trangThai"));
        entity.setGhiChu(rs.getString("ghiChu"));
        entity.setNgayTao(Rs.dateTime(rs, "ngayTao"));
        entity.setNguoiTao(Maps.nhanVien(rs, "nguoiTao", "tenNguoiTao"));
        entity.setKhachHang(Maps.khachHang(rs, "maKH", "tenKhachHang", null));
        entity.setKeHoachDieuTri(Maps.keHoach(rs, "maKeHoach", "lyDoKeHoach"));
        entity.setBacSi(Maps.nhanVien(rs, "maBacSi", "tenBacSi"));
        return entity;
    }
}
