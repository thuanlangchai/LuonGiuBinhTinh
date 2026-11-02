package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private final DefaultTableModel model = ViewTheme.taoModel(COT, false, false);
    private final JTable tblBang = new JTable(model);
    private final JLabel lblThongTinKh = new JLabel("Chưa chọn khách hàng");
    private final JLabel lblDem = new JLabel();
    private final JComboBox<String> cmbKhach = new JComboBox<>();
    private final Consumer<String> moChiTiet;
    private String maKhLoc = "";

    public DsKeHoachPanel(Consumer<String> moChiTiet) {
        this.moChiTiet = moChiTiet;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));

        JPanel pnlThe = new JPanel(new BorderLayout(0, 8));
        pnlThe.setBackground(ViewTheme.TRANG);
        pnlThe.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel lblTieuDe = ViewTheme.lblTieuDeTrang("Kế hoạch điều trị");
        JButton btnThem = new JButton("+ Kế hoạch điều trị");
        ViewTheme.btnNutXanh(btnThem);
        btnThem.addActionListener(e -> moThem());
        JPanel pnlDau = new JPanel(new BorderLayout());
        pnlDau.setOpaque(false);
        pnlDau.add(lblTieuDe, BorderLayout.WEST);
        JPanel pnlPhai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        pnlPhai.setOpaque(false);
        pnlPhai.add(btnThem);
        pnlDau.add(pnlPhai, BorderLayout.EAST);

        lblThongTinKh.setFont(ViewTheme.FONT);
        lblThongTinKh.setForeground(ViewTheme.CHU);

        cmbKhach.addItem("Tất cả khách hàng");
        for (String k : DuLieuMau.dsKhach()) {
            cmbKhach.addItem(k);
        }
        ViewTheme.vienHop(cmbKhach);
        cmbKhach.addActionListener(e -> {
            Object sel = cmbKhach.getSelectedItem();
            if (cmbKhach.getSelectedIndex() <= 0 || sel == null) {
                maKhLoc = "";
            } else {
                maKhLoc = maKhach(String.valueOf(sel));
            }
            capNhatThongTinKh();
            lamMoi();
        });

        JPanel pnlLoc = new JPanel(new BorderLayout(0, 8));
        pnlLoc.setBackground(ViewTheme.NEN_TIM);
        pnlLoc.setBorder(new EmptyBorder(10, 12, 10, 12));
        pnlLoc.add(ViewTheme.chuPhu("Khách hàng"), BorderLayout.NORTH);
        pnlLoc.add(cmbKhach, BorderLayout.CENTER);
        pnlLoc.add(lblThongTinKh, BorderLayout.SOUTH);

        ViewTheme.trangTriBang(tblBang);
        ViewTheme.ganThaoTac(tblBang, this::xuLy);
        tblBang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tblBang.rowAtPoint(e.getPoint()) >= 0
                        && tblBang.columnAtPoint(e.getPoint()) != tblBang.getColumnCount() - 1) {
                    KeHoachView kh = keHoachTai(tblBang.convertRowIndexToModel(tblBang.rowAtPoint(e.getPoint())));
                    if (kh != null && moChiTiet != null) {
                        moChiTiet.accept(kh.maKeHoach);
                    }
                }
            }
        });
        JScrollPane scrBang = new JScrollPane(tblBang);
        scrBang.setPreferredSize(new Dimension(400, 240));
        scrBang.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        scrBang.getViewport().setBackground(ViewTheme.TRANG);
        lblDem.setFont(ViewTheme.FONT_NHO);
        lblDem.setForeground(ViewTheme.CHU_NHAT);

        pnlThe.add(pnlDau, BorderLayout.NORTH);
        JPanel pnlGiua = new JPanel(new BorderLayout(0, 8));
        pnlGiua.setOpaque(false);
        pnlGiua.add(pnlLoc, BorderLayout.NORTH);
        pnlGiua.add(scrBang, BorderLayout.CENTER);
        pnlGiua.add(lblDem, BorderLayout.SOUTH);
        pnlThe.add(pnlGiua, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(pnlThe, BorderLayout.NORTH);

        KeHoachDuLieu.khiDoi(this::lamMoi);
        lamMoi();
    }

    public void locTheoKhach(String maKH, String ten, String sdt, String email, String diaChi, String lyDo) {
        maKhLoc = maKH == null ? "" : maKH;
        if (maKhLoc.isEmpty()) {
            cmbKhach.setSelectedIndex(0);
        } else {
            for (int i = 0; i < cmbKhach.getItemCount(); i++) {
                if (cmbKhach.getItemAt(i).startsWith(maKhLoc)) {
                    cmbKhach.setSelectedIndex(i);
                    break;
                }
            }
        }
        if (ten != null && !ten.isEmpty()) {
            lblThongTinKh.setText("<html><b>" + ten + "</b> (" + maKhLoc + ") — "
                    + nullSafe(sdt) + " | " + nullSafe(email) + "<br>"
                    + nullSafe(diaChi) + " | Lý do: " + nullSafe(lyDo) + "</html>");
        } else {
            capNhatThongTinKh();
        }
        lamMoi();
    }

    private void capNhatThongTinKh() {
        if (maKhLoc == null || maKhLoc.isEmpty()) {
            lblThongTinKh.setText("Đang xem tất cả khách hàng");
            return;
        }
        String ten = tenKhachTuMa(maKhLoc);
        lblThongTinKh.setText("<html><b>" + ten + "</b> — Mã KH: " + maKhLoc + "</html>");
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
        lblDem.setText(ViewTheme.demDong(ds.isEmpty() ? 0 : 1, ds.size(), ds.size()));
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
        JTextField txtKhach = h.chu("Khách hàng", maKH + " - " + tenKH, true);
        txtKhach.setEditable(false);
        JTextField txtNgay = h.chu("Ngày lập", sua ? goc.ngayLap : LocalDate.now().format(NGAY), true);
        JComboBox<String> bacSi = h.chon("Bác sĩ phụ trách", true, bacSiNhan());
        if (sua && goc.tenBacSi != null) {
            bacSi.setSelectedItem(goc.tenBacSi);
        }
        JTextField txtLyDo = h.chu("Lý do điều trị", sua ? nullSafe(goc.lyDoDieuTri) : "", true);
        JTextField txtGhiChu = h.chu("Ghi chú", sua ? nullSafe(goc.ghiChu) : "", false);

        if (sua && goc.coHoaDon()) {
            txtNgay.setEditable(false);
            bacSi.setEnabled(false);
            txtLyDo.setEditable(false);
        }

        h.hien(() -> {
            String ngayLap = txtNgay.getText().trim();
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
                    goc.lyDoDieuTri = txtLyDo.getText().trim();
                }
                goc.ghiChu = txtGhiChu.getText().trim();
                KeHoachDuLieu.capNhat(goc);
            } else {
                KeHoachDuLieu.them(maKH, tenKH, ngayLap, maBs, tenBs,
                        txtLyDo.getText().trim(), txtGhiChu.getText().trim());
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
