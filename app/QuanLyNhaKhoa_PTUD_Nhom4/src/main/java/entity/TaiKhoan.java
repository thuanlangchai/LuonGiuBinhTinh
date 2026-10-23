package entity;

import java.time.LocalDateTime;

public class TaiKhoan {

    private String tenDangNhap;
    private String matKhau;
    private String email;
    private String vaiTro;
    private String maNhanVien;
    private boolean kichHoat;
    private LocalDateTime ngayTao;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String email, String vaiTro,
            String maNhanVien, boolean kichHoat, LocalDateTime ngayTao) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.email = email;
        this.vaiTro = vaiTro;
        this.maNhanVien = maNhanVien;
        this.kichHoat = kichHoat;
        this.ngayTao = ngayTao;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public boolean isKichHoat() {
        return kichHoat;
    }

    public void setKichHoat(boolean kichHoat) {
        this.kichHoat = kichHoat;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}
