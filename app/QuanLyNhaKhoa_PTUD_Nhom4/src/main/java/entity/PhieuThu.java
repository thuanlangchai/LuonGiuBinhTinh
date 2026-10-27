package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PhieuThu {

    private String maPhieuThu; 
    private LocalDate ngayThu;
    private String maKH;
    private String maHoaDon;
    private String lyDo;
    private long soTien;
    private String hinhThucThanhToan;
    private String trangThai;
    private String maBacSi;
    private String maPhuTa;
    private String nguoiGioiThieu;
    private LocalDateTime ngayTao;
    private String nguoiTao;

    public PhieuThu() {
    }

    public PhieuThu(String maPhieuThu, LocalDate ngayThu, String maKH, String maHoaDon, String lyDo, long soTien, String hinhThucThanhToan, String trangThai, String maBacSi, String maPhuTa, String nguoiGioiThieu, LocalDateTime ngayTao, String nguoiTao) {
        this.maPhieuThu = maPhieuThu;
        this.ngayThu = ngayThu;
        this.maKH = maKH;
        this.maHoaDon = maHoaDon;
        this.lyDo = lyDo;
        this.soTien = soTien;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.trangThai = trangThai;
        this.maBacSi = maBacSi;
        this.maPhuTa = maPhuTa;
        this.nguoiGioiThieu = nguoiGioiThieu;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
    }

    public String getMaPhieuThu() {
        return maPhieuThu;
    }

    public void setMaPhieuThu(String maPhieuThu) {
        this.maPhieuThu = maPhieuThu;
    }

    public LocalDate getNgayThu() {
        return ngayThu;
    }

    public void setNgayThu(LocalDate ngayThu) {
        this.ngayThu = ngayThu;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public long getSoTien() {
        return soTien;
    }

    public void setSoTien(long soTien) {
        this.soTien = soTien;
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

    public String getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(String maBacSi) {
        this.maBacSi = maBacSi;
    }

    public String getMaPhuTa() {
        return maPhuTa;
    }

    public void setMaPhuTa(String maPhuTa) {
        this.maPhuTa = maPhuTa;
    }

    public String getNguoiGioiThieu() {
        return nguoiGioiThieu;
    }

    public void setNguoiGioiThieu(String nguoiGioiThieu) {
        this.nguoiGioiThieu = nguoiGioiThieu;
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
