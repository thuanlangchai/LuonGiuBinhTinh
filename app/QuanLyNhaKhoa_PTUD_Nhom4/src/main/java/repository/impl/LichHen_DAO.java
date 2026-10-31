package repository.impl;

import entity.LichHen;
import i_repository.LichHen_IDAO;
import java.util.List;
import mapper.LichHenMapper;


public class LichHen_DAO extends AbstractDAO<LichHen> implements LichHen_IDAO {

    @Override
    public List<LichHen> findAllLichHen() {
        String sql = "SELECT * FROM LichHen";
        return findAll(sql, new LichHenMapper());
    }

    @Override
    public LichHen findById(String maLichHen) {
        String sql = "SELECT * FROM LichHen WHERE maLichHen = ?";
        List<LichHen> ds = findAll(sql, new LichHenMapper(), maLichHen);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(LichHen entity) {
        String sql = "INSERT INTO LichHen(maLichHen,maKH,batDau,thoiLuongPhut,maBacSi,maPhuTa,ghiChu,trangThai) VALUES(?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaLichHen(), entity.getMaKH(), entity.getBatDau(), entity.getThoiLuongPhut(), entity.getMaBacSi(), entity.getMaPhuTa(), entity.getGhiChu(), entity.getTrangThai());
    }

    @Override
    public void update(LichHen entity) {
        String sql = "UPDATE LichHen SET maKH = ?, batDau = ?, thoiLuongPhut = ?, maBacSi = ?, maPhuTa = ?, ghiChu = ?, trangThai = ? WHERE maLichHen = ?";
        update(sql, entity.getMaKH(), entity.getBatDau(), entity.getThoiLuongPhut(), entity.getMaBacSi(), entity.getMaPhuTa(), entity.getGhiChu(), entity.getTrangThai(), entity.getMaLichHen());
    }

    @Override
    public void delete(String maLichHen) {
        String sql = "DELETE FROM LichHen WHERE maLichHen = ?";
        delete(sql, maLichHen);
    }
    @Override
    public List<LichHen> findByMaKH(String maKH) {
        String sql = "SELECT * FROM LichHen WHERE maKH = ?";
        return findAll(sql, new LichHenMapper(), maKH);
    }
}
