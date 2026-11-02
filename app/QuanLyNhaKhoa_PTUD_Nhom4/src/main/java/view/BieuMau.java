package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import view.model.CauHinhTrang;

public final class BieuMau {

    public interface Luu {
        void nhan(Object[] dong);
    }

    private static int demKiem = 2;

    private BieuMau() {
    }

    public static void mo(Component cha, CauHinhTrang cauHinh, Object[] cu, Luu luu) {
        Window cuaSo = cha instanceof Window ? (Window) cha : SwingUtilities.getWindowAncestor(cha);
        if ("dichvu".equals(cauHinh.ma)) {
            dichVu(cuaSo, cu, luu);
        } else if ("nhanvien".equals(cauHinh.ma)) {
            nhanVien(cuaSo, cu, luu);
        } else if ("loairang".equals(cauHinh.ma)) {
            loaiRang(cuaSo, cu, luu);
        } else if ("thoigian".equals(cauHinh.ma)) {
            thoiGian(cuaSo, cu, luu);
        } else if ("kiemkho".equals(cauHinh.ma)) {
            kiemKho(cuaSo, cu, luu);
        } else if ("loaisanpham".equals(cauHinh.ma)) {
            loaiSanPham(cuaSo, cu, luu);
        } else if ("lichhen-ds".equals(cauHinh.ma)) {
            lichHen(cuaSo, cu, luu);
        } else {
            macDinh(cuaSo, cauHinh, cu, luu);
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

    private static void macDinh(Window cuaSo, CauHinhTrang cauHinh, Object[] cu, Luu luu) {
        String tieuDe = (sua(cu) ? "Cập nhật " : "Thêm ") + cauHinh.tieuDe.toLowerCase();
        HopThoai hopThoai = new HopThoai(cuaSo, tieuDe, sua(cu) ? "Lưu" : cauHinh.nutThem);
        JTextField[] txtCacTruong = new JTextField[cauHinh.cot.length];
        for (int i = 0; i < cauHinh.cot.length; i++) {
            txtCacTruong[i] = hopThoai.chu(cauHinh.cot[i], gia(cu, i), i == 0);
        }
        hopThoai.hien(() -> {
            Object[] dong = new Object[cauHinh.cot.length];
            for (int i = 0; i < txtCacTruong.length; i++) {
                dong[i] = txtCacTruong[i].getText().trim();
            }
            luu.nhan(dong);
        });
    }

    private static void dichVu(Window cuaSo, Object[] cu, Luu luu) {
        HopThoai hopThoai = new HopThoai(cuaSo, sua(cu) ? "Cập nhật dịch vụ" : "Thêm dịch vụ",
                sua(cu) ? "Lưu" : "Thêm dịch vụ");
        JTextField txtMa = hopThoai.chu("Mã dịch vụ", gia(cu, 0), true);
        JComboBox<String> cboNhom = hopThoai.chonVoiNut("Nhóm dịch vụ", true, DuLieuMau.dsNhomDv());
        if (!gia(cu, 1).isEmpty()) {
            cboNhom.setSelectedItem(gia(cu, 1));
        }
        JTextField txtTen = hopThoai.chu("Tên dịch vụ", gia(cu, 2), true);
        JTextField txtGia = hopThoai.chu("Giá", gia(cu, 3).isEmpty() ? "0" : gia(cu, 3), false);
        JComboBox<String> cboTrangThai = hopThoai.chon("Trạng thái", false, "Hoạt động", "Ngừng");
        if (!gia(cu, 6).isEmpty()) {
            cboTrangThai.setSelectedItem(gia(cu, 6));
        }
        hopThoai.hien(() -> luu.nhan(new Object[]{
            txtMa.getText().trim(),
            String.valueOf(cboNhom.getSelectedItem()),
            txtTen.getText().trim(),
            txtGia.getText().trim(),
            String.valueOf(cboTrangThai.getSelectedItem())
        }));
    }

    private static void nhanVien(Window cuaSo, Object[] cu, Luu luu) {
        HopThoai hopThoai = new HopThoai(cuaSo, sua(cu) ? "Cập nhật nhân viên" : "Thêm nhân viên",
                sua(cu) ? "Lưu" : "Thêm nhân viên");
        JTextField txtMa = hopThoai.chu("Mã nhân viên", gia(cu, 0), true);
        JTextField txtChucDanh = hopThoai.chuGoiY("Chức danh", "Bs, Ths, Ts ...", false);
        txtChucDanh.setText(gia(cu, 1));
        JTextField txtTen = hopThoai.chu("Họ và tên", gia(cu, 2), true);
        JTextField txtEmail = hopThoai.chu("Email", gia(cu, 3), false);
        JTextField txtSdt = hopThoai.chu("Số điện thoại", gia(cu, 4), false);
        JComboBox<String> cboVaiTro = hopThoai.chon("Vai trò", true, "Bác sĩ", "Phụ tá", "Lễ tân", "Tư vấn", "Sale");
        if (!gia(cu, 5).isEmpty()) {
            cboVaiTro.setSelectedItem(gia(cu, 5));
        }
        JComboBox<String> cboKichHoat = hopThoai.chon("Kích hoạt", true, "Có", "Không");
        if (!gia(cu, 6).isEmpty()) {
            cboKichHoat.setSelectedItem(gia(cu, 6));
        }
        JComboBox<String> cboChiNhanh = hopThoai.chon("Chi nhánh", true, "CN1");
        if (!gia(cu, 7).isEmpty()) {
            cboChiNhanh.setSelectedItem(gia(cu, 7));
        }
        hopThoai.hien(() -> luu.nhan(new Object[]{
            txtMa.getText().trim(),
            txtChucDanh.getText().trim(),
            txtTen.getText().trim(),
            txtEmail.getText().trim(),
            txtSdt.getText().trim(),
            String.valueOf(cboVaiTro.getSelectedItem()),
            String.valueOf(cboKichHoat.getSelectedItem()),
            String.valueOf(cboChiNhanh.getSelectedItem())
        }));
    }

    private static void loaiRang(Window cuaSo, Object[] cu, Luu luu) {
        HopThoai hopThoai = new HopThoai(cuaSo, sua(cu) ? "Cập nhật loại răng" : "Thêm loại răng",
                sua(cu) ? "Lưu" : "Thêm loại răng");
        JTextField txtMa = hopThoai.chu("Mã loại răng", gia(cu, 0), true);
        JTextField txtTen = hopThoai.chu("Tên loại răng", gia(cu, 1), true);
        hopThoai.hien(() -> luu.nhan(new Object[]{txtMa.getText().trim(), txtTen.getText().trim()}));
    }

    private static void thoiGian(Window cuaSo, Object[] cu, Luu luu) {
        HopThoai hopThoai = new HopThoai(cuaSo, sua(cu) ? "Cập nhật thời gian làm việc" : "Thêm thời gian làm việc",
                sua(cu) ? "Lưu" : "Thêm thời gian làm việc");
        JPanel panelNgay = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelNgay.setOpaque(false);
        String[] nhanNgay = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
        JCheckBox[] chkNgay = new JCheckBox[nhanNgay.length];
        for (int i = 0; i < nhanNgay.length; i++) {
            chkNgay[i] = new JCheckBox(nhanNgay[i]);
            chkNgay[i].setOpaque(false);
            if (gia(cu, 1).contains(nhanNgay[i])) {
                chkNgay[i].setSelected(true);
            }
            panelNgay.add(chkNgay[i]);
        }
        hopThoai.them("Ngày", panelNgay, null, true);
        JTextField txtMa = hopThoai.chu("Mã lịch", gia(cu, 0), true);
        JTextField txtBatDau = hopThoai.chu("Giờ bắt đầu", gia(cu, 2).isEmpty() ? "08:00" : gia(cu, 2), true);
        JTextField txtKetThuc = hopThoai.chu("Giờ kết thúc", gia(cu, 3).isEmpty() ? "17:00" : gia(cu, 3), true);
        JComboBox<String> cboBacSi = hopThoai.chonVoiNut("Bác sĩ", true, DuLieuMau.dsBacSi());
        if (!gia(cu, 4).isEmpty()) {
            cboBacSi.setSelectedItem(gia(cu, 4));
        }
        JComboBox<String> cboChiNhanh = hopThoai.chon("Chi nhánh", true, "CN1");
        if (!gia(cu, 5).isEmpty()) {
            cboChiNhanh.setSelectedItem(gia(cu, 5));
        }
        hopThoai.hien(() -> {
            StringBuilder ngayChon = new StringBuilder();
            for (int i = 0; i < chkNgay.length; i++) {
                if (chkNgay[i].isSelected()) {
                    if (ngayChon.length() > 0) {
                        ngayChon.append(", ");
                    }
                    ngayChon.append(nhanNgay[i]);
                }
            }
            if (ngayChon.length() == 0) {
                ngayChon.append("Mo, Tu, We, Th, Fr");
            }
            luu.nhan(new Object[]{txtMa.getText().trim(), ngayChon.toString(), txtBatDau.getText().trim(), txtKetThuc.getText().trim(),
                String.valueOf(cboBacSi.getSelectedItem()), String.valueOf(cboChiNhanh.getSelectedItem())});
        });
    }

    private static void kiemKho(Window cuaSo, Object[] cu, Luu luu) {
        HopThoai hopThoai = new HopThoai(cuaSo, sua(cu) ? "Cập nhật Kiểm kho" : "Thêm Kiểm kho",
                sua(cu) ? "Lưu" : "Thêm Kiểm kho");
        JTextField txtLyDo = new JTextField(gia(cu, 1));
        ViewTheme.vienNhap(txtLyDo);
        hopThoai.them("Lý do", txtLyDo, txtLyDo, true);
        JPanel panelHang = dongSanPham();
        JPanel panelDs = new JPanel();
        panelDs.setLayout(new BoxLayout(panelDs, BoxLayout.Y_AXIS));
        panelDs.setOpaque(false);
        panelDs.add(panelHang);
        JButton btnCong = new JButton("+");
        ViewTheme.btnNutChinh(btnCong);
        btnCong.addActionListener(e -> {
            panelDs.add(dongSanPham());
            panelDs.revalidate();
        });
        JPanel panelGiua = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelGiua.setOpaque(false);
        panelGiua.add(btnCong);
        hopThoai.themHang(kiemKho());
        hopThoai.themHang(panelDs);
        hopThoai.themHang(panelGiua);
        hopThoai.hien(() -> {
            String ma = sua(cu) ? gia(cu, 0) : String.format("%06d", demKiem++);
            luu.nhan(new Object[]{ma, txtLyDo.getText().trim(), "Đã duyệt", DuLieuMau.NGUOI,
                sua(cu) && !gia(cu, 4).isEmpty() ? gia(cu, 4) : ViewTheme.bayGio()});
        });
    }

    private static JPanel kiemKho() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 0, 0));
        panel.setOpaque(false);
        panel.add(ViewTheme.lblChuPhu("Sản phẩm"));
        panel.add(ViewTheme.lblChuPhu("Số lượng hệ thống"));
        panel.add(ViewTheme.lblChuPhu("Số lượng thực tế"));
        panel.add(ViewTheme.lblChuPhu("Chênh lệch"));
        panel.add(ViewTheme.lblChuPhu(""));
        return panel;
    }

    private static JPanel dongSanPham() {
        JPanel panel = new JPanel(new GridLayout(1, 5, 8, 0));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(4, 0, 4, 0));
        JComboBox<String> cboSanPham = new JComboBox<>(DuLieuMau.dsSanPham());
        ViewTheme.cmbVienHop(cboSanPham);
        JTextField txtSoLuong = new JTextField("0");
        JTextField txtChenhLech = new JTextField("0");
        JTextField txtGia = new JTextField("0");
        ViewTheme.vienNhap(txtSoLuong);
        ViewTheme.vienNhap(txtChenhLech);
        ViewTheme.vienNhap(txtGia);
        JButton btnXoa = new JButton("Xóa");
        ViewTheme.btnNutDo(btnXoa);
        btnXoa.addActionListener(e -> {
            Container cha = panel.getParent();
            if (cha != null && cha.getComponentCount() > 1) {
                cha.remove(panel);
                cha.revalidate();
                cha.repaint();
            }
        });
        panel.add(cboSanPham);
        panel.add(txtSoLuong);
        panel.add(txtChenhLech);
        panel.add(txtGia);
        panel.add(btnXoa);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        return panel;
    }

    private static void loaiSanPham(Window cuaSo, Object[] cu, Luu luu) {
        HopThoai hopThoai = new HopThoai(cuaSo, sua(cu) ? "Cập nhật loại sản phẩm" : "Thêm loại sản phẩm",
                sua(cu) ? "Lưu" : "Thêm loại sản phẩm");
        JTextField txtMa = hopThoai.chu("Mã loại", gia(cu, 0), true);
        JTextField txtTen = hopThoai.chu("Tên loại sản phẩm", gia(cu, 1), true);
        hopThoai.hien(() -> luu.nhan(new Object[]{txtMa.getText().trim(), txtTen.getText().trim()}));
    }

    private static void lichHen(Window cuaSo, Object[] cu, Luu luu) {
        HopThoai hopThoai = new HopThoai(cuaSo, sua(cu) ? "Cập nhật lịch hẹn" : "Thêm lịch hẹn",
                sua(cu) ? "Lưu" : "Thêm lịch hẹn");
        JTextField txtMa = hopThoai.chu("Mã lịch hẹn", gia(cu, 0), true);
        JTextField txtMaKH = hopThoai.chu("Mã KH", gia(cu, 1), true);
        JTextField txtBatDau = hopThoai.chu("Bắt đầu", gia(cu, 2).isEmpty() ? "2026-09-28 08:00" : gia(cu, 2), true);
        JComboBox<String> cboPhut = hopThoai.chon("Thời lượng (phút)", true, "15", "30", "45", "60", "90");
        if (!gia(cu, 3).isEmpty()) {
            cboPhut.setSelectedItem(gia(cu, 3));
        }
        JComboBox<String> cboBacSi = hopThoai.chon("Bác sĩ", false, "NV03", "NV02", "NV01");
        if (!gia(cu, 4).isEmpty()) {
            cboBacSi.setSelectedItem(gia(cu, 4));
        }
        JTextField txtPhuTa = hopThoai.chu("Phụ tá", gia(cu, 5), false);
        JTextField txtGhiChu = hopThoai.chu("Ghi chú", gia(cu, 6), false);
        JComboBox<String> cboTrangThai = hopThoai.chon("Trạng thái", false, "Mới", "Xác nhận", "Đang đợi", "Đang điều trị", "Hoàn tất", "Khách ra về", "Hủy bỏ");
        if (!gia(cu, 7).isEmpty()) {
            cboTrangThai.setSelectedItem(gia(cu, 7));
        }
        hopThoai.hien(() -> luu.nhan(new Object[]{
            txtMa.getText().trim(),
            txtMaKH.getText().trim(),
            txtBatDau.getText().trim(),
            String.valueOf(cboPhut.getSelectedItem()),
            String.valueOf(cboBacSi.getSelectedItem()),
            txtPhuTa.getText().trim(),
            txtGhiChu.getText().trim(),
            String.valueOf(cboTrangThai.getSelectedItem())
        }));
    }

}
