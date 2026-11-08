package service.impl;

import database.BusinessException;
import database.Transactions;
import entity.ChiTietToaThuoc;
import entity.Refs;
import entity.ToaThuoc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import repository.impl.ChiTietToaThuoc_DAO;
import repository.impl.Thuoc_DAO;
import repository.impl.ToaThuoc_DAO;
import service.ToaThuoc_IService;

public class ToaThuoc_Service implements ToaThuoc_IService {

    private final ToaThuoc_DAO toaDao;
    private final ChiTietToaThuoc_DAO chiTietDao;
    private final Thuoc_DAO thuocDao;

    public ToaThuoc_Service() {
        this(new ToaThuoc_DAO(), new ChiTietToaThuoc_DAO(), new Thuoc_DAO());
    }

    public ToaThuoc_Service(ToaThuoc_DAO toaDao, ChiTietToaThuoc_DAO chiTietDao) {
        this(toaDao, chiTietDao, new Thuoc_DAO());
    }

    public ToaThuoc_Service(ToaThuoc_DAO toaDao, ChiTietToaThuoc_DAO chiTietDao, Thuoc_DAO thuocDao) {
        this.toaDao = toaDao;
        this.chiTietDao = chiTietDao;
        this.thuocDao = thuocDao;
    }

    @Override
    public List<ToaThuoc> findAllToaThuoc() {
        return toaDao.findAllToaThuoc();
    }

    public List<ToaThuoc> findAllKemChiTiet() {
        List<ToaThuoc> danhSach = toaDao.findAllToaThuoc();
        ganChiTiet(danhSach);
        return danhSach;
    }

    @Override
    public ToaThuoc findById(String maToaThuoc) {
        return toaDao.findById(maToaThuoc);
    }

    public ToaThuoc findDetailById(String maToaThuoc) {
        ToaThuoc toa = toaDao.findById(maToaThuoc);
        if (toa == null) {
            return null;
        }
        toa.setChiTiet(chiTietDao.findByMaToaThuoc(maToaThuoc));
        return toa;
    }

    @Override
    public List<ToaThuoc> findByMaKH(String maKH) {
        List<ToaThuoc> danhSach = toaDao.findByMaKH(maKH);
        ganChiTiet(danhSach);
        return danhSach;
    }

    public int nextSo() {
        return toaDao.nextSo();
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
        Transactions.run(() -> {
            chiTietDao.deleteByMaToaThuoc(maToaThuoc);
            toaDao.delete(maToaThuoc);
        });
    }

    @Override
    public List<ChiTietToaThuoc> findChiTietByMaToaThuoc(String maToaThuoc) {
        return chiTietDao.findByMaToaThuoc(maToaThuoc);
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
        int[] ketQua = new int[1];
        Transactions.run(() -> {
            kiemTraThuoc(chiTiet);
            toa.setChiTiet(chiTiet == null ? List.of() : chiTiet);
            ketQua[0] = toaDao.add(toa);
            if (chiTiet != null) {
                for (ChiTietToaThuoc dong : chiTiet) {
                    dong.setToaThuoc(Refs.toaThuoc(toa.getMaToaThuoc()));
                    chiTietDao.add(dong);
                }
            }
        });
        return ketQua[0];
    }

    private void kiemTraThuoc(List<ChiTietToaThuoc> chiTiet) {
        if (chiTiet == null || chiTiet.isEmpty()) {
            return;
        }
        List<String> ids = new ArrayList<>();
        for (ChiTietToaThuoc dong : chiTiet) {
            String ma = Refs.maThuoc(dong.getThuoc());
            if (Refs.blank(ma)) {
                throw new BusinessException("Chi tiết đơn thuốc thiếu mã thuốc.");
            }
            ids.add(ma);
        }
        Set<String> coSan = new HashSet<>(thuocDao.findExisting(ids));
        for (String ma : ids) {
            if (!coSan.contains(ma)) {
                throw new BusinessException("Thuốc không tồn tại: " + ma);
            }
        }
    }

    private void ganChiTiet(List<ToaThuoc> danhSach) {
        if (danhSach.isEmpty()) {
            return;
        }
        List<String> ids = new ArrayList<>();
        for (ToaThuoc toa : danhSach) {
            ids.add(toa.getMaToaThuoc());
        }
        Map<String, List<ChiTietToaThuoc>> chiTiet = chiTietDao.findByMaToaThuocIn(ids);
        for (ToaThuoc toa : danhSach) {
            toa.setChiTiet(chiTiet.getOrDefault(toa.getMaToaThuoc(), List.of()));
        }
    }
}
