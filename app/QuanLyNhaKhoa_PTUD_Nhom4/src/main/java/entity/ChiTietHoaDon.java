package entity;

import java.time.LocalDate;

public class ChiTietHoaDon {

    private String maChiTiet;
    private HoaDon hoaDon;
    private LocalDate ngay;
    private Rang rang;
    private DichVu dichVu;
    private int soLuong;
    private long donGia;
    private long thanhTien;
    private String keHoach;

    public String getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(String maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public Rang getRang() {
        return rang;
    }

    public void setRang(Rang rang) {
        this.rang = rang;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
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
