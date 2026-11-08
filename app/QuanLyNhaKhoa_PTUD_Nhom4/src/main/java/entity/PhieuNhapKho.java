package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PhieuNhapKho {

    private String maPhieuNhap;
    private LocalDate ngayNhap;
    private String loaiVatTu;
    private NhaCungCap nhaCungCap;
    private String lyDo;
    private long tongTien;
    private String fileExcel;
    private LocalDateTime ngayTao;
    private NhanVien nguoiTao;
    private List<ChiTietNhapKho> chiTiet;

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

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
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

    public NhanVien getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(NhanVien nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public List<ChiTietNhapKho> getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(List<ChiTietNhapKho> chiTiet) {
        this.chiTiet = chiTiet;
    }
}
