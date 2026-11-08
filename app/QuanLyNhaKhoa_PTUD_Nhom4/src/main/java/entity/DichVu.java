package entity;

public class DichVu {

    private String maDichVu;
    private NhomDichVu nhomDichVu;
    private String tenDichVu;
    private long gia;
    private String trangThai;

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public NhomDichVu getNhomDichVu() {
        return nhomDichVu;
    }

    public void setNhomDichVu(NhomDichVu nhomDichVu) {
        this.nhomDichVu = nhomDichVu;
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
