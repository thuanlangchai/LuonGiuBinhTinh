package service;

import entity.PhieuThu;
import java.util.List;

public interface PhieuThu_IService {

    List<PhieuThu> findAllPhieuThu();

    PhieuThu findById(String maPhieuThu);

    Integer add(PhieuThu entity);

    void update(PhieuThu entity);

    void delete(String maPhieuThu);
}
