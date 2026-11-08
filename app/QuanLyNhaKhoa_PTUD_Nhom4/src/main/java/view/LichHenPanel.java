package view;

import view.*;
import entity.KhachHang;
import entity.LichHen;
import entity.NhanVien;
import entity.Refs;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import service.impl.DanhMuc_Service;
import service.impl.KhachHang_Service;
import service.impl.LichHen_Service;
import view.model.CuocHen;

public class LichHenPanel extends JPanel {

    private static final int CAO_O = 36;
    private static final int pnlGio_DAU = 7;
    private static final int SO_O = 24;
    private static final DateTimeFormatter F_pnlGio = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter F_NGAY_pnlGio = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String[] THU = {"thứ hai", "thứ ba", "thứ tư", "thứ năm", "thứ sáu", "thứ bảy", "chủ nhật"};
    private static final String[] CHE_DO = {"ngay", "2ngay", "tuan", "thang", "kanban", "list"};
    private static final String[] NHAN_CHE_DO = {"Ngày", "2 ngày", "Tuần", "Tháng", "Kanban", "List"};

    private LocalDate ngay = LocalDate.of(2026, 9, 28);
    private boolean daNeoNgay;
    private String cheDo = "tuan";
    private final List<CuocHen> ds = new ArrayList<CuocHen>();
    private final JPanel pnlThan = new JPanel(new BorderLayout());
    private final JLabel lblTieuDeNgay = new JLabel("", SwingConstants.CENTER);
    private final JLabel[] lblDem = new JLabel[6];
    private final JTextField txtTimBacSi = new JTextField(18);
    private final JComboBox<String> cmbTrangThai = new JComboBox<String>(new String[]{"Tất cả", "Mới", "Xác nhận",
        "Đang đợi", "Đang điều trị", "Hoàn tất", "Khách ra về", "Hủy bỏ"});
    private final JButton[] btnNutCheDo = new JButton[CHE_DO.length];
    private final LichHen_Service lichHenService = new LichHen_Service();
    private final KhachHang_Service khachHangService = new KhachHang_Service();
    private final DanhMuc_Service danhMucService = new DanhMuc_Service();

