package entity;

public class ChiTietToaThuoc {

    private String maChiTiet;
    private String maToaThuoc;
    private String maThuoc;
    private int soNgay;
    private String cachSuDung;
    private int tongSo;
    private long donGia;
    private long thanhTien;

    public ChiTietToaThuoc() {
    }

    public ChiTietToaThuoc(String maChiTiet, String maToaThuoc, String maThuoc, int soNgay, String cachSuDung, int tongSo, long donGia, long thanhTien) {
        this.maChiTiet = maChiTiet;
        this.maToaThuoc = maToaThuoc;
        this.maThuoc = maThuoc;
        this.soNgay = soNgay;
        this.cachSuDung = cachSuDung;
        this.tongSo = tongSo;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public String getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(String maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public String getMaToaThuoc() {
        return maToaThuoc;
    }

    public void setMaToaThuoc(String maToaThuoc) {
        this.maToaThuoc = maToaThuoc;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
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
