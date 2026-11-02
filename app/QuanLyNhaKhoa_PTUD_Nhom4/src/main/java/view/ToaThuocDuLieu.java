package view;

import entity.ChiTietToaThuoc;
import entity.KhachHang;
import entity.NhanVien;
import entity.Thuoc;
import entity.ToaThuoc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import service.impl.DanhMuc_Service;
import service.impl.KhachHang_Service;
import service.impl.ToaThuoc_Service;
import view.model.ChiTietToaThuocView;
import view.model.ToaThuocView;

public final class ToaThuocDuLieu {

    private static final List<Runnable> LANG_NGHE = new ArrayList<>();
    private static final ToaThuoc_Service toaThuocService = new ToaThuoc_Service();
    private static final KhachHang_Service khachHangService = new KhachHang_Service();
    private static final DanhMuc_Service danhMucService = new DanhMuc_Service();

    private ToaThuocDuLieu() {
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

    public static List<ToaThuocView> tatCa() {
        List<ToaThuocView> ketQua = new ArrayList<>();
        for (ToaThuoc e : toaThuocService.findAllToaThuoc()) {
            ketQua.add(mapView(e));
        }
        return ketQua;
    }

    public static ToaThuocView theoMa(String ma) {
        ToaThuoc e = toaThuocService.findById(ma);
        return e == null ? null : mapView(e);
    }

    public static List<ToaThuocView> theoKhach(String maKH) {
        List<ToaThuocView> ketQua = new ArrayList<>();
        for (ToaThuoc e : toaThuocService.findByMaKH(maKH)) {
            ketQua.add(mapView(e));
        }
        return ketQua;
    }

    public static ToaThuocView them(ToaThuocView tt) {
        if (tt == null) {
            return null;
        }
        if (tt.maToaThuoc == null || tt.maToaThuoc.isEmpty()) {
            tt.maToaThuoc = maMoi();
        }
        ToaThuoc e = new ToaThuoc();
        e.setMaToaThuoc(tt.maToaThuoc);
        e.setMaKH(tt.maKH);
        e.setMaChanDoan(tt.maChanDoan);
        e.setMaBacSi(tt.maBacSi);
        e.setHinhThucThanhToan(tt.hinhThucThanhToan);
        e.setNhanTaiPhongKham(tt.nhanTaiPhongKham);
        e.setGhiChu(tt.ghiChu);
        e.setThanhTien(tt.thanhTien);
        e.setNgayTao(LocalDateTime.now());

        List<ChiTietToaThuoc> cts = new ArrayList<>();
        int i = 1;
        for (ChiTietToaThuocView cv : tt.chiTiet) {
            ChiTietToaThuoc ct = new ChiTietToaThuoc();
            ct.setMaChiTiet(tt.maToaThuoc + "C" + i++);
            ct.setMaToaThuoc(tt.maToaThuoc);
            ct.setMaThuoc(cv.maThuoc);
            ct.setSoNgay(cv.soNgay);
            ct.setCachSuDung(cv.cachSuDung);
            ct.setTongSo(cv.tongSo);
            ct.setDonGia(cv.donGia);
            ct.setThanhTien(cv.thanhTien);
            cts.add(ct);
        }
        if (toaThuocService.themToaVoiChiTiet(e, cts) == null) {
            return null;
        }
        bao();
        return theoMa(tt.maToaThuoc);
    }

    public static void xoa(ToaThuocView tt) {
        if (tt == null) {
            return;
        }
        toaThuocService.delete(tt.maToaThuoc);
        bao();
    }

    private static ToaThuocView mapView(ToaThuoc e) {
        ToaThuocView v = new ToaThuocView();
        v.maToaThuoc = e.getMaToaThuoc();
        v.maKH = e.getMaKH();
        KhachHang kh = khachHangService.findById(e.getMaKH());
        v.tenKh = kh != null ? kh.getHoTen() : e.getMaKH();
        v.maChanDoan = e.getMaChanDoan();
        v.maBacSi = e.getMaBacSi();
        NhanVien nv = e.getMaBacSi() == null ? null : danhMucService.findNhanVienById(e.getMaBacSi());
        v.tenBacSi = nv != null ? nv.getHoTen() : "";
        v.hinhThucThanhToan = e.getHinhThucThanhToan();
        v.nhanTaiPhongKham = e.isNhanTaiPhongKham();
        v.ghiChu = e.getGhiChu();
        v.thanhTien = e.getThanhTien();
        v.ngayTao = NgayUtil.fmtFull(e.getNgayTao());
        for (ChiTietToaThuoc ct : toaThuocService.findChiTietByMaToaThuoc(e.getMaToaThuoc())) {
            Thuoc th = danhMucService.findThuocById(ct.getMaThuoc());
            v.chiTiet.add(new ChiTietToaThuocView(
                    ct.getMaThuoc(),
                    th != null ? th.getTenThuoc() : ct.getMaThuoc(),
                    ct.getSoNgay(),
                    ct.getCachSuDung(),
                    ct.getTongSo(),
                    ct.getDonGia(),
                    ct.getThanhTien()));
        }
        return v;
    }

    private static String maMoi() {
        int max = 0;
        for (ToaThuoc e : toaThuocService.findAllToaThuoc()) {
            String ma = e.getMaToaThuoc();
            if (ma != null && ma.startsWith("TT")) {
                try {
                    max = Math.max(max, Integer.parseInt(ma.substring(2)));
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return String.format("TT%02d", max + 1);
    }
}
