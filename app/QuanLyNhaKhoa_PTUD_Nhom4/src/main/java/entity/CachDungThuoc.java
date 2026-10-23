package entity;

public class CachDungThuoc {

    private String maCachDung;
    private String tenCachDung;

    public CachDungThuoc() {
    }

    public CachDungThuoc(String maCachDung, String tenCachDung) {
        this.maCachDung = maCachDung;
        this.tenCachDung = tenCachDung;
    }

    public String getMaCachDung() {
        return maCachDung;
    }

    public void setMaCachDung(String maCachDung) {
        this.maCachDung = maCachDung;
    }

    public String getTenCachDung() {
        return tenCachDung;
    }

    public void setTenCachDung(String tenCachDung) {
        this.tenCachDung = tenCachDung;
    }
}
