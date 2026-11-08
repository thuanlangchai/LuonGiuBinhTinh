package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import entity.TaiKhoan;
import service.impl.TaiKhoan_Service;
import view.model.CauHinhTrang;

public class QuanLyView extends JFrame implements DieuHuong {

    private final String tenDangNhap;
    private final VungTrang vung = new VungTrang();
    private final JScrollPane srcCuon;
    private final LichHenPanel lichHen = new LichHenPanel();
    private final KeHoachDieuTriPanel keHoach = new KeHoachDieuTriPanel();
    private final DsKeHoachPanel dsKeHoach;
    private final KhachHangPanel khachHang;
    private final ToaThuocPanel lapToaThuoc;
    private final DsToaThuocPanel toaThuoc;
    private final PhieuTienPanel phieuThu = new PhieuTienPanel(true);
    private final PhieuTienPanel phieuChi = new PhieuTienPanel(false);
    private final HoaDonPanel hoaDon;
    private final BaoCaoPanel baoCao = new BaoCaoPanel();
    private final DanhMucPanel danhMuc;
    private final NhapKhoPanel nhapKho = new NhapKhoPanel("Thêm Nhập kho", "Thêm Nhập kho");
    private final NhapKhoPanel xuatKho = new NhapKhoPanel("Thêm Xuất kho", "Thêm Xuất kho");
    private final TrangDanhSach danhSach = new TrangDanhSach();
    private final ONhap txtTimKhach = new ONhap("Tìm khách hàng", 16);
    
    private final TaiKhoan_Service taiKhoanService = new TaiKhoan_Service();

