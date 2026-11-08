package repository.impl;

import entity.ChiTietNhapKho;
import entity.Refs;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mapper.ChiTietNhapKhoMapper;
import repository.ChiTietNhapKho_IDAO;

public class ChiTietNhapKho_DAO extends AbstractDAO<ChiTietNhapKho> implements ChiTietNhapKho_IDAO {

    private static final String SELECT = """
            SELECT ct.maChiTiet, ct.soLuong, ct.donGia, ct.maPhieuNhap, sp.maSanPham, sp.tenSanPham
            FROM ChiTietNhapKho ct
            JOIN SanPham sp ON sp.maSanPham = ct.maSanPham
            """;

    private final ChiTietNhapKhoMapper mapper = new ChiTietNhapKhoMapper();

    @Override
    public List<ChiTietNhapKho> findAllChiTietNhapKho() {
        return query(SELECT, mapper);
    }

    @Override
    public ChiTietNhapKho findById(String maChiTiet) {
        return one(SELECT + " WHERE ct.maChiTiet = ?", mapper, maChiTiet);
    }

    @Override
    public List<ChiTietNhapKho> findByMaPhieuNhap(String maPhieuNhap) {
        return query(SELECT + " WHERE ct.maPhieuNhap = ?", mapper, maPhieuNhap);
    }

    public Map<String, List<ChiTietNhapKho>> findByMaPhieuNhapIn(List<String> ids) {
        Map<String, List<ChiTietNhapKho>> map = new LinkedHashMap<>();
        for (ChiTietNhapKho row : queryIn(SELECT + " WHERE ct.maPhieuNhap", ids, mapper)) {
            String ma = row.getPhieuNhapKho() == null ? null : row.getPhieuNhapKho().getMaPhieuNhap();
            map.computeIfAbsent(ma, key -> new ArrayList<>()).add(row);
        }
        return map;
    }

    @Override
    public Integer add(ChiTietNhapKho entity) {
        String maPhieu = entity.getPhieuNhapKho() == null ? null : entity.getPhieuNhapKho().getMaPhieuNhap();
        return execute("INSERT INTO ChiTietNhapKho(maChiTiet,maPhieuNhap,maSanPham,soLuong,donGia) VALUES(?,?,?,?,?)",
                entity.getMaChiTiet(), maPhieu, Refs.maSp(entity.getSanPham()), entity.getSoLuong(), entity.getDonGia());
    }

    @Override
    public void update(ChiTietNhapKho entity) {
        String maPhieu = entity.getPhieuNhapKho() == null ? null : entity.getPhieuNhapKho().getMaPhieuNhap();
        execute("UPDATE ChiTietNhapKho SET maPhieuNhap = ?, maSanPham = ?, soLuong = ?, donGia = ? WHERE maChiTiet = ?",
                maPhieu, Refs.maSp(entity.getSanPham()), entity.getSoLuong(), entity.getDonGia(), entity.getMaChiTiet());
    }

    @Override
    public void delete(String maChiTiet) {
        execute("DELETE FROM ChiTietNhapKho WHERE maChiTiet = ?", maChiTiet);
    }
}
