package mapper;

import entity.PhieuXuatKho;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhieuXuatKhoMapper implements RowMapper<PhieuXuatKho> {

    @Override
    public PhieuXuatKho mapper(ResultSet rs) throws SQLException {
        PhieuXuatKho entity = new PhieuXuatKho();
        entity.setMaPhieuXuat(rs.getString("maPhieuXuat"));
        entity.setNgayXuat(Rs.date(rs, "ngayXuat"));
        entity.setLoaiVatTu(rs.getString("loaiVatTu"));
        entity.setLyDo(rs.getString("lyDo"));
        entity.setTongTien(rs.getLong("tongTien"));
        entity.setNgayTao(Rs.dateTime(rs, "ngayTao"));
        entity.setNguoiTao(Maps.nhanVien(rs, "nguoiTao", "tenNguoiTao"));
        entity.setNhaCungCap(Maps.nhaCungCap(rs, "maNhaCungCap", "tenNhaCungCap"));
        return entity;
    }
}
