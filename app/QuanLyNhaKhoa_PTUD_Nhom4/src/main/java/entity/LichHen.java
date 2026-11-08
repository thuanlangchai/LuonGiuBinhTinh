package entity;

import java.time.LocalDateTime;

public class LichHen {

    private String maLichHen;
    private KhachHang khachHang;
    private LocalDateTime batDau;
    private int thoiLuongPhut;
    private NhanVien bacSi;
    private NhanVien phuTa;
    private KeHoachDieuTri keHoachDieuTri;
    private String ghiChu;
    private String trangThai;

    public String getMaLichHen() {
        return maLichHen;
    }

    public void setMaLichHen(String maLichHen) {
        this.maLichHen = maLichHen;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public LocalDateTime getBatDau() {
        return batDau;
    }

    public void setBatDau(LocalDateTime batDau) {
        this.batDau = batDau;
    }

    public int getThoiLuongPhut() {
        return thoiLuongPhut;
    }

    public void setThoiLuongPhut(int thoiLuongPhut) {
        this.thoiLuongPhut = thoiLuongPhut;
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

    public KeHoachDieuTri getKeHoachDieuTri() {
        return keHoachDieuTri;
    }

    public void setKeHoachDieuTri(KeHoachDieuTri keHoachDieuTri) {
        this.keHoachDieuTri = keHoachDieuTri;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
