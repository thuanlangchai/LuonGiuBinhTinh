package dto;

import entity.HoaDon;

public final class HoaDonKemThanhToan {

    private final HoaDon hoaDon;
    private final HoaDonThongTinThanhToan thanhToan;

    public HoaDonKemThanhToan(HoaDon hoaDon, HoaDonThongTinThanhToan thanhToan) {
        this.hoaDon = hoaDon;
        this.thanhToan = thanhToan;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public HoaDonThongTinThanhToan getThanhToan() {
        return thanhToan;
    }
}
