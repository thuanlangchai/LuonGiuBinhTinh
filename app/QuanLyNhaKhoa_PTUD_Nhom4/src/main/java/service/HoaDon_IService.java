package service;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.util.List;

public interface HoaDon_IService {

    List<HoaDon> findAllHoaDon();

    HoaDon findById(String maHoaDon);

    List<HoaDon> findByMaKH(String maKH);

    Integer add(HoaDon entity);

    void update(HoaDon entity);

    void delete(String maHoaDon);

    List<ChiTietHoaDon> findChiTietByMaHoaDon(String maHoaDon);

    Integer addChiTiet(ChiTietHoaDon entity);

    void updateChiTiet(ChiTietHoaDon entity);

    void deleteChiTiet(String maChiTiet);

    Integer themHoaDonVoiChiTiet(HoaDon hoaDon, List<ChiTietHoaDon> chiTiet);
}
