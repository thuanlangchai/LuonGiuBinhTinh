package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PhieuThu {

    private String maPhieuThu;
    private LocalDate ngayThu;
    private KhachHang khachHang;
    private HoaDon hoaDon;
    private String lyDo;
    private long soTien;
    private String hinhThucThanhToan;
    private String trangThai;
    private NhanVien bacSi;
    private NhanVien phuTa;
    private String nguoiGioiThieu;
    private LocalDateTime ngayTao;
    private NhanVien nguoiTao;

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

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
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

    public NhanVien getBacSi() {
        return bacSi;
    }

    public void setBacSi(NhanVien bacSi) {
        this.bacSi = bacSi;
    }

    public NhanVien getPhuTa() {
        return phuTa;
    }

    public void setPhuTa(NhanVien phuTa) {
        this.phuTa = phuTa;
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

    public NhanVien getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(NhanVien nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

}
