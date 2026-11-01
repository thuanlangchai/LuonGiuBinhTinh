package repository.impl;

import entity.KeHoachDieuTri;
import repository.KeHoachDieuTri_IDAO;
import java.util.List;
import mapper.KeHoachDieuTriMapper;

public class KeHoachDieuTri_DAO extends AbstractDAO<KeHoachDieuTri> implements KeHoachDieuTri_IDAO {

    @Override
    public List<KeHoachDieuTri> findAllKeHoachDieuTri() {
        String sql = "SELECT * FROM KeHoachDieuTri";
        return findAll(sql, new KeHoachDieuTriMapper());
    }

    @Override
    public KeHoachDieuTri findById(String maKeHoach) {
        String sql = "SELECT * FROM KeHoachDieuTri WHERE maKeHoach = ?";
        List<KeHoachDieuTri> ds = findAll(sql, new KeHoachDieuTriMapper(), maKeHoach);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public List<KeHoachDieuTri> findByMaKH(String maKH) {
        String sql = "SELECT * FROM KeHoachDieuTri WHERE maKH = ? ORDER BY ngayKeHoach DESC";
        return findAll(sql, new KeHoachDieuTriMapper(), maKH);
    }

    @Override
    public Integer add(KeHoachDieuTri entity) {
        String sql = "INSERT INTO KeHoachDieuTri(maKeHoach,maKH,ngayKeHoach,maBacSi,lyDoDieuTri,ghiChu,trangThai,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaKeHoach(), entity.getMaKH(), entity.getNgayKeHoach(),
                entity.getMaBacSi(), entity.getLyDoDieuTri(), entity.getGhiChu(),
                entity.getTrangThai(), entity.getNgayTao(), entity.getNguoiTao());
    }

    @Override
    public void update(KeHoachDieuTri entity) {
        String sql = "UPDATE KeHoachDieuTri SET maKH = ?, ngayKeHoach = ?, maBacSi = ?, lyDoDieuTri = ?, ghiChu = ?, trangThai = ?, ngayTao = ?, nguoiTao = ? WHERE maKeHoach = ?";
        update(sql, entity.getMaKH(), entity.getNgayKeHoach(), entity.getMaBacSi(),
                entity.getLyDoDieuTri(), entity.getGhiChu(), entity.getTrangThai(),
                entity.getNgayTao(), entity.getNguoiTao(), entity.getMaKeHoach());
    }

    @Override
    public void delete(String maKeHoach) {
        String sql = "DELETE FROM KeHoachDieuTri WHERE maKeHoach = ?";
        delete(sql, maKeHoach);
    }
}
