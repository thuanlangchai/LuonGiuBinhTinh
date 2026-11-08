package dto;

import entity.HoaDon;


public class HoaDonDong {        
        public final HoaDon hoaDon;
        public final long tongTien;
        public final long daThu;

        public HoaDonDong(HoaDon hoaDon, long tongTien, long daThu) {
            this.hoaDon = hoaDon;
            this.tongTien = tongTien;
            this.daThu = daThu;
        }

        public HoaDon getHoaDon() {
            return hoaDon;
        }

        public long getTongTien() {
            return tongTien;
        }

        public long getDaThu() {
            return daThu;
        }    
}