    public LichHenPanel() {
        setLayout(new BorderLayout(0, 8));
        setBackground(ViewTheme.TRANG);
        setBorder(new EmptyBorder(8, 10, 8, 10));
        setPreferredSize(new Dimension(1180, 820));
        add(taoThanh(), BorderLayout.NORTH);
        pnlThan.setOpaque(false);
        add(pnlThan, BorderLayout.CENTER);
        txtTimBacSi.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ve();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                ve();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                ve();
            }
        });
        cmbTrangThai.addActionListener(e -> ve());
        taiLai();
    }

    private void taiLai() {
        ds.clear();
        try {
            if (!daNeoNgay) {
                LocalDateTime somNhat = lichHenService.batDauSomNhat();
                if (somNhat != null) {
                    ngay = somNhat.toLocalDate();
                }
                daNeoNgay = true;
            }
            for (LichHen lh : lichHenService.findTheoKhoang(dauKhoang().atStartOfDay(), cuoiKhoang().atStartOfDay())) {
                ds.add(mapCuocHen(lh));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Không tải được lịch hẹn.\n" + ex.getMessage());
        }
        ve();
    }

    private LocalDate dauKhoang() {
        if ("thang".equals(cheDo)) {
            return ngay.withDayOfMonth(1);
        }
        if ("tuan".equals(cheDo)) {
            return ngay.with(DayOfWeek.MONDAY);
        }
        return ngay;
    }

    private LocalDate cuoiKhoang() {
        if ("thang".equals(cheDo)) {
            return ngay.withDayOfMonth(1).plusMonths(1);
        }
        if ("tuan".equals(cheDo)) {
            return ngay.with(DayOfWeek.MONDAY).plusDays(7);
        }
        if ("2ngay".equals(cheDo)) {
            return ngay.plusDays(2);
        }
        return ngay.plusDays(1);
    }

    private CuocHen mapCuocHen(LichHen lh) {
        return new CuocHen(lh.getMaLichHen(), Refs.maKh(lh.getKhachHang()), Refs.tenKh(lh.getKhachHang()), lh.getBatDau(),
                lh.getThoiLuongPhut(), lh.getBacSi() == null ? "" : Refs.tenNv(lh.getBacSi()),
                lh.getPhuTa() == null ? "" : Refs.tenNv(lh.getPhuTa()),
                lh.getGhiChu() == null ? "" : lh.getGhiChu(), lh.getTrangThai());
    }

    private String[] dsKhachCombo() {
        List<String> kq = new ArrayList<>();
        for (KhachHang kh : khachHangService.findAllKhachHang()) {
            kq.add(kh.getMaKH() + " - " + kh.getHoTen());
        }
        return kq.isEmpty() ? new String[]{"0001 - "} : kq.toArray(new String[0]);
    }

    private String[] dsBacSiCombo() {
        List<String> kq = new ArrayList<>();
        for (NhanVien nv : danhMucService.findAllNhanVien()) {
            kq.add(nv.getHoTen());
        }
        return kq.isEmpty() ? DuLieuMau.BAC_SI : kq.toArray(new String[0]);
    }

    private String maBacSiTheoTen(String ten) {
        for (NhanVien nv : danhMucService.findAllNhanVien()) {
            if (ten != null && ten.equals(nv.getHoTen())) {
                return nv.getMaNhanVien();
            }
        }
        return null;
    }

    private String maLichMoi() {
        return String.format("LH%02d", lichHenService.nextSo() + 1);
    }

    private JPanel taoThanh() {
        JPanel pnlGoc = new JPanel(new BorderLayout(0, 8));
        pnlGoc.setOpaque(false);

        JPanel pnlLoc = new JPanel(new BorderLayout());
        pnlLoc.setOpaque(false);
        JPanel pnlTrai = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnlTrai.setOpaque(false);
        String[] tenDem = {"Tổng cộng", "Đang chờ", "Đang xử lý", "Hoàn thành", "Huỷ", "Chờ lâu"};
        for (int i = 0; i < tenDem.length; i++) {
            lblDem[i] = new JLabel(tenDem[i] + " 0");
            lblDem[i].setFont(ViewTheme.FONT_DAM);
            pnlTrai.add(lblDem[i]);
        }
        ViewTheme.vienNhap(txtTimBacSi);
        txtTimBacSi.setPreferredSize(new Dimension(220, 32));
        JLabel lblGoi = new JLabel("Nhân viên");
        lblGoi.setForeground(ViewTheme.CHU_NHAT);
        pnlTrai.add(lblGoi);
        pnlTrai.add(txtTimBacSi);
        ViewTheme.vienHop(cmbTrangThai);
        pnlTrai.add(cmbTrangThai);
        pnlLoc.add(pnlTrai, BorderLayout.CENTER);
        JPanel huy = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        huy.setOpaque(false);
        pnlLoc.add(huy, BorderLayout.EAST);

        JPanel pnlDieuHuong = new JPanel(new BorderLayout());
        pnlDieuHuong.setOpaque(false);
        JPanel pnlNutNgay = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        pnlNutNgay.setOpaque(false);
        JButton btnTruoc = new JButton("‹");
        JButton btnSau = new JButton("›");
        JButton btnHomNay = new JButton("Hôm nay");
        ViewTheme.nutXam(btnTruoc);
        ViewTheme.nutXam(btnSau);
        ViewTheme.nutXam(btnHomNay);
        btnTruoc.addActionListener(e -> doiNgay(-1));
        btnSau.addActionListener(e -> doiNgay(1));
        btnHomNay.addActionListener(e -> {
            ngay = LocalDate.now();
            daNeoNgay = true;
            taiLai();
        });
        pnlNutNgay.add(btnTruoc);
        pnlNutNgay.add(btnSau);
        pnlNutNgay.add(btnHomNay);
        lblTieuDeNgay.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTieuDeNgay.setForeground(ViewTheme.XANH_DAM);
        JPanel pnlGiua = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 4));
        pnlGiua.setOpaque(false);
        pnlGiua.add(lblTieuDeNgay);

        JPanel pnlChe = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
        pnlChe.setOpaque(false);
        for (int i = 0; i < CHE_DO.length; i++) {
            final String ma = CHE_DO[i];
            btnNutCheDo[i] = new JButton(NHAN_CHE_DO[i]);
            ViewTheme.nutXam(btnNutCheDo[i]);
            btnNutCheDo[i].addActionListener(e -> {
                cheDo = ma;
                taiLai();
            });
            pnlChe.add(btnNutCheDo[i]);
        }
        pnlDieuHuong.add(pnlNutNgay, BorderLayout.WEST);
        pnlDieuHuong.add(pnlGiua, BorderLayout.CENTER);
        pnlDieuHuong.add(pnlChe, BorderLayout.EAST);

        pnlGoc.add(pnlLoc, BorderLayout.NORTH);
        pnlGoc.add(pnlDieuHuong, BorderLayout.SOUTH);
        return pnlGoc;
    }

    private void doiNgay(int buoc) {
        if ("thang".equals(cheDo)) {
            ngay = ngay.plusMonths(buoc);
        } else if ("tuan".equals(cheDo)) {
            ngay = ngay.plusWeeks(buoc);
        } else if ("2ngay".equals(cheDo)) {
            ngay = ngay.plusDays(2L * buoc);
        } else {
            ngay = ngay.plusDays(buoc);
        }
        daNeoNgay = true;
        taiLai();
    }

    private void ve() {
        capNhatNut();
        capNhatDem();
        lblTieuDeNgay.setText(chuNgay());
        pnlThan.removeAll();
        if ("kanban".equals(cheDo)) {
            pnlThan.add(pnlVeKanban(), BorderLayout.CENTER);
        } else if ("list".equals(cheDo)) {
            pnlThan.add(scrVeBang(), BorderLayout.CENTER);
        } else if ("thang".equals(cheDo)) {
            pnlThan.add(pnlVeThang(), BorderLayout.CENTER);
        } else {
            int so = "tuan".equals(cheDo) ? 7 : ("2ngay".equals(cheDo) ? 2 : 1);
            pnlThan.add(scrVeCot(so), BorderLayout.CENTER);
        }
        pnlThan.revalidate();
        pnlThan.repaint();
    }

    private void capNhatNut() {
        for (int i = 0; i < btnNutCheDo.length; i++) {
            if (CHE_DO[i].equals(cheDo)) {
                btnNutCheDo[i].setBackground(ViewTheme.XANH);
                btnNutCheDo[i].setForeground(Color.WHITE);
                btnNutCheDo[i].putClientProperty("nenpnlGoc", ViewTheme.XANH);
            } else {
                Color xam = new Color(226, 230, 234);
                btnNutCheDo[i].setBackground(xam);
                btnNutCheDo[i].setForeground(ViewTheme.CHU);
                btnNutCheDo[i].putClientProperty("nenpnlGoc", xam);
            }
        }
    }

    private String chuNgay() {
        if ("thang".equals(cheDo)) {
            return "tháng " + ngay.getMonthValue() + " " + ngay.getYear();
        }
        if ("tuan".equals(cheDo)) {
            LocalDate dau = ngay.with(DayOfWeek.MONDAY);
            LocalDate cuoi = dau.plusDays(6);
            return "tuần " + dau.getDayOfMonth() + " - " + cuoi.getDayOfMonth() + " Th"
                    + String.format("%02d", cuoi.getMonthValue()) + " " + cuoi.getYear();
        }
        if ("2ngay".equals(cheDo)) {
            LocalDate sau = ngay.plusDays(1);
            return ngay.getDayOfMonth() + " - " + sau.getDayOfMonth() + " Th"
                    + String.format("%02d", sau.getMonthValue()) + " " + sau.getYear();
        }
        return THU[ngay.getDayOfWeek().getValue() - 1] + ", " + ngay.getDayOfMonth() + " Th"
                + String.format("%02d", ngay.getMonthValue()) + " " + ngay.getYear();
    }

    private List<CuocHen> dangXem() {
        List<CuocHen> kq = new ArrayList<CuocHen>();
        String tu = txtTimBacSi.getText().trim().toLowerCase();
        String tt = String.valueOf(cmbTrangThai.getSelectedItem());
        for (CuocHen c : ds) {
            if (!trongKhoang(c.batDau.toLocalDate())) {
                continue;
            }
            if (!"Tất cả".equals(tt) && !tt.equals(c.trangThai)) {
                continue;
            }
            if ("Tất cả".equals(tt) && "Hủy bỏ".equals(c.trangThai)) {
                continue;
            }
            if (!tu.isEmpty() && !c.bacSi.toLowerCase().contains(tu) && !c.khach.toLowerCase().contains(tu)) {
                continue;
            }
            kq.add(c);
        }
        return kq;
    }

    private boolean trongKhoang(LocalDate d) {
        if ("thang".equals(cheDo)) {
            return d.getMonthValue() == ngay.getMonthValue() && d.getYear() == ngay.getYear();
        }
        if ("tuan".equals(cheDo)) {
            LocalDate dau = ngay.with(DayOfWeek.MONDAY);
            return !d.isBefore(dau) && d.isBefore(dau.plusDays(7));
        }
        if ("2ngay".equals(cheDo)) {
            return !d.isBefore(ngay) && d.isBefore(ngay.plusDays(2));
        }
        return d.equals(ngay);
    }

    private void capNhatDem() {
        int tong = 0;
        int cho = 0;
        int xuLy = 0;
        int xong = 0;
        int huy = 0;
        for (CuocHen c : ds) {
            if (!c.batDau.toLocalDate()
                    .equals("thang".equals(cheDo) || "tuan".equals(cheDo) || "2ngay".equals(cheDo)
                            ? c.batDau.toLocalDate()
                            : ngay)) {
                if (!trongKhoang(c.batDau.toLocalDate())) {
                    continue;
                }
            } else if (!trongKhoang(c.batDau.toLocalDate())) {
                continue;
            }
            tong++;
            if ("Đang đợi".equals(c.trangThai)) {
                cho++;
            } else if ("Đang điều trị".equals(c.trangThai)) {
                xuLy++;
            } else if ("Hoàn tất".equals(c.trangThai) || "Khách ra về".equals(c.trangThai)) {
                xong++;
            } else if ("Hủy bỏ".equals(c.trangThai)) {
                huy++;
            }
        }
        lblDem[0].setText("Tổng cộng " + tong);
        lblDem[1].setText("Đang chờ " + cho);
        lblDem[2].setText("Đang xử lý " + xuLy);
        lblDem[3].setText("Hoàn thành " + xong);
        lblDem[4].setText("Huỷ " + huy);
        lblDem[5].setText("Chờ lâu " + cho);
    }

    private JScrollPane scrVeCot(int soNgay) {
        LocalDate dau = "tuan".equals(cheDo) ? ngay.with(DayOfWeek.MONDAY) : ngay;
        JPanel pnlKhung = new JPanel(new BorderLayout());
        pnlKhung.setBackground(ViewTheme.TRANG);
        JPanel dauBang = new JPanel(new BorderLayout());
        dauBang.setBackground(new Color(248, 250, 252));
        dauBang.setPreferredSize(new Dimension(0, 36));
        JPanel tenCot = new JPanel(new GridLayout(1, soNgay));
        tenCot.setOpaque(false);
        for (int i = 0; i < soNgay; i++) {
            LocalDate d = dau.plusDays(i);
            String chu = soNgay == 1 ? "Lê Trần Quang Thanh"
                    : (d.getDayOfMonth() + "/" + d.getMonthValue() + " " + THU[d.getDayOfWeek().getValue() - 1]);
            JLabel lbl = new JLabel(chu, SwingConstants.CENTER);
            lbl.setFont(ViewTheme.FONT_DAM);
            tenCot.add(lbl);
        }
        JPanel pnlLe = new JPanel();
        pnlLe.setPreferredSize(new Dimension(68, 36));
        pnlLe.setOpaque(false);
        dauBang.add(pnlLe, BorderLayout.WEST);
        dauBang.add(tenCot, BorderLayout.CENTER);

        JPanel pnlGio = new JPanel(null);
        pnlGio.setPreferredSize(new Dimension(68, SO_O * CAO_O));
        pnlGio.setBackground(ViewTheme.TRANG);
        for (int i = 0; i < SO_O; i++) {
            int phut = (pnlGio_DAU * 60) + i * 30;
            JLabel lbl = new JLabel(String.format("%02d:%02d", phut / 60, phut % 60));
            lbl.setForeground(ViewTheme.CHU_NHAT);
            lbl.setBounds(6, i * CAO_O, 58, CAO_O);
            pnlGio.add(lbl);
        }

        JPanel pnlCot = new JPanel(new GridLayout(1, soNgay));
        pnlCot.setBackground(ViewTheme.TRANG);
        List<CuocHen> hien = dangXem();
        for (int i = 0; i < soNgay; i++) {
            pnlCot.add(pnlCotNgay(dau.plusDays(i), hien));
        }
        JPanel pnlThanLuoi = new JPanel(new BorderLayout());
        pnlThanLuoi.setBackground(ViewTheme.TRANG);
        pnlThanLuoi.add(pnlGio, BorderLayout.WEST);
        pnlThanLuoi.add(pnlCot, BorderLayout.CENTER);
        pnlKhung.add(dauBang, BorderLayout.NORTH);
        pnlKhung.add(pnlThanLuoi, BorderLayout.CENTER);
        JScrollPane scrCuon = new JScrollPane(pnlKhung);
        scrCuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        scrCuon.getViewport().setBackground(ViewTheme.TRANG);
        scrCuon.getVerticalScrollBar().setUnitIncrement(18);
        return scrCuon;
    }

    private JPanel pnlCotNgay(LocalDate d, List<CuocHen> hien) {

        JPanel pnlCot = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.setColor(new Color(236, 239, 241));

                for (int i = 0; i <= SO_O; i++) {
                    g.drawLine(0, i * CAO_O, getWidth(), i * CAO_O);
                }
            }
        };
        pnlCot.setBackground(ViewTheme.TRANG);
        pnlCot.setPreferredSize(new Dimension(180, SO_O * CAO_O));

        pnlCot.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getSource() != pnlCot) {
                    return;
                }
                int o = Math.max(0, Math.min(SO_O - 1, e.getY() / CAO_O));
                int phut = pnlGio_DAU * 60 + o * 30;
                LocalDateTime luc = LocalDateTime.of(d, LocalTime.of(phut / 60, phut % 60));
                moForm(null, luc);
            }
        });

        for (CuocHen c : hien) {

            if (!c.batDau.toLocalDate().equals(d)) {
                continue;
            }
            int phut = c.batDau.getHour() * 60 + c.batDau.getMinute() - pnlGio_DAU * 60;
            if (phut < 0 || phut >= SO_O * 30) {
                continue;
            }
            int y = phut * CAO_O / 30;

            int cao = Math.max(CAO_O - 4, c.phut * CAO_O / 30 - 4);
            JPanel pnlThe = new JPanel(new BorderLayout());
            if (c.ghiChu != null && c.ghiChu.contains("[KHẨN CẤP]")) {
                pnlThe.setBackground(new Color(254, 226, 226));
                pnlThe.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(239, 68, 68)),
                        BorderFactory.createLineBorder(new Color(248, 113, 113), 1, true)));
            } else {
                pnlThe.setBackground(nenTrangThai(c.trangThai));
                pnlThe.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(vienTrangThai(c.trangThai)), new EmptyBorder(5, 7, 5, 7)));
            }

            JPanel pnlNoiDung = new JPanel();
            pnlNoiDung.setOpaque(false);
            pnlNoiDung.setLayout(new BoxLayout(pnlNoiDung, BoxLayout.Y_AXIS));
            JLabel lblThoiGian = new JLabel(
                    c.batDau.format(DateTimeFormatter.ofPattern("HH:mm")) + " | " + c.maLichHen);
            lblThoiGian.setFont(ViewTheme.FONT_NHO);
            lblThoiGian.setForeground(new Color(40, 70, 110));

            JLabel lblKhachHang = new JLabel("Khách hàng: " + c.khach);
            lblKhachHang.setFont(new Font("Arial", Font.BOLD, 12));
            JLabel lblBacSi = new JLabel("Bác sĩ: " + c.bacSi);
            lblBacSi.setFont(new Font("Arial", Font.PLAIN, 11));
            JLabel lblNgay = new JLabel("Ngày: " + c.batDau.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            lblNgay.setFont(new Font("Arial", Font.PLAIN, 11));
            pnlNoiDung.add(lblThoiGian);
            pnlNoiDung.add(Box.createVerticalStrut(4));
            pnlNoiDung.add(lblKhachHang);
            pnlNoiDung.add(Box.createVerticalStrut(3));
            pnlNoiDung.add(lblBacSi);
            pnlNoiDung.add(Box.createVerticalStrut(3));
            pnlNoiDung.add(lblNgay);
            pnlThe.add(pnlNoiDung, BorderLayout.CENTER);
            pnlThe.setBounds(6, y + 2, 160, cao);
            pnlThe.putClientProperty("khoi", Boolean.TRUE);
            pnlThe.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        hienThiChiTiet(c);
                    } else {
                        moForm(c, c.batDau);
                    }
                }
            });
            pnlCot.add(pnlThe);
        }

        pnlCot.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                int rong = Math.max(48, pnlCot.getWidth() - 12);

                for (Component c : pnlCot.getComponents()) {

                    if (c instanceof JPanel && Boolean.TRUE.equals(((JPanel) c).getClientProperty("khoi"))) {

                        c.setSize(rong, c.getHeight());
                    }
                }
            }
        });

        return pnlCot;
    }

    private JPanel pnlVeKanban() {
        String[] cot = {"Khách hẹn", "Khách đang chờ", "Khách đang điều trị", "Khách kết thúc điều trị",
            "Khách ra về"};
        Color[] mau = {new Color(176, 184, 191), new Color(240, 180, 60), new Color(80, 170, 90),
            new Color(46, 140, 70), new Color(186, 104, 200)};
        JPanel pnlLuoi = new JPanel(new GridLayout(1, 5, 8, 8));
        pnlLuoi.setOpaque(false);
        pnlLuoi.setBorder(new EmptyBorder(8, 0, 0, 0));
        List<CuocHen> hien = dangXem();
        for (int i = 0; i < cot.length; i++) {
            JPanel pnlThe = new JPanel(new BorderLayout(0, 8));
            pnlThe.setBackground(ViewTheme.TRANG);
            pnlThe.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(4, 1, 1, 1, mau[i]),
                    new EmptyBorder(8, 8, 8, 8)));
            JLabel lblTen = new JLabel(cot[i], SwingConstants.CENTER);
            lblTen.setFont(ViewTheme.FONT_DAM);
            JButton btnThem = new JButton("Thêm lịch hẹn");
            ViewTheme.nutLink(btnThem);
            btnThem.setHorizontalAlignment(SwingConstants.CENTER);
            btnThem.setForeground(ViewTheme.XANH_DAM);
            final int nhom = i;
            btnThem.addActionListener(
                    e -> moForm(null, LocalDateTime.of(ngay, LocalTime.of(8, 0)), trangThaiCot(nhom)));
            JPanel pnlDsThe = new JPanel();
            pnlDsThe.setLayout(new BoxLayout(pnlDsThe, BoxLayout.Y_AXIS));
            pnlDsThe.setOpaque(false);
            for (CuocHen c : hien) {
                if (cotKanban(c.trangThai) != i) {
                    continue;
                }
                if ("Hủy bỏ".equals(c.trangThai)) {
                    continue;
                }
                JPanel pnlCard = new JPanel(new BorderLayout());

                JPanel pnlThongTin = new JPanel();
                pnlThongTin.setLayout(new BoxLayout(pnlThongTin, BoxLayout.Y_AXIS));
                pnlThongTin.setOpaque(false);

                JButton btnXemChiTiet = new JButton("👁");
                btnXemChiTiet.setToolTipText("Xem chi tiết lịch hẹn");
                ViewTheme.nutLink(btnXemChiTiet); // Dùng style nút link hoặc nút nhỏ gọn
                btnXemChiTiet.addActionListener(e -> {
                    // Ngăn chặn sự kiện lan truyền nếu cần và gọi hàm chuyển trang
                    hienThiChiTiet(c);
                });
                pnlCard.add(pnlThongTin, BorderLayout.CENTER);
                pnlCard.add(btnXemChiTiet, BorderLayout.EAST);

                boolean khanCap = c.ghiChu != null && c.ghiChu.contains("[KHẨN CẤP]");

                if (khanCap) {
                    pnlCard.setBackground(new Color(254, 226, 226));
                    pnlCard.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(239, 68, 68), 1, true),
                            new EmptyBorder(6, 8, 6, 8)
                    ));
                } else {
                    pnlCard.setBackground(new Color(248, 250, 252));
                    pnlCard.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ViewTheme.VIEN),
                            new EmptyBorder(8, 8, 8, 8)));
                }

                pnlCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));

                String badgeKanban = khanCap ? "<span style='color: #dc2626; font-weight: bold;'>[KHẨN CẤP]</span><br>" : "";
                String bacSiText = (c.bacSi != null && !c.bacSi.isEmpty()) ? c.bacSi : "Chưa phân công";

                String htmlKanban = String.format(
                        "<html>"
                        + "%s<b style='color: #1e293b; font-size: 11px;'>%s</b><br>"
                        + "<span style='color: #64748b; font-size: 10px;'>🕒 %s &nbsp;•&nbsp; 👨‍⚕️ %s</span>"
                        + "</html>",
                        badgeKanban,
                        c.khach,
                        c.batDau.format(F_pnlGio),
                        bacSiText
                );

                JLabel lbl = new JLabel(htmlKanban);
                pnlCard.add(lbl, BorderLayout.CENTER);
                pnlCard.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            hienThiChiTiet(c);
                        } else {
                            moForm(c, c.batDau);
                        }
                    }
                });
                pnlDsThe.add(pnlCard);
                pnlDsThe.add(Box.createVerticalStrut(6));
            }
            pnlThe.add(lblTen, BorderLayout.NORTH);
            pnlThe.add(new JScrollPane(pnlDsThe), BorderLayout.CENTER);
            pnlThe.add(btnThem, BorderLayout.SOUTH);
            pnlLuoi.add(pnlThe);
        }
        return pnlLuoi;
    }

    private JScrollPane scrVeBang() {
        String[] cot = {"Mã lịch hẹn", "Mã KH", "Khách hàng", "Bắt đầu", "Thời lượng (phút)", "Bác sĩ", "Phụ tá",
            "Ghi chú", "Trạng thái", "Thao tác"};
        DefaultTableModel model = ViewTheme.taoModel(cot, false, false);
        for (CuocHen c : dangXem()) {
            model.addRow(new Object[]{c.maLichHen, c.maKH, c.khach, c.batDau.format(F_NGAY_pnlGio), c.phut, c.bacSi,
                c.phuTa, c.ghiChu, c.trangThai, "👁 Xem"});
        }
        JTable tblBang = new JTable(model);
        ViewTheme.trangTriBang(tblBang);
        tblBang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tblBang.rowAtPoint(e.getPoint());
                int col = tblBang.columnAtPoint(e.getPoint());
                if (e.getClickCount() == 2 && tblBang.getSelectedRow() >= 0) {
                    int i = tblBang.convertRowIndexToModel(tblBang.getSelectedRow());
                    List<CuocHen> hien = dangXem();
                    if (i >= 0 && i < hien.size()) {
//						moForm(hien.get(i), hien.get(i).batDau);
                        hienThiChiTiet(hien.get(i));
                    }
                }
            }
        });
        JScrollPane srcCuon = new JScrollPane(tblBang);
        srcCuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        srcCuon.getViewport().setBackground(ViewTheme.TRANG);
        return srcCuon;
    }

    private JPanel pnlVeThang() {
        JPanel pnlGoc = new JPanel(new BorderLayout(0, 8));
        pnlGoc.setOpaque(false);
        JPanel pnlLuoi = new JPanel(new GridLayout(0, 7, 6, 6));
        pnlLuoi.setOpaque(false);
        String[] nhan = {"T2", "T3", "T4", "T5", "T6", "T7", "CN"};
        for (String n : nhan) {
            JLabel lbl = new JLabel(n, SwingConstants.CENTER);
            lbl.setFont(ViewTheme.FONT_DAM);
            lbl.setForeground(ViewTheme.CHU_NHAT);
            pnlLuoi.add(lbl);
        }
        LocalDate dau = ngay.withDayOfMonth(1);
        int lech = dau.getDayOfWeek().getValue() - 1;
        for (int i = 0; i < lech; i++) {
            pnlLuoi.add(new JLabel());
        }
        int soNgay = ngay.lengthOfMonth();
        for (int i = 1; i <= soNgay; i++) {
            LocalDate d = LocalDate.of(ngay.getYear(), ngay.getMonth(), i);
            int so = 0;
            for (CuocHen c : ds) {
                if (c.batDau.toLocalDate().equals(d) && !"Hủy bỏ".equals(c.trangThai)) {
                    so++;
                }
            }
            JButton btnO = new JButton("<html><center>" + i
                    + (so > 0 ? "<br><font color='#0e84b8'>" + so + " lịch</font>" : "") + "</center></html>");
            if (d.equals(ngay)) {
                ViewTheme.nutChinh(btnO);
            } else {
                ViewTheme.nutXam(btnO);
            }
            btnO.addActionListener(e -> {
                ngay = d;
                cheDo = "ngay";
                ve();
            });
            pnlLuoi.add(btnO);
        }
        pnlGoc.add(pnlLuoi, BorderLayout.NORTH);
        return pnlGoc;
    }

    private void moForm(CuocHen cu, LocalDateTime luc) {
        moForm(cu, luc, cu == null ? "Mới" : cu.trangThai);
    }

    private void moForm(CuocHen cu, LocalDateTime luc, String trangThaiMacDinh) {
        HopThoai hopThoai = new HopThoai(cuaSoCha(), cu == null ? "Thêm lịch hẹn" : "Cập nhật lịch hẹn",
                cu == null ? "Thêm" : "Lưu");

        JComboBox<String> cmbKh = hopThoai.chon("Khách hàng", false, dsKhachCombo());
        JTextField txtGhi = hopThoai.chu("Ghi chú", cu == null ? "" : cu.ghiChu, false);
        JTextField txtBatDau = hopThoai.chu("Bắt đầu", (cu == null ? luc : cu.batDau).format(F_NGAY_pnlGio), true);
        JComboBox<String> cmbDuKien = hopThoai.chon("Thời lượng (phút)", true, "15", "30", "45", "60", "90", "120");
        if (cu != null) {
            cmbDuKien.setSelectedItem(String.valueOf(cu.phut));
        }

        JComboBox<String> cmbLoaiLich = hopThoai.chon("Loại lịch hẹn", false, "Bình thường", "Khẩn cấp");

        // Tự động chọn "Khẩn cấp" nếu lịch hẹn cũ có chứa nhãn [KHẨN CẤP] trong ghi chú
        if (cu != null && cu.ghiChu != null && cu.ghiChu.contains("[KHẨN CẤP]")) {
            cmbLoaiLich.setSelectedItem("Khẩn cấp");
        }

        JComboBox<String> cmbBs = hopThoai.chon("Bác sĩ", true, dsBacSiCombo());
        if (cu != null) {
            cmbBs.setSelectedItem(cu.bacSi);
        }
        JComboBox<String> cmbPhuTa = hopThoai.chon("Phụ tá", false, "", "Nguyễn Khắc Duy", "Hồ Văn Khánh");
        if (cu != null && cu.phuTa != null && !cu.phuTa.isEmpty()) {
            cmbPhuTa.setSelectedItem(cu.phuTa);
        }
        JComboBox<String> cmbTt = hopThoai.chon("Trạng thái", false, "Mới", "Xác nhận", "Đang đợi", "Đang điều trị",
                "Hoàn tất", "Khách ra về", "Hủy bỏ");
        cmbTt.setSelectedItem(trangThaiMacDinh = trangThaiMacDinh); // Sửa tạm biến nếu cần, hoặc giữ nguyên cmbTt.setSelectedItem(trangThaiMacDinh);

        hopThoai.hien(() -> {
            LocalDateTime pnlGio;
            try {
                pnlGio = LocalDateTime.parse(txtBatDau.getText().trim(), F_NGAY_pnlGio);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Thời gian theo dạng yyyy-MM-dd HH:mm");
                return;
            }
            int phut = Integer.parseInt(String.valueOf(cmbDuKien.getSelectedItem()));
            LocalDateTime ketThucMoi = pnlGio.plusMinutes(phut);

            boolean isKhanCap = "Khẩn cấp".equals(cmbLoaiLich.getSelectedItem());

            String ghiChuMoi = txtGhi.getText().trim();
            if (isKhanCap) {
                if (!ghiChuMoi.contains("[KHẨN CẤP]")) {
                    ghiChuMoi = "[KHẨN CẤP] " + ghiChuMoi;
                }
            } else {

                ghiChuMoi = ghiChuMoi.replace("[KHẨN CẤP]", "").trim();
            }

            LocalDateTime nghiBatDau = LocalDateTime.of(pnlGio.toLocalDate(), LocalTime.of(12, 30));
            LocalDateTime nghiKetThuc = LocalDateTime.of(pnlGio.toLocalDate(), LocalTime.of(13, 30));

            boolean trungGioNghi = pnlGio.isBefore(nghiKetThuc) && nghiBatDau.isBefore(ketThucMoi);

            if (trungGioNghi && !isKhanCap) {
                JOptionPane.showMessageDialog(this,
                        "Khung giờ từ 12:30 đến 13:30 là thời gian nghỉ trưa!\n"
                        + "Bạn chỉ có thể đặt lịch trong khung giờ này nếu chọn loại lịch là 'Khẩn cấp'.",
                        "Cảnh báo giờ nghỉ", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String tenKhach = String.valueOf(cmbKh.getSelectedItem());
            String maKH = "0001";
            if (tenKhach.contains(" - ")) {
                String[] tach = tenKhach.split(" - ", 2);
                maKH = tach[0].trim();
                if (tach.length >= 2) {
                    tenKhach = tach[1].trim();
                }
            }

            String maBacSiMoi = maBacSiTheoTen(String.valueOf(cmbBs.getSelectedItem()));

            LichHen entity = cu == null ? new LichHen() : lichHenService.findById(cu.maLichHen);
            if (entity == null) {
                entity = new LichHen();
            }
            if (cu == null) {
                entity.setMaLichHen(maLichMoi());
            } else {
                entity.setMaLichHen(cu.maLichHen);
            }
            entity.setKhachHang(Refs.khachHang(maKH));
            entity.setBatDau(pnlGio);
            entity.setThoiLuongPhut(phut);
            entity.setBacSi(Refs.nhanVien(maBacSiMoi));
            entity.setPhuTa(Refs.nhanVien(maBacSiTheoTen(String.valueOf(cmbPhuTa.getSelectedItem()))));

            // QUAN TRỌNG: Gán biến ghiChuMoi (đã có hoặc không có chữ [KHẨN CẤP]) vào entity
            entity.setGhiChu(ghiChuMoi);

            entity.setTrangThai(String.valueOf(cmbTt.getSelectedItem()));
            try {
                if (cu == null) {
                    if (lichHenService.add(entity) == null) {
                        JOptionPane.showMessageDialog(this, "Không thêm được lịch hẹn.");
                        return;
                    }
                } else {
                    lichHenService.update(entity);
                }
                ngay = pnlGio.toLocalDate();
                taiLai();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi lưu lịch hẹn.\n" + ex.getMessage());
            }
        });
    }

    private Window cuaSoCha() {
        return SwingUtilities.getWindowAncestor(this);
    }

    private int cotKanban(String tt) {
        if ("Đang đợi".equals(tt)) {
            return 1;
        }
        if ("Đang điều trị".equals(tt)) {
            return 2;
        }
        if ("Hoàn tất".equals(tt)) {
            return 3;
        }
        if ("Khách ra về".equals(tt)) {
            return 4;
        }
        return 0;
    }

    private String trangThaiCot(int cot) {
        if (cot == 1) {
            return "Đang đợi";
        }
        if (cot == 2) {
            return "Đang điều trị";
        }
        if (cot == 3) {
            return "Hoàn tất";
        }
        if (cot == 4) {
            return "Khách ra về";
        }
        return "Mới";
    }

    private Color nenTrangThai(String tt) {
        if ("Đang đợi".equals(tt)) {
            return new Color(255, 243, 214);
        }
        if ("Đang điều trị".equals(tt)) {
            return new Color(226, 245, 230);
        }
        if ("Hoàn tất".equals(tt)) {
            return new Color(226, 242, 234);
        }
        if ("Khách ra về".equals(tt)) {
            return new Color(246, 232, 248);
        }
        if ("Hủy bỏ".equals(tt)) {
            return new Color(243, 244, 246);
        }

        return new Color(232, 244, 252);
    }

    private Color vienTrangThai(String tt) {
        if ("Đang đợi".equals(tt)) {
            return new Color(230, 170, 40);
        }
        if ("Đang điều trị".equals(tt)) {
            return new Color(70, 160, 80);
        }
        if ("Hoàn tất".equals(tt)) {
            return new Color(46, 140, 70);
        }
        if ("Khách ra về".equals(tt)) {
            return new Color(176, 90, 190);
        }
        if ("Hủy bỏ".equals(tt)) {
            return new Color(180, 186, 192);
        }
        return ViewTheme.XANH;
    }

    private void hienThiChiTiet(CuocHen c) {
        pnlThan.removeAll();
        ChiTietLichHenPanel chiTietPanel = new ChiTietLichHenPanel(c, () -> {
            // Hành động callback khi bấm nút "Quay lại" từ trang chi tiết
            taiLai();
        });
        pnlThan.add(chiTietPanel, BorderLayout.CENTER);
        pnlThan.revalidate();
        pnlThan.repaint();
    }

}
