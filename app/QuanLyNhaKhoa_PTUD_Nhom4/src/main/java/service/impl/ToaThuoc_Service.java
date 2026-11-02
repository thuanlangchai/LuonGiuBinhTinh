package service.impl;

import entity.ChiTietToaThuoc;
import entity.ToaThuoc;
import java.util.List;
import repository.impl.ChiTietToaThuoc_DAO;
import repository.impl.ToaThuoc_DAO;
import service.ToaThuoc_IService;

public class ToaThuoc_Service implements ToaThuoc_IService {

    private final ToaThuoc_DAO toaDao;
    private final ChiTietToaThuoc_DAO chiTietDao;

    public ToaThuoc_Service() {
        this(new ToaThuoc_DAO(), new ChiTietToaThuoc_DAO());
    }

    public ToaThuoc_Service(ToaThuoc_DAO toaDao, ChiTietToaThuoc_DAO chiTietDao) {
        this.toaDao = toaDao;
        this.chiTietDao = chiTietDao;
    }

    @Override
    public List<ToaThuoc> findAllToaThuoc() {
        return Lists.orEmpty(toaDao.findAllToaThuoc());
    }

    @Override
    public ToaThuoc findById(String maToaThuoc) {
        return toaDao.findById(maToaThuoc);
    }

    @Override
    public List<ToaThuoc> findByMaKH(String maKH) {
        return Lists.orEmpty(toaDao.findByMaKH(maKH));
    }

    @Override
    public Integer add(ToaThuoc entity) {
        return toaDao.add(entity);
    }

    @Override
    public void update(ToaThuoc entity) {
        toaDao.update(entity);
    }

    @Override
    public void delete(String maToaThuoc) {
        for (ChiTietToaThuoc ct : findChiTietByMaToaThuoc(maToaThuoc)) {
            chiTietDao.delete(ct.getMaChiTiet());
        }
        toaDao.delete(maToaThuoc);
    }

    @Override
    public List<ChiTietToaThuoc> findChiTietByMaToaThuoc(String maToaThuoc) {
        return Lists.orEmpty(chiTietDao.findByMaToaThuoc(maToaThuoc));
    }

    @Override
    public Integer addChiTiet(ChiTietToaThuoc entity) {
        return chiTietDao.add(entity);
    }

    @Override
    public void updateChiTiet(ChiTietToaThuoc entity) {
        chiTietDao.update(entity);
    }

    @Override
    public void deleteChiTiet(String maChiTiet) {
        chiTietDao.delete(maChiTiet);
    }

    @Override
    public Integer themToaVoiChiTiet(ToaThuoc toa, List<ChiTietToaThuoc> chiTiet) {
        if (toa == null) {
            return null;
        }
        Integer kq = toaDao.add(toa);
        if (kq == null) {
            return null;
        }
        if (chiTiet != null) {
            for (ChiTietToaThuoc ct : chiTiet) {
                ct.setMaToaThuoc(toa.getMaToaThuoc());
                chiTietDao.add(ct);
            }
        }
        return kq;
    }
}
