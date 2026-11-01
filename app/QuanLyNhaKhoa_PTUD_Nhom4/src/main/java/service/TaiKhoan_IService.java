package service;

import entity.TaiKhoan;
import java.util.List;

public interface TaiKhoan_IService {

    List<TaiKhoan> findAllTaiKhoan();

    TaiKhoan findById(String tenDangNhap);

    Integer add(TaiKhoan entity);

    void update(TaiKhoan entity);

    void delete(String tenDangNhap);

    /** Đăng nhập: trả về tài khoản nếu đúng và đang kích hoạt, ngược lại null. */
    TaiKhoan dangNhap(String tenDangNhap, String matKhau);
}
