package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Voucher {

    private String maVoucher;
    private String tenVoucher;
    private double phanTram;
    private LocalDate tuNgay;
    private LocalDate denNgay;
    private String trangThai;
    private LocalDateTime ngayTao;

    public Voucher() {
    }

    public Voucher(String maVoucher, String tenVoucher, double phanTram, LocalDate tuNgay, LocalDate denNgay, String trangThai, LocalDateTime ngayTao) {
        this.maVoucher = maVoucher;
        this.tenVoucher = tenVoucher;
        this.phanTram = phanTram;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public double getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(double phanTram) {
        this.phanTram = phanTram;
    }

    public LocalDate getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(LocalDate tuNgay) {
        this.tuNgay = tuNgay;
    }

    public LocalDate getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(LocalDate denNgay) {
        this.denNgay = denNgay;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}
