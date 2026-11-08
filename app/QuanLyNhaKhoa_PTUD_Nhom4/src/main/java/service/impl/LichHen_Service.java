package service.impl;

import database.BusinessException;
import entity.LichHen;
import entity.Refs;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import repository.impl.LichHen_DAO;
import service.LichHen_IService;

public class LichHen_Service implements LichHen_IService {

    private static final DateTimeFormatter GIO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final LichHen_DAO dao;

    public LichHen_Service() {
        this(new LichHen_DAO());
    }

    public LichHen_Service(LichHen_DAO dao) {
        this.dao = dao;
    }

    @Override
    public List<LichHen> findAllLichHen() {
        return dao.findAllLichHen();
    }

    public List<LichHen> findTheoKhoang(LocalDateTime tu, LocalDateTime den) {
        return dao.findTheoKhoang(tu, den);
    }

    public LocalDateTime batDauSomNhat() {
        return dao.batDauSomNhat();
    }

    public int nextSo() {
        return dao.nextSo();
    }

    @Override
    public LichHen findById(String maLichHen) {
        return dao.findById(maLichHen);
    }

    @Override
    public List<LichHen> findByMaKH(String maKH) {
        return dao.findByMaKH(maKH);
    }

    @Override
    public Integer add(LichHen entity) {
        kiemTraTrung(entity);
        return dao.add(entity);
    }

    @Override
    public void update(LichHen entity) {
        kiemTraTrung(entity);
        dao.update(entity);
    }

    @Override
    public void delete(String maLichHen) {
        dao.delete(maLichHen);
    }

    public void kiemTraTrung(LichHen entity) {
        if (entity.getBatDau() == null || entity.getThoiLuongPhut() <= 0) {
            throw new BusinessException("Lịch hẹn thiếu thời gian bắt đầu hoặc thời lượng.");
        }
        LocalDateTime ketThuc = entity.getBatDau().plusMinutes(entity.getThoiLuongPhut());
        String ma = entity.getMaLichHen();
        baoTrung(dao.timTrung(ma, Refs.maKh(entity.getKhachHang()), null, null, entity.getBatDau(), ketThuc),
                "Khách hàng này đã có lịch hẹn khác trùng thời gian");
        if (!Refs.blank(Refs.maNv(entity.getBacSi()))) {
            baoTrung(dao.timTrung(ma, null, Refs.maNv(entity.getBacSi()), null, entity.getBatDau(), ketThuc),
                    "Bác sĩ này đã bận vào khung giờ này");
        }
        if (!Refs.blank(Refs.maNv(entity.getPhuTa()))) {
            baoTrung(dao.timTrung(ma, null, null, Refs.maNv(entity.getPhuTa()), entity.getBatDau(), ketThuc),
                    "Phụ tá này đã được phân công vào khung giờ này");
        }
    }

    private static void baoTrung(LichHen trung, String loi) {
        if (trung == null) {
            return;
        }
        String luc = trung.getBatDau() == null ? "" : trung.getBatDau().format(GIO);
        throw new BusinessException(loi + ".\n(Thời gian: " + luc + " - Mã lịch: " + trung.getMaLichHen() + ")");
    }
}
