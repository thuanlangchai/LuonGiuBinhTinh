package view;

import entity.ChiTietHoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuThu;
import entity.Rang;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import service.impl.DanhMuc_Service;
import service.impl.HoaDon_Service;
import service.impl.KhachHang_Service;
import service.impl.PhieuThu_Service;
import view.model.ChiTietHoaDonView;
import view.model.HoaDonView;

public final class HoaDonDuLieu {

    private static final List<Runnable> LANG_NGHE = new ArrayList<>();
    private static final HoaDon_Service hoaDonService = new HoaDon_Service();
    private static final KhachHang_Service khachHangService = new KhachHang_Service();
    private static final DanhMuc_Service danhMucService = new DanhMuc_Service();
    private static final PhieuThu_Service phieuThuService = new PhieuThu_Service();

    private HoaDonDuLieu() {
    }

    public static void khiDoi(Runnable r) {
        if (r != null) {
            LANG_NGHE.add(r);
        }
    }

    private static void bao() {
        for (Runnable r : LANG_NGHE) {
            r.run();
        }
    }

    public static List<HoaDonView> tatCa() {
        List<HoaDonView> ketQua = new ArrayList<>();
        for (HoaDon e : hoaDonService.findAllHoaDon()) {
            ketQua.add(mapView(e));
        }
        return ketQua;
    }

    public static HoaDonView theoMa(String ma) {
        HoaDon e = hoaDonService.findById(ma);
        return e == null ? null : mapView(e);
    }

    public static List<HoaDonView> theoKhach(String maKh) {
        List<HoaDonView> ketQua = new ArrayList<>();
        for (HoaDon e : hoaDonService.findByMaKH(maKh)) {
            ketQua.add(mapView(e));
        }
        return ketQua;
    }

    public static List<HoaDonView> conNoTheoKhach(String maKh) {
        List<HoaDonView> ketQua = new ArrayList<>();
        for (HoaDonView hd : theoKhach(maKh)) {
            if (hd.conNo() > 0) {
                ketQua.add(hd);
            }
        }
        return ketQua;
    }

    public static HoaDonView them(String ngayLap, String maKh, String tenKh, String keHoach, String bacSi,
            long chietKhau, String ghiChu, List<ChiTietHoaDonView> chiTiet) {
        HoaDon e = new HoaDon();
        e.setMaHoaDon(maMoi());
        e.setNgayLap(NgayUtil.parseDate(ngayLap) != null ? NgayUtil.parseDate(ngayLap) : LocalDate.now());
        e.setMaKH(maKh);
        e.setMaKeHoach(trichMaKeHoach(keHoach));
        e.setMaBacSi(maBacSiTheoTen(bacSi));
        e.setChietKhau(Math.max(0, chietKhau));
        e.setGhiChu(ghiChu);
        e.setNgayTao(LocalDateTime.now());
        e.setNguoiTao(DuLieuMau.NGUOI);
        long tong = 0;
        List<ChiTietHoaDon> cts = new ArrayList<>();
        int i = 1;
        if (chiTiet != null) {
            for (ChiTietHoaDonView cv : chiTiet) {
                ChiTietHoaDon ct = new ChiTietHoaDon();
                ct.setMaChiTiet(e.getMaHoaDon() + "C" + i++);
                ct.setMaHoaDon(e.getMaHoaDon());
                ct.setNgay(NgayUtil.parseDate(cv.ngay) != null ? NgayUtil.parseDate(cv.ngay) : e.getNgayLap());
                ct.setMaRang(maRangTheoTen(cv.rang));
                ct.setMaDichVu(maDichVuTheoTen(cv.dichVu));
                ct.setSoLuong(cv.soLuong);
                ct.setDonGia(cv.donGia);
                ct.setThanhTien(cv.thanhTien());
                ct.setKeHoach(cv.keHoach);
                tong += ct.getThanhTien();
                cts.add(ct);
            }
        }
        e.setTongTien(tong);
        e.setThanhTien(Math.max(0, tong - e.getChietKhau()));
        e.setDaThu(0);
        e.setConNo(e.getThanhTien());
        e.setTrangThai("Chưa thanh toán");
        if (hoaDonService.themHoaDonVoiChiTiet(e, cts) == null) {
            return null;
        }
        bao();
        return mapView(e);
    }

