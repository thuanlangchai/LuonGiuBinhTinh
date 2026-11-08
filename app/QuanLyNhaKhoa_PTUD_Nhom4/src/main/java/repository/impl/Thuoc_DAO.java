package repository.impl;

import entity.Refs;
import entity.Thuoc;
import java.util.List;
import mapper.MaMapper;
import mapper.ThuocMapper;
import repository.Thuoc_IDAO;

public class Thuoc_DAO extends AbstractDAO<Thuoc> implements Thuoc_IDAO {

    private static final String SELECT = """
            SELECT t.maThuoc, t.tenThuoc, t.gia, t.cachDung, dv.maDonVi, dv.tenDonVi
            FROM Thuoc t
            LEFT JOIN DonVi dv ON dv.maDonVi = t.maDonVi
            """;

    private final ThuocMapper mapper = new ThuocMapper();

    @Override
    public List<Thuoc> findAllThuoc() {
        return query(SELECT + " ORDER BY t.maThuoc", mapper);
    }

    @Override
    public Thuoc findById(String maThuoc) {
        return one(SELECT + " WHERE t.maThuoc = ?", mapper, maThuoc);
    }

    public List<String> findExisting(List<String> ids) {
        return queryIn("SELECT maThuoc FROM Thuoc WHERE maThuoc", ids, new MaMapper("maThuoc"));
    }

    @Override
    public Integer add(Thuoc entity) {
        return execute("INSERT INTO Thuoc(maThuoc,tenThuoc,maDonVi,gia,cachDung) VALUES(?,?,?,?,?)",
                entity.getMaThuoc(), entity.getTenThuoc(), Refs.maDonVi(entity.getDonVi()), entity.getGia(), entity.getCachDung());
    }

    @Override
    public void update(Thuoc entity) {
        execute("UPDATE Thuoc SET tenThuoc = ?, maDonVi = ?, gia = ?, cachDung = ? WHERE maThuoc = ?",
                entity.getTenThuoc(), Refs.maDonVi(entity.getDonVi()), entity.getGia(), entity.getCachDung(), entity.getMaThuoc());
    }

    @Override
    public void delete(String maThuoc) {
        execute("DELETE FROM Thuoc WHERE maThuoc = ?", maThuoc);
    }
}
