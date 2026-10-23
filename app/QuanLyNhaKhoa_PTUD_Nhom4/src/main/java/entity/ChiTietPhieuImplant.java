package entity;

public class ChiTietPhieuImplant {

    private String maChiTiet;
    private String maPhieuImplant;
    private String vatTu;
    private String nhaSanXuat;
    private String baoHanh;
    private int soLuong;

    public ChiTietPhieuImplant() {
    }

    public ChiTietPhieuImplant(String maChiTiet, String maPhieuImplant, String vatTu, String nhaSanXuat, String baoHanh, int soLuong) {
        this.maChiTiet = maChiTiet;
        this.maPhieuImplant = maPhieuImplant;
        this.vatTu = vatTu;
        this.nhaSanXuat = nhaSanXuat;
        this.baoHanh = baoHanh;
        this.soLuong = soLuong;
    }

    public String getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(String maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public String getMaPhieuImplant() {
        return maPhieuImplant;
    }

    public void setMaPhieuImplant(String maPhieuImplant) {
        this.maPhieuImplant = maPhieuImplant;
    }

    public String getVatTu() {
        return vatTu;
    }

    public void setVatTu(String vatTu) {
        this.vatTu = vatTu;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public String getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(String baoHanh) {
        this.baoHanh = baoHanh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
