package repository.impl;

import entity.ChiTietHoaDon;
import i_repository.ChiTietHoaDon_IDAO;
import java.util.List;
import mapper.ChiTietHoaDonMapper;


public class ChiTietHoaDon_DAO extends AbstractDAO<ChiTietHoaDon> implements ChiTietHoaDon_IDAO {

    @Override
    public List<ChiTietHoaDon> findAllChiTietHoaDon() {
        String sql = "SELECT * FROM ChiTietHoaDon";
        return findAll(sql, new ChiTietHoaDonMapper());
    }

    @Override
    public ChiTietHoaDon findById(String maChiTiet) {
        String sql = "SELECT * FROM ChiTietHoaDon WHERE maChiTiet = ?";
        List<ChiTietHoaDon> ds = findAll(sql, new ChiTietHoaDonMapper(), maChiTiet);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(ChiTietHoaDon entity) {
        String sql = "INSERT INTO ChiTietHoaDon(maChiTiet,maHoaDon,ngay,maRang,maDichVu,soLuong,donGia,thanhTien,keHoach) VALUES(?,?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaChiTiet(), entity.getMaHoaDon(), entity.getNgay(), entity.getMaRang(), entity.getMaDichVu(), entity.getSoLuong(), entity.getDonGia(), entity.getThanhTien(), entity.getKeHoach());
    }

    @Override
    public void update(ChiTietHoaDon entity) {
        String sql = "UPDATE ChiTietHoaDon SET maHoaDon = ?, ngay = ?, maRang = ?, maDichVu = ?, soLuong = ?, donGia = ?, thanhTien = ?, keHoach = ? WHERE maChiTiet = ?";
        update(sql, entity.getMaHoaDon(), entity.getNgay(), entity.getMaRang(), entity.getMaDichVu(), entity.getSoLuong(), entity.getDonGia(), entity.getThanhTien(), entity.getKeHoach(), entity.getMaChiTiet());
    }

    @Override
    public void delete(String maChiTiet) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE maChiTiet = ?";
        delete(sql, maChiTiet);
    }
    @Override
    public List<ChiTietHoaDon> findByMaHoaDon(String maHoaDon) {
        String sql = "SELECT * FROM ChiTietHoaDon WHERE maHoaDon = ?";
        return findAll(sql, new ChiTietHoaDonMapper(), maHoaDon);
    }
}
