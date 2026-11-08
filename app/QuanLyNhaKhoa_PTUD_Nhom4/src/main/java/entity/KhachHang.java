package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class KhachHang {

    private String maKH;
    private String hoTen;
    private String gioiTinh;
    private LocalDate ngaySinh;
    private String soDienThoai;
    private String email;
    private String diaChi;
    private String cccd;
    private String ghiChu;
    private NhomKhachHang nhomKhachHang;
    private ChiNhanh chiNhanh;
    private String lyDoKham;
    private String tienSuBenh;
    private String diUng;
    private NhanVien bacSi;
    private NhanVien tuVan;
    private int soLanKham;
    private LocalDate lanKhamGanNhat;
    private LocalDateTime ngayTao;

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public NhomKhachHang getNhomKhachHang() {
        return nhomKhachHang;
    }

    public void setNhomKhachHang(NhomKhachHang nhomKhachHang) {
        this.nhomKhachHang = nhomKhachHang;
    }

    public ChiNhanh getChiNhanh() {
        return chiNhanh;
    }

    public void setChiNhanh(ChiNhanh chiNhanh) {
        this.chiNhanh = chiNhanh;
    }

    public String getLyDoKham() {
        return lyDoKham;
    }

    public void setLyDoKham(String lyDoKham) {
        this.lyDoKham = lyDoKham;
    }

    public String getTienSuBenh() {
        return tienSuBenh;
    }

    public void setTienSuBenh(String tienSuBenh) {
        this.tienSuBenh = tienSuBenh;
    }

    public String getDiUng() {
        return diUng;
    }

    public void setDiUng(String diUng) {
        this.diUng = diUng;
    }

    public NhanVien getBacSi() {
        return bacSi;
    }

    public void setBacSi(NhanVien bacSi) {
        this.bacSi = bacSi;
    }

    public NhanVien getTuVan() {
        return tuVan;
    }

    public void setTuVan(NhanVien tuVan) {
        this.tuVan = tuVan;
    }

    public int getSoLanKham() {
        return soLanKham;
    }

    public void setSoLanKham(int soLanKham) {
        this.soLanKham = soLanKham;
    }

    public LocalDate getLanKhamGanNhat() {
        return lanKhamGanNhat;
    }

    public void setLanKhamGanNhat(LocalDate lanKhamGanNhat) {
        this.lanKhamGanNhat = lanKhamGanNhat;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}
