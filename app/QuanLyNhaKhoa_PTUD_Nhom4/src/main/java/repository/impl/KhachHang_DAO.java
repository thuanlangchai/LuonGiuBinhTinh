package repository.impl;

import entity.KhachHang;
import entity.Refs;
import java.util.List;
import mapper.KhachHangMapper;
import repository.KhachHang_IDAO;

public class KhachHang_DAO extends AbstractDAO<KhachHang> implements KhachHang_IDAO {

    private static final String SELECT = """
            SELECT kh.maKH, kh.hoTen, kh.gioiTinh, kh.ngaySinh, kh.soDienThoai, kh.email, kh.diaChi, kh.cccd, kh.ghiChu,
                   kh.lyDoKham, kh.tienSuBenh, kh.diUng, kh.soLanKham, kh.lanKhamGanNhat, kh.ngayTao,
                   nk.maNhom, nk.tenNhom,
                   cn.maChiNhanh, cn.tenChiNhanh,
                   bs.maNhanVien AS maBacSi, bs.hoTen AS tenBacSi,
                   tv.maNhanVien AS maTuVan, tv.hoTen AS tenTuVan
            FROM KhachHang kh
            LEFT JOIN NhomKhachHang nk ON nk.maNhom = kh.maNhom
            LEFT JOIN ChiNhanh cn ON cn.maChiNhanh = kh.maChiNhanh
            LEFT JOIN NhanVien bs ON bs.maNhanVien = kh.maBacSi
            LEFT JOIN NhanVien tv ON tv.maNhanVien = kh.maTuVan
            """;

    private final KhachHangMapper mapper = new KhachHangMapper();

    @Override
    public List<KhachHang> findAllKhachHang() {
        return query(SELECT + " ORDER BY kh.maKH", mapper);
    }

    @Override
    public KhachHang findById(String maKH) {
        return one(SELECT + " WHERE kh.maKH = ?", mapper, maKH);
    }

    @Override
    public KhachHang findBySdt(String sdt) {
        return one(SELECT + " WHERE kh.soDienThoai = ?", mapper, sdt);
    }

    public List<KhachHang> findPage(int page, int size) {
        int offset = Math.max(page, 0) * Math.max(size, 1);
        return query(SELECT + " ORDER BY kh.maKH OFFSET ? ROWS FETCH NEXT ? ROWS ONLY", mapper, offset, Math.max(size, 1));
    }

    public int nextSo() {
        return (int) queryLong("SELECT ISNULL(MAX(TRY_CAST(maKH AS INT)), 0) FROM KhachHang");
    }

    @Override
    public Integer add(KhachHang entity) {
        String sql = "INSERT INTO KhachHang(maKH,hoTen,gioiTinh,ngaySinh,soDienThoai,email,diaChi,cccd,ghiChu,maNhom,maChiNhanh,lyDoKham,tienSuBenh,diUng,maBacSi,maTuVan,soLanKham,lanKhamGanNhat,ngayTao) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return execute(sql, entity.getMaKH(), entity.getHoTen(), entity.getGioiTinh(), entity.getNgaySinh(),
                entity.getSoDienThoai(), entity.getEmail(), entity.getDiaChi(), entity.getCccd(), entity.getGhiChu(),
                Refs.maNhomKh(entity.getNhomKhachHang()), Refs.maCn(entity.getChiNhanh()), entity.getLyDoKham(),
                entity.getTienSuBenh(), entity.getDiUng(), Refs.maNv(entity.getBacSi()), Refs.maNv(entity.getTuVan()),
                entity.getSoLanKham(), entity.getLanKhamGanNhat(), entity.getNgayTao());
    }

    @Override
    public void update(KhachHang entity) {
        String sql = "UPDATE KhachHang SET hoTen = ?, gioiTinh = ?, ngaySinh = ?, soDienThoai = ?, email = ?, diaChi = ?, cccd = ?, ghiChu = ?, maNhom = ?, maChiNhanh = ?, lyDoKham = ?, tienSuBenh = ?, diUng = ?, maBacSi = ?, maTuVan = ?, soLanKham = ?, lanKhamGanNhat = ?, ngayTao = ? WHERE maKH = ?";
        execute(sql, entity.getHoTen(), entity.getGioiTinh(), entity.getNgaySinh(), entity.getSoDienThoai(),
                entity.getEmail(), entity.getDiaChi(), entity.getCccd(), entity.getGhiChu(),
                Refs.maNhomKh(entity.getNhomKhachHang()), Refs.maCn(entity.getChiNhanh()), entity.getLyDoKham(),
                entity.getTienSuBenh(), entity.getDiUng(), Refs.maNv(entity.getBacSi()), Refs.maNv(entity.getTuVan()),
                entity.getSoLanKham(), entity.getLanKhamGanNhat(), entity.getNgayTao(), entity.getMaKH());
    }

    @Override
    public void delete(String maKH) {
        execute("DELETE FROM KhachHang WHERE maKH = ?", maKH);
    }
}
