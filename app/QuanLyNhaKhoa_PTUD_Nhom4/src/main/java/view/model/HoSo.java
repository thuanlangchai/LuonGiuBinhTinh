package view.model;

import java.util.ArrayList;
import java.util.List;

public class HoSo {

    public final String ma;
    public String ten;
    public String sdt;
    public String email;
    public String diaChi;
    public String lyDo;
    public final List<HangDT> hang = new ArrayList<>();

    public HoSo(String ma, String ten, String sdt, String email, String diaChi, String lyDo) {
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.lyDo = lyDo;
    }

    public void themHang(HangDT dong) {
        hang.add(dong);
    }

    public long tong() {
        long t = 0;
        for (HangDT h : hang) {
            t += h.thanhTien();
        }
        return t;
    }

    public void capNhatKhach(String ten, String sdt, String email, String diaChi, String lyDo) {
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.lyDo = lyDo == null || lyDo.isEmpty() ? "Đau răng" : lyDo;
    }

}
