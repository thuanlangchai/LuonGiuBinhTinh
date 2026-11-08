package entity;

public class ChiTietXuatKho {

    private String maChiTiet;
    private PhieuXuatKho phieuXuatKho;
    private SanPham sanPham;
    private int soLuong;
    private long donGia;
    private long thanhTien;

    public String getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(String maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public PhieuXuatKho getPhieuXuatKho() {
        return phieuXuatKho;
    }

    public void setPhieuXuatKho(PhieuXuatKho phieuXuatKho) {
        this.phieuXuatKho = phieuXuatKho;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }
}
