package entity;

public class HangDT {

    public String ngay;
    public String rang;
    public String dichVu;
    public int soLuong;
    public long donGia;
    public String trangThai;
    public String maHoaDon;

    public HangDT(String ngay, String rang, String dichVu, int soLuong, long donGia, String trangThai) {
        this.ngay = ngay;
        this.rang = rang == null ? "" : rang;
        this.dichVu = dichVu;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public long thanhTien() {
        return soLuong * donGia;
    }

    public String tenKeHoach() {
        return "Kế hoạch điều trị ngày " + ngay;
    }
}
