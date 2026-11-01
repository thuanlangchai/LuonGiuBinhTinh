package service.impl;

import entity.LichHen;
import java.util.List;
import repository.impl.LichHen_DAO;
import service.LichHen_IService;

public class LichHen_Service implements LichHen_IService {

    private final LichHen_DAO dao;

    public LichHen_Service() {
        this(new LichHen_DAO());
    }

    public LichHen_Service(LichHen_DAO dao) {
        this.dao = dao;
    }

    @Override
    public List<LichHen> findAllLichHen() {
        return Lists.orEmpty(dao.findAllLichHen());
    }

    @Override
    public LichHen findById(String maLichHen) {
        return dao.findById(maLichHen);
    }

    @Override
    public List<LichHen> findByMaKH(String maKH) {
        return Lists.orEmpty(dao.findByMaKH(maKH));
    }

    @Override
    public Integer add(LichHen entity) {
        return dao.add(entity);
    }

    @Override
    public void update(LichHen entity) {
        dao.update(entity);
    }

    @Override
    public void delete(String maLichHen) {
        dao.delete(maLichHen);
    }
}