    public static void thuTien(HoaDonView hd, long soTien) {
        if (hd == null || soTien <= 0) {
            return;
        }
        HoaDon e = hoaDonService.findById(hd.ma);
        if (e == null) {
            return;
        }
        long daThu = e.getDaThu() + soTien;
        if (daThu > e.getThanhTien()) {
            daThu = e.getThanhTien();
        }
        e.setDaThu(daThu);
        e.setConNo(Math.max(0, e.getThanhTien() - daThu));
        if (daThu <= 0) {
            e.setTrangThai("Chưa thanh toán");
        } else if (daThu < e.getThanhTien()) {
            e.setTrangThai("Thanh toán một phần");
        } else {
            e.setTrangThai("Đã thanh toán");
        }
        hoaDonService.update(e);

        PhieuThu pt = new PhieuThu();
        pt.setMaPhieuThu(maPhieuThuMoi());
        pt.setNgayThu(LocalDate.now());
        pt.setMaKH(e.getMaKH());
        pt.setMaHoaDon(e.getMaHoaDon());
        pt.setLyDo("Thu tiền hóa đơn " + e.getMaHoaDon());
        pt.setSoTien(soTien);
        pt.setHinhThucThanhToan("Tiền mặt");
        pt.setTrangThai("Đã chi trả");
        pt.setNgayTao(LocalDateTime.now());
        pt.setNguoiTao(DuLieuMau.NGUOI);
        phieuThuService.add(pt);

        hd.daThu = e.getDaThu();
        hd.capNhatTrangThai();
        bao();
    }

    public static void xoa(HoaDonView hd) {
        if (hd == null) {
            return;
        }
        hoaDonService.delete(hd.ma);
        bao();
    }

    public static long daThuTheoKhach(String maKh) {
        long t = 0;
        for (HoaDonView hd : theoKhach(maKh)) {
            t += hd.daThu;
        }
        return t;
    }

    public static long conNoTheoKhachTong(String maKh) {
        long t = 0;
        for (HoaDonView hd : theoKhach(maKh)) {
            t += hd.conNo();
        }
        return t;
    }

    public static Object[][] dongCongNo() {
        Map<String, long[]> gop = new LinkedHashMap<>();
        Map<String, String> ten = new LinkedHashMap<>();
        for (HoaDonView hd : tatCa()) {
            long[] so = gop.computeIfAbsent(hd.maKh, k -> new long[2]);
            so[0] += hd.thanhTien;
            so[1] += hd.daThu;
            ten.put(hd.maKh, hd.tenKh);
        }
        if (gop.isEmpty()) {
            return new Object[][]{{"", "", "", "Tổng", "0"}};
        }
        Object[][] dong = new Object[gop.size() + 1][];
        int i = 0;
        long tongPs = 0;
        long tongThu = 0;
        for (Map.Entry<String, long[]> e : gop.entrySet()) {
            long ps = e.getValue()[0];
            long thu = e.getValue()[1];
            tongPs += ps;
            tongThu += thu;
            dong[i] = new Object[]{
                String.valueOf(i + 1),
                e.getKey() + " - " + ten.get(e.getKey()),
                ViewTheme.tien(ps),
                ViewTheme.tien(thu),
                ViewTheme.tien(Math.max(0, ps - thu))
            };
            i++;
        }
        dong[i] = new Object[]{"", "Tổng", ViewTheme.tien(tongPs), ViewTheme.tien(tongThu),
            ViewTheme.tien(Math.max(0, tongPs - tongThu))};
        return dong;
    }

    public static String homNay() {
        return NgayUtil.fmt(LocalDate.now());
    }

