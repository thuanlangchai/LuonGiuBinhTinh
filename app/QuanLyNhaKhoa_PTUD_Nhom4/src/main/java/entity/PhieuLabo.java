package entity;

import java.time.LocalDate;

public class PhieuLabo {

    private String maPhieuLabo;
    private String maNhaCungCap;
    private String maBacSi;
    private String maKH;
    private String chiTiet;
    private String tinhTrang;
    private LocalDate ngayGui;
    private LocalDate ngayNhan;
    private LocalDate ngayHoanThanh;

    public PhieuLabo() {
    }

    public PhieuLabo(String maPhieuLabo, String maNhaCungCap, String maBacSi, String maKH, String chiTiet, String tinhTrang, LocalDate ngayGui, LocalDate ngayNhan, LocalDate ngayHoanThanh) {
        this.maPhieuLabo = maPhieuLabo;
        this.maNhaCungCap = maNhaCungCap;
        this.maBacSi = maBacSi;
        this.maKH = maKH;
        this.chiTiet = chiTiet;
        this.tinhTrang = tinhTrang;
        this.ngayGui = ngayGui;
        this.ngayNhan = ngayNhan;
        this.ngayHoanThanh = ngayHoanThanh;
    }

    public String getMaPhieuLabo() {
        return maPhieuLabo;
    }

    public void setMaPhieuLabo(String maPhieuLabo) {
        this.maPhieuLabo = maPhieuLabo;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(String maBacSi) {
        this.maBacSi = maBacSi;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public LocalDate getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(LocalDate ngayGui) {
        this.ngayGui = ngayGui;
    }

    public LocalDate getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(LocalDate ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public LocalDate getNgayHoanThanh() {
        return ngayHoanThanh;
    }

    public void setNgayHoanThanh(LocalDate ngayHoanThanh) {
        this.ngayHoanThanh = ngayHoanThanh;
    }
}
