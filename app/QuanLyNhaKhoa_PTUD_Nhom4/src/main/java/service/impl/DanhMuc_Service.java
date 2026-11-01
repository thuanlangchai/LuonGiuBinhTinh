package service.impl;

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
import repository.impl.ChiNhanh_DAO;
import repository.impl.DichVu_DAO;
import repository.impl.DieuTri_DAO;
import repository.impl.DonVi_DAO;
import repository.impl.LoaiRang_DAO;
import repository.impl.LoaiSanPham_DAO;
import repository.impl.NhaCungCap_DAO;
import repository.impl.NhanVien_DAO;
import repository.impl.NhomDichVu_DAO;
import repository.impl.NhomKhachHang_DAO;
import repository.impl.Rang_DAO;
import repository.impl.SanPham_DAO;
import repository.impl.ThoiGianLamViec_DAO;
import repository.impl.Thuoc_DAO;
import service.DanhMuc_IService;

public class DanhMuc_Service implements DanhMuc_IService {

    private final DichVu_DAO dichVuDao = new DichVu_DAO();
    private final NhomDichVu_DAO nhomDvDao = new NhomDichVu_DAO();
    private final NhanVien_DAO nhanVienDao = new NhanVien_DAO();
    private final Rang_DAO rangDao = new Rang_DAO();
    private final LoaiRang_DAO loaiRangDao = new LoaiRang_DAO();
    private final DieuTri_DAO dieuTriDao = new DieuTri_DAO();
    private final ThoiGianLamViec_DAO thoiGianDao = new ThoiGianLamViec_DAO();
    private final LoaiSanPham_DAO loaiSpDao = new LoaiSanPham_DAO();
    private final Thuoc_DAO thuocDao = new Thuoc_DAO();
    private final SanPham_DAO sanPhamDao = new SanPham_DAO();
    private final ChiNhanh_DAO chiNhanhDao = new ChiNhanh_DAO();
    private final NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
    private final NhomKhachHang_DAO nhomKhDao = new NhomKhachHang_DAO();
    private final DonVi_DAO donViDao = new DonVi_DAO();

    @Override
    public List<DichVu> findAllDichVu() {
        return Lists.orEmpty(dichVuDao.findAllDichVu());
    }

    @Override
    public DichVu findDichVuById(String ma) {
        return dichVuDao.findById(ma);
    }

    @Override
    public Integer addDichVu(DichVu e) {
        return dichVuDao.add(e);
    }

    @Override
    public void updateDichVu(DichVu e) {
        dichVuDao.update(e);
    }

    @Override
    public void deleteDichVu(String ma) {
        dichVuDao.delete(ma);
    }

    @Override
    public List<NhomDichVu> findAllNhomDichVu() {
        return Lists.orEmpty(nhomDvDao.findAllNhomDichVu());
    }

    @Override
    public Integer addNhomDichVu(NhomDichVu e) {
        return nhomDvDao.add(e);
    }

    @Override
    public void updateNhomDichVu(NhomDichVu e) {
        nhomDvDao.update(e);
    }

    @Override
    public void deleteNhomDichVu(String ma) {
        nhomDvDao.delete(ma);
    }

    @Override
    public List<NhanVien> findAllNhanVien() {
        return Lists.orEmpty(nhanVienDao.findAllNhanVien());
    }

    @Override
    public NhanVien findNhanVienById(String ma) {
        return nhanVienDao.findById(ma);
    }

    @Override
    public Integer addNhanVien(NhanVien e) {
        return nhanVienDao.add(e);
    }

    @Override
    public void updateNhanVien(NhanVien e) {
        nhanVienDao.update(e);
    }

    @Override
    public void deleteNhanVien(String ma) {
        nhanVienDao.delete(ma);
    }

    @Override
    public List<Rang> findAllRang() {
        return Lists.orEmpty(rangDao.findAllRang());
    }

    @Override
    public Integer addRang(Rang e) {
        return rangDao.add(e);
    }

    @Override
    public void updateRang(Rang e) {
        rangDao.update(e);
    }

    @Override
    public void deleteRang(String ma) {
        rangDao.delete(ma);
    }

    @Override
    public List<LoaiRang> findAllLoaiRang() {
        return Lists.orEmpty(loaiRangDao.findAllLoaiRang());
    }

    @Override
    public Integer addLoaiRang(LoaiRang e) {
        return loaiRangDao.add(e);
    }

    @Override
    public void updateLoaiRang(LoaiRang e) {
        loaiRangDao.update(e);
    }

    @Override
    public void deleteLoaiRang(String ma) {
        loaiRangDao.delete(ma);
    }

