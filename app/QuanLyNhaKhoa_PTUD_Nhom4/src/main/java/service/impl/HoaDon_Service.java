package service.impl;

import database.BusinessException;
import database.Transactions;
import dto.HoaDonDong;
import dto.HoaDonKemThanhToan;
import dto.HoaDonThongTinThanhToan;
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

    public HoaDonThongTinThanhToan tinhThongTinThanhToan(String maHoaDon, long chietKhau, long tongTien, long daThu) {
        long ck = Math.max(0, chietKhau);
        long tong = Math.max(0, tongTien);
        long thu = Math.max(0, daThu);
        long thanhTien = Math.max(0, tong - ck);
        long conNo = Math.max(0, thanhTien - thu);
        String trangThaiThanhToan;
        if (thu <= 0) {
            trangThaiThanhToan = "Chưa thanh toán";
        } else if (thu < thanhTien) {
            trangThaiThanhToan = "Thanh toán một phần";
        } else {
            trangThaiThanhToan = "Đã thanh toán";
        }
        return new HoaDonThongTinThanhToan(maHoaDon, tong, ck, thanhTien, thu, conNo, trangThaiThanhToan);
    }

    public HoaDonThongTinThanhToan tinhThongTinThanhToan(HoaDon hoaDon, List<ChiTietHoaDon> chiTiet, long daThu) {
        long tong = 0;
        if (chiTiet != null) {
            for (ChiTietHoaDon dong : chiTiet) {
                tong += dong.getThanhTien();
            }
        }
        long chietKhau = hoaDon == null ? 0 : hoaDon.getChietKhau();
        String ma = hoaDon == null ? null : hoaDon.getMaHoaDon();
        return tinhThongTinThanhToan(ma, chietKhau, tong, daThu);
    }

    private HoaDonKemThanhToan gan(HoaDonDong dong) {
        HoaDon hoaDon = dong.getHoaDon();
        HoaDonThongTinThanhToan thanhToan = tinhThongTinThanhToan(
                hoaDon.getMaHoaDon(), hoaDon.getChietKhau(), dong.getTongTien(), dong.getDaThu());
        return new HoaDonKemThanhToan(hoaDon, thanhToan);
    }

    public List<HoaDonKemThanhToan> findAllKemThanhToan() {
        List<HoaDonKemThanhToan> ketQua = new ArrayList<>();
        for (HoaDonDong dong : hoaDonDao.findAllDong()) {
            ketQua.add(gan(dong));
        }
        return ketQua;
    }

    public List<HoaDonKemThanhToan> findKemThanhToanByMaKH(String maKH) {
        List<HoaDonKemThanhToan> ketQua = new ArrayList<>();
        for (HoaDonDong dong : hoaDonDao.findDongByMaKH(maKH)) {
            ketQua.add(gan(dong));
        }
        return ketQua;
    }

    public HoaDonKemThanhToan findKemThanhToanById(String maHoaDon) {
        HoaDonDong dong = hoaDonDao.findDongById(maHoaDon);
        return dong == null ? null : gan(dong);
    }

    public HoaDonKemThanhToan findDetailKemThanhToanById(String maHoaDon) {
        HoaDonKemThanhToan item = findKemThanhToanById(maHoaDon);
        if (item == null) {
            return null;
        }
        item.getHoaDon().setChiTietHoaDon(chiTietDao.findByMaHoaDon(maHoaDon));
        return item;
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
        return hoaDonDao.findByMaKH(maKH);
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
            hoaDon.setChietKhau(Math.max(0, hoaDon.getChietKhau()));
            hoaDon.setChiTietHoaDon(chiTiet == null ? List.of() : chiTiet);
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

    public HoaDonKemThanhToan thuTien(String maHoaDon, long soTien, PhieuThu phieu) {
        if (soTien <= 0) {
            throw new BusinessException("Số tiền thu phải lớn hơn 0.");
        }
        if (phieu == null) {
            throw new BusinessException("Thiếu phiếu thu.");
        }
        HoaDonKemThanhToan[] ketQua = new HoaDonKemThanhToan[1];
        Transactions.run(() -> {
            HoaDonDong dong = hoaDonDao.lockDongById(maHoaDon);
            if (dong == null) {
                throw new BusinessException("Không tìm thấy hóa đơn.");
            }
            HoaDonThongTinThanhToan thanhToan = tinhThongTinThanhToan(
                    dong.getHoaDon().getMaHoaDon(),
                    dong.getHoaDon().getChietKhau(),
                    dong.getTongTien(),
                    dong.getDaThu());
            if (soTien > thanhToan.getConNo()) {
                throw new BusinessException("Số tiền thu vượt quá số tiền còn nợ.");
            }
            HoaDon hoaDon = dong.getHoaDon();
            if (phieu.getKhachHang() == null) {
                phieu.setKhachHang(hoaDon.getKhachHang());
            }
            phieu.setHoaDon(Refs.hoaDon(hoaDon.getMaHoaDon()));
            phieu.setSoTien(soTien);
            phieuThuDao.add(phieu);
            ketQua[0] = gan(hoaDonDao.findDongById(maHoaDon));
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
}
