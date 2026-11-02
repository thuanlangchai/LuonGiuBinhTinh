package service.impl;

import entity.TaiKhoan;
import java.util.List;
import repository.impl.TaiKhoan_DAO;
import service.TaiKhoan_IService;

public class TaiKhoan_Service implements TaiKhoan_IService {

    private final TaiKhoan_DAO dao;

    public TaiKhoan_Service() {
        this(new TaiKhoan_DAO());
    }

    public TaiKhoan_Service(TaiKhoan_DAO dao) {
        this.dao = dao;
    }

    @Override
    public List<TaiKhoan> findAllTaiKhoan() {
        return Lists.orEmpty(dao.findAllTaiKhoan());
    }

    @Override
    public TaiKhoan findById(String tenDangNhap) {
        return dao.findById(tenDangNhap);
    }

    @Override
    public Integer add(TaiKhoan entity) {
        return dao.add(entity);
    }

    @Override
    public void update(TaiKhoan entity) {
        dao.update(entity);
    }

    @Override
    public void delete(String tenDangNhap) {
        dao.delete(tenDangNhap);
    }

    @Override
    public TaiKhoan dangNhap(String tenDangNhap, String matKhau) {
        if (tenDangNhap == null || tenDangNhap.isBlank() || matKhau == null) {
            return null;
        }
        TaiKhoan tk = dao.findByTenDangNhap(tenDangNhap.trim());
        if (tk == null) {
            return null;
        }
        if (!tk.isKichHoat()) {
            return null;
        }
        if (!matKhau.equals(tk.getMatKhau())) {
            return null;
        }
        return tk;
    }
}
