package mapper;

import entity.NhomKhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NhomKhachHangMapper implements RowMapper<NhomKhachHang> {

    @Override
    public NhomKhachHang mapper(ResultSet rs) throws SQLException {
        NhomKhachHang entity = new NhomKhachHang();
        entity.setMaNhom(rs.getString("maNhom"));
        entity.setTenNhom(rs.getString("tenNhom"));
        return entity;
    }
}
