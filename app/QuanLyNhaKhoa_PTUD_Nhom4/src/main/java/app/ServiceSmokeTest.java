package app;

import entity.TaiKhoan;
import service.impl.BaoCao_Service;
import service.impl.DanhMuc_Service;
import service.impl.HoaDon_Service;
import service.impl.KeHoachDieuTri_Service;
import service.impl.KhachHang_Service;
import service.impl.Kho_Service;
import service.impl.LichHen_Service;
import service.impl.PhieuChi_Service;
import service.impl.PhieuThu_Service;
import service.impl.TaiKhoan_Service;
import service.impl.ToaThuoc_Service;

/** Smoke test Service Layer — Giai đoạn 2. */
public class ServiceSmokeTest {

    public static void main(String[] args) {
        TaiKhoan_Service tk = new TaiKhoan_Service();
        TaiKhoan ok = tk.dangNhap("thuan080", "123456");
        TaiKhoan fail = tk.dangNhap("thuan080", "sai");
        System.out.println("dangNhap OK=" + (ok != null ? ok.getTenDangNhap() : null)
                + ", saiMK=" + (fail == null));

        System.out.println("KH=" + new KhachHang_Service().findAllKhachHang().size());
        System.out.println("LichHen=" + new LichHen_Service().findAllLichHen().size());
        System.out.println("KHDT=" + new KeHoachDieuTri_Service().findAllKeHoachDieuTri().size());
        System.out.println("CTKHDT(KH01)=" + new KeHoachDieuTri_Service().findChiTietByMaKeHoach("KH01").size());
        System.out.println("HoaDon=" + new HoaDon_Service().findAllHoaDon().size());
        System.out.println("PhieuThu=" + new PhieuThu_Service().findAllPhieuThu().size());
        System.out.println("PhieuChi=" + new PhieuChi_Service().findAllPhieuChi().size());
        System.out.println("ToaThuoc=" + new ToaThuoc_Service().findAllToaThuoc().size());
        System.out.println("DichVu=" + new DanhMuc_Service().findAllDichVu().size());
        System.out.println("NhanVien=" + new DanhMuc_Service().findAllNhanVien().size());
        System.out.println("SanPham=" + new Kho_Service().findAllSanPham().size());
        System.out.println("BaoCao HD conNo 0001=" + new BaoCao_Service().tongConNoTheoKhach("0001"));
    }
}
