package service;

import entity.ChiTietToaThuoc;
import entity.ToaThuoc;
import java.util.List;

public interface ToaThuoc_IService {

    List<ToaThuoc> findAllToaThuoc();

    ToaThuoc findById(String maToaThuoc);

    List<ToaThuoc> findByMaKH(String maKH);

    Integer add(ToaThuoc entity);

    void update(ToaThuoc entity);

    void delete(String maToaThuoc);

    List<ChiTietToaThuoc> findChiTietByMaToaThuoc(String maToaThuoc);

    Integer addChiTiet(ChiTietToaThuoc entity);

    void updateChiTiet(ChiTietToaThuoc entity);

    void deleteChiTiet(String maChiTiet);

    Integer themToaVoiChiTiet(ToaThuoc toa, List<ChiTietToaThuoc> chiTiet);
}
