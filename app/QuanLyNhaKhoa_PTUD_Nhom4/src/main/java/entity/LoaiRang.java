package entity;

public class LoaiRang {

    private String maLoaiRang;
    private String tenLoaiRang;

    public LoaiRang() {
    }

    public LoaiRang(String maLoaiRang, String tenLoaiRang) {
        super();
        this.maLoaiRang = maLoaiRang;
        this.tenLoaiRang = tenLoaiRang;
    }

    public String getMaLoaiRang() {
        return maLoaiRang;
    }

    public void setMaLoaiRang(String maLoaiRang) {
        this.maLoaiRang = maLoaiRang;
    }

    public String getTenLoaiRang() {
        return tenLoaiRang;
    }

    public void setTenLoaiRang(String tenLoaiRang) {
        this.tenLoaiRang = tenLoaiRang;
    }
}
