package entity;

public class Thuoc {

    private String maThuoc;
    private String tenThuoc;
    private DonVi donVi;
    private long gia;
    private String cachDung;

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

    public DonVi getDonVi() {
        return donVi;
    }

    public void setDonVi(DonVi donVi) {
        this.donVi = donVi;
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