    @Override
    public List<DieuTri> findAllDieuTri() {
        return Lists.orEmpty(dieuTriDao.findAllDieuTri());
    }

    @Override
    public Integer addDieuTri(DieuTri e) {
        return dieuTriDao.add(e);
    }

    @Override
    public void updateDieuTri(DieuTri e) {
        dieuTriDao.update(e);
    }

    @Override
    public void deleteDieuTri(String ma) {
        dieuTriDao.delete(ma);
    }

    @Override
    public List<ThoiGianLamViec> findAllThoiGianLamViec() {
        return Lists.orEmpty(thoiGianDao.findAllThoiGianLamViec());
    }

    @Override
    public Integer addThoiGianLamViec(ThoiGianLamViec e) {
        return thoiGianDao.add(e);
    }

    @Override
    public void updateThoiGianLamViec(ThoiGianLamViec e) {
        thoiGianDao.update(e);
    }

    @Override
    public void deleteThoiGianLamViec(String ma) {
        thoiGianDao.delete(ma);
    }

    @Override
    public List<LoaiSanPham> findAllLoaiSanPham() {
        return Lists.orEmpty(loaiSpDao.findAllLoaiSanPham());
    }

    @Override
    public Integer addLoaiSanPham(LoaiSanPham e) {
        return loaiSpDao.add(e);
    }

    @Override
    public void updateLoaiSanPham(LoaiSanPham e) {
        loaiSpDao.update(e);
    }

    @Override
    public void deleteLoaiSanPham(String ma) {
        loaiSpDao.delete(ma);
    }

    @Override
    public List<Thuoc> findAllThuoc() {
        return Lists.orEmpty(thuocDao.findAllThuoc());
    }

    @Override
    public Thuoc findThuocById(String ma) {
        return thuocDao.findById(ma);
    }

    @Override
    public Integer addThuoc(Thuoc e) {
        return thuocDao.add(e);
    }

    @Override
    public void updateThuoc(Thuoc e) {
        thuocDao.update(e);
    }

    @Override
    public void deleteThuoc(String ma) {
        thuocDao.delete(ma);
    }

    @Override
    public List<SanPham> findAllSanPham() {
        return Lists.orEmpty(sanPhamDao.findAllSanPham());
    }

    @Override
    public SanPham findSanPhamById(String ma) {
        return sanPhamDao.findById(ma);
    }

    @Override
    public Integer addSanPham(SanPham e) {
        return sanPhamDao.add(e);
    }

    @Override
    public void updateSanPham(SanPham e) {
        sanPhamDao.update(e);
    }

    @Override
    public void deleteSanPham(String ma) {
        sanPhamDao.delete(ma);
    }

    @Override
    public List<ChiNhanh> findAllChiNhanh() {
        return Lists.orEmpty(chiNhanhDao.findAllChiNhanh());
    }

    @Override
    public Integer addChiNhanh(ChiNhanh e) {
        return chiNhanhDao.add(e);
    }

    @Override
    public void updateChiNhanh(ChiNhanh e) {
        chiNhanhDao.update(e);
    }

    @Override
    public void deleteChiNhanh(String ma) {
        chiNhanhDao.delete(ma);
    }

    @Override
    public List<NhaCungCap> findAllNhaCungCap() {
        return Lists.orEmpty(nccDao.findAllNhaCungCap());
    }

    @Override
    public Integer addNhaCungCap(NhaCungCap e) {
        return nccDao.add(e);
    }

    @Override
    public void updateNhaCungCap(NhaCungCap e) {
        nccDao.update(e);
    }

    @Override
    public void deleteNhaCungCap(String ma) {
        nccDao.delete(ma);
    }

    @Override
    public List<NhomKhachHang> findAllNhomKhachHang() {
        return Lists.orEmpty(nhomKhDao.findAllNhomKhachHang());
    }

    @Override
    public Integer addNhomKhachHang(NhomKhachHang e) {
        return nhomKhDao.add(e);
    }

    @Override
    public void updateNhomKhachHang(NhomKhachHang e) {
        nhomKhDao.update(e);
    }

    @Override
    public void deleteNhomKhachHang(String ma) {
        nhomKhDao.delete(ma);
    }

    @Override
    public List<DonVi> findAllDonVi() {
        return Lists.orEmpty(donViDao.findAllDonVi());
    }

    @Override
    public Integer addDonVi(DonVi e) {
        return donViDao.add(e);
    }

    @Override
    public void updateDonVi(DonVi e) {
        donViDao.update(e);
    }

    @Override
    public void deleteDonVi(String ma) {
        donViDao.delete(ma);
    }
}
