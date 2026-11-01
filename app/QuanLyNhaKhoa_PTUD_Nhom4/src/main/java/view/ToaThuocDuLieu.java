package view;

import java.util.ArrayList;
import java.util.List;
import view.model.ChiTietToaThuocView;
import view.model.ToaThuocView;

public final class ToaThuocDuLieu {

    private static final List<ToaThuocView> DS = new ArrayList<>();
    private static final List<Runnable> LANG_NGHE = new ArrayList<>();
    private static int dem = 2;

    static {
        ToaThuocView tt = new ToaThuocView();
        tt.maToaThuoc = "TT01";
        tt.maKH = "0001";
        tt.tenKh = "Lê Trần Quang Thanh";
        tt.maChanDoan = "CD01";
        tt.maBacSi = "NV03";
        tt.tenBacSi = "Lê Trần Quang Thanh";
        tt.hinhThucThanhToan = "Tiền mặt";
        tt.nhanTaiPhongKham = true;
        tt.ghiChu = "";
        tt.thanhTien = 108000;
        tt.ngayTao = "28 Sep 2026 09:34:12 PM";
        tt.chiTiet.add(new ChiTietToaThuocView("0001", "aptx 4869", 1,
                "Sáng 1 - Trưa 1 - Chiều 1", 3, 36000, 108000));
        DS.add(tt);
    }

    private ToaThuocDuLieu() {
    }

    public static void khiDoi(Runnable r) {
        if (r != null) {
            LANG_NGHE.add(r);
        }
    }

    public static List<ToaThuocView> tatCa() {
        return DS;
    }

    public static ToaThuocView theoMa(String ma) {
        for (ToaThuocView tt : DS) {
            if (tt.maToaThuoc.equals(ma)) {
                return tt;
            }
        }
        return null;
    }

    public static List<ToaThuocView> theoKhach(String maKH) {
        List<ToaThuocView> kq = new ArrayList<>();
        for (ToaThuocView tt : DS) {
            if (tt.maKH.equals(maKH)) {
                kq.add(tt);
            }
        }
        return kq;
    }

    public static ToaThuocView them(ToaThuocView tt) {
        if (tt.maToaThuoc == null || tt.maToaThuoc.isEmpty()) {
            tt.maToaThuoc = String.format("TT%02d", dem++);
        }
        if (tt.ngayTao == null || tt.ngayTao.isEmpty()) {
            tt.ngayTao = ViewTheme.bayGio();
        }
        DS.add(tt);
        bao();
        return tt;
    }

    public static void xoa(ToaThuocView tt) {
        DS.remove(tt);
        bao();
    }

    private static void bao() {
        for (Runnable r : LANG_NGHE) {
            r.run();
        }
    }
}
