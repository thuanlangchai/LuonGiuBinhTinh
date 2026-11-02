package service.impl;

import entity.ChiTietKeHoachDieuTri;
import entity.KeHoachDieuTri;
import java.util.List;
import repository.impl.ChiTietKeHoachDieuTri_DAO;
import repository.impl.KeHoachDieuTri_DAO;
import service.KeHoachDieuTri_IService;

public class KeHoachDieuTri_Service implements KeHoachDieuTri_IService {

    private final KeHoachDieuTri_DAO keHoachDao;
    private final ChiTietKeHoachDieuTri_DAO chiTietDao;

    public KeHoachDieuTri_Service() {
        this(new KeHoachDieuTri_DAO(), new ChiTietKeHoachDieuTri_DAO());
    }

    public KeHoachDieuTri_Service(KeHoachDieuTri_DAO keHoachDao, ChiTietKeHoachDieuTri_DAO chiTietDao) {
        this.keHoachDao = keHoachDao;
        this.chiTietDao = chiTietDao;
    }

    @Override
    public List<KeHoachDieuTri> findAllKeHoachDieuTri() {
        return Lists.orEmpty(keHoachDao.findAllKeHoachDieuTri());
    }

    @Override
    public KeHoachDieuTri findById(String maKeHoach) {
        return keHoachDao.findById(maKeHoach);
    }

    @Override
    public List<KeHoachDieuTri> findByMaKH(String maKH) {
        return Lists.orEmpty(keHoachDao.findByMaKH(maKH));
    }

    @Override
    public Integer add(KeHoachDieuTri entity) {
        return keHoachDao.add(entity);
    }

    @Override
    public void update(KeHoachDieuTri entity) {
        keHoachDao.update(entity);
    }

    @Override
    public void delete(String maKeHoach) {
        keHoachDao.delete(maKeHoach);
    }

    @Override
    public boolean huy(String maKeHoach) {
        KeHoachDieuTri kh = keHoachDao.findById(maKeHoach);
        if (kh == null) {
            return false;
        }
        if ("Đã hủy".equalsIgnoreCase(kh.getTrangThai()) || "Hủy".equalsIgnoreCase(kh.getTrangThai())) {
            return false;
        }
        for (ChiTietKeHoachDieuTri ct : findChiTietByMaKeHoach(maKeHoach)) {
            if (ct.getMaHoaDon() != null && !ct.getMaHoaDon().isBlank()) {
                return false;
            }
        }
        kh.setTrangThai("Đã hủy");
        keHoachDao.update(kh);
        return true;
    }

    @Override
    public List<ChiTietKeHoachDieuTri> findChiTietByMaKeHoach(String maKeHoach) {
        return Lists.orEmpty(chiTietDao.findByMaKeHoach(maKeHoach));
    }

    @Override
    public ChiTietKeHoachDieuTri findChiTietById(String maChiTiet) {
        return chiTietDao.findById(maChiTiet);
    }

    @Override
    public Integer addChiTiet(ChiTietKeHoachDieuTri entity) {
        KeHoachDieuTri kh = keHoachDao.findById(entity.getMaKeHoach());
        if (kh == null) {
            return null;
        }
        if ("Đã hủy".equalsIgnoreCase(kh.getTrangThai()) || "Hủy".equalsIgnoreCase(kh.getTrangThai())) {
            return null;
        }
        Integer kq = chiTietDao.add(entity);
        if (kq != null && "Mới".equals(kh.getTrangThai())) {
            kh.setTrangThai("Đang điều trị");
            keHoachDao.update(kh);
        }
        return kq;
    }

    @Override
    public void updateChiTiet(ChiTietKeHoachDieuTri entity) {
        chiTietDao.update(entity);
    }

    @Override
    public void deleteChiTiet(String maChiTiet) {
        ChiTietKeHoachDieuTri ct = chiTietDao.findById(maChiTiet);
        if (ct != null && ct.getMaHoaDon() != null && !ct.getMaHoaDon().isBlank()) {
            return;
        }
        chiTietDao.delete(maChiTiet);
    }
}
