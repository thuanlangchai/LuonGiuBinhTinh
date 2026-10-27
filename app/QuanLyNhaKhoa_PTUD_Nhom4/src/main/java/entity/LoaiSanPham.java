package entity;

public class LoaiSanPham {

    private String maLoai; //view themloaisp chưa có mã loại sản phẩm
    private String tenLoaiSanPham;
    private String loaiKho; // chưa có table kho 

    public LoaiSanPham() {
    }

    public LoaiSanPham(String maLoai, String tenLoaiSanPham, String loaiKho) {
        this.maLoai = maLoai;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.loaiKho = loaiKho;
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

    public String getLoaiKho() {
        return loaiKho;
    }

    public void setLoaiKho(String loaiKho) {
        this.loaiKho = loaiKho;
    }
}
