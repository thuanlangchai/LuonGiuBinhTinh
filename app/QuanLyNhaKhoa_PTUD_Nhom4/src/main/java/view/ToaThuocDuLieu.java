package view;

import entity.ChiTietToaThuoc;
import entity.KhachHang;
import entity.NhanVien;
import entity.Refs;
import entity.Thuoc;
import entity.ToaThuoc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import service.impl.ToaThuoc_Service;
import view.model.ChiTietToaThuocView;
import view.model.ToaThuocView;

public final class ToaThuocDuLieu {

    private static final List<Runnable> LANG_NGHE = new ArrayList<>();
    private static final ToaThuoc_Service toaThuocService = new ToaThuoc_Service();

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
        for (ToaThuoc e : toaThuocService.findAllKemChiTiet()) {
            ketQua.add(mapView(e));
        }
        return ketQua;
    }

    public static ToaThuocView theoMa(String ma) {
        ToaThuoc e = toaThuocService.findDetailById(ma);
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
        KhachHang khach = Refs.khachHang(tt.maKH);
        if (khach != null) {
            khach.setHoTen(tt.tenKh);
        }
        e.setKhachHang(khach);
        e.setMaChanDoan(tt.maChanDoan);
        NhanVien bacSi = Refs.nhanVien(tt.maBacSi);
        if (bacSi != null) {
            bacSi.setHoTen(tt.tenBacSi);
        }
        e.setBacSi(bacSi);
        e.setHinhThucThanhToan(tt.hinhThucThanhToan);
        e.setNhanTaiPhongKham(tt.nhanTaiPhongKham);
        e.setGhiChu(tt.ghiChu);
        e.setNgayTao(LocalDateTime.now());

        List<ChiTietToaThuoc> cts = new ArrayList<>();
        int i = 1;
        for (ChiTietToaThuocView cv : tt.chiTiet) {
            ChiTietToaThuoc ct = new ChiTietToaThuoc();
            ct.setMaChiTiet(tt.maToaThuoc + "C" + i++);
            ct.setToaThuoc(Refs.toaThuoc(tt.maToaThuoc));
            Thuoc thuoc = Refs.thuoc(cv.maThuoc);
            if (thuoc != null) {
                thuoc.setTenThuoc(cv.tenThuoc);
            }
            ct.setThuoc(thuoc);
            ct.setSoNgay(cv.soNgay);
            ct.setCachSuDung(cv.cachSuDung);
            ct.setTongSo(cv.tongSo);
            ct.setDonGia(cv.donGia);
            cts.add(ct);
        }
        e.setChiTiet(cts);
        if (toaThuocService.themToaVoiChiTiet(e, cts) == null) {
            return null;
        }
        bao();
        return mapView(e);
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
        v.maKH = Refs.maKh(e.getKhachHang());
        v.tenKh = Refs.tenKh(e.getKhachHang());
        v.maChanDoan = e.getMaChanDoan();
        v.maBacSi = Refs.maNv(e.getBacSi());
        v.tenBacSi = e.getBacSi() == null ? "" : Refs.tenNv(e.getBacSi());
        v.hinhThucThanhToan = e.getHinhThucThanhToan();
        v.nhanTaiPhongKham = e.isNhanTaiPhongKham();
        v.ghiChu = e.getGhiChu();
        v.thanhTien = e.getThanhTien();
        v.ngayTao = NgayUtil.fmtFull(e.getNgayTao());
        List<ChiTietToaThuoc> chiTiet = e.getChiTiet() == null ? List.of() : e.getChiTiet();
        for (ChiTietToaThuoc ct : chiTiet) {
            v.chiTiet.add(new ChiTietToaThuocView(
                    Refs.maThuoc(ct.getThuoc()),
                    Refs.tenThuoc(ct.getThuoc()),
                    ct.getSoNgay(),
                    ct.getCachSuDung(),
                    ct.getTongSo(),
                    ct.getDonGia(),
                    ct.getThanhTien()));
        }
        return v;
    }

    private static String maMoi() {
        return String.format("TT%02d", toaThuocService.nextSo() + 1);
    }
}
