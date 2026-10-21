package entity;

public class DiUng {

    private String maDiUng;
    private String tenDiUng;

    public DiUng() {
    }

    public DiUng(String maDiUng, String tenDiUng) {
        this.maDiUng = maDiUng;
        this.tenDiUng = tenDiUng;
    }

    public String getMaDiUng() {
        return maDiUng;
    }

    public void setMaDiUng(String maDiUng) {
        this.maDiUng = maDiUng;
    }

    public String getTenDiUng() {
        return tenDiUng;
    }

    public void setTenDiUng(String tenDiUng) {
        this.tenDiUng = tenDiUng;
    }
}
