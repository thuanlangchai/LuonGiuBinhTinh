package mapper;

import entity.LoaiSanPham;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoaiSanPhamMapper implements RowMapper<LoaiSanPham> {

    @Override
    public LoaiSanPham mapper(ResultSet rs) throws SQLException {
        LoaiSanPham entity = new LoaiSanPham();
        entity.setMaLoai(rs.getString("maLoai"));
        entity.setTenLoaiSanPham(rs.getString("tenLoaiSanPham"));
        return entity;
    }
}
