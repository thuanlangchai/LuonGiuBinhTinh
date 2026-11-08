package mapper;

import entity.ChiTietToaThuoc;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietToaThuocMapper implements RowMapper<ChiTietToaThuoc> {

    @Override
    public ChiTietToaThuoc mapper(ResultSet rs) throws SQLException {
        ChiTietToaThuoc entity = new ChiTietToaThuoc();
        entity.setMaChiTiet(rs.getString("maChiTiet"));
        entity.setSoNgay(rs.getInt("soNgay"));
        entity.setCachSuDung(rs.getString("cachSuDung"));
        entity.setTongSo(rs.getInt("tongSo"));
        entity.setDonGia(rs.getLong("donGia"));
        entity.setThanhTien(rs.getLong("thanhTien"));
        entity.setToaThuoc(Maps.toaThuoc(rs, "maToaThuoc"));
        entity.setThuoc(Maps.thuoc(rs, "maThuoc", "tenThuoc"));
        return entity;
    }
}
