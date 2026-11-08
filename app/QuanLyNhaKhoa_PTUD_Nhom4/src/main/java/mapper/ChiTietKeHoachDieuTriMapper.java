package mapper;

import entity.ChiTietKeHoachDieuTri;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietKeHoachDieuTriMapper implements RowMapper<ChiTietKeHoachDieuTri> {

    @Override
    public ChiTietKeHoachDieuTri mapper(ResultSet rs) throws SQLException {
        ChiTietKeHoachDieuTri entity = new ChiTietKeHoachDieuTri();
        entity.setMaChiTiet(rs.getString("maChiTiet"));
        entity.setNgay(Rs.date(rs, "ngay"));
        entity.setSoLuong(rs.getInt("soLuong"));
        entity.setDonGia(rs.getLong("donGia"));
        entity.setThanhTien(rs.getLong("thanhTien"));
        entity.setTrangThai(rs.getString("trangThai"));
        entity.setKeHoachDieuTri(Maps.keHoach(rs, "maKeHoach", null));
        entity.setRang(Maps.rang(rs, "maRang", "tenRang"));
        entity.setDichVu(Maps.dichVu(rs, "maDichVu", "tenDichVu"));
        entity.setHoaDon(Maps.hoaDon(rs, "maHoaDon"));
        return entity;
    }
}
