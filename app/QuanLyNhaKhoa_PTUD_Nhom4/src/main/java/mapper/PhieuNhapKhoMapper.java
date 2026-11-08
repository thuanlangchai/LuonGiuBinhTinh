package mapper;

import entity.PhieuNhapKho;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhieuNhapKhoMapper implements RowMapper<PhieuNhapKho> {

    @Override
    public PhieuNhapKho mapper(ResultSet rs) throws SQLException {
        PhieuNhapKho entity = new PhieuNhapKho();
        entity.setMaPhieuNhap(rs.getString("maPhieuNhap"));
        entity.setNgayNhap(Rs.date(rs, "ngayNhap"));
        entity.setLoaiVatTu(rs.getString("loaiVatTu"));
        entity.setLyDo(rs.getString("lyDo"));
        entity.setTongTien(rs.getLong("tongTien"));
        entity.setFileExcel(rs.getString("fileExcel"));
        entity.setNgayTao(Rs.dateTime(rs, "ngayTao"));
        entity.setNguoiTao(Maps.nhanVien(rs, "nguoiTao", "tenNguoiTao"));
        entity.setNhaCungCap(Maps.nhaCungCap(rs, "maNhaCungCap", "tenNhaCungCap"));
        return entity;
    }
}
