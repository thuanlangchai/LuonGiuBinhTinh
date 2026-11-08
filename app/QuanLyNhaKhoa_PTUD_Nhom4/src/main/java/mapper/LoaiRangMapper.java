package mapper;

import entity.LoaiRang;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoaiRangMapper implements RowMapper<LoaiRang> {

    @Override
    public LoaiRang mapper(ResultSet rs) throws SQLException {
        LoaiRang entity = new LoaiRang();
        entity.setMaLoaiRang(rs.getString("maLoaiRang"));
        entity.setTenLoaiRang(rs.getString("tenLoaiRang"));
        return entity;
    }
}
