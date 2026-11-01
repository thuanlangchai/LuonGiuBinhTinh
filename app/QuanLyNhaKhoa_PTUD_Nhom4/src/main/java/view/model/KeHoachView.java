package view.model;

import java.util.ArrayList;
import java.util.List;

public class KeHoachView {

    public String maKeHoach;
    public String maKH;
    public String tenKh;
    public String ngayLap;
    public String maBacSi;
    public String tenBacSi;
    public String lyDoDieuTri;
    public String ghiChu;
    public String trangThai;
    public final List<HangDT> chiTiet = new ArrayList<>();

    public long tongTien() {
        long t = 0;
        for (HangDT h : chiTiet) {
            t += h.thanhTien();
        }
        return t;
    }

    public boolean daHuy() {
        return "Đã hủy".equalsIgnoreCase(trangThai) || "Hủy".equalsIgnoreCase(trangThai);
    }

    public boolean coHoaDon() {
        for (HangDT h : chiTiet) {
            if (h.maHoaDon != null && !h.maHoaDon.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
