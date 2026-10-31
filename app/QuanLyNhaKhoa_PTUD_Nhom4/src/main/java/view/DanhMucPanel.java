package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DanhMucPanel extends JPanel {

    private final DieuHuong dieuHuong;

    public DanhMucPanel(DieuHuong dieuHuong) {
        this.dieuHuong = dieuHuong;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));
        JPanel the = new JPanel(new BorderLayout(0, 10));
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(new EmptyBorder(12, 14, 16, 14));
        the.add(ViewTheme.tieuDeTrang("Danh mục"), BorderLayout.NORTH);

        JTextField tim = new ONhap("Search for objects by name", 30);
        tim.setPreferredSize(new Dimension(400, 34));
        JPanel luoi = new JPanel(new GridBagLayout());
        luoi.setOpaque(false);
        JPanel cot1 = cot(
                nhom("1. Phòng khám", new String[][]{
                    {"Chi nhánh", "chinhanh"},
                    {"Khách hàng", "khachhang"},
                    {"Nhân viên", "nhanvien"}
                }),
                nhom("2. Dịch vụ", new String[][]{
                    {"Dịch vụ", "dichvu"},
                    {"Nhóm dịch vụ", "nhomdichvu"}
                }),
                nhom("3. Doanh thu", new String[][]{
                    {"Phiếu chi", "phieuchi"},
                    {"Phiếu thu", "phieuthu"}
                }));
        JPanel cot2 = cot(
                nhom("4. Kho", new String[][]{
                    {"Loại sản phẩm", "loaisanpham"},
                    {"Nhập kho", "nhapkho"},
                    {"Sản phẩm", "sanpham"},
                    {"Xuất kho", "xuatkho"},
                    {"Đơn vị", "donvi"}
                }),
                nhom("5. Toa thuốc", new String[][]{
                    {"Thuốc", "thuoc"}
                }));
        JPanel cot3 = cot(
                nhom("Master data", new String[][]{
                    {"Nhóm khách hàng", "nhomkhach"},
                    {"Răng", "rang"},
                    {"Loại răng", "loairang"},
                    {"Điều trị", "dieutri"},
                    {"Thời gian làm việc", "thoigian"},
                    {"Nhà cung cấp", "nhacungcap"}
                }));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 6, 0, 6);
        gbc.gridx = 0;
        luoi.add(cot1, gbc);
        gbc.gridx = 1;
        luoi.add(cot2, gbc);
        gbc.gridx = 2;
        luoi.add(cot3, gbc);

        tim.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                loc(luoi, tim.getText());
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                loc(luoi, tim.getText());
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                loc(luoi, tim.getText());
            }
        });

        JPanel giua = new JPanel(new BorderLayout(0, 10));
        giua.setOpaque(false);
        giua.add(tim, BorderLayout.NORTH);
        giua.add(luoi, BorderLayout.CENTER);
        the.add(giua, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(the, BorderLayout.NORTH);
    }

    private JPanel cot(JPanel... nhom) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        for (JPanel n : nhom) {
            n.setAlignmentX(LEFT_ALIGNMENT);
            p.add(n);
            p.add(Box.createVerticalStrut(10));
        }
        return p;
    }

    private JPanel nhom(String tieu, String[][] muc) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setBackground(ViewTheme.NEN_TIM);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ViewTheme.VIEN),
                new EmptyBorder(8, 10, 8, 10)));
        JLabel lb = ViewTheme.tieuDeMuc(tieu);
        p.add(lb, BorderLayout.NORTH);
        JPanel ds = new JPanel(new GridLayout(0, 1, 0, 0));
        ds.setOpaque(false);
        for (String[] m : muc) {
            JButton b = new JButton(m[0]);
            ViewTheme.nutLink(b);
            b.putClientProperty("tu", m[0]);
            b.addActionListener(e -> dieuHuong.mo(m[1]));
            ds.add(b);
        }
        p.add(ds, BorderLayout.CENTER);
        return p;
    }

    private void loc(JPanel goc, String tu) {
        String khoa = tu == null ? "" : tu.trim().toLowerCase();
        anHien(goc, khoa);
        goc.revalidate();
        goc.repaint();
    }

    private void anHien(java.awt.Container goc, String khoa) {
        for (java.awt.Component c : goc.getComponents()) {
            if (c instanceof JButton) {
                Object ten = ((JButton) c).getClientProperty("tu");
                boolean hien = khoa.isEmpty() || (ten != null && ten.toString().toLowerCase().contains(khoa));
                c.setVisible(hien);
            } else if (c instanceof java.awt.Container) {
                anHien((java.awt.Container) c, khoa);
            }
        }
    }
}
