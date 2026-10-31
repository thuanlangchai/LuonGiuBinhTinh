package view.model;

public class ChiTietHoaDonView {

    public String ngay;
    public String rang;
    public String dichVu;
    public int soLuong;
    public long donGia;
    public String keHoach;

    public ChiTietHoaDonView(String ngay, String rang, String dichVu, int soLuong, long donGia, String keHoach) {
        this.ngay = ngay;
        this.rang = rang == null ? "" : rang;
        this.dichVu = dichVu;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.keHoach = keHoach == null ? "" : keHoach;
    }

    public long thanhTien() {
        return soLuong * donGia;
    }
}
