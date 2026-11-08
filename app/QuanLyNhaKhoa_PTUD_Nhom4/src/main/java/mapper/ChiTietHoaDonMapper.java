package mapper;

import entity.ChiTietHoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietHoaDonMapper implements RowMapper<ChiTietHoaDon> {

    @Override
    public ChiTietHoaDon mapper(ResultSet rs) throws SQLException {
        ChiTietHoaDon entity = new ChiTietHoaDon();
        entity.setMaChiTiet(rs.getString("maChiTiet"));
        entity.setNgay(Rs.date(rs, "ngay"));
        entity.setSoLuong(rs.getInt("soLuong"));
        entity.setDonGia(rs.getLong("donGia"));
        entity.setThanhTien(rs.getLong("thanhTien"));
        entity.setKeHoach(rs.getString("keHoach"));
        entity.setHoaDon(Maps.hoaDon(rs, "maHoaDon"));
        entity.setRang(Maps.rang(rs, "maRang", "tenRang"));
        entity.setDichVu(Maps.dichVu(rs, "maDichVu", "tenDichVu"));
        return entity;
    }
}
