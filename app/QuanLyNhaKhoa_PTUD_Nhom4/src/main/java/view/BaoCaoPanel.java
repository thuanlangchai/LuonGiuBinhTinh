package view;

import entity.ChiTietKeHoachDieuTri;
import entity.HoaDon;
import entity.KeHoachDieuTri;
import entity.LichHen;
import entity.PhieuChi;
import entity.PhieuThu;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import service.impl.BaoCao_Service;
import service.impl.KhachHang_Service;
import view.model.ChiTietToaThuocView;
import view.model.ToaThuocView;

public class BaoCaoPanel extends JPanel {

    private String ma = "tongquan";

    public BaoCaoPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());
        chon("tongquan");
    }

    public void chon(String maMoi) {
        this.ma = maMoi;
        removeAll();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(6, 6, 16, 6));
        JPanel pnlThe = new JPanel(new BorderLayout(0, 8));
        pnlThe.setBackground(ViewTheme.TRANG);
        pnlThe.setBorder(new EmptyBorder(12, 14, 12, 14));
        if ("donthuoc".equals(ma)) {
            pnlThe.add(donThuoc(), BorderLayout.CENTER);
        } else if ("thuchi".equals(ma) || "thuchihangngay".equals(ma)) {
            pnlThe.add(thuChi("thuchihangngay".equals(ma) ? "Báo cáo thu chi hàng ngày" : "Báo cáo thu chi"), BorderLayout.CENTER);
        } else if ("dtdichvu".equals(ma)) {
            pnlThe.add(doanhThuDichVu(), BorderLayout.CENTER);
        } else if ("dtcanhan".equals(ma)) {
            pnlThe.add(doanhThuCaNhan(), BorderLayout.CENTER);
        } else {
            pnlThe.add(mauChung(tieuCua(ma), cotCua(ma), dongCua(ma)), BorderLayout.CENTER);
        }
        add(pnlThe, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    private JPanel donThuoc() {
        JPanel pnl = new JPanel(new BorderLayout(0, 8));
        pnl.setOpaque(false);
        pnl.add(dau("Báo cáo toa thuốc", true), BorderLayout.NORTH);
        long tongTien = 0;
        List<ToaThuocView> danhSach = ToaThuocDuLieu.tatCa();
        Object[][] dong = new Object[Math.max(1, danhSach.size())][];
        int i = 0;
        for (ToaThuocView toaThuoc : danhSach) {
            StringBuilder chiTiet = new StringBuilder();
            for (ChiTietToaThuocView ct : toaThuoc.chiTiet) {
                if (chiTiet.length() > 0) {
                    chiTiet.append("; ");
                }
                chiTiet.append(ct.tongSo).append(" x ").append(ct.tenThuoc)
                        .append(" (").append(ViewTheme.tien(ct.donGia)).append(")");
            }
            tongTien += toaThuoc.thanhTien;
            dong[i] = new Object[]{
                String.valueOf(i + 1), toaThuoc.maToaThuoc, toaThuoc.ngayTao, toaThuoc.tenKh, chiTiet.toString(),
                toaThuoc.hinhThucThanhToan, ViewTheme.tien(toaThuoc.thanhTien), "0",
                ViewTheme.tien(toaThuoc.thanhTien), toaThuoc.tenBacSi
            };
            i++;
        }
        if (danhSach.isEmpty()) {
            dong[0] = new Object[]{"", "", "", "", "", "", "0", "0", "0", ""};
        }
        JPanel pnlTomTat = new JPanel(new GridLayout(1, 3, 12, 0));
        pnlTomTat.setOpaque(false);
        pnlTomTat.add(oSo("Tổng tiền thuốc", ViewTheme.tien(tongTien)));
        pnlTomTat.add(oSo("Tổng chiết khấu", "0"));
        pnlTomTat.add(oSo("Thành tiền", ViewTheme.tien(tongTien)));
        JPanel pnlTren = new JPanel(new BorderLayout(12, 0));
        pnlTren.setOpaque(false);
        pnlTren.add(oNgay(), BorderLayout.WEST);
        pnlTren.add(pnlTomTat, BorderLayout.CENTER);
        String[] cot = {"#", "Mã toa thuốc", "Ngày tạo", "Khách hàng", "Toa thuốc chi tiết", "Hình thức TT", "Tiền thuốc", "Chiết khấu", "Thành tiền", "Bác sĩ"};
        JPanel pnlThan = new JPanel(new BorderLayout(0, 8));
        pnlThan.setOpaque(false);
        pnlThan.add(pnlTren, BorderLayout.NORTH);
        pnlThan.add(bang(cot, dong), BorderLayout.CENTER);
        pnlThan.add(nutIn(true), BorderLayout.SOUTH);
        pnl.add(pnlThan, BorderLayout.CENTER);
        return pnl;
    }

    private JPanel thuChi(String tieuDe) {
        JPanel pnl = new JPanel(new BorderLayout(0, 8));
        pnl.setOpaque(false);
        pnl.add(dau(tieuDe, false), BorderLayout.NORTH);
        BaoCao_Service baoCaoService = new BaoCao_Service();
        long tongThu = 0;
        long tongChi = 0;
        List<Object[]> rows = new ArrayList<>();
        int stt = 1;
        for (PhieuThu phieuThu : baoCaoService.danhSachPhieuThu()) {
            tongThu += phieuThu.getSoTien();
            rows.add(new Object[]{
                String.valueOf(stt++), phieuThu.getMaPhieuThu(), NgayUtil.fmt(phieuThu.getNgayThu()),
                phieuThu.getMaKH(), "", phieuThu.getLyDo(),
                ViewTheme.tien(phieuThu.getSoTien()), "0", "0", "0", ViewTheme.tien(phieuThu.getSoTien()),
                "0", "0", "0", "0", "0"
            });
        }
        for (PhieuChi phieuChi : baoCaoService.danhSachPhieuChi()) {
            tongChi += phieuChi.getSoTien();
            rows.add(new Object[]{
                String.valueOf(stt++), phieuChi.getMaPhieuChi(), NgayUtil.fmt(phieuChi.getNgayChi()),
                "", phieuChi.getMaNhaCungCap(), phieuChi.getLyDoChi(),
                "0", "0", "0", "0", "0",
                ViewTheme.tien(phieuChi.getSoTien()), "0", "0", "0", ViewTheme.tien(phieuChi.getSoTien())
            });
        }
        rows.add(new Object[]{"", "", "", "", "", "Tổng tiền",
            ViewTheme.tien(tongThu), "0", "0", "0", ViewTheme.tien(tongThu),
            ViewTheme.tien(tongChi), "0", "0", "0", ViewTheme.tien(tongChi)});
        JPanel pnlSo = new JPanel(new GridLayout(1, 3, 8, 0));
        pnlSo.setOpaque(false);
        pnlSo.add(oSo("Tổng thu", ViewTheme.tien(tongThu)));
        pnlSo.add(oSo("Tổng chi", ViewTheme.tien(tongChi)));
        pnlSo.add(oSo("Tổng (Thu - chi)", ViewTheme.tien(tongThu - tongChi)));
        JPanel pnlTren = new JPanel(new BorderLayout(12, 0));
        pnlTren.setOpaque(false);
        pnlTren.add(oNgay(), BorderLayout.WEST);
        pnlTren.add(pnlSo, BorderLayout.CENTER);
        String[] cot = {"#", "Mã phiếu", "Ngày", "Khách hàng", "Nhà cung cấp", "Lý do",
            "Thu - Tiền mặt", "Thu - Thẻ", "Thu - Chuyển khoản", "Thu - Trả góp", "Thu - Tổng",
            "Chi - Tiền mặt", "Chi - Thẻ", "Chi - Chuyển khoản", "Chi - Trả góp", "Chi - Tổng"};
        Object[][] dong = rows.toArray(new Object[0][]);
        JPanel pnlThan = new JPanel(new BorderLayout(0, 8));
        pnlThan.setOpaque(false);
        pnlThan.add(pnlTren, BorderLayout.NORTH);
        pnlThan.add(bang(cot, dong), BorderLayout.CENTER);
        pnlThan.add(nutIn(true), BorderLayout.SOUTH);
        pnl.add(pnlThan, BorderLayout.CENTER);
        return pnl;
    }

    private JPanel doanhThuDichVu() {
        return baoCaoTab("Doanh thu dịch vụ",
                new String[]{"Tổng quan", "Loại dịch vụ", "Dịch vụ"},
                new String[]{"#", "Mã phiếu", "Ngày", "Đối tượng", "Điện thoại", "Lý do", "Loại dịch vụ", "Tổng tiền"},
                new Object[][]{{"", "", "", "", "", "", "Tổng tiền", "0"}});
    }

    private JPanel doanhThuCaNhan() {
        return baoCaoTab("Doanh thu cá nhân",
                new String[]{"Doanh thu", "Tổng hợp", "Người làm chính", "Phụ tá", "Tư vấn", "Sale", "Telesale", "Người giới thiệu"},
                new String[]{"#", "Mã chi nhánh", "Mã phiếu", "Ngày", "Đối tượng", "Điện thoại", "Lý do", "Người làm chính", "Phụ tá", "Tư vấn", "Sale", "Người giới thiệu", "Tổng cộng"},
                new Object[][]{{"", "", "", "", "", "", "", "", "", "", "", "Tổng tiền", "0"}});
    }

    private JPanel baoCaoTab(String tieuDe, String[] tab, String[] cot, Object[][] dong) {
        JPanel pnl = new JPanel(new BorderLayout(0, 8));
        pnl.setOpaque(false);
        pnl.add(dau(tieuDe, false), BorderLayout.NORTH);
        JPanel pnlThanh = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        pnlThanh.setOpaque(false);
        JPanel pnlVung = new JPanel(new BorderLayout());
        pnlVung.setOpaque(false);
        pnlVung.add(bang(cot, dong), BorderLayout.CENTER);
        for (int i = 0; i < tab.length; i++) {
            JButton btnTab = new JButton(tab[i]);
            if (i == 0) {
                ViewTheme.nutChinh(btnTab);
            } else {
                ViewTheme.nutXam(btnTab);
            }
            btnTab.addActionListener(e -> {
                for (Component component : pnlThanh.getComponents()) {
                    if (component instanceof JButton) {
                        ViewTheme.nutXam((JButton) component);
                    }
                }
                ViewTheme.nutChinh(btnTab);
            });
            pnlThanh.add(btnTab);
        }
        JPanel pnlTren = new JPanel(new BorderLayout(0, 8));
        pnlTren.setOpaque(false);
        pnlTren.add(oNgay(), BorderLayout.NORTH);
        pnlTren.add(pnlThanh, BorderLayout.SOUTH);
        JPanel pnlThan = new JPanel(new BorderLayout(0, 8));
        pnlThan.setOpaque(false);
        pnlThan.add(pnlTren, BorderLayout.NORTH);
        pnlThan.add(pnlVung, BorderLayout.CENTER);
        pnlThan.add(nutIn(true), BorderLayout.SOUTH);
        pnl.add(pnlThan, BorderLayout.CENTER);
        return pnl;
    }

    private JPanel mauChung(String tieuDe, String[] cot, Object[][] dong) {
        JPanel pnl = new JPanel(new BorderLayout(0, 8));
        pnl.setOpaque(false);
        pnl.add(dau(tieuDe, false), BorderLayout.NORTH);
        JPanel pnlThan = new JPanel(new BorderLayout(0, 8));
        pnlThan.setOpaque(false);
        pnlThan.add(oNgay(), BorderLayout.NORTH);
        pnlThan.add(bang(cot, dong), BorderLayout.CENTER);
        pnlThan.add(nutIn(true), BorderLayout.SOUTH);
        pnl.add(pnlThan, BorderLayout.CENTER);
        return pnl;
    }

    private JPanel dau(String tieuDe, boolean taiLai) {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setOpaque(false);
        JLabel nhanTieuDe = ViewTheme.tieuDeTrang(tieuDe);
        pnl.add(nhanTieuDe, BorderLayout.WEST);
        if (taiLai) {
            JButton btnTai = new JButton("Tải lại");
            ViewTheme.nutXanh(btnTai);
            btnTai.addActionListener(e -> chon(ma));
            JPanel pnlPhai = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
            pnlPhai.setOpaque(false);
            pnlPhai.add(btnTai);
            pnl.add(pnlPhai, BorderLayout.CENTER);
        }
        return pnl;
    }

    private JTextField oNgay() {
        JTextField txtNgay = new JTextField("28/09/2026 - 28/09/2026");
        ViewTheme.vienNhap(txtNgay);
        txtNgay.setPreferredSize(new Dimension(220, 32));
        return txtNgay;
    }

    private JPanel oSo(String nhan, String so) {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setOpaque(false);
        JLabel nhanTieu = ViewTheme.chuPhu(nhan);
        JLabel nhanSo = new JLabel(so);
        nhanSo.setFont(ViewTheme.FONT_SO);
        nhanSo.setForeground(ViewTheme.CHU);
        pnl.add(nhanTieu, BorderLayout.NORTH);
        pnl.add(nhanSo, BorderLayout.CENTER);
        return pnl;
    }

    private JScrollPane bang(String[] cot, Object[][] dong) {
        DefaultTableModel model = ViewTheme.taoModel(cot, false, false);
        for (Object[] hang : dong) {
            model.addRow(hang);
        }
        JTable table = new JTable(model);
        ViewTheme.trangTriBang(table);
        JScrollPane scrollBang = new JScrollPane(table);
        scrollBang.setPreferredSize(new Dimension(400, 180));
        scrollBang.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        scrollBang.getViewport().setBackground(ViewTheme.TRANG);
        return scrollBang;
    }

    private JPanel nutIn(boolean excel) {
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        pnl.setOpaque(false);
        JButton btnIn = new JButton("In");
        ViewTheme.nutXanh(btnIn);
        btnIn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Đã chuẩn bị bản in " + tieuCua(ma) + "."));
        pnl.add(btnIn);
        if (excel) {
            JButton btnXuat = new JButton("Xuất excel...");
            ViewTheme.nutXanh(btnXuat);
            btnXuat.addActionListener(e -> JOptionPane.showMessageDialog(this, "Đã chuẩn bị file Excel cho " + tieuCua(ma) + "."));
            pnl.add(btnXuat);
        }
        return pnl;
    }

    private String tieuCua(String maBaoCao) {
        if ("congno".equals(maBaoCao)) {
            return "Thống kê doanh thu";
        }
        if ("donthuoc".equals(maBaoCao)) {
            return "Báo cáo toa thuốc";
        }
        if ("thuchi".equals(maBaoCao)) {
            return "Báo cáo thu chi";
        }
        if ("thuchihangngay".equals(maBaoCao)) {
            return "Báo cáo thu chi hàng ngày";
        }
        return "Báo cáo";
    }

    private String[] cotCua(String maBaoCao) {
        if ("lichhen".equals(maBaoCao)) {
            return new String[]{"#", "Khách hàng", "Công việc", "Bác sĩ", "Bắt đầu", "Trạng thái"};
        }
        if ("dieutri".equals(maBaoCao)) {
            return new String[]{"#", "Khách hàng", "Dịch vụ", "Ngày", "Bác sĩ", "Thành tiền"};
        }
        if ("congno".equals(maBaoCao)) {
            return new String[]{"#", "Khách hàng", "Phát sinh", "Đã thu", "Còn nợ"};
        }
        if ("chiphi".equals(maBaoCao)) {
            return new String[]{"#", "Ngày", "Loại chi", "Lý do", "Số tiền"};
        }
        if ("nhatky".equals(maBaoCao)) {
            return new String[]{"#", "Thời gian", "Người dùng", "Hành động", "Đối tượng"};
        }
        if ("sale".equals(maBaoCao)) {
            return new String[]{"#", "Nhân viên", "Số ca", "Doanh số"};
        }
        if ("nguon".equals(maBaoCao)) {
            return new String[]{"#", "Nguồn", "Số khách", "Doanh thu"};
        }
        if ("baocaolabo".equals(maBaoCao)) {
            return new String[]{"#", "Mã", "Nhà cung cấp", "Khách hàng", "Chi tiết", "Tình trạng", "Ngày gửi"};
        }
        return new String[]{"#", "Nội dung", "Số lượng", "Giá trị"};
    }

    private Object[][] dongCua(String maBaoCao) {
        BaoCao_Service baoCaoService = new BaoCao_Service();
        if ("lichhen".equals(maBaoCao)) {
            List<Object[]> rows = new ArrayList<>();
            int i = 1;
            for (LichHen lichHen : baoCaoService.danhSachLichHen()) {
                rows.add(new Object[]{
                    String.valueOf(i++), lichHen.getMaKH(), "", lichHen.getMaBacSi(),
                    NgayUtil.fmtFull(lichHen.getBatDau()), lichHen.getTrangThai()
                });
            }
            return rows.isEmpty() ? new Object[][]{{"", "", "", "", "", ""}} : rows.toArray(new Object[0][]);
        }
        if ("dieutri".equals(maBaoCao)) {
            List<Object[]> rows = new ArrayList<>();
            int i = 1;
            for (KeHoachDieuTri keHoach : baoCaoService.danhSachKeHoach()) {
                for (ChiTietKeHoachDieuTri chiTiet : baoCaoService.chiTietTheoKeHoach(keHoach.getMaKeHoach())) {
                    rows.add(new Object[]{
                        String.valueOf(i++), keHoach.getMaKH(), chiTiet.getMaDichVu(),
                        NgayUtil.fmt(chiTiet.getNgay()), keHoach.getMaBacSi(), ViewTheme.tien(chiTiet.getThanhTien())
                    });
                }
            }
            return rows.isEmpty() ? new Object[][]{{"", "", "", "", "", "0"}} : rows.toArray(new Object[0][]);
        }
        if ("congno".equals(maBaoCao)) {
            return HoaDonDuLieu.dongCongNo();
        }
        if ("chiphi".equals(maBaoCao)) {
            long tong = 0;
            for (PhieuChi phieuChi : baoCaoService.danhSachPhieuChi()) {
                tong += phieuChi.getSoTien();
            }
            return new Object[][]{{"", "", "", "Tổng tiền", ViewTheme.tien(tong)}};
        }
        if ("nhatky".equals(maBaoCao)) {
            List<PhieuThu> danhSachPhieuThu = baoCaoService.danhSachPhieuThu();
            if (danhSachPhieuThu.isEmpty()) {
                return new Object[][]{{"", "", "", "", ""}};
            }
            PhieuThu phieuThu = danhSachPhieuThu.get(0);
            return new Object[][]{{"1", NgayUtil.fmtFull(phieuThu.getNgayTao()), phieuThu.getNguoiTao(), "Tạo phiếu thu", phieuThu.getMaPhieuThu()}};
        }
        if ("sale".equals(maBaoCao) || "nguon".equals(maBaoCao) || "baocaolabo".equals(maBaoCao)) {
            return new Object[][]{{"", "", "", "0"}};
        }
        if ("tongquan".equals(maBaoCao)) {
            long doanhThu = 0;
            for (HoaDon hoaDon : baoCaoService.danhSachHoaDon()) {
                doanhThu += hoaDon.getThanhTien();
            }
            return new Object[][]{
                {"1", "Lịch hẹn", String.valueOf(baoCaoService.danhSachLichHen().size()), ""},
                {"2", "Khách hàng", String.valueOf(new KhachHang_Service().findAllKhachHang().size()), ""},
                {"3", "Doanh thu", "", ViewTheme.tien(doanhThu)}
            };
        }
        return new Object[][]{{"", "", "Tổng", "0"}};
    }
}