    public QuanLyView(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap == null || tenDangNhap.isEmpty() ? "thuan080" : tenDangNhap;
        this.hoaDon = new HoaDonPanel(keHoach, phieuThu);
        this.keHoach.ganHoaDon(hoaDon);
        this.hoaDon.ganMoPhieuThu(() -> mo("phieuthu"));
        this.lapToaThuoc = new ToaThuocPanel(() -> mo("toathuoc"));
        this.toaThuoc = new DsToaThuocPanel(() -> mo("toathuoc-lap"));
        this.dsKeHoach = new DsKeHoachPanel(maKeHoach -> {
            keHoach.napMaKeHoach(maKeHoach);
            mo("kehoach-ct");
        });
        this.khachHang = new KhachHangPanel((ma, ten, sdt, email, diaChi, lyDo) -> {
            dsKeHoach.locTheoKhach(ma, ten, sdt, email, diaChi, lyDo);
            mo("kehoach");
        }, maKH -> {
            toaThuoc.locTheoKhach(maKH);
            lapToaThuoc.chonKhach(maKH);
            mo("toathuoc");
        });
        this.danhMuc = new DanhMucPanel(this);
        setTitle("Quản lý nha khoa - Nhóm 4");
        setSize(1360, 820);
        setMinimumSize(new Dimension(1100, 680));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ViewTheme.NEN);
        srcCuon = new JScrollPane(vung);
        srcCuon.setBorder(BorderFactory.createEmptyBorder());
        srcCuon.getViewport().setBackground(ViewTheme.NEN);
        srcCuon.getVerticalScrollBar().setUnitIncrement(18);
        JPanel pnlGoc = new JPanel(new BorderLayout());
        pnlGoc.setBackground(ViewTheme.NEN);
        pnlGoc.add(pnlTaoDau(), BorderLayout.NORTH);
        pnlGoc.add(srcCuon, BorderLayout.CENTER);
        JLabel lblBanQuyen = new JLabel("Luon Giu Binh Tinh. All rights reserved. Hotline: 0933.942.776", JLabel.CENTER);
        lblBanQuyen.setForeground(Color.WHITE);
        lblBanQuyen.setFont(ViewTheme.FONT_NHO);
        lblBanQuyen.setBorder(new EmptyBorder(6, 0, 6, 0));
        pnlGoc.add(lblBanQuyen, BorderLayout.SOUTH);
        setContentPane(pnlGoc);
        mo("lichhen");
    }

    private JPanel pnlTaoDau() {
        JPanel pnlDau = new JPanel(new BorderLayout(12, 0));
        pnlDau.setBackground(ViewTheme.XANH);
        pnlDau.setBorder(new EmptyBorder(8, 12, 8, 12));
        JLabel lblLogo = new JLabel("LGBTDENTAL");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtTimKhach.setPreferredSize(new Dimension(200, 32));
        JButton btnNutTim = new JButton("Tìm");
        ViewTheme.nutXam(btnNutTim);
        btnNutTim.addActionListener(e -> timVaMo());
        txtTimKhach.addActionListener(e -> timVaMo());
        JPanel pnlTrai = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pnlTrai.setOpaque(false);
        pnlTrai.add(lblLogo);
        pnlTrai.add(txtTimKhach);
        pnlTrai.add(btnNutTim);

        JPanel pnlNav = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 0));
        pnlNav.setOpaque(false);
        pnlNav.add(btnNutMenu("Hệ thống", new String[][]{
            {"Tài khoản", "sys:taikhoan"},
            {"Đăng xuất", "sys:dangxuat"},
            {"Đổi Mật Khẩu", "sys:doiMK"}
        }));
        pnlNav.add(btnNutMenu("Danh mục", new String[][]{
            {"Bảng danh mục", "danhmuc"},
            {"Dịch vụ", "dichvu"},
            {"Nhóm dịch vụ", "nhomdichvu"},
            {"Khách hàng", "khachhang"},
            {"Nhân viên", "nhanvien"},
            {"Lịch hẹn", "lichhen"},
            {"Toa thuốc", "toathuoc"},
            {"Thuốc", "thuoc"},
            {"Răng", "rang"},
            {"Sản phẩm", "sanpham"},
            {"Nhà cung cấp", "nhacungcap"}
        }));
        pnlNav.add(btnNutMenu("Thống kê", new String[][]{
            {"Thống kê cá nhân", "bc:dtcanhan"},
            {"Thống kê doanh thu", "bc:congno"},}));
        pnlNav.add(btnNutMenu("Tìm kiếm", new String[][]{
            {"Khách hàng", "khachhang"},
            {"Lịch hẹn", "lichhen"},
            {"Hóa đơn", "hoadon"},
            {"Toa thuốc", "toathuoc"},
            {"Phiếu thu", "phieuthu"},
            {"Dịch vụ", "dichvu"},
            {"Nhân viên", "nhanvien"}
        }));
        pnlNav.add(btnNutMenu("Xử lý", new String[][]{
            {"Kế hoạch điều trị", "kehoach"},
            {"Toa thuốc", "toathuoc"},
            {"Nhập kho", "nhapkho"},
            {"Xuất kho", "xuatkho"},
            {"Hóa đơn", "hoadon"},
            {"Phiếu thu", "phieuthu"},
            {"Phiếu chi", "phieuchi"}}));
        pnlNav.add(btnNutMenu("Báo cáo", new String[][]{
            {"Báo cáo đơn thuốc", "bc:donthuoc"},
            {"Báo cáo thu chi", "bc:thuchi"}
        }));
        JLabel lblUser = new JLabel(tenDangNhap);
        lblUser.setForeground(Color.WHITE);
        lblUser.setFont(ViewTheme.FONT_DAM);
        lblUser.setBorder(new EmptyBorder(8, 10, 8, 4));
        pnlNav.add(lblUser);

        pnlDau.add(pnlTrai, BorderLayout.WEST);
        pnlDau.add(pnlNav, BorderLayout.CENTER);
        return pnlDau;
    }

    private void timVaMo() {
        khachHang.tim(txtTimKhach.getText().trim());
        mo("khachhang");
    }

    private JButton btnNutMenu(String chu, String[][] muc) {
        JButton btnB = new JButton(chu);
        ViewTheme.nutTrong(btnB);
        JPopupMenu pmnPop = new JPopupMenu();
        for (String[] m : muc) {
            JMenuItem nmiItem = new JMenuItem(m[0]);
            nmiItem.addActionListener(e -> chonMuc(m[1]));
            pmnPop.add(nmiItem);
        }
        btnB.addActionListener(e -> pmnPop.show(btnB, 0, btnB.getHeight()));
        return btnB;
    }

    private void chonMuc(String ma) {
        if ("sys:taikhoan".equals(ma)) {
            JOptionPane.showMessageDialog(this,
                    "Tài khoản: " + tenDangNhap + "\nVai trò: Bác sĩ\nChi nhánh: Quang Thanh");
            return;
        }
        if ("sys:dangxuat".equals(ma)) {
            dangXuat();
            return;
        }
        if("sys:doiMK".equals(ma)) {
        	doiMK(tenDangNhap);
        }
        mo(ma);
    }
    
    private void doiMK(String tenDangNhap) {
    	try {
            
            TaiKhoan tk = taiKhoanService.findById(tenDangNhap); 

            
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

            
            TaiKhoan newTK = new TaiKhoan(tenDangNhap, matKhauMoi,tk.getEmail(),tk.getVaiTro(),tk.getMaNhanVien(),tk.isKichHoat(),tk.getNgayTao() );
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

    @Override
    public void mo(String ma) {
        if ("lichhen".equals(ma)) {
            hien(lichHen);
        } else if ("khachhang".equals(ma)) {
            hien(khachHang);
        } else if ("kehoach".equals(ma)) {
            hien(dsKeHoach);
        } else if ("kehoach-ct".equals(ma)) {
            hien(keHoach);
        } else if ("toathuoc".equals(ma)) {
            toaThuoc.lamMoi();
            hien(toaThuoc);
        } else if ("toathuoc-lap".equals(ma)) {
            String maKH = toaThuoc.maKhachDangLoc();
            if (maKH != null) {
                lapToaThuoc.chonKhach(maKH);
            }
            hien(lapToaThuoc);
        } else if ("hoadon".equals(ma)) {
            hoaDon.lamMoi();
            hien(hoaDon);
        } else if ("phieuthu".equals(ma)) {
            hien(phieuThu);
        } else if ("phieuchi".equals(ma)) {
            hien(phieuChi);
        } else if ("danhmuc".equals(ma)) {
            hien(danhMuc);
        } else if ("nhapkho".equals(ma)) {
            hien(nhapKho);
        } else if ("xuatkho".equals(ma)) {
            hien(xuatKho);
        } else if (ma.startsWith("bc:")) {
            baoCao.chon(ma.substring(3));
            hien(baoCao);
        } else {
            CauHinhTrang c = DanhMucDb.lay(ma);
            if (c != null) {
                danhSach.nap(c);
                hien(danhSach);
            }
        }
        srcCuon.getVerticalScrollBar().setValue(0);
    }

    private void hien(JPanel trang) {
        vung.removeAll();
        vung.add(trang, BorderLayout.CENTER);
        vung.revalidate();
        vung.repaint();
    }

    private void dangXuat() {
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            dispose();
            SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
        }
    }
}
