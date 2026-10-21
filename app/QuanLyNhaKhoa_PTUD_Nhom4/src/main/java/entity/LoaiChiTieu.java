package entity;

public class LoaiChiTieu {

    private String maLoai;
    private String tenLoaiChiTieu;

    public LoaiChiTieu() {
    }

    public LoaiChiTieu(String maLoai, String tenLoaiChiTieu) {
        this.maLoai = maLoai;
        this.tenLoaiChiTieu = tenLoaiChiTieu;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoaiChiTieu() {
        return tenLoaiChiTieu;
    }

    public void setTenLoaiChiTieu(String tenLoaiChiTieu) {
        this.tenLoaiChiTieu = tenLoaiChiTieu;
    }
}
