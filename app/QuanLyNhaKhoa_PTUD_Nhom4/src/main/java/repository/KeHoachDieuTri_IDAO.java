package repository;

import entity.KeHoachDieuTri;
import java.util.List;

public interface KeHoachDieuTri_IDAO {
    List<KeHoachDieuTri> findAllKeHoachDieuTri();
    KeHoachDieuTri findById(String maKeHoach);
    List<KeHoachDieuTri> findByMaKH(String maKH);
    Integer add(KeHoachDieuTri entity);
    void update(KeHoachDieuTri entity);
    void delete(String maKeHoach);
}
