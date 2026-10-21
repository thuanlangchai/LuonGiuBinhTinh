package entity;

public class Thuoc {

    private String maThuoc;
    private String tenThuoc;
    private String maDonVi;
    private long gia;
    private String cachDung;
    private String maThuocGoc;

    public Thuoc() {
    }

    public Thuoc(String maThuoc, String tenThuoc, String maDonVi, long gia, String cachDung, String maThuocGoc) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.maDonVi = maDonVi;
        this.gia = gia;
        this.cachDung = cachDung;
        this.maThuocGoc = maThuocGoc;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getMaDonVi() {
        return maDonVi;
    }

    public void setMaDonVi(String maDonVi) {
        this.maDonVi = maDonVi;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getCachDung() {
        return cachDung;
    }

    public void setCachDung(String cachDung) {
        this.cachDung = cachDung;
    }

    public String getMaThuocGoc() {
        return maThuocGoc;
    }

    public void setMaThuocGoc(String maThuocGoc) {
        this.maThuocGoc = maThuocGoc;
    }
}
