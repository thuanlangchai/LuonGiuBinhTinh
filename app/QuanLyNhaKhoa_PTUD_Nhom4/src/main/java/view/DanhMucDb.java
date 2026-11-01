package view;

import entity.ChiNhanh;
import entity.DichVu;
import entity.DieuTri;
import entity.DonVi;
import entity.LoaiRang;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.NhomDichVu;
import entity.NhomKhachHang;
import entity.Rang;
import entity.SanPham;
import entity.ThoiGianLamViec;
import entity.Thuoc;
import java.util.ArrayList;
import java.util.List;
import service.impl.DanhMuc_Service;
import view.model.CauHinhTrang;

/** Nạp CauHinhTrang từ database, giữ cấu trúc cột của DuLieuMau. */
public final class DanhMucDb {

    private static final DanhMuc_Service SVC = new DanhMuc_Service();

    private DanhMucDb() {
    }

    public static CauHinhTrang lay(String ma) {
        CauHinhTrang mau = DuLieuMau.lay(ma);
        if (mau == null) {
            return null;
        }
        Object[][] duLieu = napDuLieu(ma);
        return new CauHinhTrang(mau.ma, mau.tieuDe, mau.goiY, mau.nutThem, mau.cot, duLieu, mau.ghiChu);
    }

    public static boolean xoa(String maTrang, Object[] dong) {
        if (dong == null || dong.length == 0) {
            return false;
        }
        String id = String.valueOf(dong[0]);
        switch (maTrang) {
            case "dichvu" -> SVC.deleteDichVu(id);
            case "nhomdichvu" -> SVC.deleteNhomDichVu(id);
            case "nhanvien" -> SVC.deleteNhanVien(id);
            case "rang" -> SVC.deleteRang(id);
            case "loairang" -> SVC.deleteLoaiRang(id);
            case "dieutri" -> SVC.deleteDieuTri(id);
            case "thoigian" -> SVC.deleteThoiGianLamViec(id);
            case "loaisanpham" -> SVC.deleteLoaiSanPham(id);
            case "thuoc" -> SVC.deleteThuoc(id);
            case "sanpham" -> SVC.deleteSanPham(id);
            case "chinhanh" -> SVC.deleteChiNhanh(id);
            case "nhacungcap" -> SVC.deleteNhaCungCap(id);
            case "nhomkhach" -> SVC.deleteNhomKhachHang(id);
            case "donvi" -> SVC.deleteDonVi(id);
            default -> {
                return false;
            }
        }
        return true;
    }

