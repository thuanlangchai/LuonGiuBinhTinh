package mapper;

import entity.ChiTietXuatKho;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietXuatKhoMapper implements RowMapper<ChiTietXuatKho> {

    @Override
    public ChiTietXuatKho mapper(ResultSet rs) throws SQLException {
        ChiTietXuatKho entity = new ChiTietXuatKho();
        entity.setMaChiTiet(rs.getString("maChiTiet"));
        entity.setSoLuong(rs.getInt("soLuong"));
        entity.setDonGia(rs.getLong("donGia"));
        entity.setPhieuXuatKho(Maps.phieuXuat(rs, "maPhieuXuat"));
        entity.setSanPham(Maps.sanPham(rs, "maSanPham", "tenSanPham"));
        return entity;
    }
}
