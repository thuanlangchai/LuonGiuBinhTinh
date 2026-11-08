package entity;

import java.time.LocalDateTime;
import java.util.List;

public class ToaThuoc {

    private String maToaThuoc;
    private KhachHang khachHang;
    private String maChanDoan;
    private NhanVien bacSi;
    private String hinhThucThanhToan;
    private boolean nhanTaiPhongKham;
    private String ghiChu;
    private long thanhTien;
    private LocalDateTime ngayTao;
    private List<ChiTietToaThuoc> chiTiet;

    public String getMaToaThuoc() {
        return maToaThuoc;
    }

    public void setMaToaThuoc(String maToaThuoc) {
        this.maToaThuoc = maToaThuoc;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public String getMaChanDoan() {
        return maChanDoan;
    }

    public void setMaChanDoan(String maChanDoan) {
        this.maChanDoan = maChanDoan;
    }

    public NhanVien getBacSi() {
        return bacSi;
    }

    public void setBacSi(NhanVien bacSi) {
        this.bacSi = bacSi;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public boolean isNhanTaiPhongKham() {
        return nhanTaiPhongKham;
    }

    public void setNhanTaiPhongKham(boolean nhanTaiPhongKham) {
        this.nhanTaiPhongKham = nhanTaiPhongKham;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public List<ChiTietToaThuoc> getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(List<ChiTietToaThuoc> chiTiet) {
        this.chiTiet = chiTiet;
    }
}
