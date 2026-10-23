package entity;

import java.time.LocalDate;

public class ChiTietKeHoachDieuTri {

    private String maChiTiet;
    private String maKeHoach;
    private LocalDate ngay;
    private String maRang;
    private String maDichVu;
    private int soLuong;
    private long donGia;
    private long thanhTien;
    private String trangThai;
    private String maHoaDon;

    public ChiTietKeHoachDieuTri() {
    }

    public ChiTietKeHoachDieuTri(String maChiTiet, String maKeHoach, LocalDate ngay, String maRang, String maDichVu, int soLuong, long donGia, long thanhTien, String trangThai, String maHoaDon) {
        this.maChiTiet = maChiTiet;
        this.maKeHoach = maKeHoach;
        this.ngay = ngay;
        this.maRang = maRang;
        this.maDichVu = maDichVu;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
        this.maHoaDon = maHoaDon;
    }

    public String getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(String maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public String getMaKeHoach() {
        return maKeHoach;
    }

    public void setMaKeHoach(String maKeHoach) {
        this.maKeHoach = maKeHoach;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
}
