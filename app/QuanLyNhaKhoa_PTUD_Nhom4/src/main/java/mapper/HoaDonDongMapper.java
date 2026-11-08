package mapper;

import dto.HoaDonDong;
import entity.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDonDongMapper implements RowMapper<HoaDonDong> {

    private final HoaDonMapper hoaDonMapper = new HoaDonMapper();

    @Override
    public HoaDonDong mapper(ResultSet rs) throws SQLException {
        HoaDon hoaDon = hoaDonMapper.mapper(rs);
        return new HoaDonDong(hoaDon, rs.getLong("tongTien"), rs.getLong("daThu"));
    }
}
