package entity;

public class TienSuBenh {

    private String maTienSu;
    private String tenTienSu;

    public TienSuBenh() {
    }

    public TienSuBenh(String maTienSu, String tenTienSu) {
        this.maTienSu = maTienSu;
        this.tenTienSu = tenTienSu;
    }

    public String getMaTienSu() {
        return maTienSu;
    }

    public void setMaTienSu(String maTienSu) {
        this.maTienSu = maTienSu;
    }

    public String getTenTienSu() {
        return tenTienSu;
    }

    public void setTenTienSu(String tenTienSu) {
        this.tenTienSu = tenTienSu;
    }
}
