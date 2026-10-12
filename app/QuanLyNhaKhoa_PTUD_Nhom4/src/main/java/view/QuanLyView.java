package view;

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
    private final BaoCaoPanel baoCao = new BaoCaoPanel();
    private final DanhMucPanel danhMuc;
    private final NhapKhoPanel nhapKho = new NhapKhoPanel("Thêm Nhập kho", "Thêm Nhập kho");
    private final NhapKhoPanel xuatKho = new NhapKhoPanel("Thêm Xuất kho", "Thêm Xuất kho");
    private final ImplantPanel implant;
    private final TrangDanhSach danhSach = new TrangDanhSach();
    private final ONhap timKhach = new ONhap("Tìm khách hàng", 16);

    public QuanLyView(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap == null || tenDangNhap.isEmpty() ? "thuan080" : tenDangNhap;
        this.khachHang = new KhachHangPanel(keHoach, () -> mo("kehoach"));
        this.danhMuc = new DanhMucPanel(this);
        this.implant = new ImplantPanel(this);
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
        nav.add(nutTrucTiep("Lịch hẹn", "lichhen"));
        nav.add(nutTrucTiep("Khách hàng", "khachhang"));
        nav.add(nutMenu("Tiện ích", new String[][]{
            {"Toa thuốc", "toathuoc"},
            {"Kế hoạch điều trị", "kehoach"},
            {"Phiếu implant", "implant"},
            {"Nhập kho", "nhapkho"},
            {"Kiểm kho", "kiemkho"}
        }));
        nav.add(nutMenu("CSKH", new String[][]{
            {"Chăm sóc khách hàng", "cskh"},
            {"Lịch chăm sóc", "lichchamsoc"},
            {"Nhắc lịch", "nhaclich"}
        }));
        nav.add(nutMenu("Thu chi", new String[][]{
            {"Phiếu thu", "phieuthu"},
            {"Phiếu chi", "phieuchi"},
            {"Báo cáo thu chi", "bc:thuchi"}
        }));
        nav.add(nutMenu("Báo cáo", new String[][]{
            {"Tổng quan", "bc:tongquan"},
            {"Báo cáo lịch hẹn", "bc:lichhen"},
            {"Báo cáo điều trị", "bc:dieutri"},
            {"Báo cáo chưa khai thác", "bc:chuakhoi"},
            {"Phiếu labo", "phieulabo"},
            {"Báo cáo labo", "bc:baocaolabo"},
            {"Phiếu implant", "implant"},
            {"Báo cáo implant", "bc:implant"},
            {"Kê đơn thuốc", "toathuoc"},
            {"Báo cáo đơn thuốc", "bc:donthuoc"},
            {"Doanh số chốt sale", "bc:sale"},
            {"Doanh thu cá nhân", "bc:dtcanhan"},
            {"Doanh thu nhân viên", "bc:dtnhanvien"},
            {"Doanh thu dịch vụ", "bc:dtdichvu"},
            {"Doanh thu nguồn", "bc:nguon"},
            {"Báo cáo công nợ", "bc:congno"},
            {"Nhật ký", "bc:nhatky"},
            {"Báo cáo chi phí", "bc:chiphi"},
            {"Báo cáo thực thu", "bc:thucthu"},
            {"Báo cáo thu chi", "bc:thuchi"}
        }));
        nav.add(nutTrucTiep("Danh mục", "danhmuc"));
        JButton chuong = new JButton("●");
        ViewTheme.nutTrong(chuong);
        chuong.setForeground(new Color(255, 90, 90));
        chuong.addActionListener(e -> JOptionPane.showMessageDialog(this, "Không có thông báo mới."));
        nav.add(chuong);
        JButton user = new JButton(tenDangNhap);
        ViewTheme.nutTrong(user);
        JPopupMenu pop = new JPopupMenu();
        JMenuItem tk = new JMenuItem("Tài khoản");
        JMenuItem thoat = new JMenuItem("Đăng xuất");
        tk.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Tài khoản: " + tenDangNhap + "\nVai trò: Bác sĩ\nChi nhánh: Quang Thanh"));
        thoat.addActionListener(e -> dangXuat());
        pop.add(tk);
        pop.add(thoat);
        user.addActionListener(e -> pop.show(user, 0, user.getHeight()));
        nav.add(user);

        dau.add(trai, BorderLayout.WEST);
        dau.add(nav, BorderLayout.CENTER);
        return dau;
    }

    private void timVaMo() {
        khachHang.tim(timKhach.getText().trim());
        mo("khachhang");
    }

    private JButton nutTrucTiep(String chu, String ma) {
        JButton b = new JButton(chu);
        ViewTheme.nutTrong(b);
        b.addActionListener(e -> mo(ma));
        return b;
    }

    private JButton nutMenu(String chu, String[][] muc) {
        JButton b = new JButton(chu);
        ViewTheme.nutTrong(b);
        JPopupMenu pop = new JPopupMenu();
        for (String[] m : muc) {
            JMenuItem item = new JMenuItem(m[0]);
            item.addActionListener(e -> mo(m[1]));
            pop.add(item);
        }
        b.addActionListener(e -> pop.show(b, 0, b.getHeight()));
        return b;
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
        } else if ("implant".equals(ma)) {
            hien(implant);
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
