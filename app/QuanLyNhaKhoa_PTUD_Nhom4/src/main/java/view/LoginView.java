package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginView extends JFrame {

    private final JTextField txtTen = new JTextField(22);
    private final JPasswordField txtMatKhau = new JPasswordField(22);
    private final JButton btnDangNhap = new JButton("Đăng nhập");
    private final JButton btnThoat = new JButton("Thoát");
    private final Box box;

    public LoginView() {
        setTitle("Đăng nhập - Quản lý nha khoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setMinimumSize(new Dimension(420, 420));
        setLocationRelativeTo(null);

        JPanel goc = new JPanel(new BorderLayout());
        goc.setBackground(ViewTheme.NEN_TIM);

        JPanel dau = new JPanel(new GridLayout(2, 1));
        dau.setBackground(ViewTheme.XANH);
        dau.setBorder(new EmptyBorder(28, 16, 28, 16));
        JLabel logo = new JLabel("MAYDENTAL", JLabel.CENTER);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logo.setForeground(Color.WHITE);
        JLabel phu = new JLabel("Hệ thống quản lý nha khoa", JLabel.CENTER);
        phu.setFont(ViewTheme.FONT);
        phu.setForeground(new Color(224, 242, 254));
        dau.add(logo);
        dau.add(phu);

        JPanel the = new JPanel(new BorderLayout(0, 16));
        the.setBackground(Color.WHITE);
        the.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ViewTheme.VIEN),
                new EmptyBorder(24, 28, 24, 28)));
        JLabel tieu = ViewTheme.tieuDeTrang("Đăng nhập");
        JPanel form = new JPanel(new GridLayout(4, 1, 0, 6));
        form.setOpaque(false);
        JLabel lbTen = ViewTheme.chuPhu("Tên đăng nhập");
        JLabel lbMk = ViewTheme.chuPhu("Mật khẩu");
        ViewTheme.vienNhap(txtTen);
        ViewTheme.vienNhap(txtMatKhau);
        form.add(lbTen);
        form.add(txtTen);
        form.add(lbMk);
        form.add(txtMatKhau);
        ViewTheme.nutChinh(btnDangNhap);
        btnDangNhap.addActionListener(e -> dangNhap());
        the.add(tieu, BorderLayout.NORTH);
        the.add(form, BorderLayout.CENTER);
        the.add(box = Box.createVerticalBox(), BorderLayout.SOUTH);
        //the.add(btnDangNhap, BorderLayout.SOUTH);

        box.add(btnDangNhap);
        box.add(Box.createVerticalStrut(10));
        box.add(btnThoat);
       
        ViewTheme.nutPhu(btnThoat);
        btnThoat.addActionListener(e -> thoat());
        //the.add(btnThoat, BorderLayout.SOUTH);

        JPanel giua = new JPanel(new BorderLayout());
        giua.setOpaque(false);
        giua.setBorder(new EmptyBorder(28, 36, 36, 36));
        giua.add(the, BorderLayout.CENTER);

        goc.add(dau, BorderLayout.NORTH);
        goc.add(giua, BorderLayout.CENTER);
        setContentPane(goc);
        getRootPane().setDefaultButton(btnDangNhap);
    }

    private void dangNhap() {
        String ten = txtTen.getText().trim();
        String mk = String.valueOf(txtMatKhau.getPassword());
        if (ten.isEmpty() || mk.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ tên đăng nhập và mật khẩu.", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return;
        }
        dispose();
        new TrangChuView(ten).setVisible(true);
    }

    private void thoat() {
        setVisible(false);
    }
}
