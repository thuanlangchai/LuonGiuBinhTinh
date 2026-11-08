package service.impl;

import database.BusinessException;
import database.Transactions;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.PhieuThu;
import entity.Refs;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import repository.impl.ChiTietHoaDon_DAO;
import repository.impl.ChiTietKeHoachDieuTri_DAO;
import repository.impl.HoaDon_DAO;
import repository.impl.PhieuThu_DAO;
import service.HoaDon_IService;

public class HoaDon_Service implements HoaDon_IService {

    private final HoaDon_DAO hoaDonDao;
    private final ChiTietHoaDon_DAO chiTietDao;
    private final ChiTietKeHoachDieuTri_DAO chiTietKeHoachDao;
    private final PhieuThu_DAO phieuThuDao;

    public HoaDon_Service() {
        this(new HoaDon_DAO(), new ChiTietHoaDon_DAO(), new ChiTietKeHoachDieuTri_DAO(), new PhieuThu_DAO());
    }

    public HoaDon_Service(HoaDon_DAO hoaDonDao, ChiTietHoaDon_DAO chiTietDao) {
        this(hoaDonDao, chiTietDao, new ChiTietKeHoachDieuTri_DAO(), new PhieuThu_DAO());
    }

    public HoaDon_Service(HoaDon_DAO hoaDonDao, ChiTietHoaDon_DAO chiTietDao,
            ChiTietKeHoachDieuTri_DAO chiTietKeHoachDao, PhieuThu_DAO phieuThuDao) {
        this.hoaDonDao = hoaDonDao;
        this.chiTietDao = chiTietDao;
        this.chiTietKeHoachDao = chiTietKeHoachDao;
        this.phieuThuDao = phieuThuDao;
    }

    @Override
    public List<HoaDon> findAllHoaDon() {
        return hoaDonDao.findAllHoaDon();
    }

    public List<HoaDon> findAllKemChiTiet() {
        List<HoaDon> danhSach = hoaDonDao.findAllHoaDon();
        ganChiTiet(danhSach);
        return danhSach;
    }

    @Override
    public HoaDon findById(String maHoaDon) {
        return hoaDonDao.findById(maHoaDon);
    }

    public HoaDon findDetailById(String maHoaDon) {
        HoaDon hoaDon = hoaDonDao.findById(maHoaDon);
        if (hoaDon == null) {
            return null;
        }
        hoaDon.setChiTietHoaDon(chiTietDao.findByMaHoaDon(maHoaDon));
        return hoaDon;
    }

    @Override
    public List<HoaDon> findByMaKH(String maKH) {
        List<HoaDon> danhSach = hoaDonDao.findByMaKH(maKH);
        ganChiTiet(danhSach);
        return danhSach;
    }

    public int nextSo() {
        return hoaDonDao.nextSo();
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
        Transactions.run(() -> {
            if (phieuThuDao.countByMaHoaDon(maHoaDon) > 0) {
                throw new BusinessException("Không xóa hóa đơn đã phát sinh phiếu thu.");
            }
            chiTietKeHoachDao.boHoaDon(maHoaDon);
            chiTietDao.deleteByMaHoaDon(maHoaDon);
            hoaDonDao.delete(maHoaDon);
        });
    }

    @Override
    public List<ChiTietHoaDon> findChiTietByMaHoaDon(String maHoaDon) {
        return chiTietDao.findByMaHoaDon(maHoaDon);
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
        return themHoaDonVoiChiTiet(hoaDon, chiTiet, List.of());
    }

