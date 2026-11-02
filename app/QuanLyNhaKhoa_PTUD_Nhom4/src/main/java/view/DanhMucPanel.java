package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DanhMucPanel extends JPanel {

    private final DieuHuong dieuHuong;

    public DanhMucPanel(DieuHuong dieuHuong) {
        this.dieuHuong = dieuHuong;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));
        JPanel pnlThe = new JPanel(new BorderLayout(0, 10));
        pnlThe.setBackground(ViewTheme.TRANG);
        pnlThe.setBorder(new EmptyBorder(12, 14, 16, 14));
        pnlThe.add(ViewTheme.tieuDeTrang("Danh mục"), BorderLayout.NORTH);

        JTextField tim = new ONhap("Search for objects by name", 30);
        tim.setPreferredSize(new Dimension(400, 34));
        JPanel pnlLuoi = new JPanel(new GridBagLayout());
        pnlLuoi.setOpaque(false);
        JPanel pnlCot1 = cot(
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
        JPanel pnlCot2 = cot(
                nhom("4. Kho", new String[][]{
            {"Loại sản phẩm", "loaisanpham"},
            {"Nhập kho", "nhapkho"},
            {"Sản phẩm", "sanpham"},
            {"Xuất kho", "xuatkho"},
            {"Đơn vị", "donvi"}
        }),
                nhom("5. Toa thuốc", new String[][]{
            {"Toa thuốc", "toathuoc"},
            {"Thuốc", "thuoc"}
        }));
        JPanel pnlCot3 = cot(
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
        pnlLuoi.add(pnlCot1, gbc);
        gbc.gridx = 1;
        pnlLuoi.add(pnlCot2, gbc);
        gbc.gridx = 2;
        pnlLuoi.add(pnlCot3, gbc);

        tim.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                loc(pnlLuoi, tim.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                loc(pnlLuoi, tim.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                loc(pnlLuoi, tim.getText());
            }
        });

        JPanel pnlGiua = new JPanel(new BorderLayout(0, 10));
        pnlGiua.setOpaque(false);
        pnlGiua.add(tim, BorderLayout.NORTH);
        pnlGiua.add(pnlLuoi, BorderLayout.CENTER);
        pnlThe.add(pnlGiua, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(pnlThe, BorderLayout.NORTH);
    }

    private JPanel cot(JPanel... nhom) {
        JPanel pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        pnl.setOpaque(false);
        for (JPanel n : nhom) {
            n.setAlignmentX(LEFT_ALIGNMENT);
            pnl.add(n);
            pnl.add(Box.createVerticalStrut(10));
        }
        return pnl;
    }

    private JPanel nhom(String tieu, String[][] muc) {
        JPanel pnl = new JPanel(new BorderLayout(0, 4));
        pnl.setBackground(ViewTheme.NEN_TIM);
        pnl.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ViewTheme.VIEN),
                new EmptyBorder(8, 10, 8, 10)));
        JLabel lbl = ViewTheme.tieuDeMuc(tieu);
        pnl.add(lbl, BorderLayout.NORTH);
        JPanel pnlDs = new JPanel(new GridLayout(0, 1, 0, 0));
        pnlDs.setOpaque(false);
        for (String[] m : muc) {
            JButton btn = new JButton(m[0]);
            ViewTheme.nutLink(btn);
            btn.putClientProperty("tu", m[0]);
            btn.addActionListener(e -> dieuHuong.mo(m[1]));
            pnlDs.add(btn);
        }
        pnl.add(pnlDs, BorderLayout.CENTER);
        return pnl;
    }

    private void loc(JPanel goc, String tu) {
        String khoa = tu == null ? "" : tu.trim().toLowerCase();
        anHien(goc, khoa);
        goc.revalidate();
        goc.repaint();
    }

    private void anHien(Container goc, String khoa) {
        for (Component component : goc.getComponents()) {
            if (component instanceof JButton) {
                Object ten = ((JButton) component).getClientProperty("tu");
                boolean hien = khoa.isEmpty() || (ten != null && ten.toString().toLowerCase().contains(khoa));
                component.setVisible(hien);
            } else if (component instanceof Container) {
                anHien((Container) component, khoa);
            }
        }
    }
}
