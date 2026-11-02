package service.impl;

import entity.KhachHang;
import java.util.List;
import repository.impl.KhachHang_DAO;
import service.KhachHang_IService;

public class KhachHang_Service implements KhachHang_IService {

    private final KhachHang_DAO dao;

    public KhachHang_Service() {
        this(new KhachHang_DAO());
    }

    public KhachHang_Service(KhachHang_DAO dao) {
        this.dao = dao;
    }

    @Override
    public List<KhachHang> findAllKhachHang() {
        return Lists.orEmpty(dao.findAllKhachHang());
    }

    @Override
    public KhachHang findById(String maKH) {
        return dao.findById(maKH);
    }

    @Override
    public KhachHang findBySdt(String sdt) {
        return dao.findBySdt(sdt);
    }

    @Override
    public Integer add(KhachHang entity) {
        return dao.add(entity);
    }

    @Override
    public void update(KhachHang entity) {
        dao.update(entity);
    }

    @Override
    public void delete(String maKH) {
        dao.delete(maKH);
    }
}
