package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import view.model.ChiTietToaThuocView;
import view.model.ToaThuocView;

public class ToaThuocPanel extends JPanel {

    private final DefaultTableModel model = ViewTheme.taoModel(
            new String[]{"#", "Thuốc", "Số ngày", "Cách sử dụng", "Tổng số", "Đơn giá", "Thành tiền"}, false, true);
    private final JLabel thanhTien = new JLabel("0");
    private final JPanel dsThuoc = new JPanel();
    private final JTextField tim = new ONhap("Tìm thuốc ...", 16);
    private final JComboBox<String> khach = new JComboBox<String>(DuLieuMau.KHACH);
    private final JTextField chanDoan = new JTextField();
    private final JTextField ghi = new JTextField();
    private final JComboBox<String> hinhThuc = new JComboBox<String>(new String[]{"Tiền mặt", "Thẻ", "Chuyển khoản"});
    private final JComboBox<String> bacSi = new JComboBox<String>(DuLieuMau.BAC_SI);
    private final JCheckBox nhanPK = new JCheckBox("KH nhận thuốc tại PK", true);
    private final Runnable sauLuu;

    public ToaThuocPanel() {
        this(null);
    }

    public ToaThuocPanel(Runnable sauLuu) {
        this.sauLuu = sauLuu;
        setLayout(new BorderLayout(10, 0));
        setBackground(ViewTheme.TRANG);
        setBorder(new EmptyBorder(12, 12, 12, 12));
        setPreferredSize(new Dimension(1180, 680));

        JPanel trai = new JPanel(new BorderLayout(0, 8));
        trai.setBackground(ViewTheme.TRANG);
        trai.setPreferredSize(new Dimension(250, 400));
        trai.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        tim.setPreferredSize(new Dimension(200, 32));
        JButton all = new JButton("ALL");
        ViewTheme.nutChinh(all);
        all.setPreferredSize(new Dimension(220, 32));
        all.addActionListener(e -> {
            tim.setText("");
            locThe();
        });
        JPanel dauTrai = new JPanel(new BorderLayout(0, 6));
        dauTrai.setOpaque(false);
        dauTrai.setBorder(new EmptyBorder(8, 8, 8, 8));
        dauTrai.add(tim, BorderLayout.NORTH);
        dauTrai.add(all, BorderLayout.SOUTH);
        dsThuoc.setLayout(new javax.swing.BoxLayout(dsThuoc, javax.swing.BoxLayout.Y_AXIS));
        dsThuoc.setBackground(ViewTheme.TRANG);
        themTheThuoc("0001 - aptx 4869", "36,000", "36000");
        themTheThuoc("0002 - Paracetamol 500mg", "2,000", "2000");
        themTheThuoc("0003 - Amoxicillin 500mg", "5,000", "5000");
        tim.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                locThe();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                locThe();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                locThe();
            }
        });
        trai.add(dauTrai, BorderLayout.NORTH);
        trai.add(new JScrollPane(dsThuoc), BorderLayout.CENTER);

        ViewTheme.vienNhap(chanDoan);
        ViewTheme.vienHop(khach);
        JLabel thongTin = new JLabel("Giới tính: Nam     Ngày: 28/09/2026");
        thongTin.setFont(ViewTheme.FONT);

        JTable bang = new JTable(model);
        ViewTheme.trangTriBang(bang);
        ViewTheme.ganThaoTac(bang, (lenh, hang) -> {
            if ("Xóa".equals(lenh) || "Sửa".equals(lenh)) {
                model.removeRow(hang);
                danhLai();
                tinhTien();
            }
        });
        JScrollPane cuon = new JScrollPane(bang);
        cuon.setPreferredSize(new Dimension(400, 180));
        cuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));

        ViewTheme.vienNhap(ghi);
        ViewTheme.vienHop(hinhThuc);
        ViewTheme.vienHop(bacSi);
        nhanPK.setOpaque(false);
        nhanPK.setFont(ViewTheme.FONT);
        JButton luu = new JButton("Lưu toa thuốc");
        ViewTheme.nutChinh(luu);
        luu.addActionListener(e -> luuToa());

        JPanel chan = new JPanel(new GridLayout(2, 1, 0, 8));
        chan.setOpaque(false);
        JPanel dong1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        dong1.setOpaque(false);
        dong1.add(new JLabel("Thành tiền:"));
        thanhTien.setFont(ViewTheme.FONT_SO);
        dong1.add(thanhTien);
        dong1.add(hinhThuc);
        dong1.add(bacSi);
        dong1.add(luu);
        JPanel dong2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        dong2.setOpaque(false);
        dong2.add(nhanPK);
        chan.add(dong2);
        chan.add(dong1);

        JPanel phai = new JPanel(new BorderLayout(0, 8));
        phai.setOpaque(false);
        JPanel dau = new JPanel(new BorderLayout(8, 8));
        dau.setOpaque(false);
        JPanel traiDau = new JPanel(new GridLayout(2, 1, 0, 6));
        traiDau.setOpaque(false);
        traiDau.add(khach);
        traiDau.add(chanDoan);
        dau.add(traiDau, BorderLayout.CENTER);
        dau.add(thongTin, BorderLayout.EAST);
        phai.add(dau, BorderLayout.NORTH);
        phai.add(cuon, BorderLayout.CENTER);
        JPanel duoi = new JPanel(new BorderLayout(0, 8));
        duoi.setOpaque(false);
        duoi.add(ghi, BorderLayout.NORTH);
        duoi.add(chan, BorderLayout.SOUTH);
        phai.add(duoi, BorderLayout.SOUTH);

        JLabel tieu = ViewTheme.tieuDeTrang("Lập toa thuốc");
        JPanel noi = new JPanel(new BorderLayout(0, 8));
        noi.setOpaque(false);
        noi.add(tieu, BorderLayout.NORTH);
        noi.add(phai, BorderLayout.CENTER);
        add(trai, BorderLayout.WEST);
        add(noi, BorderLayout.CENTER);
        tinhTien();
        chanDoan.setText("");
        ghi.putClientProperty("JTextField.placeholderText", "Ghi chú");
        ((ONhap) tim).datGoiY("Tìm thuốc ...");
    }

    public void chonKhach(String maKH) {
        if (maKH == null || maKH.isEmpty()) {
            return;
        }
        for (int i = 0; i < khach.getItemCount(); i++) {
            if (khach.getItemAt(i).startsWith(maKH)) {
                khach.setSelectedIndex(i);
                return;
            }
        }
    }

    private void luuToa() {
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một thuốc.");
            return;
        }
        String nhanKh = String.valueOf(khach.getSelectedItem());
        String maKH = nhanKh.length() >= 4 ? nhanKh.substring(0, 4) : nhanKh;
        String tenKh = nhanKh;
        if (nhanKh.contains(" - ")) {
            String[] tach = nhanKh.split(" - ");
            maKH = tach[0].trim();
            if (tach.length >= 2) {
                tenKh = tach[1].trim();
            }
        }
        ToaThuocView tt = new ToaThuocView();
        tt.maKH = maKH;
        tt.tenKh = tenKh;
        tt.maChanDoan = chanDoan.getText().trim();
        tt.tenBacSi = String.valueOf(bacSi.getSelectedItem());
        tt.maBacSi = "NV03";
        if ("Hồ Văn Khánh".equals(tt.tenBacSi)) {
            tt.maBacSi = "NV02";
        } else if ("Nguyễn Khắc Duy".equals(tt.tenBacSi)) {
            tt.maBacSi = "NV01";
        }
        tt.hinhThucThanhToan = String.valueOf(hinhThuc.getSelectedItem());
        tt.nhanTaiPhongKham = nhanPK.isSelected();
        tt.ghiChu = ghi.getText().trim();
        for (int i = 0; i < model.getRowCount(); i++) {
            String ten = String.valueOf(model.getValueAt(i, 1));
            String maThuoc = ten.length() >= 4 ? ten.substring(0, 4) : ten;
            int soNgay = parseSo(model.getValueAt(i, 2), 1);
            String cach = String.valueOf(model.getValueAt(i, 3));
            int tongSo = parseSo(model.getValueAt(i, 4), 1);
            long donGia = parseTien(model.getValueAt(i, 5));
            long thanh = parseTien(model.getValueAt(i, 6));
            tt.chiTiet.add(new ChiTietToaThuocView(maThuoc, ten, soNgay, cach, tongSo, donGia, thanh));
            tt.thanhTien += thanh;
        }
        ToaThuocDuLieu.them(tt);
        JOptionPane.showMessageDialog(this,
                "Đã lưu toa " + tt.maToaThuoc + " cho " + tenKh
                + ".\nThành tiền " + ViewTheme.tien(tt.thanhTien) + " - " + tt.hinhThucThanhToan);
        model.setRowCount(0);
        tinhTien();
        if (sauLuu != null) {
            sauLuu.run();
        }
    }

    private static int parseSo(Object v, int macDinh) {
        try {
            return Integer.parseInt(String.valueOf(v).replace(",", "").trim());
        } catch (NumberFormatException e) {
            return macDinh;
        }
    }

    private static long parseTien(Object v) {
        try {
            return Long.parseLong(String.valueOf(v).replace(",", "").trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void themTheThuoc(String ten, String giaNhan, String giaSo) {
        JPanel the = new JPanel(new BorderLayout());
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ViewTheme.VIEN),
                new EmptyBorder(8, 8, 8, 8)));
        the.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));
        the.putClientProperty("ten", ten);
        JLabel lb = new JLabel("<html><b>" + ten + "</b><br>Giá: " + giaNhan + "</html>");
        the.add(lb);
        the.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        the.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                long donGia = parseTien(giaSo);
                model.addRow(new Object[]{String.valueOf(model.getRowCount() + 1), ten, "1",
                    "Sáng 1 - Trưa 1 - Chiều 1", "3", giaSo, String.valueOf(3 * donGia)});
                danhLai();
                tinhTien();
            }
        });
        dsThuoc.add(the);
        dsThuoc.add(javax.swing.Box.createVerticalStrut(6));
    }

    private void locThe() {
        String tu = tim.getText().trim().toLowerCase();
        for (java.awt.Component c : dsThuoc.getComponents()) {
            if (c instanceof JPanel) {
                Object ten = ((JPanel) c).getClientProperty("ten");
                c.setVisible(tu.isEmpty() || (ten != null && ten.toString().toLowerCase().contains(tu)));
            }
        }
        dsThuoc.revalidate();
    }

    private void danhLai() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(String.valueOf(i + 1), i, 0);
        }
    }

    private void tinhTien() {
        long tong = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            tong += parseTien(model.getValueAt(i, 6));
        }
        thanhTien.setText(ViewTheme.tien(tong));
    }
}
