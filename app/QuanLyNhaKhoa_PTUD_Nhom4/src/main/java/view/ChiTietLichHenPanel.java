package view;

import entity.KhachHang;
import entity.LichHen;
import entity.NhanVien;
import repository.impl.NhanVien_DAO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import service.impl.DanhMuc_Service;
import service.impl.KhachHang_Service;
import service.impl.LichHen_Service;
import view.model.CuocHen;

public class ChiTietLichHenPanel extends JPanel {

    private final LichHen_Service lichHenService = new LichHen_Service();
    private final KhachHang_Service khachHangService = new KhachHang_Service();
    private final DanhMuc_Service danhMucService = new DanhMuc_Service();
    
    private final Runnable suKienQuayLai;
    private final CuocHen cuocHen;
    
    

    public ChiTietLichHenPanel(CuocHen cuocHen, Runnable suKienQuayLai) {
        this.cuocHen = cuocHen;
        this.suKienQuayLai = suKienQuayLai;

        setLayout(new BorderLayout(0, 10));
        setBackground(ViewTheme.TRANG);
        setBorder(new EmptyBorder(12, 16, 12, 16));

        add(taoThanhTieuDe(), BorderLayout.NORTH);
        add(taoNoiDungChiTiet(), BorderLayout.CENTER);
    }

    private JPanel taoThanhTieuDe() {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setOpaque(false);

        JLabel lblTieuDe = new JLabel("Chi Tiết Lịch Hẹn: " + cuocHen.maLichHen);
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTieuDe.setForeground(ViewTheme.XANH_DAM);

        JButton btnQuayLai = new JButton("‹ Quay lại danh sách");
        ViewTheme.nutXam(btnQuayLai);
        btnQuayLai.addActionListener(e -> {
            if (suKienQuayLai != null) {
                suKienQuayLai.run();
            }
        });

        pnl.add(btnQuayLai, BorderLayout.WEST);
        pnl.add(lblTieuDe, BorderLayout.EAST);
        return pnl;
    }

    private JScrollPane taoNoiDungChiTiet() {
        JPanel pnlNoiDung = new JPanel(new GridBagLayout());
        pnlNoiDung.setBackground(ViewTheme.TRANG);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        KhachHang kh = khachHangService.findById(cuocHen.maKH);
        LichHen lh = lichHenService.findById(cuocHen.maLichHen);

        int dong = 0;
        
        // Nhóm thông tin Khách hàng
        themDongThongTin(pnlNoiDung, gbc, dong++, "Mã lịch hẹn:", cuocHen.maLichHen);
        themDongThongTin(pnlNoiDung, gbc, dong++, "Khách hàng:", cuocHen.khach + (kh != null && kh.getSoDienThoai() != null ? " (SĐT: " + kh.getSoDienThoai() + ")" : ""));
        themDongThongTin(pnlNoiDung, gbc, dong++, "Thời gian bắt đầu:", cuocHen.batDau.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        themDongThongTin(pnlNoiDung, gbc, dong++, "Thời lượng:", cuocHen.phut + " phút");
        themDongThongTin(pnlNoiDung, gbc, dong++, "Bác sĩ phụ trách:", cuocHen.bacSi.isEmpty() ? "Chưa phân công" : cuocHen.bacSi);
        themDongThongTin(pnlNoiDung, gbc, dong++, "Phụ tá:", cuocHen.phuTa.isEmpty() ? "Không có" : cuocHen.phuTa);
        themDongThongTin(pnlNoiDung, gbc, dong++, "Trạng thái:", cuocHen.trangThai);
        themDongThongTin(pnlNoiDung, gbc, dong++, "Ghi chú:", cuocHen.ghiChu.isEmpty() ? "Không có ghi chú" : cuocHen.ghiChu);

        // Panel chứa các nút tác vụ nhanh
        JPanel pnlTacVu = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnlTacVu.setOpaque(false);
        
        String maKeHoachLienKet = trichXuatMaKeHoach(cuocHen.ghiChu);
        
        if (maKeHoachLienKet != null && !maKeHoachLienKet.isEmpty()) {
            JButton btnXemKeHoach = new JButton("👁 Xem Kế Hoạch Điều Trị (" + maKeHoachLienKet + ")");
            ViewTheme.nutXanh(btnXemKeHoach);
            btnXemKeHoach.addActionListener(e -> {
                // Thực hiện chuyển đổi màn hình sang trang KeHoachDieuTriPanel tương ứng
                Container parent = this.getParent();
                if (parent != null) {
                    parent.removeAll();
                    KeHoachDieuTriPanel panelKH = new KeHoachDieuTriPanel(maKeHoachLienKet);
                    parent.add(panelKH, BorderLayout.CENTER);
                    parent.revalidate();
                    parent.repaint();
                }
            });
            pnlTacVu.add(btnXemKeHoach);
        }
        
        JButton btnHuyLich = new JButton("Hủy lịch hẹn này");
        ViewTheme.nutXam(btnHuyLich);
        btnHuyLich.setForeground(Color.RED);
        btnHuyLich.addActionListener(e -> {
            int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy lịch hẹn " + cuocHen.maLichHen + " không?", "Xác nhận hủy", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                try {
                    if (lh != null) {
                        lh.setTrangThai("Hủy bỏ");
                        lichHenService.update(lh);
                        JOptionPane.showMessageDialog(this, "Đã hủy lịch hẹn thành công!");
                        if (suKienQuayLai != null) suKienQuayLai.run();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi hủy lịch hẹn: " + ex.getMessage());
                }
            }
        });

        pnlTacVu.add(btnHuyLich);

        gbc.gridx = 0;
        gbc.gridy = dong;
        gbc.gridwidth = 2;
        pnlNoiDung.add(pnlTacVu, gbc);

        JScrollPane scrollPane = new JScrollPane(pnlNoiDung);
        scrollPane.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        scrollPane.getViewport().setBackground(ViewTheme.TRANG);
        return scrollPane;
    }

    private void themDongThongTin(JPanel pnl, GridBagConstraints gbc, int hang, String nhan,String giaTri) {
        gbc.gridx = 0;
        gbc.gridy = hang;
        gbc.weightx = 0.3;
        JLabel lblNhan = new JLabel(nhan);
        lblNhan.setFont(ViewTheme.FONT_DAM);
        pnl.add(lblNhan, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JLabel lblGiaTri = new JLabel(giaTri.toString());
        lblGiaTri.setFont(ViewTheme.FONT);
        pnl.add(lblGiaTri, gbc);
    }
    
    private String trichXuatMaKeHoach(String ghiChu) {
        if (ghiChu == null || !ghiChu.contains("[KHDT:")) {
            return null;
        }
        try {
            int batDau = ghiChu.indexOf("[KHDT:") + 6;
            int ketThuc = ghiChu.indexOf("]", batDau);
            if (ketThuc > batDau) {
                return ghiChu.substring(batDau, ketThuc).trim();
            }
        } catch (Exception ignored) {}
        return null;
    }
    
    
}