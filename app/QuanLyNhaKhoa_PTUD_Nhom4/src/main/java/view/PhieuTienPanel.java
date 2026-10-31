package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PhieuTienPanel extends JPanel {

    private final boolean laPhieuThu;
    private final DefaultTableModel model;
    private final JTable bang;
    private final JTextField tim = new ONhap("", 24);
    private int dem = 3;

    public PhieuTienPanel(boolean laPhieuThu) {
        this.laPhieuThu = laPhieuThu;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));
        String[] cot = laPhieuThu
                ? new String[]{"Mã", "Ngày thu", "Mã KH", "Khách hàng", "Lý do", "Số tiền", "Hình thức thanh toán", "Trạng thái", "Bác sĩ", "Phụ tá", "Người giới thiệu", "Ngày tạo", "Người tạo"}
                : new String[]{"Mã phiếu chi", "Nhà cung cấp", "Loại chi tiêu", "Lý do chi", "Số tiền", "Ngày chi", "Trạng thái", "Ngày tạo", "Người tạo"};
        model = ViewTheme.taoModel(cot, true, true);
        if (laPhieuThu) {
            model.addRow(ViewTheme.dongBang(new Object[]{
                "0001", "27/09/2026", "0001", "Lê Trần Quang Thanh", "bệnh", "72,000", "Tiền mặt", "Đã chi trả", "", "", "", "27 Sep 2026 10:22:19 PM", DuLieuMau.NGUOI
            }, true, true));
            model.addRow(ViewTheme.dongBang(new Object[]{
                "0002", "28/09/2026", "0001", "Lê Trần Quang Thanh", "3 x aptx 4869 (36000)", "36,000", "Tiền mặt", "Đã chi trả", "Lê Trần Quang Thanh", "", "", "28 Sep 2026 09:34:12 PM", DuLieuMau.NGUOI
            }, true, true));
        }
        bang = new JTable(model);
        ViewTheme.trangTriBang(bang);
        ViewTheme.ganThaoTac(bang, this::xuLy);

        JPanel the = new JPanel(new BorderLayout(0, 8));
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel tieu = ViewTheme.tieuDeTrang(laPhieuThu ? "Phiếu thu" : "Phiếu chi");
        JButton them = new JButton(laPhieuThu ? "Thêm phiếu thu" : "Thêm phiếu chi");
        ViewTheme.nutXanh(them);
        them.addActionListener(e -> moForm(-1));
        JPanel dau = new JPanel(new BorderLayout());
        dau.setOpaque(false);
        dau.add(tieu, BorderLayout.WEST);
        JPanel phai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        phai.setOpaque(false);
        phai.add(them);
        if (laPhieuThu) {
            JButton xuat = new JButton("Export Excel");
            ViewTheme.nutXam(xuat);
            xuat.addActionListener(e -> JOptionPane.showMessageDialog(this, "Đã chuẩn bị danh sách phiếu thu để xuất Excel."));
            phai.add(xuat);
        }
        dau.add(phai, BorderLayout.EAST);

        ((ONhap) tim).datGoiY(laPhieuThu ? "Tìm phiếu thu..." : "Tìm phiếu chi...");
        tim.setPreferredSize(new Dimension(360, 32));
        tim.addActionListener(e -> loc());
        JComboBox<String> ngay = new JComboBox<String>(new String[]{"Ngày", "28/09/2026", "27/09/2026", "Tháng 9/2026"});
        JComboBox<String> loai = new JComboBox<String>(laPhieuThu
                ? new String[]{"Loại", "Dịch vụ", "Thuốc", "Khác"}
                : new String[]{"Loại chi tiêu", "Vật tư", "Lương", "Labo"});
        JComboBox<String> hinhThuc = new JComboBox<String>(new String[]{"Hình thức thanh toán", "Tiền mặt", "Thẻ", "Chuyển khoản"});
        ViewTheme.vienHop(ngay);
        ViewTheme.vienHop(loai);
        ViewTheme.vienHop(hinhThuc);
        JPanel loc = new JPanel(new BorderLayout(0, 8));
        loc.setBackground(ViewTheme.NEN_TIM);
        loc.setBorder(new EmptyBorder(10, 12, 10, 12));
        loc.add(ViewTheme.chuPhu("Tìm kiếm"), BorderLayout.NORTH);
        loc.add(tim, BorderLayout.CENTER);
        JPanel dong = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        dong.setOpaque(false);
        dong.add(ngay);
        if (laPhieuThu) {
            JComboBox<String> khach = new JComboBox<String>();
            khach.addItem("Khách hàng");
            for (String k : DuLieuMau.KHACH) {
                khach.addItem(k);
            }
            ViewTheme.vienHop(khach);
            dong.add(khach);
        }
        dong.add(loai);
        dong.add(hinhThuc);
        loc.add(dong, BorderLayout.SOUTH);

        JScrollPane cuon = new JScrollPane(bang);
        cuon.setPreferredSize(new Dimension(400, laPhieuThu ? 180 : 140));
        cuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        cuon.getViewport().setBackground(ViewTheme.TRANG);
        JLabel dem = new JLabel(laPhieuThu
                ? "Hiển thị 1 tới 2 của 2 kết quả, kết quả trên 1 trang"
                : "Hiển thị 0 tới 0 của 0 kết quả, kết quả trên 1 trang");
        dem.setFont(ViewTheme.FONT_NHO);
        dem.setForeground(ViewTheme.CHU_NHAT);

        the.add(dau, BorderLayout.NORTH);
        JPanel giua = new JPanel(new BorderLayout(0, 8));
        giua.setOpaque(false);
        giua.add(loc, BorderLayout.NORTH);
        giua.add(cuon, BorderLayout.CENTER);
        giua.add(dem, BorderLayout.SOUTH);
        the.add(giua, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(the, BorderLayout.NORTH);
    }

    public void themPhieu(String ngayThu, String maKh, String tenKh, String lyDo, String soTien,
            String hinhThuc, String trangThai) {
        if (!laPhieuThu) {
            return;
        }
        Object[] dong = new Object[]{
            String.format("%04d", dem++),
            ngayThu, maKh, tenKh, lyDo, soTien, hinhThuc, trangThai,
            "", "", "", ViewTheme.bayGio(), DuLieuMau.NGUOI
        };
        model.addRow(ViewTheme.dongBang(dong, true, true));
    }

    private void loc() {
        String tu = tim.getText().trim();
        javax.swing.table.TableRowSorter<DefaultTableModel> sx = new javax.swing.table.TableRowSorter<DefaultTableModel>(model);
        bang.setRowSorter(sx);
        if (tu.isEmpty()) {
            sx.setRowFilter(null);
        } else {
            sx.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(tu)));
        }
    }

    private void xuLy(String lenh, int hang) {
        if ("Xóa".equals(lenh)) {
            if (JOptionPane.showConfirmDialog(this, "Xóa phiếu này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                model.removeRow(hang);
            }
            return;
        }
        if ("Xem".equals(lenh)) {
            StringBuilder sb = new StringBuilder();
            for (int c = 1; c < model.getColumnCount() - 1; c++) {
                sb.append(model.getColumnName(c)).append(": ").append(model.getValueAt(hang, c)).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Chi tiết phiếu", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        moForm(hang);
    }

    private void moForm(int hang) {
        boolean sua = hang >= 0;
        HopThoai h = new HopThoai(SwingUtilities.getWindowAncestor(this),
                sua ? "Cập nhật phiếu" : (laPhieuThu ? "Thêm phiếu thu" : "Thêm phiếu chi"),
                sua ? "Lưu" : "Thêm");
        if (laPhieuThu) {
            JComboBox<String> khach = h.chon("Khách hàng", true, DuLieuMau.KHACH);
            JTextField lyDo = h.chu("Lý do", sua ? String.valueOf(model.getValueAt(hang, 5)) : "", false);
            JTextField tien = h.chu("Số tiền", sua ? String.valueOf(model.getValueAt(hang, 6)) : "", true);
            JComboBox<String> ht = h.chon("Hình thức thanh toán", true, "Tiền mặt", "Thẻ", "Chuyển khoản");
            JComboBox<String> tt = h.chon("Trạng thái", false, "Đã chi trả", "Chưa chi trả");
            JComboBox<String> bs = h.chon("Bác sĩ", false, DuLieuMau.BAC_SI);
            h.hien(() -> {
                String maKh = "0001";
                String tenKh = String.valueOf(khach.getSelectedItem());
                if (tenKh.length() >= 4) {
                    maKh = tenKh.substring(0, 4);
                }
                Object[] dong = new Object[]{
                    sua ? model.getValueAt(hang, 1) : String.format("%04d", dem++),
                    "12/10/2026", maKh, tenKh, lyDo.getText().trim(), tien.getText().trim(),
                    ht.getSelectedItem(), tt.getSelectedItem(), bs.getSelectedItem(), "", "", ViewTheme.bayGio(), DuLieuMau.NGUOI
                };
                gan(hang, dong);
            });
        } else {
            JComboBox<String> ncc = h.chon("Nhà cung cấp", true, "STRAUMANN", "Nhà thuốc Trung tâm");
            JComboBox<String> loai = h.chon("Loại chi tiêu", true, "Vật tư", "Lương", "Labo", "Mặt bằng");
            JTextField lyDo = h.chu("Lý do chi", sua ? String.valueOf(model.getValueAt(hang, 4)) : "", true);
            JTextField tien = h.chu("Số tiền", sua ? String.valueOf(model.getValueAt(hang, 5)) : "", true);
            JComboBox<String> ht = h.chon("Phương thức thanh toán", true, "Tiền mặt", "Thẻ", "Chuyển khoản");
            h.hien(() -> {
                Object[] dong = new Object[]{
                    sua ? model.getValueAt(hang, 1) : String.format("%04d", dem++),
                    ncc.getSelectedItem(), loai.getSelectedItem(), lyDo.getText().trim(), tien.getText().trim(),
                    "12/10/2026", "Đã chi", ViewTheme.bayGio(), DuLieuMau.NGUOI
                };
                gan(hang, dong);
            });
            ht.setSelectedIndex(0);
        }
    }

    private void gan(int hang, Object[] dong) {
        if (hang < 0) {
            model.addRow(ViewTheme.dongBang(dong, true, true));
        } else {
            for (int i = 0; i < dong.length; i++) {
                model.setValueAt(dong[i], hang, i + 1);
            }
        }
    }
}
