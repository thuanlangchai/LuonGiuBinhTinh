package repository.impl;

import entity.ToaThuoc;
import i_repository.ToaThuoc_IDAO;
import java.util.List;
import mapper.ToaThuocMapper;


public class ToaThuoc_DAO extends AbstractDAO<ToaThuoc> implements ToaThuoc_IDAO {

    @Override
    public List<ToaThuoc> findAllToaThuoc() {
        String sql = "SELECT * FROM ToaThuoc";
        return findAll(sql, new ToaThuocMapper());
    }

    @Override
    public ToaThuoc findById(String maToaThuoc) {
        String sql = "SELECT * FROM ToaThuoc WHERE maToaThuoc = ?";
        List<ToaThuoc> ds = findAll(sql, new ToaThuocMapper(), maToaThuoc);
        if (ds == null || ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public Integer add(ToaThuoc entity) {
        String sql = "INSERT INTO ToaThuoc(maToaThuoc,maKH,maChanDoan,maBacSi,hinhThucThanhToan,nhanTaiPhongKham,ghiChu,thanhTien,ngayTao) VALUES(?,?,?,?,?,?,?,?,?)";
        return add(sql, entity.getMaToaThuoc(), entity.getMaKH(), entity.getMaChanDoan(), entity.getMaBacSi(), entity.getHinhThucThanhToan(), entity.isNhanTaiPhongKham(), entity.getGhiChu(), entity.getThanhTien(), entity.getNgayTao());
    }

    @Override
    public void update(ToaThuoc entity) {
        String sql = "UPDATE ToaThuoc SET maKH = ?, maChanDoan = ?, maBacSi = ?, hinhThucThanhToan = ?, nhanTaiPhongKham = ?, ghiChu = ?, thanhTien = ?, ngayTao = ? WHERE maToaThuoc = ?";
        update(sql, entity.getMaKH(), entity.getMaChanDoan(), entity.getMaBacSi(), entity.getHinhThucThanhToan(), entity.isNhanTaiPhongKham(), entity.getGhiChu(), entity.getThanhTien(), entity.getNgayTao(), entity.getMaToaThuoc());
    }

    @Override
    public void delete(String maToaThuoc) {
        String sql = "DELETE FROM ToaThuoc WHERE maToaThuoc = ?";
        delete(sql, maToaThuoc);
    }
}
