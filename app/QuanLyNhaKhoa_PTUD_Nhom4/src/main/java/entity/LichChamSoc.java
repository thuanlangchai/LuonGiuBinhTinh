package entity;

import java.time.LocalDate;

public class LichChamSoc {
	// Bo table nay
    private String maLichChamSoc;
    private String maKH;
    private String noiDung;
    private LocalDate ngayHen;
    private String maNguoiPhuTrach;
    private String trangThai;

    public LichChamSoc() {
    }

    public LichChamSoc(String maLichChamSoc, String maKH, String noiDung, LocalDate ngayHen, String maNguoiPhuTrach, String trangThai) {
        this.maLichChamSoc = maLichChamSoc;
        this.maKH = maKH;
        this.noiDung = noiDung;
        this.ngayHen = ngayHen;
        this.maNguoiPhuTrach = maNguoiPhuTrach;
        this.trangThai = trangThai;
    }

    public String getMaLichChamSoc() {
        return maLichChamSoc;
    }

    public void setMaLichChamSoc(String maLichChamSoc) {
        this.maLichChamSoc = maLichChamSoc;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public LocalDate getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(LocalDate ngayHen) {
        this.ngayHen = ngayHen;
    }

    public String getMaNguoiPhuTrach() {
        return maNguoiPhuTrach;
    }

    public void setMaNguoiPhuTrach(String maNguoiPhuTrach) {
        this.maNguoiPhuTrach = maNguoiPhuTrach;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
