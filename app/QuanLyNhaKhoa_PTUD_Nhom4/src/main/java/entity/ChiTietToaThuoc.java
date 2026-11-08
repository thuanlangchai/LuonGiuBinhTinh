package entity;

public class ChiTietToaThuoc {

    private String maChiTiet;
    private ToaThuoc toaThuoc;
    private Thuoc thuoc;
    private int soNgay;
    private String cachSuDung;
    private int tongSo;
    private long donGia;
    private long thanhTien;

    public String getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(String maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public ToaThuoc getToaThuoc() {
        return toaThuoc;
    }

    public void setToaThuoc(ToaThuoc toaThuoc) {
        this.toaThuoc = toaThuoc;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public int getSoNgay() {
        return soNgay;
    }

    public void setSoNgay(int soNgay) {
        this.soNgay = soNgay;
    }

    public String getCachSuDung() {
        return cachSuDung;
    }

    public void setCachSuDung(String cachSuDung) {
        this.cachSuDung = cachSuDung;
    }

    public int getTongSo() {
        return tongSo;
    }

    public void setTongSo(int tongSo) {
        this.tongSo = tongSo;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }
}
