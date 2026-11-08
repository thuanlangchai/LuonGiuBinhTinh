package mapper;

import entity.Rang;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RangMapper implements RowMapper<Rang> {

    @Override
    public Rang mapper(ResultSet rs) throws SQLException {
        Rang entity = new Rang();
        entity.setMaRang(rs.getString("maRang"));
        entity.setTenRang(rs.getString("tenRang"));
        entity.setLoaiRang(Maps.loaiRang(rs, "maLoaiRang", "tenLoaiRang"));
        return entity;
    }
}
