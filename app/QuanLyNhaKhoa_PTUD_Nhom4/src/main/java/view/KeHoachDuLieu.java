package view;

import entity.ChiTietKeHoachDieuTri;
import entity.DichVu;
import entity.KeHoachDieuTri;
import entity.KhachHang;
import entity.NhanVien;
import entity.Rang;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.impl.DanhMuc_Service;
import service.impl.KeHoachDieuTri_Service;
import service.impl.KhachHang_Service;
import view.model.HangDT;
import view.model.KeHoachView;

public final class KeHoachDuLieu {

    private static final List<Runnable> LANG_NGHE = new ArrayList<>();
    private static final KeHoachDieuTri_Service keHoachService = new KeHoachDieuTri_Service();
    private static final KhachHang_Service khachHangService = new KhachHang_Service();
    private static final DanhMuc_Service danhMucService = new DanhMuc_Service();

    private KeHoachDuLieu() {
    }

    public static void khiDoi(Runnable r) {
        if (r != null) {
            LANG_NGHE.add(r);
        }
    }

    private static void baoDoi() {
        for (Runnable r : LANG_NGHE) {
            r.run();
        }
    }

    public static List<KeHoachView> tatCa() {
        List<KeHoachView> ketQua = new ArrayList<>();
        for (KeHoachDieuTri e : keHoachService.findAllKeHoachDieuTri()) {
            ketQua.add(mapView(e));
        }
        return ketQua;
    }

    public static KeHoachView theoMa(String maKeHoach) {
        KeHoachDieuTri e = keHoachService.findById(maKeHoach);
        return e == null ? null : mapView(e);
    }

    public static List<KeHoachView> theoMaKH(String maKH) {
        List<KeHoachView> ketQua = new ArrayList<>();
        for (KeHoachDieuTri e : keHoachService.findByMaKH(maKH)) {
            ketQua.add(mapView(e));
        }
        return ketQua;
    }

