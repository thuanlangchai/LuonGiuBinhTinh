package service;

import entity.ChiTietKeHoachDieuTri;
import entity.KeHoachDieuTri;
import java.util.List;

public interface KeHoachDieuTri_IService {

    List<KeHoachDieuTri> findAllKeHoachDieuTri();

    KeHoachDieuTri findById(String maKeHoach);

    List<KeHoachDieuTri> findByMaKH(String maKH);

    Integer add(KeHoachDieuTri entity);

    void update(KeHoachDieuTri entity);

    void delete(String maKeHoach);

    boolean huy(String maKeHoach);

    List<ChiTietKeHoachDieuTri> findChiTietByMaKeHoach(String maKeHoach);

    ChiTietKeHoachDieuTri findChiTietById(String maChiTiet);

    Integer addChiTiet(ChiTietKeHoachDieuTri entity);

    void updateChiTiet(ChiTietKeHoachDieuTri entity);

    void deleteChiTiet(String maChiTiet);
}
