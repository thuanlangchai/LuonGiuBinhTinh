package service.impl;

import entity.ChiTietNhapKho;
import entity.ChiTietXuatKho;
import entity.NhaCungCap;
import entity.PhieuNhapKho;
import entity.PhieuXuatKho;
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
        return Lists.orEmpty(nhapDao.findAllPhieuNhapKho());
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
        return Lists.orEmpty(ctNhapDao.findByMaPhieuNhap(maPhieuNhap));
    }

    @Override
    public Integer addChiTietNhap(ChiTietNhapKho entity) {
        return ctNhapDao.add(entity);
    }

    @Override
    public List<PhieuXuatKho> findAllPhieuXuat() {
        return Lists.orEmpty(xuatDao.findAllPhieuXuatKho());
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
        return Lists.orEmpty(ctXuatDao.findByMaPhieuXuat(maPhieuXuat));
    }

    @Override
    public Integer addChiTietXuat(ChiTietXuatKho entity) {
        return ctXuatDao.add(entity);
    }

    @Override
    public List<SanPham> findAllSanPham() {
        return Lists.orEmpty(sanPhamDao.findAllSanPham());
    }

    @Override
    public List<NhaCungCap> findAllNhaCungCap() {
        return Lists.orEmpty(nccDao.findAllNhaCungCap());
    }

    @Override
    public Integer nhapKho(PhieuNhapKho phieu, List<ChiTietNhapKho> chiTiet) {
        if (phieu == null) {
            return null;
        }
        Integer kq = nhapDao.add(phieu);
        if (kq == null) {
            return null;
        }
        if (chiTiet != null) {
            for (ChiTietNhapKho ct : chiTiet) {
                ct.setMaPhieuNhap(phieu.getMaPhieuNhap());
                ctNhapDao.add(ct);
                SanPham sp = sanPhamDao.findById(ct.getMaSanPham());
                if (sp != null) {
                    sp.setSoLuong(sp.getSoLuong() + ct.getSoLuong());
                    sanPhamDao.update(sp);
                }
            }
        }
        return kq;
    }

    @Override
    public Integer xuatKho(PhieuXuatKho phieu, List<ChiTietXuatKho> chiTiet) {
        if (phieu == null) {
            return null;
        }
        if (chiTiet != null) {
            for (ChiTietXuatKho ct : chiTiet) {
                SanPham sp = sanPhamDao.findById(ct.getMaSanPham());
                if (sp == null || sp.getSoLuong() < ct.getSoLuong()) {
                    return null;
                }
            }
        }
        Integer kq = xuatDao.add(phieu);
        if (kq == null) {
            return null;
        }
        if (chiTiet != null) {
            for (ChiTietXuatKho ct : chiTiet) {
                ct.setMaPhieuXuat(phieu.getMaPhieuXuat());
                ctXuatDao.add(ct);
                SanPham sp = sanPhamDao.findById(ct.getMaSanPham());
                if (sp != null) {
                    sp.setSoLuong(sp.getSoLuong() - ct.getSoLuong());
                    sanPhamDao.update(sp);
                }
            }
        }
        return kq;
    }
}
