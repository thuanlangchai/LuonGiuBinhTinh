package view;

import entity.KhachHang;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.PhieuChi;
import entity.PhieuThu;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import service.impl.DanhMuc_Service;
import service.impl.KhachHang_Service;
import service.impl.PhieuChi_Service;
import service.impl.PhieuThu_Service;

public class PhieuTienPanel extends JPanel {

    private final boolean laPhieuThu;
    private final DefaultTableModel model;
    private final JTable bang;
    private final JTextField tim = new ONhap("", 24);
    private final JLabel demLb = new JLabel();
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
        bang = new JTable(model);
        ViewTheme.trangTriBang(bang);
        ViewTheme.ganThaoTac(bang, this::xuLy);

        JPanel the = new JPanel(new BorderLayout(0, 8));
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel tieu = ViewTheme.tieuDeTrang(laPhieuThu ? "Phiếu thu" : "Phiếu chi");
        JButton them = new JButton(laPhieuThu ? "Thêm phiếu thu" : "Thêm phiếu chi");
        ViewTheme.nutXanh(them);
        them.addActionListener(e -> moForm(-1));
        JPanel dau = new JPanel(new BorderLayout());
        dau.setOpaque(false);
        dau.add(tieu, BorderLayout.WEST);
        JPanel phai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        phai.setOpaque(false);
        phai.add(them);
        dau.add(phai, BorderLayout.EAST);

        ((ONhap) tim).datGoiY(laPhieuThu ? "Tìm phiếu thu..." : "Tìm phiếu chi...");
        tim.setPreferredSize(new Dimension(360, 32));
        tim.addActionListener(e -> loc());

        JPanel loc = new JPanel(new BorderLayout(0, 8));
        loc.setBackground(ViewTheme.NEN_TIM);
        loc.setBorder(new EmptyBorder(10, 12, 10, 12));
        loc.add(ViewTheme.chuPhu("Tìm kiếm"), BorderLayout.NORTH);
        loc.add(tim, BorderLayout.CENTER);

        JScrollPane cuon = new JScrollPane(bang);
        cuon.setPreferredSize(new Dimension(400, laPhieuThu ? 180 : 140));
        cuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        cuon.getViewport().setBackground(ViewTheme.TRANG);
        demLb.setFont(ViewTheme.FONT_NHO);
        demLb.setForeground(ViewTheme.CHU_NHAT);

        the.add(dau, BorderLayout.NORTH);
        JPanel giua = new JPanel(new BorderLayout(0, 8));
        giua.setOpaque(false);
        giua.add(loc, BorderLayout.NORTH);
        giua.add(cuon, BorderLayout.CENTER);
        giua.add(demLb, BorderLayout.SOUTH);
        the.add(giua, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(the, BorderLayout.NORTH);
        taiLai();
    }

    public void taiLai() {
        model.setRowCount(0);
        if (laPhieuThu) {
            for (PhieuThu p : thuSvc.findAllPhieuThu()) {
                KhachHang kh = khSvc.findById(p.getMaKH());
                NhanVien bs = p.getMaBacSi() == null ? null : dmSvc.findNhanVienById(p.getMaBacSi());
                model.addRow(ViewTheme.dongBang(new Object[]{
                    p.getMaPhieuThu(),
                    NgayUtil.fmt(p.getNgayThu()),
                    p.getMaKH(),
                    kh != null ? kh.getHoTen() : "",
                    p.getLyDo(),
                    ViewTheme.tien(p.getSoTien()),
                    p.getHinhThucThanhToan(),
                    p.getTrangThai(),
                    bs != null ? bs.getHoTen() : "",
                    "",
                    p.getNguoiGioiThieu() == null ? "" : p.getNguoiGioiThieu(),
                    NgayUtil.fmtFull(p.getNgayTao()),
                    p.getNguoiTao() == null ? "" : p.getNguoiTao()
                }, true, true));
            }
        } else {
            for (PhieuChi p : chiSvc.findAllPhieuChi()) {
                NhaCungCap ncc = p.getMaNhaCungCap() == null ? null : findNcc(p.getMaNhaCungCap());
                model.addRow(ViewTheme.dongBang(new Object[]{
                    p.getMaPhieuChi(),
                    ncc != null ? ncc.getTenNhaCungCap() : p.getMaNhaCungCap(),
                    p.getLoaiChiTieu(),
                    p.getLyDoChi(),
                    ViewTheme.tien(p.getSoTien()),
                    NgayUtil.fmt(p.getNgayChi()),
                    p.getHinhThucThanhToan(),
                    p.getTrangThai(),
                    NgayUtil.fmtFull(p.getNgayTao()),
                    p.getNguoiTao() == null ? "" : p.getNguoiTao()
                }, true, true));
            }
        }
        demLb.setText(ViewTheme.demDong(model.getRowCount() == 0 ? 0 : 1, model.getRowCount(), model.getRowCount()));
    }

