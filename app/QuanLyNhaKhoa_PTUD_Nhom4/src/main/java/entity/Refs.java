package entity;

import java.util.ArrayList;
import java.util.List;

public final class Refs {

    private Refs() {
    }

    public static String text(String value) {
        return value == null ? "" : value;
    }

    public static boolean blank(String value) {
        return value == null || value.isBlank();
    }

    public static KhachHang khachHang(String ma) {
        if (blank(ma)) {
            return null;
        }
        KhachHang entity = new KhachHang();
        entity.setMaKH(ma.trim());
        return entity;
    }

    public static String maKh(KhachHang entity) {
        return entity == null ? null : entity.getMaKH();
    }

    public static String tenKh(KhachHang entity) {
        if (entity == null || entity.getHoTen() == null) {
            return text(maKh(entity));
        }
        return entity.getHoTen();
    }

    public static NhanVien nhanVien(String ma) {
        if (blank(ma)) {
            return null;
        }
        NhanVien entity = new NhanVien();
        entity.setMaNhanVien(ma.trim());
        return entity;
    }

    public static String maNv(NhanVien entity) {
        return entity == null ? null : entity.getMaNhanVien();
    }

    public static String tenNv(NhanVien entity) {
        if (entity == null || entity.getHoTen() == null) {
            return text(maNv(entity));
        }
        return entity.getHoTen();
    }

    public static ChiNhanh chiNhanh(String ma) {
        if (blank(ma)) {
            return null;
        }
        ChiNhanh entity = new ChiNhanh();
        entity.setMaChiNhanh(ma.trim());
        return entity;
    }

    public static String maCn(ChiNhanh entity) {
        return entity == null ? null : entity.getMaChiNhanh();
    }

    public static NhomKhachHang nhomKhach(String ma) {
        if (blank(ma)) {
            return null;
        }
        NhomKhachHang entity = new NhomKhachHang();
        entity.setMaNhom(ma.trim());
        return entity;
    }

    public static String maNhomKh(NhomKhachHang entity) {
        return entity == null ? null : entity.getMaNhom();
    }

    public static NhomDichVu nhomDichVu(String ma) {
        if (blank(ma)) {
            return null;
        }
        NhomDichVu entity = new NhomDichVu();
        entity.setMaNhom(ma.trim());
        return entity;
    }

    public static String maNhomDv(NhomDichVu entity) {
        return entity == null ? null : entity.getMaNhom();
    }

    public static DichVu dichVu(String ma) {
        if (blank(ma)) {
            return null;
        }
        DichVu entity = new DichVu();
        entity.setMaDichVu(ma.trim());
        return entity;
    }

    public static String maDv(DichVu entity) {
        return entity == null ? null : entity.getMaDichVu();
    }

    public static String tenDv(DichVu entity) {
        if (entity == null || entity.getTenDichVu() == null) {
            return text(maDv(entity));
        }
        return entity.getTenDichVu();
    }

    public static Rang rang(String ma) {
        if (blank(ma)) {
            return null;
        }
        Rang entity = new Rang();
        entity.setMaRang(ma.trim());
        return entity;
    }

    public static String maRang(Rang entity) {
        return entity == null ? null : entity.getMaRang();
    }

    public static String tenRang(Rang entity) {
        if (entity == null || entity.getTenRang() == null) {
            return text(maRang(entity));
        }
        return entity.getTenRang();
    }

    public static LoaiRang loaiRang(String ma) {
        if (blank(ma)) {
            return null;
        }
        LoaiRang entity = new LoaiRang();
        entity.setMaLoaiRang(ma.trim());
        return entity;
    }

    public static String maLoaiRang(LoaiRang entity) {
        return entity == null ? null : entity.getMaLoaiRang();
    }

    public static KeHoachDieuTri keHoach(String ma) {
        if (blank(ma)) {
            return null;
        }
        KeHoachDieuTri entity = new KeHoachDieuTri();
        entity.setMaKeHoach(ma.trim());
        return entity;
    }

    public static String maKhdt(KeHoachDieuTri entity) {
        return entity == null ? null : entity.getMaKeHoach();
    }

    public static HoaDon hoaDon(String ma) {
        if (blank(ma)) {
            return null;
        }
        HoaDon entity = new HoaDon();
        entity.setMaHoaDon(ma.trim());
        return entity;
    }

    public static String maHd(HoaDon entity) {
        return entity == null ? null : entity.getMaHoaDon();
    }

    public static Thuoc thuoc(String ma) {
        if (blank(ma)) {
            return null;
        }
        Thuoc entity = new Thuoc();
        entity.setMaThuoc(ma.trim());
        return entity;
    }

    public static String maThuoc(Thuoc entity) {
        return entity == null ? null : entity.getMaThuoc();
    }

    public static String tenThuoc(Thuoc entity) {
        if (entity == null || entity.getTenThuoc() == null) {
            return text(maThuoc(entity));
        }
        return entity.getTenThuoc();
    }

    public static DonVi donVi(String ma) {
        if (blank(ma)) {
            return null;
        }
        DonVi entity = new DonVi();
        entity.setMaDonVi(ma.trim());
        return entity;
    }

    public static String maDonVi(DonVi entity) {
        return entity == null ? null : entity.getMaDonVi();
    }

    public static SanPham sanPham(String ma) {
        if (blank(ma)) {
            return null;
        }
        SanPham entity = new SanPham();
        entity.setMaSanPham(ma.trim());
        return entity;
    }

    public static String maSp(SanPham entity) {
        return entity == null ? null : entity.getMaSanPham();
    }

    public static LoaiSanPham loaiSanPham(String ma) {
        if (blank(ma)) {
            return null;
        }
        LoaiSanPham entity = new LoaiSanPham();
        entity.setMaLoai(ma.trim());
        return entity;
    }

    public static String maLoaiSp(LoaiSanPham entity) {
        return entity == null ? null : entity.getMaLoai();
    }

    public static NhaCungCap nhaCungCap(String ma) {
        if (blank(ma)) {
            return null;
        }
        NhaCungCap entity = new NhaCungCap();
        entity.setMaNhaCungCap(ma.trim());
        return entity;
    }

    public static String maNcc(NhaCungCap entity) {
        return entity == null ? null : entity.getMaNhaCungCap();
    }

    public static String tenNcc(NhaCungCap entity) {
        if (entity == null || entity.getTenNhaCungCap() == null) {
            return text(maNcc(entity));
        }
        return entity.getTenNhaCungCap();
    }

    public static ToaThuoc toaThuoc(String ma) {
        if (blank(ma)) {
            return null;
        }
        ToaThuoc entity = new ToaThuoc();
        entity.setMaToaThuoc(ma.trim());
        return entity;
    }

    public static String maToa(ToaThuoc entity) {
        return entity == null ? null : entity.getMaToaThuoc();
    }

    public static PhieuNhapKho phieuNhap(String ma) {
        if (blank(ma)) {
            return null;
        }
        PhieuNhapKho entity = new PhieuNhapKho();
        entity.setMaPhieuNhap(ma.trim());
        return entity;
    }

    public static PhieuXuatKho phieuXuat(String ma) {
        if (blank(ma)) {
            return null;
        }
        PhieuXuatKho entity = new PhieuXuatKho();
        entity.setMaPhieuXuat(ma.trim());
        return entity;
    }

    public static List<String> ids(List<String> source) {
        List<String> ids = new ArrayList<>();
        if (source == null) {
            return ids;
        }
        for (String id : source) {
            if (!blank(id)) {
                ids.add(id);
            }
        }
        return ids;
    }
}
