package view;

import entity.Thuoc;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import service.impl.DanhMuc_Service;
import view.model.ChiTietToaThuocView;
import view.model.ToaThuocView;

public class ToaThuocPanel extends JPanel {

    private final DefaultTableModel model = ViewTheme.taoModel(
            new String[]{"#", "Thuốc", "Số ngày", "Cách sử dụng", "Tổng số", "Đơn giá", "Thành tiền"}, false, true);
    private final JLabel nhanThanhTien = new JLabel("0");
    private final JPanel panelDsThuoc = new JPanel();
    private final JTextField txtTim = new ONhap("Tìm thuốc ...", 16);
    private final JComboBox<String> cboKhach = new JComboBox<String>(DuLieuMau.dsKhach());
    private final JTextField txtChanDoan = new JTextField();
    private final JTextField txtGhiChu = new JTextField();
    private final JComboBox<String> cboHinhThuc = new JComboBox<String>(new String[]{"Tiền mặt", "Thẻ", "Chuyển khoản"});
    private final JComboBox<String> cboBacSi = new JComboBox<String>(DuLieuMau.dsBacSi());
    private final JCheckBox chkNhanPK = new JCheckBox("KH nhận thuốc tại PK", true);
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

        JPanel panelTrai = new JPanel(new BorderLayout(0, 8));
        panelTrai.setBackground(ViewTheme.TRANG);
        panelTrai.setPreferredSize(new Dimension(250, 400));
        panelTrai.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        txtTim.setPreferredSize(new Dimension(200, 32));
        JButton btnAll = new JButton("ALL");
        ViewTheme.nutChinh(btnAll);
        btnAll.setPreferredSize(new Dimension(220, 32));
        btnAll.addActionListener(e -> {
            txtTim.setText("");
            locThe();
        });
        JPanel panelDauTrai = new JPanel(new BorderLayout(0, 6));
        panelDauTrai.setOpaque(false);
        panelDauTrai.setBorder(new EmptyBorder(8, 8, 8, 8));
        panelDauTrai.add(txtTim, BorderLayout.NORTH);
        panelDauTrai.add(btnAll, BorderLayout.SOUTH);
        panelDsThuoc.setLayout(new BoxLayout(panelDsThuoc, BoxLayout.Y_AXIS));
        panelDsThuoc.setBackground(ViewTheme.TRANG);
        napThuocTuDb();
        txtTim.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                locThe();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                locThe();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                locThe();
            }
        });
        panelTrai.add(panelDauTrai, BorderLayout.NORTH);
        panelTrai.add(new JScrollPane(panelDsThuoc), BorderLayout.CENTER);

        ViewTheme.vienNhap(txtChanDoan);
        ViewTheme.vienHop(cboKhach);
        JLabel nhanThongTin = new JLabel("Giới tính: Nam     Ngày: 28/09/2026");
        nhanThongTin.setFont(ViewTheme.FONT);

        JTable bang = new JTable(model);
        ViewTheme.trangTriBang(bang);
        ViewTheme.ganThaoTac(bang, (lenh, hang) -> {
            if ("Xóa".equals(lenh) || "Sửa".equals(lenh)) {
                model.removeRow(hang);
                danhLai();
                tinhTien();
            }
        });
        JScrollPane scrollBang = new JScrollPane(bang);
        scrollBang.setPreferredSize(new Dimension(400, 180));
        scrollBang.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));

        ViewTheme.vienNhap(txtGhiChu);
        ViewTheme.vienHop(cboHinhThuc);
        ViewTheme.vienHop(cboBacSi);
        chkNhanPK.setOpaque(false);
        chkNhanPK.setFont(ViewTheme.FONT);
        JButton btnLuu = new JButton("Lưu toa thuốc");
        ViewTheme.nutChinh(btnLuu);
        btnLuu.addActionListener(e -> luuToa());

        JPanel panelChan = new JPanel(new GridLayout(2, 1, 0, 8));
        panelChan.setOpaque(false);
        JPanel panelDong1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelDong1.setOpaque(false);
        panelDong1.add(new JLabel("Thành tiền:"));
        nhanThanhTien.setFont(ViewTheme.FONT_SO);
        panelDong1.add(nhanThanhTien);
        panelDong1.add(cboHinhThuc);
        panelDong1.add(cboBacSi);
        panelDong1.add(btnLuu);
        JPanel panelDong2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelDong2.setOpaque(false);
        panelDong2.add(chkNhanPK);
        panelChan.add(panelDong2);
        panelChan.add(panelDong1);

        JPanel panelPhai = new JPanel(new BorderLayout(0, 8));
        panelPhai.setOpaque(false);
        JPanel panelDau = new JPanel(new BorderLayout(8, 8));
        panelDau.setOpaque(false);
        JPanel panelTraiDau = new JPanel(new GridLayout(2, 1, 0, 6));
        panelTraiDau.setOpaque(false);
        panelTraiDau.add(cboKhach);
        panelTraiDau.add(txtChanDoan);
        panelDau.add(panelTraiDau, BorderLayout.CENTER);
        panelDau.add(nhanThongTin, BorderLayout.EAST);
        panelPhai.add(panelDau, BorderLayout.NORTH);
        panelPhai.add(scrollBang, BorderLayout.CENTER);
        JPanel panelDuoi = new JPanel(new BorderLayout(0, 8));
        panelDuoi.setOpaque(false);
        panelDuoi.add(txtGhiChu, BorderLayout.NORTH);
        panelDuoi.add(panelChan, BorderLayout.SOUTH);
        panelPhai.add(panelDuoi, BorderLayout.SOUTH);

        JLabel nhanTieuDe = ViewTheme.tieuDeTrang("Lập toa thuốc");
        JPanel panelNoiDung = new JPanel(new BorderLayout(0, 8));
        panelNoiDung.setOpaque(false);
        panelNoiDung.add(nhanTieuDe, BorderLayout.NORTH);
        panelNoiDung.add(panelPhai, BorderLayout.CENTER);
        add(panelTrai, BorderLayout.WEST);
        add(panelNoiDung, BorderLayout.CENTER);
        tinhTien();
        txtChanDoan.setText("");
        txtGhiChu.putClientProperty("JTextField.placeholderText", "Ghi chú");
        ((ONhap) txtTim).datGoiY("Tìm thuốc ...");
    }

    public void chonKhach(String maKH) {
        if (maKH == null || maKH.isEmpty()) {
            return;
        }
        for (int i = 0; i < cboKhach.getItemCount(); i++) {
            if (cboKhach.getItemAt(i).startsWith(maKH)) {
                cboKhach.setSelectedIndex(i);
                return;
            }
        }
    }

    private void luuToa() {
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một thuốc.");
            return;
        }
        String nhanKh = String.valueOf(cboKhach.getSelectedItem());
        String maKH = nhanKh.length() >= 4 ? nhanKh.substring(0, 4) : nhanKh;
        String tenKh = nhanKh;
        if (nhanKh.contains(" - ")) {
            String[] tach = nhanKh.split(" - ");
            maKH = tach[0].trim();
            if (tach.length >= 2) {
                tenKh = tach[1].trim();
            }
        }
        ToaThuocView toaThuoc = new ToaThuocView();
        toaThuoc.maKH = maKH;
        toaThuoc.tenKh = tenKh;
        toaThuoc.maChanDoan = txtChanDoan.getText().trim();
        toaThuoc.tenBacSi = String.valueOf(cboBacSi.getSelectedItem());
        toaThuoc.maBacSi = DuLieuMau.maBacSiTheoTen(toaThuoc.tenBacSi);
        if (toaThuoc.maBacSi.isEmpty()) {
            toaThuoc.maBacSi = "NV03";
        }
        toaThuoc.hinhThucThanhToan = String.valueOf(cboHinhThuc.getSelectedItem());
        toaThuoc.nhanTaiPhongKham = chkNhanPK.isSelected();
        toaThuoc.ghiChu = txtGhiChu.getText().trim();
        for (int i = 0; i < model.getRowCount(); i++) {
            String ten = String.valueOf(model.getValueAt(i, 1));
            String maThuoc = ten.length() >= 4 ? ten.substring(0, 4) : ten;
            int soNgay = parseSo(model.getValueAt(i, 2), 1);
            String cach = String.valueOf(model.getValueAt(i, 3));
            int tongSo = parseSo(model.getValueAt(i, 4), 1);
            long donGia = parseTien(model.getValueAt(i, 5));
            long thanh = parseTien(model.getValueAt(i, 6));
            toaThuoc.chiTiet.add(new ChiTietToaThuocView(maThuoc, ten, soNgay, cach, tongSo, donGia, thanh));
            toaThuoc.thanhTien += thanh;
        }
        ToaThuocDuLieu.them(toaThuoc);
        JOptionPane.showMessageDialog(this,
                "Đã lưu toa " + toaThuoc.maToaThuoc + " cho " + tenKh
                + ".\nThành tiền " + ViewTheme.tien(toaThuoc.thanhTien) + " - " + toaThuoc.hinhThucThanhToan);
        model.setRowCount(0);
        tinhTien();
        if (sauLuu != null) {
            sauLuu.run();
        }
    }

    private static int parseSo(Object giaTri, int macDinh) {
        try {
            return Integer.parseInt(String.valueOf(giaTri).replace(",", "").trim());
        } catch (NumberFormatException e) {
            return macDinh;
        }
    }

    private static long parseTien(Object giaTri) {
        try {
            return Long.parseLong(String.valueOf(giaTri).replace(",", "").trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void napThuocTuDb() {
        List<Thuoc> danhSach = new DanhMuc_Service().findAllThuoc();
        if (danhSach.isEmpty()) {
            themTheThuoc("0001 - aptx 4869", "36,000", "36000");
            return;
        }
        for (Thuoc thuoc : danhSach) {
            String ten = thuoc.getMaThuoc() + " - " + thuoc.getTenThuoc();
            themTheThuoc(ten, ViewTheme.tien(thuoc.getGia()), String.valueOf(thuoc.getGia()));
        }
    }

    private void themTheThuoc(String ten, String giaNhan, String giaSo) {
        JPanel panelThe = new JPanel(new BorderLayout());
        panelThe.setBackground(ViewTheme.TRANG);
        panelThe.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ViewTheme.VIEN),
                new EmptyBorder(8, 8, 8, 8)));
        panelThe.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));
        panelThe.putClientProperty("ten", ten);
        JLabel nhanThe = new JLabel("<html><b>" + ten + "</b><br>Giá: " + giaNhan + "</html>");
        panelThe.add(nhanThe);
        panelThe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panelThe.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                long donGia = parseTien(giaSo);
                model.addRow(new Object[]{String.valueOf(model.getRowCount() + 1), ten, "1",
                    "Sáng 1 - Trưa 1 - Chiều 1", "3", giaSo, String.valueOf(3 * donGia)});
                danhLai();
                tinhTien();
            }
        });
        panelDsThuoc.add(panelThe);
        panelDsThuoc.add(Box.createVerticalStrut(6));
    }

    private void locThe() {
        String tuKhoa = txtTim.getText().trim().toLowerCase();
        for (Component component : panelDsThuoc.getComponents()) {
            if (component instanceof JPanel) {
                Object ten = ((JPanel) component).getClientProperty("ten");
                component.setVisible(tuKhoa.isEmpty() || (ten != null && ten.toString().toLowerCase().contains(tuKhoa)));
            }
        }
        panelDsThuoc.revalidate();
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
        nhanThanhTien.setText(ViewTheme.tien(tong));
    }
}
