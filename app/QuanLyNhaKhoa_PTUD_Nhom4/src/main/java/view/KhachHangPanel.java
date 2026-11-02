package view;

import entity.KhachHang;
import entity.NhanVien;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import service.impl.DanhMuc_Service;
import service.impl.KhachHang_Service;

public class KhachHangPanel extends JPanel {

    private static final String[] COT = {
        "Mã KH", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Địa chỉ", "CCCD",
        "Ghi chú", "Nhóm KH", "Chi nhánh", "Lý do khám", "Tiền sử bệnh", "Dị ứng",
        "Bác sĩ", "Tư vấn", "Số lần khám", "Lần khám gần nhất", "Ngày tạo"
    };

    @FunctionalInterface
    public interface MoHoSoKeHoach {
        void mo(String ma, String ten, String sdt, String email, String diaChi, String lyDo);
    }

    private final MoHoSoKeHoach moKeHoach;
    private final Consumer<String> moToaThuoc;
    private final KhachHang_Service khachHangService = new KhachHang_Service();
    private final DanhMuc_Service danhMucService = new DanhMuc_Service();
    private final DefaultTableModel model;
    private final JTable bang;
    private final JTextField txtTim = new JTextField();
    private final JPanel panelLocThem = new JPanel(new GridLayout(2, 6, 8, 8));
    private final JLabel lblDem = new JLabel();
    private int demMa = 1;

    public KhachHangPanel(MoHoSoKeHoach moKeHoach) {
        this(moKeHoach, null);
    }