    public static String maMoi() {
        int max = 0;
        for (KeHoachDieuTri e : keHoachService.findAllKeHoachDieuTri()) {
            String ma = e.getMaKeHoach();
            if (ma != null && ma.startsWith("KH")) {
                try {
                    max = Math.max(max, Integer.parseInt(ma.substring(2)));
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return String.format("KH%02d", max + 1);
    }

    public static KeHoachView them(String maKH, String tenKh, String ngayLap, String maBacSi,
            String tenBacSi, String lyDo, String ghiChu) {
        KeHoachDieuTri e = new KeHoachDieuTri();
        e.setMaKeHoach(maMoi());
        e.setMaKH(maKH);
        e.setNgayKeHoach(NgayUtil.parseDate(ngayLap) != null ? NgayUtil.parseDate(ngayLap) : LocalDate.now());
        e.setMaBacSi(maBacSi);
        e.setLyDoDieuTri(lyDo);
        e.setGhiChu(ghiChu);
        e.setTrangThai("Mới");
        e.setNgayTao(LocalDateTime.now());
        e.setNguoiTao(DuLieuMau.NGUOI);
        if (keHoachService.add(e) == null) {
            return null;
        }
        baoDoi();
        return mapView(e);
    }

    public static void capNhat(KeHoachView kh) {
        if (kh == null) {
            return;
        }
        KeHoachDieuTri e = keHoachService.findById(kh.maKeHoach);
        if (e == null) {
            return;
        }
        e.setNgayKeHoach(NgayUtil.parseDate(kh.ngayLap) != null ? NgayUtil.parseDate(kh.ngayLap) : e.getNgayKeHoach());
        e.setMaBacSi(kh.maBacSi);
        e.setLyDoDieuTri(kh.lyDoDieuTri);
        e.setGhiChu(kh.ghiChu);
        e.setTrangThai(kh.trangThai);
        keHoachService.update(e);
        baoDoi();
    }

    public static boolean huy(String maKeHoach) {
        boolean ok = keHoachService.huy(maKeHoach);
        if (ok) {
            baoDoi();
        }
        return ok;
    }

    public static boolean themDichVu(String maKeHoach, HangDT hang) {
        KeHoachDieuTri kh = keHoachService.findById(maKeHoach);
        if (kh == null || hang == null) {
            return false;
        }
        if ("Đã hủy".equalsIgnoreCase(kh.getTrangThai())) {
            return false;
        }
        String maDv = hang.maDichVu;
        if (maDv == null || maDv.isBlank()) {
            maDv = maDichVuTheoTen(hang.dichVu);
        }
        if (maDv == null) {
            return false;
        }
        ChiTietKeHoachDieuTri ct = new ChiTietKeHoachDieuTri();
        ct.setMaChiTiet(maChiTietMoi());
        ct.setMaKeHoach(maKeHoach);
        ct.setNgay(NgayUtil.parseDate(hang.ngay) != null ? NgayUtil.parseDate(hang.ngay) : LocalDate.now());
        ct.setMaRang(maRangTheoTen(hang.rang));
        ct.setMaDichVu(maDv);
        ct.setSoLuong(hang.soLuong);
        ct.setDonGia(hang.donGia);
        ct.setThanhTien(hang.soLuong * hang.donGia);
        ct.setTrangThai(hang.trangThai == null ? "Mới" : hang.trangThai);
        ct.setMaHoaDon(null);
        if (keHoachService.addChiTiet(ct) == null) {
            return false;
        }
        hang.maChiTiet = ct.getMaChiTiet();
        hang.maDichVu = maDv;
        hang.maKeHoach = maKeHoach;
        baoDoi();
        return true;
    }

    public static boolean xoaDichVu(String maKeHoach, HangDT hang) {
        if (hang == null || hang.maChiTiet == null || hang.maChiTiet.isBlank()) {
            return false;
        }
        if (hang.maHoaDon != null && !hang.maHoaDon.isEmpty()) {
            return false;
        }
        keHoachService.deleteChiTiet(hang.maChiTiet);
        baoDoi();
        return true;
    }

    public static List<HangDT> hangChuaXuat(String maKH) {
        List<HangDT> ketQua = new ArrayList<>();
        for (KeHoachView kh : theoMaKH(maKH)) {
            if (kh.daHuy()) {
                continue;
            }
            for (HangDT hang : kh.chiTiet) {
                if (hang.maHoaDon == null || hang.maHoaDon.isEmpty()) {
                    ketQua.add(hang);
                }
            }
        }
        return ketQua;
    }

    public static void danhDauHoaDon(List<HangDT> ds, String maHd) {
        if (ds == null) {
            return;
        }
        for (HangDT hang : ds) {
            hang.maHoaDon = maHd;
            if (hang.maChiTiet != null) {
                ChiTietKeHoachDieuTri ct = keHoachService.findChiTietById(hang.maChiTiet);
                if (ct != null) {
                    ct.setMaHoaDon(maHd);
                    keHoachService.updateChiTiet(ct);
                }
            }
        }
        baoDoi();
    }

    public static void boDanhDauHoaDon(String maHd) {
        if (maHd == null) {
            return;
        }
        for (KeHoachDieuTri kh : keHoachService.findAllKeHoachDieuTri()) {
            for (ChiTietKeHoachDieuTri ct : keHoachService.findChiTietByMaKeHoach(kh.getMaKeHoach())) {
                if (maHd.equals(ct.getMaHoaDon())) {
                    ct.setMaHoaDon(null);
                    keHoachService.updateChiTiet(ct);
                }
            }
        }
        baoDoi();
    }

    private static KeHoachView mapView(KeHoachDieuTri e) {
        KeHoachView v = new KeHoachView();
        v.maKeHoach = e.getMaKeHoach();
        v.maKH = e.getMaKH();
        KhachHang kh = khachHangService.findById(e.getMaKH());
        v.tenKh = kh != null ? kh.getHoTen() : e.getMaKH();
        v.ngayLap = NgayUtil.fmt(e.getNgayKeHoach());
        v.maBacSi = e.getMaBacSi();
        NhanVien nv = e.getMaBacSi() == null ? null : danhMucService.findNhanVienById(e.getMaBacSi());
        v.tenBacSi = nv != null ? nv.getHoTen() : (e.getMaBacSi() == null ? "" : e.getMaBacSi());
        v.lyDoDieuTri = e.getLyDoDieuTri();
        v.ghiChu = e.getGhiChu();
        v.trangThai = e.getTrangThai();
        Map<String, String> tenDv = tenDichVu();
        Map<String, String> tenRang = tenRangMap();
        for (ChiTietKeHoachDieuTri ct : keHoachService.findChiTietByMaKeHoach(e.getMaKeHoach())) {
            HangDT h = new HangDT(
                    e.getMaKeHoach(),
                    NgayUtil.fmt(ct.getNgay()),
                    ct.getMaRang() == null ? "" : tenRang.getOrDefault(ct.getMaRang(), ct.getMaRang()),
                    tenDv.getOrDefault(ct.getMaDichVu(), ct.getMaDichVu()),
                    ct.getSoLuong(),
                    ct.getDonGia(),
                    ct.getTrangThai());
            h.maChiTiet = ct.getMaChiTiet();
            h.maDichVu = ct.getMaDichVu();
            h.maHoaDon = ct.getMaHoaDon();
            v.chiTiet.add(h);
        }
        return v;
    }

    private static String maChiTietMoi() {
        int max = 0;
        for (KeHoachDieuTri kh : keHoachService.findAllKeHoachDieuTri()) {
            for (ChiTietKeHoachDieuTri ct : keHoachService.findChiTietByMaKeHoach(kh.getMaKeHoach())) {
                String ma = ct.getMaChiTiet();
                if (ma != null && ma.startsWith("CTK")) {
                    try {
                        max = Math.max(max, Integer.parseInt(ma.substring(3)));
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        }
        return String.format("CTK%02d", max + 1);
    }

    private static String maDichVuTheoTen(String ten) {
        if (ten == null) {
            return null;
        }
        for (DichVu dv : danhMucService.findAllDichVu()) {
            if (ten.equals(dv.getTenDichVu())) {
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

    private static Map<String, String> tenRangMap() {
        Map<String, String> m = new HashMap<>();
        for (Rang r : danhMucService.findAllRang()) {
            m.put(r.getMaRang(), r.getTenRang() != null ? r.getTenRang() : r.getMaRang());
        }
        return m;
    }
}
