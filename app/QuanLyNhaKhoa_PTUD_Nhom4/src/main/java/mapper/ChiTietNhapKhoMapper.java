package mapper;

import entity.ChiTietNhapKho;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietNhapKhoMapper implements RowMapper<ChiTietNhapKho> {

    @Override
    public ChiTietNhapKho mapper(ResultSet rs) throws SQLException {
        ChiTietNhapKho entity = new ChiTietNhapKho();
        entity.setMaChiTiet(rs.getString("maChiTiet"));
        entity.setSoLuong(rs.getInt("soLuong"));
        entity.setDonGia(rs.getLong("donGia"));
        entity.setPhieuNhapKho(Maps.phieuNhap(rs, "maPhieuNhap"));
        entity.setSanPham(Maps.sanPham(rs, "maSanPham", "tenSanPham"));
        return entity;
    }
}
