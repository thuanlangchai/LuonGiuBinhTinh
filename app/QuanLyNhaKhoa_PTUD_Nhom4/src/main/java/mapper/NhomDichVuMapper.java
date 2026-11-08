package mapper;

import entity.NhomDichVu;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NhomDichVuMapper implements RowMapper<NhomDichVu> {

    @Override
    public NhomDichVu mapper(ResultSet rs) throws SQLException {
        NhomDichVu entity = new NhomDichVu();
        entity.setMaNhom(rs.getString("maNhom"));
        entity.setTenNhomDichVu(rs.getString("tenNhomDichVu"));
        entity.setMoTa(rs.getString("moTa"));
        return entity;
    }
}
