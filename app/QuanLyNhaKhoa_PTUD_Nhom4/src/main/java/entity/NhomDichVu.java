package entity;

public class NhomDichVu {

    private String maNhom;
    private String tenNhomDichVu;
    private String iconDichVu;
    private String lichChamSoc;
    private String moTa;

    public NhomDichVu() {
    }

    public NhomDichVu(String maNhom, String tenNhomDichVu, String iconDichVu, String lichChamSoc, String moTa) {
        this.maNhom = maNhom;
        this.tenNhomDichVu = tenNhomDichVu;
        this.iconDichVu = iconDichVu;
        this.lichChamSoc = lichChamSoc;
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

    public String getIconDichVu() {
        return iconDichVu;
    }

    public void setIconDichVu(String iconDichVu) {
        this.iconDichVu = iconDichVu;
    }

    public String getLichChamSoc() {
        return lichChamSoc;
    }

    public void setLichChamSoc(String lichChamSoc) {
        this.lichChamSoc = lichChamSoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
