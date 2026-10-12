package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NhapKhoPanel extends JPanel {

    private final JPanel dsDong = new JPanel();
    private final JLabel tong = new JLabel("0");
    private final String tieuDe;
    private final String nhanNut;

    public NhapKhoPanel(String tieuDe, String nhanNut) {
        this.tieuDe = tieuDe;
        this.nhanNut = nhanNut;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));
        JPanel the = new JPanel(new BorderLayout(0, 12));
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(new EmptyBorder(16, 18, 16, 18));

        JLabel tieu = ViewTheme.tieuDeTrang(tieuDe);
        JTextField ngay = o("2026-09-28");
        JComboBox<String> loai = new JComboBox<String>(new String[]{"Thuốc", "Vật liệu tiêu hao", "Thiết bị nha khoa", "Công cụ, dụng cụ"});
        JComboBox<String> ncc = new JComboBox<String>(new String[]{"STRAUMANN", "Nhà thuốc Trung tâm", "Dental Supply"});
        JTextField lyDo = o("");
        ViewTheme.vienHop(loai);
        ViewTheme.vienHop(ncc);

        JPanel form = new JPanel(new GridLayout(0, 2, 12, 10));
        form.setOpaque(false);
        form.add(nhan("Ngày nhập kho"));
        form.add(ngay);
        form.add(nhan("Loại vật tư"));
        form.add(loai);
        form.add(nhan("Nhà cung cấp"));
        form.add(ncc);
        form.add(nhan("<html>Lý do <font color='#e74c3c'>*</font></html>"));
        form.add(lyDo);

        dsDong.setLayout(new BoxLayout(dsDong, BoxLayout.Y_AXIS));
        dsDong.setOpaque(false);
        dsDong.add(dongHang());
        JButton cong = new JButton("+");
        ViewTheme.nutChinh(cong);
        cong.addActionListener(e -> {
            dsDong.add(dongHang());
            dsDong.revalidate();
        });
        JPanel giuaCong = new JPanel(new FlowLayout(FlowLayout.CENTER));
        giuaCong.setOpaque(false);
        giuaCong.add(cong);

        JLabel file = new JLabel("No file chosen");
        JButton chon = new JButton("Choose File");
        ViewTheme.nutXam(chon);
        chon.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                file.setText(fc.getSelectedFile().getName());
            }
        });
        JButton mau = new JButton("Tải file excel mẫu");
        ViewTheme.nutLink(mau);
        mau.setForeground(ViewTheme.XANH_DAM);
        mau.addActionListener(e -> JOptionPane.showMessageDialog(this, "File mẫu gồm cột: Sản phẩm, Số lượng, Đơn giá."));

        JPanel excel = new JPanel(new GridLayout(0, 2, 8, 8));
        excel.setOpaque(false);
        excel.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập liệu từ excel"));
        excel.add(nhan("Nhập file excel"));
        JPanel hangFile = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        hangFile.setOpaque(false);
        hangFile.add(chon);
        hangFile.add(file);
        excel.add(hangFile);
        excel.add(nhan("File mẫu"));
        excel.add(mau);

        JButton boQua = new JButton("Bỏ qua");
        JButton luu = new JButton(nhanNut);
        ViewTheme.nutXam(boQua);
        ViewTheme.nutChinh(luu);
        boQua.addActionListener(e -> lyDo.setText(""));
        luu.addActionListener(e -> {
            if (lyDo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập lý do.");
                return;
            }
            JOptionPane.showMessageDialog(this, "Đã ghi nhận " + tieuDe.toLowerCase() + ". Tổng thành tiền " + tong.getText());
            lyDo.setText("");
        });
        JPanel chan = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        chan.setOpaque(false);
        chan.add(boQua);
        chan.add(luu);

        JPanel tieuCot = new JPanel(new GridLayout(1, 4, 8, 0));
        tieuCot.setOpaque(false);
        tieuCot.add(ViewTheme.chuPhu("Sản phẩm"));
        tieuCot.add(ViewTheme.chuPhu("Số lượng"));
        tieuCot.add(ViewTheme.chuPhu("Đơn giá"));
        tieuCot.add(ViewTheme.chuPhu("Thành tiền"));

        JPanel tongHang = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        tongHang.setOpaque(false);
        tongHang.add(new JLabel("Tổng thành tiền"));
        tong.setFont(ViewTheme.FONT_SO);
        tongHang.add(tong);

        JPanel noi = new JPanel();
        noi.setLayout(new BoxLayout(noi, BoxLayout.Y_AXIS));
        noi.setOpaque(false);
        form.setAlignmentX(LEFT_ALIGNMENT);
        tieuCot.setAlignmentX(LEFT_ALIGNMENT);
        dsDong.setAlignmentX(LEFT_ALIGNMENT);
        giuaCong.setAlignmentX(LEFT_ALIGNMENT);
        tongHang.setAlignmentX(LEFT_ALIGNMENT);
        excel.setAlignmentX(LEFT_ALIGNMENT);
        chan.setAlignmentX(LEFT_ALIGNMENT);
        noi.add(form);
        noi.add(Box.createVerticalStrut(12));
        noi.add(tieuCot);
        noi.add(dsDong);
        noi.add(giuaCong);
        noi.add(tongHang);
        noi.add(Box.createVerticalStrut(8));
        noi.add(excel);
        noi.add(Box.createVerticalStrut(12));
        noi.add(chan);

        the.add(tieu, BorderLayout.NORTH);
        the.add(noi, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(the, BorderLayout.NORTH);
    }

    private JPanel dongHang() {
        JPanel p = new JPanel(new GridLayout(1, 5, 8, 0));
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(4, 0, 4, 0));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        JComboBox<String> sp = new JComboBox<String>(new String[]{"Găng tay", "Khẩu trang", "Mũi khoan", "aptx 4869"});
        JTextField sl = o("0");
        JTextField gia = o("0");
        JTextField tien = o("0");
        tien.setEditable(false);
        ViewTheme.vienHop(sp);
        Runnable tinh = () -> {
            try {
                long a = Long.parseLong(sl.getText().trim().replace(",", ""));
                long b = Long.parseLong(gia.getText().trim().replace(",", ""));
                tien.setText(ViewTheme.tien(a * b));
            } catch (NumberFormatException ex) {
                tien.setText("0");
            }
            tinhTong();
        };
        sl.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                tinh.run();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                tinh.run();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                tinh.run();
            }
        });
        gia.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                tinh.run();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                tinh.run();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                tinh.run();
            }
        });
        JButton xoa = new JButton("Xóa");
        ViewTheme.nutDo(xoa);
        xoa.addActionListener(e -> {
            if (dsDong.getComponentCount() > 1) {
                dsDong.remove(p);
                dsDong.revalidate();
                dsDong.repaint();
                tinhTong();
            }
        });
        p.add(sp);
        p.add(sl);
        p.add(gia);
        p.add(tien);
        p.add(xoa);
        return p;
    }

    private void tinhTong() {
        long sum = 0;
        for (java.awt.Component c : dsDong.getComponents()) {
            if (c instanceof JPanel) {
                JPanel p = (JPanel) c;
                if (p.getComponentCount() >= 4 && p.getComponent(3) instanceof JTextField) {
                    try {
                        sum += Long.parseLong(((JTextField) p.getComponent(3)).getText().replace(",", ""));
                    } catch (NumberFormatException ex) {
                    }
                }
            }
        }
        tong.setText(ViewTheme.tien(sum));
    }

    private JTextField o(String giaTri) {
        JTextField t = new JTextField(giaTri);
        ViewTheme.vienNhap(t);
        return t;
    }

    private JLabel nhan(String chu) {
        JLabel lb = new JLabel(chu, JLabel.RIGHT);
        lb.setFont(ViewTheme.FONT);
        return lb;
    }
}
