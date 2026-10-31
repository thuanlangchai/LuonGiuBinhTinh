package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class KhachHangPanel extends JPanel {

    private static final String[] COT = {
        "Mã KH", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Địa chỉ", "CCCD",
        "Ghi chú", "Nhóm KH", "Chi nhánh", "Lý do khám", "Tiền sử bệnh", "Dị ứng",
        "Bác sĩ", "Tư vấn", "Số lần khám", "Lần khám gần nhất", "Ngày tạo"
    };

    private final KeHoachDieuTriPanel keHoach;
    private final Runnable moKeHoach;
    private final DefaultTableModel model;
    private final JTable bang;
    private final JTextField tim = new JTextField();
    private final JPanel locThem = new JPanel(new GridLayout(2, 6, 8, 8));
    private int demMa = 3;

    public KhachHangPanel(KeHoachDieuTriPanel keHoach, Runnable moKeHoach) {
        this.keHoach = keHoach;
        this.moKeHoach = moKeHoach;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));
        JPanel the = new JPanel(new BorderLayout(0, 8));
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel tieu = ViewTheme.tieuDeTrang("Khách hàng");
        JButton xuat = new JButton("Xuất Excel");
        JButton them = new JButton("Thêm khách hàng");
        JComboBox<String> hienThi = new JComboBox<String>(new String[]{"Hiển thị", "Tất cả cột", "Cột chính"});
        JComboBox<String> dang = new JComboBox<String>(new String[]{"Dạng", "Bảng", "Thẻ"});
        ViewTheme.nutXam(xuat);
        ViewTheme.nutCam(them);
        ViewTheme.vienHop(hienThi);
        ViewTheme.vienHop(dang);
        them.addActionListener(e -> moForm(-1));
        xuat.addActionListener(e -> JOptionPane.showMessageDialog(this, "Đã chuẩn bị dữ liệu khách hàng để xuất Excel."));

        JPanel dau = new JPanel(new BorderLayout());
        dau.setOpaque(false);
        dau.add(tieu, BorderLayout.WEST);
        JPanel phai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        phai.setOpaque(false);
        phai.add(xuat);
        phai.add(them);
        phai.add(hienThi);
        phai.add(dang);
        dau.add(phai, BorderLayout.EAST);

        ViewTheme.vienNhap(tim);
        tim.setPreferredSize(new Dimension(220, 32));
        JButton nutTim = new JButton("Tìm");
        ViewTheme.nutChinh(nutTim);
        nutTim.addActionListener(e -> loc());
        tim.addActionListener(e -> loc());

        JComboBox<String> phanLoai = hop("Phân loại", "Tất cả", "Mới", "Cũ");
        JComboBox<String> bacSi = hop("Bác sĩ", "Tất cả", "Lê Trần Quang Thanh", "Hồ Văn Khánh");
        JComboBox<String> nhan = hop("Nhãn", "Tất cả", "Nhổ răng khôn", "Chỉnh nha");
        JComboBox<String> trangThai = hop("Trạng thái điều trị", "Tất cả", "Đang điều trị", "Hoàn tất");
        JComboBox<String> dichVu = hop("Dịch vụ", "Tất cả", "Thăm khám", "Cạo vôi");
        JButton more = new JButton("More");
        ViewTheme.nutChinh(more);

        locThem.setOpaque(false);
        locThem.setVisible(false);
        locThem.add(hop("Giới tính", "Tất cả", "Nam", "Nữ"));
        locThem.add(hop("Nguồn", "Tất cả", "Cư dân", "Facebook"));
        locThem.add(o("Ngày điều trị"));
        locThem.add(o("Ngày hẹn"));
        locThem.add(o("Ngày tạo"));
        locThem.add(hop("Nhóm khách hàng", "Tất cả", "PTUD", "VIP"));
        locThem.add(o("Chọn độ tuổi hoặc tháng sinh"));
        locThem.add(o("Số tiền đã thanh toán"));
        locThem.add(o("Số tiền dịch vụ"));
        locThem.add(o("Số tiền nợ còn lại"));
        JButton less = new JButton("Less");
        ViewTheme.nutChinh(less);
        locThem.add(less);
        more.addActionListener(e -> locThem.setVisible(true));
        less.addActionListener(e -> locThem.setVisible(false));

        JPanel dong1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        dong1.setOpaque(false);
        dong1.add(tim);
        dong1.add(nutTim);
        dong1.add(phanLoai);
        dong1.add(bacSi);
        dong1.add(nhan);
        dong1.add(trangThai);
        dong1.add(dichVu);
        dong1.add(more);

        JPanel vungLoc = new JPanel(new BorderLayout());
        vungLoc.setOpaque(false);
        vungLoc.add(dong1, BorderLayout.NORTH);
        vungLoc.add(locThem, BorderLayout.CENTER);

        model = ViewTheme.taoModel(COT, true, true);
        model.addRow(ViewTheme.dongBang(new Object[]{
            "0001", "Lê Trần Quang Thanh", "Nam", "27/08/2006", "03xxxx2808", "thanhqt@gmail.com", "28 Lê Lai", "",
            "", "NK01", "CN1", "Đau răng", "Béo phì", "", "NV03", "", "1", "27/09/2026", "27/09/2026"
        }, true, true));
        model.addRow(ViewTheme.dongBang(new Object[]{
            "0002", "Nguyễn Minh Lý", "Nam", "", "09xxxx8352", "minh@maysoft.vn", "29 Nội Khu Mỹ Toàn - Q7 - TP.HCM", "",
            "", "NK01", "CN1", "", "", "", "", "", "0", "", "27/09/2026"
        }, true, true));
        bang = new JTable(model);
        ViewTheme.trangTriBang(bang);
        ViewTheme.ganThaoTac(bang, (lenh, hang) -> xuLy(lenh, hang));
        bang.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && bang.rowAtPoint(e.getPoint()) >= 0
                        && bang.columnAtPoint(e.getPoint()) != bang.getColumnCount() - 1) {
                    moHoSo(bang.convertRowIndexToModel(bang.rowAtPoint(e.getPoint())));
                }
            }
        });

        JScrollPane cuon = new JScrollPane(bang);
        cuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        cuon.setPreferredSize(new Dimension(400, 220));
        cuon.getViewport().setBackground(ViewTheme.TRANG);

        JLabel dem = new JLabel("Hiển thị 1 tới 2 của 2 kết quả");
        dem.setFont(ViewTheme.FONT_NHO);
        dem.setForeground(ViewTheme.CHU_NHAT);
        JPanel chan = new JPanel(new BorderLayout());
        chan.setOpaque(false);
        JPanel trai = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        trai.setOpaque(false);
        trai.add(new JLabel("Display"));
        JComboBox<String> so = new JComboBox<String>(new String[]{"15", "10", "25"});
        ViewTheme.vienHop(so);
        trai.add(so);
        trai.add(new JLabel("records"));
        trai.add(dem);
        chan.add(trai, BorderLayout.WEST);

        the.add(dau, BorderLayout.NORTH);
        JPanel giua = new JPanel(new BorderLayout(0, 8));
        giua.setOpaque(false);
        giua.add(vungLoc, BorderLayout.NORTH);
        giua.add(cuon, BorderLayout.CENTER);
        the.add(giua, BorderLayout.CENTER);
        the.add(chan, BorderLayout.SOUTH);
        setLayout(new BorderLayout());
        add(the, BorderLayout.NORTH);
    }

    public void tim(String tuKhoa) {
        tim.setText(tuKhoa == null ? "" : tuKhoa);
        loc();
    }

    private void loc() {
        String tu = tim.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sx = new TableRowSorter<>(model);
        bang.setRowSorter(sx);
        if (tu.isEmpty()) {
            sx.setRowFilter(null);
        } else {
            sx.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(tu)));
        }
    }

    private void xuLy(String lenh, int hang) {
        if ("Xóa".equals(lenh)) {
            if (JOptionPane.showConfirmDialog(this, "Xóa khách hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                model.removeRow(hang);
            }
            return;
        }
        if ("Xem".equals(lenh)) {
            moHoSo(hang);
            return;
        }
        moForm(hang);
    }

    private void moHoSo(int hang) {
        keHoach.nap(
                String.valueOf(model.getValueAt(hang, 1)),
                String.valueOf(model.getValueAt(hang, 2)),
                String.valueOf(model.getValueAt(hang, 5)),
                String.valueOf(model.getValueAt(hang, 6)),
                String.valueOf(model.getValueAt(hang, 7)),
                String.valueOf(model.getValueAt(hang, 12)),
                "");
        moKeHoach.run();
    }

    private void moForm(int hang) {
        boolean sua = hang >= 0;
        Window w = SwingUtilities.getWindowAncestor(this);
        javax.swing.JDialog d = new javax.swing.JDialog(w, sua ? "Cập nhật khách hàng" : "Thêm khách hàng", java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        d.setLayout(new BorderLayout());
        JPanel noi = new JPanel(new GridLayout(1, 2, 18, 0));
        noi.setBackground(ViewTheme.TRANG);
        noi.setBorder(new EmptyBorder(12, 16, 12, 16));

        JTextField ma = oForm(sua ? String.valueOf(model.getValueAt(hang, 1)) : String.format("%04d", demMa));
        ma.setEditable(false);
        JTextField ten = oForm(sua ? String.valueOf(model.getValueAt(hang, 2)) : "");
        JTextField sdt = oForm(sua ? String.valueOf(model.getValueAt(hang, 5)) : "");
        JTextField email = oForm(sua ? String.valueOf(model.getValueAt(hang, 6)) : "");
        JTextField cccd = oForm(sua ? String.valueOf(model.getValueAt(hang, 8)) : "");
        JComboBox<String> gioi = new JComboBox<String>(new String[]{"Nam", "Nữ", "Khác"});
        ViewTheme.vienHop(gioi);
        if (sua) {
            gioi.setSelectedItem(model.getValueAt(hang, 3));
        }
        JTextField ngaySinh = oForm(sua ? String.valueOf(model.getValueAt(hang, 4)) : "");
        JTextField diaChi = oForm(sua ? String.valueOf(model.getValueAt(hang, 7)) : "");
        JTextField ghi = oForm(sua ? String.valueOf(model.getValueAt(hang, 9)) : "");
        JComboBox<String> chiNhanh = new JComboBox<String>(new String[]{"CN1", "Quang Thanh"});
        ViewTheme.vienHop(chiNhanh);

        JPanel trai = cotForm("Thông tin khách hàng",
                new String[]{"Mã KH", "Họ và tên", "Số điện thoại", "Địa chỉ email", "CCCD", "Giới tính", "Ngày sinh", "Địa chỉ", "Ghi chú", "Chi nhánh"},
                new JComponent[]{ma, ten, sdt, email, cccd, gioi, ngaySinh, diaChi, ghi, chiNhanh},
                new boolean[]{false, true, false, false, false, false, false, false, false, false});

        JTextField lyDo = oForm(sua ? String.valueOf(model.getValueAt(hang, 12)) : "");
        JTextField tienSu = oForm(sua ? String.valueOf(model.getValueAt(hang, 13)) : "");
        JTextField diUng = oForm(sua ? String.valueOf(model.getValueAt(hang, 14)) : "");
        JComboBox<String> nhom = new JComboBox<>(new String[]{"NK01", "NK02", "NK03"});                
        JComboBox<String> bacSi = new JComboBox<String>(DuLieuMau.BAC_SI);
        JComboBox<String> tuVan = new JComboBox<String>(DuLieuMau.BAC_SI);
        ViewTheme.vienHop(bacSi);
        ViewTheme.vienHop(tuVan);

        JPanel phai = new JPanel(new BorderLayout(0, 12));
        phai.setOpaque(false);
        phai.add(cotForm("Bệnh lý",
                new String[]{"Lý do đến khám", "Tiền sử bệnh", "Dị ứng", "Nhóm khách hàng"},
                new javax.swing.JComponent[]{lyDo, tienSu, diUng, nhom},
                new boolean[]{false, false, false, false, false}), BorderLayout.NORTH);
        phai.add(cotForm("Người phụ trách",
                new String[]{"Bác sĩ", "Tư vấn"},
                new javax.swing.JComponent[]{bacSi, tuVan},
                new boolean[]{false, false}), BorderLayout.CENTER);

        noi.add(trai);
        noi.add(phai);
        d.add(new JScrollPane(noi), BorderLayout.CENTER);

        JButton boQua = new JButton("Bỏ qua");
        JButton luu = new JButton(sua ? "Lưu" : "Thêm");
        ViewTheme.nutXam(boQua);
        ViewTheme.nutChinh(luu);
        boQua.addActionListener(e -> d.dispose());
        luu.addActionListener(e -> {
            if (ten.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(d, "Vui lòng nhập họ và tên.");
                return;
            }
            Object[] dong = new Object[]{
                sua ? model.getValueAt(hang, 1) : String.format("%04d", demMa++),
                ten.getText().trim(),
                String.valueOf(gioi.getSelectedItem()),
                ngaySinh.getText().trim(),
                sdt.getText().trim(),
                email.getText().trim(),
                diaChi.getText().trim(),
                cccd.getText().trim(),
                ghi.getText().trim(),
                String.valueOf(nhom.getSelectedItem()),
                String.valueOf(chiNhanh.getSelectedItem()),
                lyDo.getText().trim(),
                tienSu.getText().trim(),
                diUng.getText().trim(),
                String.valueOf(bacSi.getSelectedItem()),
                String.valueOf(tuVan.getSelectedItem()),
                sua ? model.getValueAt(hang, 17) : "0",
                sua ? model.getValueAt(hang, 18) : "",
                sua ? model.getValueAt(hang, 19) : "12/10/2026"
            };
            if (sua) {
                for (int i = 0; i < dong.length; i++) {
                    model.setValueAt(dong[i], hang, i + 1);
                }
            } else {
                model.addRow(ViewTheme.dongBang(dong, true, true));
            }
            d.dispose();
        });
        JPanel chan = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        chan.setBackground(ViewTheme.TRANG);
        chan.add(boQua);
        chan.add(luu);
        d.add(chan, BorderLayout.SOUTH);
        d.setSize(980, 640);
        d.setLocationRelativeTo(w);
        d.setVisible(true);
    }

    private JPanel cotForm(String tieu, String[] nhan, javax.swing.JComponent[] o, boolean[] batBuoc) {
        JPanel p = new JPanel(new GridLayout(0, 2, 8, 8));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createTitledBorder(tieu));
        for (int i = 0; i < nhan.length; i++) {
            JLabel lb = new JLabel(batBuoc[i] ? "<html>" + nhan[i] + " <font color='#e74c3c'>*</font></html>" : nhan[i]);
            lb.setFont(ViewTheme.FONT);
            p.add(lb);
            p.add(o[i]);
        }
        return p;
    }

    private JTextField oForm(String giaTri) {
        JTextField t = new JTextField(giaTri);
        ViewTheme.vienNhap(t);
        return t;
    }

    private JComboBox<String> hop(String dau, String... muc) {
        JComboBox<String> c = new JComboBox<String>();
        c.addItem(dau);
        for (String m : muc) {
            if (!m.equals(dau)) {
                c.addItem(m);
            }
        }
        ViewTheme.vienHop(c);
        return c;
    }

    private JTextField o(String goi) {
        JTextField t = new ONhap(goi, 12);
        t.setPreferredSize(new Dimension(160, 32));
        return t;
    }
}