    public void themPhieu(String ngayThu, String maKh, String tenKh, String lyDo, String soTien,
            String hinhThuc, String trangThai) {
        if (!laPhieuThu) {
            return;
        }
        PhieuThu p = new PhieuThu();
        p.setMaPhieuThu(maThuMoi());
        p.setNgayThu(NgayUtil.parseDate(ngayThu) != null ? NgayUtil.parseDate(ngayThu) : LocalDate.now());
        p.setMaKH(maKh);
        p.setLyDo(lyDo);
        p.setSoTien(parseTien(soTien));
        p.setHinhThucThanhToan(hinhThuc);
        p.setTrangThai(trangThai);
        p.setNgayTao(LocalDateTime.now());
        p.setNguoiTao(DuLieuMau.NGUOI);
        thuSvc.add(p);
        taiLai();
    }

    private void loc() {
        String tu = tim.getText().trim();
        javax.swing.table.TableRowSorter<DefaultTableModel> sx = new javax.swing.table.TableRowSorter<>(model);
        bang.setRowSorter(sx);
        if (tu.isEmpty()) {
            sx.setRowFilter(null);
        } else {
            sx.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(tu)));
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
            StringBuilder sb = new StringBuilder();
            for (int c = 1; c < model.getColumnCount() - 1; c++) {
                sb.append(model.getColumnName(c)).append(": ").append(model.getValueAt(hang, c)).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Chi tiết phiếu", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        moForm(hang);
    }

