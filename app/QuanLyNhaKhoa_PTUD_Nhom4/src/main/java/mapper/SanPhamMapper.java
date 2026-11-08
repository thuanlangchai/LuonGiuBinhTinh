package mapper;

import entity.SanPham;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SanPhamMapper implements RowMapper<SanPham> {

    @Override
    public SanPham mapper(ResultSet rs) throws SQLException {
        SanPham entity = new SanPham();
        entity.setMaSanPham(rs.getString("maSanPham"));
        entity.setTenSanPham(rs.getString("tenSanPham"));
        entity.setSoLuong(rs.getInt("soLuong"));
        entity.setDonGia(rs.getLong("donGia"));
        entity.setLoaiSanPham(Maps.loaiSanPham(rs, "maLoai", "tenLoaiSanPham"));
        return entity;
    }
}
