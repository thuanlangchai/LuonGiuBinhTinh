package entity;

public class LoaiRang {

    private String maLoaiRang;
    private String tenLoaiRang;
//    private String baoHanh;  chưa có cột bảo hành, xem lại view_Rang

    public LoaiRang() {
    }

    
    
	public LoaiRang(String maLoaiRang, String tenLoaiRang) {
		super();
		this.maLoaiRang = maLoaiRang;
		this.tenLoaiRang = tenLoaiRang;
	}

//    public LoaiRang(String maLoaiRang, String tenLoaiRang, String baoHanh) {
//        this.maLoaiRang = maLoaiRang;
//        this.tenLoaiRang = tenLoaiRang;
//        this.baoHanh = baoHanh;
//    }

    public String getMaLoaiRang() {
        return maLoaiRang;
    }

    public void setMaLoaiRang(String maLoaiRang) {
        this.maLoaiRang = maLoaiRang;
    }

    public String getTenLoaiRang() {
        return tenLoaiRang;
    }

    public void setTenLoaiRang(String tenLoaiRang) {
        this.tenLoaiRang = tenLoaiRang;
    }

//    public String getBaoHanh() {
//        return baoHanh;
//    }
//
//    public void setBaoHanh(String baoHanh) {
//        this.baoHanh = baoHanh;
//    }
}