    private void moForm(int hang) {
        boolean sua = hang >= 0;
        HopThoai h = new HopThoai(SwingUtilities.getWindowAncestor(this),
                sua ? "Cập nhật phiếu" : (laPhieuThu ? "Thêm phiếu thu" : "Thêm phiếu chi"),
                sua ? "Lưu" : "Thêm");
        if (laPhieuThu) {
            JComboBox<String> khach = h.chon("Khách hàng", true, dsKhach());
            JTextField lyDo = h.chu("Lý do", sua ? String.valueOf(model.getValueAt(hang, 5)) : "", false);
            JTextField tien = h.chu("Số tiền", sua ? String.valueOf(model.getValueAt(hang, 6)) : "", true);
            JComboBox<String> ht = h.chon("Hình thức thanh toán", true, "Tiền mặt", "Thẻ", "Chuyển khoản");
            JComboBox<String> tt = h.chon("Trạng thái", false, "Đã chi trả", "Chưa chi trả");
            h.hien(() -> {
                String sel = String.valueOf(khach.getSelectedItem());
                String maKh = sel.contains(" - ") ? sel.substring(0, sel.indexOf(" - ")).trim() : sel;
                PhieuThu p = sua ? thuSvc.findById(String.valueOf(model.getValueAt(hang, 1))) : new PhieuThu();
                if (p == null) {
                    p = new PhieuThu();
                }
                if (!sua) {
                    p.setMaPhieuThu(maThuMoi());
                    p.setNgayTao(LocalDateTime.now());
                    p.setNguoiTao(DuLieuMau.NGUOI);
                }
                p.setNgayThu(LocalDate.now());
                p.setMaKH(maKh);
                p.setLyDo(lyDo.getText().trim());
                p.setSoTien(parseTien(tien.getText()));
                p.setHinhThucThanhToan(String.valueOf(ht.getSelectedItem()));
                p.setTrangThai(String.valueOf(tt.getSelectedItem()));
                if (sua) {
                    thuSvc.update(p);
                } else if (thuSvc.add(p) == null) {
                    JOptionPane.showMessageDialog(this, "Không thêm được phiếu thu.");
                    return;
                }
                taiLai();
            });
        } else {
            JComboBox<String> ncc = h.chon("Nhà cung cấp", true, dsNcc());
            JComboBox<String> loai = h.chon("Loại chi tiêu", true, "Vật tư", "Lương", "Labo", "Mặt bằng");
            JTextField lyDo = h.chu("Lý do chi", sua ? String.valueOf(model.getValueAt(hang, 4)) : "", true);
            JTextField tien = h.chu("Số tiền", sua ? String.valueOf(model.getValueAt(hang, 5)) : "", true);
            JComboBox<String> ht = h.chon("Phương thức thanh toán", true, "Tiền mặt", "Thẻ", "Chuyển khoản");
            h.hien(() -> {
                PhieuChi p = sua ? chiSvc.findById(String.valueOf(model.getValueAt(hang, 1))) : new PhieuChi();
                if (p == null) {
                    p = new PhieuChi();
                }
                if (!sua) {
                    p.setMaPhieuChi(maChiMoi());
                    p.setNgayTao(LocalDateTime.now());
                    p.setNguoiTao(DuLieuMau.NGUOI);
                }
                String tenNcc = String.valueOf(ncc.getSelectedItem());
                p.setMaNhaCungCap(maNccTheoTen(tenNcc));
                p.setLoaiChiTieu(String.valueOf(loai.getSelectedItem()));
                p.setLyDoChi(lyDo.getText().trim());
                p.setSoTien(parseTien(tien.getText()));
                p.setNgayChi(LocalDate.now());
                p.setHinhThucThanhToan(String.valueOf(ht.getSelectedItem()));
                p.setTrangThai("Đã chi");
                if (sua) {
                    chiSvc.update(p);
                } else if (chiSvc.add(p) == null) {
                    JOptionPane.showMessageDialog(this, "Không thêm được phiếu chi.");
                    return;
                }
                taiLai();
            });
        }
    }

    private String[] dsKhach() {
        List<String> kq = new ArrayList<>();
        for (KhachHang kh : khSvc.findAllKhachHang()) {
            kq.add(kh.getMaKH() + " - " + kh.getHoTen());
        }
        return kq.isEmpty() ? DuLieuMau.KHACH : kq.toArray(new String[0]);
    }

    private String[] dsNcc() {
        List<String> kq = new ArrayList<>();
        for (NhaCungCap n : dmSvc.findAllNhaCungCap()) {
            kq.add(n.getTenNhaCungCap());
        }
        return kq.isEmpty() ? new String[]{"STRAUMANN"} : kq.toArray(new String[0]);
    }

    private NhaCungCap findNcc(String ma) {
        for (NhaCungCap n : dmSvc.findAllNhaCungCap()) {
            if (ma.equals(n.getMaNhaCungCap())) {
                return n;
            }
        }
        return null;
    }

    private String maNccTheoTen(String ten) {
        for (NhaCungCap n : dmSvc.findAllNhaCungCap()) {
            if (ten.equals(n.getTenNhaCungCap())) {
                return n.getMaNhaCungCap();
            }
        }
        return "NCC01";
    }

    private String maThuMoi() {
        int max = 0;
        for (PhieuThu p : thuSvc.findAllPhieuThu()) {
            try {
                max = Math.max(max, Integer.parseInt(p.getMaPhieuThu()));
            } catch (Exception ignored) {
            }
        }
        return String.format("%04d", max + 1);
    }

    private String maChiMoi() {
        int max = 0;
        for (PhieuChi p : chiSvc.findAllPhieuChi()) {
            String ma = p.getMaPhieuChi();
            if (ma != null && ma.startsWith("PC")) {
                try {
                    max = Math.max(max, Integer.parseInt(ma.substring(2)));
                } catch (Exception ignored) {
                }
            }
        }
        return String.format("PC%02d", max + 1);
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
