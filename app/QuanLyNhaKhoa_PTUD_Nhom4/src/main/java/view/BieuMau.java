package view;

import view.model.CauHinhTrang;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public final class BieuMau {

    public interface Luu {
        void nhan(Object[] dong);
    }

    private static int demKiem = 2;

    private BieuMau() {
    }

    public static void mo(Component cha, CauHinhTrang c, Object[] cu, Luu luu) {
        Window w = cha instanceof Window ? (Window) cha : SwingUtilities.getWindowAncestor(cha);
        if ("dichvu".equals(c.ma)) {
            dichVu(w, cu, luu);
        } else if ("nhanvien".equals(c.ma)) {
            nhanVien(w, cu, luu);
        } else if ("loairang".equals(c.ma)) {
            loaiRang(w, cu, luu);
        } else if ("thoigian".equals(c.ma)) {
            thoiGian(w, cu, luu);
        } else if ("kiemkho".equals(c.ma)) {
            kiemKho(w, cu, luu);
        } else if ("loaisanpham".equals(c.ma)) {
            loaiSanPham(w, cu, luu);
        } else if ("lichhen-ds".equals(c.ma)) {
            lichHen(w, cu, luu);
        } else {
            macDinh(w, c, cu, luu);
        }
    }

    private static String gia(Object[] cu, int i) {
        if (cu == null || i < 0 || i >= cu.length || cu[i] == null) {
            return "";
        }
        return String.valueOf(cu[i]);
    }

    private static boolean sua(Object[] cu) {
        return cu != null;
    }

    private static void macDinh(Window w, CauHinhTrang c, Object[] cu, Luu luu) {
        String tieu = (sua(cu) ? "Cập nhật " : "Thêm ") + c.tieuDe.toLowerCase();
        HopThoai h = new HopThoai(w, tieu, sua(cu) ? "Lưu" : c.nutThem);
        JTextField[] o = new JTextField[c.cot.length];
        for (int i = 0; i < c.cot.length; i++) {
            o[i] = h.chu(c.cot[i], gia(cu, i), i == 0);
        }
        h.hien(() -> {
            Object[] dong = new Object[c.cot.length];
            for (int i = 0; i < o.length; i++) {
                dong[i] = o[i].getText().trim();
            }
            luu.nhan(dong);
        });
    }

    private static void dichVu(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật dịch vụ" : "Thêm dịch vụ",
                sua(cu) ? "Lưu" : "Thêm dịch vụ");
        JTextField ma = h.chu("Mã dịch vụ", gia(cu, 0), true);
        JComboBox<String> nhom = h.chonVoiNut("Nhóm dịch vụ", true, DuLieuMau.NHOM_DV);
        if (!gia(cu, 1).isEmpty()) {
            nhom.setSelectedItem(gia(cu, 1));
        }
        JTextField ten = h.chu("Tên dịch vụ", gia(cu, 2), true);
        JTextField gia = h.chu("Giá", gia(cu, 3).isEmpty() ? "0" : gia(cu, 3), false);
        JComboBox<String> tt = h.chon("Trạng thái", false, "Hoạt động", "Ngừng");
        if (!gia(cu, 6).isEmpty()) {
            tt.setSelectedItem(gia(cu, 6));
        }
        h.hien(() -> luu.nhan(new Object[]{
            ma.getText().trim(),
            String.valueOf(nhom.getSelectedItem()),
            ten.getText().trim(),
            gia.getText().trim(),
            String.valueOf(tt.getSelectedItem())
        }));
    }

    private static void nhanVien(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật nhân viên" : "Thêm nhân viên",
                sua(cu) ? "Lưu" : "Thêm nhân viên");
        JTextField ma = h.chu("Mã nhân viên", gia(cu, 0), true);
        JTextField chuc = h.chuGoiY("Chức danh", "Bs, Ths, Ts ...", false);
        chuc.setText(gia(cu, 1));
        JTextField ten = h.chu("Họ và tên", gia(cu, 2), true);
        JTextField email = h.chu("Email", gia(cu, 3), false);
        JTextField sdt = h.chu("Số điện thoại", gia(cu, 4), false);
        JComboBox<String> vaiTro = h.chon("Vai trò", true, "Bác sĩ", "Phụ tá", "Lễ tân", "Tư vấn", "Sale");
        if (!gia(cu, 5).isEmpty()) {
            vaiTro.setSelectedItem(gia(cu, 5));
        }
        JComboBox<String> kichHoat = h.chon("Kích hoạt", true, "Có", "Không");
        if (!gia(cu, 6).isEmpty()) {
            kichHoat.setSelectedItem(gia(cu, 6));
        }
        JComboBox<String> chiNhanh = h.chon("Chi nhánh", true, "CN1");
        if (!gia(cu, 7).isEmpty()) {
            chiNhanh.setSelectedItem(gia(cu, 7));
        }
        h.hien(() -> luu.nhan(new Object[]{
            ma.getText().trim(),
            chuc.getText().trim(),
            ten.getText().trim(),
            email.getText().trim(),
            sdt.getText().trim(),
            String.valueOf(vaiTro.getSelectedItem()),
            String.valueOf(kichHoat.getSelectedItem()),
            String.valueOf(chiNhanh.getSelectedItem())
        }));
    }


    private static void loaiRang(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật loại răng" : "Thêm loại răng",
                sua(cu) ? "Lưu" : "Thêm loại răng");
        JTextField ma = h.chu("Mã loại răng", gia(cu, 0), true);
        JTextField ten = h.chu("Tên loại răng", gia(cu, 1), true);
        h.hien(() -> luu.nhan(new Object[]{ma.getText().trim(), ten.getText().trim()}));
    }


    private static void thoiGian(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật thời gian làm việc" : "Thêm thời gian làm việc",
                sua(cu) ? "Lưu" : "Thêm thời gian làm việc");
        JPanel ngay = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        ngay.setOpaque(false);
        String[] nhan = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
        JCheckBox[] o = new JCheckBox[nhan.length];
        for (int i = 0; i < nhan.length; i++) {
            o[i] = new JCheckBox(nhan[i]);
            o[i].setOpaque(false);
            if (gia(cu, 1).contains(nhan[i])) {
                o[i].setSelected(true);
            }
            ngay.add(o[i]);
        }
        h.them("Ngày", ngay, null, true);
        JTextField ma = h.chu("Mã lịch", gia(cu, 0), true);
        JTextField bd = h.chu("Giờ bắt đầu", gia(cu, 2).isEmpty() ? "08:00" : gia(cu, 2), true);
        JTextField kt = h.chu("Giờ kết thúc", gia(cu, 3).isEmpty() ? "17:00" : gia(cu, 3), true);
        JComboBox<String> bs = h.chonVoiNut("Bác sĩ", true, DuLieuMau.BAC_SI);
        if (!gia(cu, 4).isEmpty()) {
            bs.setSelectedItem(gia(cu, 4));
        }
        JComboBox<String> cn = h.chon("Chi nhánh", true, "CN1");
        if (!gia(cu, 5).isEmpty()) {
            cn.setSelectedItem(gia(cu, 5));
        }
        h.hien(() -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < o.length; i++) {
                if (o[i].isSelected()) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(nhan[i]);
                }
            }
            if (sb.length() == 0) {
                sb.append("Mo, Tu, We, Th, Fr");
            }
            luu.nhan(new Object[]{ma.getText().trim(), sb.toString(), bd.getText().trim(), kt.getText().trim(),
                String.valueOf(bs.getSelectedItem()), String.valueOf(cn.getSelectedItem())});
        });
    }

    private static void kiemKho(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật Kiểm kho" : "Thêm Kiểm kho",
                sua(cu) ? "Lưu" : "Thêm Kiểm kho");
        JTextField ly = new JTextField(gia(cu, 1));
        ViewTheme.vienNhap(ly);
        h.them("Lý do", ly, ly, true);
        JPanel hang = dongSanPham();
        JPanel ds = new JPanel();
        ds.setLayout(new javax.swing.BoxLayout(ds, javax.swing.BoxLayout.Y_AXIS));
        ds.setOpaque(false);
        ds.add(hang);
        JButton cong = new JButton("+");
        ViewTheme.nutChinh(cong);
        cong.addActionListener(e -> {
            ds.add(dongSanPham());
            ds.revalidate();
        });
        JPanel giua = new JPanel(new FlowLayout(FlowLayout.CENTER));
        giua.setOpaque(false);
        giua.add(cong);
        h.themHang(kiemKho());
        h.themHang(ds);
        h.themHang(giua);
        h.hien(() -> {
            String ma = sua(cu) ? gia(cu, 0) : String.format("%06d", demKiem++);
            luu.nhan(new Object[]{ma, ly.getText().trim(), "Đã duyệt", DuLieuMau.NGUOI,
                sua(cu) && !gia(cu, 4).isEmpty() ? gia(cu, 4) : ViewTheme.bayGio()});
        });
    }

    private static JPanel kiemKho() {
        JPanel p = new JPanel(new GridLayout(1, 4, 0, 0));
        p.setOpaque(false);
        p.add(ViewTheme.chuPhu("Sản phẩm"));
        p.add(ViewTheme.chuPhu("Số lượng hệ thống"));
        p.add(ViewTheme.chuPhu("Số lượng thực tế"));
        p.add(ViewTheme.chuPhu("Chênh lệch"));
        p.add(ViewTheme.chuPhu(""));
        return p;
    }

    private static JPanel dongSanPham() {
        JPanel p = new JPanel(new java.awt.GridLayout(1, 5, 8, 0));
        p.setOpaque(false);
        p.setBorder(new javax.swing.border.EmptyBorder(4, 0, 4, 0));
        JComboBox<String> sp = new JComboBox<>(new String[]{"Găng tay", "Khẩu trang", "Mũi khoan", "aptx 4869"});
        ViewTheme.vienHop(sp);
        JTextField sl = new JTextField("0");
        JTextField lech = new JTextField("0");
        JTextField gia = new JTextField("0");
        ViewTheme.vienNhap(sl);
        ViewTheme.vienNhap(lech);
        ViewTheme.vienNhap(gia);
        JButton xoa = new JButton("Xóa");
        ViewTheme.nutDo(xoa);
        xoa.addActionListener(e -> {
            java.awt.Container cha = p.getParent();
            if (cha != null && cha.getComponentCount() > 1) {
                cha.remove(p);
                cha.revalidate();
                cha.repaint();
            }
        });
        p.add(sp);
        p.add(sl);
        p.add(lech);
        p.add(gia);
        p.add(xoa);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        return p;
    }

    private static void loaiSanPham(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật loại sản phẩm" : "Thêm loại sản phẩm",
                sua(cu) ? "Lưu" : "Thêm loại sản phẩm");
        JTextField ma = h.chu("Mã loại", gia(cu, 0), true);
        JTextField ten = h.chu("Tên loại sản phẩm", gia(cu, 1), true);
        h.hien(() -> luu.nhan(new Object[]{ma.getText().trim(), ten.getText().trim()}));
    }

    private static void lichHen(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật lịch hẹn" : "Thêm lịch hẹn",
                sua(cu) ? "Lưu" : "Thêm lịch hẹn");
        JTextField ma = h.chu("Mã lịch hẹn", gia(cu, 0), true);
        JTextField maKH = h.chu("Mã KH", gia(cu, 1), true);
        JTextField batDau = h.chu("Bắt đầu", gia(cu, 2).isEmpty() ? "2026-09-28 08:00" : gia(cu, 2), true);
        JComboBox<String> phut = h.chon("Thời lượng (phút)", true, "15", "30", "45", "60", "90");
        if (!gia(cu, 3).isEmpty()) {
            phut.setSelectedItem(gia(cu, 3));
        }
        JComboBox<String> bs = h.chon("Bác sĩ", false, "NV03", "NV02", "NV01");
        if (!gia(cu, 4).isEmpty()) {
            bs.setSelectedItem(gia(cu, 4));
        }
        JTextField phuTa = h.chu("Phụ tá", gia(cu, 5), false);
        JTextField ghi = h.chu("Ghi chú", gia(cu, 6), false);
        JComboBox<String> tt = h.chon("Trạng thái", false, "Mới", "Xác nhận", "Đang đợi", "Đang điều trị", "Hoàn tất", "Khách ra về", "Hủy bỏ");
        if (!gia(cu, 7).isEmpty()) {
            tt.setSelectedItem(gia(cu, 7));
        }
        h.hien(() -> luu.nhan(new Object[]{
            ma.getText().trim(),
            maKH.getText().trim(),
            batDau.getText().trim(),
            String.valueOf(phut.getSelectedItem()),
            String.valueOf(bs.getSelectedItem()),
            phuTa.getText().trim(),
            ghi.getText().trim(),
            String.valueOf(tt.getSelectedItem())
        }));
    }

}
