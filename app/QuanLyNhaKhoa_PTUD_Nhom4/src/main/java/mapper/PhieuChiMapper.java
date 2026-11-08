package mapper;

import entity.PhieuChi;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhieuChiMapper implements RowMapper<PhieuChi> {

    @Override
    public PhieuChi mapper(ResultSet rs) throws SQLException {
        PhieuChi entity = new PhieuChi();
        entity.setMaPhieuChi(rs.getString("maPhieuChi"));
        entity.setLoaiChiTieu(rs.getString("loaiChiTieu"));
        entity.setLyDoChi(rs.getString("lyDoChi"));
        entity.setSoTien(rs.getLong("soTien"));
        entity.setNgayChi(Rs.date(rs, "ngayChi"));
        entity.setHinhThucThanhToan(rs.getString("hinhThucThanhToan"));
        entity.setTrangThai(rs.getString("trangThai"));
        entity.setNgayTao(Rs.dateTime(rs, "ngayTao"));
        entity.setNguoiTao(Maps.nhanVien(rs, "nguoiTao", "tenNguoiTao"));
        entity.setNhaCungCap(Maps.nhaCungCap(rs, "maNhaCungCap", "tenNhaCungCap"));
        return entity;
    }
}
