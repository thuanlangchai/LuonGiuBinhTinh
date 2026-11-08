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
        entity.setNgayTao(Rs.dateTime(rs, "ngayTao"));
        entity.setNhaCungCap(Maps.nhaCungCap(rs, "maNhaCungCap", "tenNhaCungCap"));
        entity.setNguoiTao(Maps.nhanVien(rs, "nguoiTao", null));
        try {
            entity.ganTongTienDoc(rs.getLong("tongTien"));
        } catch (SQLException ignored) {
        }
        return entity;
    }
}
