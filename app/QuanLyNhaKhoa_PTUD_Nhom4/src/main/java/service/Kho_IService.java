package service;

import entity.ChiTietNhapKho;
import entity.ChiTietXuatKho;
import entity.NhaCungCap;
import entity.PhieuNhapKho;
import entity.PhieuXuatKho;
import entity.SanPham;
import java.util.List;

public interface Kho_IService {

    List<PhieuNhapKho> findAllPhieuNhap();

    PhieuNhapKho findPhieuNhapById(String ma);

    Integer addPhieuNhap(PhieuNhapKho entity);

    void updatePhieuNhap(PhieuNhapKho entity);

    void deletePhieuNhap(String ma);

    List<ChiTietNhapKho> findChiTietNhapByMaPhieu(String maPhieuNhap);

    Integer addChiTietNhap(ChiTietNhapKho entity);

    List<PhieuXuatKho> findAllPhieuXuat();

    PhieuXuatKho findPhieuXuatById(String ma);

    Integer addPhieuXuat(PhieuXuatKho entity);

    void updatePhieuXuat(PhieuXuatKho entity);

    void deletePhieuXuat(String ma);

    List<ChiTietXuatKho> findChiTietXuatByMaPhieu(String maPhieuXuat);

    Integer addChiTietXuat(ChiTietXuatKho entity);

    List<SanPham> findAllSanPham();

    List<NhaCungCap> findAllNhaCungCap();

    /** Lưu phiếu nhập kèm chi tiết và cộng tồn sản phẩm. */
    Integer nhapKho(PhieuNhapKho phieu, List<ChiTietNhapKho> chiTiet);

    /** Lưu phiếu xuất kèm chi tiết và trừ tồn sản phẩm. */
    Integer xuatKho(PhieuXuatKho phieu, List<ChiTietXuatKho> chiTiet);
}
