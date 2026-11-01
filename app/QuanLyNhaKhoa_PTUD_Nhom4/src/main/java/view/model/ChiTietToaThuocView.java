package view.model;

public class ChiTietToaThuocView {

    public String maThuoc;
    public String tenThuoc;
    public int soNgay;
    public String cachSuDung;
    public int tongSo;
    public long donGia;
    public long thanhTien;

    public ChiTietToaThuocView(String maThuoc, String tenThuoc, int soNgay, String cachSuDung,
            int tongSo, long donGia, long thanhTien) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.soNgay = soNgay;
        this.cachSuDung = cachSuDung;
        this.tongSo = tongSo;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }
}
