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
                    DichVu dichVu = new DichVu(str(dong, 0), maNhomTheoTen(str(dong, 1)), str(dong, 2), parseTien(str(dong, 3)), str(dong, 4));
                    if (them) {
                        return SVC.addDichVu(dichVu) != null;
                    }
                    SVC.updateDichVu(dichVu);
                    return true;
                }
                case "nhomdichvu" -> {
                    NhomDichVu nhomDichVu = new NhomDichVu(str(dong, 0), str(dong, 1), str(dong, 2));
                    if (them) {
                        return SVC.addNhomDichVu(nhomDichVu) != null;
                    }
                    SVC.updateNhomDichVu(nhomDichVu);
                    return true;
                }
                case "nhanvien" -> {
                    NhanVien nhanVien = new NhanVien(str(dong, 0), str(dong, 1), str(dong, 2), str(dong, 3), str(dong, 4),
                            str(dong, 5), "Có".equalsIgnoreCase(str(dong, 6)) || "true".equalsIgnoreCase(str(dong, 6)), str(dong, 7));
                    if (them) {
                        return SVC.addNhanVien(nhanVien) != null;
                    }
                    SVC.updateNhanVien(nhanVien);
                    return true;
                }
                case "rang" -> {
                    Rang rang = new Rang(str(dong, 0), str(dong, 1), str(dong, 2));
                    if (them) {
                        return SVC.addRang(rang) != null;
                    }
                    SVC.updateRang(rang);
                    return true;
                }
                case "loairang" -> {
                    LoaiRang loaiRang = new LoaiRang(str(dong, 0), str(dong, 1));
                    if (them) {
                        return SVC.addLoaiRang(loaiRang) != null;
                    }
                    SVC.updateLoaiRang(loaiRang);
                    return true;
                }
                case "dieutri" -> {
                    DieuTri dieuTri = new DieuTri(str(dong, 0), str(dong, 1));
                    if (them) {
                        return SVC.addDieuTri(dieuTri) != null;
                    }
                    SVC.updateDieuTri(dieuTri);
                    return true;
                }
                case "thuoc" -> {
                    Thuoc thuoc = new Thuoc(str(dong, 0), str(dong, 1), str(dong, 2), parseTien(str(dong, 3)), dong.length > 4 ? str(dong, 4) : "");
                    if (them) {
                        return SVC.addThuoc(thuoc) != null;
                    }
                    SVC.updateThuoc(thuoc);
                    return true;
                }
                case "sanpham" -> {
                    SanPham sanPham = new SanPham(str(dong, 0), str(dong, 1), str(dong, 2), parseInt(str(dong, 3)), parseTien(str(dong, 4)));
                    if (them) {
                        return SVC.addSanPham(sanPham) != null;
                    }
                    SVC.updateSanPham(sanPham);
                    return true;
                }
                case "chinhanh" -> {
                    ChiNhanh chiNhanh = new ChiNhanh(str(dong, 0), str(dong, 1), dong.length > 2 ? str(dong, 2) : "", dong.length > 3 ? str(dong, 3) : "");
                    if (them) {
                        return SVC.addChiNhanh(chiNhanh) != null;
                    }
                    SVC.updateChiNhanh(chiNhanh);
                    return true;
                }
                case "nhacungcap" -> {
                    NhaCungCap nhaCungCap = new NhaCungCap(str(dong, 0), str(dong, 1), dong.length > 2 ? str(dong, 2) : "", dong.length > 3 ? str(dong, 3) : "");
                    if (them) {
                        return SVC.addNhaCungCap(nhaCungCap) != null;
                    }
                    SVC.updateNhaCungCap(nhaCungCap);
                    return true;
                }
                case "nhomkhach" -> {
                    NhomKhachHang nhomKhachHang = new NhomKhachHang(str(dong, 0), str(dong, 1));
                    if (them) {
                        return SVC.addNhomKhachHang(nhomKhachHang) != null;
                    }
                    SVC.updateNhomKhachHang(nhomKhachHang);
                    return true;
                }
                case "donvi" -> {
                    DonVi donVi = new DonVi(str(dong, 0), str(dong, 1));
                    if (them) {
                        return SVC.addDonVi(donVi) != null;
                    }
                    SVC.updateDonVi(donVi);
                    return true;
                }
                case "loaisanpham" -> {
                    LoaiSanPham loaiSanPham = new LoaiSanPham(str(dong, 0), str(dong, 1));
                    if (them) {
                        return SVC.addLoaiSanPham(loaiSanPham) != null;
                    }
                    SVC.updateLoaiSanPham(loaiSanPham);
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
        List<Object[]> danhSach = new ArrayList<>();
        switch (ma) {
            case "dichvu" -> {
                for (DichVu dichVu : SVC.findAllDichVu()) {
                    danhSach.add(new Object[]{dichVu.getMaDichVu(), tenNhom(dichVu.getMaNhomDichVu()), dichVu.getTenDichVu(),
                        ViewTheme.tien(dichVu.getGia()), dichVu.getTrangThai()});
                }
            }
            case "nhomdichvu" -> {
                for (NhomDichVu nhomDichVu : SVC.findAllNhomDichVu()) {
                    danhSach.add(new Object[]{nhomDichVu.getMaNhom(), nhomDichVu.getTenNhomDichVu(), nhomDichVu.getMoTa() == null ? "" : nhomDichVu.getMoTa()});
                }
            }
            case "nhanvien" -> {
                for (NhanVien nhanVien : SVC.findAllNhanVien()) {
                    danhSach.add(new Object[]{nhanVien.getMaNhanVien(), nhanVien.getChucDanh(), nhanVien.getHoTen(), nhanVien.getEmail(),
                        nhanVien.getSoDienThoai(), nhanVien.getVaiTro(), nhanVien.isKichHoat() ? "Có" : "Không", nhanVien.getMaChiNhanh()});
                }
            }
            case "rang" -> {
                for (Rang rang : SVC.findAllRang()) {
                    danhSach.add(new Object[]{rang.getMaRang(), rang.getTenRang(), rang.getMaLoaiRang()});
                }
            }
            case "loairang" -> {
                for (LoaiRang loaiRang : SVC.findAllLoaiRang()) {
                    danhSach.add(new Object[]{loaiRang.getMaLoaiRang(), loaiRang.getTenLoaiRang()});
                }
            }
            case "dieutri" -> {
                for (DieuTri dieuTri : SVC.findAllDieuTri()) {
                    danhSach.add(new Object[]{dieuTri.getMaDieuTri(), dieuTri.getTenDieuTri()});
                }
            }
            case "thoigian" -> {
                for (ThoiGianLamViec thoiGian : SVC.findAllThoiGianLamViec()) {
                    danhSach.add(new Object[]{thoiGian.getMaLich(), thoiGian.getNgay(),
                        thoiGian.getGioBatDau() == null ? "" : thoiGian.getGioBatDau().toString(),
                        thoiGian.getGioKetThuc() == null ? "" : thoiGian.getGioKetThuc().toString(),
                        thoiGian.getMaBacSi(), thoiGian.getMaChiNhanh()});
                }
            }
            case "loaisanpham" -> {
                for (LoaiSanPham loaiSanPham : SVC.findAllLoaiSanPham()) {
                    danhSach.add(new Object[]{loaiSanPham.getMaLoai(), loaiSanPham.getTenLoaiSanPham()});
                }
            }
            case "thuoc" -> {
                for (Thuoc thuoc : SVC.findAllThuoc()) {
                    danhSach.add(new Object[]{thuoc.getMaThuoc(), thuoc.getTenThuoc(), thuoc.getMaDonVi(), ViewTheme.tien(thuoc.getGia()),
                        thuoc.getCachDung() == null ? "" : thuoc.getCachDung()});
                }
            }
            case "sanpham" -> {
                for (SanPham sanPham : SVC.findAllSanPham()) {
                    danhSach.add(new Object[]{sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getMaLoai(),
                        String.valueOf(sanPham.getSoLuong()), ViewTheme.tien(sanPham.getDonGia())});
                }
            }
            case "chinhanh" -> {
                for (ChiNhanh chiNhanh : SVC.findAllChiNhanh()) {
                    danhSach.add(new Object[]{chiNhanh.getMaChiNhanh(), chiNhanh.getTenChiNhanh(), chiNhanh.getDiaChi(), chiNhanh.getDienThoai()});
                }
            }
            case "nhacungcap" -> {
                for (NhaCungCap nhaCungCap : SVC.findAllNhaCungCap()) {
                    danhSach.add(new Object[]{nhaCungCap.getMaNhaCungCap(), nhaCungCap.getTenNhaCungCap(), nhaCungCap.getDienThoai(), nhaCungCap.getDiaChi()});
                }
            }
            case "nhomkhach" -> {
                for (NhomKhachHang nhomKhachHang : SVC.findAllNhomKhachHang()) {
                    danhSach.add(new Object[]{nhomKhachHang.getMaNhom(), nhomKhachHang.getTenNhom()});
                }
            }
            case "donvi" -> {
                for (DonVi donVi : SVC.findAllDonVi()) {
                    danhSach.add(new Object[]{donVi.getMaDonVi(), donVi.getTenDonVi()});
                }
            }
            default -> {
                CauHinhTrang mau = DuLieuMau.lay(ma);
                return mau == null ? new Object[0][] : mau.duLieu;
            }
        }
        return danhSach.toArray(new Object[0][]);
    }

    private static String tenNhom(String ma) {
        for (NhomDichVu nhom : SVC.findAllNhomDichVu()) {
            if (nhom.getMaNhom().equals(ma)) {
                return nhom.getTenNhomDichVu();
            }
        }
        return ma;
    }

    private static String maNhomTheoTen(String ten) {
        for (NhomDichVu nhom : SVC.findAllNhomDichVu()) {
            if (nhom.getTenNhomDichVu().equals(ten) || nhom.getMaNhom().equals(ten)) {
                return nhom.getMaNhom();
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
