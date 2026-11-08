package view;

import entity.KhachHang;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.PhieuChi;
import entity.PhieuThu;
import entity.Refs;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import service.impl.DanhMuc_Service;
import service.impl.KhachHang_Service;
import service.impl.PhieuChi_Service;
import service.impl.PhieuThu_Service;

public class PhieuTienPanel extends JPanel {

    private final boolean laPhieuThu;
    private final DefaultTableModel model;
    private final JTable tblBang;
    private final JTextField txtTim = new ONhap("", 24);
    private final JLabel lblNhanDem = new JLabel();
    private final PhieuThu_Service thuSvc = new PhieuThu_Service();
    private final PhieuChi_Service chiSvc = new PhieuChi_Service();
    private final KhachHang_Service khSvc = new KhachHang_Service();
    private final DanhMuc_Service dmSvc = new DanhMuc_Service();

    public PhieuTienPanel(boolean laPhieuThu) {
        this.laPhieuThu = laPhieuThu;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));
        String[] cot = laPhieuThu
                ? new String[]{"Mã", "Ngày thu", "Mã KH", "Khách hàng", "Lý do", "Số tiền", "Hình thức thanh toán", "Trạng thái", "Bác sĩ", "Phụ tá", "Người giới thiệu", "Ngày tạo", "Người tạo"}
                : new String[]{"Mã phiếu chi", "Nhà cung cấp", "Loại chi tiêu", "Lý do chi", "Số tiền", "Ngày chi", "Hình thức thanh toán", "Trạng thái", "Ngày tạo", "Người tạo"};
        model = ViewTheme.taoModel(cot, true, true);
        tblBang = new JTable(model);
        ViewTheme.trangTriBang(tblBang);
        ViewTheme.ganThaoTac(tblBang, this::xuLy);

        JPanel pnlThe = new JPanel(new BorderLayout(0, 8));
        pnlThe.setBackground(ViewTheme.TRANG);
        pnlThe.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel lblNhanTieuDe = ViewTheme.tieuDeTrang(laPhieuThu ? "Phiếu thu" : "Phiếu chi");
        JButton btnThem = new JButton(laPhieuThu ? "Thêm phiếu thu" : "Thêm phiếu chi");
        ViewTheme.nutXanh(btnThem);
        btnThem.addActionListener(e -> moForm(-1));
        JPanel pnlDau = new JPanel(new BorderLayout());
        pnlDau.setOpaque(false);
        pnlDau.add(lblNhanTieuDe, BorderLayout.WEST);
        JPanel panelPhai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panelPhai.setOpaque(false);
        panelPhai.add(btnThem);
        pnlDau.add(panelPhai, BorderLayout.EAST);

        ((ONhap) txtTim).datGoiY(laPhieuThu ? "Tìm phiếu thu..." : "Tìm phiếu chi...");
        txtTim.setPreferredSize(new Dimension(360, 32));
        txtTim.addActionListener(e -> loc());

        JPanel pnlLoc = new JPanel(new BorderLayout(0, 8));
        pnlLoc.setBackground(ViewTheme.NEN_TIM);
        pnlLoc.setBorder(new EmptyBorder(10, 12, 10, 12));
        pnlLoc.add(ViewTheme.chuPhu("Tìm kiếm"), BorderLayout.NORTH);
        pnlLoc.add(txtTim, BorderLayout.CENTER);

        JScrollPane scrBang = new JScrollPane(tblBang);
        scrBang.setPreferredSize(new Dimension(400, laPhieuThu ? 180 : 140));
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
        taiLai();
    }

    public void taiLai() {
        model.setRowCount(0);
        if (laPhieuThu) {
            for (PhieuThu phieuThu : thuSvc.findAllPhieuThu()) {
                model.addRow(ViewTheme.dongBang(new Object[]{
                    phieuThu.getMaPhieuThu(),
                    NgayUtil.fmt(phieuThu.getNgayThu()),
                    entity.Refs.maKh(phieuThu.getKhachHang()),
                    entity.Refs.tenKh(phieuThu.getKhachHang()),
                    phieuThu.getLyDo(),
                    ViewTheme.tien(phieuThu.getSoTien()),
                    phieuThu.getHinhThucThanhToan(),
                    phieuThu.getTrangThai(),
                    phieuThu.getBacSi() == null ? "" : entity.Refs.tenNv(phieuThu.getBacSi()),
                    "",
                    phieuThu.getNguoiGioiThieu() == null ? "" : phieuThu.getNguoiGioiThieu(),
                    NgayUtil.fmtFull(phieuThu.getNgayTao()),
                    phieuThu.getNguoiTao() == null ? "" : phieuThu.getNguoiTao()
                }, true, true));
            }
        } else {
            for (PhieuChi phieuChi : chiSvc.findAllPhieuChi()) {
                model.addRow(ViewTheme.dongBang(new Object[]{
                    phieuChi.getMaPhieuChi(),
                    entity.Refs.tenNcc(phieuChi.getNhaCungCap()),
                    phieuChi.getLoaiChiTieu(),
                    phieuChi.getLyDoChi(),
                    ViewTheme.tien(phieuChi.getSoTien()),
                    NgayUtil.fmt(phieuChi.getNgayChi()),
                    phieuChi.getHinhThucThanhToan(),
                    phieuChi.getTrangThai(),
                    NgayUtil.fmtFull(phieuChi.getNgayTao()),
                    phieuChi.getNguoiTao() == null ? "" : phieuChi.getNguoiTao()
                }, true, true));
            }
        }
        lblNhanDem.setText(ViewTheme.demDong(model.getRowCount() == 0 ? 0 : 1, model.getRowCount(), model.getRowCount()));
    }

    public void themPhieu(String ngayThu, String maKh, String tenKh, String lyDo, String soTien,
            String hinhThuc, String trangThai) {
        if (!laPhieuThu) {
            return;
        }
        PhieuThu phieuThu = new PhieuThu();
        phieuThu.setMaPhieuThu(maThuMoi());
        phieuThu.setNgayThu(NgayUtil.parseDate(ngayThu) != null ? NgayUtil.parseDate(ngayThu) : LocalDate.now());
        phieuThu.setKhachHang(entity.Refs.khachHang(maKh));
        phieuThu.setLyDo(lyDo);
        phieuThu.setSoTien(parseTien(soTien));
        phieuThu.setHinhThucThanhToan(hinhThuc);
        phieuThu.setTrangThai(trangThai);
        phieuThu.setNgayTao(LocalDateTime.now());
        phieuThu.setNguoiTao(Refs.nhanVien(DuLieuMau.NGUOI));
        thuSvc.add(phieuThu);
        taiLai();
    }

    private void loc() {
        String tuKhoa = txtTim.getText().trim();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tblBang.setRowSorter(sorter);
        if (tuKhoa.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(tuKhoa)));
        }
    }

    private void xuLy(String lenh, int hang) {
        if ("Xóa".equals(lenh)) {
            if (JOptionPane.showConfirmDialog(this, "Xóa phiếu này?", "Xác nhận", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return;
            }
            String ma = String.valueOf(model.getValueAt(hang, 1));
            if (laPhieuThu) {
                thuSvc.delete(ma);
            } else {
                chiSvc.delete(ma);
            }
            taiLai();
            return;
        }
        if ("Xem".equals(lenh)) {
            StringBuilder noiDung = new StringBuilder();
            for (int c = 1; c < model.getColumnCount() - 1; c++) {
                noiDung.append(model.getColumnName(c)).append(": ").append(model.getValueAt(hang, c)).append("\n");
            }
            JOptionPane.showMessageDialog(this, noiDung.toString(), "Chi tiết phiếu", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        moForm(hang);
    }

    private void moForm(int hang) {
        boolean sua = hang >= 0;
        HopThoai hopThoai = new HopThoai(SwingUtilities.getWindowAncestor(this),
                sua ? "Cập nhật phiếu" : (laPhieuThu ? "Thêm phiếu thu" : "Thêm phiếu chi"),
                sua ? "Lưu" : "Thêm");
        if (laPhieuThu) {
            JComboBox<String> cmbKhach = hopThoai.chon("Khách hàng", true, dsKhach());
            JTextField txtLyDo = hopThoai.chu("Lý do", sua ? String.valueOf(model.getValueAt(hang, 5)) : "", false);
            JTextField txtTien = hopThoai.chu("Số tiền", sua ? String.valueOf(model.getValueAt(hang, 6)) : "", true);
            JComboBox<String> cmbHinhThuc = hopThoai.chon("Hình thức thanh toán", true, "Tiền mặt", "Thẻ", "Chuyển khoản");
            JComboBox<String> cmbTrangThai = hopThoai.chon("Trạng thái", false, "Đã chi trả", "Chưa chi trả");
            hopThoai.hien(() -> {
                String chon = String.valueOf(cmbKhach.getSelectedItem());
                String maKh = chon.contains(" - ") ? chon.substring(0, chon.indexOf(" - ")).trim() : chon;
                PhieuThu phieuThu = sua ? thuSvc.findById(String.valueOf(model.getValueAt(hang, 1))) : new PhieuThu();
                if (phieuThu == null) {
                    phieuThu = new PhieuThu();
                }
                if (!sua) {
                    phieuThu.setMaPhieuThu(maThuMoi());
                    phieuThu.setNgayTao(LocalDateTime.now());
                    phieuThu.setNguoiTao(Refs.nhanVien(DuLieuMau.NGUOI));
                }
                phieuThu.setNgayThu(LocalDate.now());
                phieuThu.setKhachHang(entity.Refs.khachHang(maKh));
                phieuThu.setLyDo(txtLyDo.getText().trim());
                phieuThu.setSoTien(parseTien(txtTien.getText()));
                phieuThu.setHinhThucThanhToan(String.valueOf(cmbHinhThuc.getSelectedItem()));
                phieuThu.setTrangThai(String.valueOf(cmbTrangThai.getSelectedItem()));
                if (sua) {
                    thuSvc.update(phieuThu);
                } else if (thuSvc.add(phieuThu) == null) {
                    JOptionPane.showMessageDialog(this, "Không thêm được phiếu thu.");
                    return;
                }
                taiLai();
            });
        } else {
            JComboBox<String> cmbNhaCungCap = hopThoai.chon("Nhà cung cấp", true, dsNcc());
            JComboBox<String> cmbLoai = hopThoai.chon("Loại chi tiêu", true, "Vật tư", "Lương", "Labo", "Mặt bằng");
            JTextField txtLyDo = hopThoai.chu("Lý do chi", sua ? String.valueOf(model.getValueAt(hang, 4)) : "", true);
            JTextField txtTien = hopThoai.chu("Số tiền", sua ? String.valueOf(model.getValueAt(hang, 5)) : "", true);
            JComboBox<String> cmbHinhThuc = hopThoai.chon("Phương thức thanh toán", true, "Tiền mặt", "Thẻ", "Chuyển khoản");
            hopThoai.hien(() -> {
                PhieuChi phieuChi = sua ? chiSvc.findById(String.valueOf(model.getValueAt(hang, 1))) : new PhieuChi();
                if (phieuChi == null) {
                    phieuChi = new PhieuChi();
                }
                if (!sua) {
                    phieuChi.setMaPhieuChi(maChiMoi());
                    phieuChi.setNgayTao(LocalDateTime.now());
                    phieuChi.setNguoiTao(Refs.nhanVien(DuLieuMau.NGUOI));
                }
                String tenNhaCungCap = String.valueOf(cmbNhaCungCap.getSelectedItem());
                phieuChi.setNhaCungCap(entity.Refs.nhaCungCap(maNccTheoTen(tenNhaCungCap)));
                phieuChi.setLoaiChiTieu(String.valueOf(cmbLoai.getSelectedItem()));
                phieuChi.setLyDoChi(txtLyDo.getText().trim());
                phieuChi.setSoTien(parseTien(txtTien.getText()));
                phieuChi.setNgayChi(LocalDate.now());
                phieuChi.setHinhThucThanhToan(String.valueOf(cmbHinhThuc.getSelectedItem()));
                phieuChi.setTrangThai("Đã chi");
                if (sua) {
                    chiSvc.update(phieuChi);
                } else if (chiSvc.add(phieuChi) == null) {
                    JOptionPane.showMessageDialog(this, "Không thêm được phiếu chi.");
                    return;
                }
                taiLai();
            });
        }
    }

    private String[] dsKhach() {
        List<String> ketQua = new ArrayList<>();
        for (KhachHang khachHang : khSvc.findAllKhachHang()) {
            ketQua.add(khachHang.getMaKH() + " - " + khachHang.getHoTen());
        }
        return ketQua.isEmpty() ? DuLieuMau.KHACH : ketQua.toArray(new String[0]);
    }

    private String[] dsNcc() {
        List<String> ketQua = new ArrayList<>();
        for (NhaCungCap nhaCungCap : dmSvc.findAllNhaCungCap()) {
            ketQua.add(nhaCungCap.getTenNhaCungCap());
        }
        return ketQua.isEmpty() ? new String[]{"STRAUMANN"} : ketQua.toArray(new String[0]);
    }

    private NhaCungCap findNcc(String ma) {
        for (NhaCungCap nhaCungCap : dmSvc.findAllNhaCungCap()) {
            if (ma.equals(nhaCungCap.getMaNhaCungCap())) {
                return nhaCungCap;
            }
        }
        return null;
    }

    private String maNccTheoTen(String ten) {
        for (NhaCungCap nhaCungCap : dmSvc.findAllNhaCungCap()) {
            if (ten.equals(nhaCungCap.getTenNhaCungCap())) {
                return nhaCungCap.getMaNhaCungCap();
            }
        }
        return "NCC01";
    }

    private String maThuMoi() {
        return String.format("%04d", thuSvc.nextSo() + 1);
    }

    private String maChiMoi() {
        return String.format("PC%02d", chiSvc.nextSo() + 1);
    }

    private static long parseTien(String s) {
        String so = s == null ? "" : s.replaceAll("[^0-9]", "");
        if (so.isEmpty()) {
            return 0;
        }
        try {
            return Long.parseLong(so);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
