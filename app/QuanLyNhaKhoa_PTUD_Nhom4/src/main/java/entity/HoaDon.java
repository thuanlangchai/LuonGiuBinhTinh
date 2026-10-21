package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HoaDon {

    private String maHoaDon;
    private LocalDate ngayLap;
    private String maKH;
    private String maKeHoach;
    private String maBacSi;
    private long tongTien;
    private long chietKhau;
    private long thanhTien;
    private long daThu;
    private long conNo;
    private String trangThai;
    private String ghiChu;
    private LocalDateTime ngayTao;
    private String nguoiTao;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, LocalDate ngayLap, String maKH, String maKeHoach, String maBacSi, long tongTien, long chietKhau, long thanhTien, long daThu, long conNo, String trangThai, String ghiChu, LocalDateTime ngayTao, String nguoiTao) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.maKH = maKH;
        this.maKeHoach = maKeHoach;
        this.maBacSi = maBacSi;
        this.tongTien = tongTien;
        this.chietKhau = chietKhau;
        this.thanhTien = thanhTien;
        this.daThu = daThu;
        this.conNo = conNo;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaKeHoach() {
        return maKeHoach;
    }

    public void setMaKeHoach(String maKeHoach) {
        this.maKeHoach = maKeHoach;
    }

    public String getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(String maBacSi) {
        this.maBacSi = maBacSi;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public long getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(long chietKhau) {
        this.chietKhau = chietKhau;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public long getDaThu() {
        return daThu;
    }

    public void setDaThu(long daThu) {
        this.daThu = daThu;
    }

    public long getConNo() {
        return conNo;
    }

    public void setConNo(long conNo) {
        this.conNo = conNo;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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
