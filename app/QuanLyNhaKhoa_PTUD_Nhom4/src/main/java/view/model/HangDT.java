package view.model;

public class HangDT {
    public String maChiTiet;
    public String maKeHoach;
    public String maDichVu;
    public String ngay;
    public String rang;
    public String dichVu;
    public int soLuong;
    public long donGia;
    public String trangThai;
    public String maHoaDon;
    public String ghiChu;

    public HangDT(String ngay, String rang, String dichVu, int soLuong, long donGia, String trangThai) {
        this(null, ngay, rang, dichVu, soLuong, donGia, trangThai);
    }

    public HangDT(String maKeHoach, String ngay, String rang, String dichVu, int soLuong, long donGia, String trangThai) {
        this.maKeHoach = maKeHoach;
        this.ngay = ngay;
        this.rang = rang == null ? "" : rang;
        this.dichVu = dichVu;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.trangThai = trangThai;
        this.ghiChu = "";
    }

    public long thanhTien() {
        return soLuong * donGia;
    }

    public String tenKeHoach() {
        if (maKeHoach != null && !maKeHoach.isEmpty()) {
            return "Kế hoạch " + maKeHoach + " - ngày " + ngay;
        }
        return "Kế hoạch điều trị ngày " + ngay;
    }
}
