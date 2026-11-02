package service.impl;

import entity.ChiTietKeHoachDieuTri;
import entity.HoaDon;
import entity.KeHoachDieuTri;
import entity.LichHen;
import entity.PhieuChi;
import entity.PhieuThu;
import entity.ToaThuoc;
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
    private final LichHen_DAO lichHenDao = new LichHen_DAO();
    private final KeHoachDieuTri_DAO keHoachDao = new KeHoachDieuTri_DAO();
    private final ChiTietKeHoachDieuTri_DAO chiTietKhDao = new ChiTietKeHoachDieuTri_DAO();

    @Override
    public List<ToaThuoc> baoCaoToaThuoc() {
        return Lists.orEmpty(toaDao.findAllToaThuoc());
    }

    @Override
    public List<PhieuThu> danhSachPhieuThu() {
        return Lists.orEmpty(thuDao.findAllPhieuThu());
    }

    @Override
    public List<PhieuChi> danhSachPhieuChi() {
        return Lists.orEmpty(chiDao.findAllPhieuChi());
    }

    @Override
    public List<HoaDon> danhSachHoaDon() {
        return Lists.orEmpty(hoaDonDao.findAllHoaDon());
    }

    @Override
    public List<LichHen> danhSachLichHen() {
        return Lists.orEmpty(lichHenDao.findAllLichHen());
    }

    @Override
    public List<KeHoachDieuTri> danhSachKeHoach() {
        return Lists.orEmpty(keHoachDao.findAllKeHoachDieuTri());
    }

    @Override
    public List<ChiTietKeHoachDieuTri> chiTietTheoKeHoach(String maKeHoach) {
        return Lists.orEmpty(chiTietKhDao.findByMaKeHoach(maKeHoach));
    }

    @Override
    public long tongDaThuTheoKhach(String maKH) {
        long tong = 0;
        for (HoaDon hd : hoaDonDao.findByMaKH(maKH) == null ? List.<HoaDon>of() : hoaDonDao.findByMaKH(maKH)) {
            tong += hd.getDaThu();
        }
        return tong;
    }

    @Override
    public long tongConNoTheoKhach(String maKH) {
        long tong = 0;
        for (HoaDon hd : hoaDonDao.findByMaKH(maKH) == null ? List.<HoaDon>of() : hoaDonDao.findByMaKH(maKH)) {
            tong += hd.getConNo();
        }
        return tong;
    }
}
