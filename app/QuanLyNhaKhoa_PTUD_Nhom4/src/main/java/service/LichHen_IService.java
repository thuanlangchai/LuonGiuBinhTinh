package service;

import entity.LichHen;
import java.util.List;

public interface LichHen_IService {

    List<LichHen> findAllLichHen();

    LichHen findById(String maLichHen);

    List<LichHen> findByMaKH(String maKH);

    Integer add(LichHen entity);

    void update(LichHen entity);

    void delete(String maLichHen);
}
