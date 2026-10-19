package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
        } else if ("implant".equals(ma)) {
            the.add(implant(), BorderLayout.CENTER);
        } else if ("thucthu".equals(ma)) {
            the.add(thucThu(), BorderLayout.CENTER);
        } else if ("thuchi".equals(ma) || "thuchihangngay".equals(ma)) {
            the.add(thuChi("thuchihangngay".equals(ma) ? "Báo cáo thu chi hàng ngày" : "Báo cáo thu chi"), BorderLayout.CENTER);
        } else if ("dtdichvu".equals(ma)) {
            the.add(doanhThuDichVu(), BorderLayout.CENTER);
        } else if ("dtcanhan".equals(ma)) {
            the.add(doanhThuCaNhan(), BorderLayout.CENTER);
        } else if ("dtnhanvien".equals(ma)) {
            the.add(doanhThuNhanVien(), BorderLayout.CENTER);
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
        JPanel tomTat = new JPanel(new GridLayout(1, 3, 12, 0));
        tomTat.setOpaque(false);
        tomTat.add(oSo("Tổng tiền thuốc", "36,000"));
        tomTat.add(oSo("Tổng chiết khấu", "0"));
        tomTat.add(oSo("Thành tiền", "36,000"));
        JPanel tren = new JPanel(new BorderLayout(12, 0));
        tren.setOpaque(false);
        tren.add(oNgay(), BorderLayout.WEST);
        tren.add(tomTat, BorderLayout.CENTER);
        String[] cot = {"#", "Mã toa thuốc", "Ngày tạo", "Khách hàng", "Toa thuốc chi tiết", "Hình thức TT", "Tiền thuốc", "Chiết khấu", "Thành tiền", "Bác sĩ"};
        Object[][] dong = {{"1", "0002", "28/09/2026", "Lê Trần Quang Thanh", "3 x aptx 4869 (36000)", "Tiền mặt", "36,000", "0", "36,000", "Lê Trần Quang Thanh"}};
        JPanel than = new JPanel(new BorderLayout(0, 8));
        than.setOpaque(false);
        than.add(tren, BorderLayout.NORTH);
        than.add(bang(cot, dong), BorderLayout.CENTER);
        than.add(nutIn(true), BorderLayout.SOUTH);
        p.add(than, BorderLayout.CENTER);
        return p;
    }

    private JPanel implant() {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setOpaque(false);
        p.add(dau("Báo cáo implant", false), BorderLayout.NORTH);
        JComboBox<String> ngayGui = new JComboBox<String>(new String[]{"Ngày gửi", "28/09/2026"});
        JComboBox<String> tt = new JComboBox<String>(new String[]{"Tình trạng", "Mới", "Hoàn thành"});
        JComboBox<String> ncc = new JComboBox<String>(new String[]{"Nhà cung cấp", "STRAUMANN"});
        ViewTheme.vienHop(ngayGui);
        ViewTheme.vienHop(tt);
        ViewTheme.vienHop(ncc);
        JPanel loc = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        loc.setOpaque(false);
        loc.add(ngayGui);
        loc.add(oNgay());
        loc.add(tt);
        loc.add(ncc);
        String[] cot = {"Mã", "Nhà cung cấp", "Bác sĩ", "Mã KH", "Khách hàng", "Chi tiết", "Tình trạng", "Ngày gửi", "Ngày nhận", "Ngày hoàn thành"};
        Object[][] dong = {{"0001", "STRAUMANN", "Lê Trần Quang Thanh", "0002", "Nguyễn Minh Lý", "1 x TRU IMPLANT - STRAUMANN - 360 (Hạn BH: 28/03/2027)", "Mới", "28/09/2026", "28/09/2026", "28/09/2026"}};
        DefaultTableModel model = ViewTheme.taoModel(cot, false, false);
        model.addRow(dong[0]);
        JTable table = new JTable(model);
        ViewTheme.trangTriBang(table);
        tt.addActionListener(e -> {
            javax.swing.table.TableRowSorter<DefaultTableModel> sx = new javax.swing.table.TableRowSorter<DefaultTableModel>(model);
            table.setRowSorter(sx);
            if (tt.getSelectedIndex() <= 0 || "Mới".equals(tt.getSelectedItem())) {
                sx.setRowFilter(null);
            } else {
                sx.setRowFilter(javax.swing.RowFilter.regexFilter(String.valueOf(tt.getSelectedItem())));
            }
        });
        JScrollPane cuon = new JScrollPane(table);
        cuon.setPreferredSize(new Dimension(400, 160));
        cuon.setBorder(javax.swing.BorderFactory.createLineBorder(ViewTheme.VIEN));
        JPanel than = new JPanel(new BorderLayout(0, 8));
        than.setOpaque(false);
        than.add(loc, BorderLayout.NORTH);
        than.add(cuon, BorderLayout.CENTER);
        than.add(nutIn(false), BorderLayout.SOUTH);
        p.add(than, BorderLayout.CENTER);
        return p;
    }

    private JPanel thucThu() {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setOpaque(false);
        p.add(dau("Báo cáo thực thu", true), BorderLayout.NORTH);
        JPanel tab = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        tab.setOpaque(false);
        JButton tongQuan = new JButton("Tổng quan");
        JButton chiTiet = new JButton("Chi tiết");
        ViewTheme.nutChinh(tongQuan);
        ViewTheme.nutXam(chiTiet);
        JPanel vung = new JPanel(new BorderLayout());
        vung.setOpaque(false);
        String[] cot = {"#", "Mã phiếu", "Ngày", "Khách hàng", "Nguồn", "KH(Mới/Cũ)", "Lý do", "Tiền mặt", "Thẻ", "Chuyển khoản", "Trả góp - BH", "Tổng tiền"};
        Object[][] tong = {{"1", "0002", "28/09/2026", "Lê Trần Quang Thanh", "Cư dân", "Cũ", "3 x aptx 4869 (36000)", "36,000", "0", "0", "0", "36,000"},
            {"", "", "", "", "", "", "Tổng tiền", "36,000", "0", "0", "0", "36,000"}};
        Object[][] ct = {{"1", "0002", "28/09/2026", "Lê Trần Quang Thanh", "Thuốc", "Cũ", "aptx 4869 x3", "36,000", "0", "0", "0", "36,000"}};
        vung.add(bang(cot, tong), BorderLayout.CENTER);
        tongQuan.addActionListener(e -> {
            ViewTheme.nutChinh(tongQuan);
            ViewTheme.nutXam(chiTiet);
            vung.removeAll();
            vung.add(bang(cot, tong), BorderLayout.CENTER);
            vung.revalidate();
        });
        chiTiet.addActionListener(e -> {
            ViewTheme.nutXam(tongQuan);
            ViewTheme.nutChinh(chiTiet);
            vung.removeAll();
            vung.add(bang(cot, ct), BorderLayout.CENTER);
            vung.revalidate();
        });
        tab.add(tongQuan);
        tab.add(chiTiet);
        JLabel tongThu = new JLabel("Tổng thu    36,000");
        tongThu.setFont(ViewTheme.FONT_DAM);
        JPanel tren = new JPanel(new BorderLayout());
        tren.setOpaque(false);
        JPanel trai = new JPanel(new BorderLayout(0, 6));
        trai.setOpaque(false);
        trai.add(oNgay(), BorderLayout.NORTH);
        trai.add(tab, BorderLayout.SOUTH);
        tren.add(trai, BorderLayout.WEST);
        tren.add(tongThu, BorderLayout.EAST);
        JPanel than = new JPanel(new BorderLayout(0, 8));
        than.setOpaque(false);
        than.add(tren, BorderLayout.NORTH);
        than.add(vung, BorderLayout.CENTER);
        than.add(nutIn(true), BorderLayout.SOUTH);
        p.add(than, BorderLayout.CENTER);
        return p;
    }

    private JPanel thuChi(String tieu) {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setOpaque(false);
        p.add(dau(tieu, false), BorderLayout.NORTH);
        JPanel so = new JPanel(new GridLayout(1, 3, 8, 0));
        so.setOpaque(false);
        so.add(oSo("Tổng thu", "36,000"));
        so.add(oSo("Tổng chi", "0"));
        so.add(oSo("Tổng (Thu - chi)", "36,000"));
        JPanel tren = new JPanel(new BorderLayout(12, 0));
        tren.setOpaque(false);
        tren.add(oNgay(), BorderLayout.WEST);
        tren.add(so, BorderLayout.CENTER);
        String[] cot = {"#", "Mã phiếu", "Ngày", "Khách hàng", "Nhà cung cấp", "Lý do",
            "Thu - Tiền mặt", "Thu - Thẻ", "Thu - Chuyển khoản", "Thu - Trả góp", "Thu - Tổng",
            "Chi - Tiền mặt", "Chi - Thẻ", "Chi - Chuyển khoản", "Chi - Trả góp", "Chi - Tổng"};
        Object[][] dong = {
            {"1", "0002", "28/09/2026", "Lê Trần Quang Thanh", "", "3 x aptx 4869 (36000)", "36,000", "0", "0", "0", "36,000", "0", "0", "0", "0", "0"},
            {"", "", "", "", "", "Tổng tiền", "36,000", "0", "0", "0", "36,000", "0", "0", "0", "0", "0"}
        };
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

    private JPanel doanhThuNhanVien() {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setOpaque(false);
        p.add(dau("Doanh thu nhân viên", false), BorderLayout.NORTH);
        JTextField chinh = oLoc("Người làm chính");
        JTextField phu = oLoc("Phụ tá");
        JTextField tuVan = oLoc("Tư vấn");
        JTextField sale = oLoc("Sale/Telesale");
        JTextField gt = oLoc("Người giới thiệu");
        JPanel loc = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        loc.setOpaque(false);
        loc.add(oNgay());
        loc.add(chinh);
        loc.add(phu);
        loc.add(tuVan);
        loc.add(sale);
        loc.add(gt);
        JPanel vung = new JPanel(new BorderLayout());
        vung.setOpaque(false);
        String[] tab = {"Doanh thu", "Nhân viên", "Người làm chính", "Phụ tá", "Tư vấn", "Sale", "Telesale", "Người giới thiệu"};
        JPanel thanh = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        thanh.setOpaque(false);
        String[] cotThu = {"#", "Mã phiếu", "Ngày", "Đối tượng", "Điện thoại", "Lý do", "Người làm chính", "Phụ tá", "Tư vấn", "Sale", "Telesale", "Người giới thiệu", "Tiền mặt", "Thẻ", "Chuyển khoản", "Trả góp - BH", "Tổng tiền"};
        String[] cotNv = {"#", "Chức danh", "Tên nhân viên", "Vai trò", "Doanh thu"};
        Object[][] nv = {
            {"1", "Bs", "Lê Trần Quang Thanh", "Bác sĩ", "36,000"},
            {"2", "Bs", "Hồ Văn Khánh", "Bác sĩ", "0"},
            {"", "", "", "Tổng tiền", "36,000"}
        };
        vung.add(bang(cotThu, new Object[][]{{"", "", "", "", "", "", "", "", "", "", "", "", "Tổng tiền", "0", "0", "0", "0"}}), BorderLayout.CENTER);
        for (int i = 0; i < tab.length; i++) {
            JButton b = new JButton(tab[i]);
            if (i == 0) {
                ViewTheme.nutChinh(b);
            } else {
                ViewTheme.nutXam(b);
            }
            int idx = i;
            b.addActionListener(e -> {
                for (java.awt.Component c : thanh.getComponents()) {
                    if (c instanceof JButton) {
                        ViewTheme.nutXam((JButton) c);
                    }
                }
                ViewTheme.nutChinh(b);
                vung.removeAll();
                if (idx == 1) {
                    vung.add(bang(cotNv, nv), BorderLayout.CENTER);
                } else {
                    vung.add(bang(cotThu, new Object[][]{{"1", "0002", "28/09/2026", "Lê Trần Quang Thanh", "03xxxx2808", "Thuốc", "Lê Trần Quang Thanh", "", "", "", "", "", "36,000", "0", "0", "0", "36,000"}}), BorderLayout.CENTER);
                }
                vung.revalidate();
                vung.repaint();
            });
            thanh.add(b);
        }
        JPanel than = new JPanel(new BorderLayout(0, 8));
        than.setOpaque(false);
        than.add(loc, BorderLayout.NORTH);
        than.add(thanh, BorderLayout.CENTER);
        JPanel duoi = new JPanel(new BorderLayout(0, 8));
        duoi.setOpaque(false);
        duoi.add(vung, BorderLayout.CENTER);
        duoi.add(nutIn(false), BorderLayout.SOUTH);
        JPanel gop = new JPanel(new BorderLayout(0, 8));
        gop.setOpaque(false);
        gop.add(than, BorderLayout.NORTH);
        gop.add(duoi, BorderLayout.CENTER);
        p.add(gop, BorderLayout.CENTER);
        return p;
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
                for (java.awt.Component c : thanh.getComponents()) {
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

    private JTextField oLoc(String goi) {
        JTextField t = new ONhap(goi, 12);
        t.setPreferredSize(new Dimension(140, 32));
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
        if ("tongquan".equals(maBaoCao)) {
            return "Tổng quan";
        }
        if ("lichhen".equals(maBaoCao)) {
            return "Báo cáo lịch hẹn";
        }
        if ("dieutri".equals(maBaoCao)) {
            return "Báo cáo điều trị";
        }
        if ("chuakhoi".equals(maBaoCao)) {
            return "Báo cáo chưa khai thác";
        }
        if ("baocaolabo".equals(maBaoCao)) {
            return "Báo cáo labo";
        }
        if ("sale".equals(maBaoCao)) {
            return "Doanh số chốt sale";
        }
        if ("nguon".equals(maBaoCao)) {
            return "Doanh thu nguồn";
        }
        if ("congno".equals(maBaoCao)) {
            return "Báo cáo công nợ";
        }
        if ("nhatky".equals(maBaoCao)) {
            return "Nhật ký";
        }
        if ("chiphi".equals(maBaoCao)) {
            return "Báo cáo chi phí";
        }
        if ("donthuoc".equals(maBaoCao)) {
            return "Báo cáo toa thuốc";
        }
        if ("implant".equals(maBaoCao)) {
            return "Báo cáo implant";
        }
        if ("thucthu".equals(maBaoCao)) {
            return "Báo cáo thực thu";
        }
        if ("thuchi".equals(maBaoCao)) {
            return "Báo cáo thu chi";
        }
        if ("thuchihangngay".equals(maBaoCao)) {
            return "Báo cáo thu chi hàng ngày";
        }
        if ("dtdichvu".equals(maBaoCao)) {
            return "Doanh thu dịch vụ";
        }
        if ("dtcanhan".equals(maBaoCao)) {
            return "Doanh thu cá nhân";
        }
        if ("dtnhanvien".equals(maBaoCao)) {
            return "Doanh thu nhân viên";
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
        if ("lichhen".equals(maBaoCao)) {
            return new Object[][]{{"1", "Lê Trần Quang Thanh", "Chỉnh nha", "Lê Trần Quang Thanh", "28/09/2026 08:00", "Đang điều trị"}};
        }
        if ("dieutri".equals(maBaoCao)) {
            return new Object[][]{{"1", "Lê Trần Quang Thanh", "Thăm khám - Tư vấn", "27/09/2026", "Lê Trần Quang Thanh", "200,000"}};
        }
        if ("congno".equals(maBaoCao)) {
            return HoaDonDuLieu.dongCongNo();
        }
        if ("chiphi".equals(maBaoCao)) {
            return new Object[][]{{"", "", "", "Tổng tiền", "0"}};
        }
        if ("nhatky".equals(maBaoCao)) {
            return new Object[][]{{"1", "28/09/2026 09:34", DuLieuMau.NGUOI, "Tạo phiếu thu", "0002"}};
        }
        if ("sale".equals(maBaoCao)) {
            return new Object[][]{{"1", "Nguyễn Khắc Duy", "1", "36,000"}};
        }
        if ("nguon".equals(maBaoCao)) {
            return new Object[][]{{"1", "Cư dân", "1", "36,000"}};
        }
        if ("baocaolabo".equals(maBaoCao)) {
            return new Object[][]{{"1", "0001", "STRAUMANN", "Nguyễn Minh Lý", "Mão sứ răng 11", "Mới", "28/09/2026"}};
        }
        if ("tongquan".equals(maBaoCao)) {
            return new Object[][]{
                {"1", "Lịch hẹn trong ngày", "3", ""},
                {"2", "Khách hàng", "2", ""},
                {"3", "Doanh thu", "", "36,000"}
            };
        }
        return new Object[][]{{"", "", "Tổng", "0"}};
    }
}
