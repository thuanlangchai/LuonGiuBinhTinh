package entity;

import java.time.LocalDateTime;

public class KiemKho {

    private String maKiemKho;
    private String lyDo;
    private String duyet;
    private String nguoiDuyet;
    private LocalDateTime ngayTao;

    public KiemKho() {
    }

    public KiemKho(String maKiemKho, String lyDo, String duyet, String nguoiDuyet, LocalDateTime ngayTao) {
        this.maKiemKho = maKiemKho;
        this.lyDo = lyDo;
        this.duyet = duyet;
        this.nguoiDuyet = nguoiDuyet;
        this.ngayTao = ngayTao;
    }

    public String getMaKiemKho() {
        return maKiemKho;
    }

    public void setMaKiemKho(String maKiemKho) {
        this.maKiemKho = maKiemKho;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public String getDuyet() {
        return duyet;
    }

    public void setDuyet(String duyet) {
        this.duyet = duyet;
    }

    public String getNguoiDuyet() {
        return nguoiDuyet;
    }

    public void setNguoiDuyet(String nguoiDuyet) {
        this.nguoiDuyet = nguoiDuyet;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}
