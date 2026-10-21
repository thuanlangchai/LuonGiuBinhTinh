package entity;

import java.time.LocalDate;

public class ChiTietHoaDon {

    private String maChiTiet;
    private String maHoaDon;
    private LocalDate ngay;
    private String maRang;
    private String maDichVu;
    private int soLuong;
    private long donGia;
    private long thanhTien;
    private String keHoach;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String maChiTiet, String maHoaDon, LocalDate ngay, String maRang, String maDichVu, int soLuong, long donGia, long thanhTien, String keHoach) {
        this.maChiTiet = maChiTiet;
        this.maHoaDon = maHoaDon;
        this.ngay = ngay;
        this.maRang = maRang;
        this.maDichVu = maDichVu;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.keHoach = keHoach;
    }

    public String getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(String maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public String getMaRang() {
        return maRang;
    }

    public void setMaRang(String maRang) {
        this.maRang = maRang;
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getKeHoach() {
        return keHoach;
    }

    public void setKeHoach(String keHoach) {
        this.keHoach = keHoach;
    }
}
