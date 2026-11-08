package repository.impl;

import entity.Refs;
import entity.TaiKhoan;
import java.util.List;
import mapper.TaiKhoanMapper;
import repository.TaiKhoan_IDAO;

public class TaiKhoan_DAO extends AbstractDAO<TaiKhoan> implements TaiKhoan_IDAO {

    private static final String SELECT = """
            SELECT tk.tenDangNhap, tk.matKhau, tk.email, tk.kichHoat, tk.ngayTao,
                   nv.maNhanVien, nv.hoTen AS tenNhanVien
            FROM TaiKhoan tk
            LEFT JOIN NhanVien nv ON nv.maNhanVien = tk.maNhanVien
            """;

    private final TaiKhoanMapper mapper = new TaiKhoanMapper();

    @Override
    public List<TaiKhoan> findAllTaiKhoan() {
        return query(SELECT + " ORDER BY tk.tenDangNhap", mapper);
    }

    @Override
    public TaiKhoan findById(String tenDangNhap) {
        return one(SELECT + " WHERE tk.tenDangNhap = ?", mapper, tenDangNhap);
    }

    @Override
    public TaiKhoan findByTenDangNhap(String tenDangNhap) {
        return findById(tenDangNhap);
    }

    @Override
    public Integer add(TaiKhoan entity) {
        return execute("INSERT INTO TaiKhoan(tenDangNhap,matKhau,email,maNhanVien,kichHoat,ngayTao) VALUES(?,?,?,?,?,?)",
                entity.getTenDangNhap(), entity.getMatKhau(), entity.getEmail(),
                Refs.maNv(entity.getNhanVien()), entity.isKichHoat(), entity.getNgayTao());
    }

    @Override
    public void update(TaiKhoan entity) {
        execute("UPDATE TaiKhoan SET matKhau = ?, email = ?, maNhanVien = ?, kichHoat = ?, ngayTao = ? WHERE tenDangNhap = ?",
                entity.getMatKhau(), entity.getEmail(), Refs.maNv(entity.getNhanVien()),
                entity.isKichHoat(), entity.getNgayTao(), entity.getTenDangNhap());
    }

    @Override
    public void delete(String tenDangNhap) {
        execute("DELETE FROM TaiKhoan WHERE tenDangNhap = ?", tenDangNhap);
    }
}
