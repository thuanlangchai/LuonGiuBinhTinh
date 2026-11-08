package mapper;

import entity.TaiKhoan;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanMapper implements RowMapper<TaiKhoan> {

    @Override
    public TaiKhoan mapper(ResultSet rs) throws SQLException {
        TaiKhoan entity = new TaiKhoan();
        entity.setTenDangNhap(rs.getString("tenDangNhap"));
        entity.setMatKhau(rs.getString("matKhau"));
        entity.setEmail(rs.getString("email"));
        entity.setKichHoat(rs.getBoolean("kichHoat"));
        entity.setNgayTao(Rs.dateTime(rs, "ngayTao"));
        entity.setNhanVien(Maps.nhanVien(rs, "maNhanVien", "tenNhanVien"));
        return entity;
    }
}
