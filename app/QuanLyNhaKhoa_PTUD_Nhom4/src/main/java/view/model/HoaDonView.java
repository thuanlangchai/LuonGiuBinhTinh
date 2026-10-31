package view.model;

import java.util.ArrayList;
import java.util.List;
import view.HoaDonDuLieu;
import view.ViewTheme;

public class HoaDonView {
    public String ma;
    public String ngayLap;
    public String maKh;
    public String tenKh;
    public String keHoach;
    public String bacSi;
    public String ghiChu;
    public String trangThai = "Chưa thanh toán";
    public String ngayTao;
    public long tong;
    public long chietKhau;
    public long thanhTien;
    public long daThu;
    public final List<ChiTietHoaDonView> chiTiet = new ArrayList<>();

    public long conNo() {
        return Math.max(0, thanhTien - daThu);
    }

    public void tinhTien() {
        tong = 0;
        for (ChiTietHoaDonView ct : chiTiet) {
            tong += ct.thanhTien();
        }
        if (chietKhau > tong) {
            chietKhau = tong;
        }
        thanhTien = tong - chietKhau;
        capNhatTrangThai();
    }

    public void capNhatTrangThai() {
        if (daThu <= 0) {
            trangThai = "Chưa thanh toán";
        } else if (daThu < thanhTien) {
            trangThai = "Thanh toán một phần";
        } else {
            trangThai = "Đã thanh toán";
        }
    }

    public String nhan() {
        return ma + " - " + tenKh + " (" + ViewTheme.tien(conNo()) + ")";
    }
}
