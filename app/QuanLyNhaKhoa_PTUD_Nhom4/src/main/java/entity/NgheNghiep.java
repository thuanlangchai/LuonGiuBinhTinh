package entity;

public class NgheNghiep {

    private String maNghe;
    private String tenNghe;

    public NgheNghiep() {
    }

    public NgheNghiep(String maNghe, String tenNghe) {
        this.maNghe = maNghe;
        this.tenNghe = tenNghe;
    }

    public String getMaNghe() {
        return maNghe;
    }

    public void setMaNghe(String maNghe) {
        this.maNghe = maNghe;
    }

    public String getTenNghe() {
        return tenNghe;
    }

    public void setTenNghe(String tenNghe) {
        this.tenNghe = tenNghe;
    }
}
