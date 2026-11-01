package service;

import entity.ChiTietKeHoachDieuTri;
import entity.HoaDon;
import entity.KeHoachDieuTri;
import entity.LichHen;
import entity.PhieuChi;
import entity.PhieuThu;
import entity.ToaThuoc;
import java.util.List;


public interface BaoCao_IService {

    List<ToaThuoc> baoCaoToaThuoc();

    List<PhieuThu> danhSachPhieuThu();

    List<PhieuChi> danhSachPhieuChi();

    List<HoaDon> danhSachHoaDon();

    List<LichHen> danhSachLichHen();

    List<KeHoachDieuTri> danhSachKeHoach();

    List<ChiTietKeHoachDieuTri> chiTietTheoKeHoach(String maKeHoach);

    long tongDaThuTheoKhach(String maKH);

    long tongConNoTheoKhach(String maKH);
}
