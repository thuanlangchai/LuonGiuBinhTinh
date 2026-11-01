package service;

import entity.PhieuChi;
import java.util.List;

public interface PhieuChi_IService {

    List<PhieuChi> findAllPhieuChi();

    PhieuChi findById(String maPhieuChi);

    Integer add(PhieuChi entity);

    void update(PhieuChi entity);

    void delete(String maPhieuChi);
}
