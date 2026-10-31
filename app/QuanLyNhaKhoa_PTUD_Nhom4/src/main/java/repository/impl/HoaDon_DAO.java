package repository.impl;

import entity.HoaDon;
import i_repository.HoaDon_IDAO;
import java.util.List;
import mapper.HoaDonMapper;

public class HoaDon_DAO extends AbstractDAO<HoaDon> implements HoaDon_IDAO {

    @Override
    public List<HoaDon> findAllHoaDon() {
        String sql = "SELECT * FROM HoaDon";
        return findAll(sql, new HoaDonMapper());
    }

    @Override
    public HoaDon findById(String maHoaDon) {
        String sql = "SELECT * FROM HoaDon WHERE maHoaDon = ?";
        List<HoaDon> ds = findAll(sql, new HoaDonMapper(), maHoaDon);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(HoaDon entity) {
        String sql = "INSERT INTO HoaDon(maHoaDon,ngayLap,maKH,maKeHoach,maBacSi,tongTien,chietKhau,thanhTien,daThu,conNo,trangThai,ghiChu,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaHoaDon(), entity.getNgayLap(), entity.getMaKH(), entity.getMaKeHoach(), entity.getMaBacSi(), entity.getTongTien(), entity.getChietKhau(), entity.getThanhTien(), entity.getDaThu(), entity.getConNo(), entity.getTrangThai(), entity.getGhiChu(), entity.getNgayTao(), entity.getNguoiTao());
    }

    @Override
    public void update(HoaDon entity) {
        String sql = "UPDATE HoaDon SET ngayLap = ?, maKH = ?, maKeHoach = ?, maBacSi = ?, tongTien = ?, chietKhau = ?, thanhTien = ?, daThu = ?, conNo = ?, trangThai = ?, ghiChu = ?, ngayTao = ?, nguoiTao = ? WHERE maHoaDon = ?";
        update(sql, entity.getNgayLap(), entity.getMaKH(), entity.getMaKeHoach(), entity.getMaBacSi(), entity.getTongTien(), entity.getChietKhau(), entity.getThanhTien(), entity.getDaThu(), entity.getConNo(), entity.getTrangThai(), entity.getGhiChu(), entity.getNgayTao(), entity.getNguoiTao(), entity.getMaHoaDon());
    }

    @Override
    public void delete(String maHoaDon) {
        String sql = "DELETE FROM HoaDon WHERE maHoaDon = ?";
        delete(sql, maHoaDon);
    }
    @Override
    public List<HoaDon> findByMaKH(String maKH) {
        String sql = "SELECT * FROM HoaDon WHERE maKH = ?";
        return findAll(sql, new HoaDonMapper(), maKH);
    }
}
