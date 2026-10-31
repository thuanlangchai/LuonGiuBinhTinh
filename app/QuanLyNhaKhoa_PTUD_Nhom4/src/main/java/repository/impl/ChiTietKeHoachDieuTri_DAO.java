package repository.impl;

import entity.ChiTietKeHoachDieuTri;
import i_repository.ChiTietKeHoachDieuTri_IDAO;
import java.util.List;
import mapper.ChiTietKeHoachDieuTriMapper;


public class ChiTietKeHoachDieuTri_DAO extends AbstractDAO<ChiTietKeHoachDieuTri> implements ChiTietKeHoachDieuTri_IDAO {

    @Override
    public List<ChiTietKeHoachDieuTri> findAllChiTietKeHoachDieuTri() {
        String sql = "SELECT * FROM ChiTietKeHoachDieuTri";
        return findAll(sql, new ChiTietKeHoachDieuTriMapper());
    }

    @Override
    public ChiTietKeHoachDieuTri findById(String maChiTiet) {
        String sql = "SELECT * FROM ChiTietKeHoachDieuTri WHERE maChiTiet = ?";
        List<ChiTietKeHoachDieuTri> ds = findAll(sql, new ChiTietKeHoachDieuTriMapper(), maChiTiet);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(ChiTietKeHoachDieuTri entity) {
        String sql = "INSERT INTO ChiTietKeHoachDieuTri(maChiTiet,maKeHoach,ngay,maRang,maDichVu,soLuong,donGia,thanhTien,trangThai,maHoaDon) VALUES(?,?,?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaChiTiet(), entity.getMaKeHoach(), entity.getNgay(), entity.getMaRang(), entity.getMaDichVu(), entity.getSoLuong(), entity.getDonGia(), entity.getThanhTien(), entity.getTrangThai(), entity.getMaHoaDon());
    }

    @Override
    public void update(ChiTietKeHoachDieuTri entity) {
        String sql = "UPDATE ChiTietKeHoachDieuTri SET maKeHoach = ?, ngay = ?, maRang = ?, maDichVu = ?, soLuong = ?, donGia = ?, thanhTien = ?, trangThai = ?, maHoaDon = ? WHERE maChiTiet = ?";
        update(sql, entity.getMaKeHoach(), entity.getNgay(), entity.getMaRang(), entity.getMaDichVu(), entity.getSoLuong(), entity.getDonGia(), entity.getThanhTien(), entity.getTrangThai(), entity.getMaHoaDon(), entity.getMaChiTiet());
    }

    @Override
    public void delete(String maChiTiet) {
        String sql = "DELETE FROM ChiTietKeHoachDieuTri WHERE maChiTiet = ?";
        delete(sql, maChiTiet);
    }
    @Override
    public List<ChiTietKeHoachDieuTri> findByMaKeHoach(String maKeHoach) {
        String sql = "SELECT * FROM ChiTietKeHoachDieuTri WHERE maKeHoach = ?";
        return findAll(sql, new ChiTietKeHoachDieuTriMapper(), maKeHoach);
    }
}
