package repository.impl;

import entity.ChiTietXuatKho;
import entity.Refs;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mapper.ChiTietXuatKhoMapper;
import repository.ChiTietXuatKho_IDAO;

public class ChiTietXuatKho_DAO extends AbstractDAO<ChiTietXuatKho> implements ChiTietXuatKho_IDAO {

    private static final String SELECT = """
            SELECT ct.maChiTiet, ct.soLuong, ct.donGia, ct.thanhTien, ct.maPhieuXuat, sp.maSanPham, sp.tenSanPham
            FROM ChiTietXuatKho ct
            JOIN SanPham sp ON sp.maSanPham = ct.maSanPham
            """;

    private final ChiTietXuatKhoMapper mapper = new ChiTietXuatKhoMapper();

    @Override
    public List<ChiTietXuatKho> findAllChiTietXuatKho() {
        return query(SELECT, mapper);
    }

    @Override
    public ChiTietXuatKho findById(String maChiTiet) {
        return one(SELECT + " WHERE ct.maChiTiet = ?", mapper, maChiTiet);
    }

    @Override
    public List<ChiTietXuatKho> findByMaPhieuXuat(String maPhieuXuat) {
        return query(SELECT + " WHERE ct.maPhieuXuat = ?", mapper, maPhieuXuat);
    }

    public Map<String, List<ChiTietXuatKho>> findByMaPhieuXuatIn(List<String> ids) {
        Map<String, List<ChiTietXuatKho>> map = new LinkedHashMap<>();
        for (ChiTietXuatKho row : queryIn(SELECT + " WHERE ct.maPhieuXuat", ids, mapper)) {
            String ma = row.getPhieuXuatKho() == null ? null : row.getPhieuXuatKho().getMaPhieuXuat();
            map.computeIfAbsent(ma, key -> new ArrayList<>()).add(row);
        }
        return map;
    }

    @Override
    public Integer add(ChiTietXuatKho entity) {
        String maPhieu = entity.getPhieuXuatKho() == null ? null : entity.getPhieuXuatKho().getMaPhieuXuat();
        return execute("INSERT INTO ChiTietXuatKho(maChiTiet,maPhieuXuat,maSanPham,soLuong,donGia,thanhTien) VALUES(?,?,?,?,?,?)",
                entity.getMaChiTiet(), maPhieu, Refs.maSp(entity.getSanPham()), entity.getSoLuong(), entity.getDonGia(), entity.getThanhTien());
    }

    @Override
    public void update(ChiTietXuatKho entity) {
        String maPhieu = entity.getPhieuXuatKho() == null ? null : entity.getPhieuXuatKho().getMaPhieuXuat();
        execute("UPDATE ChiTietXuatKho SET maPhieuXuat = ?, maSanPham = ?, soLuong = ?, donGia = ?, thanhTien = ? WHERE maChiTiet = ?",
                maPhieu, Refs.maSp(entity.getSanPham()), entity.getSoLuong(), entity.getDonGia(), entity.getThanhTien(), entity.getMaChiTiet());
    }

    @Override
    public void delete(String maChiTiet) {
        execute("DELETE FROM ChiTietXuatKho WHERE maChiTiet = ?", maChiTiet);
    }
}
