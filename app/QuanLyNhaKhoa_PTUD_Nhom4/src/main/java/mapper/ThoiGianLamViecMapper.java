package mapper;

import entity.ThoiGianLamViec;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThoiGianLamViecMapper implements RowMapper<ThoiGianLamViec> {

    @Override
    public ThoiGianLamViec mapper(ResultSet rs) throws SQLException {
        ThoiGianLamViec entity = new ThoiGianLamViec();
        entity.setMaLich(rs.getString("maLich"));
        entity.setNgay(rs.getString("ngay"));
        entity.setGioBatDau(Rs.time(rs, "gioBatDau"));
        entity.setGioKetThuc(Rs.time(rs, "gioKetThuc"));
        entity.setBacSi(Maps.nhanVien(rs, "maBacSi", "tenBacSi"));
        entity.setChiNhanh(Maps.chiNhanh(rs, "maChiNhanh", "tenChiNhanh"));
        return entity;
    }
}
