package mapper;

import entity.ToaThuoc;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToaThuocMapper implements RowMapper<ToaThuoc> {

    @Override
    public ToaThuoc mapper(ResultSet rs) throws SQLException {
        ToaThuoc entity = new ToaThuoc();
        entity.setMaToaThuoc(rs.getString("maToaThuoc"));
        entity.setMaChanDoan(rs.getString("maChanDoan"));
        entity.setHinhThucThanhToan(rs.getString("hinhThucThanhToan"));
        entity.setNhanTaiPhongKham(rs.getBoolean("nhanTaiPhongKham"));
        entity.setGhiChu(rs.getString("ghiChu"));
        entity.setNgayTao(Rs.dateTime(rs, "ngayTao"));
        entity.setKhachHang(Maps.khachHang(rs, "maKH", "tenKhachHang", null));
        entity.setBacSi(Maps.nhanVien(rs, "maBacSi", "tenBacSi"));
        try {
            entity.ganThanhTienDoc(rs.getLong("thanhTien"));
        } catch (SQLException ignored) {
        }
        return entity;
    }
}
