package mapper;

import entity.ChiNhanh;
import entity.DichVu;
import entity.DonVi;
import entity.HoaDon;
import entity.KeHoachDieuTri;
import entity.KhachHang;
import entity.LoaiRang;
import entity.LoaiSanPham;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.NhomDichVu;
import entity.NhomKhachHang;
import entity.PhieuNhapKho;
import entity.PhieuXuatKho;
import entity.Rang;
import entity.SanPham;
import entity.Thuoc;
import entity.ToaThuoc;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Maps {

    private Maps() {
    }

    public static KhachHang khachHang(ResultSet rs, String maCol, String tenCol, String sdtCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        KhachHang entity = new KhachHang();
        entity.setMaKH(ma);
        if (tenCol != null) {
            entity.setHoTen(rs.getString(tenCol));
        }
        if (sdtCol != null) {
            entity.setSoDienThoai(rs.getString(sdtCol));
        }
        return entity;
    }

    public static NhanVien nhanVien(ResultSet rs, String maCol, String tenCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        NhanVien entity = new NhanVien();
        entity.setMaNhanVien(ma);
        if (tenCol != null) {
            entity.setHoTen(rs.getString(tenCol));
        }
        return entity;
    }

    public static ChiNhanh chiNhanh(ResultSet rs, String maCol, String tenCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        ChiNhanh entity = new ChiNhanh();
        entity.setMaChiNhanh(ma);
        if (tenCol != null) {
            entity.setTenChiNhanh(rs.getString(tenCol));
        }
        return entity;
    }

    public static NhomKhachHang nhomKhach(ResultSet rs, String maCol, String tenCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        NhomKhachHang entity = new NhomKhachHang();
        entity.setMaNhom(ma);
        if (tenCol != null) {
            entity.setTenNhom(rs.getString(tenCol));
        }
        return entity;
    }

    public static NhomDichVu nhomDichVu(ResultSet rs, String maCol, String tenCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        NhomDichVu entity = new NhomDichVu();
        entity.setMaNhom(ma);
        if (tenCol != null) {
            entity.setTenNhomDichVu(rs.getString(tenCol));
        }
        return entity;
    }

    public static DichVu dichVu(ResultSet rs, String maCol, String tenCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        DichVu entity = new DichVu();
        entity.setMaDichVu(ma);
        if (tenCol != null) {
            entity.setTenDichVu(rs.getString(tenCol));
        }
        return entity;
    }

    public static Rang rang(ResultSet rs, String maCol, String tenCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        Rang entity = new Rang();
        entity.setMaRang(ma);
        if (tenCol != null) {
            entity.setTenRang(rs.getString(tenCol));
        }
        return entity;
    }

    public static LoaiRang loaiRang(ResultSet rs, String maCol, String tenCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        LoaiRang entity = new LoaiRang();
        entity.setMaLoaiRang(ma);
        if (tenCol != null) {
            entity.setTenLoaiRang(rs.getString(tenCol));
        }
        return entity;
    }

    public static KeHoachDieuTri keHoach(ResultSet rs, String maCol, String lyDoCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        KeHoachDieuTri entity = new KeHoachDieuTri();
        entity.setMaKeHoach(ma);
        if (lyDoCol != null) {
            entity.setLyDoDieuTri(rs.getString(lyDoCol));
        }
        return entity;
    }

    public static HoaDon hoaDon(ResultSet rs, String maCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        HoaDon entity = new HoaDon();
        entity.setMaHoaDon(ma);
        return entity;
    }

    public static Thuoc thuoc(ResultSet rs, String maCol, String tenCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        Thuoc entity = new Thuoc();
        entity.setMaThuoc(ma);
        if (tenCol != null) {
            entity.setTenThuoc(rs.getString(tenCol));
        }
        return entity;
    }

    public static DonVi donVi(ResultSet rs, String maCol, String tenCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        DonVi entity = new DonVi();
        entity.setMaDonVi(ma);
        if (tenCol != null) {
            entity.setTenDonVi(rs.getString(tenCol));
        }
        return entity;
    }

    public static SanPham sanPham(ResultSet rs, String maCol, String tenCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        SanPham entity = new SanPham();
        entity.setMaSanPham(ma);
        if (tenCol != null) {
            entity.setTenSanPham(rs.getString(tenCol));
        }
        return entity;
    }

    public static LoaiSanPham loaiSanPham(ResultSet rs, String maCol, String tenCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        LoaiSanPham entity = new LoaiSanPham();
        entity.setMaLoai(ma);
        if (tenCol != null) {
            entity.setTenLoaiSanPham(rs.getString(tenCol));
        }
        return entity;
    }

    public static NhaCungCap nhaCungCap(ResultSet rs, String maCol, String tenCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        NhaCungCap entity = new NhaCungCap();
        entity.setMaNhaCungCap(ma);
        if (tenCol != null) {
            entity.setTenNhaCungCap(rs.getString(tenCol));
        }
        return entity;
    }

    public static ToaThuoc toaThuoc(ResultSet rs, String maCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        ToaThuoc entity = new ToaThuoc();
        entity.setMaToaThuoc(ma);
        return entity;
    }

    public static PhieuNhapKho phieuNhap(ResultSet rs, String maCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        PhieuNhapKho entity = new PhieuNhapKho();
        entity.setMaPhieuNhap(ma);
        return entity;
    }

    public static PhieuXuatKho phieuXuat(ResultSet rs, String maCol) throws SQLException {
        String ma = rs.getString(maCol);
        if (ma == null) {
            return null;
        }
        PhieuXuatKho entity = new PhieuXuatKho();
        entity.setMaPhieuXuat(ma);
        return entity;
    }
}
