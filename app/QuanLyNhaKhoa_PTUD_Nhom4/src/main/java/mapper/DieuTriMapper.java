package mapper;

import entity.DieuTri;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DieuTriMapper implements RowMapper<DieuTri> {

    @Override
    public DieuTri mapper(ResultSet rs) throws SQLException {
        DieuTri entity = new DieuTri();
        entity.setMaDieuTri(rs.getString("maDieuTri"));
        entity.setTenDieuTri(rs.getString("tenDieuTri"));
        return entity;
    }
}
