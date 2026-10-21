package entity;

public class Rang {

    private String maRang;
    private String tenRang;
    private String maLoaiRang;

    public Rang() {
    }

    public Rang(String maRang, String tenRang, String maLoaiRang) {
        this.maRang = maRang;
        this.tenRang = tenRang;
        this.maLoaiRang = maLoaiRang;
    }

    public String getMaRang() {
        return maRang;
    }

    public void setMaRang(String maRang) {
        this.maRang = maRang;
    }

    public String getTenRang() {
        return tenRang;
    }

    public void setTenRang(String tenRang) {
        this.tenRang = tenRang;
    }

    public String getMaLoaiRang() {
        return maLoaiRang;
    }

    public void setMaLoaiRang(String maLoaiRang) {
        this.maLoaiRang = maLoaiRang;
    }
}
