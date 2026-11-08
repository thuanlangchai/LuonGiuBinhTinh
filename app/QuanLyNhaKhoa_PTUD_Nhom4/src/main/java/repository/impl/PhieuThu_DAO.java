package repository.impl;

import entity.PhieuThu;
import entity.Refs;
import java.util.List;
import mapper.PhieuThuMapper;
import repository.PhieuThu_IDAO;

public class PhieuThu_DAO extends AbstractDAO<PhieuThu> implements PhieuThu_IDAO {

    private static final String SELECT = """
            SELECT pt.maPhieuThu, pt.ngayThu, pt.lyDo, pt.soTien, pt.hinhThucThanhToan, pt.trangThai,
                   pt.nguoiGioiThieu, pt.ngayTao, pt.nguoiTao,
                   kh.maKH, kh.hoTen AS tenKhachHang,
                   pt.maHoaDon,
                   bs.maNhanVien AS maBacSi, bs.hoTen AS tenBacSi,
                   pta.maNhanVien AS maPhuTa, pta.hoTen AS tenPhuTa,
                   nt.hoTen AS tenNguoiTao                      
            FROM PhieuThu pt
            JOIN KhachHang kh ON kh.maKH = pt.maKH
            LEFT JOIN NhanVien bs ON bs.maNhanVien = pt.maBacSi
            LEFT JOIN NhanVien pta ON pta.maNhanVien = pt.maPhuTa
            LEFT JOIN NhanVien nt ON nt.maNhanVien = pt.maPhuTa                             
            """;

    private final PhieuThuMapper mapper = new PhieuThuMapper();

    @Override
    public List<PhieuThu> findAllPhieuThu() {
        return query(SELECT + " ORDER BY pt.ngayThu DESC, pt.maPhieuThu", mapper);
    }

    @Override
    public PhieuThu findById(String maPhieuThu) {
        return one(SELECT + " WHERE pt.maPhieuThu = ?", mapper, maPhieuThu);
    }

    public List<PhieuThu> findPage(int page, int size) {
        int offset = Math.max(page, 0) * Math.max(size, 1);
        return query(SELECT + " ORDER BY pt.ngayThu DESC, pt.maPhieuThu OFFSET ? ROWS FETCH NEXT ? ROWS ONLY",
                mapper, offset, Math.max(size, 1));
    }

    public long countByMaHoaDon(String maHoaDon) {
        return queryLong("SELECT COUNT(*) FROM PhieuThu WHERE maHoaDon = ?", maHoaDon);
    }

    public int nextSo() {
        return (int) queryLong("SELECT ISNULL(MAX(TRY_CAST(maPhieuThu AS INT)), 0) FROM PhieuThu");
    }

    @Override
    public Integer add(PhieuThu entity) {
        String sql = "INSERT INTO PhieuThu(maPhieuThu,ngayThu,maKH,maHoaDon,lyDo,soTien,hinhThucThanhToan,trangThai,maBacSi,maPhuTa,nguoiGioiThieu,ngayTao,nguoiTao) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return execute(sql, entity.getMaPhieuThu(), entity.getNgayThu(), Refs.maKh(entity.getKhachHang()),
                Refs.maHd(entity.getHoaDon()), entity.getLyDo(), entity.getSoTien(), entity.getHinhThucThanhToan(),
                entity.getTrangThai(), Refs.maNv(entity.getBacSi()), Refs.maNv(entity.getPhuTa()),
                entity.getNguoiGioiThieu(), entity.getNgayTao(), entity.getNguoiTao());
    }

    @Override
    public void update(PhieuThu entity) {
        String sql = "UPDATE PhieuThu SET ngayThu = ?, maKH = ?, maHoaDon = ?, lyDo = ?, soTien = ?, hinhThucThanhToan = ?, trangThai = ?, maBacSi = ?, maPhuTa = ?, nguoiGioiThieu = ?, ngayTao = ?, nguoiTao = ? WHERE maPhieuThu = ?";
        execute(sql, entity.getNgayThu(), Refs.maKh(entity.getKhachHang()), Refs.maHd(entity.getHoaDon()),
                entity.getLyDo(), entity.getSoTien(), entity.getHinhThucThanhToan(), entity.getTrangThai(),
                Refs.maNv(entity.getBacSi()), Refs.maNv(entity.getPhuTa()), entity.getNguoiGioiThieu(),
                entity.getNgayTao(), entity.getNguoiTao(), entity.getMaPhieuThu());
    }

    @Override
    public void delete(String maPhieuThu) {
        execute("DELETE FROM PhieuThu WHERE maPhieuThu = ?", maPhieuThu);
    }
}
