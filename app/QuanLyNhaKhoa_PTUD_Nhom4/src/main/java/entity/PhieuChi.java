package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PhieuChi {

    private String maPhieuChi;
    private String maNhaCungCap;
    private String loaiChiTieu;
    private String lyDoChi;
    private long soTien;
    private LocalDate ngayChi;
    private String hinhThucThanhToan;
    private String trangThai;
    private LocalDateTime ngayTao;
    private String nguoiTao;

    public PhieuChi() {
    }

    public PhieuChi(String maPhieuChi, String maNhaCungCap, String loaiChiTieu, String lyDoChi, long soTien, LocalDate ngayChi, String hinhThucThanhToan, String trangThai, LocalDateTime ngayTao, String nguoiTao) {
        this.maPhieuChi = maPhieuChi;
        this.maNhaCungCap = maNhaCungCap;
        this.loaiChiTieu = loaiChiTieu;
        this.lyDoChi = lyDoChi;
        this.soTien = soTien;
        this.ngayChi = ngayChi;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
    }

    public String getMaPhieuChi() {
        return maPhieuChi;
    }

    public void setMaPhieuChi(String maPhieuChi) {
        this.maPhieuChi = maPhieuChi;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getLoaiChiTieu() {
        return loaiChiTieu;
    }

    public void setLoaiChiTieu(String loaiChiTieu) {
        this.loaiChiTieu = loaiChiTieu;
    }

    public String getLyDoChi() {
        return lyDoChi;
    }

    public void setLyDoChi(String lyDoChi) {
        this.lyDoChi = lyDoChi;
    }

    public long getSoTien() {
        return soTien;
    }

    public void setSoTien(long soTien) {
        this.soTien = soTien;
    }

    public LocalDate getNgayChi() {
        return ngayChi;
    }

    public void setNgayChi(LocalDate ngayChi) {
        this.ngayChi = ngayChi;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }
}
