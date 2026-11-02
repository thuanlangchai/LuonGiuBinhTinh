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
    private final JTable bang = new JTable(model);
    private final JTextField txtTim = new ONhap("", 24);
    private final JLabel nhanDem = new JLabel();
    private final JComboBox<String> cboKhach = new JComboBox<String>();
    private final Runnable moLapToa;

    public DsToaThuocPanel(Runnable moLapToa) {
        this.moLapToa = moLapToa;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));

        JPanel panelThe = new JPanel(new BorderLayout(0, 8));
        panelThe.setBackground(ViewTheme.TRANG);
        panelThe.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel nhanTieuDe = ViewTheme.lblTieuDeTrang("Toa thuốc");
        JButton btnThem = new JButton("Thêm toa thuốc");
        ViewTheme.btnNutXanh(btnThem);
        btnThem.addActionListener(e -> {
            if (moLapToa != null) {
                moLapToa.run();
            }
        });
        JPanel panelDau = new JPanel(new BorderLayout());
        panelDau.setOpaque(false);
        panelDau.add(nhanTieuDe, BorderLayout.WEST);
        JPanel panelPhai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panelPhai.setOpaque(false);
        panelPhai.add(btnThem);
        panelDau.add(panelPhai, BorderLayout.EAST);

        ((ONhap) txtTim).datGoiY("Tìm toa thuốc...");
        txtTim.setPreferredSize(new Dimension(360, 32));
        txtTim.addActionListener(e -> loc());
        cboKhach.addItem("Khách hàng");
        for (String khach : DuLieuMau.dsKhach()) {
            cboKhach.addItem(khach);
        }
        ViewTheme.cmbVienHop(cboKhach);
        cboKhach.addActionListener(e -> loc());

        JPanel panelLoc = new JPanel(new BorderLayout(0, 8));
        panelLoc.setBackground(ViewTheme.NEN_TIM);
        panelLoc.setBorder(new EmptyBorder(10, 12, 10, 12));
        panelLoc.add(ViewTheme.lblChuPhu("Tìm kiếm"), BorderLayout.NORTH);
        panelLoc.add(txtTim, BorderLayout.CENTER);
        JPanel panelDong = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelDong.setOpaque(false);
        panelDong.add(cboKhach);
        panelLoc.add(panelDong, BorderLayout.SOUTH);

        ViewTheme.trangTriBang(bang);
        ViewTheme.ganThaoTac(bang, this::xuLy);
        bang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && bang.rowAtPoint(e.getPoint()) >= 0
                        && bang.columnAtPoint(e.getPoint()) != bang.getColumnCount() - 1) {
                    moChiTiet(toaTai(bang.convertRowIndexToModel(bang.rowAtPoint(e.getPoint()))));
                }
            }
        });
        JScrollPane scrollBang = new JScrollPane(bang);
        scrollBang.setPreferredSize(new Dimension(400, 220));
        scrollBang.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        scrollBang.getViewport().setBackground(ViewTheme.TRANG);
        nhanDem.setFont(ViewTheme.FONT_NHO);
        nhanDem.setForeground(ViewTheme.CHU_NHAT);

        panelThe.add(panelDau, BorderLayout.NORTH);
        JPanel panelGiua = new JPanel(new BorderLayout(0, 8));
        panelGiua.setOpaque(false);
        panelGiua.add(panelLoc, BorderLayout.NORTH);
        panelGiua.add(scrollBang, BorderLayout.CENTER);
        panelGiua.add(nhanDem, BorderLayout.SOUTH);
        panelThe.add(panelGiua, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(panelThe, BorderLayout.NORTH);

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
        String khach = String.valueOf(cboKhach.getSelectedItem());
        return khach.length() >= 4 ? khach.substring(0, 4) : khach;
    }

    public void lamMoi() {
        model.setRowCount(0);
        for (ToaThuocView toaThuoc : ToaThuocDuLieu.tatCa()) {
            model.addRow(ViewTheme.dongBang(dongToa(toaThuoc), true, true));
        }
        int soLuong = ToaThuocDuLieu.tatCa().size();
        nhanDem.setText(ViewTheme.demDong(soLuong == 0 ? 0 : 1, soLuong, soLuong));
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
        String khach = String.valueOf(cboKhach.getSelectedItem());
        TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<DefaultTableModel>(model);
        bang.setRowSorter(sorter);
        sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                if (cboKhach.getSelectedIndex() > 0) {
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

        JPanel panelDau = new JPanel(new GridLayout(0, 4, 10, 8));
        panelDau.setBackground(ViewTheme.TRANG);
        panelDau.setBorder(new EmptyBorder(12, 16, 8, 16));
        panelDau.add(nhan("Mã toa"));
        panelDau.add(oKhoa(toaThuoc.maToaThuoc));
        panelDau.add(nhan("Ngày tạo"));
        panelDau.add(oKhoa(toaThuoc.ngayTao));
        panelDau.add(nhan("Khách hàng"));
        panelDau.add(oKhoa(toaThuoc.maKH + " - " + toaThuoc.tenKh));
        panelDau.add(nhan("Chẩn đoán"));
        panelDau.add(oKhoa(toaThuoc.maChanDoan));
        panelDau.add(nhan("Bác sĩ"));
        panelDau.add(oKhoa(toaThuoc.tenBacSi));
        panelDau.add(nhan("Hình thức TT"));
        panelDau.add(oKhoa(toaThuoc.hinhThucThanhToan));
        panelDau.add(nhan("Nhận tại PK"));
        panelDau.add(oKhoa(toaThuoc.nhanTaiPhongKham ? "Có" : "Không"));
        panelDau.add(nhan("Ghi chú"));
        panelDau.add(oKhoa(toaThuoc.ghiChu));

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
        JTable bangChiTiet = new JTable(modelChiTiet);
        ViewTheme.trangTriBang(bangChiTiet);
        JScrollPane scrollChiTiet = new JScrollPane(bangChiTiet);
        scrollChiTiet.setBorder(BorderFactory.createTitledBorder("Chi tiết thuốc"));

        JPanel panelTong = new JPanel(new FlowLayout(FlowLayout.RIGHT, 18, 4));
        panelTong.setBackground(ViewTheme.TRANG);
        panelTong.add(nhanTong("Thành tiền", ViewTheme.tien(toaThuoc.thanhTien)));

        JButton btnIn = new JButton("In toa thuốc");
        JButton btnDong = new JButton("Đóng");
        ViewTheme.btnNutXanh(btnIn);
        ViewTheme.btnNutXam(btnDong);
        btnIn.addActionListener(e -> JOptionPane.showMessageDialog(dialog,
                "Toa " + toaThuoc.maToaThuoc + "\nKhách: " + toaThuoc.tenKh
                + "\nThành tiền: " + ViewTheme.tien(toaThuoc.thanhTien)));
        btnDong.addActionListener(e -> dialog.dispose());
        JPanel panelNut = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        panelNut.setBackground(ViewTheme.TRANG);
        panelNut.add(btnIn);
        panelNut.add(btnDong);

        JPanel panelDuoi = new JPanel(new BorderLayout());
        panelDuoi.setBackground(ViewTheme.TRANG);
        panelDuoi.add(panelTong, BorderLayout.CENTER);
        panelDuoi.add(panelNut, BorderLayout.SOUTH);

        dialog.add(panelDau, BorderLayout.NORTH);
        dialog.add(scrollChiTiet, BorderLayout.CENTER);
        dialog.add(panelDuoi, BorderLayout.SOUTH);
        dialog.setSize(920, 560);
        dialog.setLocationRelativeTo(cuaSoCha);
        dialog.setVisible(true);
    }

    private static JLabel nhan(String chu) {
        JLabel nhan = new JLabel(chu);
        nhan.setFont(ViewTheme.FONT_NHO);
        nhan.setForeground(ViewTheme.CHU_NHAT);
        return nhan;
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
