package service.impl;

import entity.PhieuChi;
import java.util.List;
import repository.impl.PhieuChi_DAO;
import service.PhieuChi_IService;

public class PhieuChi_Service implements PhieuChi_IService {

    private final PhieuChi_DAO dao;

    public PhieuChi_Service() {
        this(new PhieuChi_DAO());
    }

    public PhieuChi_Service(PhieuChi_DAO dao) {
        this.dao = dao;
    }

    @Override
    public List<PhieuChi> findAllPhieuChi() {
        return Lists.orEmpty(dao.findAllPhieuChi());
    }

    @Override
    public PhieuChi findById(String maPhieuChi) {
        return dao.findById(maPhieuChi);
    }

    @Override
    public Integer add(PhieuChi entity) {
        return dao.add(entity);
    }

    @Override
    public void update(PhieuChi entity) {
        dao.update(entity);
    }

    @Override
    public void delete(String maPhieuChi) {
        dao.delete(maPhieuChi);
    }

    public int nextSo() {
        return dao.nextSo();
    }
}
