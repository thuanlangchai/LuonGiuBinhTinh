package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class KeHoachDieuTri {

    private String maKeHoach;
    private KhachHang khachHang;
    private LocalDate ngayKeHoach;
    private NhanVien bacSi;
    private String lyDoDieuTri;
    private String ghiChu;
    private String trangThai;
    private LocalDateTime ngayTao;
    private NhanVien nguoiTao;
    private List<ChiTietKeHoachDieuTri> chiTiet;

    public String getMaKeHoach() {
        return maKeHoach;
    }

    public void setMaKeHoach(String maKeHoach) {
        this.maKeHoach = maKeHoach;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public LocalDate getNgayKeHoach() {
        return ngayKeHoach;
    }

    public void setNgayKeHoach(LocalDate ngayKeHoach) {
        this.ngayKeHoach = ngayKeHoach;
    }

    public NhanVien getBacSi() {
        return bacSi;
    }

    public void setBacSi(NhanVien bacSi) {
        this.bacSi = bacSi;
    }

    public String getLyDoDieuTri() {
        return lyDoDieuTri;
    }

    public void setLyDoDieuTri(String lyDoDieuTri) {
        this.lyDoDieuTri = lyDoDieuTri;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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

    public List<ChiTietKeHoachDieuTri> getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(List<ChiTietKeHoachDieuTri> chiTiet) {
        this.chiTiet = chiTiet;
    }
}
