package entity;

public class DichVu {

    private String maDichVu;
    private String maNhomDichVu;
    private String tenDichVu;
    private long gia;
    private String trangThai;

    public DichVu() {
    }

    public DichVu(String maDichVu, String maNhomDichVu, String tenDichVu, long gia, String trangThai) {
        this.maDichVu = maDichVu;
        this.maNhomDichVu = maNhomDichVu;
        this.tenDichVu = tenDichVu;
        this.gia = gia;
        this.trangThai = trangThai;
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getMaNhomDichVu() {
        return maNhomDichVu;
    }

    public void setMaNhomDichVu(String maNhomDichVu) {
        this.maNhomDichVu = maNhomDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
