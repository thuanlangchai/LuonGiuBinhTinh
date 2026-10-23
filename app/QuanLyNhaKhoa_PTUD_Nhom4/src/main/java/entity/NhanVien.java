package entity;

public class NhanVien {

    private String maNhanVien;
    private String chucDanh;
    private String hoTen;
    private String vietTat;
    private double hoaHongChinh;
    private double hoaHongPhu;
    private double hoaHongTuVan;
    private double hoaHongSale;
    private String email;
    private String soDienThoai;
    private String vaiTro;
    private boolean kichHoat;
    private String maChiNhanh;

    public NhanVien() {
    }

    public NhanVien(String maNhanVien, String chucDanh, String hoTen, String vietTat, double hoaHongChinh, double hoaHongPhu, double hoaHongTuVan, double hoaHongSale, String email, String soDienThoai, String vaiTro, boolean kichHoat, String maChiNhanh) {
        this.maNhanVien = maNhanVien;
        this.chucDanh = chucDanh;
        this.hoTen = hoTen;
        this.vietTat = vietTat;
        this.hoaHongChinh = hoaHongChinh;
        this.hoaHongPhu = hoaHongPhu;
        this.hoaHongTuVan = hoaHongTuVan;
        this.hoaHongSale = hoaHongSale;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.vaiTro = vaiTro;
        this.kichHoat = kichHoat;
        this.maChiNhanh = maChiNhanh;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getChucDanh() {
        return chucDanh;
    }

    public void setChucDanh(String chucDanh) {
        this.chucDanh = chucDanh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getVietTat() {
        return vietTat;
    }

    public void setVietTat(String vietTat) {
        this.vietTat = vietTat;
    }

    public double getHoaHongChinh() {
        return hoaHongChinh;
    }

    public void setHoaHongChinh(double hoaHongChinh) {
        this.hoaHongChinh = hoaHongChinh;
    }

    public double getHoaHongPhu() {
        return hoaHongPhu;
    }

    public void setHoaHongPhu(double hoaHongPhu) {
        this.hoaHongPhu = hoaHongPhu;
    }

    public double getHoaHongTuVan() {
        return hoaHongTuVan;
    }

    public void setHoaHongTuVan(double hoaHongTuVan) {
        this.hoaHongTuVan = hoaHongTuVan;
    }

    public double getHoaHongSale() {
        return hoaHongSale;
    }

    public void setHoaHongSale(double hoaHongSale) {
        this.hoaHongSale = hoaHongSale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public boolean isKichHoat() {
        return kichHoat;
    }

    public void setKichHoat(boolean kichHoat) {
        this.kichHoat = kichHoat;
    }

    public String getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(String maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }
}