    public KhachHangPanel(MoHoSoKeHoach moKeHoach, Consumer<String> moToaThuoc) {
        this.moKeHoach = moKeHoach;
        this.moToaThuoc = moToaThuoc;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));
        JPanel the = new JPanel(new BorderLayout(0, 8));
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel lblTieuDe = ViewTheme.lblTieuDeTrang("Khách hàng");
        JButton btnXuat = new JButton("Xuất Excel");
        JButton btnToa = new JButton("Toa thuốc");
        JButton btnThem = new JButton("Thêm khách hàng");
        JComboBox<String> cboHienThi = new JComboBox<String>(new String[]{"Hiển thị", "Tất cả cột", "Cột chính"});
        JComboBox<String> cboDang = new JComboBox<String>(new String[]{"Dạng", "Bảng", "Thẻ"});
        ViewTheme.btnNutXam(btnXuat);
        ViewTheme.btnNutXanh(btnToa);
        ViewTheme.btnNutCam(btnThem);
        ViewTheme.cmbVienHop(cboHienThi);
        ViewTheme.cmbVienHop(cboDang);
        btnThem.addActionListener(e -> moForm(-1));
        btnToa.addActionListener(e -> moToaChoKhach());
        btnXuat.addActionListener(e -> JOptionPane.showMessageDialog(this, "Đã chuẩn bị dữ liệu khách hàng để xuất Excel."));

        JPanel panelDau = new JPanel(new BorderLayout());
        panelDau.setOpaque(false);
        panelDau.add(lblTieuDe, BorderLayout.WEST);
        JPanel panelPhai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panelPhai.setOpaque(false);
        panelPhai.add(btnXuat);
        panelPhai.add(btnToa);
        panelPhai.add(btnThem);
        panelPhai.add(cboHienThi);
        panelPhai.add(cboDang);
        panelDau.add(panelPhai, BorderLayout.EAST);

        ViewTheme.vienNhap(txtTim);
        txtTim.setPreferredSize(new Dimension(220, 32));
        JButton btnTim = new JButton("Tìm");
        ViewTheme.btnNutChinh(btnTim);
        btnTim.addActionListener(e -> loc());
        txtTim.addActionListener(e -> loc());

        JComboBox<String> cboPhanLoai = hop("Phân loại", "Tất cả", "Mới", "Cũ");
        JComboBox<String> cboBacSiLoc = hop("Bác sĩ", "Tất cả", "Lê Trần Quang Thanh", "Hồ Văn Khánh");
        JComboBox<String> cboNhan = hop("Nhãn", "Tất cả", "Nhổ răng khôn", "Chỉnh nha");
        JComboBox<String> cboTrangThai = hop("Trạng thái điều trị", "Tất cả", "Đang điều trị", "Hoàn tất");
        JComboBox<String> cboDichVu = hop("Dịch vụ", "Tất cả", "Thăm khám", "Cạo vôi");
        JButton btnMore = new JButton("More");
        ViewTheme.btnNutChinh(btnMore);

        panelLocThem.setOpaque(false);
        panelLocThem.setVisible(false);
        panelLocThem.add(hop("Giới tính", "Tất cả", "Nam", "Nữ"));
        panelLocThem.add(hop("Nguồn", "Tất cả", "Cư dân", "Facebook"));
        panelLocThem.add(o("Ngày điều trị"));
        panelLocThem.add(o("Ngày hẹn"));
        panelLocThem.add(o("Ngày tạo"));
        panelLocThem.add(hop("Nhóm khách hàng", "Tất cả", "PTUD", "VIP"));
        panelLocThem.add(o("Chọn độ tuổi hoặc tháng sinh"));
        panelLocThem.add(o("Số tiền đã thanh toán"));
        panelLocThem.add(o("Số tiền dịch vụ"));
        panelLocThem.add(o("Số tiền nợ còn lại"));
        JButton btnLess = new JButton("Less");
        ViewTheme.btnNutChinh(btnLess);
        panelLocThem.add(btnLess);
        btnMore.addActionListener(e -> panelLocThem.setVisible(true));
        btnLess.addActionListener(e -> panelLocThem.setVisible(false));

        JPanel panelDong1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        panelDong1.setOpaque(false);
        panelDong1.add(txtTim);
        panelDong1.add(btnTim);
        panelDong1.add(cboPhanLoai);
        panelDong1.add(cboBacSiLoc);
        panelDong1.add(cboNhan);
        panelDong1.add(cboTrangThai);
        panelDong1.add(cboDichVu);
        panelDong1.add(btnMore);

        JPanel panelVungLoc = new JPanel(new BorderLayout());
        panelVungLoc.setOpaque(false);
        panelVungLoc.add(panelDong1, BorderLayout.NORTH);
        panelVungLoc.add(panelLocThem, BorderLayout.CENTER);

        model = ViewTheme.taoModel(COT, true, true);
        bang = new JTable(model);
        ViewTheme.trangTriBang(bang);
        ViewTheme.ganThaoTac(bang, (lenh, hang) -> xuLy(lenh, hang));
        bang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && bang.rowAtPoint(e.getPoint()) >= 0
                        && bang.columnAtPoint(e.getPoint()) != bang.getColumnCount() - 1) {
                    moHoSo(bang.convertRowIndexToModel(bang.rowAtPoint(e.getPoint())));
                }
            }
        });

        JScrollPane scrollBang = new JScrollPane(bang);
        scrollBang.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        scrollBang.setPreferredSize(new Dimension(400, 220));
        scrollBang.getViewport().setBackground(ViewTheme.TRANG);

        lblDem.setFont(ViewTheme.FONT_NHO);
        lblDem.setForeground(ViewTheme.CHU_NHAT);
        JPanel panelChan = new JPanel(new BorderLayout());
        panelChan.setOpaque(false);
        JPanel panelTrai = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        panelTrai.setOpaque(false);
        panelTrai.add(new JLabel("Display"));
        JComboBox<String> cboSoBanGhi = new JComboBox<String>(new String[]{"15", "10", "25"});
        ViewTheme.cmbVienHop(cboSoBanGhi);
        panelTrai.add(cboSoBanGhi);
        panelTrai.add(new JLabel("records"));
        panelTrai.add(lblDem);
        panelChan.add(panelTrai, BorderLayout.WEST);

        the.add(panelDau, BorderLayout.NORTH);
        JPanel panelGiua = new JPanel(new BorderLayout(0, 8));
        panelGiua.setOpaque(false);
        panelGiua.add(panelVungLoc, BorderLayout.NORTH);
        panelGiua.add(scrollBang, BorderLayout.CENTER);
        the.add(panelGiua, BorderLayout.CENTER);
        the.add(panelChan, BorderLayout.SOUTH);
        setLayout(new BorderLayout());
        add(the, BorderLayout.NORTH);
        taiLai();
    }

    public void taiLai() {
        new SwingWorker<List<KhachHang>, Void>() {
            @Override
            protected List<KhachHang> doInBackground() {
                return khachHangService.findAllKhachHang();
            }

            @Override
            protected void done() {
                try {
                    napBang(get());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(KhachHangPanel.this,
                            "Không tải được danh sách khách hàng.\n" + ex.getMessage(),
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    napBang(List.of());
                }
            }
        }.execute();
    }

    private void napBang(List<KhachHang> ds) {
        model.setRowCount(0);
        int max = 0;
        for (KhachHang kh : ds) {
            model.addRow(ViewTheme.dongBang(dongTuEntity(kh), true, true));
            try {
                max = Math.max(max, Integer.parseInt(kh.getMaKH()));
            } catch (Exception ignored) {
            }
        }
        demMa = max + 1;
        lblDem.setText(ViewTheme.demDong(ds.isEmpty() ? 0 : 1, ds.size(), ds.size()));
        loc();
    }

    private Object[] dongTuEntity(KhachHang kh) {
        return new Object[]{
            kh.getMaKH(),
            kh.getHoTen(),
            nullToEmpty(kh.getGioiTinh()),
            NgayUtil.fmt(kh.getNgaySinh()),
            nullToEmpty(kh.getSoDienThoai()),
            nullToEmpty(kh.getEmail()),
            nullToEmpty(kh.getDiaChi()),
            nullToEmpty(kh.getCccd()),
            nullToEmpty(kh.getGhiChu()),
            nullToEmpty(kh.getMaNhom()),
            nullToEmpty(kh.getMaChiNhanh()),
            nullToEmpty(kh.getLyDoKham()),
            nullToEmpty(kh.getTienSuBenh()),
            nullToEmpty(kh.getDiUng()),
            nullToEmpty(kh.getMaBacSi()),
            nullToEmpty(kh.getMaTuVan()),
            String.valueOf(kh.getSoLanKham()),
            NgayUtil.fmt(kh.getLanKhamGanNhat()),
            NgayUtil.fmt(kh.getNgayTao())
        };
    }

    public void tim(String tuKhoa) {
        txtTim.setText(tuKhoa == null ? "" : tuKhoa);
        loc();
    }

    private void loc() {
        String tuKhoa = txtTim.getText().trim().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        bang.setRowSorter(sorter);
        if (tuKhoa.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(tuKhoa)));
        }
    }

    private void moToaChoKhach() {
        int hang = bang.getSelectedRow();
        if (hang < 0 && bang.getRowCount() > 0) {
            hang = 0;
        }
        if (hang < 0) {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng trước.");
            return;
        }
        int modelHang = bang.convertRowIndexToModel(hang);
        String maKH = String.valueOf(model.getValueAt(modelHang, 1));
        if (moToaThuoc != null) {
            moToaThuoc.accept(maKH);
        }
    }

    private void xuLy(String lenh, int hang) {
        if ("Xóa".equals(lenh)) {
            if (JOptionPane.showConfirmDialog(this, "Xóa khách hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String ma = String.valueOf(model.getValueAt(hang, 1));
                try {
                    khachHangService.delete(ma);
                    taiLai();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Không xóa được khách hàng.\n" + ex.getMessage());
                }
            }
            return;
        }
        if ("Xem".equals(lenh)) {
            moHoSo(hang);
            return;
        }
        moForm(hang);
    }

    private void moHoSo(int hang) {
        if (moKeHoach != null) {
            moKeHoach.mo(
                    String.valueOf(model.getValueAt(hang, 1)),
                    String.valueOf(model.getValueAt(hang, 2)),
                    String.valueOf(model.getValueAt(hang, 5)),
                    String.valueOf(model.getValueAt(hang, 6)),
                    String.valueOf(model.getValueAt(hang, 7)),
                    String.valueOf(model.getValueAt(hang, 12)));
        }
    }

    private void moForm(int hang) {
        boolean sua = hang >= 0;
        Window w = SwingUtilities.getWindowAncestor(this);
        JDialog d = new JDialog(w, sua ? "Cập nhật khách hàng" : "Thêm khách hàng", Dialog.ModalityType.APPLICATION_MODAL);
        d.setLayout(new BorderLayout());
        JPanel noi = new JPanel(new GridLayout(1, 2, 18, 0));
        noi.setBackground(ViewTheme.TRANG);
        noi.setBorder(new EmptyBorder(12, 16, 12, 16));

        JTextField ma = oForm(sua ? String.valueOf(model.getValueAt(hang, 1)) : String.format("%04d", demMa));
        ma.setEditable(false);
        JTextField ten = oForm(sua ? String.valueOf(model.getValueAt(hang, 2)) : "");
        JTextField sdt = oForm(sua ? String.valueOf(model.getValueAt(hang, 5)) : "");
        JTextField email = oForm(sua ? String.valueOf(model.getValueAt(hang, 6)) : "");
        JTextField cccd = oForm(sua ? String.valueOf(model.getValueAt(hang, 8)) : "");
        JComboBox<String> gioi = new JComboBox<String>(new String[]{"Nam", "Nữ", "Khác"});
        ViewTheme.cmbVienHop(gioi);
        if (sua) {
            gioi.setSelectedItem(model.getValueAt(hang, 3));
        }
        JTextField ngaySinh = oForm(sua ? String.valueOf(model.getValueAt(hang, 4)) : "");
        JTextField diaChi = oForm(sua ? String.valueOf(model.getValueAt(hang, 7)) : "");
        JTextField ghi = oForm(sua ? String.valueOf(model.getValueAt(hang, 9)) : "");
        JComboBox<String> chiNhanh = new JComboBox<String>(new String[]{"CN1", "Quang Thanh"});
        ViewTheme.cmbVienHop(chiNhanh);

        JPanel trai = cotForm("Thông tin khách hàng",
                new String[]{"Mã KH", "Họ và tên", "Số điện thoại", "Địa chỉ email", "CCCD", "Giới tính", "Ngày sinh", "Địa chỉ", "Ghi chú", "Chi nhánh"},
                new JComponent[]{ma, ten, sdt, email, cccd, gioi, ngaySinh, diaChi, ghi, chiNhanh},
                new boolean[]{false, true, false, false, false, false, false, false, false, false});

        JTextField lyDo = oForm(sua ? String.valueOf(model.getValueAt(hang, 12)) : "");
        JTextField tienSu = oForm(sua ? String.valueOf(model.getValueAt(hang, 13)) : "");
        JTextField diUng = oForm(sua ? String.valueOf(model.getValueAt(hang, 14)) : "");
        JComboBox<String> nhom = new JComboBox<>(new String[]{"NK01", "NK02", "NK03"});
        JComboBox<String> bacSi = new JComboBox<>();
        JComboBox<String> tuVan = new JComboBox<>();
        bacSi.addItem("");
        tuVan.addItem("");
        for (NhanVien nv : danhMucService.findAllNhanVien()) {
            String nhan = nv.getMaNhanVien() + " - " + nv.getHoTen();
            bacSi.addItem(nhan);
            tuVan.addItem(nhan);
        }
        ViewTheme.cmbVienHop(bacSi);
        ViewTheme.cmbVienHop(tuVan);
        if (sua) {
            chonMa(bacSi, String.valueOf(model.getValueAt(hang, 15)));
            chonMa(tuVan, String.valueOf(model.getValueAt(hang, 16)));
        }

        JPanel phai = new JPanel(new BorderLayout(0, 12));
        phai.setOpaque(false);
        phai.add(cotForm("Bệnh lý",
                new String[]{"Lý do đến khám", "Tiền sử bệnh", "Dị ứng", "Nhóm khách hàng"},
                new JComponent[]{lyDo, tienSu, diUng, nhom},
                new boolean[]{false, false, false, false, false}), BorderLayout.NORTH);
        phai.add(cotForm("Người phụ trách",
                new String[]{"Bác sĩ", "Tư vấn"},
                new JComponent[]{bacSi, tuVan},
                new boolean[]{false, false}), BorderLayout.CENTER);

        noi.add(trai);
        noi.add(phai);
        d.add(new JScrollPane(noi), BorderLayout.CENTER);

        JButton boQua = new JButton("Bỏ qua");
        JButton luu = new JButton(sua ? "Lưu" : "Thêm");
        ViewTheme.btnNutXam(boQua);
        ViewTheme.btnNutChinh(luu);
        boQua.addActionListener(e -> d.dispose());
        luu.addActionListener(e -> {
            if (ten.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(d, "Vui lòng nhập họ và tên.");
                return;
            }
            String maKH = sua ? String.valueOf(model.getValueAt(hang, 1)) : String.format("%04d", demMa);
            KhachHang kh = sua ? khachHangService.findById(maKH) : new KhachHang();
            if (kh == null) {
                kh = new KhachHang();
            }
            kh.setMaKH(maKH);
            kh.setHoTen(ten.getText().trim());
            kh.setGioiTinh(String.valueOf(gioi.getSelectedItem()));
            kh.setNgaySinh(NgayUtil.parseDate(ngaySinh.getText()));
            kh.setSoDienThoai(sdt.getText().trim());
            kh.setEmail(email.getText().trim());
            kh.setDiaChi(diaChi.getText().trim());
            kh.setCccd(cccd.getText().trim());
            kh.setGhiChu(ghi.getText().trim());
            kh.setMaNhom(String.valueOf(nhom.getSelectedItem()));
            String cn = String.valueOf(chiNhanh.getSelectedItem());
            kh.setMaChiNhanh(cn.startsWith("CN") ? cn.split(" ")[0] : "CN1");
            kh.setLyDoKham(lyDo.getText().trim());
            kh.setTienSuBenh(tienSu.getText().trim());
            kh.setDiUng(diUng.getText().trim());
            kh.setMaBacSi(maTuCombo(bacSi));
            kh.setMaTuVan(maTuCombo(tuVan));
            if (!sua) {
                kh.setSoLanKham(0);
                kh.setNgayTao(LocalDateTime.now());
            }
            try {
                if (sua) {
                    khachHangService.update(kh);
                } else {
                    if (khachHangService.add(kh) == null) {
                        JOptionPane.showMessageDialog(d, "Không thêm được khách hàng vào database.");
                        return;
                    }
                    demMa++;
                }
                d.dispose();
                taiLai();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(d, "Lỗi lưu khách hàng.\n" + ex.getMessage());
            }
        });
        JPanel chan = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
        chan.setBackground(ViewTheme.TRANG);
        chan.add(boQua);
        chan.add(luu);
        d.add(chan, BorderLayout.SOUTH);
        d.setSize(980, 640);
        d.setLocationRelativeTo(w);
        d.setVisible(true);
    }

    private JPanel cotForm(String tieu, String[] nhan, JComponent[] o, boolean[] batBuoc) {
        JPanel p = new JPanel(new GridLayout(0, 2, 8, 8));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createTitledBorder(tieu));
        for (int i = 0; i < nhan.length; i++) {
            JLabel lb = new JLabel(batBuoc[i] ? "<html>" + nhan[i] + " <font color='#e74c3c'>*</font></html>" : nhan[i]);
            lb.setFont(ViewTheme.FONT);
            p.add(lb);
            p.add(o[i]);
        }
        return p;
    }

    private JTextField oForm(String giaTri) {
        JTextField t = new JTextField(giaTri);
        ViewTheme.vienNhap(t);
        return t;
    }

    private JComboBox<String> hop(String dau, String... muc) {
        JComboBox<String> c = new JComboBox<String>();
        c.addItem(dau);
        for (String m : muc) {
            if (!m.equals(dau)) {
                c.addItem(m);
            }
        }
        ViewTheme.cmbVienHop(c);
        return c;
    }

    private JTextField o(String goi) {
        JTextField t = new ONhap(goi, 12);
        t.setPreferredSize(new Dimension(160, 32));
        return t;
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    private static String maTuCombo(JComboBox<String> cbo) {
        Object sel = cbo.getSelectedItem();
        if (sel == null) {
            return null;
        }
        String s = String.valueOf(sel).trim();
        if (s.isEmpty()) {
            return null;
        }
        int i = s.indexOf(" - ");
        return i > 0 ? s.substring(0, i).trim() : s;
    }

    private static void chonMa(JComboBox<String> cbo, String ma) {
        if (ma == null || ma.isBlank()) {
            cbo.setSelectedIndex(0);
            return;
        }
        for (int i = 0; i < cbo.getItemCount(); i++) {
            if (cbo.getItemAt(i).startsWith(ma)) {
                cbo.setSelectedIndex(i);
                return;
            }
        }
    }
}
