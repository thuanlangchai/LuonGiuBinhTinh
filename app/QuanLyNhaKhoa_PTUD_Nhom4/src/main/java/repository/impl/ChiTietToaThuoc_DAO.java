package repository.impl;

import entity.ChiTietToaThuoc;
import entity.Refs;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mapper.ChiTietToaThuocMapper;
import repository.ChiTietToaThuoc_IDAO;

public class ChiTietToaThuoc_DAO extends AbstractDAO<ChiTietToaThuoc> implements ChiTietToaThuoc_IDAO {

    private static final String SELECT = """
            SELECT ct.maChiTiet, ct.soNgay, ct.cachSuDung, ct.tongSo, ct.donGia,
                   ct.maToaThuoc, th.maThuoc, th.tenThuoc
            FROM ChiTietToaThuoc ct
            JOIN Thuoc th ON th.maThuoc = ct.maThuoc
            """;

    private final ChiTietToaThuocMapper mapper = new ChiTietToaThuocMapper();

    @Override
    public List<ChiTietToaThuoc> findAllChiTietToaThuoc() {
        return query(SELECT, mapper);
    }

    @Override
    public ChiTietToaThuoc findById(String maChiTiet) {
        return one(SELECT + " WHERE ct.maChiTiet = ?", mapper, maChiTiet);
    }

    @Override
    public List<ChiTietToaThuoc> findByMaToaThuoc(String maToaThuoc) {
        return query(SELECT + " WHERE ct.maToaThuoc = ?", mapper, maToaThuoc);
    }

    public Map<String, List<ChiTietToaThuoc>> findByMaToaThuocIn(List<String> ids) {
        Map<String, List<ChiTietToaThuoc>> map = new LinkedHashMap<>();
        for (ChiTietToaThuoc row : queryIn(SELECT + " WHERE ct.maToaThuoc", ids, mapper)) {
            map.computeIfAbsent(Refs.maToa(row.getToaThuoc()), key -> new ArrayList<>()).add(row);
        }
        return map;
    }

    public void deleteByMaToaThuoc(String maToaThuoc) {
        execute("DELETE FROM ChiTietToaThuoc WHERE maToaThuoc = ?", maToaThuoc);
    }

    @Override
    public Integer add(ChiTietToaThuoc entity) {
        String sql = "INSERT INTO ChiTietToaThuoc(maChiTiet,maToaThuoc,maThuoc,soNgay,cachSuDung,tongSo,donGia) VALUES(?,?,?,?,?,?,?)";
        return execute(sql, entity.getMaChiTiet(), Refs.maToa(entity.getToaThuoc()), Refs.maThuoc(entity.getThuoc()),
                entity.getSoNgay(), entity.getCachSuDung(), entity.getTongSo(), entity.getDonGia());
    }

    @Override
    public void update(ChiTietToaThuoc entity) {
        String sql = "UPDATE ChiTietToaThuoc SET maToaThuoc = ?, maThuoc = ?, soNgay = ?, cachSuDung = ?, tongSo = ?, donGia = ? WHERE maChiTiet = ?";
        execute(sql, Refs.maToa(entity.getToaThuoc()), Refs.maThuoc(entity.getThuoc()), entity.getSoNgay(),
                entity.getCachSuDung(), entity.getTongSo(), entity.getDonGia(), entity.getMaChiTiet());
    }

    @Override
    public void delete(String maChiTiet) {
        execute("DELETE FROM ChiTietToaThuoc WHERE maChiTiet = ?", maChiTiet);
    }
}