    public Integer themHoaDonVoiChiTiet(HoaDon hoaDon, List<ChiTietHoaDon> chiTiet, List<String> maChiTietKeHoach) {
        if (hoaDon == null) {
            return null;
        }
        int[] ketQua = new int[1];
        Transactions.run(() -> {
            chuanHoaTien(hoaDon, chiTiet);
            ketQua[0] = hoaDonDao.add(hoaDon);
            if (chiTiet != null) {
                for (ChiTietHoaDon dong : chiTiet) {
                    dong.setHoaDon(Refs.hoaDon(hoaDon.getMaHoaDon()));
                    chiTietDao.add(dong);
                }
            }
            List<String> lienKet = Refs.ids(maChiTietKeHoach);
            if (!lienKet.isEmpty()) {
                int gan = chiTietKeHoachDao.ganHoaDon(lienKet, hoaDon.getMaHoaDon());
                if (gan != lienKet.size()) {
                    throw new BusinessException("Một số dòng kế hoạch đã được lập hóa đơn hoặc không còn tồn tại.");
                }
            }
        });
        return ketQua[0];
    }

    public HoaDon thuTien(String maHoaDon, long soTien, PhieuThu phieu) {
        if (soTien <= 0) {
            throw new BusinessException("Số tiền thu phải lớn hơn 0.");
        }
        if (phieu == null) {
            throw new BusinessException("Thiếu phiếu thu.");
        }
        HoaDon[] ketQua = new HoaDon[1];
        Transactions.run(() -> {
            HoaDon hoaDon = hoaDonDao.lockById(maHoaDon);
            if (hoaDon == null) {
                throw new BusinessException("Không tìm thấy hóa đơn.");
            }
            if (soTien > hoaDon.getConNo()) {
                throw new BusinessException("Số tiền thu vượt quá số tiền còn nợ.");
            }
            if (phieu.getKhachHang() == null) {
                phieu.setKhachHang(hoaDon.getKhachHang());
            }
            phieu.setHoaDon(Refs.hoaDon(hoaDon.getMaHoaDon()));
            phieu.setSoTien(soTien);
            phieuThuDao.add(phieu);
            long daThu = hoaDon.getDaThu() + soTien;
            hoaDon.setDaThu(daThu);
            hoaDon.setConNo(Math.max(0, hoaDon.getThanhTien() - daThu));
            if (daThu <= 0) {
                hoaDon.setTrangThai("Chưa thanh toán");
            } else if (daThu < hoaDon.getThanhTien()) {
                hoaDon.setTrangThai("Thanh toán một phần");
            } else {
                hoaDon.setTrangThai("Đã thanh toán");
            }
            hoaDonDao.update(hoaDon);
            ketQua[0] = hoaDon;
        });
        return ketQua[0];
    }

    private void ganChiTiet(List<HoaDon> danhSach) {
        if (danhSach.isEmpty()) {
            return;
        }
        List<String> ids = new ArrayList<>();
        for (HoaDon hoaDon : danhSach) {
            ids.add(hoaDon.getMaHoaDon());
        }
        Map<String, List<ChiTietHoaDon>> chiTiet = chiTietDao.findByMaHoaDonIn(ids);
        for (HoaDon hoaDon : danhSach) {
            hoaDon.setChiTietHoaDon(chiTiet.getOrDefault(hoaDon.getMaHoaDon(), List.of()));
        }
    }

    private static void chuanHoaTien(HoaDon hoaDon, List<ChiTietHoaDon> chiTiet) {
        long tong = 0;
        if (chiTiet != null) {
            for (ChiTietHoaDon dong : chiTiet) {
                long thanh = dong.getSoLuong() * dong.getDonGia();
                dong.setThanhTien(thanh);
                tong += thanh;
            }
        }
        long chietKhau = Math.max(0, hoaDon.getChietKhau());
        long thanhTien = Math.max(0, tong - chietKhau);
        hoaDon.setTongTien(tong);
        hoaDon.setChietKhau(chietKhau);
        hoaDon.setThanhTien(thanhTien);
        hoaDon.setDaThu(0);
        hoaDon.setConNo(thanhTien);
        if (hoaDon.getTrangThai() == null || hoaDon.getTrangThai().isBlank()) {
            hoaDon.setTrangThai("Chưa thanh toán");
        }
    }
}
