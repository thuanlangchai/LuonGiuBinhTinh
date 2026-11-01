package app;

import entity.DichVu;
import entity.KhachHang;
import entity.TaiKhoan;
import java.sql.Connection;
import java.util.List;
import repository.impl.AbstractDAO;
import repository.impl.DichVu_DAO;
import repository.impl.KhachHang_DAO;
import repository.impl.TaiKhoan_DAO;

/** Smoke test JDBC + DAO. Chạy tạm thời trong Giai đoạn 1, không dùng cho UI. */
public class DbSmokeTest {

    public static void main(String[] args) {
        Connection conn = AbstractDAO.getConnection();
        if (conn == null) {
            System.out.println("FAIL: khong ket noi duoc SQL Server");
            return;
        }
        try {
            System.out.println("OK: connected -> " + conn.getMetaData().getURL());
            conn.close();
        } catch (Exception e) {
            System.out.println("FAIL: " + e.getMessage());
            return;
        }

        TaiKhoan_DAO tkDao = new TaiKhoan_DAO();
        List<TaiKhoan> tks = tkDao.findAllTaiKhoan();
        System.out.println("TaiKhoan count=" + (tks == null ? "null" : tks.size()));
        if (tks != null) {
            for (TaiKhoan tk : tks) {
                System.out.println("  - " + tk.getTenDangNhap() + " / " + tk.getMatKhau() + " / " + tk.getVaiTro());
            }
        }

        KhachHang_DAO khDao = new KhachHang_DAO();
        List<KhachHang> khs = khDao.findAllKhachHang();
        System.out.println("KhachHang count=" + (khs == null ? "null" : khs.size()));
        if (khs != null && !khs.isEmpty()) {
            KhachHang kh = khs.get(0);
            System.out.println("  sample: " + kh.getMaKH() + " - " + kh.getHoTen());
        }

        DichVu_DAO dvDao = new DichVu_DAO();
        List<DichVu> dvs = dvDao.findAllDichVu();
        System.out.println("DichVu count=" + (dvs == null ? "null" : dvs.size()));

        TaiKhoan login = tkDao.findByTenDangNhap("thuan080");
        System.out.println("findByTenDangNhap(thuan080)=" + (login == null ? "null" : login.getMatKhau()));

        repository.impl.KeHoachDieuTri_DAO khdtDao = new repository.impl.KeHoachDieuTri_DAO();
        System.out.println("KeHoachDieuTri count=" + size(khdtDao.findAllKeHoachDieuTri()));
        System.out.println("KeHoach by KH 0001=" + size(khdtDao.findByMaKH("0001")));

        repository.impl.HoaDon_DAO hdDao = new repository.impl.HoaDon_DAO();
        System.out.println("HoaDon count=" + size(hdDao.findAllHoaDon()));

        repository.impl.LichHen_DAO lhDao = new repository.impl.LichHen_DAO();
        System.out.println("LichHen count=" + size(lhDao.findAllLichHen()));

        repository.impl.ToaThuoc_DAO ttDao = new repository.impl.ToaThuoc_DAO();
        System.out.println("ToaThuoc count=" + size(ttDao.findAllToaThuoc()));

        // Smoke insert/delete DichVu (không đụng dữ liệu seed)
        DichVu thu = new DichVu("DV99", "ND01", "Smoke test DV", 1000L, "Tạm");
        Integer rows = dvDao.add(thu);
        DichVu doc = dvDao.findById("DV99");
        System.out.println("DichVu add rows=" + rows + ", findById=" + (doc == null ? "null" : doc.getTenDichVu()));
        dvDao.delete("DV99");
        System.out.println("DichVu after delete=" + (dvDao.findById("DV99") == null ? "deleted" : "still exists"));
    }

    private static int size(List<?> list) {
        return list == null ? -1 : list.size();
    }
}
