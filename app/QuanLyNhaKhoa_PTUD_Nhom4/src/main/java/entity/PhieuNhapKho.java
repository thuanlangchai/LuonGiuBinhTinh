package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PhieuNhapKho {

    private String maPhieuNhap;
    private LocalDate ngayNhap;
    private String loaiVatTu;
    private String maNhaCungCap;
    private String lyDo;
    private long tongTien;
    private String fileExcel;
    private LocalDateTime ngayTao;
    private String nguoiTao;

    public PhieuNhapKho() {
    }

    public PhieuNhapKho(String maPhieuNhap, LocalDate ngayNhap, String loaiVatTu, String maNhaCungCap, String lyDo, long tongTien, String fileExcel, LocalDateTime ngayTao, String nguoiTao) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.loaiVatTu = loaiVatTu;
        this.maNhaCungCap = maNhaCungCap;
        this.lyDo = lyDo;
        this.tongTien = tongTien;
        this.fileExcel = fileExcel;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
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

    public String getFileExcel() {
        return fileExcel;
    }

    public void setFileExcel(String fileExcel) {
        this.fileExcel = fileExcel;
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
