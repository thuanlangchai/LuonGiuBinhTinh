package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PhieuXuatKho {

    private String maPhieuXuat;
    private LocalDate ngayXuat;
    private String loaiVatTu;
    private NhaCungCap nhaCungCap;
    private String lyDo;
    private LocalDateTime ngayTao;
    private NhanVien nguoiTao;
    private List<ChiTietXuatKho> chiTiet;
    private Long tongTienDoc;

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
        if (chiTiet != null) {
            long tong = 0;
            for (ChiTietXuatKho dong : chiTiet) {
                tong += dong.getThanhTien();
            }
            return tong;
        }
        return tongTienDoc == null ? 0 : tongTienDoc;
    }

    public void ganTongTienDoc(Long tongTienDoc) {
        this.tongTienDoc = tongTienDoc;
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

    public List<ChiTietXuatKho> getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(List<ChiTietXuatKho> chiTiet) {
        this.chiTiet = chiTiet;
        this.tongTienDoc = null;
    }
}
