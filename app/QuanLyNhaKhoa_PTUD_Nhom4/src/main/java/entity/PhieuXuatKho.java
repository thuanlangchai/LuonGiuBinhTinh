package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PhieuXuatKho {

    private String maPhieuXuat;
    private LocalDate ngayXuat;
    private String loaiVatTu;
    private String maNhaCungCap;
    private String lyDo;
    private long tongTien;
    private LocalDateTime ngayTao;
    private String nguoiTao;

    public PhieuXuatKho() {
    }

    public PhieuXuatKho(String maPhieuXuat, LocalDate ngayXuat, String loaiVatTu, String maNhaCungCap, String lyDo, long tongTien, LocalDateTime ngayTao, String nguoiTao) {
        this.maPhieuXuat = maPhieuXuat;
        this.ngayXuat = ngayXuat;
        this.loaiVatTu = loaiVatTu;
        this.maNhaCungCap = maNhaCungCap;
        this.lyDo = lyDo;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
    }

    public String getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(String maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }

    public LocalDate getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(LocalDate ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public String getLoaiVatTu() {
        return loaiVatTu;
    }

    public void setLoaiVatTu(String loaiVatTu) {
        this.loaiVatTu = loaiVatTu;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
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
