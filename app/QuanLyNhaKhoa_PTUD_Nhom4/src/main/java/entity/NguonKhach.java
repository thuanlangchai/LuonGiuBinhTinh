package entity;

public class NguonKhach {

    private String maNguon;
    private String tenNguon;

    public NguonKhach() {
    }

    public NguonKhach(String maNguon, String tenNguon) {
        this.maNguon = maNguon;
        this.tenNguon = tenNguon;
    }

    public String getMaNguon() {
        return maNguon;
    }

    public void setMaNguon(String maNguon) {
        this.maNguon = maNguon;
    }

    public String getTenNguon() {
        return tenNguon;
    }

    public void setTenNguon(String tenNguon) {
        this.tenNguon = tenNguon;
    }
}
