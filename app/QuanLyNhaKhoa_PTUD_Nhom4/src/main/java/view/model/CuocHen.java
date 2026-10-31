package view.model;

import java.time.LocalDateTime;

public class CuocHen {

    public String congViec;
    public String khach;
    public LocalDateTime batDau;
    public int phut;
    public String bacSi;
    public String ghiChu;
    public String trangThai;

    public CuocHen(String congViec, String khach, LocalDateTime batDau, int phut, String bacSi, String ghiChu, String trangThai) {
        this.congViec = congViec;
        this.khach = khach;
        this.batDau = batDau;
        this.phut = phut;
        this.bacSi = bacSi;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }
}
