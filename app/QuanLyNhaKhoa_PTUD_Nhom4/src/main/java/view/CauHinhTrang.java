package view;

public class CauHinhTrang {

    public final String ma;
    public final String tieuDe;
    public final String goiY;
    public final String nutThem;
    public final String[] cot;
    public final Object[][] duLieu;
    public final String ghiChu;

    public CauHinhTrang(String ma, String tieuDe, String goiY, String nutThem, String[] cot, Object[][] duLieu, String ghiChu) {
        this.ma = ma;
        this.tieuDe = tieuDe;
        this.goiY = goiY;
        this.nutThem = nutThem;
        this.cot = cot;
        this.duLieu = duLieu;
        this.ghiChu = ghiChu;
    }
}
