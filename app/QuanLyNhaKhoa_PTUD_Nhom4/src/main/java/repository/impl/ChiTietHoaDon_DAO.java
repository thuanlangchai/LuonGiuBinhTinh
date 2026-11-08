package repository.impl;

import entity.ChiTietHoaDon;
import entity.Refs;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mapper.ChiTietHoaDonMapper;
import repository.ChiTietHoaDon_IDAO;

public class ChiTietHoaDon_DAO extends AbstractDAO<ChiTietHoaDon> implements ChiTietHoaDon_IDAO {

    private static final String SELECT = """
            SELECT ct.maChiTiet, ct.ngay, ct.soLuong, ct.donGia, ct.keHoach,
                   ct.maHoaDon, r.maRang, r.tenRang, dv.maDichVu, dv.tenDichVu
            FROM ChiTietHoaDon ct
            LEFT JOIN Rang r ON r.maRang = ct.maRang
            JOIN DichVu dv ON dv.maDichVu = ct.maDichVu
            """;

    private final ChiTietHoaDonMapper mapper = new ChiTietHoaDonMapper();

    @Override
    public List<ChiTietHoaDon> findAllChiTietHoaDon() {
        return query(SELECT, mapper);
    }

    @Override
    public ChiTietHoaDon findById(String maChiTiet) {
        return one(SELECT + " WHERE ct.maChiTiet = ?", mapper, maChiTiet);
    }

    @Override
    public List<ChiTietHoaDon> findByMaHoaDon(String maHoaDon) {
        return query(SELECT + " WHERE ct.maHoaDon = ?", mapper, maHoaDon);
    }

    public Map<String, List<ChiTietHoaDon>> findByMaHoaDonIn(List<String> ids) {
        Map<String, List<ChiTietHoaDon>> map = new LinkedHashMap<>();
        for (ChiTietHoaDon row : queryIn(SELECT + " WHERE ct.maHoaDon", ids, mapper)) {
            map.computeIfAbsent(Refs.maHd(row.getHoaDon()), key -> new ArrayList<>()).add(row);
        }
        return map;
    }

    public void deleteByMaHoaDon(String maHoaDon) {
        execute("DELETE FROM ChiTietHoaDon WHERE maHoaDon = ?", maHoaDon);
    }

    @Override
    public Integer add(ChiTietHoaDon entity) {
        String sql = "INSERT INTO ChiTietHoaDon(maChiTiet,maHoaDon,ngay,maRang,maDichVu,soLuong,donGia,keHoach) VALUES(?,?,?,?,?,?,?,?)";
        return execute(sql, entity.getMaChiTiet(), Refs.maHd(entity.getHoaDon()), entity.getNgay(),
                Refs.maRang(entity.getRang()), Refs.maDv(entity.getDichVu()), entity.getSoLuong(),
                entity.getDonGia(), entity.getKeHoach());
    }

    @Override
    public void update(ChiTietHoaDon entity) {
        String sql = "UPDATE ChiTietHoaDon SET maHoaDon = ?, ngay = ?, maRang = ?, maDichVu = ?, soLuong = ?, donGia = ?, keHoach = ? WHERE maChiTiet = ?";
        execute(sql, Refs.maHd(entity.getHoaDon()), entity.getNgay(), Refs.maRang(entity.getRang()),
                Refs.maDv(entity.getDichVu()), entity.getSoLuong(), entity.getDonGia(),
                entity.getKeHoach(), entity.getMaChiTiet());
    }

    @Override
    public void delete(String maChiTiet) {
        execute("DELETE FROM ChiTietHoaDon WHERE maChiTiet = ?", maChiTiet);
    }
}
