package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import view.model.ChiTietHoaDonView;
import view.model.HoaDonView;

public final class HoaDonDuLieu {

    private static final DateTimeFormatter NGAY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final List<HoaDonView> DS = new ArrayList<>();
    private static final List<Runnable> LANG_NGHE = new ArrayList<>();
    private static int dem = 2;

    static {
        HoaDonView hd = new HoaDonView();
        hd.ma = "0001";
        hd.ngayLap = "27/09/2026";
        hd.maKh = "0001";
        hd.tenKh = "Lê Trần Quang Thanh";
        hd.keHoach = "Kế hoạch điều trị ngày 27/09/2026";
        hd.bacSi = "Lê Trần Quang Thanh";
        hd.ghiChu = "Xuất từ kế hoạch điều trị";
        hd.chietKhau = 0;
        hd.chiTiet.add(new ChiTietHoaDonView("27/09/2026", "", "Thăm khám - Tư vấn", 1, 200_000, "Kế hoạch điều trị ngày 27/09/2026"));
        hd.chiTiet.add(new ChiTietHoaDonView("27/09/2026", "", "Cạo vôi - Đánh bóng", 1, 700_000, "Kế hoạch điều trị ngày 27/09/2026"));
        hd.tinhTien();
        hd.ngayTao = "27 Sep 2026 10:22:19 PM";
        DS.add(hd);
    }

    private HoaDonDuLieu() {
    }

    public static void khiDoi(Runnable r) {
        if (r != null) {
            LANG_NGHE.add(r);
        }
    }

    public static List<HoaDonView> tatCa() {
        return DS;
    }

    public static HoaDonView theoMa(String ma) {
        for (HoaDonView hd : DS) {
            if (hd.ma.equals(ma)) {
                return hd;
            }
        }
        return null;
    }

    public static List<HoaDonView> theoKhach(String maKh) {
        List<HoaDonView> kq = new ArrayList<>();
        for (HoaDonView hd : DS) {
            if (hd.maKh.equals(maKh)) {
                kq.add(hd);
            }
        }
        return kq;
    }

    public static List<HoaDonView> conNoTheoKhach(String maKh) {
        List<HoaDonView> kq = new ArrayList<>();
        for (HoaDonView hd : theoKhach(maKh)) {
            if (hd.conNo() > 0) {
                kq.add(hd);
            }
        }
        return kq;
    }

    public static HoaDonView them(String ngayLap, String maKh, String tenKh, String keHoach, String bacSi,
            long chietKhau, String ghiChu, List<ChiTietHoaDonView> chiTiet) {
        HoaDonView hd = new HoaDonView();
        hd.ma = String.format("%04d", dem++);
        hd.ngayLap = ngayLap;
        hd.maKh = maKh;
        hd.tenKh = tenKh;
        hd.keHoach = keHoach;
        hd.bacSi = bacSi;
        hd.ghiChu = ghiChu;
        hd.chietKhau = Math.max(0, chietKhau);
        hd.chiTiet.addAll(chiTiet);
        hd.tinhTien();
        hd.ngayTao = ViewTheme.bayGio();
        DS.add(hd);
        bao();
        return hd;
    }

    public static void thuTien(HoaDonView hd, long soTien) {
        if (hd == null || soTien <= 0) {
            return;
        }
        hd.daThu += soTien;
        if (hd.daThu > hd.thanhTien) {
            hd.daThu = hd.thanhTien;
        }
        hd.capNhatTrangThai();
        bao();
    }

    public static void xoa(HoaDonView hd) {
        DS.remove(hd);
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
        Map<String, long[]> gop = new LinkedHashMap<String, long[]>();
        Map<String, String> ten = new LinkedHashMap<String, String>();
        for (HoaDonView hd : DS) {
            long[] so = gop.get(hd.maKh);
            if (so == null) {
                so = new long[2];
                gop.put(hd.maKh, so);
            }
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
        return LocalDate.now().format(NGAY);
    }

    private static void bao() {
        for (Runnable r : LANG_NGHE) {
            r.run();
        }
    }


}
