package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class KeHoachDieuTri {

    private String maKeHoach;
    private String maKH;
    private LocalDate ngayKeHoach;
    private String ghiChu;
    private String trangThai;
    private LocalDateTime ngayTao;
    private String nguoiTao;

    public KeHoachDieuTri() {
    }

    public KeHoachDieuTri(String maKeHoach, String maKH, LocalDate ngayKeHoach, String ghiChu, String trangThai, LocalDateTime ngayTao, String nguoiTao) {
        this.maKeHoach = maKeHoach;
        this.maKH = maKH;
        this.ngayKeHoach = ngayKeHoach;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
    }

    public String getMaKeHoach() {
        return maKeHoach;
    }

    public void setMaKeHoach(String maKeHoach) {
        this.maKeHoach = maKeHoach;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public LocalDate getNgayKeHoach() {
        return ngayKeHoach;
    }

    public void setNgayKeHoach(LocalDate ngayKeHoach) {
        this.ngayKeHoach = ngayKeHoach;
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

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }
}
