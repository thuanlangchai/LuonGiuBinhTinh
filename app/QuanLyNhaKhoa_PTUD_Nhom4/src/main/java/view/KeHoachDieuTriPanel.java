package view;

import view.model.CauHinhTrang;
import view.model.HangDT;
import view.model.HoSo;
import view.model.KeHoachView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Window;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import entity.LichHen;
import service.impl.LichHen_Service;
import view.model.HoaDonView;

public class KeHoachDieuTriPanel extends JPanel {

    private static final DateTimeFormatter NGAY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final JLabel lblTen = new JLabel();
    private final JLabel lblLienHe = new JLabel();
    private final JLabel lblBenh = new JLabel();
    private final JLabel lblTong = new JLabel();
    private final JLabel lblKeHoach = new JLabel();
    private final JLabel lblTongCong = new JLabel();
    private final JLabel lblChietKhau = new JLabel();
    private final JLabel lblThanhTien = new JLabel();
    private final JLabel lblDaChiTra = new JLabel();
    private final JLabel lblConNo = new JLabel();
    private final DefaultTableModel model = ViewTheme.taoModel(
            new String[]{"Ngày", "Răng", "Dịch vụ", "Số lượng", "Đơn giá", "Thành tiền", "Trạng thái", "Mã hóa đơn"},
            true, true);
    private final JTable tblBang = new JTable(model);
    private final Map<String, HoSo> hoSoTheoMa = new LinkedHashMap<String, HoSo>();
    private final JPanel pnlavatar = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(ViewTheme.XANH);
            g.fillOval(24, 8, 88, 88);
            g.setColor(Color.WHITE);
            g.setFont(ViewTheme.FONT_TRANG);
            g.drawString(kyHieu, kyHieu.length() > 1 ? 48 : 56, 60);
        }
    };

    private HoaDonPanel hoaDon;
    private HoSo hienTai;
    private KeHoachView keHoachHienTai;
    private String maKeHoach;
    private String kyHieu = "KH";
    private JTextField txtDonGia;
    private JComboBox<String> cmbDichVu;
    private HopThoai hopThoai;
    private JTextField txtSoLuong;

    public KeHoachDieuTriPanel() {
        this(null);
    }

    public KeHoachDieuTriPanel(String maKeHoach) {
        setLayout(new BorderLayout());
        setBackground(ViewTheme.TRANG);
        setBorder(new EmptyBorder(12, 12, 12, 12));
        setPreferredSize(new Dimension(1180, 680));

        pnlavatar.setPreferredSize(new Dimension(140, 110));
        pnlavatar.setOpaque(false);
        lblTen.setFont(ViewTheme.FONT_TRANG);
        lblLienHe.setFont(ViewTheme.FONT);
        lblBenh.setFont(ViewTheme.FONT);
        lblKeHoach.setFont(ViewTheme.FONT);
        lblKeHoach.setForeground(ViewTheme.CHU_NHAT);

        JPanel pnlTrai = new JPanel(new BorderLayout(0, 8));
        pnlTrai.setBackground(ViewTheme.TRANG);
        pnlTrai.setBorder(new EmptyBorder(8, 8, 8, 8));
        JPanel pnlThongTin = new JPanel(new BorderLayout(0, 8));
        pnlThongTin.setOpaque(false);
        pnlThongTin.add(lblTen, BorderLayout.NORTH);
        pnlThongTin.add(lblLienHe, BorderLayout.CENTER);
        pnlThongTin.add(lblBenh, BorderLayout.SOUTH);
        pnlTrai.add(pnlavatar, BorderLayout.NORTH);
        pnlTrai.add(pnlThongTin, BorderLayout.CENTER);
        pnlTrai.add(ViewTheme.tieuDeMuc("Tóm tắt bệnh lý"), BorderLayout.SOUTH);

        ViewTheme.trangTriBang(tblBang);
        ViewTheme.ganThaoTac(tblBang, this::xuLyHang);
        JScrollPane scrCuon = new JScrollPane(tblBang);
        scrCuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));

        JButton btnNutThemDv = new JButton("+ Thêm dịch vụ");
        JButton btnNutHoaDon = new JButton("Lập hóa đơn");
        JButton btnNutThanhToan = new JButton("Thu tiền");
        JButton btnNutLenLich = new JButton("Lên lịch hẹn");
        ViewTheme.nutXanh(btnNutThemDv);
        ViewTheme.nutCam(btnNutHoaDon);
        ViewTheme.nutChinh(btnNutLenLich);
        ViewTheme.nutChinh(btnNutThanhToan);
        btnNutThemDv.addActionListener(e -> moThemDichVu());
        btnNutHoaDon.addActionListener(e -> moLapHoaDon());
        btnNutThanhToan.addActionListener(e -> moThanhToan());
        btnNutLenLich.addActionListener(e -> moLenLichHen());

        JPanel pnlnutTrai = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pnlnutTrai.setOpaque(false);
        pnlnutTrai.add(btnNutThemDv);
        pnlnutTrai.add(btnNutHoaDon);
        pnlnutTrai.add(btnNutThanhToan);
        pnlnutTrai.add(btnNutLenLich);
        
        
        JPanel pnlThongKe = new JPanel(new FlowLayout(FlowLayout.RIGHT, 18, 0));
        pnlThongKe.setOpaque(false);
        pnlThongKe.add(oSo(lblTongCong, "Tổng cộng", ViewTheme.CHU));
        pnlThongKe.add(oSo(lblChietKhau, "Chiết khấu", ViewTheme.CHU_NHAT));
        pnlThongKe.add(oSo(lblThanhTien, "Thành tiền", ViewTheme.CHU));
        pnlThongKe.add(oSo(lblDaChiTra, "Đã chi trả", new Color(40, 150, 70)));
        pnlThongKe.add(oSo(lblConNo, "Chưa thanh toán", ViewTheme.DO));

        JPanel pnlThanhCongCu = new JPanel(new BorderLayout());
        pnlThanhCongCu.setOpaque(false);
        pnlThanhCongCu.add(pnlnutTrai, BorderLayout.WEST);
        pnlThanhCongCu.add(pnlThongKe, BorderLayout.EAST);

        JPanel pnlDauPhai = new JPanel(new BorderLayout(0, 8));
        pnlDauPhai.setOpaque(false);
        pnlDauPhai.add(ViewTheme.tieuDeTrang("Chi tiết kế hoạch điều trị"), BorderLayout.NORTH);
        pnlDauPhai.add(lblKeHoach, BorderLayout.CENTER);
        pnlDauPhai.add(pnlThanhCongCu, BorderLayout.SOUTH);

        lblTong.setFont(ViewTheme.FONT_SO);
        lblTong.setForeground(ViewTheme.XANH_DAM);
        lblTong.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel pnlPhai = new JPanel(new BorderLayout(0, 8));
        pnlPhai.setBackground(ViewTheme.TRANG);
        pnlPhai.setBorder(new EmptyBorder(8, 8, 8, 8));
        pnlPhai.add(pnlDauPhai, BorderLayout.NORTH);
        pnlPhai.add(scrCuon, BorderLayout.CENTER);
        pnlPhai.add(lblTong, BorderLayout.SOUTH);

        JSplitPane tach = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlTrai, pnlPhai);
        tach.setDividerLocation(280);
        tach.setResizeWeight(0);
        add(tach, BorderLayout.CENTER);
        HoaDonDuLieu.khiDoi(this::lamMoiBang);
        KeHoachDuLieu.khiDoi(this::lamMoiBang);

        if (maKeHoach != null && !maKeHoach.isEmpty()) {
            napMaKeHoach(maKeHoach);
        } else {
            lblTen.setText("Chọn kế hoạch để xem");
            lblLienHe.setText("");
            lblBenh.setText("");
            lblKeHoach.setText("Mở từ danh sách kế hoạch điều trị");
            veBang();
        }
    }

    public void ganHoaDon(HoaDonPanel hoaDon) {
        this.hoaDon = hoaDon;
    }

    public void lamMoiBang() {
        if (maKeHoach != null) {
            keHoachHienTai = KeHoachDuLieu.theoMa(maKeHoach);
        }
        veBang();
    }

    public List<HangDT> hangChuaXuat(String maKh) {
        return KeHoachDuLieu.hangChuaXuat(maKh);
    }

    public void danhDauHoaDon(List<HangDT> ds, String maHd) {
        KeHoachDuLieu.danhDauHoaDon(ds, maHd);
        veBang();
    }

    public void boDanhDauHoaDon(String maHd) {
        KeHoachDuLieu.boDanhDauHoaDon(maHd);
        veBang();
    }

    public void napMaKeHoach(String ma) {
        this.maKeHoach = ma;
        this.keHoachHienTai = KeHoachDuLieu.theoMa(ma);
        if (keHoachHienTai == null) {
            lblTen.setText("Không tìm thấy kế hoạch");
            lblLienHe.setText("");
            lblBenh.setText("");
            lblKeHoach.setText("Mã: " + ma);
            hienTai = null;
            veBang();
            return;
        }
        HoSo hs = hoSoTheoMa.get(keHoachHienTai.maKH);
        if (hs == null) {
            hs = new HoSo(keHoachHienTai.maKH, keHoachHienTai.tenKh, "", "", "", keHoachHienTai.lyDoDieuTri);
            hoSoTheoMa.put(keHoachHienTai.maKH, hs);
        } else {
            hs.capNhatKhach(keHoachHienTai.tenKh, hs.sdt, hs.email, hs.diaChi, keHoachHienTai.lyDoDieuTri);
        }
        hienTai = hs;
        kyHieu = vietTat(keHoachHienTai.tenKh);
        lblTen.setText(keHoachHienTai.tenKh);
        lblLienHe.setText("<html>Mã KH: " + keHoachHienTai.maKH
                + (hs.sdt == null || hs.sdt.isEmpty() ? "" : "<br>" + hs.sdt)
                + (hs.email == null || hs.email.isEmpty() ? "" : "<br>" + hs.email)
                + (hs.diaChi == null || hs.diaChi.isEmpty() ? "" : "<br>" + hs.diaChi)
                + "</html>");
        lblBenh.setText("<html>Lý do điều trị: " + nullSafe(keHoachHienTai.lyDoDieuTri)
                + "<br>Bác sĩ: " + nullSafe(keHoachHienTai.tenBacSi)
                + "<br>Ghi chú: " + nullSafe(keHoachHienTai.ghiChu) + "</html>");
        lblKeHoach.setText("Mã KHĐT: " + keHoachHienTai.maKeHoach
                + "  |  Ngày lập: " + keHoachHienTai.ngayLap
                + "  |  Trạng thái: " + keHoachHienTai.trangThai);
        pnlavatar.repaint();
        veBang();
    }

    public void nap(String ma, String hoTen, String sdt, String email, String diaChi, String lyDo, String dieuTri) {
        HoSo hs = hoSoTheoMa.get(ma);
        if (hs == null) {
            hs = new HoSo(ma, hoTen, sdt, email, diaChi, lyDo);
            hoSoTheoMa.put(ma, hs);
        } else {
            hs.capNhatKhach(hoTen, sdt, email, diaChi, lyDo);
        }
        hienTai = hs;
        List<KeHoachView> ds = KeHoachDuLieu.theoMaKH(ma);
        if (!ds.isEmpty()) {
            napMaKeHoach(ds.get(0).maKeHoach);
            return;
        }
        String[] bacSi = DuLieuMau.dsBacSi();
        String tenBs = bacSi.length > 0 ? bacSi[0] : "";
        KeHoachView moi = KeHoachDuLieu.them(ma, hoTen, homNay(), DuLieuMau.maBacSiTheoTen(tenBs), tenBs,
                lyDo == null || lyDo.isEmpty() ? "Đau răng" : lyDo, "");
        if (dieuTri != null && !dieuTri.isEmpty() && !dieuTri.contains("Thăm khám")) {
            KeHoachDuLieu.themDichVu(moi.maKeHoach,
                    new HangDT(moi.maKeHoach, homNay(), "", dieuTri, 1, giaDichVu(dieuTri), "Mới"));
        }
        napMaKeHoach(moi.maKeHoach);
    }

    private void moThemDichVu() {
        if (keHoachHienTai == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn kế hoạch điều trị.");
            return;
        }
        if (keHoachHienTai.daHuy()) {
            JOptionPane.showMessageDialog(this, "Không thể thêm dịch vụ vào kế hoạch đã hủy.");
            return;
        }
        Window w = SwingUtilities.getWindowAncestor(this);
        hopThoai = new HopThoai(w, "Thêm dịch vụ vào kế hoạch", "+ Thêm");
        JComboBox<String> cmbNgay = hopThoai.chon("Ngày", true, homNay(), "19/10/2026", "27/09/2026");
        cmbNgay.setEditable(true);
        JComboBox<String> cmbRang = hopThoai.chon("Răng", false, tenRang());
        cmbDichVu = hopThoai.chon("Dịch vụ", true, tenDichVu());
        txtSoLuong = hopThoai.chu("Số lượng", "1", true);
        txtDonGia = hopThoai.chu("Đơn giá", ViewTheme.tien(giaDichVu(String.valueOf(cmbDichVu.getSelectedItem()))), true);
        txtSoLuong.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setDonGia();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setDonGia();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        txtDonGia.setEditable(false);
        JComboBox<String> trangThai = hopThoai.chon("Trạng thái", true, "Mới", "Đang điều trị", "Hoàn tất");
        JTextField txtGhiChu = hopThoai.chu("Ghi chú", "", false);
        cmbDichVu.addActionListener(e -> txtDonGia.setText(ViewTheme.tien(giaDichVu(String.valueOf(cmbDichVu.getSelectedItem())))));
        hopThoai.hien(() -> {
            String ngayKH = String.valueOf(cmbNgay.getEditor().getItem()).trim();
            if (ngayKH.isEmpty()) {
                JOptionPane.showMessageDialog(hopThoai, "Vui lòng nhập ngày.");
                return;
            }
            int soLuong = parseSo(String.valueOf(txtSoLuong.getText()), 1);
            long donGiaDv = giaDichVu(String.valueOf(cmbDichVu.getSelectedItem()));
            HangDT hang = new HangDT(
                    maKeHoach,
                    ngayKH,
                    String.valueOf(cmbRang.getSelectedItem()),
                    String.valueOf(cmbDichVu.getSelectedItem()),
                    soLuong,
                    donGiaDv,
                    String.valueOf(trangThai.getSelectedItem()));
            hang.ghiChu = txtGhiChu.getText().trim();
            if (!KeHoachDuLieu.themDichVu(maKeHoach, hang)) {
                JOptionPane.showMessageDialog(hopThoai, "Không thêm được dịch vụ vào kế hoạch.");
                return;
            }
            napMaKeHoach(maKeHoach);
        });
    }

    private void moLapHoaDon() {
        if (hienTai == null || hoaDon == null || keHoachHienTai == null) {
            return;
        }
        if (keHoachHienTai.daHuy()) {
            JOptionPane.showMessageDialog(this, "Kế hoạch đã hủy, không lập hóa đơn.");
            return;
        }
        List<HangDT> chon = hangDangChon();
        List<HangDT> chuaXuat = new ArrayList<HangDT>();
        for (HangDT hang : chon) {
            if (hang.maHoaDon == null || hang.maHoaDon.isEmpty()) {
                chuaXuat.add(hang);
            }
        }
        if (chuaXuat.isEmpty()) {
            for (HangDT hang : keHoachHienTai.chiTiet) {
                if (hang.maHoaDon == null || hang.maHoaDon.isEmpty()) {
                    chuaXuat.add(hang);
                }
            }
        }
        if (chuaXuat.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Các dịch vụ của kế hoạch đã được xuất hóa đơn.\nThêm dịch vụ mới hoặc mở hóa đơn để thu tiền.");
            return;
        }
        hoaDon.moLapHoaDon(hienTai.ma, chuaXuat);
    }

    private void moThanhToan() {
        if (hienTai == null) {
            return;
        }
        List<HoaDonView> no = HoaDonDuLieu.conNoTheoKhach(hienTai.ma);
        if (no.isEmpty()) {
            List<HangDT> chua = hangChuaXuatCuaKeHoach();
            if (!chua.isEmpty()) {
                int lap = JOptionPane.showConfirmDialog(this,
                        "Chưa có hóa đơn còn nợ. Lập hóa đơn trước khi thu tiền?",
                        "Thu tiền", JOptionPane.YES_NO_OPTION);
                if (lap == JOptionPane.YES_OPTION) {
                    moLapHoaDon();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Khách hàng không còn công nợ trên hóa đơn.");
            }
            return;
        }
        HoaDonView hd = no.get(0);
        if (no.size() > 1) {
            String[] nhan = new String[no.size()];
            for (int i = 0; i < no.size(); i++) {
                nhan[i] = no.get(i).nhan();
            }
            String chon = (String) JOptionPane.showInputDialog(this, "Chọn hóa đơn cần thu tiền",
                    "Thu tiền", JOptionPane.QUESTION_MESSAGE, null, nhan, nhan[0]);
            if (chon == null) {
                return;
            }
            for (HoaDonView item : no) {
                if (item.nhan().equals(chon)) {
                    hd = item;
                    break;
                }
            }
        }
        if (hoaDon != null) {
            hoaDon.thuTien(hd);
        }
    }

    private List<HangDT> hangChuaXuatCuaKeHoach() {
        List<HangDT> kq = new ArrayList<>();
        if (keHoachHienTai == null) {
            return kq;
        }
        for (HangDT hang : keHoachHienTai.chiTiet) {
            if (hang.maHoaDon == null || hang.maHoaDon.isEmpty()) {
                kq.add(hang);
            }
        }
        return kq;
    }

    private List<HangDT> hangDangChon() {
        List<HangDT> kq = new ArrayList<HangDT>();
        if (keHoachHienTai == null) {
            return kq;
        }
        for (int i = 0; i < model.getRowCount() && i < keHoachHienTai.chiTiet.size(); i++) {
            if (Boolean.TRUE.equals(model.getValueAt(i, 0))) {
                kq.add(keHoachHienTai.chiTiet.get(i));
            }
        }
        return kq;
    }

    private void xuLyHang(String lenh, int hang) {
        if (keHoachHienTai == null || hang < 0 || hang >= keHoachHienTai.chiTiet.size()) {
            return;
        }
        HangDT dt = keHoachHienTai.chiTiet.get(hang);
        if ("Xóa".equals(lenh)) {
            if (keHoachHienTai.daHuy()) {
                JOptionPane.showMessageDialog(this, "Kế hoạch đã hủy, không thể xóa dịch vụ.");
                return;
            }
            if (dt.maHoaDon != null && !dt.maHoaDon.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Dịch vụ đã xuất hóa đơn " + dt.maHoaDon + ", không thể xóa.");
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "Xóa dịch vụ này khỏi kế hoạch?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                KeHoachDuLieu.xoaDichVu(maKeHoach, dt);
                napMaKeHoach(maKeHoach);
            }
            return;
        }
        if ("Xem".equals(lenh)) {
            JOptionPane.showMessageDialog(this,
                    "Kế hoạch: " + maKeHoach + "\nNgày: " + dt.ngay
                    + "\nRăng: " + dt.rang
                    + "\nDịch vụ: " + dt.dichVu
                    + "\nSố lượng: " + dt.soLuong
                    + "\nĐơn giá: " + ViewTheme.tien(dt.donGia)
                    + "\nThành tiền: " + ViewTheme.tien(dt.thanhTien())
                    + "\nTrạng thái: " + dt.trangThai,
                    "Chi tiết điều trị", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (keHoachHienTai.daHuy()) {
            JOptionPane.showMessageDialog(this, "Kế hoạch đã hủy, không thể sửa dịch vụ.");
            return;
        }
        if (dt.maHoaDon != null && !dt.maHoaDon.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Dịch vụ đã xuất hóa đơn, không thể sửa.");
            return;
        }
        Window w = SwingUtilities.getWindowAncestor(this);
        HopThoai dlg = new HopThoai(w, "Cập nhật điều trị", "Lưu");
        JTextField txtNgay = dlg.chu("Ngày", dt.ngay, true);
        JComboBox<String> cmbRang = dlg.chon("Răng", false, tenRang());
        cmbRang.setSelectedItem(dt.rang);
        JComboBox<String> cmbDichVuCb = dlg.chon("Dịch vụ", true, tenDichVu());
        cmbDichVuCb.setSelectedItem(dt.dichVu);
        JTextField txtSlTf = dlg.chu("Số lượng", String.valueOf(dt.soLuong), true);
        JTextField txtDonGiaTf = dlg.chu("Đơn giá", ViewTheme.tien(dt.donGia), true);
        JComboBox<String> cmbTrangThai = dlg.chon("Trạng thái", true, "Mới", "Đang điều trị", "Hoàn tất");
        cmbTrangThai.setSelectedItem(dt.trangThai);
        dlg.hien(() -> {
            dt.ngay = txtNgay.getText().trim();
            dt.rang = String.valueOf(cmbRang.getSelectedItem());
            dt.dichVu = String.valueOf(cmbDichVuCb.getSelectedItem());
            dt.soLuong = parseSo(String.valueOf(txtSlTf.getText()), 1);
            dt.donGia = parseTien(txtDonGiaTf.getText());
            dt.trangThai = String.valueOf(cmbTrangThai.getSelectedItem());
            KeHoachDuLieu.capNhat(keHoachHienTai);
            veBang();
        });
    }

    private void veBang() {
        model.setRowCount(0);
        if (keHoachHienTai != null) {
            for (HangDT hang : keHoachHienTai.chiTiet) {
                if (maKeHoach != null && hang.maKeHoach != null
                        && !maKeHoach.equals(hang.maKeHoach)) {
                    continue;
                }
                model.addRow(ViewTheme.dongBang(new Object[]{
                    hang.ngay,
                    hang.rang,
                    hang.dichVu,
                    String.valueOf(hang.soLuong),
                    ViewTheme.tien(hang.donGia),
                    ViewTheme.tien(hang.thanhTien()),
                    hang.trangThai,
                    hang.maHoaDon == null || hang.maHoaDon.isEmpty() ? "" : hang.maHoaDon
                }, true, true));
            }
        }
        capNhatTong();
    }

    private void capNhatTong() {
        long tongTien = keHoachHienTai == null ? 0 : keHoachHienTai.tongTien();
        String maKh = hienTai == null ? null : hienTai.ma;
        long daTra = maKh == null ? 0 : HoaDonDuLieu.daThuTheoKhach(maKh);
        long conNoHd = maKh == null ? 0 : HoaDonDuLieu.conNoTheoKhachTong(maKh);
        long chuaXuat = 0;
        for (HangDT hang : hangChuaXuatCuaKeHoach()) {
            chuaXuat += hang.thanhTien();
        }
        long conNo = conNoHd + chuaXuat;
        lblTongCong.setText(ViewTheme.tien(tongTien) + "đ");
        lblChietKhau.setText("0đ");
        lblThanhTien.setText(ViewTheme.tien(tongTien) + "đ");
        lblDaChiTra.setText(ViewTheme.tien(daTra) + "đ");
        lblConNo.setText(ViewTheme.tien(conNo) + "đ");
        lblTong.setText("Tổng: " + ViewTheme.tien(tongTien) + "   Còn nợ HĐ: " + ViewTheme.tien(conNoHd));
    }

    private JPanel oSo(JLabel so, String nhan, Color mau) {
        so.setFont(ViewTheme.FONT_SO);
        so.setForeground(mau);
        JLabel lb = new JLabel(nhan);
        lb.setFont(ViewTheme.FONT_NHO);
        lb.setForeground(ViewTheme.CHU_NHAT);
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(so);
        p.add(Box.createVerticalStrut(2));
        p.add(lb);
        return p;
    }

    private static String[] tenDichVu() {
        CauHinhTrang c = DanhMucDb.lay("dichvu");
        if (c == null || c.duLieu.length == 0) {
            return DuLieuMau.TEN_DV;
        }
        String[] ten = new String[c.duLieu.length];
        for (int i = 0; i < c.duLieu.length; i++) {
            ten[i] = String.valueOf(c.duLieu[i][2]);
        }
        return ten;
    }

    private static String[] tenRang() {
        List<String> ds = new ArrayList<String>();
        ds.add("");
        CauHinhTrang c = DanhMucDb.lay("rang");
        if (c != null) {
            for (Object[] d : c.duLieu) {
                ds.add(String.valueOf(d[0]));
            }
        }
        return ds.toArray(new String[0]);
    }

    private static long giaDichVu(String ten) {
        CauHinhTrang c = DanhMucDb.lay("dichvu");
        if (c != null) {
            for (Object[] d : c.duLieu) {
                if (String.valueOf(d[2]).equals(ten)) {
                    return parseTien(String.valueOf(d[3]));
                }
            }
        }
        return 0;
    }

    private static String homNay() {
        return LocalDate.now().format(NGAY);
    }

    private static String vietTat(String hoTen) {
        if (hoTen == null || hoTen.trim().isEmpty()) {
            return "KH";
        }
        String[] p = hoTen.trim().split("\\s+");
        if (p.length == 1) {
            return p[0].substring(0, Math.min(2, p[0].length())).toUpperCase();
        }
        return (p[p.length - 2].substring(0, 1) + p[p.length - 1].substring(0, 1)).toUpperCase();
    }

    private static long parseTien(String s) {
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

    private static int parseSo(String s, int macDinh) {
        try {
            return Integer.parseInt(String.valueOf(s).replaceAll("[^0-9]", ""));
        } catch (Exception ex) {
            return macDinh;
        }
    }

    private void moLenLichHen() {
        if (keHoachHienTai == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn kế hoạch điều trị để lên lịch.");
            return;
        }
        if (keHoachHienTai.daHuy()) {
            JOptionPane.showMessageDialog(this, "Kế hoạch đã hủy, không thể lên lịch hẹn.");
            return;
        }

        Window w = SwingUtilities.getWindowAncestor(this);
        HopThoai hopThoai = new HopThoai(w, "Lên lịch hẹn cho KH: " + keHoachHienTai.tenKh, "Xác nhận lên lịch");

        String[] dsBs = DuLieuMau.dsBacSi(); 
        
        // Thêm ComboBox chọn Dịch vụ lấy từ danh sách dịch vụ của kế hoạch hoặc danh mục chung
        JComboBox<String> cmbDichVu = hopThoai.chon("Dịch vụ thực hiện", true, tenDichVu());
        JTextField txtBatDau = hopThoai.chu("Thời gian (yyyy-MM-dd HH:mm)", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 09:00", true);
        JComboBox<String> cmbDuKien = hopThoai.chon("Thời lượng (phút)", true, "30", "45", "60", "90", "120");
        JComboBox<String> cmbBs = hopThoai.chon("Bác sĩ", true, dsBs.length > 0 ? dsBs : new String[]{keHoachHienTai.tenBacSi});
        if (keHoachHienTai.tenBacSi != null && !keHoachHienTai.tenBacSi.isEmpty()) {
            cmbBs.setSelectedItem(keHoachHienTai.tenBacSi);
        }
        JTextField txtGhiChu = hopThoai.chu("Ghi chú", "Lên lịch hẹn điều trị", false);

        hopThoai.hien(() -> {
            try {
                LocalDateTime thoiGianBatDau = LocalDateTime.parse(txtBatDau.getText().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                int thoiLuong = Integer.parseInt(String.valueOf(cmbDuKien.getSelectedItem()));
                String tenBacSiChon = String.valueOf(cmbBs.getSelectedItem());
                String dichVuChon = String.valueOf(cmbDichVu.getSelectedItem());
                String maBs = DuLieuMau.maBacSiTheoTen(tenBacSiChon);

                LichHen lhMoi = new LichHen();
                lhMoi.setMaLichHen("LH" + System.currentTimeMillis() % 10000); 
                lhMoi.setKhachHang(entity.Refs.khachHang(keHoachHienTai.maKH));
                lhMoi.setBatDau(thoiGianBatDau);
                lhMoi.setThoiLuongPhut(thoiLuong);
                lhMoi.setBacSi(entity.Refs.nhanVien(maBs));
                lhMoi.setKeHoachDieuTri(entity.Refs.keHoach(keHoachHienTai.maKeHoach));
                
                // Gộp thông tin Dịch vụ và Mã kế hoạch vào ghi chú (hoặc trường dịch vụ nếu entity có hỗ trợ)
                // Cú pháp chuẩn chứa [KHDT:MãKếHoạch] để hệ thống tự động nhận diện liên kết hai chiều
                String ghiChuDayDu = String.format("Dịch vụ: %s - %s [KHDT:%s]", 
                        dichVuChon, 
                        txtGhiChu.getText().trim(), 
                        keHoachHienTai.maKeHoach);
                
                lhMoi.setGhiChu(ghiChuDayDu);
                lhMoi.setTrangThai("Mới");

                LichHen_Service lichHenService = new LichHen_Service();
                if (lichHenService.add(lhMoi) != null) {
                    JOptionPane.showMessageDialog(this, "Đã tạo lịch hẹn và liên kết thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể lưu lịch hẹn.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi định dạng thời gian (Yêu cầu: yyyy-MM-dd HH:mm).\n" + ex.getMessage());
            }
        });
    }
    
    private void setDonGia() {
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        long donGia = giaDichVu(String.valueOf(cmbDichVu.getSelectedItem()));
        long sum = donGia * soLuong;
        txtDonGia.setText(ViewTheme.tien(sum));
    }

    private static String nullSafe(String s) {
        return s == null ? "" : s;
    }
}
