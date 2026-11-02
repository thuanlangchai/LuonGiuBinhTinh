package service;

import entity.TaiKhoan;
import java.util.List;

public interface TaiKhoan_IService {

    List<TaiKhoan> findAllTaiKhoan();

    TaiKhoan findById(String tenDangNhap);

    Integer add(TaiKhoan entity);

    void update(TaiKhoan entity);

    void delete(String tenDangNhap);

    TaiKhoan dangNhap(String tenDangNhap, String matKhau);
}
