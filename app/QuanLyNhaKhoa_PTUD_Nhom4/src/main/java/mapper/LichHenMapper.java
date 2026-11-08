package mapper;

import entity.LichHen;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LichHenMapper implements RowMapper<LichHen> {

    @Override
    public LichHen mapper(ResultSet rs) throws SQLException {
        LichHen entity = new LichHen();
        entity.setMaLichHen(rs.getString("maLichHen"));
        entity.setBatDau(Rs.dateTime(rs, "batDau"));
        entity.setThoiLuongPhut(rs.getInt("thoiLuongPhut"));
        entity.setGhiChu(rs.getString("ghiChu"));
        entity.setTrangThai(rs.getString("trangThai"));
        entity.setKhachHang(Maps.khachHang(rs, "maKH", "tenKhachHang", "sdtKhachHang"));
        entity.setBacSi(Maps.nhanVien(rs, "maBacSi", "tenBacSi"));
        entity.setPhuTa(Maps.nhanVien(rs, "maPhuTa", "tenPhuTa"));
        entity.setKeHoachDieuTri(Maps.keHoach(rs, "maKeHoach", "lyDoKeHoach"));
        return entity;
    }
}
