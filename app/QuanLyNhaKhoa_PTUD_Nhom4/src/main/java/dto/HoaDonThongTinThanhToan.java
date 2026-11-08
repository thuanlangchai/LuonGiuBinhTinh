package dto;

public final class HoaDonThongTinThanhToan {

    private final String maHoaDon;
    private final long tongTien;
    private final long chietKhau;
    private final long thanhTien;
    private final long daThu;
    private final long conNo;
    private final String trangThaiThanhToan;

    public HoaDonThongTinThanhToan(String maHoaDon, long tongTien, long chietKhau, long thanhTien,
            long daThu, long conNo, String trangThaiThanhToan) {
        this.maHoaDon = maHoaDon;
        this.tongTien = tongTien;
        this.chietKhau = chietKhau;
        this.thanhTien = thanhTien;
        this.daThu = daThu;
        this.conNo = conNo;
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public long getTongTien() {
        return tongTien;
    }

    public long getChietKhau() {
        return chietKhau;
    }

    public long getThanhTien() {
        return thanhTien;
    }

    public long getDaThu() {
        return daThu;
    }

    public long getConNo() {
        return conNo;
    }

    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }
}
