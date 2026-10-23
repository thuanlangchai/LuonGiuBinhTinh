package entity;

public class LyDoKham {

    private String maLyDo;
    private String tenLyDo;

    public LyDoKham() {
    }

    public LyDoKham(String maLyDo, String tenLyDo) {
        this.maLyDo = maLyDo;
        this.tenLyDo = tenLyDo;
    }

    public String getMaLyDo() {
        return maLyDo;
    }

    public void setMaLyDo(String maLyDo) {
        this.maLyDo = maLyDo;
    }

    public String getTenLyDo() {
        return tenLyDo;
    }

    public void setTenLyDo(String tenLyDo) {
        this.tenLyDo = tenLyDo;
    }
}
