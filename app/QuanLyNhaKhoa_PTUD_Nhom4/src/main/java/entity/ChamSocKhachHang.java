package entity;

import java.time.LocalDate;

public class ChamSocKhachHang {

    private String maCSKH;
    private String maKH;
    private String noiDung;
    private String kenh;
    private LocalDate ngay;
    private String trangThai;

    public ChamSocKhachHang() {
    }

    public ChamSocKhachHang(String maCSKH, String maKH, String noiDung, String kenh, LocalDate ngay, String trangThai) {
        this.maCSKH = maCSKH;
        this.maKH = maKH;
        this.noiDung = noiDung;
        this.kenh = kenh;
        this.ngay = ngay;
        this.trangThai = trangThai;
    }

    public String getMaCSKH() {
        return maCSKH;
    }

    public void setMaCSKH(String maCSKH) {
        this.maCSKH = maCSKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getKenh() {
        return kenh;
    }

    public void setKenh(String kenh) {
        this.kenh = kenh;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
