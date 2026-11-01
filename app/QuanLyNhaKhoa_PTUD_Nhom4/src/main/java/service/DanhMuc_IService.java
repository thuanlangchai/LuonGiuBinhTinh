package service;

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
import java.util.List;

/** Facade danh mục phục vụ DanhMucPanel / TrangDanhSach / combo form. */
public interface DanhMuc_IService {

    List<DichVu> findAllDichVu();

    DichVu findDichVuById(String ma);

    Integer addDichVu(DichVu e);

    void updateDichVu(DichVu e);

    void deleteDichVu(String ma);

    List<NhomDichVu> findAllNhomDichVu();

    Integer addNhomDichVu(NhomDichVu e);

    void updateNhomDichVu(NhomDichVu e);

    void deleteNhomDichVu(String ma);

    List<NhanVien> findAllNhanVien();

    NhanVien findNhanVienById(String ma);

    Integer addNhanVien(NhanVien e);

    void updateNhanVien(NhanVien e);

    void deleteNhanVien(String ma);

    List<Rang> findAllRang();

    Integer addRang(Rang e);

    void updateRang(Rang e);

    void deleteRang(String ma);

    List<LoaiRang> findAllLoaiRang();

    Integer addLoaiRang(LoaiRang e);

    void updateLoaiRang(LoaiRang e);

    void deleteLoaiRang(String ma);

    List<DieuTri> findAllDieuTri();

    Integer addDieuTri(DieuTri e);

    void updateDieuTri(DieuTri e);

    void deleteDieuTri(String ma);

    List<ThoiGianLamViec> findAllThoiGianLamViec();

    Integer addThoiGianLamViec(ThoiGianLamViec e);

    void updateThoiGianLamViec(ThoiGianLamViec e);

    void deleteThoiGianLamViec(String ma);

    List<LoaiSanPham> findAllLoaiSanPham();

    Integer addLoaiSanPham(LoaiSanPham e);

    void updateLoaiSanPham(LoaiSanPham e);

    void deleteLoaiSanPham(String ma);

    List<Thuoc> findAllThuoc();

    Thuoc findThuocById(String ma);

    Integer addThuoc(Thuoc e);

    void updateThuoc(Thuoc e);

    void deleteThuoc(String ma);

    List<SanPham> findAllSanPham();

    SanPham findSanPhamById(String ma);

    Integer addSanPham(SanPham e);

    void updateSanPham(SanPham e);

    void deleteSanPham(String ma);

    List<ChiNhanh> findAllChiNhanh();

    Integer addChiNhanh(ChiNhanh e);

    void updateChiNhanh(ChiNhanh e);

    void deleteChiNhanh(String ma);

    List<NhaCungCap> findAllNhaCungCap();

    Integer addNhaCungCap(NhaCungCap e);

    void updateNhaCungCap(NhaCungCap e);

    void deleteNhaCungCap(String ma);

    List<NhomKhachHang> findAllNhomKhachHang();

    Integer addNhomKhachHang(NhomKhachHang e);

    void updateNhomKhachHang(NhomKhachHang e);

    void deleteNhomKhachHang(String ma);

    List<DonVi> findAllDonVi();

    Integer addDonVi(DonVi e);

    void updateDonVi(DonVi e);

    void deleteDonVi(String ma);
}
