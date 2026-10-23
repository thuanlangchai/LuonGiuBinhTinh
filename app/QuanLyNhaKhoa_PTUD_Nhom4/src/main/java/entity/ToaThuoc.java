package entity;

import java.time.LocalDateTime;

public class ToaThuoc {

    private String maToaThuoc;
    private String maKH;
    private String maChanDoan;
    private String maBacSi;
    private String hinhThucThanhToan;
    private boolean nhanTaiPhongKham;
    private String ghiChu;
    private long thanhTien;
    private LocalDateTime ngayTao;

    public ToaThuoc() {
    }

    public ToaThuoc(String maToaThuoc, String maKH, String maChanDoan, String maBacSi, String hinhThucThanhToan, boolean nhanTaiPhongKham, String ghiChu, long thanhTien, LocalDateTime ngayTao) {
        this.maToaThuoc = maToaThuoc;
        this.maKH = maKH;
        this.maChanDoan = maChanDoan;
        this.maBacSi = maBacSi;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.nhanTaiPhongKham = nhanTaiPhongKham;
        this.ghiChu = ghiChu;
        this.thanhTien = thanhTien;
        this.ngayTao = ngayTao;
    }

    public String getMaToaThuoc() {
        return maToaThuoc;
    }

    public void setMaToaThuoc(String maToaThuoc) {
        this.maToaThuoc = maToaThuoc;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaChanDoan() {
        return maChanDoan;
    }

    public void setMaChanDoan(String maChanDoan) {
        this.maChanDoan = maChanDoan;
    }

    public String getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(String maBacSi) {
        this.maBacSi = maBacSi;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public boolean isNhanTaiPhongKham() {
        return nhanTaiPhongKham;
    }

    public void setNhanTaiPhongKham(boolean nhanTaiPhongKham) {
        this.nhanTaiPhongKham = nhanTaiPhongKham;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}
