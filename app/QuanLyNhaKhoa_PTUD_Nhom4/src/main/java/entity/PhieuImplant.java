package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PhieuImplant {

    private String maPhieuImplant;
    private LocalDate ngayGui;
    private String maKH;
    private String maBacSi;
    private String tinhTrang;
    private LocalDateTime ngayNhan;
    private LocalDateTime ngayHoanThanh;
    private String maNhaCungCap;

    public PhieuImplant() {
    }

    public PhieuImplant(String maPhieuImplant, LocalDate ngayGui, String maKH, String maBacSi, String tinhTrang, LocalDateTime ngayNhan, LocalDateTime ngayHoanThanh, String maNhaCungCap) {
        this.maPhieuImplant = maPhieuImplant;
        this.ngayGui = ngayGui;
        this.maKH = maKH;
        this.maBacSi = maBacSi;
        this.tinhTrang = tinhTrang;
        this.ngayNhan = ngayNhan;
        this.ngayHoanThanh = ngayHoanThanh;
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getMaPhieuImplant() {
        return maPhieuImplant;
    }

    public void setMaPhieuImplant(String maPhieuImplant) {
        this.maPhieuImplant = maPhieuImplant;
    }

    public LocalDate getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(LocalDate ngayGui) {
        this.ngayGui = ngayGui;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(String maBacSi) {
        this.maBacSi = maBacSi;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public LocalDateTime getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(LocalDateTime ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public LocalDateTime getNgayHoanThanh() {
        return ngayHoanThanh;
    }

    public void setNgayHoanThanh(LocalDateTime ngayHoanThanh) {
        this.ngayHoanThanh = ngayHoanThanh;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }
}
