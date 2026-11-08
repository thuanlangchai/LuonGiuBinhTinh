package entity;

import java.time.LocalTime;

public class ThoiGianLamViec {

    private String maLich;
    private String ngay;
    private LocalTime gioBatDau;
    private LocalTime gioKetThuc;
    private NhanVien bacSi;
    private ChiNhanh chiNhanh;

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

    public NhanVien getBacSi() {
        return bacSi;
    }

    public void setBacSi(NhanVien bacSi) {
        this.bacSi = bacSi;
    }

    public ChiNhanh getChiNhanh() {
        return chiNhanh;
    }

    public void setChiNhanh(ChiNhanh chiNhanh) {
        this.chiNhanh = chiNhanh;
    }
}
