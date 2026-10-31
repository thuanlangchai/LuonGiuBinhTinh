package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import view.model.CuocHen;

public class LichHenPanel extends JPanel {

    private static final int CAO_O = 36;
    private static final int GIO_DAU = 7;
    private static final int SO_O = 24;
    private static final DateTimeFormatter F_GIO = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter F_NGAY_GIO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String[] THU = {"thứ hai", "thứ ba", "thứ tư", "thứ năm", "thứ sáu", "thứ bảy", "chủ nhật"};
    private static final String[] CHE_DO = {"ngay", "2ngay", "tuan", "thang", "kanban", "list"};
    private static final String[] NHAN_CHE_DO = {"Ngày", "2 ngày", "Tuần", "Tháng", "Kanban", "List"};

    private LocalDate ngay = LocalDate.of(2026, 9, 28);
    private String cheDo = "ngay";
    private final List<CuocHen> ds = new ArrayList<CuocHen>();
    private final JPanel than = new JPanel(new BorderLayout());
    private final JLabel tieuDeNgay = new JLabel("", SwingConstants.CENTER);
    private final JLabel[] dem = new JLabel[6];
    private final JTextField timBacSi = new JTextField(18);
    private final JComboBox<String> cboTrangThai = new JComboBox<String>(new String[]{
        "Tất cả", "Mới", "Xác nhận", "Đang đợi", "Đang điều trị", "Hoàn tất", "Khách ra về", "Hủy bỏ"
    });
    private final JButton[] nutCheDo = new JButton[CHE_DO.length];

