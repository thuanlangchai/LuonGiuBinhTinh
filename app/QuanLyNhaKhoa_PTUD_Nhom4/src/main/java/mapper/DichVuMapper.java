package mapper;

import entity.DichVu;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DichVuMapper implements RowMapper<DichVu> {

    @Override
    public DichVu mapper(ResultSet rs) throws SQLException {
        DichVu entity = new DichVu();
        entity.setMaDichVu(rs.getString("maDichVu"));
        entity.setTenDichVu(rs.getString("tenDichVu"));
        entity.setGia(rs.getLong("gia"));
        entity.setTrangThai(rs.getString("trangThai"));
        entity.setNhomDichVu(Maps.nhomDichVu(rs, "maNhom", "tenNhomDichVu"));
        return entity;
    }
}
