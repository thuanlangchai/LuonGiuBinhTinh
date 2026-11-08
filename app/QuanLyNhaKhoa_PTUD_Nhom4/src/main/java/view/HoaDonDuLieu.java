package view;

import dto.HoaDonKemThanhToan;
import dto.HoaDonThongTinThanhToan;
import entity.ChiTietHoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuThu;
import entity.Rang;
import entity.Refs;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import service.impl.DanhMuc_Service;
import service.impl.HoaDon_Service;
import service.impl.PhieuThu_Service;
import view.model.ChiTietHoaDonView;
import view.model.HoaDonView;

public final class HoaDonDuLieu {

    private static final List<Runnable> LANG_NGHE = new ArrayList<>();
    private static final HoaDon_Service hoaDonService = new HoaDon_Service();
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
        for (HoaDonKemThanhToan item : hoaDonService.findAllKemThanhToan()) {
            ketQua.add(mapView(item));
        }
        return ketQua;
    }

    public static HoaDonView theoMa(String ma) {
        HoaDonKemThanhToan item = hoaDonService.findDetailKemThanhToanById(ma);
        return item == null ? null : mapView(item);
    }

    public static List<HoaDonView> theoKhach(String maKh) {
        List<HoaDonView> ketQua = new ArrayList<>();
        for (HoaDonKemThanhToan item : hoaDonService.findKemThanhToanByMaKH(maKh)) {
            ketQua.add(mapView(item));
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
        return them(ngayLap, maKh, tenKh, keHoach, bacSi, chietKhau, ghiChu, chiTiet, List.of());
    }

    public static HoaDonView them(String ngayLap, String maKh, String tenKh, String keHoach, String bacSi,
            long chietKhau, String ghiChu, List<ChiTietHoaDonView> chiTiet, List<String> maChiTietKeHoach) {
        HoaDon e = new HoaDon();
        e.setMaHoaDon(maMoi());
        e.setNgayLap(NgayUtil.parseDate(ngayLap) != null ? NgayUtil.parseDate(ngayLap) : LocalDate.now());
        KhachHang khach = Refs.khachHang(maKh);
        if (khach != null) {
            khach.setHoTen(tenKh);
        }
        e.setKhachHang(khach);
        e.setKeHoachDieuTri(Refs.keHoach(trichMaKeHoach(keHoach)));
        NhanVien bacSiEntity = Refs.nhanVien(maBacSiTheoTen(bacSi));
        if (bacSiEntity != null) {
            bacSiEntity.setHoTen(bacSi);
        }
        e.setBacSi(bacSiEntity);
        e.setChietKhau(Math.max(0, chietKhau));
        e.setGhiChu(ghiChu);
        e.setNgayTao(LocalDateTime.now());
        e.setNguoiTao(Refs.nhanVien(DuLieuMau.NGUOI));
        List<ChiTietHoaDon> cts = new ArrayList<>();
        int i = 1;
        if (chiTiet != null) {
            for (ChiTietHoaDonView cv : chiTiet) {
                ChiTietHoaDon ct = new ChiTietHoaDon();
                ct.setMaChiTiet(e.getMaHoaDon() + "C" + i++);
                ct.setHoaDon(Refs.hoaDon(e.getMaHoaDon()));
                ct.setNgay(NgayUtil.parseDate(cv.ngay) != null ? NgayUtil.parseDate(cv.ngay) : e.getNgayLap());
                ct.setRang(Refs.rang(maRangTheoTen(cv.rang)));
                ct.setDichVu(Refs.dichVu(maDichVuTheoTen(cv.dichVu)));
                ct.setSoLuong(cv.soLuong);
                ct.setDonGia(cv.donGia);
                ct.setKeHoach(cv.keHoach);
                cts.add(ct);
            }
        }
        e.setChiTietHoaDon(cts);
        if (hoaDonService.themHoaDonVoiChiTiet(e, cts, maChiTietKeHoach) == null) {
            return null;
        }
        bao();
        HoaDonKemThanhToan item = hoaDonService.findDetailKemThanhToanById(e.getMaHoaDon());
        return item == null ? null : mapView(item);
    }

    public static void thuTien(HoaDonView hd, long soTien) {
        if (hd == null || soTien <= 0) {
            return;
        }
        PhieuThu pt = new PhieuThu();
        pt.setMaPhieuThu(maPhieuThuMoi());
        pt.setNgayThu(LocalDate.now());
        pt.setKhachHang(Refs.khachHang(hd.maKh));
        pt.setLyDo("Thu tiền hóa đơn " + hd.ma);
        pt.setSoTien(soTien);
        pt.setHinhThucThanhToan("Tiền mặt");
        pt.setTrangThai("Đã chi trả");
        pt.setNgayTao(LocalDateTime.now());
        pt.setNguoiTao(Refs.nhanVien(DuLieuMau.NGUOI));
        HoaDonKemThanhToan item = hoaDonService.thuTien(hd.ma, soTien, pt);
        HoaDonThongTinThanhToan tt = item.getThanhToan();
        hd.daThu = tt.getDaThu();
        hd.thanhTien = tt.getThanhTien();
        hd.tong = tt.getTongTien();
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

    private static HoaDonView mapView(HoaDonKemThanhToan item) {
        HoaDon e = item.getHoaDon();
        HoaDonThongTinThanhToan tt = item.getThanhToan();
        HoaDonView v = new HoaDonView();
        v.ma = e.getMaHoaDon();
        v.ngayLap = NgayUtil.fmt(e.getNgayLap());
        v.maKh = Refs.maKh(e.getKhachHang());
        v.tenKh = Refs.tenKh(e.getKhachHang());
        String maKeHoach = Refs.maKhdt(e.getKeHoachDieuTri());
        v.keHoach = maKeHoach == null ? "" : "Kế hoạch " + maKeHoach;
        v.bacSi = e.getBacSi() == null ? "" : Refs.tenNv(e.getBacSi());
        v.ghiChu = e.getGhiChu();
        v.trangThai = tt.getTrangThaiThanhToan();
        v.ngayTao = NgayUtil.fmtFull(e.getNgayTao());
        v.tong = tt.getTongTien();
        v.chietKhau = e.getChietKhau();
        v.thanhTien = tt.getThanhTien();
        v.daThu = tt.getDaThu();
        List<ChiTietHoaDon> chiTiet = e.getChiTietHoaDon() == null ? List.of() : e.getChiTietHoaDon();
        for (ChiTietHoaDon ct : chiTiet) {
            v.chiTiet.add(new ChiTietHoaDonView(
                    NgayUtil.fmt(ct.getNgay()),
                    Refs.tenRang(ct.getRang()),
                    Refs.tenDv(ct.getDichVu()),
                    ct.getSoLuong(),
                    ct.getDonGia(),
                    ct.getKeHoach()));
        }
        return v;
    }

    private static String maMoi() {
        return String.format("%04d", hoaDonService.nextSo() + 1);
    }

    private static String maPhieuThuMoi() {
        return String.format("%04d", phieuThuService.nextSo() + 1);
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
}
