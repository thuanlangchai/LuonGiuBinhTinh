package mapper;

import entity.DonVi;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DonViMapper implements RowMapper<DonVi> {

    @Override
    public DonVi mapper(ResultSet rs) throws SQLException {
        DonVi entity = new DonVi();
        entity.setMaDonVi(rs.getString("maDonVi"));
        entity.setTenDonVi(rs.getString("tenDonVi"));
        return entity;
    }
}
