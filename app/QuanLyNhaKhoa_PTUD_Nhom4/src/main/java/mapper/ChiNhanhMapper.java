package mapper;

import entity.ChiNhanh;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiNhanhMapper implements RowMapper<ChiNhanh> {

    @Override
    public ChiNhanh mapper(ResultSet rs) throws SQLException {
        ChiNhanh entity = new ChiNhanh();
        entity.setMaChiNhanh(rs.getString("maChiNhanh"));
        entity.setTenChiNhanh(rs.getString("tenChiNhanh"));
        entity.setDiaChi(rs.getString("diaChi"));
        entity.setDienThoai(rs.getString("dienThoai"));
        return entity;
    }
}
