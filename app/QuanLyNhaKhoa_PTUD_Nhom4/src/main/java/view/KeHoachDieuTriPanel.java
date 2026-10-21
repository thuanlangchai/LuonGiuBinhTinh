package view;

import entity.HangDT;
import entity.HoSo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Window;
import java.time.LocalDate;
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

public class KeHoachDieuTriPanel extends JPanel {

    private static final DateTimeFormatter NGAY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final JLabel ten = new JLabel();
    private final JLabel lienHe = new JLabel();
    private final JLabel benh = new JLabel();
    private final JLabel tong = new JLabel();
    private final JLabel lbTongCong = new JLabel();
    private final JLabel lbChietKhau = new JLabel();
    private final JLabel lbThanhTien = new JLabel();
    private final JLabel lbDaChiTra = new JLabel();
    private final JLabel lbConNo = new JLabel();
    private final DefaultTableModel model = ViewTheme.taoModel(
            new String[]{"Ngày", "Răng", "Dịch vụ", "Số lượng", "Đơn giá", "Thành tiền", "Trạng thái", "Kế hoạch", "Hóa đơn"},
            true, true);
    private final JTable bang = new JTable(model);
    private final Map<String, HoSo> hoSoTheoMa = new LinkedHashMap<String, HoSo>();
    private final JPanel avatar = new JPanel() {
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
    private String kyHieu = "LT";
    private JTextField donGia;
    private JComboBox<String> dichVu;
    private HopThoai h;
    private JTextField sl;

    public KeHoachDieuTriPanel() {
        setLayout(new BorderLayout());
        setBackground(ViewTheme.TRANG);
        setBorder(new EmptyBorder(12, 12, 12, 12));
        setPreferredSize(new Dimension(1180, 680));

        avatar.setPreferredSize(new Dimension(140, 110));
        avatar.setOpaque(false);
        ten.setFont(ViewTheme.FONT_TRANG);
        lienHe.setFont(ViewTheme.FONT);
        benh.setFont(ViewTheme.FONT);

        JPanel trai = new JPanel(new BorderLayout(0, 8));
        trai.setBackground(ViewTheme.TRANG);
        trai.setBorder(new EmptyBorder(8, 8, 8, 8));
        JPanel thongTin = new JPanel(new BorderLayout(0, 8));
        thongTin.setOpaque(false);
        thongTin.add(ten, BorderLayout.NORTH);
        thongTin.add(lienHe, BorderLayout.CENTER);
        thongTin.add(benh, BorderLayout.SOUTH);
        trai.add(avatar, BorderLayout.NORTH);
        trai.add(thongTin, BorderLayout.CENTER);
        trai.add(ViewTheme.tieuDeMuc("Tóm tắt bệnh lý"), BorderLayout.SOUTH);

        ViewTheme.trangTriBang(bang);
        ViewTheme.ganThaoTac(bang, this::xuLyHang);
        JScrollPane cuon = new JScrollPane(bang);
        cuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));

        JButton nutKeHoach = new JButton("+ Kế hoạch");
        JButton nutHoaDon = new JButton("Lập hóa đơn");
        JButton nutThanhToan = new JButton("Thu tiền");
        ViewTheme.nutXanh(nutKeHoach);
        ViewTheme.nutCam(nutHoaDon);
        ViewTheme.nutChinh(nutThanhToan);
        nutKeHoach.addActionListener(e -> moThemKeHoach());
        nutHoaDon.addActionListener(e -> moLapHoaDon());
        nutThanhToan.addActionListener(e -> moThanhToan());

        JPanel nutTrai = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        nutTrai.setOpaque(false);
        nutTrai.add(nutKeHoach);
        nutTrai.add(nutHoaDon);
        nutTrai.add(nutThanhToan);

        JPanel thongKe = new JPanel(new FlowLayout(FlowLayout.RIGHT, 18, 0));
        thongKe.setOpaque(false);
        thongKe.add(oSo(lbTongCong, "Tổng cộng", ViewTheme.CHU));
        thongKe.add(oSo(lbChietKhau, "Chiết khấu", ViewTheme.CHU_NHAT));
        thongKe.add(oSo(lbThanhTien, "Thành tiền", ViewTheme.CHU));
        thongKe.add(oSo(lbDaChiTra, "Đã chi trả", new Color(40, 150, 70)));
        thongKe.add(oSo(lbConNo, "Chưa thanh toán", ViewTheme.DO));