    public static boolean luu(String maTrang, Object[] dong, boolean them) {
        if (dong == null || dong.length == 0) {
            return false;
        }
        try {
            switch (maTrang) {
                case "dichvu" -> {
                    DichVu e = new DichVu(str(dong, 0), maNhomTheoTen(str(dong, 1)), str(dong, 2), parseTien(str(dong, 3)), str(dong, 4));
                    if (them) {
                        return SVC.addDichVu(e) != null;
                    }
                    SVC.updateDichVu(e);
                    return true;
                }
                case "nhomdichvu" -> {
                    NhomDichVu e = new NhomDichVu(str(dong, 0), str(dong, 1), str(dong, 2));
                    if (them) {
                        return SVC.addNhomDichVu(e) != null;
                    }
                    SVC.updateNhomDichVu(e);
                    return true;
                }
                case "nhanvien" -> {
                    NhanVien e = new NhanVien(str(dong, 0), str(dong, 1), str(dong, 2), str(dong, 3), str(dong, 4),
                            str(dong, 5), "Có".equalsIgnoreCase(str(dong, 6)) || "true".equalsIgnoreCase(str(dong, 6)), str(dong, 7));
                    if (them) {
                        return SVC.addNhanVien(e) != null;
                    }
                    SVC.updateNhanVien(e);
                    return true;
                }
                case "rang" -> {
                    Rang e = new Rang(str(dong, 0), str(dong, 1), str(dong, 2));
                    if (them) {
                        return SVC.addRang(e) != null;
                    }
                    SVC.updateRang(e);
                    return true;
                }
                case "loairang" -> {
                    LoaiRang e = new LoaiRang(str(dong, 0), str(dong, 1));
                    if (them) {
                        return SVC.addLoaiRang(e) != null;
                    }
                    SVC.updateLoaiRang(e);
                    return true;
                }
                case "dieutri" -> {
                    DieuTri e = new DieuTri(str(dong, 0), str(dong, 1));
                    if (them) {
                        return SVC.addDieuTri(e) != null;
                    }
                    SVC.updateDieuTri(e);
                    return true;
                }
                case "thuoc" -> {
                    Thuoc e = new Thuoc(str(dong, 0), str(dong, 1), str(dong, 2), parseTien(str(dong, 3)), dong.length > 4 ? str(dong, 4) : "");
                    if (them) {
                        return SVC.addThuoc(e) != null;
                    }
                    SVC.updateThuoc(e);
                    return true;
                }
                case "sanpham" -> {
                    SanPham e = new SanPham(str(dong, 0), str(dong, 1), str(dong, 2), parseInt(str(dong, 3)), parseTien(str(dong, 4)));
                    if (them) {
                        return SVC.addSanPham(e) != null;
                    }
                    SVC.updateSanPham(e);
                    return true;
                }
                case "chinhanh" -> {
                    ChiNhanh e = new ChiNhanh(str(dong, 0), str(dong, 1), dong.length > 2 ? str(dong, 2) : "", dong.length > 3 ? str(dong, 3) : "");
                    if (them) {
                        return SVC.addChiNhanh(e) != null;
                    }
                    SVC.updateChiNhanh(e);
                    return true;
                }
                case "nhacungcap" -> {
                    NhaCungCap e = new NhaCungCap(str(dong, 0), str(dong, 1), dong.length > 2 ? str(dong, 2) : "", dong.length > 3 ? str(dong, 3) : "");
                    if (them) {
                        return SVC.addNhaCungCap(e) != null;
                    }
                    SVC.updateNhaCungCap(e);
                    return true;
                }
                case "nhomkhach" -> {
                    NhomKhachHang e = new NhomKhachHang(str(dong, 0), str(dong, 1));
                    if (them) {
                        return SVC.addNhomKhachHang(e) != null;
                    }
                    SVC.updateNhomKhachHang(e);
                    return true;
                }
                case "donvi" -> {
                    DonVi e = new DonVi(str(dong, 0), str(dong, 1));
                    if (them) {
                        return SVC.addDonVi(e) != null;
                    }
                    SVC.updateDonVi(e);
                    return true;
                }
                case "loaisanpham" -> {
                    LoaiSanPham e = new LoaiSanPham(str(dong, 0), str(dong, 1));
                    if (them) {
                        return SVC.addLoaiSanPham(e) != null;
                    }
                    SVC.updateLoaiSanPham(e);
                    return true;
                }
                default -> {
                    return false;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    private static Object[][] napDuLieu(String ma) {
        List<Object[]> ds = new ArrayList<>();
        switch (ma) {
            case "dichvu" -> {
                for (DichVu e : SVC.findAllDichVu()) {
                    ds.add(new Object[]{e.getMaDichVu(), tenNhom(e.getMaNhomDichVu()), e.getTenDichVu(),
                        ViewTheme.tien(e.getGia()), e.getTrangThai()});
                }
            }
            case "nhomdichvu" -> {
                for (NhomDichVu e : SVC.findAllNhomDichVu()) {
                    ds.add(new Object[]{e.getMaNhom(), e.getTenNhomDichVu(), e.getMoTa() == null ? "" : e.getMoTa()});
                }
            }
            case "nhanvien" -> {
                for (NhanVien e : SVC.findAllNhanVien()) {
                    ds.add(new Object[]{e.getMaNhanVien(), e.getChucDanh(), e.getHoTen(), e.getEmail(),
                        e.getSoDienThoai(), e.getVaiTro(), e.isKichHoat() ? "Có" : "Không", e.getMaChiNhanh()});
                }
            }
            case "rang" -> {
                for (Rang e : SVC.findAllRang()) {
                    ds.add(new Object[]{e.getMaRang(), e.getTenRang(), e.getMaLoaiRang()});
                }
            }
            case "loairang" -> {
                for (LoaiRang e : SVC.findAllLoaiRang()) {
                    ds.add(new Object[]{e.getMaLoaiRang(), e.getTenLoaiRang()});
                }
            }
            case "dieutri" -> {
                for (DieuTri e : SVC.findAllDieuTri()) {
                    ds.add(new Object[]{e.getMaDieuTri(), e.getTenDieuTri()});
                }
            }
            case "thoigian" -> {
                for (ThoiGianLamViec e : SVC.findAllThoiGianLamViec()) {
                    ds.add(new Object[]{e.getMaLich(), e.getNgay(),
                        e.getGioBatDau() == null ? "" : e.getGioBatDau().toString(),
                        e.getGioKetThuc() == null ? "" : e.getGioKetThuc().toString(),
                        e.getMaBacSi(), e.getMaChiNhanh()});
                }
            }
            case "loaisanpham" -> {
                for (LoaiSanPham e : SVC.findAllLoaiSanPham()) {
                    ds.add(new Object[]{e.getMaLoai(), e.getTenLoaiSanPham()});
                }
            }
            case "thuoc" -> {
                for (Thuoc e : SVC.findAllThuoc()) {
                    ds.add(new Object[]{e.getMaThuoc(), e.getTenThuoc(), e.getMaDonVi(), ViewTheme.tien(e.getGia()),
                        e.getCachDung() == null ? "" : e.getCachDung()});
                }
            }
            case "sanpham" -> {
                for (SanPham e : SVC.findAllSanPham()) {
                    ds.add(new Object[]{e.getMaSanPham(), e.getTenSanPham(), e.getMaLoai(),
                        String.valueOf(e.getSoLuong()), ViewTheme.tien(e.getDonGia())});
                }
            }
            case "chinhanh" -> {
                for (ChiNhanh e : SVC.findAllChiNhanh()) {
                    ds.add(new Object[]{e.getMaChiNhanh(), e.getTenChiNhanh(), e.getDiaChi(), e.getDienThoai()});
                }
            }
            case "nhacungcap" -> {
                for (NhaCungCap e : SVC.findAllNhaCungCap()) {
                    ds.add(new Object[]{e.getMaNhaCungCap(), e.getTenNhaCungCap(), e.getDienThoai(), e.getDiaChi()});
                }
            }
            case "nhomkhach" -> {
                for (NhomKhachHang e : SVC.findAllNhomKhachHang()) {
                    ds.add(new Object[]{e.getMaNhom(), e.getTenNhom()});
                }
            }
            case "donvi" -> {
                for (DonVi e : SVC.findAllDonVi()) {
                    ds.add(new Object[]{e.getMaDonVi(), e.getTenDonVi()});
                }
            }
            default -> {
                CauHinhTrang mau = DuLieuMau.lay(ma);
                return mau == null ? new Object[0][] : mau.duLieu;
            }
        }
        return ds.toArray(new Object[0][]);
    }

    private static String tenNhom(String ma) {
        for (NhomDichVu n : SVC.findAllNhomDichVu()) {
            if (n.getMaNhom().equals(ma)) {
                return n.getTenNhomDichVu();
            }
        }
        return ma;
    }

    private static String maNhomTheoTen(String ten) {
        for (NhomDichVu n : SVC.findAllNhomDichVu()) {
            if (n.getTenNhomDichVu().equals(ten) || n.getMaNhom().equals(ten)) {
                return n.getMaNhom();
            }
        }
        return ten;
    }

    private static String str(Object[] dong, int i) {
        if (i >= dong.length || dong[i] == null) {
            return "";
        }
        return String.valueOf(dong[i]).trim();
    }

    private static long parseTien(String s) {
        String so = s == null ? "" : s.replaceAll("[^0-9]", "");
        if (so.isEmpty()) {
            return 0;
        }
        try {
            return Long.parseLong(so);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static int parseInt(String s) {
        try {
            return Integer.parseInt(s.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return 0;
        }
    }
}
