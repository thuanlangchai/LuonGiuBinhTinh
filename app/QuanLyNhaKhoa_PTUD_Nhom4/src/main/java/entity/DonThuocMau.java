package entity;

public class DonThuocMau {

    private String maMau;
    private String tenMau;

    public DonThuocMau() {
    }

    public DonThuocMau(String maMau, String tenMau) {
        this.maMau = maMau;
        this.tenMau = tenMau;
    }

    public String getMaMau() {
        return maMau;
    }

    public void setMaMau(String maMau) {
        this.maMau = maMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }
}
