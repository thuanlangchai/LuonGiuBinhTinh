package view.model;

import java.time.LocalDateTime;

public class CuocHen {

    public String maLichHen;
    public String maKH;
    public String khach;
    public LocalDateTime batDau;
    public int phut;
    public String bacSi;
    public String phuTa;
    public String ghiChu;
    public String trangThai;

    public CuocHen(String maLichHen, String maKH, String khach, LocalDateTime batDau, int phut,
            String bacSi, String phuTa, String ghiChu, String trangThai) {
        this.maLichHen = maLichHen;
        this.maKH = maKH;
        this.khach = khach;
        this.batDau = batDau;
        this.phut = phut;
        this.bacSi = bacSi;
        this.phuTa = phuTa;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }
}
