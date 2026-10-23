package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class HopThoai extends JDialog {

    private final JPanel luoi = new JPanel(new GridBagLayout());
    private final List<JComponent> batBuoc = new ArrayList<JComponent>();
    private int dong;
    private Runnable khiLuu;

    public HopThoai(Window owner, String tieuDe, String nhanLuu, String nhanTiepTuc) {
        super(owner, tieuDe, ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        luoi.setBackground(ViewTheme.TRANG);
        luoi.setBorder(new EmptyBorder(16, 28, 12, 28));

        JScrollPane cuon = new JScrollPane(luoi);
        cuon.setBorder(BorderFactory.createEmptyBorder());
        cuon.getViewport().setBackground(ViewTheme.TRANG);

        JPanel chan = new JPanel(new BorderLayout());
        chan.setBackground(ViewTheme.TRANG);
        chan.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, ViewTheme.VIEN),
                new EmptyBorder(12, 20, 12, 20)));

        JPanel trai = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        trai.setOpaque(false);
        if (nhanTiepTuc != null) {
            JLabel lb = new JLabel("<html>" + nhanTiepTuc + "</html>");
            lb.setFont(ViewTheme.FONT);
            trai.add(lb);
        }
        chan.add(trai, BorderLayout.WEST);

        JButton boQua = new JButton("Bỏ qua");
        JButton luu = new JButton(nhanLuu);
        ViewTheme.nutXam(boQua);
        ViewTheme.nutChinh(luu);
        boQua.addActionListener(e -> dispose());
        luu.addActionListener(e -> luu());
        JPanel phai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        phai.setOpaque(false);
        phai.add(boQua);
        phai.add(luu);
        chan.add(phai, BorderLayout.EAST);

        add(cuon, BorderLayout.CENTER);
        add(chan, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(luu);
    }

    public void them(String nhan, JComponent hien, JComponent kiemTra, boolean batBuocTruong) {
        GridBagConstraints lc = new GridBagConstraints();
        lc.gridx = 0;
        lc.gridy = dong;
        lc.anchor = GridBagConstraints.EAST;
        lc.insets = new Insets(7, 4, 7, 12);
        JLabel lb = new JLabel(batBuocTruong
                ? "<html>" + nhan + " <font color='#e74c3c'>*</font></html>"
                : nhan);
        lb.setFont(ViewTheme.FONT);
        luoi.add(lb, lc);

        GridBagConstraints fc = new GridBagConstraints();
        fc.gridx = 1;
        fc.gridy = dong;
        fc.weightx = 1;
        fc.fill = GridBagConstraints.HORIZONTAL;
        fc.insets = new Insets(7, 0, 7, 4);
        if (!(hien instanceof JScrollPane)) {
            hien.setPreferredSize(new Dimension(360, 34));
        }
        luoi.add(hien, fc);
        if (batBuocTruong && kiemTra != null) {
            batBuoc.add(kiemTra);
        }
        dong++;
    }

    public JTextField chu(String nhan, String giaTri, boolean batBuocTruong) {
        JTextField t = new JTextField(giaTri == null ? "" : giaTri);
        ViewTheme.vienNhap(t);
        them(nhan, t, t, batBuocTruong);
        return t;
    }

    public JTextField chuGoiY(String nhan, String goiY, boolean batBuocTruong) {
        ONhap t = new ONhap(goiY, 24);
        them(nhan, t, t, batBuocTruong);
        return t;
    }

    public JTextArea vung(String nhan, String giaTri, int hang) {
        JTextArea a = new JTextArea(giaTri == null ? "" : giaTri, hang, 24);
        a.setLineWrap(true);
        a.setWrapStyleWord(true);
        a.setFont(ViewTheme.FONT);
        JScrollPane cuon = new JScrollPane(a);
        cuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        cuon.setPreferredSize(new Dimension(360, 70));
        them(nhan, cuon, a, false);
        return a;
    }

    public JComboBox<String> chon(String nhan, boolean batBuocTruong, String... muc) {
        JComboBox<String> c = new JComboBox<String>(muc);
        ViewTheme.vienHop(c);
        them(nhan, c, c, batBuocTruong);
        return c;
    }

    public JComboBox<String> chonVoiNut(String nhan, boolean batBuocTruong, String... muc) {
        JComboBox<String> c = new JComboBox<String>(muc);
        ViewTheme.vienHop(c);
        JButton cong = new JButton("+");
        ViewTheme.nutXam(cong);
        cong.setPreferredSize(new Dimension(42, 34));
        cong.addActionListener(e -> {
            String ten = JOptionPane.showInputDialog(this, "Nhập giá trị mới");
            if (ten != null && !ten.trim().isEmpty()) {
                c.addItem(ten.trim());
                c.setSelectedItem(ten.trim());
            }
        });
        JPanel p = new JPanel(new BorderLayout(6, 0));
        p.setOpaque(false);
        p.add(c, BorderLayout.CENTER);
        p.add(cong, BorderLayout.EAST);
        them(nhan, p, c, batBuocTruong);
        return c;
    }

    public JCheckBox oChon(String nhan, boolean bat) {
        JCheckBox c = new JCheckBox();
        c.setSelected(bat);
        c.setOpaque(false);
        them(nhan, c, null, false);
        return c;
    }


    public void themHang(JComponent c) {
        GridBagConstraints fc = new GridBagConstraints();
        fc.gridx = 0;
        fc.gridy = dong;
        fc.gridwidth = 2;
        fc.weightx = 1;
        fc.fill = GridBagConstraints.HORIZONTAL;
        fc.insets = new Insets(6, 4, 6, 4);
        luoi.add(c, fc);
        dong++;
    }

    public void hien(Runnable khiLuu) {
        this.khiLuu = khiLuu;
        pack();
        int cao = Math.min(Math.max(getHeight() + 10, 260), 700);
        setSize(Math.max(getWidth(), 780), cao);
        setLocationRelativeTo(getOwner());
        setVisible(true);
    }

    private void luu() {
        for (JComponent c : batBuoc) {
            if (c instanceof JTextField && ((JTextField) c).getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ các trường bắt buộc.");
                return;
            }
            if (c instanceof JComboBox && (((JComboBox<?>) c).getSelectedItem() == null
                    || String.valueOf(((JComboBox<?>) c).getSelectedItem()).trim().isEmpty())) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ các trường bắt buộc.");
                return;
            }
        }
        if (khiLuu != null) {
            khiLuu.run();        
        } else {
            dispose();
        }
    }

    private void xoaO() {
        for (java.awt.Component c : luoi.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
            if (c instanceof JScrollPane) {
                java.awt.Component view = ((JScrollPane) c).getViewport().getView();
                if (view instanceof JTextArea) {
                    ((JTextArea) view).setText("");
                }
            }
        }
    }
}
