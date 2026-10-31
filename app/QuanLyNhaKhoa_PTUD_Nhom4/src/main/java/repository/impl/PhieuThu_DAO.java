package repository.impl;

import entity.PhieuThu;
import i_repository.PhieuThu_IDAO;
import java.util.List;
import mapper.PhieuThuMapper;


public class PhieuThu_DAO extends AbstractDAO<PhieuThu> implements PhieuThu_IDAO {

    @Override
    public List<PhieuThu> findAllPhieuThu() {
        String sql = "SELECT * FROM PhieuThu";
        return findAll(sql, new PhieuThuMapper());
    }

    @Override
    public PhieuThu findById(String maPhieuThu) {
        String sql = "SELECT * FROM PhieuThu WHERE maPhieuThu = ?";
        List<PhieuThu> ds = findAll(sql, new PhieuThuMapper(), maPhieuThu);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(PhieuThu entity) {
        String sql = "INSERT INTO PhieuThu(maPhieuThu,ngayThu,maKH,maHoaDon,lyDo,soTien,hinhThucThanhToan,trangThai,maBacSi,maPhuTa,nguoiGioiThieu,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaPhieuThu(), entity.getNgayThu(), entity.getMaKH(), entity.getMaHoaDon(), entity.getLyDo(), entity.getSoTien(), entity.getHinhThucThanhToan(), entity.getTrangThai(), entity.getMaBacSi(), entity.getMaPhuTa(), entity.getNguoiGioiThieu(), entity.getNgayTao(), entity.getNguoiTao());
    }

    @Override
    public void update(PhieuThu entity) {
        String sql = "UPDATE PhieuThu SET ngayThu = ?, maKH = ?, maHoaDon = ?, lyDo = ?, soTien = ?, hinhThucThanhToan = ?, trangThai = ?, maBacSi = ?, maPhuTa = ?, nguoiGioiThieu = ?, ngayTao = ?, nguoiTao = ? WHERE maPhieuThu = ?";
        update(sql, entity.getNgayThu(), entity.getMaKH(), entity.getMaHoaDon(), entity.getLyDo(), entity.getSoTien(), entity.getHinhThucThanhToan(), entity.getTrangThai(), entity.getMaBacSi(), entity.getMaPhuTa(), entity.getNguoiGioiThieu(), entity.getNgayTao(), entity.getNguoiTao(), entity.getMaPhieuThu());
    }

    @Override
    public void delete(String maPhieuThu) {
        String sql = "DELETE FROM PhieuThu WHERE maPhieuThu = ?";
        delete(sql, maPhieuThu);
    }
}
