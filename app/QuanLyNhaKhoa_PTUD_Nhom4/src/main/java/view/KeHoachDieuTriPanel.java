package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class KeHoachDieuTriPanel extends JPanel {

    private final JLabel ten = new JLabel("Lê Trần Quang Thanh");
    private final JLabel lienHe = new JLabel("<html>03xxxx2808<br>thanhqt@gmail.com<br>28 Lê Lai</html>");
    private final JLabel benh = new JLabel("<html>Lý do khám: Đau răng<br>Tiền sử: Béo phì</html>");
    private final JLabel tong = new JLabel("Tổng: 900,000");
    private final DefaultTableModel model = ViewTheme.taoModel(
            new String[]{"Ngày", "Dịch vụ", "Số lượng", "Đơn giá", "Thành tiền", "Trạng thái"}, false, false);

    public KeHoachDieuTriPanel() {
        setLayout(new BorderLayout());
        setBackground(ViewTheme.TRANG);
        setBorder(new EmptyBorder(12, 12, 12, 12));
        setPreferredSize(new Dimension(1100, 640));
        nap("0001", "Lê Trần Quang Thanh", "03xxxx2808", "thanhqt@gmail.com", "28 Lê Lai", "Đau răng", "Thăm khám - Tư vấn");

        JPanel avatar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(ViewTheme.XANH);
                g.fillOval(24, 8, 88, 88);
                g.setColor(Color.WHITE);
                g.setFont(ViewTheme.FONT_TRANG);
                g.drawString("LT", 48, 60);
            }
        };
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

        JTable bang = new JTable(model);
        ViewTheme.trangTriBang(bang);
        JScrollPane cuon = new JScrollPane(bang);
        cuon.setBorder(BorderFactory.createLineBorder(ViewTheme.VIEN));
        tong.setFont(ViewTheme.FONT_SO);
        tong.setForeground(ViewTheme.XANH_DAM);
        tong.setHorizontalAlignment(SwingConstants.RIGHT);
        JPanel phai = new JPanel(new BorderLayout(0, 8));
        phai.setBackground(ViewTheme.TRANG);
        phai.setBorder(new EmptyBorder(8, 8, 8, 8));
        JLabel dau = ViewTheme.tieuDeTrang("Kế hoạch điều trị");
        phai.add(dau, BorderLayout.NORTH);
        phai.add(cuon, BorderLayout.CENTER);
        phai.add(tong, BorderLayout.SOUTH);

        JSplitPane tach = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, trai, phai);
        tach.setDividerLocation(300);
        tach.setResizeWeight(0);
        add(tach, BorderLayout.CENTER);
    }

    public void nap(String ma, String hoTen, String sdt, String email, String diaChi, String lyDo, String dieuTri) {
        ten.setText(hoTen);
        lienHe.setText("<html>" + sdt + "<br>" + email + "<br>" + diaChi + "</html>");
        benh.setText("<html>Lý do khám: " + (lyDo == null || lyDo.isEmpty() ? "Đau răng" : lyDo)
                + "<br>Tiền sử: Béo phì<br>Mã KH: " + ma + "</html>");
        model.setRowCount(0);
        model.addRow(new Object[]{"27/09/2026", "Thăm khám - Tư vấn", "1", "200,000", "200,000", "Hoàn tất"});
        model.addRow(new Object[]{"27/09/2026", "Cạo vôi - Đánh bóng", "1", "700,000", "700,000", "Hoàn tất"});
        if (dieuTri != null && !dieuTri.isEmpty() && !dieuTri.contains("Thăm khám")) {
            model.addRow(new Object[]{"12/10/2026", dieuTri, "1", "0", "0", "Mới"});
        }
        tong.setText("Tổng: 900,000");
    }
}
