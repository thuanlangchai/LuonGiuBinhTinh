package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import view.model.KeHoachView;

public class DsKeHoachPanel extends JPanel {

    private static final DateTimeFormatter NGAY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String[] COT = {
        "Mã kế hoạch", "Ngày lập", "Bác sĩ phụ trách", "Lý do điều trị", "Trạng thái", "Thao tác"
    };

    private final DefaultTableModel model = ViewTheme.taoModel(COT, false, true);
    private final JTable bang = new JTable(model);
    private final JLabel thongTinKh = new JLabel("Chưa chọn khách hàng");
    private final JLabel dem = new JLabel();
    private final JComboBox<String> cboKhach = new JComboBox<>();
    private final Consumer<String> moChiTiet;
    private String maKhLoc = "";

    public DsKeHoachPanel(Consumer<String> moChiTiet) {
        this.moChiTiet = moChiTiet;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));

        JPanel the = new JPanel(new BorderLayout(0, 8));
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel tieu = ViewTheme.tieuDeTrang("Kế hoạch điều trị");
        JButton them = new JButton("+ Kế hoạch điều trị");
        ViewTheme.nutXanh(them);
        them.addActionListener(e -> moThem());
        JPanel dau = new JPanel(new BorderLayout());
        dau.setOpaque(false);
        dau.add(tieu, BorderLayout.WEST);
        JPanel phai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        phai.setOpaque(false);
        phai.add(them);
        dau.add(phai, BorderLayout.EAST);

        thongTinKh.setFont(ViewTheme.FONT);
        thongTinKh.setForeground(ViewTheme.CHU);

        cboKhach.addItem("Tất cả khách hàng");
        for (String k : DuLieuMau.dsKhach()) {
            cboKhach.addItem(k);
        }
        ViewTheme.vienHop(cboKhach);
        cboKhach.addActionListener(e -> {
            Object sel = cboKhach.getSelectedItem();
            if (cboKhach.getSelectedIndex() <= 0 || sel == null) {
                maKhLoc = "";
            } else {
                maKhLoc = maKhach(String.valueOf(sel));
            }
            capNhatThongTinKh();
            lamMoi();
        });

        JPanel loc = new JPanel(new BorderLayout(0, 8));
        loc.setBackground(ViewTheme.NEN_TIM);
        loc.setBorder(new EmptyBorder(10, 12, 10, 12));
        loc.add(ViewTheme.chuPhu("Khách hàng"), BorderLayout.NORTH);
        loc.add(cboKhach, BorderLayout.CENTER);
        loc.add(thongTinKh, BorderLayout.SOUTH);

        ViewTheme.trangTriBang(bang);
        ViewTheme.ganThaoTac(bang, this::xuLy);
        bang.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && bang.rowAtPoint(e.getPoint()) >= 0
                        && bang.columnAtPoint(e.getPoint()) != bang.getColumnCount() - 1) {
                    KeHoachView kh = keHoachTai(bang.convertRowIndexToModel(bang.rowAtPoint(e.getPoint())));
                    if (kh != null && moChiTiet != null) {
                        moChiTiet.accept(kh.maKeHoach);
                    }
                }
            }
        });
        JScrollPane cuon = new JScrollPane(bang);
        cuon.setPreferredSize(new Dimension(400, 240));
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

        KeHoachDuLieu.khiDoi(this::lamMoi);
        lamMoi();
    }

    public void locTheoKhach(String maKH, String ten, String sdt, String email, String diaChi, String lyDo) {
        maKhLoc = maKH == null ? "" : maKH;
        if (maKhLoc.isEmpty()) {
            cboKhach.setSelectedIndex(0);
        } else {
            for (int i = 0; i < cboKhach.getItemCount(); i++) {
                if (cboKhach.getItemAt(i).startsWith(maKhLoc)) {
                    cboKhach.setSelectedIndex(i);
                    break;
                }
            }
        }
        if (ten != null && !ten.isEmpty()) {
            thongTinKh.setText("<html><b>" + ten + "</b> (" + maKhLoc + ") — "
                    + nullSafe(sdt) + " | " + nullSafe(email) + "<br>"
                    + nullSafe(diaChi) + " | Lý do: " + nullSafe(lyDo) + "</html>");
        } else {
            capNhatThongTinKh();
        }
        lamMoi();
    }

    private void capNhatThongTinKh() {
        if (maKhLoc == null || maKhLoc.isEmpty()) {
            thongTinKh.setText("Đang xem tất cả khách hàng");
            return;
        }
        String ten = tenKhachTuMa(maKhLoc);
        thongTinKh.setText("<html><b>" + ten + "</b> — Mã KH: " + maKhLoc + "</html>");
    }

    private void lamMoi() {
        model.setRowCount(0);
        List<KeHoachView> ds = maKhLoc == null || maKhLoc.isEmpty()
                ? KeHoachDuLieu.tatCa() : KeHoachDuLieu.theoMaKH(maKhLoc);
        for (KeHoachView kh : ds) {
            model.addRow(new Object[]{
                kh.maKeHoach,
                kh.ngayLap,
                kh.tenBacSi,
                kh.lyDoDieuTri,
                kh.trangThai,
                "Sửa      Xem      Hủy"
            });
        }
        dem.setText(ViewTheme.demDong(ds.isEmpty() ? 0 : 1, ds.size(), ds.size()));
    }

    private void xuLy(String lenh, int hang) {
        KeHoachView kh = keHoachTai(hang);
        if (kh == null) {
            return;
        }
        if ("Xem".equals(lenh)) {
            if (moChiTiet != null) {
                moChiTiet.accept(kh.maKeHoach);
            }
            return;
        }
        if ("Sửa".equals(lenh)) {
            moSua(kh);
            return;
        }
        if ("Xóa".equals(lenh)) {
            moHuy(kh);
        }
    }

    private void moThem() {
        if (maKhLoc == null || maKhLoc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng trước khi thêm kế hoạch điều trị.");
            return;
        }
        moForm(null);
    }

    private void moSua(KeHoachView kh) {
        if (kh.daHuy()) {
            JOptionPane.showMessageDialog(this, "Kế hoạch đã hủy, không thể sửa.");
            return;
        }
        if (kh.coHoaDon()) {
            JOptionPane.showMessageDialog(this,
                    "Kế hoạch đã phát sinh hóa đơn, chỉ được xem hoặc cập nhật ghi chú hạn chế.");
        }
        moForm(kh);
    }

    private void moHuy(KeHoachView kh) {
        if (kh.daHuy()) {
            JOptionPane.showMessageDialog(this, "Kế hoạch đã được hủy trước đó.");
            return;
        }
        if (kh.coHoaDon()) {
            JOptionPane.showMessageDialog(this,
                    "Không thể hủy kế hoạch đã phát sinh hóa đơn/thanh toán.\nGiữ lại lịch sử điều trị.");
            return;
        }
        if (JOptionPane.showConfirmDialog(this,
                "Hủy kế hoạch " + kh.maKeHoach + "?\nCác dịch vụ chưa xuất hóa đơn sẽ không còn hiệu lực.",
                "Hủy kế hoạch", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        if (!KeHoachDuLieu.huy(kh.maKeHoach)) {
            JOptionPane.showMessageDialog(this, "Không hủy được kế hoạch này.");
        }
    }

    private void moForm(KeHoachView goc) {
        boolean sua = goc != null;
        Window w = SwingUtilities.getWindowAncestor(this);
        HopThoai h = new HopThoai(w, sua ? "Sửa kế hoạch điều trị" : "Thêm kế hoạch điều trị", "Lưu");

        String maKH = sua ? goc.maKH : maKhLoc;
        String tenKH = sua ? goc.tenKh : tenKhachTuMa(maKH);
        JTextField khach = h.chu("Khách hàng", maKH + " - " + tenKH, true);
        khach.setEditable(false);
        JTextField ngay = h.chu("Ngày lập", sua ? goc.ngayLap : LocalDate.now().format(NGAY), true);
        JComboBox<String> bacSi = h.chon("Bác sĩ phụ trách", true, bacSiNhan());
        if (sua && goc.tenBacSi != null) {
            bacSi.setSelectedItem(goc.tenBacSi);
        }
        JTextField lyDo = h.chu("Lý do điều trị", sua ? nullSafe(goc.lyDoDieuTri) : "", true);
        JTextField ghiChu = h.chu("Ghi chú", sua ? nullSafe(goc.ghiChu) : "", false);

        if (sua && goc.coHoaDon()) {
            ngay.setEditable(false);
            bacSi.setEnabled(false);
            lyDo.setEditable(false);
        }

        h.hien(() -> {
            String ngayLap = ngay.getText().trim();
            if (ngayLap.isEmpty()) {
                JOptionPane.showMessageDialog(h, "Vui lòng nhập ngày lập.");
                return;
            }
            String tenBs = String.valueOf(bacSi.getSelectedItem());
            String maBs = maBacSiTuTen(tenBs);
            if (sua) {
                if (!goc.coHoaDon()) {
                    goc.ngayLap = ngayLap;
                    goc.maBacSi = maBs;
                    goc.tenBacSi = tenBs;
                    goc.lyDoDieuTri = lyDo.getText().trim();
                }
                goc.ghiChu = ghiChu.getText().trim();
                KeHoachDuLieu.capNhat(goc);
            } else {
                KeHoachDuLieu.them(maKH, tenKH, ngayLap, maBs, tenBs,
                        lyDo.getText().trim(), ghiChu.getText().trim());
            }
        });
    }

    private KeHoachView keHoachTai(int hang) {
        if (hang < 0 || hang >= model.getRowCount()) {
            return null;
        }
        return KeHoachDuLieu.theoMa(String.valueOf(model.getValueAt(hang, 0)));
    }

    private static String maKhach(String nhan) {
        if (nhan == null) {
            return "";
        }
        int i = nhan.indexOf(" - ");
        return i > 0 ? nhan.substring(0, i).trim() : nhan.trim();
    }

    private static String tenKhachTuMa(String ma) {
        for (String k : DuLieuMau.dsKhach()) {
            if (k.startsWith(ma)) {
                String[] p = k.split(" - ", 3);
                return p.length > 1 ? p[1].trim() : k;
            }
        }
        return ma;
    }

    private static String[] bacSiNhan() {
        return DuLieuMau.dsBacSi();
    }

    private static String maBacSiTuTen(String ten) {
        return DuLieuMau.maBacSiTheoTen(ten);
    }

    private static String nullSafe(String s) {
        return s == null ? "" : s;
    }
}
