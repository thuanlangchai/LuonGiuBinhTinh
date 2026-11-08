package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class HoaDon {

    private String maHoaDon;
    private LocalDate ngayLap;
    private KhachHang khachHang;
    private KeHoachDieuTri keHoachDieuTri;
    private NhanVien bacSi;
    private long chietKhau;
    private String ghiChu;
    private LocalDateTime ngayTao;
    private NhanVien nguoiTao;
    private List<ChiTietHoaDon> chiTietHoaDon;

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public KeHoachDieuTri getKeHoachDieuTri() {
        return keHoachDieuTri;
    }

    public void setKeHoachDieuTri(KeHoachDieuTri keHoachDieuTri) {
        this.keHoachDieuTri = keHoachDieuTri;
    }

    public NhanVien getBacSi() {
        return bacSi;
    }

    public void setBacSi(NhanVien bacSi) {
        this.bacSi = bacSi;
    }

    public long getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(long chietKhau) {
        this.chietKhau = chietKhau;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public NhanVien getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(NhanVien nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public List<ChiTietHoaDon> getChiTietHoaDon() {
        return chiTietHoaDon;
    }

    public void setChiTietHoaDon(List<ChiTietHoaDon> chiTietHoaDon) {
        this.chiTietHoaDon = chiTietHoaDon;
    }
}
