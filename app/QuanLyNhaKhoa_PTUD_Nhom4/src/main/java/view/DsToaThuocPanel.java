package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
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
import view.model.ChiTietToaThuocView;
import view.model.ToaThuocView;

public class DsToaThuocPanel extends JPanel {

    private static final String[] COT = {
        "Mã toa", "Mã KH", "Khách hàng", "Chẩn đoán", "Bác sĩ",
        "Hình thức TT", "Nhận tại PK", "Ghi chú", "Thành tiền", "Ngày tạo"
    };

    private final DefaultTableModel model = ViewTheme.taoModel(COT, true, true);
    private final JTable bang = new JTable(model);
    private final JTextField tim = new ONhap("", 24);
    private final JLabel dem = new JLabel();
    private final JComboBox<String> cboKhach = new JComboBox<String>();
    private final Runnable moLapToa;

    public DsToaThuocPanel(Runnable moLapToa) {
        this.moLapToa = moLapToa;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));

        JPanel the = new JPanel(new BorderLayout(0, 8));
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel tieu = ViewTheme.tieuDeTrang("Toa thuốc");
        JButton them = new JButton("Thêm toa thuốc");
        ViewTheme.nutXanh(them);
        them.addActionListener(e -> {
            if (moLapToa != null) {
                moLapToa.run();
            }
        });
        JPanel dau = new JPanel(new BorderLayout());
        dau.setOpaque(false);
        dau.add(tieu, BorderLayout.WEST);
        JPanel phai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        phai.setOpaque(false);
        phai.add(them);
        dau.add(phai, BorderLayout.EAST);

        ((ONhap) tim).datGoiY("Tìm toa thuốc...");
        tim.setPreferredSize(new Dimension(360, 32));
        tim.addActionListener(e -> loc());
        cboKhach.addItem("Khách hàng");
        for (String k : DuLieuMau.KHACH) {
            cboKhach.addItem(k);
        }
        ViewTheme.vienHop(cboKhach);
        cboKhach.addActionListener(e -> loc());

        JPanel loc = new JPanel(new BorderLayout(0, 8));
        loc.setBackground(ViewTheme.NEN_TIM);
        loc.setBorder(new EmptyBorder(10, 12, 10, 12));
        loc.add(ViewTheme.chuPhu("Tìm kiếm"), BorderLayout.NORTH);
        loc.add(tim, BorderLayout.CENTER);
        JPanel dong = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        dong.setOpaque(false);
        dong.add(cboKhach);
        loc.add(dong, BorderLayout.SOUTH);

        ViewTheme.trangTriBang(bang);
        ViewTheme.ganThaoTac(bang, this::xuLy);
        bang.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && bang.rowAtPoint(e.getPoint()) >= 0
                        && bang.columnAtPoint(e.getPoint()) != bang.getColumnCount() - 1) {
                    moChiTiet(toaTai(bang.convertRowIndexToModel(bang.rowAtPoint(e.getPoint()))));
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

        ToaThuocDuLieu.khiDoi(this::lamMoi);
        lamMoi();
    }

    public void locTheoKhach(String maKH) {
        if (maKH == null || maKH.isEmpty()) {
            cboKhach.setSelectedIndex(0);
        } else {
            for (int i = 0; i < cboKhach.getItemCount(); i++) {
                if (cboKhach.getItemAt(i).startsWith(maKH)) {
                    cboKhach.setSelectedIndex(i);
                    break;
                }
            }
        }
        loc();
    }

    public String maKhachDangLoc() {
        if (cboKhach.getSelectedIndex() <= 0) {
            return null;
        }
        String kh = String.valueOf(cboKhach.getSelectedItem());
        return kh.length() >= 4 ? kh.substring(0, 4) : kh;
    }

    public void lamMoi() {
        model.setRowCount(0);
        for (ToaThuocView tt : ToaThuocDuLieu.tatCa()) {
            model.addRow(ViewTheme.dongBang(dongToa(tt), true, true));
        }
        int n = ToaThuocDuLieu.tatCa().size();
        dem.setText(ViewTheme.demDong(n == 0 ? 0 : 1, n, n));
        loc();
    }

    private static Object[] dongToa(ToaThuocView tt) {
        return new Object[]{
            tt.maToaThuoc, tt.maKH, tt.tenKh, tt.maChanDoan, tt.tenBacSi,
            tt.hinhThucThanhToan, tt.nhanTaiPhongKham ? "Có" : "Không",
            tt.ghiChu, ViewTheme.tien(tt.thanhTien), tt.ngayTao
        };
    }

    private void loc() {
        String tu = tim.getText().trim();
        String kh = String.valueOf(cboKhach.getSelectedItem());
        javax.swing.table.TableRowSorter<DefaultTableModel> sx =
                new javax.swing.table.TableRowSorter<DefaultTableModel>(model);
        bang.setRowSorter(sx);
        sx.setRowFilter(new javax.swing.RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                if (cboKhach.getSelectedIndex() > 0) {
                    String maKH = kh.length() >= 4 ? kh.substring(0, 4) : kh;
                    if (!maKH.equals(String.valueOf(entry.getValue(2)))) {
                        return false;
                    }
                }
                if (tu.isEmpty()) {
                    return true;
                }
                String khoa = tu.toLowerCase();
                for (int i = 1; i < entry.getValueCount() - 1; i++) {
                    Object v = entry.getValue(i);
                    if (v != null && v.toString().toLowerCase().contains(khoa)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void xuLy(String lenh, int hang) {
        ToaThuocView tt = toaTai(hang);
        if (tt == null) {
            return;
        }
        if ("Xóa".equals(lenh)) {
            if (JOptionPane.showConfirmDialog(this, "Xóa toa thuốc " + tt.maToaThuoc + "?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ToaThuocDuLieu.xoa(tt);
            }
            return;
        }
        moChiTiet(tt);
    }

    private ToaThuocView toaTai(int hangModel) {
        if (hangModel < 0 || hangModel >= model.getRowCount()) {
            return null;
        }
        return ToaThuocDuLieu.theoMa(String.valueOf(model.getValueAt(hangModel, 1)));
    }

    private void moChiTiet(ToaThuocView tt) {
        if (tt == null) {
            return;
        }
        Window w = SwingUtilities.getWindowAncestor(this);
        JDialog d = new JDialog(w, "Chi tiết toa thuốc " + tt.maToaThuoc, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        d.setLayout(new BorderLayout());

        JPanel dau = new JPanel(new GridLayout(0, 4, 10, 8));
        dau.setBackground(ViewTheme.TRANG);
        dau.setBorder(new EmptyBorder(12, 16, 8, 16));
        dau.add(nhan("Mã toa"));
        dau.add(oKhoa(tt.maToaThuoc));
        dau.add(nhan("Ngày tạo"));
        dau.add(oKhoa(tt.ngayTao));
        dau.add(nhan("Khách hàng"));
        dau.add(oKhoa(tt.maKH + " - " + tt.tenKh));
        dau.add(nhan("Chẩn đoán"));
        dau.add(oKhoa(tt.maChanDoan));
        dau.add(nhan("Bác sĩ"));
        dau.add(oKhoa(tt.tenBacSi));
        dau.add(nhan("Hình thức TT"));
        dau.add(oKhoa(tt.hinhThucThanhToan));
        dau.add(nhan("Nhận tại PK"));
        dau.add(oKhoa(tt.nhanTaiPhongKham ? "Có" : "Không"));
        dau.add(nhan("Ghi chú"));
        dau.add(oKhoa(tt.ghiChu));

        DefaultTableModel ct = ViewTheme.taoModel(
                new String[]{"#", "Thuốc", "Số ngày", "Cách sử dụng", "Tổng số", "Đơn giá", "Thành tiền"},
                false, false);
        int stt = 1;
        for (ChiTietToaThuocView c : tt.chiTiet) {
            ct.addRow(new Object[]{
                String.valueOf(stt++), c.tenThuoc, String.valueOf(c.soNgay), c.cachSuDung,
                String.valueOf(c.tongSo), ViewTheme.tien(c.donGia), ViewTheme.tien(c.thanhTien)
            });
        }
        JTable table = new JTable(ct);
        ViewTheme.trangTriBang(table);
        JScrollPane cuon = new JScrollPane(table);
        cuon.setBorder(BorderFactory.createTitledBorder("Chi tiết thuốc"));

        JPanel tong = new JPanel(new FlowLayout(FlowLayout.RIGHT, 18, 4));
        tong.setBackground(ViewTheme.TRANG);
        tong.add(nhanTong("Thành tiền", ViewTheme.tien(tt.thanhTien)));

        JButton in = new JButton("In toa thuốc");
        JButton dong = new JButton("Đóng");
        ViewTheme.nutXanh(in);
        ViewTheme.nutXam(dong);
        in.addActionListener(e -> JOptionPane.showMessageDialog(d,
                "Toa " + tt.maToaThuoc + "\nKhách: " + tt.tenKh
                + "\nThành tiền: " + ViewTheme.tien(tt.thanhTien)));
        dong.addActionListener(e -> d.dispose());
        JPanel nut = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        nut.setBackground(ViewTheme.TRANG);
        nut.add(in);
        nut.add(dong);

        JPanel duoi = new JPanel(new BorderLayout());
        duoi.setBackground(ViewTheme.TRANG);
        duoi.add(tong, BorderLayout.CENTER);
        duoi.add(nut, BorderLayout.SOUTH);

        d.add(dau, BorderLayout.NORTH);
        d.add(cuon, BorderLayout.CENTER);
        d.add(duoi, BorderLayout.SOUTH);
        d.setSize(920, 560);
        d.setLocationRelativeTo(w);
        d.setVisible(true);
    }

    private static JLabel nhan(String chu) {
        JLabel lb = new JLabel(chu);
        lb.setFont(ViewTheme.FONT_NHO);
        lb.setForeground(ViewTheme.CHU_NHAT);
        return lb;
    }

    private static JTextField oKhoa(String giaTri) {
        JTextField t = new JTextField(giaTri == null ? "" : giaTri);
        t.setEditable(false);
        ViewTheme.vienNhap(t);
        return t;
    }

    private static JLabel nhanTong(String nhan, String so) {
        JLabel lb = new JLabel(nhan + ": " + so);
        lb.setFont(ViewTheme.FONT_DAM);
        return lb;
    }
}
