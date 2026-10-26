package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class KhachHang {

    private String maKH;
    private String hoTen;
    private String gioiTinh;
    private LocalDate ngaySinh;
//    private Integer namSinh;
    private String soDienThoai;
    private String email;
    private String diaChi;
    private String cccd;
    private String ghiChu;
//    private String danhGia;
    private String maNhom;
    private String maChiNhanh;
    private String lyDoKham;
    private String tienSuBenh;
    private String diUng;
    private String maBacSi;
    private String maTuVan;
    private int soLanKham;
    private LocalDate lanKhamGanNhat;
    private LocalDateTime ngayTao;
    

    public KhachHang() {
    }
    
    


	public KhachHang(String maKH, String hoTen, String gioiTinh, LocalDate ngaySinh, String soDienThoai, String email,
			String diaChi, String cccd, String ghiChu, String maNhom, String maChiNhanh, String lyDoKham,
			String tienSuBenh, String diUng, String maBacSi, String maTuVan, int soLanKham, LocalDate lanKhamGanNhat,
			LocalDateTime ngayTao) {
		super();
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.diaChi = diaChi;
		this.cccd = cccd;
		this.ghiChu = ghiChu;
		this.maNhom = maNhom;
		this.maChiNhanh = maChiNhanh;
		this.lyDoKham = lyDoKham;
		this.tienSuBenh = tienSuBenh;
		this.diUng = diUng;
		this.maBacSi = maBacSi;
		this.maTuVan = maTuVan;
		this.soLanKham = soLanKham;
		this.lanKhamGanNhat = lanKhamGanNhat;
		this.ngayTao = ngayTao;
	}


	/*
    public KhachHang(String maKH, String hoTen, String gioiTinh, LocalDate ngaySinh, Integer namSinh, String soDienThoai, String email, String diaChi, String cccd, String ghiChu, String danhGia, String maNhom, String maChiNhanh, String lyDoKham, String tienSuBenh, String diUng, String maBacSi, String maTuVan, int soLanKham, LocalDate lanKhamGanNhat, LocalDateTime ngayTao) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.namSinh = namSinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.cccd = cccd;
        this.ghiChu = ghiChu;
        this.danhGia = danhGia;
        this.maNhom = maNhom;
        this.maChiNhanh = maChiNhanh;
        this.lyDoKham = lyDoKham;
        this.tienSuBenh = tienSuBenh;
        this.diUng = diUng;
        this.maBacSi = maBacSi;
        this.maTuVan = maTuVan;
        this.soLanKham = soLanKham;
        this.lanKhamGanNhat = lanKhamGanNhat;
        this.ngayTao = ngayTao;
    }
*/
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

//    public Integer getNamSinh() {
//        return namSinh;
//    }
//
//    public void setNamSinh(Integer namSinh) {
//        this.namSinh = namSinh;
//    }

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

//    public String getDanhGia() {
//        return danhGia;
//    }
//
//    public void setDanhGia(String danhGia) {
//        this.danhGia = danhGia;
//    }

    public String getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(String maNhom) {
        this.maNhom = maNhom;
    }

    public String getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(String maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
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

    public String getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(String maBacSi) {
        this.maBacSi = maBacSi;
    }

    public String getMaTuVan() {
        return maTuVan;
    }

    public void setMaTuVan(String maTuVan) {
        this.maTuVan = maTuVan;
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
    


