package mapper;

import entity.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KhachHangMapper implements RowMapper<KhachHang> {

    @Override
    public KhachHang mapper(ResultSet rs) throws SQLException {
        KhachHang entity = new KhachHang();
        entity.setMaKH(rs.getString("maKH"));
        entity.setHoTen(rs.getString("hoTen"));
        entity.setGioiTinh(rs.getString("gioiTinh"));
        entity.setNgaySinh(Rs.date(rs, "ngaySinh"));
        entity.setSoDienThoai(rs.getString("soDienThoai"));
        entity.setEmail(rs.getString("email"));
        entity.setDiaChi(rs.getString("diaChi"));
        entity.setCccd(rs.getString("cccd"));
        entity.setGhiChu(rs.getString("ghiChu"));
        entity.setLyDoKham(rs.getString("lyDoKham"));
        entity.setTienSuBenh(rs.getString("tienSuBenh"));
        entity.setDiUng(rs.getString("diUng"));
        entity.setSoLanKham(rs.getInt("soLanKham"));
        entity.setLanKhamGanNhat(Rs.date(rs, "lanKhamGanNhat"));
        entity.setNgayTao(Rs.dateTime(rs, "ngayTao"));
        entity.setNhomKhachHang(Maps.nhomKhach(rs, "maNhom", "tenNhom"));
        entity.setChiNhanh(Maps.chiNhanh(rs, "maChiNhanh", "tenChiNhanh"));
        entity.setBacSi(Maps.nhanVien(rs, "maBacSi", "tenBacSi"));
        entity.setTuVan(Maps.nhanVien(rs, "maTuVan", "tenTuVan"));
        return entity;
    }
}
