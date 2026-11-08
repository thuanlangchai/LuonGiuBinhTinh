package repository.impl;

import entity.Rang;
import entity.Refs;
import java.util.List;
import mapper.RangMapper;
import repository.Rang_IDAO;

public class Rang_DAO extends AbstractDAO<Rang> implements Rang_IDAO {

    private static final String SELECT = """
            SELECT r.maRang, r.tenRang, lr.maLoaiRang, lr.tenLoaiRang
            FROM Rang r
            LEFT JOIN LoaiRang lr ON lr.maLoaiRang = r.maLoaiRang
            """;

    private final RangMapper mapper = new RangMapper();

    @Override
    public List<Rang> findAllRang() {
        return query(SELECT + " ORDER BY r.maRang", mapper);
    }

    @Override
    public Rang findById(String maRang) {
        return one(SELECT + " WHERE r.maRang = ?", mapper, maRang);
    }

    @Override
    public Integer add(Rang entity) {
        return execute("INSERT INTO Rang(maRang,tenRang,maLoaiRang) VALUES(?,?,?)",
                entity.getMaRang(), entity.getTenRang(), Refs.maLoaiRang(entity.getLoaiRang()));
    }

    @Override
    public void update(Rang entity) {
        execute("UPDATE Rang SET tenRang = ?, maLoaiRang = ? WHERE maRang = ?",
                entity.getTenRang(), Refs.maLoaiRang(entity.getLoaiRang()), entity.getMaRang());
    }

    @Override
    public void delete(String maRang) {
        execute("DELETE FROM Rang WHERE maRang = ?", maRang);
    }
}
