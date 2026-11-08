package mapper;

import entity.Thuoc;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThuocMapper implements RowMapper<Thuoc> {

    @Override
    public Thuoc mapper(ResultSet rs) throws SQLException {
        Thuoc entity = new Thuoc();
        entity.setMaThuoc(rs.getString("maThuoc"));
        entity.setTenThuoc(rs.getString("tenThuoc"));
        entity.setGia(rs.getLong("gia"));
        entity.setCachDung(rs.getString("cachDung"));
        entity.setDonVi(Maps.donVi(rs, "maDonVi", "tenDonVi"));
        return entity;
    }
}
