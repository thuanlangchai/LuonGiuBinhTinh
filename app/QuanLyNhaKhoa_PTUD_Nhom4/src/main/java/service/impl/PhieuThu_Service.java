package service.impl;

import entity.PhieuThu;
import java.util.List;
import repository.impl.PhieuThu_DAO;
import service.PhieuThu_IService;

public class PhieuThu_Service implements PhieuThu_IService {

    private final PhieuThu_DAO dao;

    public PhieuThu_Service() {
        this(new PhieuThu_DAO());
    }

    public PhieuThu_Service(PhieuThu_DAO dao) {
        this.dao = dao;
    }

    @Override
    public List<PhieuThu> findAllPhieuThu() {
        return Lists.orEmpty(dao.findAllPhieuThu());
    }

    @Override
    public PhieuThu findById(String maPhieuThu) {
        return dao.findById(maPhieuThu);
    }

    @Override
    public Integer add(PhieuThu entity) {
        return dao.add(entity);
    }

    @Override
    public void update(PhieuThu entity) {
        dao.update(entity);
    }

    @Override
    public void delete(String maPhieuThu) {
        dao.delete(maPhieuThu);
    }

    public int nextSo() {
        return dao.nextSo();
    }
}
