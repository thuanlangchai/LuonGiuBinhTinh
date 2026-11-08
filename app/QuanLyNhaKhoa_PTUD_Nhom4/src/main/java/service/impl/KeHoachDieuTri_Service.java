package service.impl;

import database.BusinessException;
import database.Transactions;
import entity.ChiTietKeHoachDieuTri;
import entity.KeHoachDieuTri;
import entity.Refs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        return keHoachDao.findAllKeHoachDieuTri();
    }

    public List<KeHoachDieuTri> findAllKemChiTiet() {
        List<KeHoachDieuTri> danhSach = keHoachDao.findAllKeHoachDieuTri();
        ganChiTiet(danhSach);
        return danhSach;
    }

    @Override
    public KeHoachDieuTri findById(String maKeHoach) {
        return keHoachDao.findById(maKeHoach);
    }

    public KeHoachDieuTri findDetailById(String maKeHoach) {
        KeHoachDieuTri keHoach = keHoachDao.findById(maKeHoach);
        if (keHoach == null) {
            return null;
        }
        keHoach.setChiTiet(chiTietDao.findByMaKeHoach(maKeHoach));
        return keHoach;
    }

    @Override
    public List<KeHoachDieuTri> findByMaKH(String maKH) {
        List<KeHoachDieuTri> danhSach = keHoachDao.findByMaKH(maKH);
        ganChiTiet(danhSach);
        return danhSach;
    }

    public int nextSo() {
        return keHoachDao.nextSo();
    }

    public int nextChiTiet() {
        return chiTietDao.nextSo();
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
        boolean[] ok = new boolean[1];
        Transactions.run(() -> {
            KeHoachDieuTri keHoach = keHoachDao.findById(maKeHoach);
            if (keHoach == null || daHuy(keHoach.getTrangThai())) {
                ok[0] = false;
                return;
            }
            for (ChiTietKeHoachDieuTri dong : chiTietDao.findByMaKeHoach(maKeHoach)) {
                if (!Refs.blank(Refs.maHd(dong.getHoaDon()))) {
                    ok[0] = false;
                    return;
                }
            }
            keHoach.setTrangThai("Đã hủy");
            keHoachDao.update(keHoach);
            ok[0] = true;
        });
        return ok[0];
    }

    @Override
    public List<ChiTietKeHoachDieuTri> findChiTietByMaKeHoach(String maKeHoach) {
        return chiTietDao.findByMaKeHoach(maKeHoach);
    }

    @Override
    public ChiTietKeHoachDieuTri findChiTietById(String maChiTiet) {
        return chiTietDao.findById(maChiTiet);
    }

    @Override
    public Integer addChiTiet(ChiTietKeHoachDieuTri entity) {
        if (entity == null || Refs.blank(Refs.maKhdt(entity.getKeHoachDieuTri()))) {
            return null;
        }
        int[] ketQua = new int[1];
        boolean[] tuChoi = new boolean[1];
        Transactions.run(() -> {
            KeHoachDieuTri keHoach = keHoachDao.findById(Refs.maKhdt(entity.getKeHoachDieuTri()));
            if (keHoach == null || daHuy(keHoach.getTrangThai())) {
                tuChoi[0] = true;
                return;
            }
            ketQua[0] = chiTietDao.add(entity);
            if ("Mới".equals(keHoach.getTrangThai())) {
                keHoach.setTrangThai("Đang điều trị");
                keHoachDao.update(keHoach);
            }
        });
        return tuChoi[0] ? null : ketQua[0];
    }

    @Override
    public void updateChiTiet(ChiTietKeHoachDieuTri entity) {
        Transactions.run(() -> {
            ChiTietKeHoachDieuTri cu = chiTietDao.findById(entity.getMaChiTiet());
            if (cu != null && !Refs.blank(Refs.maHd(cu.getHoaDon())) && !Refs.blank(Refs.maHd(entity.getHoaDon()))) {
                boolean doiDichVu = !Objects.equals(Refs.maDv(cu.getDichVu()), Refs.maDv(entity.getDichVu()));
                boolean doiRang = !Objects.equals(Refs.maRang(cu.getRang()), Refs.maRang(entity.getRang()));
                if (doiDichVu || doiRang || cu.getSoLuong() != entity.getSoLuong() || cu.getDonGia() != entity.getDonGia()) {
                    throw new BusinessException("Không thay đổi chi tiết kế hoạch đã lập hóa đơn.");
                }
            }
            chiTietDao.update(entity);
        });
    }

    @Override
    public void deleteChiTiet(String maChiTiet) {
        Transactions.run(() -> {
            ChiTietKeHoachDieuTri cu = chiTietDao.findById(maChiTiet);
            if (cu != null && !Refs.blank(Refs.maHd(cu.getHoaDon()))) {
                return;
            }
            chiTietDao.delete(maChiTiet);
        });
    }

    public void boHoaDon(String maHoaDon) {
        chiTietDao.boHoaDon(maHoaDon);
    }

    private void ganChiTiet(List<KeHoachDieuTri> danhSach) {
        if (danhSach.isEmpty()) {
            return;
        }
        List<String> ids = new ArrayList<>();
        for (KeHoachDieuTri keHoach : danhSach) {
            ids.add(keHoach.getMaKeHoach());
        }
        Map<String, List<ChiTietKeHoachDieuTri>> chiTiet = chiTietDao.findByMaKeHoachIn(ids);
        for (KeHoachDieuTri keHoach : danhSach) {
            keHoach.setChiTiet(chiTiet.getOrDefault(keHoach.getMaKeHoach(), List.of()));
        }
    }

    private static boolean daHuy(String trangThai) {
        return "Đã hủy".equalsIgnoreCase(trangThai) || "Hủy".equalsIgnoreCase(trangThai);
    }
}
