package service.impl;

import dto.HoaDonKemThanhToan;
import entity.ChiTietKeHoachDieuTri;
import entity.HoaDon;
import entity.KeHoachDieuTri;
import entity.LichHen;
import entity.PhieuChi;
import entity.PhieuThu;
import entity.ToaThuoc;
import java.util.ArrayList;
import java.util.List;
import repository.impl.ChiTietKeHoachDieuTri_DAO;
import repository.impl.HoaDon_DAO;
import repository.impl.KeHoachDieuTri_DAO;
import repository.impl.LichHen_DAO;
import repository.impl.PhieuChi_DAO;
import repository.impl.PhieuThu_DAO;
import repository.impl.ToaThuoc_DAO;
import service.BaoCao_IService;

public class BaoCao_Service implements BaoCao_IService {

    private final ToaThuoc_DAO toaDao = new ToaThuoc_DAO();
    private final PhieuThu_DAO thuDao = new PhieuThu_DAO();
    private final PhieuChi_DAO chiDao = new PhieuChi_DAO();
    private final HoaDon_DAO hoaDonDao = new HoaDon_DAO();
    private final HoaDon_Service hoaDonService = new HoaDon_Service();
    private final LichHen_DAO lichHenDao = new LichHen_DAO();
    private final KeHoachDieuTri_DAO keHoachDao = new KeHoachDieuTri_DAO();
    private final ChiTietKeHoachDieuTri_DAO chiTietKhDao = new ChiTietKeHoachDieuTri_DAO();

    @Override
    public List<ToaThuoc> baoCaoToaThuoc() {
        return toaDao.findAllToaThuoc();
    }

    @Override
    public List<PhieuThu> danhSachPhieuThu() {
        return thuDao.findAllPhieuThu();
    }

    @Override
    public List<PhieuChi> danhSachPhieuChi() {
        return chiDao.findAllPhieuChi();
    }

    @Override
    public List<HoaDon> danhSachHoaDon() {
        return hoaDonDao.findAllHoaDon();
    }

    public List<HoaDonKemThanhToan> danhSachHoaDonKemThanhToan() {
        return hoaDonService.findAllKemThanhToan();
    }

    @Override
    public List<LichHen> danhSachLichHen() {
        return lichHenDao.findAllLichHen();
    }

    @Override
    public List<KeHoachDieuTri> danhSachKeHoach() {
        return keHoachDao.findAllKeHoachDieuTri();
    }

    public List<KeHoachDieuTri> danhSachKeHoachKemChiTiet() {
        List<KeHoachDieuTri> danhSach = keHoachDao.findAllKeHoachDieuTri();
        if (danhSach.isEmpty()) {
            return danhSach;
        }
        List<String> ids = new ArrayList<>();
        for (KeHoachDieuTri keHoach : danhSach) {
            ids.add(keHoach.getMaKeHoach());
        }
        var chiTiet = chiTietKhDao.findByMaKeHoachIn(ids);
        for (KeHoachDieuTri keHoach : danhSach) {
            keHoach.setChiTiet(chiTiet.getOrDefault(keHoach.getMaKeHoach(), List.of()));
        }
        return danhSach;
    }

    @Override
    public List<ChiTietKeHoachDieuTri> chiTietTheoKeHoach(String maKeHoach) {
        return chiTietKhDao.findByMaKeHoach(maKeHoach);
    }

    @Override
    public long tongDaThuTheoKhach(String maKH) {
        long tong = 0;
        for (HoaDonKemThanhToan item : hoaDonService.findKemThanhToanByMaKH(maKH)) {
            tong += item.getThanhToan().getDaThu();
        }
        return tong;
    }

    @Override
    public long tongConNoTheoKhach(String maKH) {
        long tong = 0;
        for (HoaDonKemThanhToan item : hoaDonService.findKemThanhToanByMaKH(maKH)) {
            tong += item.getThanhToan().getConNo();
        }
        return tong;
    }
}
