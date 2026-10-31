package entity;

public class LoaiSanPham {

    private String maLoai;
    private String tenLoaiSanPham;

    public LoaiSanPham() {
    }

    public LoaiSanPham(String maLoai, String tenLoaiSanPham) {
        this.maLoai = maLoai;
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

}
