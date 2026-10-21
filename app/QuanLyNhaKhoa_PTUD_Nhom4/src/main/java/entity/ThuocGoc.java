package entity;

public class ThuocGoc {

    private String maThuocGoc;
    private String tenThuocGoc;

    public ThuocGoc() {
    }

    public ThuocGoc(String maThuocGoc, String tenThuocGoc) {
        this.maThuocGoc = maThuocGoc;
        this.tenThuocGoc = tenThuocGoc;
    }

    public String getMaThuocGoc() {
        return maThuocGoc;
    }

    public void setMaThuocGoc(String maThuocGoc) {
        this.maThuocGoc = maThuocGoc;
    }

    public String getTenThuocGoc() {
        return tenThuocGoc;
    }

    public void setTenThuocGoc(String tenThuocGoc) {
        this.tenThuocGoc = tenThuocGoc;
    }
}
