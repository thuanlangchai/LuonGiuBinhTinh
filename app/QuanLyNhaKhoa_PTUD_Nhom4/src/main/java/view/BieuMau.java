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
        } else if ("sanpham".equals(cauHinh.ma)) {
            sanPham(cuaSo, cu, luu);
        } else if ("rang".equals(cauHinh.ma)) {
            rang(cuaSo, cu, luu);
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

    private static void sanPham(Window cuaSo, Object[] cu, Luu luu) {
        HopThoai hopThoai = new HopThoai(cuaSo, sua(cu) ? "Cập nhật sản phẩm" : "Thêm sản phẩm",
                sua(cu) ? "Lưu" : "Thêm sản phẩm");
        JTextField txtMa = hopThoai.chu("Mã SP", gia(cu, 0), true);
        JTextField txtTen = hopThoai.chu("Tên sản phẩm", gia(cu, 1), true);
        JComboBox<String> cmbLoai = hopThoai.chon("Loại", true, DuLieuMau.dsLoaiSanPham());
        chonCombo(cmbLoai, gia(cu, 2));
        JTextField txtSoLuong = hopThoai.chu("Số lượng", gia(cu, 3).isEmpty() ? "0" : gia(cu, 3), false);
        JTextField txtDonGia = hopThoai.chu("Đơn giá", gia(cu, 4).isEmpty() ? "0" : gia(cu, 4), false);
        hopThoai.hien(() -> luu.nhan(new Object[]{
            txtMa.getText().trim(),
            txtTen.getText().trim(),
            maTuCombo(cmbLoai),
            txtSoLuong.getText().trim(),
            txtDonGia.getText().trim()
        }));
    }

    private static void rang(Window cuaSo, Object[] cu, Luu luu) {
        HopThoai hopThoai = new HopThoai(cuaSo, sua(cu) ? "Cập nhật răng" : "Thêm răng",
                sua(cu) ? "Lưu" : "Thêm răng");
        JTextField txtMa = hopThoai.chu("Mã răng", gia(cu, 0), true);
        JTextField txtTen = hopThoai.chu("Tên răng", gia(cu, 1), false);
        JComboBox<String> cmbLoai = hopThoai.chon("Loại răng", true, DuLieuMau.dsLoaiRang());
        chonCombo(cmbLoai, gia(cu, 2));
        hopThoai.hien(() -> luu.nhan(new Object[]{
            txtMa.getText().trim(),
            txtTen.getText().trim(),
            maTuCombo(cmbLoai)
        }));
    }

    private static void chonCombo(JComboBox<String> cmb, String giaTri) {
        if (giaTri == null || giaTri.isEmpty()) {
            return;
        }
        for (int i = 0; i < cmb.getItemCount(); i++) {
            String item = cmb.getItemAt(i);
            if (item == null) {
                continue;
            }
            if (item.equals(giaTri) || item.startsWith(giaTri + " - ") || item.endsWith(" - " + giaTri)) {
                cmb.setSelectedIndex(i);
                return;
            }
        }
    }

    private static String maTuCombo(JComboBox<String> cmb) {
        Object chon = cmb.getSelectedItem();
        if (chon == null) {
            return "";
        }
        String giaTri = String.valueOf(chon).trim();
        int viTri = giaTri.indexOf(" - ");
        return viTri > 0 ? giaTri.substring(0, viTri).trim() : giaTri;
    }

    private static void dichVu(Window cuaSo, Object[] cu, Luu luu) {
        HopThoai hopThoai = new HopThoai(cuaSo, sua(cu) ? "Cập nhật dịch vụ" : "Thêm dịch vụ",
                sua(cu) ? "Lưu" : "Thêm dịch vụ");
        JTextField txtMa = hopThoai.chu("Mã dịch vụ", gia(cu, 0), true);
        JComboBox<String> cmbNhom = hopThoai.chonVoiNut("Nhóm dịch vụ", true, DuLieuMau.dsNhomDv());
        if (!gia(cu, 1).isEmpty()) {
            cmbNhom.setSelectedItem(gia(cu, 1));
        }
        JTextField txtTen = hopThoai.chu("Tên dịch vụ", gia(cu, 2), true);
        JTextField txtGia = hopThoai.chu("Giá", gia(cu, 3).isEmpty() ? "0" : gia(cu, 3), false);
        JComboBox<String> cmbTrangThai = hopThoai.chon("Trạng thái", false, "Hoạt động", "Ngừng");
        if (!gia(cu, 6).isEmpty()) {
            cmbTrangThai.setSelectedItem(gia(cu, 6));
        }
        hopThoai.hien(() -> luu.nhan(new Object[]{
            txtMa.getText().trim(),
            String.valueOf(cmbNhom.getSelectedItem()),
            txtTen.getText().trim(),
            txtGia.getText().trim(),
            String.valueOf(cmbTrangThai.getSelectedItem())
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
        JComboBox<String> cmbVaiTro = hopThoai.chon("Vai trò", true, "Bác sĩ", "Phụ tá", "Lễ tân", "Tư vấn", "Sale");
        if (!gia(cu, 5).isEmpty()) {
            cmbVaiTro.setSelectedItem(gia(cu, 5));
        }
        JComboBox<String> cmbKichHoat = hopThoai.chon("Kích hoạt", true, "Có", "Không");
        if (!gia(cu, 6).isEmpty()) {
            cmbKichHoat.setSelectedItem(gia(cu, 6));
        }
        JComboBox<String> cmbChiNhanh = hopThoai.chon("Chi nhánh", true, "CN1");
        if (!gia(cu, 7).isEmpty()) {
            cmbChiNhanh.setSelectedItem(gia(cu, 7));
        }
        hopThoai.hien(() -> luu.nhan(new Object[]{
            txtMa.getText().trim(),
            txtChucDanh.getText().trim(),
            txtTen.getText().trim(),
            txtEmail.getText().trim(),
            txtSdt.getText().trim(),
            String.valueOf(cmbVaiTro.getSelectedItem()),
            String.valueOf(cmbKichHoat.getSelectedItem()),
            String.valueOf(cmbChiNhanh.getSelectedItem())
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
        JPanel pnlNgay = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pnlNgay.setOpaque(false);
        String[] nhanNgay = {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"};
        JCheckBox[] chkNgay = new JCheckBox[nhanNgay.length];
        for (int i = 0; i < nhanNgay.length; i++) {
            chkNgay[i] = new JCheckBox(nhanNgay[i]);
            chkNgay[i].setOpaque(false);
            if (gia(cu, 1).contains(nhanNgay[i])) {
                chkNgay[i].setSelected(true);
            }
            pnlNgay.add(chkNgay[i]);
        }
        hopThoai.them("Ngày", pnlNgay, null, true);
        JTextField txtMa = hopThoai.chu("Mã lịch", gia(cu, 0), true);
        JTextField txtBatDau = hopThoai.chu("Giờ bắt đầu", gia(cu, 2).isEmpty() ? "08:00" : gia(cu, 2), true);
        JTextField txtKetThuc = hopThoai.chu("Giờ kết thúc", gia(cu, 3).isEmpty() ? "17:00" : gia(cu, 3), true);
        JComboBox<String> cmbBacSi = hopThoai.chonVoiNut("Bác sĩ", true, DuLieuMau.dsBacSi());
        if (!gia(cu, 4).isEmpty()) {
            cmbBacSi.setSelectedItem(gia(cu, 4));
        }
        JComboBox<String> cmbChiNhanh = hopThoai.chon("Chi nhánh", true, "CN1");
        if (!gia(cu, 5).isEmpty()) {
            cmbChiNhanh.setSelectedItem(gia(cu, 5));
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
                String.valueOf(cmbBacSi.getSelectedItem()), String.valueOf(cmbChiNhanh.getSelectedItem())});
        });
    }

    private static void kiemKho(Window cuaSo, Object[] cu, Luu luu) {
        HopThoai hopThoai = new HopThoai(cuaSo, sua(cu) ? "Cập nhật Kiểm kho" : "Thêm Kiểm kho",
                sua(cu) ? "Lưu" : "Thêm Kiểm kho");
        JTextField txtLyDo = new JTextField(gia(cu, 1));
        ViewTheme.vienNhap(txtLyDo);
        hopThoai.them("Lý do", txtLyDo, txtLyDo, true);
        JPanel pnlHang = dongSanPham();
        JPanel pnlDs = new JPanel();
        pnlDs.setLayout(new BoxLayout(pnlDs, BoxLayout.Y_AXIS));
        pnlDs.setOpaque(false);
        pnlDs.add(pnlHang);
        JButton btnCong = new JButton("+");
        ViewTheme.nutChinh(btnCong);
        btnCong.addActionListener(e -> {
            pnlDs.add(dongSanPham());
            pnlDs.revalidate();
        });
        JPanel pnlGiua = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlGiua.setOpaque(false);
        pnlGiua.add(btnCong);
        hopThoai.themHang(kiemKho());
        hopThoai.themHang(pnlDs);
        hopThoai.themHang(pnlGiua);
        hopThoai.hien(() -> {
            String ma = sua(cu) ? gia(cu, 0) : String.format("%06d", demKiem++);
            luu.nhan(new Object[]{ma, txtLyDo.getText().trim(), "Đã duyệt", DuLieuMau.NGUOI,
                sua(cu) && !gia(cu, 4).isEmpty() ? gia(cu, 4) : ViewTheme.bayGio()});
        });
    }

    private static JPanel kiemKho() {
        JPanel pnl = new JPanel(new GridLayout(1, 4, 0, 0));
        pnl.setOpaque(false);
        pnl.add(ViewTheme.chuPhu("Sản phẩm"));
        pnl.add(ViewTheme.chuPhu("Số lượng hệ thống"));
        pnl.add(ViewTheme.chuPhu("Số lượng thực tế"));
        pnl.add(ViewTheme.chuPhu("Chênh lệch"));
        pnl.add(ViewTheme.chuPhu(""));
        return pnl;
    }

    private static JPanel dongSanPham() {
        JPanel pnl = new JPanel(new GridLayout(1, 5, 8, 0));
        pnl.setOpaque(false);
        pnl.setBorder(new EmptyBorder(4, 0, 4, 0));
        JComboBox<String> cmbSanPham = new JComboBox<>(DuLieuMau.dsSanPham());
        ViewTheme.vienHop(cmbSanPham);
        JTextField txtSoLuong = new JTextField("0");
        JTextField txtChenhLech = new JTextField("0");
        JTextField txtGia = new JTextField("0");
        ViewTheme.vienNhap(txtSoLuong);
        ViewTheme.vienNhap(txtChenhLech);
        ViewTheme.vienNhap(txtGia);
        JButton btnXoa = new JButton("Xóa");
        ViewTheme.nutDo(btnXoa);
        btnXoa.addActionListener(e -> {
            Container cha = pnl.getParent();
            if (cha != null && cha.getComponentCount() > 1) {
                cha.remove(pnl);
                cha.revalidate();
                cha.repaint();
            }
        });
        pnl.add(cmbSanPham);
        pnl.add(txtSoLuong);
        pnl.add(txtChenhLech);
        pnl.add(txtGia);
        pnl.add(btnXoa);
        pnl.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        return pnl;
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
        JComboBox<String> cmbPhut = hopThoai.chon("Thời lượng (phút)", true, "15", "30", "45", "60", "90");
        if (!gia(cu, 3).isEmpty()) {
            cmbPhut.setSelectedItem(gia(cu, 3));
        }
        JComboBox<String> cmbBacSi = hopThoai.chon("Bác sĩ", false, "NV03", "NV02", "NV01");
        if (!gia(cu, 4).isEmpty()) {
            cmbBacSi.setSelectedItem(gia(cu, 4));
        }
        JTextField txtPhuTa = hopThoai.chu("Phụ tá", gia(cu, 5), false);
        JTextField txtGhiChu = hopThoai.chu("Ghi chú", gia(cu, 6), false);
        JComboBox<String> cmbTrangThai = hopThoai.chon("Trạng thái", false, "Mới", "Xác nhận", "Đang đợi", "Đang điều trị", "Hoàn tất", "Khách ra về", "Hủy bỏ");
        if (!gia(cu, 7).isEmpty()) {
            cmbTrangThai.setSelectedItem(gia(cu, 7));
        }
        hopThoai.hien(() -> luu.nhan(new Object[]{
            txtMa.getText().trim(),
            txtMaKH.getText().trim(),
            txtBatDau.getText().trim(),
            String.valueOf(cmbPhut.getSelectedItem()),
            String.valueOf(cmbBacSi.getSelectedItem()),
            txtPhuTa.getText().trim(),
            txtGhiChu.getText().trim(),
            String.valueOf(cmbTrangThai.getSelectedItem())
        }));
    }

}
