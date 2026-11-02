package service;

import entity.KhachHang;
import java.util.List;

public interface KhachHang_IService {

    List<KhachHang> findAllKhachHang();

    KhachHang findById(String maKH);

    KhachHang findBySdt(String sdt);

    Integer add(KhachHang entity);

    void update(KhachHang entity);

    void delete(String maKH);
}
