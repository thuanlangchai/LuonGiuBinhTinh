package mapper;

import entity.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NhanVienMapper implements RowMapper<NhanVien> {

    @Override
    public NhanVien mapper(ResultSet rs) throws SQLException {
        NhanVien entity = new NhanVien();
        entity.setMaNhanVien(rs.getString("maNhanVien"));
        entity.setChucDanh(rs.getString("chucDanh"));
        entity.setHoTen(rs.getString("hoTen"));
        entity.setEmail(rs.getString("email"));
        entity.setSoDienThoai(rs.getString("soDienThoai"));
        entity.setVaiTro(rs.getString("vaiTro"));
        entity.setKichHoat(rs.getBoolean("kichHoat"));
        entity.setChiNhanh(Maps.chiNhanh(rs, "maChiNhanh", "tenChiNhanh"));
        return entity;
    }
}
