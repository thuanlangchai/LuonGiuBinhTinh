package repository.impl;

import entity.Refs;
import entity.ThoiGianLamViec;
import java.util.List;
import mapper.ThoiGianLamViecMapper;
import repository.ThoiGianLamViec_IDAO;

public class ThoiGianLamViec_DAO extends AbstractDAO<ThoiGianLamViec> implements ThoiGianLamViec_IDAO {

    private static final String SELECT = """
            SELECT tg.maLich, tg.ngay, tg.gioBatDau, tg.gioKetThuc,
                   bs.maNhanVien AS maBacSi, bs.hoTen AS tenBacSi,
                   cn.maChiNhanh, cn.tenChiNhanh
            FROM ThoiGianLamViec tg
            LEFT JOIN NhanVien bs ON bs.maNhanVien = tg.maBacSi
            LEFT JOIN ChiNhanh cn ON cn.maChiNhanh = tg.maChiNhanh
            """;

    private final ThoiGianLamViecMapper mapper = new ThoiGianLamViecMapper();

    @Override
    public List<ThoiGianLamViec> findAllThoiGianLamViec() {
        return query(SELECT + " ORDER BY tg.maLich", mapper);
    }

    @Override
    public ThoiGianLamViec findById(String maLich) {
        return one(SELECT + " WHERE tg.maLich = ?", mapper, maLich);
    }

    @Override
    public Integer add(ThoiGianLamViec entity) {
        return execute("INSERT INTO ThoiGianLamViec(maLich,ngay,gioBatDau,gioKetThuc,maBacSi,maChiNhanh) VALUES(?,?,?,?,?,?)",
                entity.getMaLich(), entity.getNgay(), entity.getGioBatDau(), entity.getGioKetThuc(),
                Refs.maNv(entity.getBacSi()), Refs.maCn(entity.getChiNhanh()));
    }

    @Override
    public void update(ThoiGianLamViec entity) {
        execute("UPDATE ThoiGianLamViec SET ngay = ?, gioBatDau = ?, gioKetThuc = ?, maBacSi = ?, maChiNhanh = ? WHERE maLich = ?",
                entity.getNgay(), entity.getGioBatDau(), entity.getGioKetThuc(), Refs.maNv(entity.getBacSi()),
                Refs.maCn(entity.getChiNhanh()), entity.getMaLich());
    }

    @Override
    public void delete(String maLich) {
        execute("DELETE FROM ThoiGianLamViec WHERE maLich = ?", maLich);
    }
}
