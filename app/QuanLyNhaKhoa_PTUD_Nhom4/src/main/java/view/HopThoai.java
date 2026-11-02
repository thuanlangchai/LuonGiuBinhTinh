package view;

import java.awt.BorderLayout;
import java.awt.Component;
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

    private final JPanel pnlLuoi = new JPanel(new GridBagLayout());
    private final List<JComponent> batBuoc = new ArrayList<JComponent>();
    private int dong;
    private Runnable khiLuu;

    public HopThoai(Window owner, String tieuDe, String nhanLuu) {
        super(owner, tieuDe, ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pnlLuoi.setBackground(ViewTheme.TRANG);
        pnlLuoi.setBorder(new EmptyBorder(16, 28, 12, 28));

        JScrollPane scrCuon = new JScrollPane(pnlLuoi);
        scrCuon.setBorder(BorderFactory.createEmptyBorder());
        scrCuon.getViewport().setBackground(ViewTheme.TRANG);

        JPanel pnlChan = new JPanel(new BorderLayout());
        pnlChan.setBackground(ViewTheme.TRANG);
        pnlChan.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, ViewTheme.VIEN),
                new EmptyBorder(12, 20, 12, 20)));

        JButton btnBoQua = new JButton("Bỏ qua");
        JButton btnLuu = new JButton(nhanLuu);
        ViewTheme.nutXam(btnBoQua);
        ViewTheme.nutChinh(btnLuu);
        btnBoQua.addActionListener(e -> dispose());
        btnLuu.addActionListener(e -> luu());
        JPanel pnlPhai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        pnlPhai.setOpaque(false);
        pnlPhai.add(btnBoQua);
        pnlPhai.add(btnLuu);
        pnlChan.add(pnlPhai, BorderLayout.EAST);

        add(scrCuon, BorderLayout.CENTER);
        add(pnlChan, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(btnLuu);
    }

    public void them(String nhan, JComponent hien, JComponent kiemTra, boolean batBuocTruong) {
        GridBagConstraints lc = new GridBagConstraints();
        lc.gridx = 0;
        lc.gridy = dong;
        lc.anchor = GridBagConstraints.EAST;
        lc.insets = new Insets(7, 4, 7, 12);
        JLabel lbl = new JLabel(batBuocTruong
                ? "<html>" + nhan + " <font color='#e74c3c'>*</font></html>"
                : nhan);
        lbl.setFont(ViewTheme.FONT);
        pnlLuoi.add(lbl, lc);

        GridBagConstraints fc = new GridBagConstraints();
        fc.gridx = 1;
        fc.gridy = dong;
        fc.weightx = 1;
        fc.fill = GridBagConstraints.HORIZONTAL;
        fc.insets = new Insets(7, 0, 7, 4);
        if (!(hien instanceof JScrollPane)) {
            hien.setPreferredSize(new Dimension(360, 34));
        }
        pnlLuoi.add(hien, fc);
        if (batBuocTruong && kiemTra != null) {
            batBuoc.add(kiemTra);
        }
        dong++;
    }

    public JTextField chu(String nhan, String giaTri, boolean batBuocTruong) {
        JTextField txt = new JTextField(giaTri == null ? "" : giaTri);
        ViewTheme.vienNhap(txt);
        them(nhan, txt, txt, batBuocTruong);
        return txt;
    }

    public JTextField chuGoiY(String nhan, String goiY, boolean batBuocTruong) {
        ONhap t = new ONhap(goiY, 24);
        them(nhan, t, t, batBuocTruong);
        return t;
    }

    public JTextArea vung(String nhan, String giaTri, int hang) {
        JTextArea txa = new JTextArea(giaTri == null ? "" : giaTri, hang, 24);
        txa.setLineWrap(true);
        txa.setWrapStyleWord(true);
        txa.setFont(ViewTheme.FONT);
        JScrollPane scrCuon = new JScrollPane(txa);
        scrCuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        scrCuon.setPreferredSize(new Dimension(360, 70));
        them(nhan, scrCuon, txa, false);
        return txa;
    }

    public JComboBox<String> chon(String nhan, boolean batBuocTruong, String... muc) {
        JComboBox<String> cmb = new JComboBox<String>(muc);
        ViewTheme.vienHop(cmb);
        them(nhan, cmb, cmb, batBuocTruong);
        return cmb;
    }

    public JComboBox<String> chonVoiNut(String nhan, boolean batBuocTruong, String... muc) {
        JComboBox<String> cmb = new JComboBox<String>(muc);
        ViewTheme.vienHop(cmb);
        JButton btnCong = new JButton("+");
        ViewTheme.nutXam(btnCong);
        btnCong.setPreferredSize(new Dimension(42, 34));
        btnCong.addActionListener(e -> {
            String ten = JOptionPane.showInputDialog(this, "Nhập giá trị mới");
            if (ten != null && !ten.trim().isEmpty()) {
                cmb.addItem(ten.trim());
                cmb.setSelectedItem(ten.trim());
            }
        });
        JPanel pnl = new JPanel(new BorderLayout(6, 0));
        pnl.setOpaque(false);
        pnl.add(cmb, BorderLayout.CENTER);
        pnl.add(btnCong, BorderLayout.EAST);
        them(nhan, pnl, cmb, batBuocTruong);
        return cmb;
    }

    public JCheckBox oChon(String nhan, boolean bat) {
        JCheckBox chk = new JCheckBox();
        chk.setSelected(bat);
        chk.setOpaque(false);
        them(nhan, chk, null, false);
        return chk;
    }


    public void themHang(JComponent c) {
        GridBagConstraints fc = new GridBagConstraints();
        fc.gridx = 0;
        fc.gridy = dong;
        fc.gridwidth = 2;
        fc.weightx = 1;
        fc.fill = GridBagConstraints.HORIZONTAL;
        fc.insets = new Insets(6, 4, 6, 4);
        pnlLuoi.add(c, fc);
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
        for (Component component : pnlLuoi.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
            if (component instanceof JScrollPane) {
                Component view = ((JScrollPane) component).getViewport().getView();
                if (view instanceof JTextArea) {
                    ((JTextArea) view).setText("");
                }
            }
        }
    }
}
