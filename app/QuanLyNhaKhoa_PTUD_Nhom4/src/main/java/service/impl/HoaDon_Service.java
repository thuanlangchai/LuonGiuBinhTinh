package service.impl;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.util.List;
import repository.impl.ChiTietHoaDon_DAO;
import repository.impl.HoaDon_DAO;
import service.HoaDon_IService;

public class HoaDon_Service implements HoaDon_IService {

    private final HoaDon_DAO hoaDonDao;
    private final ChiTietHoaDon_DAO chiTietDao;

    public HoaDon_Service() {
        this(new HoaDon_DAO(), new ChiTietHoaDon_DAO());
    }

    public HoaDon_Service(HoaDon_DAO hoaDonDao, ChiTietHoaDon_DAO chiTietDao) {
        this.hoaDonDao = hoaDonDao;
        this.chiTietDao = chiTietDao;
    }

    @Override
    public List<HoaDon> findAllHoaDon() {
        return Lists.orEmpty(hoaDonDao.findAllHoaDon());
    }

    @Override
    public HoaDon findById(String maHoaDon) {
        return hoaDonDao.findById(maHoaDon);
    }

    @Override
    public List<HoaDon> findByMaKH(String maKH) {
        return Lists.orEmpty(hoaDonDao.findByMaKH(maKH));
    }

    @Override
    public Integer add(HoaDon entity) {
        return hoaDonDao.add(entity);
    }

    @Override
    public void update(HoaDon entity) {
        hoaDonDao.update(entity);
    }

    @Override
    public void delete(String maHoaDon) {
        for (ChiTietHoaDon ct : findChiTietByMaHoaDon(maHoaDon)) {
            chiTietDao.delete(ct.getMaChiTiet());
        }
        hoaDonDao.delete(maHoaDon);
    }

    @Override
    public List<ChiTietHoaDon> findChiTietByMaHoaDon(String maHoaDon) {
        return Lists.orEmpty(chiTietDao.findByMaHoaDon(maHoaDon));
    }

    @Override
    public Integer addChiTiet(ChiTietHoaDon entity) {
        return chiTietDao.add(entity);
    }

    @Override
    public void updateChiTiet(ChiTietHoaDon entity) {
        chiTietDao.update(entity);
    }

    @Override
    public void deleteChiTiet(String maChiTiet) {
        chiTietDao.delete(maChiTiet);
    }

    @Override
    public Integer themHoaDonVoiChiTiet(HoaDon hoaDon, List<ChiTietHoaDon> chiTiet) {
        if (hoaDon == null) {
            return null;
        }
        Integer kq = hoaDonDao.add(hoaDon);
        if (kq == null) {
            return null;
        }
        if (chiTiet != null) {
            for (ChiTietHoaDon ct : chiTiet) {
                ct.setMaHoaDon(hoaDon.getMaHoaDon());
                chiTietDao.add(ct);
            }
        }
        return kq;
    }
}
