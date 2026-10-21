package entity;

import java.time.LocalTime;

public class ThoiGianLamViec {

    private String maLich;
    private String ngay;
    private LocalTime gioBatDau;
    private LocalTime gioKetThuc;
    private String maBacSi;
    private String maChiNhanh;

    public ThoiGianLamViec() {
    }

    public ThoiGianLamViec(String maLich, String ngay, LocalTime gioBatDau, LocalTime gioKetThuc, String maBacSi, String maChiNhanh) {
        this.maLich = maLich;
        this.ngay = ngay;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.maBacSi = maBacSi;
        this.maChiNhanh = maChiNhanh;
    }

    public String getMaLich() {
        return maLich;
    }

    public void setMaLich(String maLich) {
        this.maLich = maLich;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public LocalTime getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(LocalTime gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public LocalTime getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(LocalTime gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public String getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(String maBacSi) {
        this.maBacSi = maBacSi;
    }

    public String getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(String maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }
}
