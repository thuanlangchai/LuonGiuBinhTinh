package mapper;

import entity.KeHoachDieuTri;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KeHoachDieuTriMapper implements RowMapper<KeHoachDieuTri> {

    @Override
    public KeHoachDieuTri mapper(ResultSet rs) throws SQLException {
        KeHoachDieuTri entity = new KeHoachDieuTri();
        entity.setMaKeHoach(rs.getString("maKeHoach"));
        entity.setNgayKeHoach(Rs.date(rs, "ngayKeHoach"));
        entity.setLyDoDieuTri(rs.getString("lyDoDieuTri"));
        entity.setGhiChu(rs.getString("ghiChu"));
        entity.setTrangThai(rs.getString("trangThai"));
        entity.setNgayTao(Rs.dateTime(rs, "ngayTao"));
        entity.setNguoiTao(Maps.nhanVien(rs, "nguoiTao", "tenNguoiTao"));
        entity.setKhachHang(Maps.khachHang(rs, "maKH", "tenKhachHang", null));
        entity.setBacSi(Maps.nhanVien(rs, "maBacSi", "tenBacSi"));
        return entity;
    }
}
