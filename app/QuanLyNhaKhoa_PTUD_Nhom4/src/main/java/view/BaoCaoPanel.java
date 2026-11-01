package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
        JPanel the = new JPanel(new BorderLayout(0, 8));
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(new EmptyBorder(12, 14, 12, 14));
        if ("donthuoc".equals(ma)) {
            the.add(donThuoc(), BorderLayout.CENTER);
        } else if ("thuchi".equals(ma) || "thuchihangngay".equals(ma)) {
            the.add(thuChi("thuchihangngay".equals(ma) ? "Báo cáo thu chi hàng ngày" : "Báo cáo thu chi"), BorderLayout.CENTER);       
        } else if ("dtdichvu".equals(ma)) {
            the.add(doanhThuDichVu(), BorderLayout.CENTER);
        } else if ("dtcanhan".equals(ma)) {
            the.add(doanhThuCaNhan(), BorderLayout.CENTER);
        } else {
            the.add(mauChung(tieuCua(ma), cotCua(ma), dongCua(ma)), BorderLayout.CENTER);
        }
        add(the, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    private JPanel donThuoc() {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setOpaque(false);
        p.add(dau("Báo cáo toa thuốc", true), BorderLayout.NORTH);
        long tongTien = 0;
        java.util.List<view.model.ToaThuocView> ds = ToaThuocDuLieu.tatCa();
        Object[][] dong = new Object[Math.max(1, ds.size())][];
        int i = 0;
        for (view.model.ToaThuocView tt : ds) {
            StringBuilder ct = new StringBuilder();
            for (view.model.ChiTietToaThuocView c : tt.chiTiet) {
                if (ct.length() > 0) {
                    ct.append("; ");
                }
                ct.append(c.tongSo).append(" x ").append(c.tenThuoc)
                        .append(" (").append(ViewTheme.tien(c.donGia)).append(")");
            }
            tongTien += tt.thanhTien;
            dong[i] = new Object[]{
                String.valueOf(i + 1), tt.maToaThuoc, tt.ngayTao, tt.tenKh, ct.toString(),
                tt.hinhThucThanhToan, ViewTheme.tien(tt.thanhTien), "0",
                ViewTheme.tien(tt.thanhTien), tt.tenBacSi
            };
            i++;
        }
        if (ds.isEmpty()) {
            dong[0] = new Object[]{"", "", "", "", "", "", "0", "0", "0", ""};
        }
        JPanel tomTat = new JPanel(new GridLayout(1, 3, 12, 0));
        tomTat.setOpaque(false);
        tomTat.add(oSo("Tổng tiền thuốc", ViewTheme.tien(tongTien)));
        tomTat.add(oSo("Tổng chiết khấu", "0"));
        tomTat.add(oSo("Thành tiền", ViewTheme.tien(tongTien)));
        JPanel tren = new JPanel(new BorderLayout(12, 0));
        tren.setOpaque(false);
        tren.add(oNgay(), BorderLayout.WEST);
        tren.add(tomTat, BorderLayout.CENTER);
        String[] cot = {"#", "Mã toa thuốc", "Ngày tạo", "Khách hàng", "Toa thuốc chi tiết", "Hình thức TT", "Tiền thuốc", "Chiết khấu", "Thành tiền", "Bác sĩ"};
        JPanel than = new JPanel(new BorderLayout(0, 8));
        than.setOpaque(false);
        than.add(tren, BorderLayout.NORTH);
        than.add(bang(cot, dong), BorderLayout.CENTER);
        than.add(nutIn(true), BorderLayout.SOUTH);
        p.add(than, BorderLayout.CENTER);
        return p;
    }



    private JPanel thuChi(String tieu) {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setOpaque(false);
        p.add(dau(tieu, false), BorderLayout.NORTH);
        service.impl.BaoCao_Service bc = new service.impl.BaoCao_Service();
        long tongThu = 0;
        long tongChi = 0;
        java.util.List<Object[]> rows = new java.util.ArrayList<>();
        int stt = 1;
        for (entity.PhieuThu pt : bc.danhSachPhieuThu()) {
            tongThu += pt.getSoTien();
            rows.add(new Object[]{
                String.valueOf(stt++), pt.getMaPhieuThu(), NgayUtil.fmt(pt.getNgayThu()),
                pt.getMaKH(), "", pt.getLyDo(),
                ViewTheme.tien(pt.getSoTien()), "0", "0", "0", ViewTheme.tien(pt.getSoTien()),
                "0", "0", "0", "0", "0"
            });
        }
        for (entity.PhieuChi pc : bc.danhSachPhieuChi()) {
            tongChi += pc.getSoTien();
            rows.add(new Object[]{
                String.valueOf(stt++), pc.getMaPhieuChi(), NgayUtil.fmt(pc.getNgayChi()),
                "", pc.getMaNhaCungCap(), pc.getLyDoChi(),
                "0", "0", "0", "0", "0",
                ViewTheme.tien(pc.getSoTien()), "0", "0", "0", ViewTheme.tien(pc.getSoTien())
            });
        }
        rows.add(new Object[]{"", "", "", "", "", "Tổng tiền",
            ViewTheme.tien(tongThu), "0", "0", "0", ViewTheme.tien(tongThu),
            ViewTheme.tien(tongChi), "0", "0", "0", ViewTheme.tien(tongChi)});
        JPanel so = new JPanel(new GridLayout(1, 3, 8, 0));
        so.setOpaque(false);
        so.add(oSo("Tổng thu", ViewTheme.tien(tongThu)));
        so.add(oSo("Tổng chi", ViewTheme.tien(tongChi)));
        so.add(oSo("Tổng (Thu - chi)", ViewTheme.tien(tongThu - tongChi)));
        JPanel tren = new JPanel(new BorderLayout(12, 0));
        tren.setOpaque(false);
        tren.add(oNgay(), BorderLayout.WEST);
        tren.add(so, BorderLayout.CENTER);
        String[] cot = {"#", "Mã phiếu", "Ngày", "Khách hàng", "Nhà cung cấp", "Lý do",
            "Thu - Tiền mặt", "Thu - Thẻ", "Thu - Chuyển khoản", "Thu - Trả góp", "Thu - Tổng",
            "Chi - Tiền mặt", "Chi - Thẻ", "Chi - Chuyển khoản", "Chi - Trả góp", "Chi - Tổng"};
        Object[][] dong = rows.toArray(new Object[0][]);
        JPanel than = new JPanel(new BorderLayout(0, 8));
        than.setOpaque(false);
        than.add(tren, BorderLayout.NORTH);
        than.add(bang(cot, dong), BorderLayout.CENTER);
        than.add(nutIn(true), BorderLayout.SOUTH);
        p.add(than, BorderLayout.CENTER);
        return p;
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



    private JPanel baoCaoTab(String tieu, String[] tab, String[] cot, Object[][] dong) {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setOpaque(false);
        p.add(dau(tieu, false), BorderLayout.NORTH);
        JPanel thanh = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        thanh.setOpaque(false);
        JPanel vung = new JPanel(new BorderLayout());
        vung.setOpaque(false);
        vung.add(bang(cot, dong), BorderLayout.CENTER);
        for (int i = 0; i < tab.length; i++) {
            JButton b = new JButton(tab[i]);
            if (i == 0) {
                ViewTheme.nutChinh(b);
            } else {
                ViewTheme.nutXam(b);
            }
            b.addActionListener(e -> {
                for (Component c : thanh.getComponents()) {
                    if (c instanceof JButton) {
                        ViewTheme.nutXam((JButton) c);
                    }
                }
                ViewTheme.nutChinh(b);
            });
            thanh.add(b);
        }
        JPanel tren = new JPanel(new BorderLayout(0, 8));
        tren.setOpaque(false);
        tren.add(oNgay(), BorderLayout.NORTH);
        tren.add(thanh, BorderLayout.SOUTH);
        JPanel than = new JPanel(new BorderLayout(0, 8));
        than.setOpaque(false);
        than.add(tren, BorderLayout.NORTH);
        than.add(vung, BorderLayout.CENTER);
        than.add(nutIn(true), BorderLayout.SOUTH);
        p.add(than, BorderLayout.CENTER);
        return p;
    }

    private JPanel mauChung(String tieu, String[] cot, Object[][] dong) {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setOpaque(false);
        p.add(dau(tieu, false), BorderLayout.NORTH);
        JPanel than = new JPanel(new BorderLayout(0, 8));
        than.setOpaque(false);
        than.add(oNgay(), BorderLayout.NORTH);
        than.add(bang(cot, dong), BorderLayout.CENTER);
        than.add(nutIn(true), BorderLayout.SOUTH);
        p.add(than, BorderLayout.CENTER);
        return p;
    }

    private JPanel dau(String tieu, boolean taiLai) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel lb = ViewTheme.tieuDeTrang(tieu);
        p.add(lb, BorderLayout.WEST);
        if (taiLai) {
            JButton tai = new JButton("Tải lại");
            ViewTheme.nutXanh(tai);
            tai.addActionListener(e -> chon(ma));
            JPanel phai = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
            phai.setOpaque(false);
            phai.add(tai);
            p.add(phai, BorderLayout.CENTER);
        }
        return p;
    }

    private JTextField oNgay() {
        JTextField t = new JTextField("28/09/2026 - 28/09/2026");
        ViewTheme.vienNhap(t);
        t.setPreferredSize(new Dimension(220, 32));
        return t;
    }


    private JPanel oSo(String nhan, String so) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel a = ViewTheme.chuPhu(nhan);
        JLabel b = new JLabel(so);
        b.setFont(ViewTheme.FONT_SO);
        b.setForeground(ViewTheme.CHU);
        p.add(a, BorderLayout.NORTH);
        p.add(b, BorderLayout.CENTER);
        return p;
    }

    private JScrollPane bang(String[] cot, Object[][] dong) {
        DefaultTableModel model = ViewTheme.taoModel(cot, false, false);
        for (Object[] r : dong) {
            model.addRow(r);
        }
        JTable table = new JTable(model);
        ViewTheme.trangTriBang(table);
        JScrollPane cuon = new JScrollPane(table);
        cuon.setPreferredSize(new Dimension(400, 180));
        cuon.setBorder(javax.swing.BorderFactory.createLineBorder(ViewTheme.VIEN));
        cuon.getViewport().setBackground(ViewTheme.TRANG);
        return cuon;
    }

    private JPanel nutIn(boolean excel) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        p.setOpaque(false);
        JButton in = new JButton("In");
        ViewTheme.nutXanh(in);
        in.addActionListener(e -> JOptionPane.showMessageDialog(this, "Đã chuẩn bị bản in " + tieuCua(ma) + "."));
        p.add(in);
        if (excel) {
            JButton xuat = new JButton("Xuất excel...");
            ViewTheme.nutXanh(xuat);
            xuat.addActionListener(e -> JOptionPane.showMessageDialog(this, "Đã chuẩn bị file Excel cho " + tieuCua(ma) + "."));
            p.add(xuat);
        }
        return p;
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
        service.impl.BaoCao_Service bc = new service.impl.BaoCao_Service();
        if ("lichhen".equals(maBaoCao)) {
            java.util.List<Object[]> rows = new java.util.ArrayList<>();
            int i = 1;
            for (entity.LichHen lh : bc.danhSachLichHen()) {
                rows.add(new Object[]{
                    String.valueOf(i++), lh.getMaKH(), "", lh.getMaBacSi(),
                    NgayUtil.fmtFull(lh.getBatDau()), lh.getTrangThai()
                });
            }
            return rows.isEmpty() ? new Object[][]{{"", "", "", "", "", ""}} : rows.toArray(new Object[0][]);
        }
        if ("dieutri".equals(maBaoCao)) {
            java.util.List<Object[]> rows = new java.util.ArrayList<>();
            int i = 1;
            for (entity.KeHoachDieuTri kh : bc.danhSachKeHoach()) {
                for (entity.ChiTietKeHoachDieuTri ct : bc.chiTietTheoKeHoach(kh.getMaKeHoach())) {
                    rows.add(new Object[]{
                        String.valueOf(i++), kh.getMaKH(), ct.getMaDichVu(),
                        NgayUtil.fmt(ct.getNgay()), kh.getMaBacSi(), ViewTheme.tien(ct.getThanhTien())
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
            for (entity.PhieuChi pc : bc.danhSachPhieuChi()) {
                tong += pc.getSoTien();
            }
            return new Object[][]{{"", "", "", "Tổng tiền", ViewTheme.tien(tong)}};
        }
        if ("nhatky".equals(maBaoCao)) {
            java.util.List<entity.PhieuThu> pts = bc.danhSachPhieuThu();
            if (pts.isEmpty()) {
                return new Object[][]{{"", "", "", "", ""}};
            }
            entity.PhieuThu p = pts.get(0);
            return new Object[][]{{"1", NgayUtil.fmtFull(p.getNgayTao()), p.getNguoiTao(), "Tạo phiếu thu", p.getMaPhieuThu()}};
        }
        if ("sale".equals(maBaoCao) || "nguon".equals(maBaoCao) || "baocaolabo".equals(maBaoCao)) {
            return new Object[][]{{"", "", "", "0"}};
        }
        if ("tongquan".equals(maBaoCao)) {
            long doanhThu = 0;
            for (entity.HoaDon hd : bc.danhSachHoaDon()) {
                doanhThu += hd.getThanhTien();
            }
            return new Object[][]{
                {"1", "Lịch hẹn", String.valueOf(bc.danhSachLichHen().size()), ""},
                {"2", "Khách hàng", String.valueOf(new service.impl.KhachHang_Service().findAllKhachHang().size()), ""},
                {"3", "Doanh thu", "", ViewTheme.tien(doanhThu)}
            };
        }
        return new Object[][]{{"", "", "Tổng", "0"}};
    }
}
