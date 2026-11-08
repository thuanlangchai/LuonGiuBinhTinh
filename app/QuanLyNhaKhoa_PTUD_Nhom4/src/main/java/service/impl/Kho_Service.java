package service.impl;

import database.BusinessException;
import database.Transactions;
import entity.ChiTietNhapKho;
import entity.ChiTietXuatKho;
import entity.NhaCungCap;
import entity.PhieuNhapKho;
import entity.PhieuXuatKho;
import entity.Refs;
import entity.SanPham;
import java.util.List;
import repository.impl.ChiTietNhapKho_DAO;
import repository.impl.ChiTietXuatKho_DAO;
import repository.impl.NhaCungCap_DAO;
import repository.impl.PhieuNhapKho_DAO;
import repository.impl.PhieuXuatKho_DAO;
import repository.impl.SanPham_DAO;
import service.Kho_IService;

public class Kho_Service implements Kho_IService {

    private final PhieuNhapKho_DAO nhapDao = new PhieuNhapKho_DAO();
    private final ChiTietNhapKho_DAO ctNhapDao = new ChiTietNhapKho_DAO();
    private final PhieuXuatKho_DAO xuatDao = new PhieuXuatKho_DAO();
    private final ChiTietXuatKho_DAO ctXuatDao = new ChiTietXuatKho_DAO();
    private final SanPham_DAO sanPhamDao = new SanPham_DAO();
    private final NhaCungCap_DAO nccDao = new NhaCungCap_DAO();

    @Override
    public List<PhieuNhapKho> findAllPhieuNhap() {
        return nhapDao.findAllPhieuNhapKho();
    }

    @Override
    public PhieuNhapKho findPhieuNhapById(String ma) {
        return nhapDao.findById(ma);
    }

    @Override
    public Integer addPhieuNhap(PhieuNhapKho entity) {
        return nhapDao.add(entity);
    }

    @Override
    public void updatePhieuNhap(PhieuNhapKho entity) {
        nhapDao.update(entity);
    }

    @Override
    public void deletePhieuNhap(String ma) {
        nhapDao.delete(ma);
    }

    @Override
    public List<ChiTietNhapKho> findChiTietNhapByMaPhieu(String maPhieuNhap) {
        return ctNhapDao.findByMaPhieuNhap(maPhieuNhap);
    }

    @Override
    public Integer addChiTietNhap(ChiTietNhapKho entity) {
        return ctNhapDao.add(entity);
    }

    @Override
    public List<PhieuXuatKho> findAllPhieuXuat() {
        return xuatDao.findAllPhieuXuatKho();
    }

    @Override
    public PhieuXuatKho findPhieuXuatById(String ma) {
        return xuatDao.findById(ma);
    }

    @Override
    public Integer addPhieuXuat(PhieuXuatKho entity) {
        return xuatDao.add(entity);
    }

    @Override
    public void updatePhieuXuat(PhieuXuatKho entity) {
        xuatDao.update(entity);
    }

    @Override
    public void deletePhieuXuat(String ma) {
        xuatDao.delete(ma);
    }

    @Override
    public List<ChiTietXuatKho> findChiTietXuatByMaPhieu(String maPhieuXuat) {
        return ctXuatDao.findByMaPhieuXuat(maPhieuXuat);
    }

    @Override
    public Integer addChiTietXuat(ChiTietXuatKho entity) {
        return ctXuatDao.add(entity);
    }

    @Override
    public List<SanPham> findAllSanPham() {
        return sanPhamDao.findAllSanPham();
    }

    @Override
    public List<NhaCungCap> findAllNhaCungCap() {
        return nccDao.findAllNhaCungCap();
    }

    @Override
    public Integer nhapKho(PhieuNhapKho phieu, List<ChiTietNhapKho> chiTiet) {
        if (phieu == null) {
            return null;
        }
        int[] ketQua = new int[1];
        Transactions.run(() -> {
            long tong = 0;
            if (chiTiet != null) {
                for (ChiTietNhapKho dong : chiTiet) {
                    dong.setThanhTien((long) dong.getSoLuong() * dong.getDonGia());
                    tong += dong.getThanhTien();
                }
            }
            phieu.setTongTien(tong);
            ketQua[0] = nhapDao.add(phieu);
            if (chiTiet != null) {
                for (ChiTietNhapKho dong : chiTiet) {
                    String maSp = Refs.maSp(dong.getSanPham());
                    if (Refs.blank(maSp)) {
                        throw new BusinessException("Chi tiết nhập kho thiếu sản phẩm.");
                    }
                    dong.setPhieuNhapKho(Refs.phieuNhap(phieu.getMaPhieuNhap()));
                    ctNhapDao.add(dong);
                    if (sanPhamDao.congTon(maSp, dong.getSoLuong()) == 0) {
                        throw new BusinessException("Sản phẩm không tồn tại: " + maSp);
                    }
                }
            }
        });
        return ketQua[0];
    }

    @Override
    public Integer xuatKho(PhieuXuatKho phieu, List<ChiTietXuatKho> chiTiet) {
        if (phieu == null) {
            return null;
        }
        int[] ketQua = new int[1];
        Transactions.run(() -> {
            long tong = 0;
            if (chiTiet != null) {
                for (ChiTietXuatKho dong : chiTiet) {
                    String maSp = Refs.maSp(dong.getSanPham());
                    if (Refs.blank(maSp)) {
                        throw new BusinessException("Chi tiết xuất kho thiếu sản phẩm.");
                    }
                    if (sanPhamDao.truTon(maSp, dong.getSoLuong()) == 0) {
                        throw new BusinessException("Xuất vượt tồn kho hoặc sản phẩm không tồn tại: " + maSp);
                    }
                    dong.setThanhTien((long) dong.getSoLuong() * dong.getDonGia());
                    tong += dong.getThanhTien();
                }
            }
            phieu.setTongTien(tong);
            ketQua[0] = xuatDao.add(phieu);
            if (chiTiet != null) {
                for (ChiTietXuatKho dong : chiTiet) {
                    dong.setPhieuXuatKho(Refs.phieuXuat(phieu.getMaPhieuXuat()));
                    ctXuatDao.add(dong);
                }
            }
        });
        return ketQua[0];
    }
}
