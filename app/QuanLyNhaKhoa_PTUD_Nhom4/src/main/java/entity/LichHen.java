package entity;

import java.time.LocalDateTime;

public class LichHen {

    private String maLichHen; //thiếu mã lịch hẹn trong view
    private String maKH;
    private LocalDateTime batDau;
    private int thoiLuongPhut; 
    private String maBacSi; // xem xét với view
    private String maPhuTa;	//xem xét với view 
    private String ghiChu;
    private String trangThai;

    public LichHen() {
    }
    
    
    
	public LichHen(String maLichHen, String maKH, LocalDateTime batDau, int thoiLuongPhut, String maBacSi,
			String maPhuTa, String ghiChu, String trangThai) {
		super();
		this.maLichHen = maLichHen;
		this.maKH = maKH;
		this.batDau = batDau;
		this.thoiLuongPhut = thoiLuongPhut;
		this.maBacSi = maBacSi;
		this.maPhuTa = maPhuTa;
		this.ghiChu = ghiChu;
		this.trangThai = trangThai;
	}


    public String getMaLichHen() {
        return maLichHen;
    }

    public void setMaLichHen(String maLichHen) {
        this.maLichHen = maLichHen;
    }


    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public LocalDateTime getBatDau() {
        return batDau;
    }

    public void setBatDau(LocalDateTime batDau) {
        this.batDau = batDau;
    }

    public int getThoiLuongPhut() {
        return thoiLuongPhut;
    }

    public void setThoiLuongPhut(int thoiLuongPhut) {
        this.thoiLuongPhut = thoiLuongPhut;
    }

    public String getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(String maBacSi) {
        this.maBacSi = maBacSi;
    }

    public String getMaPhuTa() {
        return maPhuTa;
    }

    public void setMaPhuTa(String maPhuTa) {
        this.maPhuTa = maPhuTa;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