    public LichHenPanel() {
        napMau();
        setLayout(new BorderLayout(0, 8));
        setBackground(ViewTheme.TRANG);
        setBorder(new EmptyBorder(8, 10, 8, 10));
        setPreferredSize(new Dimension(1180, 820));
        add(taoThanh(), BorderLayout.NORTH);
        than.setOpaque(false);
        add(than, BorderLayout.CENTER);
        timBacSi.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                ve();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                ve();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                ve();
            }
        });
        cboTrangThai.addActionListener(e -> ve());
        ve();
    }

    private void napMau() {
        ds.add(new CuocHen("Chỉnh nha", "Lê Trần Quang Thanh", LocalDateTime.of(2026, 9, 28, 8, 0), 60, "Lê Trần Quang Thanh", "", "Đang điều trị"));
        ds.add(new CuocHen("Cạo vôi răng", "Nguyễn Minh Lý", LocalDateTime.of(2026, 9, 28, 9, 30), 30, "Lê Trần Quang Thanh", "Nguyễn Khắc Duy", "Đang đợi"));
        ds.add(new CuocHen("Tái khám", "Nguyễn Minh Thuận", LocalDateTime.of(2026, 9, 28, 14, 0), 30, "Hồ Văn Khánh", "", "Hoàn tất"));
        ds.add(new CuocHen("Thăm khám", "Lê Trần Quang Thanh", LocalDateTime.of(2026, 9, 27, 10, 0), 45, "Lê Trần Quang Thanh", "", "Khách ra về"));
    }

    private JPanel taoThanh() {
        JPanel goc = new JPanel(new BorderLayout(0, 8));
        goc.setOpaque(false);

        JPanel loc = new JPanel(new BorderLayout());
        loc.setOpaque(false);
        JPanel trai = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        trai.setOpaque(false);
        String[] tenDem = {"Tổng cộng", "Đang chờ", "Đang xử lý", "Hoàn thành", "Huỷ", "Chờ lâu"};
        for (int i = 0; i < tenDem.length; i++) {
            dem[i] = new JLabel(tenDem[i] + " 0");
            dem[i].setFont(ViewTheme.FONT_DAM);
            trai.add(dem[i]);
        }
        ViewTheme.vienNhap(timBacSi);
        timBacSi.setPreferredSize(new Dimension(220, 32));
        JLabel goi = new JLabel("Nhân viên");
        goi.setForeground(ViewTheme.CHU_NHAT);
        trai.add(goi);
        trai.add(timBacSi);
        ViewTheme.vienHop(cboTrangThai);
        trai.add(cboTrangThai);
        loc.add(trai, BorderLayout.CENTER);
        JPanel huy = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        huy.setOpaque(false);
        loc.add(huy, BorderLayout.EAST);

        JPanel dieuHuong = new JPanel(new BorderLayout());
        dieuHuong.setOpaque(false);
        JPanel nutNgay = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        nutNgay.setOpaque(false);
        JButton truoc = new JButton("‹");
        JButton sau = new JButton("›");
        JButton homNay = new JButton("Hôm nay");
        ViewTheme.nutXam(truoc);
        ViewTheme.nutXam(sau);
        ViewTheme.nutXam(homNay);
        truoc.addActionListener(e -> doiNgay(-1));
        sau.addActionListener(e -> doiNgay(1));
        homNay.addActionListener(e -> {
            ngay = LocalDate.now();
            ve();
        });
        nutNgay.add(truoc);
        nutNgay.add(sau);
        nutNgay.add(homNay);
        tieuDeNgay.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        tieuDeNgay.setForeground(ViewTheme.XANH_DAM);
        JPanel giua = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 4));
        giua.setOpaque(false);
        giua.add(tieuDeNgay);

        JPanel che = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
        che.setOpaque(false);
        for (int i = 0; i < CHE_DO.length; i++) {
            final String ma = CHE_DO[i];
            nutCheDo[i] = new JButton(NHAN_CHE_DO[i]);
            ViewTheme.nutXam(nutCheDo[i]);
            nutCheDo[i].addActionListener(e -> {
                cheDo = ma;
                ve();
            });
            che.add(nutCheDo[i]);
        }
        dieuHuong.add(nutNgay, BorderLayout.WEST);
        dieuHuong.add(giua, BorderLayout.CENTER);
        dieuHuong.add(che, BorderLayout.EAST);

        goc.add(loc, BorderLayout.NORTH);
        goc.add(dieuHuong, BorderLayout.SOUTH);
        return goc;
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
        ve();
    }

    private void ve() {
        capNhatNut();
        capNhatDem();
        tieuDeNgay.setText(chuNgay());
        than.removeAll();
        if ("kanban".equals(cheDo)) {
            than.add(veKanban(), BorderLayout.CENTER);
        } else if ("list".equals(cheDo)) {
            than.add(veBang(), BorderLayout.CENTER);
        } else if ("thang".equals(cheDo)) {
            than.add(veThang(), BorderLayout.CENTER);
        } else {
            int so = "tuan".equals(cheDo) ? 7 : ("2ngay".equals(cheDo) ? 2 : 1);
            than.add(veCot(so), BorderLayout.CENTER);
        }
        than.revalidate();
        than.repaint();
    }

    private void capNhatNut() {
        for (int i = 0; i < nutCheDo.length; i++) {
            if (CHE_DO[i].equals(cheDo)) {
                nutCheDo[i].setBackground(ViewTheme.XANH);
                nutCheDo[i].setForeground(java.awt.Color.WHITE);
                nutCheDo[i].putClientProperty("nenGoc", ViewTheme.XANH);
            } else {
                java.awt.Color xam = new java.awt.Color(226, 230, 234);
                nutCheDo[i].setBackground(xam);
                nutCheDo[i].setForeground(ViewTheme.CHU);
                nutCheDo[i].putClientProperty("nenGoc", xam);
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
            return "tuần " + dau.getDayOfMonth() + " - " + cuoi.getDayOfMonth() + " Th" + String.format("%02d", cuoi.getMonthValue()) + " " + cuoi.getYear();
        }
        if ("2ngay".equals(cheDo)) {
            LocalDate sau = ngay.plusDays(1);
            return ngay.getDayOfMonth() + " - " + sau.getDayOfMonth() + " Th" + String.format("%02d", sau.getMonthValue()) + " " + sau.getYear();
        }
        return THU[ngay.getDayOfWeek().getValue() - 1] + ", " + ngay.getDayOfMonth() + " Th" + String.format("%02d", ngay.getMonthValue()) + " " + ngay.getYear();
    }

    private List<CuocHen> dangXem() {
        List<CuocHen> kq = new ArrayList<CuocHen>();
        String tu = timBacSi.getText().trim().toLowerCase();
        String tt = String.valueOf(cboTrangThai.getSelectedItem());
        for (CuocHen c : ds) {
            if (!trongKhoang(c.batDau.toLocalDate())) {
                continue;
            }
            if (!"Tất cả".equals(tt) && !tt.equals(c.trangThai)) {
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
            if (!c.batDau.toLocalDate().equals("thang".equals(cheDo) || "tuan".equals(cheDo) || "2ngay".equals(cheDo) ? c.batDau.toLocalDate() : ngay)) {
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
        dem[0].setText("Tổng cộng " + tong);
        dem[1].setText("Đang chờ " + cho);
        dem[2].setText("Đang xử lý " + xuLy);
        dem[3].setText("Hoàn thành " + xong);
        dem[4].setText("Huỷ " + huy);
        dem[5].setText("Chờ lâu " + cho);
    }

    private JScrollPane veCot(int soNgay) {
        LocalDate dau = "tuan".equals(cheDo) ? ngay.with(DayOfWeek.MONDAY) : ngay;
        JPanel khung = new JPanel(new BorderLayout());
        khung.setBackground(ViewTheme.TRANG);
        JPanel dauBang = new JPanel(new BorderLayout());
        dauBang.setBackground(new Color(248, 250, 252));
        dauBang.setPreferredSize(new Dimension(0, 36));
        JPanel tenCot = new JPanel(new GridLayout(1, soNgay));
        tenCot.setOpaque(false);
        for (int i = 0; i < soNgay; i++) {
            LocalDate d = dau.plusDays(i);
            String chu = soNgay == 1 ? "Lê Trần Quang Thanh" : (d.getDayOfMonth() + "/" + d.getMonthValue() + " " + THU[d.getDayOfWeek().getValue() - 1]);
            JLabel lb = new JLabel(chu, SwingConstants.CENTER);
            lb.setFont(ViewTheme.FONT_DAM);
            tenCot.add(lb);
        }
        JPanel le = new JPanel();
        le.setPreferredSize(new Dimension(68, 36));
        le.setOpaque(false);
        dauBang.add(le, BorderLayout.WEST);
        dauBang.add(tenCot, BorderLayout.CENTER);

        JPanel gio = new JPanel(null);
        gio.setPreferredSize(new Dimension(68, SO_O * CAO_O));
        gio.setBackground(ViewTheme.TRANG);
        for (int i = 0; i < SO_O; i++) {
            int phut = (GIO_DAU * 60) + i * 30;
            JLabel lb = new JLabel(String.format("%02d:%02d", phut / 60, phut % 60));
            lb.setForeground(ViewTheme.CHU_NHAT);
            lb.setBounds(6, i * CAO_O, 58, CAO_O);
            gio.add(lb);
        }

        JPanel cot = new JPanel(new GridLayout(1, soNgay));
        cot.setBackground(ViewTheme.TRANG);
        List<CuocHen> hien = dangXem();
        for (int i = 0; i < soNgay; i++) {
            cot.add(cotNgay(dau.plusDays(i), hien));
        }
        JPanel thanLuoi = new JPanel(new BorderLayout());
        thanLuoi.setBackground(ViewTheme.TRANG);
        thanLuoi.add(gio, BorderLayout.WEST);
        thanLuoi.add(cot, BorderLayout.CENTER);
        khung.add(dauBang, BorderLayout.NORTH);
        khung.add(thanLuoi, BorderLayout.CENTER);
        JScrollPane cuon = new JScrollPane(khung);
        cuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        cuon.getViewport().setBackground(ViewTheme.TRANG);
        cuon.getVerticalScrollBar().setUnitIncrement(18);
        return cuon;
    }

    private JPanel cotNgay(LocalDate d, List<CuocHen> hien) {
        JPanel cot = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(236, 239, 241));
                for (int i = 0; i <= SO_O; i++) {
                    g.drawLine(0, i * CAO_O, getWidth(), i * CAO_O);
                }
                g.setColor(new Color(226, 232, 236));
                int batDau = (12 - GIO_DAU) * 2 * CAO_O;
                int cao = 2 * CAO_O;
                for (int y = batDau; y < batDau + cao; y += 6) {
                    g.drawLine(0, y, getWidth(), y + 10);
                }
            }
        };
        cot.setBackground(ViewTheme.TRANG);
        cot.setPreferredSize(new Dimension(180, SO_O * CAO_O));
        cot.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.getComponent() != cot) {
                    return;
                }
                int o = Math.max(0, Math.min(SO_O - 1, e.getY() / CAO_O));
                int phut = GIO_DAU * 60 + o * 30;
                LocalDateTime luc = LocalDateTime.of(d, LocalTime.of(phut / 60, phut % 60));
                moForm(null, luc);
            }
        });
        for (CuocHen c : hien) {
            if (!c.batDau.toLocalDate().equals(d)) {
                continue;
            }
            int phut = c.batDau.getHour() * 60 + c.batDau.getMinute() - GIO_DAU * 60;
            if (phut < 0 || phut >= SO_O * 30) {
                continue;
            }
            int y = phut * CAO_O / 30;
            int cao = Math.max(CAO_O - 4, c.phut * CAO_O / 30 - 4);
            JPanel the = new JPanel(new BorderLayout());
            the.setBackground(nenTrangThai(c.trangThai));
            the.setBorder(BorderFactory.createLineBorder(vienTrangThai(c.trangThai)));
            JLabel lb = new JLabel("<html><b>" + c.batDau.format(F_GIO) + "</b> " + c.congViec + "<br>" + c.khach + "</html>");
            lb.setFont(ViewTheme.FONT_NHO);
            lb.setBorder(new EmptyBorder(2, 6, 2, 6));
            the.add(lb, BorderLayout.CENTER);
            the.setBounds(6, y + 2, 160, cao);
            the.putClientProperty("khoi", Boolean.TRUE);
            the.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    moForm(c, c.batDau);
                }
            });
            cot.add(the);
        }
        cot.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int rong = Math.max(48, cot.getWidth() - 12);
                for (java.awt.Component c : cot.getComponents()) {
                    if (c instanceof JPanel && Boolean.TRUE.equals(((JPanel) c).getClientProperty("khoi"))) {
                        c.setSize(rong, c.getHeight());
                    }
                }
            }
        });
        return cot;
    }

    private JPanel veKanban() {
        String[] cot = {"Khách hẹn", "Khách đang chờ", "Khách đang điều trị", "Khách kết thúc điều trị", "Khách ra về"};
        Color[] mau = {
            new Color(176, 184, 191),
            new Color(240, 180, 60),
            new Color(80, 170, 90),
            new Color(46, 140, 70),
            new Color(186, 104, 200)
        };
        JPanel luoi = new JPanel(new GridLayout(1, 5, 8, 8));
        luoi.setOpaque(false);
        luoi.setBorder(new EmptyBorder(8, 0, 0, 0));
        List<CuocHen> hien = dangXem();
        for (int i = 0; i < cot.length; i++) {
            JPanel the = new JPanel(new BorderLayout(0, 8));
            the.setBackground(ViewTheme.TRANG);
            the.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(4, 1, 1, 1, mau[i]),
                    new EmptyBorder(8, 8, 8, 8)));
            JLabel ten = new JLabel(cot[i], SwingConstants.CENTER);
            ten.setFont(ViewTheme.FONT_DAM);
            JButton them = new JButton("Thêm lịch hẹn");
            ViewTheme.nutLink(them);
            them.setHorizontalAlignment(SwingConstants.CENTER);
            them.setForeground(ViewTheme.XANH_DAM);
            final int nhom = i;
            them.addActionListener(e -> moForm(null, LocalDateTime.of(ngay, LocalTime.of(8, 0)), trangThaiCot(nhom)));
            JPanel dsThe = new JPanel();
            dsThe.setLayout(new javax.swing.BoxLayout(dsThe, javax.swing.BoxLayout.Y_AXIS));
            dsThe.setOpaque(false);
            for (CuocHen c : hien) {
                if (cotKanban(c.trangThai) != i) {
                    continue;
                }
                JPanel card = new JPanel(new BorderLayout());
                card.setBackground(new Color(248, 250, 252));
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(ViewTheme.VIEN),
                        new EmptyBorder(8, 8, 8, 8)));
                card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 72));
                JLabel lb = new JLabel("<html><b>" + c.khach + "</b><br>" + c.batDau.format(F_GIO) + " · " + c.congViec + "</html>");
                card.add(lb);
                card.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent e) {
                        moForm(c, c.batDau);
                    }
                });
                dsThe.add(card);
                dsThe.add(javax.swing.Box.createVerticalStrut(6));
            }
            the.add(ten, BorderLayout.NORTH);
            the.add(new JScrollPane(dsThe), BorderLayout.CENTER);
            the.add(them, BorderLayout.SOUTH);
            luoi.add(the);
        }
        return luoi;
    }

    private JScrollPane veBang() {
        String[] cot = {"Công việc", "Bắt đầu", "Khách hàng", "Người làm chính", "Thời lượng", "Trạng thái"};
        DefaultTableModel model = ViewTheme.taoModel(cot, false, false);
        for (CuocHen c : dangXem()) {
            model.addRow(new Object[]{
                c.congViec,
                c.batDau.format(F_NGAY_GIO),
                c.khach,
                c.bacSi,
                c.phut + " phút",
                c.trangThai
            });
        }
        JTable bang = new JTable(model);
        ViewTheme.trangTriBang(bang);
        bang.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && bang.getSelectedRow() >= 0) {
                    int i = bang.convertRowIndexToModel(bang.getSelectedRow());
                    List<CuocHen> hien = dangXem();
                    if (i >= 0 && i < hien.size()) {
                        moForm(hien.get(i), hien.get(i).batDau);
                    }
                }
            }
        });
        JScrollPane cuon = new JScrollPane(bang);
        cuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        cuon.getViewport().setBackground(ViewTheme.TRANG);
        return cuon;
    }

    private JPanel veThang() {
        JPanel goc = new JPanel(new BorderLayout(0, 8));
        goc.setOpaque(false);
        JPanel luoi = new JPanel(new GridLayout(0, 7, 6, 6));
        luoi.setOpaque(false);
        String[] nhan = {"T2", "T3", "T4", "T5", "T6", "T7", "CN"};
        for (String n : nhan) {
            JLabel lb = new JLabel(n, SwingConstants.CENTER);
            lb.setFont(ViewTheme.FONT_DAM);
            lb.setForeground(ViewTheme.CHU_NHAT);
            luoi.add(lb);
        }
        LocalDate dau = ngay.withDayOfMonth(1);
        int lech = dau.getDayOfWeek().getValue() - 1;
        for (int i = 0; i < lech; i++) {
            luoi.add(new JLabel());
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
            JButton o = new JButton("<html><center>" + i + (so > 0 ? "<br><font color='#0e84b8'>" + so + " lịch</font>" : "") + "</center></html>");
            if (d.equals(ngay)) {
                ViewTheme.nutChinh(o);
            } else {
                ViewTheme.nutXam(o);
            }
            o.addActionListener(e -> {
                ngay = d;
                cheDo = "ngay";
                ve();
            });
            luoi.add(o);
        }
        goc.add(luoi, BorderLayout.NORTH);
        return goc;
    }

    private void moForm(CuocHen cu, LocalDateTime luc) {
        moForm(cu, luc, cu == null ? "Mới" : cu.trangThai);
    }

    private void moForm(CuocHen cu, LocalDateTime luc, String trangThaiMacDinh) {
        HopThoai h = new HopThoai(SwingUtilities(), cu == null ? "Thêm lịch hẹn" : "Cập nhật lịch hẹn",
                cu == null ? "Thêm" : "Lưu");
        JComboBox<String> kh = h.chon("Khách hàng", false, DuLieuMau.KHACH);
        JComboBox<String> dv = h.chonVoiNut("Dịch vụ", false, DuLieuMau.TEN_DV);
        JTextField ghi = h.chu("Ghi chú", cu == null ? "" : cu.ghiChu, false);
        JTextField batDau = h.chu("Bắt đầu", (cu == null ? luc : cu.batDau).format(F_NGAY_GIO), true);
        JComboBox<String> duKien = h.chon("Dự kiến (phút)", true, "15", "30", "45", "60", "90", "120");
        if (cu != null) {
            duKien.setSelectedItem(String.valueOf(cu.phut));
            dv.setSelectedItem(cu.congViec);
        }
        JComboBox<String> bs = h.chon("Người làm chính", true, DuLieuMau.BAC_SI);
        if (cu != null) {
            bs.setSelectedItem(cu.bacSi);
        }
        h.chon("Phụ tá", false, "", "Nguyễn Khắc Duy", "Hồ Văn Khánh");
        JComboBox<String> tt = h.chon("Trạng thái", false, "Mới", "Xác nhận", "Đang đợi", "Đang điều trị", "Hoàn tất", "Khách ra về", "Hủy bỏ");
        tt.setSelectedItem(trangThaiMacDinh);
        h.hien(() -> {
            LocalDateTime gio;
            try {
                gio = LocalDateTime.parse(batDau.getText().trim(), F_NGAY_GIO);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Thời gian theo dạng yyyy-MM-dd HH:mm");
                return;
            }
            int phut = Integer.parseInt(String.valueOf(duKien.getSelectedItem()));
            String tenKhach = String.valueOf(kh.getSelectedItem());
            if (tenKhach.contains(" - ")) {
                String[] tach = tenKhach.split(" - ");
                if (tach.length >= 2) {
                    tenKhach = tach[1].trim();
                }
            }
            if (cu == null) {
                ds.add(new CuocHen(String.valueOf(dv.getSelectedItem()), tenKhach, gio, phut,
                        String.valueOf(bs.getSelectedItem()), ghi.getText().trim(), String.valueOf(tt.getSelectedItem())));
            } else {
                cu.congViec = String.valueOf(dv.getSelectedItem());
                cu.khach = tenKhach;
                cu.batDau = gio;
                cu.phut = phut;
                cu.bacSi = String.valueOf(bs.getSelectedItem());
                cu.ghiChu = ghi.getText().trim();
                cu.trangThai = String.valueOf(tt.getSelectedItem());
                ngay = gio.toLocalDate();
            }
            ve();
        });
    }

    private java.awt.Window SwingUtilities() {
        return javax.swing.SwingUtilities.getWindowAncestor(this);
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

}
