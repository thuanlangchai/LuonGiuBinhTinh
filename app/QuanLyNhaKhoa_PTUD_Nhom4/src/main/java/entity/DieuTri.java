package entity;

public class DieuTri {

    private String maDieuTri;
    private String tenDieuTri;

    public DieuTri() {
    }

    public DieuTri(String maDieuTri, String tenDieuTri) {
        this.maDieuTri = maDieuTri;
        this.tenDieuTri = tenDieuTri;
    }

    public String getMaDieuTri() {
        return maDieuTri;
    }

    public void setMaDieuTri(String maDieuTri) {
        this.maDieuTri = maDieuTri;
    }

    public String getTenDieuTri() {
        return tenDieuTri;
    }

    public void setTenDieuTri(String tenDieuTri) {
        this.tenDieuTri = tenDieuTri;
    }
}
