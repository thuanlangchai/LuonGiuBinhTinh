package entity;

public class DichVu {

    private String maDichVu;
    private String maNhomDichVu;
    private String tenDichVu;
    private long gia;
    private double tyLeHoaHong;
    private double thue;
    private boolean canDatLabo;
    private String trangThai;

    public DichVu() {
    }

    public DichVu(String maDichVu, String maNhomDichVu, String tenDichVu, long gia, double tyLeHoaHong, double thue, boolean canDatLabo, String trangThai) {
        this.maDichVu = maDichVu;
        this.maNhomDichVu = maNhomDichVu;
        this.tenDichVu = tenDichVu;
        this.gia = gia;
        this.tyLeHoaHong = tyLeHoaHong;
        this.thue = thue;
        this.canDatLabo = canDatLabo;
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

    public double getTyLeHoaHong() {
        return tyLeHoaHong;
    }

    public void setTyLeHoaHong(double tyLeHoaHong) {
        this.tyLeHoaHong = tyLeHoaHong;
    }

    public double getThue() {
        return thue;
    }

    public void setThue(double thue) {
        this.thue = thue;
    }

    public boolean isCanDatLabo() {
        return canDatLabo;
    }

    public void setCanDatLabo(boolean canDatLabo) {
        this.canDatLabo = canDatLabo;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
