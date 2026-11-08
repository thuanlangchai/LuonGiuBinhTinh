package repository.impl;

import entity.LichHen;
import entity.Refs;
import java.time.LocalDateTime;
import java.util.List;
import mapper.LichHenMapper;
import repository.LichHen_IDAO;

public class LichHen_DAO extends AbstractDAO<LichHen> implements LichHen_IDAO {

    private static final String SELECT = """
            SELECT lh.maLichHen, lh.batDau, lh.thoiLuongPhut, lh.ghiChu, lh.trangThai,
                   kh.maKH, kh.hoTen AS tenKhachHang, kh.soDienThoai AS sdtKhachHang,
                   bs.maNhanVien AS maBacSi, bs.hoTen AS tenBacSi,
                   pt.maNhanVien AS maPhuTa, pt.hoTen AS tenPhuTa,
                   kdt.maKeHoach, kdt.lyDoDieuTri AS lyDoKeHoach
            FROM LichHen lh
            JOIN KhachHang kh ON kh.maKH = lh.maKH
            LEFT JOIN NhanVien bs ON bs.maNhanVien = lh.maBacSi
            LEFT JOIN NhanVien pt ON pt.maNhanVien = lh.maPhuTa
            LEFT JOIN KeHoachDieuTri kdt ON kdt.maKeHoach = lh.maKeHoach
            """;

    private final LichHenMapper mapper = new LichHenMapper();

    @Override
    public List<LichHen> findAllLichHen() {
        return query(SELECT + " ORDER BY lh.batDau", mapper);
    }

    @Override
    public LichHen findById(String maLichHen) {
        return one(SELECT + " WHERE lh.maLichHen = ?", mapper, maLichHen);
    }

    @Override
    public List<LichHen> findByMaKH(String maKH) {
        return query(SELECT + " WHERE lh.maKH = ? ORDER BY lh.batDau", mapper, maKH);
    }

    public List<LichHen> findTheoKhoang(LocalDateTime tu, LocalDateTime den) {
        return query(SELECT + " WHERE lh.batDau >= ? AND lh.batDau < ? ORDER BY lh.batDau", mapper, tu, den);
    }

    public List<LichHen> findPage(int page, int size) {
        int offset = Math.max(page, 0) * Math.max(size, 1);
        return query(SELECT + " ORDER BY lh.batDau OFFSET ? ROWS FETCH NEXT ? ROWS ONLY", mapper, offset, Math.max(size, 1));
    }

    public LocalDateTime batDauSomNhat() {
        LichHen row = one("SELECT TOP (1) lh.maLichHen, lh.batDau, lh.thoiLuongPhut, lh.ghiChu, lh.trangThai, kh.maKH, kh.hoTen AS tenKhachHang, kh.soDienThoai AS sdtKhachHang, bs.maNhanVien AS maBacSi, bs.hoTen AS tenBacSi, pt.maNhanVien AS maPhuTa, pt.hoTen AS tenPhuTa, kdt.maKeHoach, kdt.lyDoDieuTri AS lyDoKeHoach FROM LichHen lh JOIN KhachHang kh ON kh.maKH = lh.maKH LEFT JOIN NhanVien bs ON bs.maNhanVien = lh.maBacSi LEFT JOIN NhanVien pt ON pt.maNhanVien = lh.maPhuTa LEFT JOIN KeHoachDieuTri kdt ON kdt.maKeHoach = lh.maKeHoach ORDER BY lh.batDau", mapper);
        return row == null ? null : row.getBatDau();
    }

    public LichHen timTrung(String maBoQua, String maKH, String maBacSi, String maPhuTa, LocalDateTime batDau, LocalDateTime ketThuc) {
        String sql = SELECT + """
                 WHERE lh.trangThai NOT IN (N'Hủy bỏ', N'Đã hủy', N'Hủy')
                   AND (? IS NULL OR lh.maLichHen <> ?)
                   AND lh.batDau < ?
                   AND DATEADD(MINUTE, lh.thoiLuongPhut, lh.batDau) > ?
                   AND ((? IS NOT NULL AND lh.maKH = ?)
                     OR (? IS NOT NULL AND lh.maBacSi = ?)
                     OR (? IS NOT NULL AND lh.maPhuTa = ?))
                """;
        return one(sql, mapper, maBoQua, maBoQua, ketThuc, batDau, maKH, maKH, maBacSi, maBacSi, maPhuTa, maPhuTa);
    }

    public int nextSo() {
        return (int) queryLong("SELECT ISNULL(MAX(TRY_CAST(SUBSTRING(maLichHen, 3, 20) AS INT)), 0) FROM LichHen WHERE maLichHen LIKE N'LH%'");
    }

    @Override
    public Integer add(LichHen entity) {
        String sql = "INSERT INTO LichHen(maLichHen,maKH,batDau,thoiLuongPhut,maBacSi,maPhuTa,maKeHoach,ghiChu,trangThai) VALUES(?,?,?,?,?,?,?,?,?)";
        return execute(sql, entity.getMaLichHen(), Refs.maKh(entity.getKhachHang()), entity.getBatDau(), entity.getThoiLuongPhut(),
                Refs.maNv(entity.getBacSi()), Refs.maNv(entity.getPhuTa()), Refs.maKhdt(entity.getKeHoachDieuTri()),
                entity.getGhiChu(), entity.getTrangThai());
    }

    @Override
    public void update(LichHen entity) {
        String sql = "UPDATE LichHen SET maKH = ?, batDau = ?, thoiLuongPhut = ?, maBacSi = ?, maPhuTa = ?, maKeHoach = ?, ghiChu = ?, trangThai = ? WHERE maLichHen = ?";
        execute(sql, Refs.maKh(entity.getKhachHang()), entity.getBatDau(), entity.getThoiLuongPhut(),
                Refs.maNv(entity.getBacSi()), Refs.maNv(entity.getPhuTa()), Refs.maKhdt(entity.getKeHoachDieuTri()),
                entity.getGhiChu(), entity.getTrangThai(), entity.getMaLichHen());
    }

    @Override
    public void delete(String maLichHen) {
        execute("DELETE FROM LichHen WHERE maLichHen = ?", maLichHen);
    }
}
