package mapper;

import entity.NhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NhaCungCapMapper implements RowMapper<NhaCungCap> {

    @Override
    public NhaCungCap mapper(ResultSet rs) throws SQLException {
        NhaCungCap entity = new NhaCungCap();
        entity.setMaNhaCungCap(rs.getString("maNhaCungCap"));
        entity.setTenNhaCungCap(rs.getString("tenNhaCungCap"));
        entity.setDienThoai(rs.getString("dienThoai"));
        entity.setDiaChi(rs.getString("diaChi"));
        return entity;
    }
}
