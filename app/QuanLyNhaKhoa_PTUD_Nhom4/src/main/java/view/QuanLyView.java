package view;

import view.model.CauHinhTrang;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class QuanLyView extends JFrame implements DieuHuong {

    private final String tenDangNhap;
    private final VungTrang vung = new VungTrang();
    private final JScrollPane cuon;
    private final LichHenPanel lichHen = new LichHenPanel();
    private final KeHoachDieuTriPanel keHoach = new KeHoachDieuTriPanel();
    private final KhachHangPanel khachHang;
    private final ToaThuocPanel toaThuoc = new ToaThuocPanel();
    private final PhieuTienPanel phieuThu = new PhieuTienPanel(true);
    private final PhieuTienPanel phieuChi = new PhieuTienPanel(false);
    private final HoaDonPanel hoaDon;
    private final BaoCaoPanel baoCao = new BaoCaoPanel();
    private final DanhMucPanel danhMuc;
    private final NhapKhoPanel nhapKho = new NhapKhoPanel("Thêm Nhập kho", "Thêm Nhập kho");
    private final NhapKhoPanel xuatKho = new NhapKhoPanel("Thêm Xuất kho", "Thêm Xuất kho");
    private final TrangDanhSach danhSach = new TrangDanhSach();
    private final ONhap timKhach = new ONhap("Tìm khách hàng", 16);

    public QuanLyView(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap == null || tenDangNhap.isEmpty() ? "thuan080" : tenDangNhap;
        this.khachHang = new KhachHangPanel(keHoach, () -> mo("kehoach"));
        this.hoaDon = new HoaDonPanel(keHoach, phieuThu);
        this.keHoach.ganHoaDon(hoaDon);
        this.hoaDon.ganMoPhieuThu(() -> mo("phieuthu"));
        this.danhMuc = new DanhMucPanel(this);
        setTitle("Quản lý nha khoa - Nhóm 4");
        setSize(1360, 820);
        setMinimumSize(new Dimension(1100, 680));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ViewTheme.NEN);
        cuon = new JScrollPane(vung);
        cuon.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        cuon.getViewport().setBackground(ViewTheme.NEN);
        cuon.getVerticalScrollBar().setUnitIncrement(18);
        JPanel goc = new JPanel(new BorderLayout());
        goc.setBackground(ViewTheme.NEN);
        goc.add(taoDau(), BorderLayout.NORTH);
        goc.add(cuon, BorderLayout.CENTER);
        JLabel banQuyen = new JLabel("Luon Giu Binh Tinh. All rights reserved. Hotline: 0933.942.776", JLabel.CENTER);
        banQuyen.setForeground(Color.WHITE);
        banQuyen.setFont(ViewTheme.FONT_NHO);
        banQuyen.setBorder(new javax.swing.border.EmptyBorder(6, 0, 6, 0));
        goc.add(banQuyen, BorderLayout.SOUTH);
        setContentPane(goc);
        mo("lichhen");
    }

    private JPanel taoDau() {
        JPanel dau = new JPanel(new BorderLayout(12, 0));
        dau.setBackground(ViewTheme.XANH);
        dau.setBorder(new javax.swing.border.EmptyBorder(8, 12, 8, 12));
        JLabel logo = new JLabel("LGBTDENTAL");
        logo.setForeground(Color.WHITE);
        logo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        timKhach.setPreferredSize(new Dimension(200, 32));
        JButton nutTim = new JButton("Tìm");
        ViewTheme.nutXam(nutTim);
        nutTim.addActionListener(e -> timVaMo());
        timKhach.addActionListener(e -> timVaMo());
        JPanel trai = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        trai.setOpaque(false);
        trai.add(logo);
        trai.add(timKhach);
        trai.add(nutTim);

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 0));
        nav.setOpaque(false);
        nav.add(nutMenu("Hệ thống", new String[][]{
            {"Tài khoản", "sys:taikhoan"},
            {"Đăng xuất", "sys:dangxuat"}
        }));
        nav.add(nutMenu("Danh mục", new String[][]{
            {"Bảng danh mục", "danhmuc"},
            {"Dịch vụ", "dichvu"},
            {"Nhóm dịch vụ", "nhomdichvu"},
            {"Khách hàng", "khachhang"},
            {"Nhân viên", "nhanvien"},
            {"Lịch hẹn", "lichhen"},
            {"Thuốc", "thuoc"},
            {"Răng", "rang"},
            {"Sản phẩm", "sanpham"},
            {"Nhà cung cấp", "nhacungcap"}
        }));
        nav.add(nutMenu("Thống kê", new String[][]{
            {"Thống kê cá nhân", "bc:dtcanhan"},
            {"Thống kê doanh thu", "bc:congno"},}));
        nav.add(nutMenu("Tìm kiếm", new String[][]{
            {"Khách hàng", "khachhang"},
            {"Lịch hẹn", "lichhen"},
            {"Hóa đơn", "hoadon"},
            {"Phiếu thu", "phieuthu"},
            {"Dịch vụ", "dichvu"},
            {"Nhân viên", "nhanvien"}
        }));
        nav.add(nutMenu("Xử lý", new String[][]{
            {"Kế hoạch điều trị", "kehoach"},
            {"Toa thuốc", "toathuoc"},
            {"Kiểm kho", "kiemkho"},
            {"Nhập kho", "nhapkho"},
            {"Xuất kho", "xuatkho"},
            {"Hóa đơn", "hoadon"},
            {"Phiếu thu", "phieuthu"},
            {"Phiếu chi", "phieuchi"}}));
        nav.add(nutMenu("Báo cáo", new String[][]{
            {"Báo cáo đơn thuốc", "bc:donthuoc"},
            //{"Thống kê doanh thu", "bc:nguon"},
            //{"Nhật ký", "bc:nhatky"},
            //{"Báo cáo chi phí", "bc:chiphi"},
            //{"Báo cáo thực thu", "bc:thucthu"},
            {"Báo cáo thu chi", "bc:thuchi"}
        }));
        JLabel user = new JLabel(tenDangNhap);
        user.setForeground(Color.WHITE);
        user.setFont(ViewTheme.FONT_DAM);
        user.setBorder(new javax.swing.border.EmptyBorder(8, 10, 8, 4));
        nav.add(user);

        dau.add(trai, BorderLayout.WEST);
        dau.add(nav, BorderLayout.CENTER);
        return dau;
    }

    private void timVaMo() {
        khachHang.tim(timKhach.getText().trim());
        mo("khachhang");
    }

    private JButton nutMenu(String chu, String[][] muc) {
        JButton b = new JButton(chu);
        ViewTheme.nutTrong(b);
        JPopupMenu pop = new JPopupMenu();
        for (String[] m : muc) {
            JMenuItem item = new JMenuItem(m[0]);
            item.addActionListener(e -> chonMuc(m[1]));
            pop.add(item);
        }
        b.addActionListener(e -> pop.show(b, 0, b.getHeight()));
        return b;
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
        mo(ma);
    }

    @Override
    public void mo(String ma) {
        if ("lichhen".equals(ma)) {
            hien(lichHen);
        } else if ("khachhang".equals(ma)) {
            hien(khachHang);
        } else if ("kehoach".equals(ma)) {
            hien(keHoach);
        } else if ("toathuoc".equals(ma)) {
            hien(toaThuoc);
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
            CauHinhTrang c = DuLieuMau.lay(ma);
            if (c != null) {
                danhSach.nap(c);
                hien(danhSach);
            }
        }
        cuon.getVerticalScrollBar().setValue(0);
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
