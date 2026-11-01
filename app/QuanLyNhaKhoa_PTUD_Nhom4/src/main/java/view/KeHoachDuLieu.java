package view;

import java.util.ArrayList;
import java.util.List;
import view.model.HangDT;
import view.model.KeHoachView;

public final class KeHoachDuLieu {

    private static final List<KeHoachView> DS = new ArrayList<>();
    private static final List<Runnable> LANG_NGHE = new ArrayList<>();
    private static int dem = 2;

    static {
        KeHoachView kh = new KeHoachView();
        kh.maKeHoach = "KH01";
        kh.maKH = "0001";
        kh.tenKh = "Lê Trần Quang Thanh";
        kh.ngayLap = "27/09/2026";
        kh.maBacSi = "NV03";
        kh.tenBacSi = "Lê Trần Quang Thanh";
        kh.lyDoDieuTri = "Đau răng";
        kh.ghiChu = "Kế hoạch điều trị ngày 27/09/2026";
        kh.trangThai = "Hoàn tất";
        HangDT k1 = new HangDT("KH01", "27/09/2026", "", "Thăm khám - Tư vấn", 1, 200_000, "Hoàn tất");
        HangDT k2 = new HangDT("KH01", "27/09/2026", "", "Cạo vôi - Đánh bóng", 1, 700_000, "Hoàn tất");
        k1.maHoaDon = "0001";
        k2.maHoaDon = "0001";
        kh.chiTiet.add(k1);
        kh.chiTiet.add(k2);
        DS.add(kh);
    }

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
        return DS;
    }

    public static KeHoachView theoMa(String maKeHoach) {
        if (maKeHoach == null) {
            return null;
        }
        for (KeHoachView kh : DS) {
            if (maKeHoach.equals(kh.maKeHoach)) {
                return kh;
            }
        }
        return null;
    }

    public static List<KeHoachView> theoMaKH(String maKH) {
        List<KeHoachView> kq = new ArrayList<>();
        if (maKH == null || maKH.isEmpty()) {
            return kq;
        }
        for (KeHoachView kh : DS) {
            if (maKH.equals(kh.maKH)) {
                kq.add(kh);
            }
        }
        return kq;
    }

    public static String maMoi() {
        return String.format("KH%02d", dem++);
    }

    public static KeHoachView them(String maKH, String tenKh, String ngayLap, String maBacSi,
            String tenBacSi, String lyDo, String ghiChu) {
        KeHoachView kh = new KeHoachView();
        kh.maKeHoach = maMoi();
        kh.maKH = maKH;
        kh.tenKh = tenKh == null ? "" : tenKh;
        kh.ngayLap = ngayLap;
        kh.maBacSi = maBacSi;
        kh.tenBacSi = tenBacSi;
        kh.lyDoDieuTri = lyDo == null ? "" : lyDo;
        kh.ghiChu = ghiChu == null ? "" : ghiChu;
        kh.trangThai = "Mới";
        DS.add(0, kh);
        baoDoi();
        return kh;
    }

    public static void capNhat(KeHoachView kh) {
        baoDoi();
    }

    public static boolean huy(String maKeHoach) {
        KeHoachView kh = theoMa(maKeHoach);
        if (kh == null) {
            return false;
        }
        if (kh.daHuy()) {
            return false;
        }
        if (kh.coHoaDon()) {
            return false;
        }
        kh.trangThai = "Đã hủy";
        baoDoi();
        return true;
    }

    public static boolean themDichVu(String maKeHoach, HangDT hang) {
        KeHoachView kh = theoMa(maKeHoach);
        if (kh == null || kh.daHuy() || hang == null) {
            return false;
        }
        hang.maKeHoach = maKeHoach;
        kh.chiTiet.add(hang);
        if ("Mới".equals(kh.trangThai)) {
            kh.trangThai = "Đang điều trị";
        }
        baoDoi();
        return true;
    }

    public static boolean xoaDichVu(String maKeHoach, HangDT hang) {
        KeHoachView kh = theoMa(maKeHoach);
        if (kh == null || hang == null) {
            return false;
        }
        if (hang.maHoaDon != null && !hang.maHoaDon.isEmpty()) {
            return false;
        }
        boolean ok = kh.chiTiet.remove(hang);
        if (ok) {
            baoDoi();
        }
        return ok;
    }

    public static List<HangDT> hangChuaXuat(String maKH) {
        List<HangDT> kq = new ArrayList<>();
        for (KeHoachView kh : theoMaKH(maKH)) {
            if (kh.daHuy()) {
                continue;
            }
            for (HangDT hang : kh.chiTiet) {
                if (hang.maHoaDon == null || hang.maHoaDon.isEmpty()) {
                    kq.add(hang);
                }
            }
        }
        return kq;
    }

    public static void danhDauHoaDon(List<HangDT> ds, String maHd) {
        if (ds == null) {
            return;
        }
        for (HangDT hang : ds) {
            hang.maHoaDon = maHd;
        }
        baoDoi();
    }

    public static void boDanhDauHoaDon(String maHd) {
        if (maHd == null) {
            return;
        }
        for (KeHoachView kh : DS) {
            for (HangDT hang : kh.chiTiet) {
                if (maHd.equals(hang.maHoaDon)) {
                    hang.maHoaDon = null;
                }
            }
        }
        baoDoi();
    }
}
