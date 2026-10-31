package repository.impl;

import entity.ThoiGianLamViec;
import i_repository.ThoiGianLamViec_IDAO;
import java.util.List;
import mapper.ThoiGianLamViecMapper;


public class ThoiGianLamViec_DAO extends AbstractDAO<ThoiGianLamViec> implements ThoiGianLamViec_IDAO {

    @Override
    public List<ThoiGianLamViec> findAllThoiGianLamViec() {
        String sql = "SELECT * FROM ThoiGianLamViec";
        return findAll(sql, new ThoiGianLamViecMapper());
    }

    @Override
    public ThoiGianLamViec findById(String maLich) {
        String sql = "SELECT * FROM ThoiGianLamViec WHERE maLich = ?";
        List<ThoiGianLamViec> ds = findAll(sql, new ThoiGianLamViecMapper(), maLich);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(ThoiGianLamViec entity) {
        String sql = "INSERT INTO ThoiGianLamViec(maLich,ngay,gioBatDau,gioKetThuc,maBacSi,maChiNhanh) VALUES(?,?,?,?,?,?)";
        return add(sql, entity.getMaLich(), entity.getNgay(), entity.getGioBatDau(), entity.getGioKetThuc(), entity.getMaBacSi(), entity.getMaChiNhanh());
    }

    @Override
    public void update(ThoiGianLamViec entity) {
        String sql = "UPDATE ThoiGianLamViec SET ngay = ?, gioBatDau = ?, gioKetThuc = ?, maBacSi = ?, maChiNhanh = ? WHERE maLich = ?";
        update(sql, entity.getNgay(), entity.getGioBatDau(), entity.getGioKetThuc(), entity.getMaBacSi(), entity.getMaChiNhanh(), entity.getMaLich());
    }

    @Override
    public void delete(String maLich) {
        String sql = "DELETE FROM ThoiGianLamViec WHERE maLich = ?";
        delete(sql, maLich);
    }
}
