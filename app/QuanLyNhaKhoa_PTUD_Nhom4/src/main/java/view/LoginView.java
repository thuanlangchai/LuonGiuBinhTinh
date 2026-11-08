package view;

import entity.TaiKhoan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import service.impl.TaiKhoan_Service;

public class LoginView extends JFrame {

    private final JTextField txtTen = new JTextField(22);
    private final JPasswordField txtMatKhau = new JPasswordField(22);
    private final JButton btnDangNhap = new JButton("Đăng nhập");
    private final JButton btnThoat = new JButton("Thoát");
    private final TaiKhoan_Service taiKhoanService = new TaiKhoan_Service();

    public LoginView() {
        setTitle("Đăng nhập - Quản lý nha khoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 580); 
        setMinimumSize(new Dimension(420, 450));
        setLocationRelativeTo(null);

        JPanel panelGoc = new JPanel(new BorderLayout());
        panelGoc.setBackground(ViewTheme.NEN_TIM);

        JPanel panelDau = new JPanel(new GridLayout(2, 1));
        panelDau.setBackground(ViewTheme.XANH);
        panelDau.setBorder(new EmptyBorder(28, 16, 28, 16));
        JLabel nhanLogo = new JLabel("MAYDENTAL", JLabel.CENTER);
        nhanLogo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        nhanLogo.setForeground(Color.WHITE);
        JLabel nhanPhu = new JLabel("Hệ thống quản lý nha khoa", JLabel.CENTER);
        nhanPhu.setFont(ViewTheme.FONT);
        nhanPhu.setForeground(new Color(224, 242, 254));
        panelDau.add(nhanLogo);
        panelDau.add(nhanPhu);

        JPanel panelThe = new JPanel(new BorderLayout(0, 16));
        panelThe.setBackground(Color.WHITE);
        panelThe.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ViewTheme.VIEN),
                new EmptyBorder(24, 28, 24, 28)));
        
        JLabel nhanTieuDe = ViewTheme.tieuDeTrang("Đăng nhập");
        
        JPanel panelForm = new JPanel(new GridLayout(5, 1, 0, 4)); // Tăng lên 5 dòng để chứa dòng chữ quên mật khẩu
        panelForm.setOpaque(false);
        
        JLabel nhanTen = ViewTheme.chuPhu("Tên đăng nhập");
        JLabel nhanMatKhau = ViewTheme.chuPhu("Mật khẩu");
        ViewTheme.vienNhap(txtTen);
        ViewTheme.vienNhap(txtMatKhau);
        
        // Tạo JLabel dạng đường link "Quên mật khẩu?"
        JLabel lblQuenMatKhau = new JLabel("Quên mật khẩu?");
        lblQuenMatKhau.setForeground(new Color(14, 132, 184)); // Màu xanh dương nhạt
        lblQuenMatKhau.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblQuenMatKhau.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xuLyQuenMatKhau();
            }
        });
        
        JPanel panelQuenMK = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelQuenMK.setOpaque(false);
        panelQuenMK.add(lblQuenMatKhau);

        panelForm.add(nhanTen);
        panelForm.add(txtTen);
        panelForm.add(nhanMatKhau);
        panelForm.add(txtMatKhau);
        panelForm.add(panelQuenMK); 

        ViewTheme.nutChinh(btnDangNhap);
        ViewTheme.nutMau(btnThoat, new Color(232, 109, 109), Color.WHITE);
        Dimension kichThuocNut = new Dimension(160, 42);
        btnDangNhap.setPreferredSize(kichThuocNut);
        btnThoat.setPreferredSize(kichThuocNut);
        btnDangNhap.setMinimumSize(kichThuocNut);
        btnThoat.setMinimumSize(kichThuocNut);
        btnDangNhap.addActionListener(e -> dangNhap());
        btnThoat.addActionListener(e -> thoat());

        JPanel panelNut = new JPanel(new GridLayout(1, 2, 16, 0));
        panelNut.setOpaque(false);
        panelNut.setBorder(new EmptyBorder(14, 0, 0, 0));
        panelNut.add(btnDangNhap);
        panelNut.add(btnThoat);

        panelThe.add(nhanTieuDe, BorderLayout.NORTH);
        panelThe.add(panelForm, BorderLayout.CENTER);
        panelThe.add(panelNut, BorderLayout.SOUTH);

        JPanel panelGiua = new JPanel(new BorderLayout());
        panelGiua.setOpaque(false);
        panelGiua.setBorder(new EmptyBorder(28, 36, 36, 36));
        panelGiua.add(panelThe, BorderLayout.CENTER);

        panelGoc.add(panelDau, BorderLayout.NORTH);
        panelGoc.add(panelGiua, BorderLayout.CENTER);
        setContentPane(panelGoc);
        getRootPane().setDefaultButton(btnDangNhap);
    }

    private void dangNhap() {
        String tenDangNhap = txtTen.getText().trim();
        String matKhau = String.valueOf(txtMatKhau.getPassword());
        if (tenDangNhap.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ tên đăng nhập và mật khẩu.", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return;
        }
        btnDangNhap.setEnabled(false);
        new SwingWorker<TaiKhoan, Void>() {
            @Override
            protected TaiKhoan doInBackground() {
                return taiKhoanService.dangNhap(tenDangNhap, matKhau);
            }

            @Override
            protected void done() {
                btnDangNhap.setEnabled(true);
                try {
                    TaiKhoan taiKhoan = get();
                    if (taiKhoan == null) {
                        JOptionPane.showMessageDialog(LoginView.this,
                                "Sai tên đăng nhập hoặc mật khẩu, hoặc tài khoản bị khóa.",
                                "Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    dispose();
                    new TrangChuView(taiKhoan.getTenDangNhap()).setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginView.this,
                            "Không kết nối được cơ sở dữ liệu.\n" + ex.getMessage(),
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    
    private void xuLyQuenMatKhau() {
        
        String tenDangNhap = JOptionPane.showInputDialog(this, 
                "Vui lòng nhập Tên đăng nhập (Username) của bạn:", 
                "Khôi phục mật khẩu", 
                JOptionPane.QUESTION_MESSAGE);

        if (tenDangNhap == null) {
            return; 
        }

        tenDangNhap = tenDangNhap.trim();
        if (tenDangNhap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        try {
           
            TaiKhoan tk = taiKhoanService.findById(tenDangNhap); 
            
            if (tk == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản với tên đăng nhập này!", "Thất bại", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            String matKhauMoi = JOptionPane.showInputDialog(this, 
                    "Nhập mật khẩu mới cho tài khoản (" + tenDangNhap + "):", 
                    "Đặt lại mật khẩu", 
                    JOptionPane.QUESTION_MESSAGE);

            if (matKhauMoi == null) {
                return;
            }

            matKhauMoi = matKhauMoi.trim();
            if (matKhauMoi.length() < 6) {
                JOptionPane.showMessageDialog(this, "Mật khẩu mới phải có ít nhất 6 ký tự!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            
            TaiKhoan newTK = new TaiKhoan();
            newTK.setTenDangNhap(tenDangNhap);
            newTK.setMatKhau(matKhauMoi);
            newTK.setEmail(tk.getEmail());
            newTK.setNhanVien(tk.getNhanVien());
            newTK.setKichHoat(tk.isKichHoat());
            newTK.setNgayTao(tk.getNgayTao());
            try {
            	taiKhoanService.update(newTK);
            	JOptionPane.showMessageDialog(this, 
                        "Đổi mật khẩu thành công! Vui lòng đăng nhập lại bằng mật khẩu mới.", 
                        "Thành công", 
                        JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(this, 
                        "Không thể cập nhật mật khẩu. Vui lòng thử lại sau.", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                    "Đã xảy ra lỗi khi kết nối cơ sở dữ liệu:\n" + ex.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void thoat() {
        dispose();
        usingSystemExit();
    }
    
    private void usingSystemExit() {
        System.exit(0);
    }
}