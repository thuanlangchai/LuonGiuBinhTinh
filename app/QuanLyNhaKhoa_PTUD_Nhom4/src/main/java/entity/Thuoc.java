package entity;

public class Thuoc {

    private String maThuoc;
    private String tenThuoc;
    private String maDonVi;
    private long gia;
    private String cachDung;

    public Thuoc() {
    }

    public Thuoc(String maThuoc, String tenThuoc, String maDonVi, long gia, String cachDung) {
        super();
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.maDonVi = maDonVi;
        this.gia = gia;
        this.cachDung = cachDung;
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

}
