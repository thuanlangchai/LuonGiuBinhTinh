package view;

import entity.NhaCungCap;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import service.impl.Kho_Service;

public class NhapKhoPanel extends JPanel {

    private final JPanel pnlDanhSachDong = new JPanel();
    private final JLabel lblTongTien = new JLabel("0");
    private final String tieuDe;
    private final String nhanNutLuu;

    public NhapKhoPanel(String tieuDe, String nhanNutLuu) {
        this.tieuDe = tieuDe;
        this.nhanNutLuu = nhanNutLuu;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));

        JPanel pnlNoiDung = new JPanel(new BorderLayout(0, 12));
        pnlNoiDung.setBackground(ViewTheme.TRANG);
        pnlNoiDung.setBorder(new EmptyBorder(16, 18, 16, 18));

        JLabel lblTieuDe = ViewTheme.tieuDeTrang(tieuDe);
        JTextField txtNgayNhap = txtTaoTextField("2026-09-28");
        JComboBox<String> cmbLoaiVatTu = new JComboBox<>(new String[]{
            "Thuốc", "Vật liệu tiêu hao", "Thiết bị nha khoa", "Công cụ, dụng cụ"
        });
        JComboBox<String> cmbNhaCungCap = new JComboBox<>();
        for (NhaCungCap nhaCungCap : new Kho_Service().findAllNhaCungCap()) {
        	cmbNhaCungCap.addItem(nhaCungCap.getTenNhaCungCap());
        }
        if (cmbNhaCungCap.getItemCount() == 0) {
        	cmbNhaCungCap.addItem("STRAUMANN");
        }
        JTextField txtLyDo = txtTaoTextField("");
        ViewTheme.vienHop(cmbLoaiVatTu);
        ViewTheme.vienHop(cmbNhaCungCap);

        JPanel pnlForm = new JPanel(new GridLayout(0, 2, 12, 10));
        pnlForm.setOpaque(false);
        pnlForm.add(lblTaoLabel("Ngày nhập kho"));
        pnlForm.add(txtNgayNhap);
        pnlForm.add(lblTaoLabel("Loại vật tư"));
        pnlForm.add(cmbLoaiVatTu);
        pnlForm.add(lblTaoLabel("Nhà cung cấp"));
        pnlForm.add(cmbNhaCungCap);
        pnlForm.add(lblTaoLabel("<html>Lý do <font color='#e74c3c'>*</font></html>"));
        pnlForm.add(txtLyDo);

        pnlDanhSachDong.setLayout(new BoxLayout(pnlDanhSachDong, BoxLayout.Y_AXIS));
        pnlDanhSachDong.setOpaque(false);
        pnlDanhSachDong.add(pnlTaoDongHang());

        JButton btnThemDong = new JButton("+");
        ViewTheme.nutChinh(btnThemDong);
        btnThemDong.addActionListener(e -> {
            pnlDanhSachDong.add(pnlTaoDongHang());
            pnlDanhSachDong.revalidate();
        });
        JPanel pnlThemDong = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlThemDong.setOpaque(false);
        pnlThemDong.add(btnThemDong);

        JLabel lblTenFile = new JLabel("No file chosen");
        JButton btnChonFile = new JButton("Choose File");
        ViewTheme.nutXam(btnChonFile);
        btnChonFile.addActionListener(e -> {
            JFileChooser fch = new JFileChooser();
            if (fch.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                lblTenFile.setText(fch.getSelectedFile().getName());
            }
        });
        JButton btnTaiMau = new JButton("Tải file excel mẫu");
        ViewTheme.nutLink(btnTaiMau);
        btnTaiMau.setForeground(ViewTheme.XANH_DAM);
        btnTaiMau.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "File mẫu gồm cột: Sản phẩm, Số lượng, Đơn giá."));

        JPanel pnlExcel = new JPanel(new GridLayout(0, 2, 8, 8));
        pnlExcel.setOpaque(false);
        pnlExcel.setBorder(BorderFactory.createTitledBorder("Nhập liệu từ excel"));
        pnlExcel.add(lblTaoLabel("Nhập file excel"));
        JPanel pnlHangFile = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pnlHangFile.setOpaque(false);
        pnlHangFile.add(btnChonFile);
        pnlHangFile.add(lblTenFile);
        pnlExcel.add(pnlHangFile);
        pnlExcel.add(lblTaoLabel("File mẫu"));
        pnlExcel.add(btnTaiMau);

        JButton btnBoQua = new JButton("Bỏ qua");
        JButton btnLuu = new JButton(nhanNutLuu);
        ViewTheme.nutXam(btnBoQua);
        ViewTheme.nutChinh(btnLuu);
        btnBoQua.addActionListener(e -> txtLyDo.setText(""));
        btnLuu.addActionListener(e -> {
            if (txtLyDo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập lý do.");
                return;
            }
            JOptionPane.showMessageDialog(this,
                    "Đã ghi nhận " + tieuDe.toLowerCase()
                    + ". Tổng thành tiền " + lblTongTien.getText());
            txtLyDo.setText("");
        });
        JPanel pnlChan = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pnlChan.setOpaque(false);
        pnlChan.add(btnBoQua);
        pnlChan.add(btnLuu);

        JPanel pnlTieuCot = new JPanel(new GridLayout(1, 4, 8, 0));
        pnlTieuCot.setOpaque(false);
        pnlTieuCot.add(ViewTheme.chuPhu("Sản phẩm"));
        pnlTieuCot.add(ViewTheme.chuPhu("Số lượng"));
        pnlTieuCot.add(ViewTheme.chuPhu("Đơn giá"));
        pnlTieuCot.add(ViewTheme.chuPhu("Thành tiền"));

        JPanel pnlTongHang = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        pnlTongHang.setOpaque(false);
        pnlTongHang.add(new JLabel("Tổng thành tiền"));
        lblTongTien.setFont(ViewTheme.FONT_SO);
        pnlTongHang.add(lblTongTien);

        JPanel pnlThan = new JPanel();
        pnlThan.setLayout(new BoxLayout(pnlThan, BoxLayout.Y_AXIS));
        pnlThan.setOpaque(false);
        pnlForm.setAlignmentX(LEFT_ALIGNMENT);
        pnlTieuCot.setAlignmentX(LEFT_ALIGNMENT);
        pnlDanhSachDong.setAlignmentX(LEFT_ALIGNMENT);
        pnlThemDong.setAlignmentX(LEFT_ALIGNMENT);
        pnlTongHang.setAlignmentX(LEFT_ALIGNMENT);
        pnlExcel.setAlignmentX(LEFT_ALIGNMENT);
        pnlChan.setAlignmentX(LEFT_ALIGNMENT);
        pnlThan.add(pnlForm);
        pnlThan.add(Box.createVerticalStrut(12));
        pnlThan.add(pnlTieuCot);
        pnlThan.add(pnlDanhSachDong);
        pnlThan.add(pnlThemDong);
        pnlThan.add(pnlTongHang);
        pnlThan.add(Box.createVerticalStrut(8));
        pnlThan.add(pnlExcel);
        pnlThan.add(Box.createVerticalStrut(12));
        pnlThan.add(pnlChan);

        pnlNoiDung.add(lblTieuDe, BorderLayout.NORTH);
        pnlNoiDung.add(pnlThan, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(pnlNoiDung, BorderLayout.NORTH);
    }

    private JPanel pnlTaoDongHang() {
        JPanel pnlDong = new JPanel(new GridLayout(1, 5, 8, 0));
        pnlDong.setOpaque(false);
        pnlDong.setBorder(new EmptyBorder(4, 0, 4, 0));
        pnlDong.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));

        JComboBox<String> cmbSanPham = new JComboBox<>(DuLieuMau.dsSanPham());
        JTextField txtSoLuong = txtTaoTextField("0");
        JTextField txtDonGia = txtTaoTextField("0");
        JTextField txtThanhTien = txtTaoTextField("0");
        txtThanhTien.setEditable(false);
        ViewTheme.vienHop(cmbSanPham);

        Runnable tinhThanhTien = () -> {
            try {
                long soLuong = Long.parseLong(txtSoLuong.getText().trim().replace(",", ""));
                long donGia = Long.parseLong(txtDonGia.getText().trim().replace(",", ""));
                txtThanhTien.setText(ViewTheme.tien(soLuong * donGia));
            } catch (NumberFormatException ex) {
                txtThanhTien.setText("0");
            }
            tinhTong();
        };

        DocumentListener listenerTinh = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                tinhThanhTien.run();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                tinhThanhTien.run();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                tinhThanhTien.run();
            }
        };
        txtSoLuong.getDocument().addDocumentListener(listenerTinh);
        txtDonGia.getDocument().addDocumentListener(listenerTinh);

        JButton btnXoa = new JButton("Xóa");
        ViewTheme.nutDo(btnXoa);
        btnXoa.addActionListener(e -> {
            if (pnlDanhSachDong.getComponentCount() > 1) {
                pnlDanhSachDong.remove(pnlDong);
                pnlDanhSachDong.revalidate();
                pnlDanhSachDong.repaint();
                tinhTong();
            }
        });

        pnlDong.add(cmbSanPham);
        pnlDong.add(txtSoLuong);
        pnlDong.add(txtDonGia);
        pnlDong.add(txtThanhTien);
        pnlDong.add(btnXoa);
        return pnlDong;
    }

    private void tinhTong() {
        long tongTien = 0;
        for (Component component : pnlDanhSachDong.getComponents()) {
            if (component instanceof JPanel) {
                JPanel pnlDong = (JPanel) component;
                if (pnlDong.getComponentCount() >= 4
                        && pnlDong.getComponent(3) instanceof JTextField) {
                    try {
                        tongTien += Long.parseLong(
                                ((JTextField) pnlDong.getComponent(3)).getText().replace(",", ""));
                    } catch (NumberFormatException ex) {
                    }
                }
            }
        }
        lblTongTien.setText(ViewTheme.tien(tongTien));
    }

    private JTextField txtTaoTextField(String giaTri) {
        JTextField txt = new JTextField(giaTri);
        ViewTheme.vienNhap(txt);
        return txt;
    }

    private JLabel lblTaoLabel(String noiDung) {
        JLabel lbl = new JLabel(noiDung, JLabel.RIGHT);
        lbl.setFont(ViewTheme.FONT);
        return lbl;
    }
}
