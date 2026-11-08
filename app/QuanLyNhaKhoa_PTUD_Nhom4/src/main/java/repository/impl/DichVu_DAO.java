package repository.impl;

import entity.DichVu;
import entity.Refs;
import java.util.List;
import mapper.DichVuMapper;
import repository.DichVu_IDAO;

public class DichVu_DAO extends AbstractDAO<DichVu> implements DichVu_IDAO {

    private static final String SELECT = """
            SELECT dv.maDichVu, dv.tenDichVu, dv.gia, dv.trangThai, n.maNhom, n.tenNhomDichVu
            FROM DichVu dv
            JOIN NhomDichVu n ON n.maNhom = dv.maNhomDichVu
            """;

    private final DichVuMapper mapper = new DichVuMapper();

    @Override
    public List<DichVu> findAllDichVu() {
        return query(SELECT + " ORDER BY dv.maDichVu", mapper);
    }

    @Override
    public DichVu findById(String maDichVu) {
        return one(SELECT + " WHERE dv.maDichVu = ?", mapper, maDichVu);
    }

    @Override
    public Integer add(DichVu entity) {
        return execute("INSERT INTO DichVu(maDichVu,maNhomDichVu,tenDichVu,gia,trangThai) VALUES(?,?,?,?,?)",
                entity.getMaDichVu(), Refs.maNhomDv(entity.getNhomDichVu()), entity.getTenDichVu(), entity.getGia(), entity.getTrangThai());
    }

    @Override
    public void update(DichVu entity) {
        execute("UPDATE DichVu SET maNhomDichVu = ?, tenDichVu = ?, gia = ?, trangThai = ? WHERE maDichVu = ?",
                Refs.maNhomDv(entity.getNhomDichVu()), entity.getTenDichVu(), entity.getGia(), entity.getTrangThai(), entity.getMaDichVu());
    }

    @Override
    public void delete(String maDichVu) {
        execute("DELETE FROM DichVu WHERE maDichVu = ?", maDichVu);
    }
}
