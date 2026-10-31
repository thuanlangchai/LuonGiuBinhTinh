package view;

import view.model.HangDT;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import view.model.ChiTietHoaDonView;
import view.model.HoaDonView;

public class HoaDonPanel extends JPanel {

    private static final String[] COT = {
        "Mã HĐ", "Ngày lập", "Mã KH", "Mã kế hoạch", "Bác sĩ", "Tổng tiền", "Chiết khấu",
        "Thành tiền", "Đã thu", "Còn nợ", "Trạng thái", "Ghi chú", "Ngày tạo", "Người tạo"
    };

    private final KeHoachDieuTriPanel keHoach;
    private final PhieuTienPanel phieuThu;
    private final DefaultTableModel model = ViewTheme.taoModel(COT, true, true);
    private final JTable bang = new JTable(model);
    private final JTextField tim = new ONhap("", 24);
    private final JLabel dem = new JLabel();
    private Runnable moPhieuThu;

    public HoaDonPanel(KeHoachDieuTriPanel keHoach, PhieuTienPanel phieuThu) {
        this.keHoach = keHoach;
        this.phieuThu = phieuThu;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));

        JPanel the = new JPanel(new BorderLayout(0, 8));
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel tieu = ViewTheme.tieuDeTrang("Hóa đơn");
        JButton them = new JButton("Thêm hóa đơn");
        ViewTheme.nutXanh(them);
        them.addActionListener(e -> moLapHoaDon(null, null));
        JPanel dau = new JPanel(new BorderLayout());
        dau.setOpaque(false);
        dau.add(tieu, BorderLayout.WEST);
        JPanel phai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        phai.setOpaque(false);
        phai.add(them);
        dau.add(phai, BorderLayout.EAST);

        ((ONhap) tim).datGoiY("Tìm hóa đơn...");
        tim.setPreferredSize(new Dimension(360, 32));
        tim.addActionListener(e -> loc());
        JComboBox<String> khach = new JComboBox<String>();
        khach.addItem("Khách hàng");
        for (String k : DuLieuMau.KHACH) {
            khach.addItem(k);
        }
        JComboBox<String> tt = new JComboBox<String>(new String[]{
            "Trạng thái", "Chưa thanh toán", "Thanh toán một phần", "Đã thanh toán"
        });
        ViewTheme.vienHop(khach);
        ViewTheme.vienHop(tt);
        khach.addActionListener(e -> loc());
        tt.addActionListener(e -> loc());

        JPanel loc = new JPanel(new BorderLayout(0, 8));
        loc.setBackground(ViewTheme.NEN_TIM);
        loc.setBorder(new EmptyBorder(10, 12, 10, 12));
        loc.add(ViewTheme.chuPhu("Tìm kiếm"), BorderLayout.NORTH);
        loc.add(tim, BorderLayout.CENTER);
        JPanel dong = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        dong.setOpaque(false);
        dong.add(khach);
        dong.add(tt);
        loc.add(dong, BorderLayout.SOUTH);

        ViewTheme.trangTriBang(bang);
        ViewTheme.ganThaoTac(bang, this::xuLy);
        bang.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && bang.rowAtPoint(e.getPoint()) >= 0
                        && bang.columnAtPoint(e.getPoint()) != bang.getColumnCount() - 1) {
                    moChiTiet(hoaDonTai(bang.convertRowIndexToModel(bang.rowAtPoint(e.getPoint()))));
                }
            }
        });
        JScrollPane cuon = new JScrollPane(bang);
        cuon.setPreferredSize(new Dimension(400, 220));
        cuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        cuon.getViewport().setBackground(ViewTheme.TRANG);
        dem.setFont(ViewTheme.FONT_NHO);
        dem.setForeground(ViewTheme.CHU_NHAT);

        the.add(dau, BorderLayout.NORTH);
        JPanel giua = new JPanel(new BorderLayout(0, 8));
        giua.setOpaque(false);
        giua.add(loc, BorderLayout.NORTH);
        giua.add(cuon, BorderLayout.CENTER);
        giua.add(dem, BorderLayout.SOUTH);
        the.add(giua, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(the, BorderLayout.NORTH);

        HoaDonDuLieu.khiDoi(this::lamMoi);
        lamMoi();
    }

    public void ganMoPhieuThu(Runnable moPhieuThu) {
        this.moPhieuThu = moPhieuThu;
    }

    public void lamMoi() {
        model.setRowCount(0);
        for (HoaDonView hd : HoaDonDuLieu.tatCa()) {
            model.addRow(ViewTheme.dongBang(dongHoaDon(hd), true, true));
        }
        dem.setText(ViewTheme.demDong(HoaDonDuLieu.tatCa().isEmpty() ? 0 : 1,
                HoaDonDuLieu.tatCa().size(), HoaDonDuLieu.tatCa().size()));
    }

    public void moLapHoaDon(String maKh, List<HangDT> goiY) {
        Window w = SwingUtilities.getWindowAncestor(this);
        JDialog d = new JDialog(w, "Lập hóa đơn", java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        d.setLayout(new BorderLayout());

        JTextField ngay = o(HoaDonDuLieu.homNay());
        JComboBox<String> khach = new JComboBox<String>(DuLieuMau.KHACH);
        ViewTheme.vienHop(khach);
        khach.setPreferredSize(new Dimension(360, 34));
        if (maKh != null) {
            chonKhach(khach, maKh);
        }
        JComboBox<String> bacSi = new JComboBox<String>(DuLieuMau.BAC_SI);
        ViewTheme.vienHop(bacSi);
        JTextField chietKhau = o("0");
        JTextField ghiChu = o("Xuất từ kế hoạch điều trị");

        DefaultTableModel ctModel = ViewTheme.taoModel(
                new String[]{"Ngày", "Răng", "Dịch vụ", "Số lượng", "Đơn giá", "Thành tiền", "Kế hoạch"},
                true, false);
        JTable ctBang = new JTable(ctModel);
        ViewTheme.trangTriBang(ctBang);
        JLabel tongChon = new JLabel("Tổng dịch vụ chọn: 0");
        tongChon.setFont(ViewTheme.FONT_DAM);

        Runnable napHang = () -> {
            String ma = maKhach(String.valueOf(khach.getSelectedItem()));
            List<HangDT> ds = keHoach.hangChuaXuat(ma);
            Set<HangDT> chonSan = new LinkedHashSet<HangDT>();
            if (goiY != null) {
                chonSan.addAll(goiY);
            }
            ctModel.setRowCount(0);
            for (HangDT hang : ds) {
                Object[] dong = ViewTheme.dongBang(new Object[]{
                    hang.ngay, hang.rang, hang.dichVu, String.valueOf(hang.soLuong),
                    ViewTheme.tien(hang.donGia), ViewTheme.tien(hang.thanhTien()), hang.tenKeHoach()
                }, true, false);
                dong[0] = Boolean.valueOf(chonSan.contains(hang) || goiY == null || goiY.isEmpty());
                ctModel.addRow(dong);
            }
            capNhatTongChon(ctModel, tongChon);
        };
        khach.addActionListener(e -> napHang.run());
        ctBang.getModel().addTableModelListener(e -> capNhatTongChon(ctModel, tongChon));

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.setOpaque(false);
        form.setBorder(new EmptyBorder(12, 16, 8, 16));
        form.add(nhan("Ngày lập *"));
        form.add(ngay);
        form.add(nhan("Khách hàng *"));
        form.add(khach);
        form.add(nhan("Bác sĩ"));
        form.add(bacSi);
        form.add(nhan("Chiết khấu"));
        form.add(chietKhau);
        form.add(nhan("Ghi chú"));
        form.add(ghiChu);

        JPanel giua = new JPanel(new BorderLayout(0, 8));
        giua.setBackground(ViewTheme.TRANG);
        giua.add(form, BorderLayout.NORTH);
        JScrollPane cuon = new JScrollPane(ctBang);
        cuon.setPreferredSize(new Dimension(760, 220));
        cuon.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn (chọn dịch vụ đã điều trị)"));
        giua.add(cuon, BorderLayout.CENTER);
        giua.add(tongChon, BorderLayout.SOUTH);
        d.add(giua, BorderLayout.CENTER);

        JButton boQua = new JButton("Bỏ qua");
        JButton luu = new JButton("+ Thêm");
        ViewTheme.nutXam(boQua);
        ViewTheme.nutChinh(luu);
        boQua.addActionListener(e -> d.dispose());
        luu.addActionListener(e -> {
            String ma = maKhach(String.valueOf(khach.getSelectedItem()));
            String ten = tenKhach(String.valueOf(khach.getSelectedItem()));
            List<HangDT> goc = keHoach.hangChuaXuat(ma);
            List<HangDT> chon = new ArrayList<HangDT>();
            List<ChiTietHoaDonView> chiTiet = new ArrayList<ChiTietHoaDonView>();
            for (int i = 0; i < ctModel.getRowCount() && i < goc.size(); i++) {
                if (Boolean.TRUE.equals(ctModel.getValueAt(i, 0))) {
                    HangDT hang = goc.get(i);
                    chon.add(hang);
                    chiTiet.add(new ChiTietHoaDonView(hang.ngay, hang.rang, hang.dichVu,
                            hang.soLuong, hang.donGia, hang.tenKeHoach()));
                }
            }
            if (chiTiet.isEmpty()) {
                JOptionPane.showMessageDialog(d, "Chọn ít nhất một dịch vụ để lập hóa đơn.");
                return;
            }
            String keHoachTen = chiTiet.get(0).keHoach;
            HoaDonView hd = HoaDonDuLieu.them(
                    ngay.getText().trim(), ma, ten, keHoachTen,
                    String.valueOf(bacSi.getSelectedItem()),
                    parseTien(chietKhau.getText()),
                    ghiChu.getText().trim(),
                    chiTiet);
            keHoach.danhDauHoaDon(chon, hd.ma);
            d.dispose();
            lamMoi();
            int xem = JOptionPane.showConfirmDialog(this,
                    "Đã lập hóa đơn " + hd.ma + " - thành tiền " + ViewTheme.tien(hd.thanhTien)
                    + ".\nMở chi tiết hóa đơn?",
                    "Hóa đơn", JOptionPane.YES_NO_OPTION);
            if (xem == JOptionPane.YES_OPTION) {
                moChiTiet(hd);
            }
        });
        JPanel chan = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        chan.setBackground(ViewTheme.TRANG);
        chan.add(boQua);
        chan.add(luu);
        d.add(chan, BorderLayout.SOUTH);
        napHang.run();
        d.setSize(920, 640);
        d.setLocationRelativeTo(w);
        d.setVisible(true);
    }

    public void thuTien(HoaDonView hd) {
        if (hd == null) {
            return;
        }
        if (hd.conNo() <= 0) {
            JOptionPane.showMessageDialog(this, "Hóa đơn " + hd.ma + " đã thanh toán đủ.");
            return;
        }
        Window w = SwingUtilities.getWindowAncestor(this);
        HopThoai h = new HopThoai(w, "Tạo phiếu thu", "+ Thêm");
        JTextField ngayThu = h.chu("Ngày thu", HoaDonDuLieu.homNay(), true);
        h.chu("Khách hàng", hd.maKh + " - " + hd.tenKh, true).setEditable(false);
        h.chu("Hóa đơn", hd.ma + " - " + hd.keHoach, true).setEditable(false);
        JTextField lyDo = h.chu("Lý do", "Thanh toán hóa đơn " + hd.ma, true);
        JTextField soTien = h.chu("Số tiền", ViewTheme.tien(hd.conNo()), true);
        JComboBox<String> trangThai = h.chon("Trạng thái", true, "Đã chi trả", "Chưa chi trả");
        JComboBox<String> hinhThuc = h.chon("Hình thức thanh toán", true, "Tiền mặt", "Thẻ", "Chuyển khoản");
        h.hien(() -> {
            long tien = parseTien(soTien.getText());
            if (tien <= 0) {
                JOptionPane.showMessageDialog(h, "Số tiền phải lớn hơn 0.");
                return;
            }
            if (tien > hd.conNo()) {
                tien = hd.conNo();
            }
            if ("Đã chi trả".equals(trangThai.getSelectedItem())) {
                HoaDonDuLieu.thuTien(hd, tien);
            }
            if (phieuThu != null) {
                phieuThu.themPhieu(ngayThu.getText().trim(), hd.maKh, hd.tenKh,
                        lyDo.getText().trim(), ViewTheme.tien(tien),
                        String.valueOf(hinhThuc.getSelectedItem()),
                        String.valueOf(trangThai.getSelectedItem()));
            }
            keHoach.lamMoiBang();
            lamMoi();
            JOptionPane.showMessageDialog(this,
                    "Đã lập phiếu thu " + ViewTheme.tien(tien) + " cho hóa đơn " + hd.ma
                    + ".\nCòn nợ: " + ViewTheme.tien(hd.conNo()));
            if (moPhieuThu != null && JOptionPane.showConfirmDialog(this,
                    "Mở danh sách phiếu thu?", "Phiếu thu", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                moPhieuThu.run();
            }
        });
    }

    private void xuLy(String lenh, int hang) {
        HoaDonView hd = hoaDonTai(hang);
        if (hd == null) {
            return;
        }
        if ("Xóa".equals(lenh)) {
            if (hd.daThu > 0) {
                JOptionPane.showMessageDialog(this, "Không xóa hóa đơn đã thu tiền.");
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "Xóa hóa đơn " + hd.ma + "?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                keHoach.boDanhDauHoaDon(hd.ma);
                HoaDonDuLieu.xoa(hd);
                lamMoi();
            }
            return;
        }
        if ("Xem".equals(lenh)) {
            moChiTiet(hd);
            return;
        }
        moChiTiet(hd);
    }

    private void moChiTiet(HoaDonView hd) {
        if (hd == null) {
            return;
        }
        Window w = SwingUtilities.getWindowAncestor(this);
        JDialog d = new JDialog(w, "Chi tiết hóa đơn " + hd.ma, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        d.setLayout(new BorderLayout());

        JPanel dau = new JPanel(new GridLayout(0, 4, 10, 8));
        dau.setBackground(ViewTheme.TRANG);
        dau.setBorder(new EmptyBorder(12, 16, 8, 16));
        dau.add(nhan("Mã hóa đơn"));
        dau.add(oKhoa(hd.ma));
        dau.add(nhan("Ngày lập"));
        dau.add(oKhoa(hd.ngayLap));
        dau.add(nhan("Khách hàng"));
        dau.add(oKhoa(hd.maKh + " - " + hd.tenKh));
        dau.add(nhan("Kế hoạch"));
        dau.add(oKhoa(hd.keHoach));
        dau.add(nhan("Bác sĩ"));
        dau.add(oKhoa(hd.bacSi));
        dau.add(nhan("Trạng thái"));
        dau.add(oKhoa(hd.trangThai));
        dau.add(nhan("Ghi chú"));
        dau.add(oKhoa(hd.ghiChu));
        dau.add(nhan("Ngày tạo"));
        dau.add(oKhoa(hd.ngayTao));

        DefaultTableModel ct = ViewTheme.taoModel(
                new String[]{"#", "Ngày", "Răng", "Dịch vụ", "Số lượng", "Đơn giá", "Thành tiền", "Kế hoạch"},
                false, false);
        int stt = 1;
        for (ChiTietHoaDonView c : hd.chiTiet) {
            ct.addRow(new Object[]{
                String.valueOf(stt++), c.ngay, c.rang, c.dichVu,
                String.valueOf(c.soLuong), ViewTheme.tien(c.donGia),
                ViewTheme.tien(c.thanhTien()), c.keHoach
            });
        }
        JTable table = new JTable(ct);
        ViewTheme.trangTriBang(table);
        JScrollPane cuon = new JScrollPane(table);
        cuon.setBorder(BorderFactory.createTitledBorder("Chi tiết dịch vụ"));

        JPanel tong = new JPanel(new FlowLayout(FlowLayout.RIGHT, 18, 4));
        tong.setBackground(ViewTheme.TRANG);
        tong.add(nhanTong("Tổng cộng", ViewTheme.tien(hd.tong)));
        tong.add(nhanTong("Chiết khấu", ViewTheme.tien(hd.chietKhau)));
        tong.add(nhanTong("Thành tiền", ViewTheme.tien(hd.thanhTien)));
        tong.add(nhanTong("Đã thu", ViewTheme.tien(hd.daThu)));
        JLabel no = nhanTong("Còn nợ", ViewTheme.tien(hd.conNo()));
        no.setForeground(ViewTheme.DO);
        tong.add(no);

        JButton thu = new JButton("Thu tiền");
        JButton in = new JButton("In hóa đơn");
        JButton dong = new JButton("Đóng");
        ViewTheme.nutChinh(thu);
        ViewTheme.nutXanh(in);
        ViewTheme.nutXam(dong);
        thu.setEnabled(hd.conNo() > 0);
        thu.addActionListener(e -> {
            d.dispose();
            thuTien(hd);
        });
        in.addActionListener(e -> JOptionPane.showMessageDialog(d,
                "Hóa đơn " + hd.ma + "\nKhách: " + hd.tenKh
                + "\nThành tiền: " + ViewTheme.tien(hd.thanhTien)
                + "\nĐã thu: " + ViewTheme.tien(hd.daThu)
                + "\nCòn nợ: " + ViewTheme.tien(hd.conNo())));
        dong.addActionListener(e -> d.dispose());
        JPanel nut = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        nut.setBackground(ViewTheme.TRANG);
        nut.add(in);
        nut.add(thu);
        nut.add(dong);

        JPanel duoi = new JPanel(new BorderLayout());
        duoi.setBackground(ViewTheme.TRANG);
        duoi.add(tong, BorderLayout.CENTER);
        duoi.add(nut, BorderLayout.SOUTH);

        d.add(dau, BorderLayout.NORTH);
        d.add(cuon, BorderLayout.CENTER);
        d.add(duoi, BorderLayout.SOUTH);
        d.setSize(980, 620);
        d.setLocationRelativeTo(w);
        d.setVisible(true);
    }

    private void loc() {
        String tu = tim.getText().trim();
        javax.swing.table.TableRowSorter<DefaultTableModel> sx =
                new javax.swing.table.TableRowSorter<DefaultTableModel>(model);
        bang.setRowSorter(sx);
        if (tu.isEmpty()) {
            sx.setRowFilter(null);
        } else {
            sx.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(tu)));
        }
    }

    private HoaDonView hoaDonTai(int hangModel) {
        if (hangModel < 0 || hangModel >= model.getRowCount()) {
            return null;
        }
        return HoaDonDuLieu.theoMa(String.valueOf(model.getValueAt(hangModel, 1)));
    }

    private static Object[] dongHoaDon(HoaDonView hd) {
        return new Object[]{
            hd.ma, hd.ngayLap, hd.maKh, hd.keHoach, hd.bacSi,
            ViewTheme.tien(hd.tong), ViewTheme.tien(hd.chietKhau), ViewTheme.tien(hd.thanhTien),
            ViewTheme.tien(hd.daThu), ViewTheme.tien(hd.conNo()), hd.trangThai, hd.ghiChu, hd.ngayTao, DuLieuMau.NGUOI
        };
    }

    private static void capNhatTongChon(DefaultTableModel ctModel, JLabel tongChon) {
        long tong = 0;
        int so = 0;
        for (int i = 0; i < ctModel.getRowCount(); i++) {
            if (Boolean.TRUE.equals(ctModel.getValueAt(i, 0))) {
                so++;
                tong += parseTien(String.valueOf(ctModel.getValueAt(i, 6)));
            }
        }
        tongChon.setText("Đã chọn " + so + " dịch vụ - Tổng: " + ViewTheme.tien(tong));
    }

    private static JLabel nhan(String chu) {
        JLabel lb = new JLabel(chu);
        lb.setFont(ViewTheme.FONT);
        return lb;
    }

    private static JLabel nhanTong(String nhan, String so) {
        JLabel lb = new JLabel(nhan + ": " + so);
        lb.setFont(ViewTheme.FONT_DAM);
        return lb;
    }

    private static JTextField o(String gt) {
        JTextField t = new JTextField(gt);
        ViewTheme.vienNhap(t);
        return t;
    }

    private static JTextField oKhoa(String gt) {
        JTextField t = o(gt == null ? "" : gt);
        t.setEditable(false);
        return t;
    }

    private static void chonKhach(JComboBox<String> hop, String ma) {
        for (int i = 0; i < hop.getItemCount(); i++) {
            if (hop.getItemAt(i).startsWith(ma)) {
                hop.setSelectedIndex(i);
                return;
            }
        }
    }

    private static String maKhach(String muc) {
        if (muc == null) {
            return "";
        }
        int gach = muc.indexOf(" - ");
        return gach > 0 ? muc.substring(0, gach).trim() : muc.trim();
    }

    private static String tenKhach(String muc) {
        if (muc == null) {
            return "";
        }
        String[] p = muc.split(" - ");
        return p.length >= 2 ? p[1].trim() : muc;
    }

    static long parseTien(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        String so = s.replaceAll("[^0-9]", "");
        if (so.isEmpty()) {
            return 0;
        }
        try {
            return Long.parseLong(so);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}
