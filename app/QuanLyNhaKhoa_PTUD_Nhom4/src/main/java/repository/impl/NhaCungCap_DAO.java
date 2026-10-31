package repository.impl;

import entity.NhaCungCap;
import i_repository.NhaCungCap_IDAO;
import java.util.List;
import mapper.NhaCungCapMapper;


public class NhaCungCap_DAO extends AbstractDAO<NhaCungCap> implements NhaCungCap_IDAO {

    @Override
    public List<NhaCungCap> findAllNhaCungCap() {
        String sql = "SELECT * FROM NhaCungCap";
        return findAll(sql, new NhaCungCapMapper());
    }

    @Override
    public NhaCungCap findById(String maNhaCungCap) {
        String sql = "SELECT * FROM NhaCungCap WHERE maNhaCungCap = ?";
        List<NhaCungCap> ds = findAll(sql, new NhaCungCapMapper(), maNhaCungCap);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(NhaCungCap entity) {
        String sql = "INSERT INTO NhaCungCap(maNhaCungCap,tenNhaCungCap,dienThoai,diaChi) VALUES(?,?,?,?)";
        return add(sql, entity.getMaNhaCungCap(), entity.getTenNhaCungCap(), entity.getDienThoai(), entity.getDiaChi());
    }

    @Override
    public void update(NhaCungCap entity) {
        String sql = "UPDATE NhaCungCap SET tenNhaCungCap = ?, dienThoai = ?, diaChi = ? WHERE maNhaCungCap = ?";
        update(sql, entity.getTenNhaCungCap(), entity.getDienThoai(), entity.getDiaChi(), entity.getMaNhaCungCap());
    }

    @Override
    public void delete(String maNhaCungCap) {
        String sql = "DELETE FROM NhaCungCap WHERE maNhaCungCap = ?";
        delete(sql, maNhaCungCap);
    }
}
