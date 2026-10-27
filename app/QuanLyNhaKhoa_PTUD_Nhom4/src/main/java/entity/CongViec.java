package entity;

import java.time.LocalDateTime;

public class CongViec {
	// bỏ table này 
    private String maCongViec;
    private String tenCongViec;
    private int thoiLuongPhut;
    private String maDichVu;
    private LocalDateTime ngayTao;

    public CongViec() {
    }

    public CongViec(String maCongViec, String tenCongViec, int thoiLuongPhut, String maDichVu, LocalDateTime ngayTao) {
        this.maCongViec = maCongViec;
        this.tenCongViec = tenCongViec;
        this.thoiLuongPhut = thoiLuongPhut;
        this.maDichVu = maDichVu;
        this.ngayTao = ngayTao;
    }

    public String getMaCongViec() {
        return maCongViec;
    }

    public void setMaCongViec(String maCongViec) {
        this.maCongViec = maCongViec;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public int getThoiLuongPhut() {
        return thoiLuongPhut;
    }

    public void setThoiLuongPhut(int thoiLuongPhut) {
        this.thoiLuongPhut = thoiLuongPhut;
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}
