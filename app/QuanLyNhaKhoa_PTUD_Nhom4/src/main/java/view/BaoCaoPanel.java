package view;

import entity.ChiTietKeHoachDieuTri;
import entity.HoaDon;
import entity.KeHoachDieuTri;
import entity.KhachHang;
import entity.LichHen;
import entity.PhieuChi;
import entity.PhieuThu;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import service.impl.BaoCao_Service;
import service.impl.KhachHang_Service;
import view.model.ChiTietToaThuocView;
import view.model.ToaThuocView;

public class BaoCaoPanel extends JPanel implements ActionListener{

    private String ma = "tongquan";
    private JTable tblThuChiHienTai;

    public BaoCaoPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());
        chon("tongquan");
    }

    public void chon(String maMoi) {
        this.ma = maMoi;
        removeAll();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(6, 6, 16, 6));
        JPanel pnlThe = new JPanel(new BorderLayout(0, 8));
        pnlThe.setBackground(ViewTheme.TRANG);
        pnlThe.setBorder(new EmptyBorder(12, 14, 12, 14));
        if ("donthuoc".equals(ma)) {
            pnlThe.add(pnlDonThuoc(), BorderLayout.CENTER);
        } else if ("thuchi".equals(ma) || "thuchihangngay".equals(ma)) {
            pnlThe.add(pnlThuChi("thuchihangngay".equals(ma) ? "Báo cáo thu chi hàng ngày" : "Báo cáo thu chi"), BorderLayout.CENTER);
        } else if ("dtdichvu".equals(ma)) {
            pnlThe.add(pnlDoanhThuDichVu(), BorderLayout.CENTER);
        } else if ("dtcanhan".equals(ma)) {
            pnlThe.add(pnlDoanhThuCaNhan(), BorderLayout.CENTER);
        } else {
            pnlThe.add(pnlMauChung(tieuCua(ma), cotCua(ma), dongCua(ma)), BorderLayout.CENTER);
        }
        add(pnlThe, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    private JPanel pnlDonThuoc() {
        JPanel pnl = new JPanel(new BorderLayout(0, 8));
        pnl.setOpaque(false);
        pnl.add(dau("Báo cáo toa thuốc", true), BorderLayout.NORTH);
        long tongTien = 0;
        List<ToaThuocView> danhSach = ToaThuocDuLieu.tatCa();
        Object[][] dong = new Object[Math.max(1, danhSach.size())][];
        int i = 0;
        for (ToaThuocView toaThuoc : danhSach) {
            StringBuilder chiTiet = new StringBuilder();
            for (ChiTietToaThuocView ct : toaThuoc.chiTiet) {
                if (chiTiet.length() > 0) {
                    chiTiet.append("; ");
                }
                chiTiet.append(ct.tongSo).append(" x ").append(ct.tenThuoc)
                        .append(" (").append(ViewTheme.tien(ct.donGia)).append(")");
            }
            tongTien += toaThuoc.thanhTien;
            dong[i] = new Object[]{
                String.valueOf(i + 1), toaThuoc.maToaThuoc, toaThuoc.ngayTao, toaThuoc.tenKh, chiTiet.toString(),
                toaThuoc.hinhThucThanhToan, ViewTheme.tien(toaThuoc.thanhTien), "0",
                ViewTheme.tien(toaThuoc.thanhTien), toaThuoc.tenBacSi
            };
            i++;
        }
        if (danhSach.isEmpty()) {
            dong[0] = new Object[]{"", "", "", "", "", "", "0", "0", "0", ""};
        }
        JPanel pnlTomTat = new JPanel(new GridLayout(1, 3, 12, 0));
        pnlTomTat.setOpaque(false);
        pnlTomTat.add(oSo("Tổng tiền thuốc", ViewTheme.tien(tongTien)));
        pnlTomTat.add(oSo("Tổng chiết khấu", "0"));
        pnlTomTat.add(oSo("Thành tiền", ViewTheme.tien(tongTien)));
        JPanel pnlTren = new JPanel(new BorderLayout(12, 0));
        pnlTren.setOpaque(false);
        pnlTren.add(oNgay(), BorderLayout.WEST);
        pnlTren.add(pnlTomTat, BorderLayout.CENTER);
        String[] cot = {"#", "Mã toa thuốc", "Ngày tạo", "Khách hàng", "Toa thuốc chi tiết", "Hình thức TT", "Tiền thuốc", "Chiết khấu", "Thành tiền", "Bác sĩ"};
        JPanel pnlThan = new JPanel(new BorderLayout(0, 8));
        pnlThan.setOpaque(false);
        pnlThan.add(pnlTren, BorderLayout.NORTH);
        pnlThan.add(bang(cot, dong), BorderLayout.CENTER);
        pnlThan.add(nutIn(true), BorderLayout.SOUTH);
        pnl.add(pnlThan, BorderLayout.CENTER);
        return pnl;
    }

//    private JPanel pnlThuChi(String tieuDe) {
//        JPanel pnl = new JPanel(new BorderLayout(0, 8));
//        pnl.setOpaque(false);
//        pnl.add(dau(tieuDe, false), BorderLayout.NORTH);
//        BaoCao_Service baoCaoService = new BaoCao_Service();
//        long tongThu = 0;
//        long tongChi = 0;
//        List<Object[]> rows = new ArrayList<>();
//        int stt = 1;
//        for (PhieuThu phieuThu : baoCaoService.danhSachPhieuThu()) {
//            tongThu += phieuThu.getSoTien();
//            rows.add(new Object[]{
//                String.valueOf(stt++), phieuThu.getMaPhieuThu(), NgayUtil.fmt(phieuThu.getNgayThu()),
//                entity.Refs.maKh(phieuThu.getKhachHang()), "", phieuThu.getLyDo(),
//                ViewTheme.tien(phieuThu.getSoTien()), "0", "0", "0", ViewTheme.tien(phieuThu.getSoTien()),
//                "0", "0", "0", "0", "0"
//            });
//        }
//        for (PhieuChi phieuChi : baoCaoService.danhSachPhieuChi()) {
//            tongChi += phieuChi.getSoTien();
//            rows.add(new Object[]{
//                String.valueOf(stt++), phieuChi.getMaPhieuChi(), NgayUtil.fmt(phieuChi.getNgayChi()),
//                "", entity.Refs.maNcc(phieuChi.getNhaCungCap()), phieuChi.getLyDoChi(),
//                "0", "0", "0", "0", "0",
//                ViewTheme.tien(phieuChi.getSoTien()), "0", "0", "0", ViewTheme.tien(phieuChi.getSoTien())
//            });
//        }
//        rows.add(new Object[]{"", "", "", "", "", "Tổng tiền",
//            ViewTheme.tien(tongThu), "0", "0", "0", ViewTheme.tien(tongThu),
//            ViewTheme.tien(tongChi), "0", "0", "0", ViewTheme.tien(tongChi)});
//        JPanel pnlSo = new JPanel(new GridLayout(1, 3, 8, 0));
//        pnlSo.setOpaque(false);
//        pnlSo.add(oSo("Tổng thu", ViewTheme.tien(tongThu)));
//        pnlSo.add(oSo("Tổng chi", ViewTheme.tien(tongChi)));
//        pnlSo.add(oSo("Tổng (Thu - chi)", ViewTheme.tien(tongThu - tongChi)));
//        JPanel pnlTren = new JPanel(new BorderLayout(12, 0));
//        pnlTren.setOpaque(false);
//        pnlTren.add(oNgay(), BorderLayout.WEST);
//        pnlTren.add(pnlSo, BorderLayout.CENTER);
//        String[] cot = {"#", "Mã phiếu", "Ngày", "Khách hàng", "Nhà cung cấp", "Lý do",
//            "Thu - Tiền mặt", "Thu - Thẻ", "Thu - Chuyển khoản", "Thu - Trả góp", "Thu - Tổng",
//            "Chi - Tiền mặt", "Chi - Thẻ", "Chi - Chuyển khoản", "Chi - Trả góp", "Chi - Tổng"};
//        Object[][] dong = rows.toArray(new Object[0][]);
//        JPanel pnlThan = new JPanel(new BorderLayout(0, 8));
//        pnlThan.setOpaque(false);
//        pnlThan.add(pnlTren, BorderLayout.NORTH);
//        pnlThan.add(bang(cot, dong), BorderLayout.CENTER);
//        pnlThan.add(thanhXuatThuChi(tieuDe), BorderLayout.SOUTH);
//        pnl.add(pnlThan, BorderLayout.CENTER);
//        return pnl;
//    }

    private JPanel pnlThuChi(String tieuDe) {
        JPanel pnl = new JPanel(new BorderLayout(0, 8));
        pnl.setOpaque(false);
        pnl.add(dau(tieuDe, false), BorderLayout.NORTH);
        BaoCao_Service baoCaoService = new BaoCao_Service();
        long tongThu = 0;
        long tongChi = 0;
        List<Object[]> rows = new ArrayList<>();
        int stt = 1;
        for (PhieuThu phieuThu : baoCaoService.danhSachPhieuThu()) {
            tongThu += phieuThu.getSoTien();
            rows.add(new Object[]{
                String.valueOf(stt++), phieuThu.getMaPhieuThu(), NgayUtil.fmt(phieuThu.getNgayThu()),
                entity.Refs.maKh(phieuThu.getKhachHang()), "", phieuThu.getLyDo(),
                ViewTheme.tien(phieuThu.getSoTien()), "0", "0", "0", ViewTheme.tien(phieuThu.getSoTien()),
                "0", "0", "0", "0", "0"
            });
        }
        for (PhieuChi phieuChi : baoCaoService.danhSachPhieuChi()) {
            tongChi += phieuChi.getSoTien();
            rows.add(new Object[]{
                String.valueOf(stt++), phieuChi.getMaPhieuChi(), NgayUtil.fmt(phieuChi.getNgayChi()),
                "", entity.Refs.maNcc(phieuChi.getNhaCungCap()), phieuChi.getLyDoChi(),
                "0", "0", "0", "0", "0",
                ViewTheme.tien(phieuChi.getSoTien()), "0", "0", "0", ViewTheme.tien(phieuChi.getSoTien())
            });
        }
        rows.add(new Object[]{"", "", "", "", "", "Tổng tiền",
            ViewTheme.tien(tongThu), "0", "0", "0", ViewTheme.tien(tongThu),
            ViewTheme.tien(tongChi), "0", "0", "0", ViewTheme.tien(tongChi)});
        JPanel pnlSo = new JPanel(new GridLayout(1, 3, 8, 0));
        pnlSo.setOpaque(false);
        pnlSo.add(oSo("Tổng thu", ViewTheme.tien(tongThu)));
        pnlSo.add(oSo("Tổng chi", ViewTheme.tien(tongChi)));
        pnlSo.add(oSo("Tổng (Thu - chi)", ViewTheme.tien(tongThu - tongChi)));
        JPanel pnlTren = new JPanel(new BorderLayout(12, 0));
        pnlTren.setOpaque(false);
        pnlTren.add(oNgay(), BorderLayout.WEST);
        pnlTren.add(pnlSo, BorderLayout.CENTER);
        String[] cot = {"#", "Mã phiếu", "Ngày", "Khách hàng", "Nhà cung cấp", "Lý do",
            "Thu - Tiền mặt", "Thu - Thẻ", "Thu - Chuyển khoản", "Thu - Trả góp", "Thu - Tổng",
            "Chi - Tiền mặt", "Chi - Thẻ", "Chi - Chuyển khoản", "Chi - Trả góp", "Chi - Tổng"};
        Object[][] dong = rows.toArray(new Object[0][]);
        JScrollPane scrBang = bang(cot, dong);
        if (scrBang.getViewport().getView() instanceof JTable) {
            tblThuChiHienTai = (JTable) scrBang.getViewport().getView();
        }
        JPanel pnlThan = new JPanel(new BorderLayout(0, 8));
        pnlThan.setOpaque(false);
        pnlThan.add(pnlTren, BorderLayout.NORTH);
        pnlThan.add(scrBang, BorderLayout.CENTER);
        pnlThan.add(thanhXuatPhieuDonLe(tieuDe), BorderLayout.SOUTH);
        pnl.add(pnlThan, BorderLayout.CENTER);
        return pnl;
    }
    
    private JPanel pnlDoanhThuDichVu() {
        return baoCaoTab("Doanh thu dịch vụ",
                new String[]{"Tổng quan", "Loại dịch vụ", "Dịch vụ"},
                new String[]{"#", "Mã phiếu", "Ngày", "Đối tượng", "Điện thoại", "Lý do", "Loại dịch vụ", "Tổng tiền"},
                new Object[][]{{"", "", "", "", "", "", "Tổng tiền", "0"}});
    }

    private JPanel pnlDoanhThuCaNhan() {
        return baoCaoTab("Doanh thu cá nhân",
                new String[]{"Doanh thu", "Tổng hợp", "Người làm chính", "Phụ tá", "Tư vấn", "Sale", "Telesale", "Người giới thiệu"},
                new String[]{"#", "Mã chi nhánh", "Mã phiếu", "Ngày", "Đối tượng", "Điện thoại", "Lý do", "Người làm chính", "Phụ tá", "Tư vấn", "Sale", "Người giới thiệu", "Tổng cộng"},
                new Object[][]{{"", "", "", "", "", "", "", "", "", "", "", "Tổng tiền", "0"}});
    }

    private JPanel baoCaoTab(String tieuDe, String[] tab, String[] cot, Object[][] dong) {
        JPanel pnl = new JPanel(new BorderLayout(0, 8));
        pnl.setOpaque(false);
        pnl.add(dau(tieuDe, false), BorderLayout.NORTH);
        JPanel pnlThanh = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        pnlThanh.setOpaque(false);
        JPanel pnlVung = new JPanel(new BorderLayout());
        pnlVung.setOpaque(false);
        pnlVung.add(bang(cot, dong), BorderLayout.CENTER);
        for (int i = 0; i < tab.length; i++) {
            JButton btnTab = new JButton(tab[i]);
            if (i == 0) {
                ViewTheme.nutChinh(btnTab);
            } else {
                ViewTheme.nutXam(btnTab);
            }
            btnTab.addActionListener(e -> {
                for (Component component : pnlThanh.getComponents()) {
                    if (component instanceof JButton) {
                        ViewTheme.nutXam((JButton) component);
                    }
                }
                ViewTheme.nutChinh(btnTab);
            });
            pnlThanh.add(btnTab);
        }
        JPanel pnlTren = new JPanel(new BorderLayout(0, 8));
        pnlTren.setOpaque(false);
        pnlTren.add(oNgay(), BorderLayout.NORTH);
        pnlTren.add(pnlThanh, BorderLayout.SOUTH);
        JPanel pnlThan = new JPanel(new BorderLayout(0, 8));
        pnlThan.setOpaque(false);
        pnlThan.add(pnlTren, BorderLayout.NORTH);
        pnlThan.add(pnlVung, BorderLayout.CENTER);
        pnlThan.add(nutIn(true), BorderLayout.SOUTH);
        pnl.add(pnlThan, BorderLayout.CENTER);
        return pnl;
    }

    private JPanel pnlMauChung(String tieuDe, String[] cot, Object[][] dong) {
        JPanel pnl = new JPanel(new BorderLayout(0, 8));
        pnl.setOpaque(false);
        pnl.add(dau(tieuDe, false), BorderLayout.NORTH);
        JPanel pnlThan = new JPanel(new BorderLayout(0, 8));
        pnlThan.setOpaque(false);
        pnlThan.add(oNgay(), BorderLayout.NORTH);
        pnlThan.add(bang(cot, dong), BorderLayout.CENTER);
        pnlThan.add(nutIn(true), BorderLayout.SOUTH);
        pnl.add(pnlThan, BorderLayout.CENTER);
        return pnl;
    }

    private JPanel dau(String tieuDe, boolean taiLai) {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setOpaque(false);
        JLabel lblNhanTieuDe = ViewTheme.tieuDeTrang(tieuDe);
        pnl.add(lblNhanTieuDe, BorderLayout.WEST);
        if (taiLai) {
            JButton btnTai = new JButton("Tải lại");
            ViewTheme.nutXanh(btnTai);
            btnTai.addActionListener(e -> chon(ma));
            JPanel pnlPhai = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
            pnlPhai.setOpaque(false);
            pnlPhai.add(btnTai);
            pnl.add(pnlPhai, BorderLayout.CENTER);
        }
        return pnl;
    }

    private JTextField oNgay() {
        JTextField txtNgay = new JTextField("28/09/2026 - 28/09/2026");
        ViewTheme.vienNhap(txtNgay);
        txtNgay.setPreferredSize(new Dimension(220, 32));
        return txtNgay;
    }

    private JPanel oSo(String nhan, String so) {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setOpaque(false);
        JLabel lblNhanTieu = ViewTheme.chuPhu(nhan);
        JLabel lblNhanSo = new JLabel(so);
        lblNhanSo.setFont(ViewTheme.FONT_SO);
        lblNhanSo.setForeground(ViewTheme.CHU);
        pnl.add(lblNhanTieu, BorderLayout.NORTH);
        pnl.add(lblNhanSo, BorderLayout.CENTER);
        return pnl;
    }

    private JScrollPane bang(String[] cot, Object[][] dong) {
        DefaultTableModel model = ViewTheme.taoModel(cot, false, false);
        for (Object[] hang : dong) {
            model.addRow(hang);
        }
        JTable tbl = new JTable(model);
        ViewTheme.trangTriBang(tbl);
        JScrollPane scrBang = new JScrollPane(tbl);
        scrBang.setPreferredSize(new Dimension(400, 180));
        scrBang.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        scrBang.getViewport().setBackground(ViewTheme.TRANG);
        return scrBang;
    }

    private JPanel nutIn(boolean excel) {
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        pnl.setOpaque(false);
        JButton btnIn = new JButton("In");
        ViewTheme.nutXanh(btnIn);
        btnIn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Đã chuẩn bị bản in " + tieuCua(ma) + "."));
        pnl.add(btnIn);
        if (excel) {
            JButton btnXuat = new JButton("Xuất excel...");
            ViewTheme.nutXanh(btnXuat);
            btnXuat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    HoaDon hd = new HoaDon();
                    
                    KhachHang kh = new KhachHang();
                    kh.setMaKH("KH-1");
                    
                    hd.setMaHoaDon("HD-1");
                    hd.setNgayLap(LocalDate.now());
                    hd.setKhachHang(kh);
                    
                    String filePath = "hoadon_" + hd.getMaHoaDon() + ".pdf";

                    
                    boolean success = generatehdPdf(hd, filePath);

                    if (success) {
                        JOptionPane.showMessageDialog(pnl, "Tạo hóa đơn thành công tại: " + filePath);
                    } else {
                        JOptionPane.showMessageDialog(pnl, "Có lỗi xảy ra khi tạo hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            pnl.add(btnXuat);
            
         
        }
        return pnl;
    }

    private String tieuCua(String maBaoCao) {
        if ("congno".equals(maBaoCao)) {
            return "Thống kê doanh thu";
        }
        if ("donthuoc".equals(maBaoCao)) {
            return "Báo cáo toa thuốc";
        }
        if ("thuchi".equals(maBaoCao)) {
            return "Báo cáo thu chi";
        }
        if ("thuchihangngay".equals(maBaoCao)) {
            return "Báo cáo thu chi hàng ngày";
        }
        return "Báo cáo";
    }

    private String[] cotCua(String maBaoCao) {
        if ("lichhen".equals(maBaoCao)) {
            return new String[]{"#", "Khách hàng", "Công việc", "Bác sĩ", "Bắt đầu", "Trạng thái"};
        }
        if ("dieutri".equals(maBaoCao)) {
            return new String[]{"#", "Khách hàng", "Dịch vụ", "Ngày", "Bác sĩ", "Thành tiền"};
        }
        if ("congno".equals(maBaoCao)) {
            return new String[]{"#", "Khách hàng", "Phát sinh", "Đã thu", "Còn nợ"};
        }
        if ("chiphi".equals(maBaoCao)) {
            return new String[]{"#", "Ngày", "Loại chi", "Lý do", "Số tiền"};
        }
        if ("nhatky".equals(maBaoCao)) {
            return new String[]{"#", "Thời gian", "Người dùng", "Hành động", "Đối tượng"};
        }
        if ("sale".equals(maBaoCao)) {
            return new String[]{"#", "Nhân viên", "Số ca", "Doanh số"};
        }
        if ("nguon".equals(maBaoCao)) {
            return new String[]{"#", "Nguồn", "Số khách", "Doanh thu"};
        }
        if ("baocaolabo".equals(maBaoCao)) {
            return new String[]{"#", "Mã", "Nhà cung cấp", "Khách hàng", "Chi tiết", "Tình trạng", "Ngày gửi"};
        }
        return new String[]{"#", "Nội dung", "Số lượng", "Giá trị"};
    }

    private Object[][] dongCua(String maBaoCao) {
        BaoCao_Service baoCaoService = new BaoCao_Service();
        if ("lichhen".equals(maBaoCao)) {
            List<Object[]> rows = new ArrayList<>();
            int i = 1;
            for (LichHen lichHen : baoCaoService.danhSachLichHen()) {
                rows.add(new Object[]{
                    String.valueOf(i++), entity.Refs.maKh(lichHen.getKhachHang()), "", entity.Refs.maNv(lichHen.getBacSi()),
                    NgayUtil.fmtFull(lichHen.getBatDau()), lichHen.getTrangThai()
                });
            }
            return rows.isEmpty() ? new Object[][]{{"", "", "", "", "", ""}} : rows.toArray(new Object[0][]);
        }
        if ("dieutri".equals(maBaoCao)) {
            List<Object[]> rows = new ArrayList<>();
            int i = 1;
            for (KeHoachDieuTri keHoach : baoCaoService.danhSachKeHoachKemChiTiet()) {
                List<ChiTietKeHoachDieuTri> chiTietKeHoach = keHoach.getChiTiet() == null ? List.of() : keHoach.getChiTiet();
                for (ChiTietKeHoachDieuTri chiTiet : chiTietKeHoach) {
                    rows.add(new Object[]{
                        String.valueOf(i++), entity.Refs.maKh(keHoach.getKhachHang()), entity.Refs.maDv(chiTiet.getDichVu()),
                        NgayUtil.fmt(chiTiet.getNgay()), entity.Refs.maNv(keHoach.getBacSi()), ViewTheme.tien(chiTiet.getThanhTien())
                    });
                }
            }
            return rows.isEmpty() ? new Object[][]{{"", "", "", "", "", "0"}} : rows.toArray(new Object[0][]);
        }
        if ("congno".equals(maBaoCao)) {
            return HoaDonDuLieu.dongCongNo();
        }
        if ("chiphi".equals(maBaoCao)) {
            long tong = 0;
            for (PhieuChi phieuChi : baoCaoService.danhSachPhieuChi()) {
                tong += phieuChi.getSoTien();
            }
            return new Object[][]{{"", "", "", "Tổng tiền", ViewTheme.tien(tong)}};
        }
        if ("nhatky".equals(maBaoCao)) {
            List<PhieuThu> danhSachPhieuThu = baoCaoService.danhSachPhieuThu();
            if (danhSachPhieuThu.isEmpty()) {
                return new Object[][]{{"", "", "", "", ""}};
            }
            PhieuThu phieuThu = danhSachPhieuThu.get(0);
            return new Object[][]{{"1", NgayUtil.fmtFull(phieuThu.getNgayTao()), phieuThu.getNguoiTao(), "Tạo phiếu thu", phieuThu.getMaPhieuThu()}};
        }
        if ("sale".equals(maBaoCao) || "nguon".equals(maBaoCao) || "baocaolabo".equals(maBaoCao)) {
            return new Object[][]{{"", "", "", "0"}};
        }
        if ("tongquan".equals(maBaoCao)) {
            long doanhThu = 0;
            for (dto.HoaDonKemThanhToan item : baoCaoService.danhSachHoaDonKemThanhToan()) {
                doanhThu += item.getThanhToan().getThanhTien();
            }
            return new Object[][]{
                {"1", "Lịch hẹn", String.valueOf(baoCaoService.danhSachLichHen().size()), ""},
                {"2", "Khách hàng", String.valueOf(new KhachHang_Service().findAllKhachHang().size()), ""},
                {"3", "Doanh thu", "", ViewTheme.tien(doanhThu)}
            };
        }
        return new Object[][]{{"", "", "Tổng", "0"}};
    }
    
    private JPanel thanhXuatThuChi(String tieuDeBaoCao) {
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        pnl.setOpaque(false);

        JButton btnIn = new JButton("In");
        ViewTheme.nutXanh(btnIn);
        btnIn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Đã chuẩn bị bản in " + tieuDeBaoCao + "."));

        JButton btnXuatPdf = new JButton("Xuất hóa đơn PDF...");
        ViewTheme.nutXanh(btnXuatPdf);
        btnXuatPdf.addActionListener(e -> xuatHoaDonThuChiPdf(tieuDeBaoCao));

        pnl.add(btnIn);
        pnl.add(btnXuatPdf);
        return pnl;
    }
    
    private void xuatHoaDonThuChiPdf(String tieuDeBaoCao) {
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        String fileMacDinh = ("thuchihangngay".equals(ma) ? "phieu_thuchi_ngay_" : "phieu_thuchi_")
                + LocalDate.now() + ".pdf";
        chooser.setSelectedFile(new java.io.File(fileMacDinh));

        int chon = chooser.showSaveDialog(this);
        if (chon != javax.swing.JFileChooser.APPROVE_OPTION) {
            return;
        }

        String dest = chooser.getSelectedFile().getAbsolutePath();
        if (!dest.toLowerCase().endsWith(".pdf")) {
            dest += ".pdf";
        }

        boolean thanhCong = generateThuChiPdf(tieuDeBaoCao, dest);
        if (thanhCong) {
            JOptionPane.showMessageDialog(this, "Xuất hóa đơn / phiếu thu chi thành công tại:\n" + dest);
        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xuất file PDF!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static boolean generateThuChiPdf(String tieuDeBaoCao, String dest) {
        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            try {
                String fontPath = "C:/Windows/Fonts/arial.ttf";
                com.itextpdf.kernel.font.PdfFont font = com.itextpdf.kernel.font.PdfFontFactory.createFont(
                        fontPath, com.itextpdf.io.font.PdfEncodings.IDENTITY_H);
                document.setFont(font);
            } catch (Exception ignored) {
                // Fallback về font mặc định nếu không tìm thấy file font
            }

            String tenTieuDe = (tieuDeBaoCao != null && !tieuDeBaoCao.isBlank())
                    ? tieuDeBaoCao.toUpperCase()
                    : "BAO CAO THU CHI";

            document.add(new Paragraph(tenTieuDe)
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("PHONG KHAM NHA KHOA\n"
                    + "Ngay lap: " + NgayUtil.fmt(LocalDate.now()) + "\n"
                    + "--------------------------------------------------------------------------------")
                    .setFontSize(10));

            Table table = new Table(new float[]{1, 1.5f, 2, 2, 2, 3.5f, 2.5f});
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell(new Cell().add(new Paragraph("STT")));
            table.addHeaderCell(new Cell().add(new Paragraph("Loai")));
            table.addHeaderCell(new Cell().add(new Paragraph("Ma phieu")));
            table.addHeaderCell(new Cell().add(new Paragraph("Ngay")));
            table.addHeaderCell(new Cell().add(new Paragraph("Doi tuong")));
            table.addHeaderCell(new Cell().add(new Paragraph("Ly do")));
            table.addHeaderCell(new Cell().add(new Paragraph("So tien")));

            BaoCao_Service baoCaoService = new BaoCao_Service();
            long tongThu = 0;
            long tongChi = 0;
            int stt = 1;

            for (PhieuThu pt : baoCaoService.danhSachPhieuThu()) {
                long soTien = pt.getSoTien();
                tongThu += soTien;
                table.addCell(String.valueOf(stt++));
                table.addCell("THU");
                table.addCell(pt.getMaPhieuThu() != null ? pt.getMaPhieuThu() : "");
                table.addCell(NgayUtil.fmt(pt.getNgayThu()));
                table.addCell(entity.Refs.maKh(pt.getKhachHang()));
                table.addCell(pt.getLyDo() != null ? pt.getLyDo() : "");
                table.addCell(ViewTheme.tien(soTien));
            }

            for (PhieuChi pc : baoCaoService.danhSachPhieuChi()) {
                long soTien = pc.getSoTien();
                tongChi += soTien;
                table.addCell(String.valueOf(stt++));
                table.addCell("CHI");
                table.addCell(pc.getMaPhieuChi() != null ? pc.getMaPhieuChi() : "");
                table.addCell(NgayUtil.fmt(pc.getNgayChi()));
                table.addCell(entity.Refs.maNcc(pc.getNhaCungCap()));
                table.addCell(pc.getLyDoChi() != null ? pc.getLyDoChi() : "");
                table.addCell("-" + ViewTheme.tien(soTien));
            }

            if (stt == 1) {
                table.addCell("-");
                table.addCell("-");
                table.addCell("-");
                table.addCell(NgayUtil.fmt(LocalDate.now()));
                table.addCell("-");
                table.addCell("Khong co du lieu thu chi");
                table.addCell(ViewTheme.tien(0));
            }

            document.add(table);

            long chenhLech = tongThu - tongChi;
            document.add(new Paragraph("\nTong thu: " + ViewTheme.tien(tongThu)
                    + "\nTong chi: " + ViewTheme.tien(tongChi)
                    + "\nTong (Thu - chi): " + ViewTheme.tien(chenhLech))
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.RIGHT));

            document.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean generatehdPdf(HoaDon hd, String dest) {
        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            String maHoaDon = (hd != null && hd.getMaHoaDon() != null) ? hd.getMaHoaDon() : "HD-MOI";
            String ngayLapStr = (hd != null && hd.getNgayLap() != null)
                    ? NgayUtil.fmt(hd.getNgayLap())
                    : NgayUtil.fmt(LocalDate.now());
            String maKhachHang = (hd != null) ? entity.Refs.maKh(hd.getKhachHang()) : "";

            
            try {
                String fontPath = "C:/Windows/Fonts/arial.ttf";
                com.itextpdf.kernel.font.PdfFont font = com.itextpdf.kernel.font.PdfFontFactory.createFont(
                        fontPath, com.itextpdf.io.font.PdfEncodings.IDENTITY_H);
                document.setFont(font);
            } catch (Exception ignored) {
                
            }

            document.add(new Paragraph("HOA DON DIEU TRI & DICH VU NHA KHOA")
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("PHONG KHAM LUON GIU BINH TINH\n"
                    + "Dia chi: TP. Ho Chi Minh\n"
                    + "--------------------------------------------------------------------------------")
                    .setFontSize(10));

            document.add(new Paragraph("\nMa hoa don: " + maHoaDon
                    + "\nNgay lap: " + ngayLapStr
                    + "\nMa khach hang: " + maKhachHang + "\n")
                    .setFontSize(11));

            Table table = new Table(new float[]{1, 4, 2, 2, 2});
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell(new Cell().add(new Paragraph("STT")));
            table.addHeaderCell(new Cell().add(new Paragraph("Dich vu / Noi dung")));
            table.addHeaderCell(new Cell().add(new Paragraph("Ngay")));
            table.addHeaderCell(new Cell().add(new Paragraph("Bac si")));
            table.addHeaderCell(new Cell().add(new Paragraph("Thanh tien")));

            BaoCao_Service baoCaoService = new BaoCao_Service();
            long tongTien = 0;
            int stt = 1;

            for (KeHoachDieuTri keHoach : baoCaoService.danhSachKeHoachKemChiTiet()) {
                List<ChiTietKeHoachDieuTri> chiTietList =
                        keHoach.getChiTiet() == null ? List.of() : keHoach.getChiTiet();
                for (ChiTietKeHoachDieuTri ct : chiTietList) {
                    long thanhTien = ct.getThanhTien();
                    tongTien += thanhTien;
                    table.addCell(String.valueOf(stt++));
                    table.addCell(entity.Refs.maDv(ct.getDichVu()));
                    table.addCell(NgayUtil.fmt(ct.getNgay()));
                    table.addCell(entity.Refs.maNv(keHoach.getBacSi()));
                    table.addCell(ViewTheme.tien(thanhTien));
                }
            }

            if (stt == 1) {
                table.addCell("-");
                table.addCell("Chua co chi tiet dieu tri");
                table.addCell(ngayLapStr);
                table.addCell("-");
                table.addCell(ViewTheme.tien(0));
            }

            document.add(table);

            document.add(new Paragraph("\nTong thanh toan: " + ViewTheme.tien(tongTien) + " VND")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.RIGHT));

            document.add(new Paragraph("\nCam on quy khach da tin tuong phong kham!")
                    .setTextAlignment(TextAlignment.CENTER));

            document.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    private JPanel thanhXuatPhieuDonLe(String tieuDeBaoCao) {
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        pnl.setOpaque(false);

        JButton btnIn = new JButton("In");
        ViewTheme.nutXanh(btnIn);
        btnIn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Đã chuẩn bị bản in " + tieuDeBaoCao + "."));

        JButton btnXuatPhieu = new JButton("Xuất phiếu đang chọn...");
        ViewTheme.nutXanh(btnXuatPhieu);
        btnXuatPhieu.addActionListener(e -> xuatPhieuDangChonPdf());

        pnl.add(btnIn);
        pnl.add(btnXuatPhieu);
        return pnl;
    }

    private void xuatPhieuDangChonPdf() {
        if (tblThuChiHienTai == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy bảng dữ liệu thu chi.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = tblThuChiHienTai.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng phiếu thu hoặc phiếu chi trên bảng trước.", "Chưa chọn phiếu", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String maPhieu = layGiaTriChuoi(tblThuChiHienTai.getValueAt(row, 1));
        String lyDo = layGiaTriChuoi(tblThuChiHienTai.getValueAt(row, 5));
        if (maPhieu.isEmpty() || "Tổng tiền".equalsIgnoreCase(lyDo)) {
            JOptionPane.showMessageDialog(this, "Dòng đang chọn không phải phiếu hợp lệ (đang chọn dòng tổng hoặc dòng trống).", "Không hợp lệ", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String ngay = layGiaTriChuoi(tblThuChiHienTai.getValueAt(row, 2));
        String khachHang = layGiaTriChuoi(tblThuChiHienTai.getValueAt(row, 3));
        String nhaCungCap = layGiaTriChuoi(tblThuChiHienTai.getValueAt(row, 4));
        String thuTong = layGiaTriChuoi(tblThuChiHienTai.getValueAt(row, 10));
        String chiTong = layGiaTriChuoi(tblThuChiHienTai.getValueAt(row, 15));

        boolean laPhieuChi = !nhaCungCap.isEmpty() || laSoKhacKhong(chiTong);
        String loaiPhieu = laPhieuChi ? "PHIẾU CHI" : "PHIẾU THU";
        String doiTuong = laPhieuChi ? nhaCungCap : khachHang;
        String soTienHienThi = laPhieuChi ? chiTong : thuTong;

        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setSelectedFile(new java.io.File("phieu_" + maPhieu + ".pdf"));

        int chon = chooser.showSaveDialog(this);
        if (chon != javax.swing.JFileChooser.APPROVE_OPTION) {
            return;
        }

        String dest = chooser.getSelectedFile().getAbsolutePath();
        if (!dest.toLowerCase().endsWith(".pdf")) {
            dest += ".pdf";
        }

        boolean thanhCong = generatePhieuDonLePdf(loaiPhieu, maPhieu, ngay, doiTuong, lyDo, soTienHienThi, dest);
        if (thanhCong) {
            JOptionPane.showMessageDialog(this, "Đã xuất " + loaiPhieu.toLowerCase() + " thành công tại:\n" + dest);
        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xuất file PDF!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String layGiaTriChuoi(Object val) {
        return val == null ? "" : val.toString().trim();
    }

    private boolean laSoKhacKhong(String soTienStr) {
        if (soTienStr == null || soTienStr.isBlank()) {
            return false;
        }
        String digitsOnly = soTienStr.replaceAll("[^0-9-]", "");
        if (digitsOnly.isEmpty() || "-".equals(digitsOnly)) {
            return false;
        }
        try {
            return Long.parseLong(digitsOnly) != 0L;
        } catch (NumberFormatException e) {
            return !digitsOnly.equals("0");
        }
    }

    public static boolean generatePhieuDonLePdf(
            String loaiPhieu,
            String maPhieu,
            String ngay,
            String doiTuong,
            String lyDo,
            String soTien,
            String dest) {
        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            try {
                String fontPath = "C:/Windows/Fonts/arial.ttf";
                com.itextpdf.kernel.font.PdfFont font = com.itextpdf.kernel.font.PdfFontFactory.createFont(
                        fontPath, com.itextpdf.io.font.PdfEncodings.IDENTITY_H);
                document.setFont(font);
            } catch (Exception ignored) {
            }

            document.add(new Paragraph(loaiPhieu)
                    .setFontSize(20)
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("PHONG KHAM NHA KHOA\n"
                    + "Ma phieu: " + maPhieu + "    |    Ngay: " + ngay + "\n"
                    + "--------------------------------------------------------------------------------")
                    .setFontSize(10));

            String nhanDoiTuong = "PHIẾU CHI".equals(loaiPhieu) ? "Nha cung cap" : "Khach hang";

            Table table = new Table(new float[]{4, 8});
            table.setWidth(UnitValue.createPercentValue(100));

            table.addCell(new Cell().add(new Paragraph("Ma phieu")));
            table.addCell(new Cell().add(new Paragraph(maPhieu)));

            table.addCell(new Cell().add(new Paragraph("Ngay lap")));
            table.addCell(new Cell().add(new Paragraph(ngay)));

            table.addCell(new Cell().add(new Paragraph(nhanDoiTuong)));
            table.addCell(new Cell().add(new Paragraph(doiTuong.isEmpty() ? "-" : doiTuong)));

            table.addCell(new Cell().add(new Paragraph("Ly do")));
            table.addCell(new Cell().add(new Paragraph(lyDo.isEmpty() ? "-" : lyDo)));

            table.addCell(new Cell().add(new Paragraph("So tien")));
            table.addCell(new Cell().add(new Paragraph(soTien.isEmpty() ? "0" : soTien)));

            document.add(table);

            document.add(new Paragraph("\nNguoi lap phieu                                                Thu quy / Ke toan")
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER));

            document.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
