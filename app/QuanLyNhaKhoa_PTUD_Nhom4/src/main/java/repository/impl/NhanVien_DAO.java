package repository.impl;

import entity.NhanVien;
import entity.Refs;
import java.util.List;
import mapper.NhanVienMapper;
import repository.NhanVien_IDAO;

public class NhanVien_DAO extends AbstractDAO<NhanVien> implements NhanVien_IDAO {

    private static final String SELECT = """
            SELECT nv.maNhanVien, nv.chucDanh, nv.hoTen, nv.email, nv.soDienThoai, nv.vaiTro, nv.kichHoat,
                   cn.maChiNhanh, cn.tenChiNhanh
            FROM NhanVien nv
            LEFT JOIN ChiNhanh cn ON cn.maChiNhanh = nv.maChiNhanh
            """;

    private final NhanVienMapper mapper = new NhanVienMapper();

    @Override
    public List<NhanVien> findAllNhanVien() {
        return query(SELECT + " ORDER BY nv.maNhanVien", mapper);
    }

    @Override
    public NhanVien findById(String maNhanVien) {
        return one(SELECT + " WHERE nv.maNhanVien = ?", mapper, maNhanVien);
    }

    @Override
    public Integer add(NhanVien entity) {
        return execute("INSERT INTO NhanVien(maNhanVien,chucDanh,hoTen,email,soDienThoai,vaiTro,kichHoat,maChiNhanh) VALUES(?,?,?,?,?,?,?,?)",
                entity.getMaNhanVien(), entity.getChucDanh(), entity.getHoTen(), entity.getEmail(), entity.getSoDienThoai(),
                entity.getVaiTro(), entity.isKichHoat(), Refs.maCn(entity.getChiNhanh()));
    }

    @Override
    public void update(NhanVien entity) {
        execute("UPDATE NhanVien SET chucDanh = ?, hoTen = ?, email = ?, soDienThoai = ?, vaiTro = ?, kichHoat = ?, maChiNhanh = ? WHERE maNhanVien = ?",
                entity.getChucDanh(), entity.getHoTen(), entity.getEmail(), entity.getSoDienThoai(), entity.getVaiTro(),
                entity.isKichHoat(), Refs.maCn(entity.getChiNhanh()), entity.getMaNhanVien());
    }

    @Override
    public void delete(String maNhanVien) {
        execute("DELETE FROM NhanVien WHERE maNhanVien = ?", maNhanVien);
    }
}
