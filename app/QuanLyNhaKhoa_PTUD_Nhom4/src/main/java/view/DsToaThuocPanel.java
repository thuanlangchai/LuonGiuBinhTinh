package view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import view.model.ChiTietToaThuocView;
import view.model.ToaThuocView;

public class DsToaThuocPanel extends JPanel {

    private static final String[] COT = {
        "Mã toa", "Mã KH", "Khách hàng", "Chẩn đoán", "Bác sĩ",
        "Hình thức TT", "Nhận tại PK", "Ghi chú", "Thành tiền", "Ngày tạo"
    };

    private final DefaultTableModel model = ViewTheme.taoModel(COT, true, true);
    private final JTable tblBang = new JTable(model);
    private final JTextField txtTim = new ONhap("", 24);
    private final JLabel lblNhanDem = new JLabel();
    private final JComboBox<String> cmbKhach = new JComboBox<String>();
    private final Runnable moLapToa;

    public DsToaThuocPanel(Runnable moLapToa) {
        this.moLapToa = moLapToa;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));

        JPanel pnlThe = new JPanel(new BorderLayout(0, 8));
        pnlThe.setBackground(ViewTheme.TRANG);
        pnlThe.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel lblNhanTieuDe = ViewTheme.tieuDeTrang("Toa thuốc");
        JButton btnThem = new JButton("Thêm toa thuốc");
        ViewTheme.nutXanh(btnThem);
        btnThem.addActionListener(e -> {
            if (moLapToa != null) {
                moLapToa.run();
            }
        });
        JPanel pnlDau = new JPanel(new BorderLayout());
        pnlDau.setOpaque(false);
        pnlDau.add(lblNhanTieuDe, BorderLayout.WEST);
        JPanel pnlPhai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        pnlPhai.setOpaque(false);
        pnlPhai.add(btnThem);
        pnlDau.add(pnlPhai, BorderLayout.EAST);

        ((ONhap) txtTim).datGoiY("Tìm toa thuốc...");
        txtTim.setPreferredSize(new Dimension(360, 32));
        txtTim.addActionListener(e -> loc());
        cmbKhach.addItem("Khách hàng");
        for (String khach : DuLieuMau.dsKhach()) {
            cmbKhach.addItem(khach);
        }
        ViewTheme.vienHop(cmbKhach);
        cmbKhach.addActionListener(e -> loc());

        JPanel pnlLoc = new JPanel(new BorderLayout(0, 8));
        pnlLoc.setBackground(ViewTheme.NEN_TIM);
        pnlLoc.setBorder(new EmptyBorder(10, 12, 10, 12));
        pnlLoc.add(ViewTheme.chuPhu("Tìm kiếm"), BorderLayout.NORTH);
        pnlLoc.add(txtTim, BorderLayout.CENTER);
        JPanel pnlDong = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pnlDong.setOpaque(false);
        pnlDong.add(cmbKhach);
        pnlLoc.add(pnlDong, BorderLayout.SOUTH);

        ViewTheme.trangTriBang(tblBang);
        ViewTheme.ganThaoTac(tblBang, this::xuLy);
        tblBang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tblBang.rowAtPoint(e.getPoint()) >= 0
                        && tblBang.columnAtPoint(e.getPoint()) != tblBang.getColumnCount() - 1) {
                    moChiTiet(toaTai(tblBang.convertRowIndexToModel(tblBang.rowAtPoint(e.getPoint()))));
                }
            }
        });
        JScrollPane scrBang = new JScrollPane(tblBang);
        scrBang.setPreferredSize(new Dimension(400, 220));
        scrBang.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        scrBang.getViewport().setBackground(ViewTheme.TRANG);
        lblNhanDem.setFont(ViewTheme.FONT_NHO);
        lblNhanDem.setForeground(ViewTheme.CHU_NHAT);

        pnlThe.add(pnlDau, BorderLayout.NORTH);
        JPanel pnlGiua = new JPanel(new BorderLayout(0, 8));
        pnlGiua.setOpaque(false);
        pnlGiua.add(pnlLoc, BorderLayout.NORTH);
        pnlGiua.add(scrBang, BorderLayout.CENTER);
        pnlGiua.add(lblNhanDem, BorderLayout.SOUTH);
        pnlThe.add(pnlGiua, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(pnlThe, BorderLayout.NORTH);

        ToaThuocDuLieu.khiDoi(this::lamMoi);
        lamMoi();
    }

    public void locTheoKhach(String maKH) {
        if (maKH == null || maKH.isEmpty()) {
            cmbKhach.setSelectedIndex(0);
        } else {
            for (int i = 0; i < cmbKhach.getItemCount(); i++) {
                if (cmbKhach.getItemAt(i).startsWith(maKH)) {
                    cmbKhach.setSelectedIndex(i);
                    break;
                }
            }
        }
        loc();
    }

    public String maKhachDangLoc() {
        if (cmbKhach.getSelectedIndex() <= 0) {
            return null;
        }
        String khach = String.valueOf(cmbKhach.getSelectedItem());
        return khach.length() >= 4 ? khach.substring(0, 4) : khach;
    }

    public void lamMoi() {
        model.setRowCount(0);
        for (ToaThuocView toaThuoc : ToaThuocDuLieu.tatCa()) {
            model.addRow(ViewTheme.dongBang(dongToa(toaThuoc), true, true));
        }
        int soLuong = ToaThuocDuLieu.tatCa().size();
        lblNhanDem.setText(ViewTheme.demDong(soLuong == 0 ? 0 : 1, soLuong, soLuong));
        loc();
    }

    private static Object[] dongToa(ToaThuocView toaThuoc) {
        return new Object[]{
            toaThuoc.maToaThuoc, toaThuoc.maKH, toaThuoc.tenKh, toaThuoc.maChanDoan, toaThuoc.tenBacSi,
            toaThuoc.hinhThucThanhToan, toaThuoc.nhanTaiPhongKham ? "Có" : "Không",
            toaThuoc.ghiChu, ViewTheme.tien(toaThuoc.thanhTien), toaThuoc.ngayTao
        };
    }

    private void loc() {
        String tuKhoa = txtTim.getText().trim();
        String khach = String.valueOf(cmbKhach.getSelectedItem());
        TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<DefaultTableModel>(model);
        tblBang.setRowSorter(sorter);
        sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                if (cmbKhach.getSelectedIndex() > 0) {
                    String maKH = khach.length() >= 4 ? khach.substring(0, 4) : khach;
                    if (!maKH.equals(String.valueOf(entry.getValue(2)))) {
                        return false;
                    }
                }
                if (tuKhoa.isEmpty()) {
                    return true;
                }
                String khoa = tuKhoa.toLowerCase();
                for (int i = 1; i < entry.getValueCount() - 1; i++) {
                    Object giaTri = entry.getValue(i);
                    if (giaTri != null && giaTri.toString().toLowerCase().contains(khoa)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void xuLy(String lenh, int hang) {
        ToaThuocView toaThuoc = toaTai(hang);
        if (toaThuoc == null) {
            return;
        }
        if ("Xóa".equals(lenh)) {
            if (JOptionPane.showConfirmDialog(this, "Xóa toa thuốc " + toaThuoc.maToaThuoc + "?",
                    "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ToaThuocDuLieu.xoa(toaThuoc);
            }
            return;
        }
        moChiTiet(toaThuoc);
    }

    private ToaThuocView toaTai(int hangModel) {
        if (hangModel < 0 || hangModel >= model.getRowCount()) {
            return null;
        }
        return ToaThuocDuLieu.theoMa(String.valueOf(model.getValueAt(hangModel, 1)));
    }

    private void moChiTiet(ToaThuocView toaThuoc) {
        if (toaThuoc == null) {
            return;
        }
        Window cuaSoCha = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(cuaSoCha, "Chi tiết toa thuốc " + toaThuoc.maToaThuoc, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLayout(new BorderLayout());

        JPanel pnlDau = new JPanel(new GridLayout(0, 4, 10, 8));
        pnlDau.setBackground(ViewTheme.TRANG);
        pnlDau.setBorder(new EmptyBorder(12, 16, 8, 16));
        pnlDau.add(nhan("Mã toa"));
        pnlDau.add(oKhoa(toaThuoc.maToaThuoc));
        pnlDau.add(nhan("Ngày tạo"));
        pnlDau.add(oKhoa(toaThuoc.ngayTao));
        pnlDau.add(nhan("Khách hàng"));
        pnlDau.add(oKhoa(toaThuoc.maKH + " - " + toaThuoc.tenKh));
        pnlDau.add(nhan("Chẩn đoán"));
        pnlDau.add(oKhoa(toaThuoc.maChanDoan));
        pnlDau.add(nhan("Bác sĩ"));
        pnlDau.add(oKhoa(toaThuoc.tenBacSi));
        pnlDau.add(nhan("Hình thức TT"));
        pnlDau.add(oKhoa(toaThuoc.hinhThucThanhToan));
        pnlDau.add(nhan("Nhận tại PK"));
        pnlDau.add(oKhoa(toaThuoc.nhanTaiPhongKham ? "Có" : "Không"));
        pnlDau.add(nhan("Ghi chú"));
        pnlDau.add(oKhoa(toaThuoc.ghiChu));

        DefaultTableModel modelChiTiet = ViewTheme.taoModel(
                new String[]{"#", "Thuốc", "Số ngày", "Cách sử dụng", "Tổng số", "Đơn giá", "Thành tiền"},
                false, false);
        int stt = 1;
        for (ChiTietToaThuocView chiTiet : toaThuoc.chiTiet) {
            modelChiTiet.addRow(new Object[]{
                String.valueOf(stt++), chiTiet.tenThuoc, String.valueOf(chiTiet.soNgay), chiTiet.cachSuDung,
                String.valueOf(chiTiet.tongSo), ViewTheme.tien(chiTiet.donGia), ViewTheme.tien(chiTiet.thanhTien)
            });
        }
        JTable tblChiTiet = new JTable(modelChiTiet);
        ViewTheme.trangTriBang(tblChiTiet);
        JScrollPane scrChiTiet = new JScrollPane(tblChiTiet);
        scrChiTiet.setBorder(BorderFactory.createTitledBorder("Chi tiết thuốc"));

        JPanel pnlTong = new JPanel(new FlowLayout(FlowLayout.RIGHT, 18, 4));
        pnlTong.setBackground(ViewTheme.TRANG);
        pnlTong.add(nhanTong("Thành tiền", ViewTheme.tien(toaThuoc.thanhTien)));

        JButton btnIn = new JButton("In toa thuốc");
        JButton btnDong = new JButton("Đóng");
        ViewTheme.nutXanh(btnIn);
        ViewTheme.nutXam(btnDong);
        btnIn.addActionListener(e -> JOptionPane.showMessageDialog(dialog,
                "Toa " + toaThuoc.maToaThuoc + "\nKhách: " + toaThuoc.tenKh
                + "\nThành tiền: " + ViewTheme.tien(toaThuoc.thanhTien)));
        btnDong.addActionListener(e -> dialog.dispose());
        JPanel pnlNut = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        pnlNut.setBackground(ViewTheme.TRANG);
        pnlNut.add(btnIn);
        pnlNut.add(btnDong);

        JPanel pnlDuoi = new JPanel(new BorderLayout());
        pnlDuoi.setBackground(ViewTheme.TRANG);
        pnlDuoi.add(pnlTong, BorderLayout.CENTER);
        pnlDuoi.add(pnlNut, BorderLayout.SOUTH);

        dialog.add(pnlDau, BorderLayout.NORTH);
        dialog.add(scrChiTiet, BorderLayout.CENTER);
        dialog.add(pnlDuoi, BorderLayout.SOUTH);
        dialog.setSize(920, 560);
        dialog.setLocationRelativeTo(cuaSoCha);
        dialog.setVisible(true);
    }

    private static JLabel nhan(String chu) {
        JLabel lblNhan = new JLabel(chu);
        lblNhan.setFont(ViewTheme.FONT_NHO);
        lblNhan.setForeground(ViewTheme.CHU_NHAT);
        return lblNhan;
    }

    private static JTextField oKhoa(String giaTri) {
        JTextField txt = new JTextField(giaTri == null ? "" : giaTri);
        txt.setEditable(false);
        ViewTheme.vienNhap(txt);
        return txt;
    }

    private static JLabel nhanTong(String nhan, String so) {
        JLabel lbl = new JLabel(nhan + ": " + so);
        lbl.setFont(ViewTheme.FONT_DAM);
        return lbl;
    }
}