        JPanel thanhCongCu = new JPanel(new BorderLayout());
        thanhCongCu.setOpaque(false);
        thanhCongCu.add(nutTrai, BorderLayout.WEST);
        thanhCongCu.add(thongKe, BorderLayout.EAST);

        JPanel dauPhai = new JPanel(new BorderLayout(0, 8));
        dauPhai.setOpaque(false);
        dauPhai.add(ViewTheme.tieuDeTrang("Kế hoạch điều trị"), BorderLayout.NORTH);
        dauPhai.add(thanhCongCu, BorderLayout.SOUTH);

        tong.setFont(ViewTheme.FONT_SO);
        tong.setForeground(ViewTheme.XANH_DAM);
        tong.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel phai = new JPanel(new BorderLayout(0, 8));
        phai.setBackground(ViewTheme.TRANG);
        phai.setBorder(new EmptyBorder(8, 8, 8, 8));
        phai.add(dauPhai, BorderLayout.NORTH);
        phai.add(cuon, BorderLayout.CENTER);
        phai.add(tong, BorderLayout.SOUTH);

        JSplitPane tach = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, trai, phai);
        tach.setDividerLocation(280);
        tach.setResizeWeight(0);
        add(tach, BorderLayout.CENTER);
        HoaDonDuLieu.khiDoi(this::lamMoiBang);

        nap("0001", "Lê Trần Quang Thanh", "03xxxx2808", "thanhqt@gmail.com", "28 Lê Lai", "Đau răng", "Thăm khám - Tư vấn");
    }

    public void ganHoaDon(HoaDonPanel hoaDon) {
        this.hoaDon = hoaDon;
    }

    public void lamMoiBang() {
        veBang();
    }

    public List<HangDT> hangChuaXuat(String maKh) {
        List<HangDT> kq = new ArrayList<HangDT>();
        HoSo hs = hoSoTheoMa.get(maKh);
        if (hs == null) {
            return kq;
        }
        for (HangDT hang : hs.hang) {
            if (hang.maHoaDon == null || hang.maHoaDon.isEmpty()) {
                kq.add(hang);
            }
        }
        return kq;
    }

    public void danhDauHoaDon(List<HangDT> ds, String maHd) {
        if (ds == null) {
            return;
        }
        for (HangDT hang : ds) {
            hang.maHoaDon = maHd;
        }
        veBang();
    }

    public void boDanhDauHoaDon(String maHd) {
        for (HoSo hs : hoSoTheoMa.values()) {
            for (HangDT hang : hs.hang) {
                if (maHd.equals(hang.maHoaDon)) {
                    hang.maHoaDon = null;
                }
            }
        }
        veBang();
    }

    public void nap(String ma, String hoTen, String sdt, String email, String diaChi, String lyDo, String dieuTri) {
        HoSo hs = hoSoTheoMa.get(ma);
        if (hs == null) {
            hs = new HoSo(ma, hoTen, sdt, email, diaChi, lyDo);
            if ("0001".equals(ma)) {
                HangDT k1 = new HangDT("27/09/2026", "", "Thăm khám - Tư vấn", 1, 200_000, "Hoàn tất");
                HangDT k2 = new HangDT("27/09/2026", "", "Cạo vôi - Đánh bóng", 1, 700_000, "Hoàn tất");
                k1.maHoaDon = "0001";
                k2.maHoaDon = "0001";
                hs.themHang(k1);
                hs.themHang(k2);
            } else if (dieuTri != null && !dieuTri.isEmpty() && !dieuTri.contains("Thăm khám")) {
                hs.themHang(new HangDT(homNay(), "", dieuTri, 1, giaDichVu(dieuTri), "Mới"));
            }
            hoSoTheoMa.put(ma, hs);
        } else {
            hs.capNhatKhach(hoTen, sdt, email, diaChi, lyDo);
        }
        hienTai = hs;
        kyHieu = vietTat(hoTen);
        ten.setText(hoTen);
        lienHe.setText("<html>" + sdt + "<br>" + email + "<br>" + diaChi + "</html>");
        benh.setText("<html>Lý do khám: " + hs.lyDo
                + "<br>Tiền sử: Béo phì<br>Mã KH: " + ma + "</html>");
        avatar.repaint();
        veBang();
    }

    private void moThemKeHoach() {
        if (hienTai == null) {
            return;
        }
        Window w = SwingUtilities.getWindowAncestor(this);
        h = new HopThoai(w, "Thêm kế hoạch điều trị", "+ Thêm",
                "Tôi muốn tiếp tục thêm dịch vụ vào kế hoạch này");
        JComboBox<String> ngay = h.chon("Ngày kế hoạch", true, homNay(), "19/10/2026", "27/09/2026");
        ngay.setEditable(true);
        JComboBox<String> rang = h.chon("Răng", false, tenRang());
        dichVu = h.chon("Dịch vụ", true, tenDichVu());        
        sl = h.chu("Số lượng", "1", true);
        donGia = h.chu("Đơn giá", ViewTheme.tien(giaDichVu(String.valueOf(dichVu.getSelectedItem()))), true);        
        sl.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {           
                setDonGia();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {                
            }
        });
        donGia.setEditable(false);
        JComboBox<String> trangThai = h.chon("Trạng thái", true, "Mới", "Đang điều trị", "Hoàn tất");
        dichVu.addActionListener(e -> donGia.setText(ViewTheme.tien(giaDichVu(String.valueOf(dichVu.getSelectedItem())))));
        h.hien(() -> {
            String ngayKH = String.valueOf(ngay.getEditor().getItem()).trim();
            if (ngayKH.isEmpty()) {
                JOptionPane.showMessageDialog(h, "Vui lòng nhập ngày kế hoạch.");
                return;
            }
            HangDT hang = new HangDT(
                    ngayKH,
                    String.valueOf(rang.getSelectedItem()),
                    String.valueOf(dichVu.getSelectedItem()),
                    parseSo(String.valueOf(sl.getText()), 1),
                    parseTien(donGia.getText()),
                    String.valueOf(trangThai.getSelectedItem()));
            hienTai.themHang(hang);
            veBang();
        });
    }

    private void moLapHoaDon() {
        if (hienTai == null || hoaDon == null) {
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
            chuaXuat = hangChuaXuat(hienTai.ma);
        }
        if (chuaXuat.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Các dịch vụ của khách đã được xuất hóa đơn.\nThêm dịch vụ mới hoặc mở hóa đơn để thu tiền.");
            return;
        }
        hoaDon.moLapHoaDon(hienTai.ma, chuaXuat);
    }

    private void moThanhToan() {
        if (hienTai == null) {
            return;
        }
        List<HoaDonDuLieu.HoaDon> no = HoaDonDuLieu.conNoTheoKhach(hienTai.ma);
        if (no.isEmpty()) {
            if (!hangChuaXuat(hienTai.ma).isEmpty()) {
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
        HoaDonDuLieu.HoaDon hd = no.get(0);
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
            for (HoaDonDuLieu.HoaDon item : no) {
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

    private List<HangDT> hangDangChon() {
        List<HangDT> kq = new ArrayList<HangDT>();
        if (hienTai == null) {
            return kq;
        }
        for (int i = 0; i < model.getRowCount() && i < hienTai.hang.size(); i++) {
            if (Boolean.TRUE.equals(model.getValueAt(i, 0))) {
                kq.add(hienTai.hang.get(i));
            }
        }
        return kq;
    }

    private void xuLyHang(String lenh, int hang) {
        if (hienTai == null || hang < 0 || hang >= hienTai.hang.size()) {
            return;
        }
        HangDT dt = hienTai.hang.get(hang);
        if ("Xóa".equals(lenh)) {
            if (dt.maHoaDon != null && !dt.maHoaDon.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Dịch vụ đã xuất hóa đơn " + dt.maHoaDon + ", không thể xóa.");
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "Xóa dịch vụ này khỏi kế hoạch?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                hienTai.hang.remove(hang);
                veBang();
            }
            return;
        }
        if ("Xem".equals(lenh)) {
            JOptionPane.showMessageDialog(this,
                    "Kế hoạch: " + dt.tenKeHoach() + "\nNgày: " + dt.ngay
                    + "\nRăng: " + dt.rang
                    + "\nDịch vụ: " + dt.dichVu
                    + "\nSố lượng: " + dt.soLuong
                    + "\nĐơn giá: " + ViewTheme.tien(dt.donGia)
                    + "\nThành tiền: " + ViewTheme.tien(dt.thanhTien())
                    + "\nTrạng thái: " + dt.trangThai,
                    "Chi tiết điều trị", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Window w = SwingUtilities.getWindowAncestor(this);
        HopThoai h = new HopThoai(w, "Cập nhật điều trị", "Lưu", null);
        JTextField ngay = h.chu("Ngày", dt.ngay, true);
        JComboBox<String> rang = h.chon("Răng", false, tenRang());
        rang.setSelectedItem(dt.rang);
        JComboBox<String> dichVu = h.chon("Dịch vụ", true, tenDichVu());
        dichVu.setSelectedItem(dt.dichVu);
        //JComboBox<String> sl = h.chon("Số lượng", true, "1", "2", "3", "4", "5");
        JTextField sl = h.chu("Số lượng", "1", true);
        //sl.setSelectedItem(String.valueOf(dt.soLuong));
        JTextField donGia = h.chu("Đơn giá", ViewTheme.tien(dt.donGia), true);
        JComboBox<String> trangThai = h.chon("Trạng thái", true, "Mới", "Đang điều trị", "Hoàn tất");
        trangThai.setSelectedItem(dt.trangThai);
        h.hien(() -> {
            dt.ngay = ngay.getText().trim();
            dt.rang = String.valueOf(rang.getSelectedItem());
            dt.dichVu = String.valueOf(dichVu.getSelectedItem());
            dt.soLuong = parseSo(String.valueOf(sl.getText()), 1);
            dt.donGia = parseTien(donGia.getText());
            dt.trangThai = String.valueOf(trangThai.getSelectedItem());
            veBang();
        });
    }

    private void veBang() {
        model.setRowCount(0);
        if (hienTai != null) {
            for (HangDT hang : hienTai.hang) {
                model.addRow(ViewTheme.dongBang(new Object[]{
                    hang.ngay,
                    hang.rang,
                    hang.dichVu,
                    String.valueOf(hang.soLuong),
                    ViewTheme.tien(hang.donGia),
                    ViewTheme.tien(hang.thanhTien()),
                    hang.trangThai,
                    hang.tenKeHoach(),
                    hang.maHoaDon == null || hang.maHoaDon.isEmpty() ? "Chưa xuất" : hang.maHoaDon
                }, true, true));
            }
        }
        capNhatTong();
    }

    private void capNhatTong() {
        long tongTien = hienTai == null ? 0 : hienTai.tong();
        long daTra = hienTai == null ? 0 : HoaDonDuLieu.daThuTheoKhach(hienTai.ma);
        long conNoHd = hienTai == null ? 0 : HoaDonDuLieu.conNoTheoKhachTong(hienTai.ma);
        long chuaXuat = 0;
        if (hienTai != null) {
            for (HangDT hang : hangChuaXuat(hienTai.ma)) {
                chuaXuat += hang.thanhTien();
            }
        }
        long conNo = conNoHd + chuaXuat;
        lbTongCong.setText(ViewTheme.tien(tongTien) + "đ");
        lbChietKhau.setText("0đ");
        lbThanhTien.setText(ViewTheme.tien(tongTien) + "đ");
        lbDaChiTra.setText(ViewTheme.tien(daTra) + "đ");
        lbConNo.setText(ViewTheme.tien(conNo) + "đ");
        tong.setText("Tổng: " + ViewTheme.tien(tongTien) + "   Còn nợ HĐ: " + ViewTheme.tien(conNoHd));
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
        CauHinhTrang c = DuLieuMau.lay("dichvu");
        if (c == null || c.duLieu.length == 0) {
            return DuLieuMau.TEN_DV;
        }
        String[] ten = new String[c.duLieu.length];
        for (int i = 0; i < c.duLieu.length; i++) {
            ten[i] = String.valueOf(c.duLieu[i][1]);
        }
        return ten;
    }

    private static String[] tenRang() {
        List<String> ds = new ArrayList<String>();
        ds.add("");
        CauHinhTrang c = DuLieuMau.lay("rang");
        if (c != null) {
            for (Object[] d : c.duLieu) {
                ds.add(String.valueOf(d[0]));
            }
        }
        return ds.toArray(new String[0]);
    }

    private static long giaDichVu(String ten) {
        CauHinhTrang c = DuLieuMau.lay("dichvu");
        if (c != null) {
            for (Object[] d : c.duLieu) {
                if (String.valueOf(d[1]).equals(ten)) {
                    return parseTien(String.valueOf(d[2]));
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

    private void setDonGia() {          
        long giaTien = giaDichVu(String.valueOf(dichVu.getSelectedItem())) * Integer.parseInt(sl.getText());        
        donGia.setText(ViewTheme.tien(giaTien));
    }


}
