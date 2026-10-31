package entity;

public class NhomDichVu {

    private String maNhom;
    private String tenNhomDichVu;
    private String moTa;

    public NhomDichVu() {
    }

    public NhomDichVu(String maNhom, String tenNhomDichVu, String moTa) {
        this.maNhom = maNhom;
        this.tenNhomDichVu = tenNhomDichVu;
        this.moTa = moTa;
    }

    public String getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(String maNhom) {
        this.maNhom = maNhom;
    }

    public String getTenNhomDichVu() {
        return tenNhomDichVu;
    }

    public void setTenNhomDichVu(String tenNhomDichVu) {
        this.tenNhomDichVu = tenNhomDichVu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    
}
