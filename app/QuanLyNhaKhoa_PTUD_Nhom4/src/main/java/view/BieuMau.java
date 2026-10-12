package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
        } else if ("congviec".equals(c.ma)) {
            congViec(w, cu, luu);
        } else if ("loairang".equals(c.ma)) {
            loaiRang(w, cu, luu);
        } else if ("voucher".equals(c.ma)) {
            voucher(w, cu, luu);
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
        HopThoai h = new HopThoai(w, tieu, sua(cu) ? "Lưu" : c.nutThem, "Tôi muốn tiếp tục thêm " + c.tieuDe.toLowerCase());
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
                sua(cu) ? "Lưu" : "Thêm dịch vụ", "Tôi muốn tiếp tục thêm dịch vụ");
        JTextField ten = h.chu("Tên dịch vụ", gia(cu, 1), true);
        h.chu("Viết tắt", "", false);
        h.chonVoiNut("Đơn vị dịch vụ", false, "Lần", "Răng", "Hàm");
        h.vung("Mô tả dịch vụ", "", 3);
        JTextField gia = h.chu("Giá", gia(cu, 2).isEmpty() ? "0" : gia(cu, 2), false);
        h.chu("Giá 1", "0", false);
        h.chu("Giá 2", "0", false);
        h.chu("Giá 3", "0", false);
        JComboBox<String> nhom = h.chonVoiNut("Nhóm dịch vụ", true, DuLieuMau.NHOM_DV);
        if (!gia(cu, 0).isEmpty()) {
            nhom.setSelectedItem(gia(cu, 0));
        }
        NutCongTac trangThai = h.congTac("Trạng thái", !"Ngưng".equals(gia(cu, 6)));
        NutCongTac labo = h.congTac("Cần đặt labo", "Có".equals(gia(cu, 5)));
        JTextField hoaHong = h.chu("Tỷ lệ hoa hồng", gia(cu, 3).isEmpty() ? "100" : gia(cu, 3), true);
        JTextField thue = h.chu("Thuế", gia(cu, 4).isEmpty() ? "8" : gia(cu, 4), false);
        h.hien(() -> luu.nhan(new Object[]{
            String.valueOf(nhom.getSelectedItem()),
            ten.getText().trim(),
            gia.getText().trim(),
            hoaHong.getText().trim(),
            thue.getText().trim(),
            labo.isSelected() ? "Có" : "Không",
            trangThai.isSelected() ? "Hoạt động" : "Ngưng"
        }));
    }

    private static void nhanVien(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật nhân viên" : "Thêm nhân viên",
                sua(cu) ? "Lưu" : "Thêm nhân viên", "Tôi muốn tiếp tục thêm nhân viên");
        JTextField chuc = h.chuGoiY("Chức danh", "Bs, Ths, Ts ...", false);
        chuc.setText(gia(cu, 0));
        JTextField ten = h.chu("Họ và tên", gia(cu, 1), true);
        JTextField vietTat = h.chu("Viết tắt", gia(cu, 2), false);
        JLabel anh = new JLabel(gia(cu, 1).isEmpty() ? "Chưa chọn ảnh" : "Đã có ảnh");
        JButton chonAnh = new JButton("Chọn ảnh");
        ViewTheme.nutXam(chonAnh);
        chonAnh.addActionListener(e -> {
            javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
            if (fc.showOpenDialog(h) == javax.swing.JFileChooser.APPROVE_OPTION) {
                anh.setText(fc.getSelectedFile().getName());
            }
        });
        JPanel hangAnh = new JPanel(new BorderLayout(8, 0));
        hangAnh.setOpaque(false);
        hangAnh.add(anh, BorderLayout.CENTER);
        hangAnh.add(chonAnh, BorderLayout.EAST);
        h.them("Hình đại diện", hangAnh, null, false);
        h.chon("Giới tính", false, "Nam", "Nữ", "Khác");
        h.chuGoiY("Ngày sinh", "dd/mm/yyyy", false);
        JTextField email = h.chu("Địa chỉ email", gia(cu, 7), false);
        JTextField sdt = h.chu("Số điện thoại", gia(cu, 8), false);
        h.chu("Địa chỉ", "", false);
        h.congTac("Xác nhận email", false);
        h.congTac("Đồng bộ lịch", false);
        JComboBox<String> vaiTro = h.chon("Vai trò", true, "Bác sĩ", "Phụ tá", "Lễ tân", "Tư vấn", "Sale");
        if (!gia(cu, 9).isEmpty()) {
            vaiTro.setSelectedItem(gia(cu, 9));
        }
        h.chon("Quét QR", false, "Đang đợi", "Đang điều trị", "Hoàn tất");
        NutCongTac kichHoat = h.congTac("Kích hoạt", !"Không".equals(gia(cu, 10)));
        h.hien(() -> luu.nhan(new Object[]{
            chuc.getText().trim(),
            ten.getText().trim(),
            vietTat.getText().trim(),
            gia(cu, 3).isEmpty() ? "0" : gia(cu, 3),
            gia(cu, 4).isEmpty() ? "0" : gia(cu, 4),
            gia(cu, 5).isEmpty() ? "0" : gia(cu, 5),
            gia(cu, 6).isEmpty() ? "0" : gia(cu, 6),
            email.getText().trim(),
            sdt.getText().trim(),
            String.valueOf(vaiTro.getSelectedItem()),
            kichHoat.isSelected() ? "Có" : "Không"
        }));
    }

    private static void congViec(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật công việc" : "Thêm công việc",
                sua(cu) ? "Lưu" : "Thêm công việc", "Tôi muốn tiếp tục thêm công việc");
        JTextField ten = h.chu("Công việc", gia(cu, 0), true);
        JComboBox<String> phut = h.chon("Thời lượng theo phút", false, "15", "30", "45", "60", "90", "120");
        if (!gia(cu, 1).isEmpty()) {
            phut.setSelectedItem(gia(cu, 1));
        }
        JComboBox<String> dv = h.chon("Dịch vụ", false, ghep(new String[]{""}, DuLieuMau.TEN_DV));
        if (!gia(cu, 2).isEmpty()) {
            dv.setSelectedItem(gia(cu, 2));
        }
        h.hien(() -> luu.nhan(new Object[]{
            ten.getText().trim(),
            String.valueOf(phut.getSelectedItem()),
            String.valueOf(dv.getSelectedItem()),
            gia(cu, 3).isEmpty() ? ViewTheme.bayGio() : gia(cu, 3)
        }));
    }

    private static void loaiRang(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật loại răng" : "Thêm loại răng",
                sua(cu) ? "Lưu" : "Thêm loại răng", "Tôi muốn tiếp tục thêm loại răng");
        JTextField ten = h.chu("Loại răng", gia(cu, 0), true);
        JComboBox<String> bh = h.chon("Bảo hành", false, "6 tháng", "12 tháng", "24 tháng", "Không");
        if (!gia(cu, 1).isEmpty()) {
            bh.setSelectedItem(gia(cu, 1));
        }
        h.hien(() -> luu.nhan(new Object[]{ten.getText().trim(), String.valueOf(bh.getSelectedItem())}));
    }

    private static void voucher(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật Voucher - Khuyến mãi" : "Thêm Voucher - Khuyến mãi",
                sua(cu) ? "Lưu" : "Thêm Voucher - Khuyến mãi", "Tôi muốn tiếp tục thêm Voucher - Khuyến mãi");
        JTextField ten = h.chu("Tên Voucher - Khuyến mãi", gia(cu, 0), false);
        JTextField tu = h.chu("Từ ngày", gia(cu, 2).isEmpty() ? "2026-09-29" : gia(cu, 2), false);
        JTextField den = h.chu("Đến ngày", gia(cu, 3).isEmpty() ? "2026-10-28" : gia(cu, 3), false);
        h.chon("Loại Voucher - Khuyến mãi", false, "Giảm giá theo phần trăm (%)", "Giảm số tiền", "Tặng dịch vụ");
        JTextField phanTram = h.chu("Phần trăm", gia(cu, 1).isEmpty() ? "5" : gia(cu, 1), false);
        h.chu("Tiền tố voucher", "", false);
        h.chu("Số lượng voucher", "1", false);
        h.chu("Số lượt dùng của mỗi voucher", "1", false);
        NutCongTac tt = h.congTac("Trạng thái", !"Ngưng".equals(gia(cu, 4)));
        h.hien(() -> luu.nhan(new Object[]{
            ten.getText().trim(),
            phanTram.getText().trim(),
            tu.getText().trim(),
            den.getText().trim(),
            tt.isSelected() ? "Hoạt động" : "Ngưng",
            gia(cu, 5).isEmpty() ? ViewTheme.bayGio() : gia(cu, 5)
        }));
    }

    private static void thoiGian(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật thời gian làm việc" : "Thêm thời gian làm việc",
                sua(cu) ? "Lưu" : "Thêm thời gian làm việc", "Tôi muốn tiếp tục thêm thời gian làm việc");
        JPanel ngay = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        ngay.setOpaque(false);
        String[] nhan = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
        JCheckBox[] o = new JCheckBox[nhan.length];
        for (int i = 0; i < nhan.length; i++) {
            o[i] = new JCheckBox(nhan[i]);
            o[i].setOpaque(false);
            if (gia(cu, 0).contains(nhan[i])) {
                o[i].setSelected(true);
            }
            ngay.add(o[i]);
        }
        h.them("Ngày", ngay, null, true);
        JTextField bd = h.chu("Giờ bắt đầu", gia(cu, 1).isEmpty() ? "08:00" : gia(cu, 1), true);
        JTextField kt = h.chu("Giờ kết thúc", gia(cu, 2).isEmpty() ? "17:00" : gia(cu, 2), true);
        JComboBox<String> cn = h.chon("Chi nhánh", true, "Quang Thanh");
        JComboBox<String> bs = h.chonVoiNut("Bác sĩ", true, DuLieuMau.BAC_SI);
        if (!gia(cu, 3).isEmpty()) {
            bs.setSelectedItem(gia(cu, 3));
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
            luu.nhan(new Object[]{sb.toString(), bd.getText().trim(), kt.getText().trim(),
                String.valueOf(bs.getSelectedItem()), String.valueOf(cn.getSelectedItem())});
        });
    }

    private static void kiemKho(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật Kiểm kho" : "Thêm Kiểm kho",
                sua(cu) ? "Lưu" : "Thêm Kiểm kho", "Tôi muốn tiếp tục thêm Kiểm kho");
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
        h.themHang(tieuCotKiem());
        h.themHang(ds);
        h.themHang(giua);
        h.hien(() -> {
            String ma = sua(cu) ? gia(cu, 0) : String.format("%06d", demKiem++);
            luu.nhan(new Object[]{ma, ly.getText().trim(), "Đã duyệt", DuLieuMau.NGUOI,
                sua(cu) && !gia(cu, 4).isEmpty() ? gia(cu, 4) : ViewTheme.bayGio()});
        });
    }

    private static JPanel tieuCotKiem() {
        JPanel p = new JPanel(new java.awt.GridLayout(1, 4, 8, 0));
        p.setOpaque(false);
        p.add(ViewTheme.chuPhu("Sản phẩm"));
        p.add(ViewTheme.chuPhu("Số lượng thực tế"));
        p.add(ViewTheme.chuPhu("Chênh lệch"));
        p.add(ViewTheme.chuPhu("Giá trị chênh lệch"));
        return p;
    }

    private static JPanel dongSanPham() {
        JPanel p = new JPanel(new java.awt.GridLayout(1, 5, 8, 0));
        p.setOpaque(false);
        p.setBorder(new javax.swing.border.EmptyBorder(4, 0, 4, 0));
        JComboBox<String> sp = new JComboBox<String>(new String[]{"Găng tay", "Khẩu trang", "Mũi khoan", "aptx 4869"});
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
                sua(cu) ? "Lưu" : "Thêm loại sản phẩm", "Tôi muốn tiếp tục thêm loại sản phẩm");
        JTextField ten = h.chu("Loại sản phẩm", gia(cu, 0), true);
        JComboBox<String> kho = h.chon("Loại kho", true, "Thuốc", "Vật liệu tiêu hao", "Công cụ tiêu hao", "Hàng hoá", "Tài sản cố định", "Thiết bị nha khoa", "Công cụ, dụng cụ");
        if (!gia(cu, 1).isEmpty()) {
            kho.setSelectedItem(gia(cu, 1));
        }
        h.hien(() -> luu.nhan(new Object[]{ten.getText().trim(), String.valueOf(kho.getSelectedItem())}));
    }

    private static void lichHen(Window w, Object[] cu, Luu luu) {
        HopThoai h = new HopThoai(w, sua(cu) ? "Cập nhật lịch hẹn" : "Thêm lịch hẹn",
                sua(cu) ? "Lưu" : "Thêm lịch hẹn", "Tôi muốn tiếp tục thêm lịch hẹn");
        JComboBox<String> viec = h.chon("Công việc", true, "Chỉnh nha", "Cạo vôi răng", "Tái khám", "Thăm khám", "Nhổ răng");
        if (!gia(cu, 0).isEmpty()) {
            viec.setSelectedItem(gia(cu, 0));
        }
        JComboBox<String> kh = h.chonVoiNut("Khách hàng", true, DuLieuMau.KHACH);
        JTextField batDau = h.chu("Bắt đầu", gia(cu, 1).isEmpty() ? "2026-09-28 08:00" : gia(cu, 1), true);
        h.chon("Dự kiến", true, "15", "30", "45", "60", "90");
        JComboBox<String> bs = h.chon("Người làm chính", false, DuLieuMau.BAC_SI);
        if (!gia(cu, 3).isEmpty()) {
            bs.setSelectedItem(gia(cu, 3));
        }
        h.chon("Phụ tá", false, "", "Nguyễn Khắc Duy", "Hồ Văn Khánh");
        h.chonVoiNut("Tài nguyên", false, "Ghế 1", "Ghế 2", "Máy CT");
        h.vung("Ghi chú", "", 3);
        h.chon("Trạng thái", false, "Mới", "Xác nhận", "Đang đợi", "Đang điều trị", "Hoàn tất", "Khách ra về", "Hủy bỏ");
        h.hien(() -> luu.nhan(new Object[]{
            String.valueOf(viec.getSelectedItem()),
            batDau.getText().trim(),
            String.valueOf(kh.getSelectedItem()),
            String.valueOf(bs.getSelectedItem())
        }));
    }

    private static String[] ghep(String[] a, String[] b) {
        String[] r = new String[a.length + b.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
    }
}