    private static HoaDonView mapView(HoaDon e) {
        HoaDonView v = new HoaDonView();
        v.ma = e.getMaHoaDon();
        v.ngayLap = NgayUtil.fmt(e.getNgayLap());
        v.maKh = e.getMaKH();
        KhachHang kh = khachHangService.findById(e.getMaKH());
        v.tenKh = kh != null ? kh.getHoTen() : e.getMaKH();
        v.keHoach = e.getMaKeHoach() == null ? "" : "Kế hoạch " + e.getMaKeHoach();
        NhanVien nv = e.getMaBacSi() == null ? null : danhMucService.findNhanVienById(e.getMaBacSi());
        v.bacSi = nv != null ? nv.getHoTen() : (e.getMaBacSi() == null ? "" : e.getMaBacSi());
        v.ghiChu = e.getGhiChu();
        v.trangThai = e.getTrangThai();
        v.ngayTao = NgayUtil.fmtFull(e.getNgayTao());
        v.tong = e.getTongTien();
        v.chietKhau = e.getChietKhau();
        v.thanhTien = e.getThanhTien();
        v.daThu = e.getDaThu();
        Map<String, String> tenDv = tenDichVu();
        Map<String, String> tenRang = tenRang();
        for (ChiTietHoaDon ct : hoaDonService.findChiTietByMaHoaDon(e.getMaHoaDon())) {
            v.chiTiet.add(new ChiTietHoaDonView(
                    NgayUtil.fmt(ct.getNgay()),
                    ct.getMaRang() == null ? "" : tenRang.getOrDefault(ct.getMaRang(), ct.getMaRang()),
                    tenDv.getOrDefault(ct.getMaDichVu(), ct.getMaDichVu()),
                    ct.getSoLuong(),
                    ct.getDonGia(),
                    ct.getKeHoach()));
        }
        return v;
    }

    private static String maMoi() {
        int max = 0;
        for (HoaDon e : hoaDonService.findAllHoaDon()) {
            try {
                max = Math.max(max, Integer.parseInt(e.getMaHoaDon()));
            } catch (Exception ignored) {
            }
        }
        return String.format("%04d", max + 1);
    }

    private static String maPhieuThuMoi() {
        int max = 0;
        for (PhieuThu p : phieuThuService.findAllPhieuThu()) {
            try {
                max = Math.max(max, Integer.parseInt(p.getMaPhieuThu()));
            } catch (Exception ignored) {
            }
        }
        return String.format("%04d", max + 1);
    }

    private static String trichMaKeHoach(String keHoach) {
        if (keHoach == null) {
            return null;
        }
        if (keHoach.startsWith("KH")) {
            int sp = keHoach.indexOf(' ');
            return sp > 0 ? keHoach.substring(0, sp) : keHoach;
        }
        int i = keHoach.indexOf("KH");
        if (i >= 0) {
            String sub = keHoach.substring(i);
            int sp = sub.indexOf(' ');
            return sp > 0 ? sub.substring(0, sp) : sub;
        }
        return null;
    }

    private static String maBacSiTheoTen(String ten) {
        if (ten == null || ten.isBlank()) {
            return null;
        }
        for (NhanVien nv : danhMucService.findAllNhanVien()) {
            if (ten.equals(nv.getHoTen()) || ten.equals(nv.getMaNhanVien())) {
                return nv.getMaNhanVien();
            }
        }
        return null;
    }

    private static String maDichVuTheoTen(String ten) {
        for (DichVu dv : danhMucService.findAllDichVu()) {
            if (ten != null && ten.equals(dv.getTenDichVu())) {
                return dv.getMaDichVu();
            }
        }
        return null;
    }

    private static String maRangTheoTen(String ten) {
        if (ten == null || ten.isBlank()) {
            return null;
        }
        for (Rang r : danhMucService.findAllRang()) {
            if (ten.equals(r.getMaRang()) || ten.equals(r.getTenRang())) {
                return r.getMaRang();
            }
        }
        return null;
    }

    private static Map<String, String> tenDichVu() {
        Map<String, String> m = new HashMap<>();
        for (DichVu dv : danhMucService.findAllDichVu()) {
            m.put(dv.getMaDichVu(), dv.getTenDichVu());
        }
        return m;
    }

    private static Map<String, String> tenRang() {
        Map<String, String> m = new HashMap<>();
        for (Rang r : danhMucService.findAllRang()) {
            m.put(r.getMaRang(), r.getTenRang());
        }
        return m;
    }
}
