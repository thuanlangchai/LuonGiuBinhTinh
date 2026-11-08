package repository.impl;

import entity.ChiTietKeHoachDieuTri;
import entity.Refs;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mapper.ChiTietKeHoachDieuTriMapper;
import repository.ChiTietKeHoachDieuTri_IDAO;

public class ChiTietKeHoachDieuTri_DAO extends AbstractDAO<ChiTietKeHoachDieuTri> implements ChiTietKeHoachDieuTri_IDAO {

    private static final String SELECT = """
            SELECT ct.maChiTiet, ct.ngay, ct.soLuong, ct.donGia, ct.trangThai,
                   ct.maKeHoach, r.maRang, r.tenRang, dv.maDichVu, dv.tenDichVu, ct.maHoaDon
            FROM ChiTietKeHoachDieuTri ct
            LEFT JOIN Rang r ON r.maRang = ct.maRang
            JOIN DichVu dv ON dv.maDichVu = ct.maDichVu
            """;

    private final ChiTietKeHoachDieuTriMapper mapper = new ChiTietKeHoachDieuTriMapper();

    @Override
    public List<ChiTietKeHoachDieuTri> findAllChiTietKeHoachDieuTri() {
        return query(SELECT, mapper);
    }

    @Override
    public ChiTietKeHoachDieuTri findById(String maChiTiet) {
        return one(SELECT + " WHERE ct.maChiTiet = ?", mapper, maChiTiet);
    }

    @Override
    public List<ChiTietKeHoachDieuTri> findByMaKeHoach(String maKeHoach) {
        return query(SELECT + " WHERE ct.maKeHoach = ?", mapper, maKeHoach);
    }

    public Map<String, List<ChiTietKeHoachDieuTri>> findByMaKeHoachIn(List<String> ids) {
        Map<String, List<ChiTietKeHoachDieuTri>> map = new LinkedHashMap<>();
        for (ChiTietKeHoachDieuTri row : queryIn(SELECT + " WHERE ct.maKeHoach", ids, mapper)) {
            map.computeIfAbsent(Refs.maKhdt(row.getKeHoachDieuTri()), key -> new ArrayList<>()).add(row);
        }
        return map;
    }

    public int ganHoaDon(List<String> maChiTiet, String maHoaDon) {
        return executeIn("UPDATE ChiTietKeHoachDieuTri SET maHoaDon = ? WHERE maHoaDon IS NULL AND maChiTiet", maChiTiet, maHoaDon);
    }

    public void boHoaDon(String maHoaDon) {
        execute("UPDATE ChiTietKeHoachDieuTri SET maHoaDon = NULL WHERE maHoaDon = ?", maHoaDon);
    }

    public int nextSo() {
        return (int) queryLong("SELECT ISNULL(MAX(TRY_CAST(SUBSTRING(maChiTiet, 4, 20) AS INT)), 0) FROM ChiTietKeHoachDieuTri WHERE maChiTiet LIKE N'CTK%'");
    }

    @Override
    public Integer add(ChiTietKeHoachDieuTri entity) {
        String sql = "INSERT INTO ChiTietKeHoachDieuTri(maChiTiet,maKeHoach,ngay,maRang,maDichVu,soLuong,donGia,trangThai,maHoaDon) VALUES(?,?,?,?,?,?,?,?,?)";
        return execute(sql, entity.getMaChiTiet(), Refs.maKhdt(entity.getKeHoachDieuTri()), entity.getNgay(),
                Refs.maRang(entity.getRang()), Refs.maDv(entity.getDichVu()), entity.getSoLuong(), entity.getDonGia(),
                entity.getTrangThai(), Refs.maHd(entity.getHoaDon()));
    }

    @Override
    public void update(ChiTietKeHoachDieuTri entity) {
        String sql = "UPDATE ChiTietKeHoachDieuTri SET maKeHoach = ?, ngay = ?, maRang = ?, maDichVu = ?, soLuong = ?, donGia = ?, trangThai = ?, maHoaDon = ? WHERE maChiTiet = ?";
        execute(sql, Refs.maKhdt(entity.getKeHoachDieuTri()), entity.getNgay(), Refs.maRang(entity.getRang()),
                Refs.maDv(entity.getDichVu()), entity.getSoLuong(), entity.getDonGia(),
                entity.getTrangThai(), Refs.maHd(entity.getHoaDon()), entity.getMaChiTiet());
    }

    @Override
    public void delete(String maChiTiet) {
        execute("DELETE FROM ChiTietKeHoachDieuTri WHERE maChiTiet = ?", maChiTiet);
    }
}
