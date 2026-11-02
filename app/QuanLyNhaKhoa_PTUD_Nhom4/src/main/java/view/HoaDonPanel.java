package view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
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
import view.model.ChiTietHoaDonView;
import view.model.HangDT;
import view.model.HoaDonView;

public class HoaDonPanel extends JPanel {

    private static final String[] COT = {
        "Mã HĐ", "Ngày lập", "Mã KH", "Mã kế hoạch", "Bác sĩ", "Tổng tiền", "Chiết khấu",
        "Thành tiền", "Đã thu", "Còn nợ", "Trạng thái", "Ghi chú", "Ngày tạo", "Người tạo"
    };

    private final KeHoachDieuTriPanel keHoach;
    private final PhieuTienPanel phieuThu;
    private final DefaultTableModel model = ViewTheme.taoModel(COT, true, true);
    private final JTable tblBang = new JTable(model);
    private final JTextField txtTim = new ONhap("", 24);
    private final JLabel lblDem = new JLabel();
    private Runnable moPhieuThu;

    public HoaDonPanel(KeHoachDieuTriPanel keHoach, PhieuTienPanel phieuThu) {
        this.keHoach = keHoach;
        this.phieuThu = phieuThu;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));

        JPanel pnlThe = new JPanel(new BorderLayout(0, 8));
        pnlThe.setBackground(ViewTheme.TRANG);
        pnlThe.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel lblTieuDe = ViewTheme.lblTieuDeTrang("Hóa đơn");
        JButton btnThem = new JButton("Thêm hóa đơn");
        ViewTheme.btnNutXanh(btnThem);
        btnThem.addActionListener(e -> moLapHoaDon(null, null));
        JPanel pnlDau = new JPanel(new BorderLayout());
        pnlDau.setOpaque(false);
        pnlDau.add(lblTieuDe, BorderLayout.WEST);
        JPanel pnlPhai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        pnlPhai.setOpaque(false);
        pnlPhai.add(btnThem);
        pnlDau.add(pnlPhai, BorderLayout.EAST);

        ((ONhap) txtTim).datGoiY("Tìm hóa đơn...");
        txtTim.setPreferredSize(new Dimension(360, 32));
        txtTim.addActionListener(e -> loc());
        JComboBox<String> cmbKhach = new JComboBox<String>();
        cmbKhach.addItem("Khách hàng");
        for (String k : DuLieuMau.dsKhach()) {
            cmbKhach.addItem(k);
        }
        JComboBox<String> cboTrangThai = new JComboBox<String>(new String[]{
            "Trạng thái", "Chưa thanh toán", "Thanh toán một phần", "Đã thanh toán"
        });
        ViewTheme.vienHop(cmbKhach);
        ViewTheme.vienHop(cboTrangThai);
        cmbKhach.addActionListener(e -> loc());
        cboTrangThai.addActionListener(e -> loc());

        JPanel pnlLoc = new JPanel(new BorderLayout(0, 8));
        pnlLoc.setBackground(ViewTheme.NEN_TIM);
        pnlLoc.setBorder(new EmptyBorder(10, 12, 10, 12));
        pnlLoc.add(ViewTheme.chuPhu("Tìm kiếm"), BorderLayout.NORTH);
        pnlLoc.add(txtTim, BorderLayout.CENTER);
        JPanel pnlDongLoc = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pnlDongLoc.setOpaque(false);
        pnlDongLoc.add(cmbKhach);
        pnlDongLoc.add(cboTrangThai);
        pnlLoc.add(pnlDongLoc, BorderLayout.SOUTH);

        ViewTheme.trangTriBang(tblBang);
        ViewTheme.ganThaoTac(tblBang, this::xuLy);
        tblBang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tblBang.rowAtPoint(e.getPoint()) >= 0
                        && tblBang.columnAtPoint(e.getPoint()) != tblBang.getColumnCount() - 1) {
                    moChiTiet(hoaDonTai(tblBang.convertRowIndexToModel(tblBang.rowAtPoint(e.getPoint()))));
                }
            }
        });
        JScrollPane scbBang = new JScrollPane(tblBang);
        scbBang.setPreferredSize(new Dimension(400, 220));
        scbBang.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        scbBang.getViewport().setBackground(ViewTheme.TRANG);
        lblDem.setFont(ViewTheme.FONT_NHO);
        lblDem.setForeground(ViewTheme.CHU_NHAT);

        pnlThe.add(pnlDau, BorderLayout.NORTH);
        JPanel pnlGiua = new JPanel(new BorderLayout(0, 8));
        pnlGiua.setOpaque(false);
        pnlGiua.add(pnlLoc, BorderLayout.NORTH);
        pnlGiua.add(scbBang, BorderLayout.CENTER);
        pnlGiua.add(lblDem, BorderLayout.SOUTH);
        pnlThe.add(pnlGiua, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(pnlThe, BorderLayout.NORTH);

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
        lblDem.setText(ViewTheme.demDong(HoaDonDuLieu.tatCa().isEmpty() ? 0 : 1,
                HoaDonDuLieu.tatCa().size(), HoaDonDuLieu.tatCa().size()));
    }

    public void moLapHoaDon(String maKh, List<HangDT> goiY) {
        Window w = SwingUtilities.getWindowAncestor(this);
        JDialog d = new JDialog(w, "Lập hóa đơn", Dialog.ModalityType.APPLICATION_MODAL);
        d.setLayout(new BorderLayout());

        JTextField txtNgay = o(HoaDonDuLieu.homNay());
        JComboBox<String> cmbKhach = new JComboBox<String>(DuLieuMau.dsKhach());
        ViewTheme.vienHop(cmbKhach);
        cmbKhach.setPreferredSize(new Dimension(360, 34));
        if (maKh != null) {
            chonKhach(cmbKhach, maKh);
        }
        JComboBox<String> cmbBacSi = new JComboBox<String>(DuLieuMau.dsBacSi());
        ViewTheme.vienHop(cmbBacSi);
        JTextField txtChietKhau = o("0");
        JTextField txtGhiChu = o("Xuất từ kế hoạch điều trị");

        DefaultTableModel ctModel = ViewTheme.taoModel(
                new String[]{"Ngày", "Răng", "Dịch vụ", "Số lượng", "Đơn giá", "Thành tiền", "Kế hoạch"},
                true, false);
        JTable tblBang = new JTable(ctModel);
        ViewTheme.trangTriBang(tblBang);
        JLabel lbltongChon = new JLabel("Tổng dịch vụ chọn: 0");
        lbltongChon.setFont(ViewTheme.FONT_DAM);

        Runnable napHang = () -> {
            String ma = maKhach(String.valueOf(cmbKhach.getSelectedItem()));
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
            capNhatTongChon(ctModel, lbltongChon);
        };
        cmbKhach.addActionListener(e -> napHang.run());
        tblBang.getModel().addTableModelListener(e -> capNhatTongChon(ctModel, lbltongChon));

        JPanel pnlform = new JPanel(new GridLayout(0, 2, 8, 8));
        pnlform.setOpaque(false);
        pnlform.setBorder(new EmptyBorder(12, 16, 8, 16));
        pnlform.add(nhan("Ngày lập *"));
        pnlform.add(txtNgay);
        pnlform.add(nhan("Khách hàng *"));
        pnlform.add(cmbKhach);
        pnlform.add(nhan("Bác sĩ"));
        pnlform.add(cmbBacSi);
        pnlform.add(nhan("Chiết khấu"));
        pnlform.add(txtChietKhau);
        pnlform.add(nhan("Ghi chú"));
        pnlform.add(txtGhiChu);

        JPanel pnlGiua = new JPanel(new BorderLayout(0, 8));
        pnlGiua.setBackground(ViewTheme.TRANG);
        pnlGiua.add(pnlform, BorderLayout.NORTH);
        JScrollPane scbCuon = new JScrollPane(tblBang);
        scbCuon.setPreferredSize(new Dimension(760, 220));
        scbCuon.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn (chọn dịch vụ đã điều trị)"));
        pnlGiua.add(scbCuon, BorderLayout.CENTER);
        pnlGiua.add(lbltongChon, BorderLayout.SOUTH);
        d.add(pnlGiua, BorderLayout.CENTER);

        JButton btnBoQua = new JButton("Bỏ qua");
        JButton btnLuu = new JButton("+ Thêm");
        ViewTheme.nutXam(btnBoQua);
        ViewTheme.nutChinh(btnLuu);
        btnBoQua.addActionListener(e -> d.dispose());
        btnLuu.addActionListener(e -> {
            String ma = maKhach(String.valueOf(cmbKhach.getSelectedItem()));
            String ten = tenKhach(String.valueOf(cmbKhach.getSelectedItem()));
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
                    txtNgay.getText().trim(), ma, ten, keHoachTen,
                    String.valueOf(cmbBacSi.getSelectedItem()),
                    parseTien(txtChietKhau.getText()),
                    txtGhiChu.getText().trim(),
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
        JPanel pnlChan = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        pnlChan.setBackground(ViewTheme.TRANG);
        pnlChan.add(btnBoQua);
        pnlChan.add(btnLuu);
        d.add(pnlChan, BorderLayout.SOUTH);
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
        JTextField txtNgayThu = h.chu("Ngày thu", HoaDonDuLieu.homNay(), true);
        h.chu("Khách hàng", hd.maKh + " - " + hd.tenKh, true).setEditable(false);
        h.chu("Hóa đơn", hd.ma + " - " + hd.keHoach, true).setEditable(false);
        JTextField txtLyDo = h.chu("Lý do", "Thanh toán hóa đơn " + hd.ma, true);
        JTextField txtSoTien = h.chu("Số tiền", ViewTheme.tien(hd.conNo()), true);
        JComboBox<String> cboTrangThai = h.chon("Trạng thái", true, "Đã chi trả", "Chưa chi trả");
        JComboBox<String> cmbHinhThuc = h.chon("Hình thức thanh toán", true, "Tiền mặt", "Thẻ", "Chuyển khoản");
        h.hien(() -> {
            long tien = parseTien(txtSoTien.getText());
            if (tien <= 0) {
                JOptionPane.showMessageDialog(h, "Số tiền phải lớn hơn 0.");
                return;
            }
            if (tien > hd.conNo()) {
                tien = hd.conNo();
            }
            if ("Đã chi trả".equals(cboTrangThai.getSelectedItem())) {
                HoaDonDuLieu.thuTien(hd, tien);
            }
            if (phieuThu != null) {
                phieuThu.themPhieu(txtNgayThu.getText().trim(), hd.maKh, hd.tenKh,
                        txtLyDo.getText().trim(), ViewTheme.tien(tien),
                        String.valueOf(cmbHinhThuc.getSelectedItem()),
                        String.valueOf(cboTrangThai.getSelectedItem()));
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
        JDialog d = new JDialog(w, "Chi tiết hóa đơn " + hd.ma, Dialog.ModalityType.APPLICATION_MODAL);
        d.setLayout(new BorderLayout());

        JPanel pnlDau = new JPanel(new GridLayout(0, 4, 10, 8));
        pnlDau.setBackground(ViewTheme.TRANG);
        pnlDau.setBorder(new EmptyBorder(12, 16, 8, 16));
        pnlDau.add(nhan("Mã hóa đơn"));
        pnlDau.add(oKhoa(hd.ma));
        pnlDau.add(nhan("Ngày lập"));
        pnlDau.add(oKhoa(hd.ngayLap));
        pnlDau.add(nhan("Khách hàng"));
        pnlDau.add(oKhoa(hd.maKh + " - " + hd.tenKh));
        pnlDau.add(nhan("Kế hoạch"));
        pnlDau.add(oKhoa(hd.keHoach));
        pnlDau.add(nhan("Bác sĩ"));
        pnlDau.add(oKhoa(hd.bacSi));
        pnlDau.add(nhan("Trạng thái"));
        pnlDau.add(oKhoa(hd.trangThai));
        pnlDau.add(nhan("Ghi chú"));
        pnlDau.add(oKhoa(hd.ghiChu));
        pnlDau.add(nhan("Ngày tạo"));
        pnlDau.add(oKhoa(hd.ngayTao));

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
        JTable tblTable = new JTable(ct);
        ViewTheme.trangTriBang(tblTable);
        JScrollPane scbCuon = new JScrollPane(tblTable);
        scbCuon.setBorder(BorderFactory.createTitledBorder("Chi tiết dịch vụ"));

        JPanel pnlTong = new JPanel(new FlowLayout(FlowLayout.RIGHT, 18, 4));
        pnlTong.setBackground(ViewTheme.TRANG);
        pnlTong.add(nhanTong("Tổng cộng", ViewTheme.tien(hd.tong)));
        pnlTong.add(nhanTong("Chiết khấu", ViewTheme.tien(hd.chietKhau)));
        pnlTong.add(nhanTong("Thành tiền", ViewTheme.tien(hd.thanhTien)));
        pnlTong.add(nhanTong("Đã thu", ViewTheme.tien(hd.daThu)));
        JLabel lblno = nhanTong("Còn nợ", ViewTheme.tien(hd.conNo()));
        lblno.setForeground(ViewTheme.DO);
        pnlTong.add(lblno);

        JButton btnThu = new JButton("Thu tiền");
        JButton btnIn = new JButton("In hóa đơn");
        JButton btnDong = new JButton("Đóng");
        ViewTheme.nutChinh(btnThu);
        ViewTheme.nutXanh(btnIn);
        ViewTheme.nutXam(btnDong);
        btnThu.setEnabled(hd.conNo() > 0);
        btnThu.addActionListener(e -> {
            d.dispose();
            thuTien(hd);
        });
        btnIn.addActionListener(e -> JOptionPane.showMessageDialog(d,
                "Hóa đơn " + hd.ma + "\nKhách: " + hd.tenKh
                + "\nThành tiền: " + ViewTheme.tien(hd.thanhTien)
                + "\nĐã thu: " + ViewTheme.tien(hd.daThu)
                + "\nCòn nợ: " + ViewTheme.tien(hd.conNo())));
        btnDong.addActionListener(e -> d.dispose());
        JPanel pnlNut = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        pnlNut.setBackground(ViewTheme.TRANG);
        pnlNut.add(btnIn);
        pnlNut.add(btnThu);
        pnlNut.add(btnDong);

        JPanel pnlDuoi = new JPanel(new BorderLayout());
        pnlDuoi.setBackground(ViewTheme.TRANG);
        pnlDuoi.add(pnlTong, BorderLayout.CENTER);
        pnlDuoi.add(pnlNut, BorderLayout.SOUTH);

        d.add(pnlDau, BorderLayout.NORTH);
        d.add(scbCuon, BorderLayout.CENTER);
        d.add(pnlDuoi, BorderLayout.SOUTH);
        d.setSize(980, 620);
        d.setLocationRelativeTo(w);
        d.setVisible(true);
    }

    private void loc() {
        String tuKhoa = txtTim.getText().trim();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
        tblBang.setRowSorter(sorter);
        if (tuKhoa.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(tuKhoa)));
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

    private static void capNhatTongChon(DefaultTableModel ctModel, JLabel lbltongChon) {
        long tong = 0;
        int so = 0;
        for (int i = 0; i < ctModel.getRowCount(); i++) {
            if (Boolean.TRUE.equals(ctModel.getValueAt(i, 0))) {
                so++;
                tong += parseTien(String.valueOf(ctModel.getValueAt(i, 6)));
            }
        }
        lbltongChon.setText("Đã chọn " + so + " dịch vụ - Tổng: " + ViewTheme.tien(tong));
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

    private static void chonKhach(JComboBox<String> cmbhop, String ma) {
        for (int i = 0; i < cmbhop.getItemCount(); i++) {
            if (cmbhop.getItemAt(i).startsWith(ma)) {
                cmbhop.setSelectedIndex(i);
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
