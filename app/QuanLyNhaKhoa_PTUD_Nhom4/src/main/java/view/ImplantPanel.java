package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ImplantPanel extends JPanel {

    private final DieuHuong dieuHuong;
    private final JPanel dsDong = new JPanel();

    public ImplantPanel(DieuHuong dieuHuong) {
        this.dieuHuong = dieuHuong;
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));
        JPanel the = new JPanel(new BorderLayout(0, 10));
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(new EmptyBorder(16, 18, 16, 18));
        the.add(ViewTheme.tieuDeTrang("Thêm phiếu implant"), BorderLayout.NORTH);

        JTextField ngayGui = o("2026-09-28");
        JComboBox<String> khach = new JComboBox<String>(DuLieuMau.KHACH);
        JComboBox<String> bacSi = new JComboBox<String>(DuLieuMau.BAC_SI);
        JComboBox<String> tinhTrang = new JComboBox<String>(new String[]{"Mới", "Đang làm", "Hoàn thành", "Hủy"});
        JTextField ngayNhan = o("2026-09-28 00:00");
        JTextField ngayXong = o("2026-09-28 00:00");
        JComboBox<String> ncc = new JComboBox<String>(new String[]{"STRAUMANN", "Osstem", "Dentium"});
        ViewTheme.vienHop(khach);
        ViewTheme.vienHop(bacSi);
        ViewTheme.vienHop(tinhTrang);
        ViewTheme.vienHop(ncc);
        khach.setSelectedIndex(1);

        JPanel form = new JPanel(new GridLayout(0, 2, 12, 10));
        form.setOpaque(false);
        form.add(phai("Ngày gửi"));
        form.add(ngayGui);
        form.add(phai("Khách hàng"));
        form.add(khach);
        form.add(phai("Bác sĩ"));
        form.add(bacSi);
        form.add(phai("Tình trạng"));
        form.add(tinhTrang);
        form.add(phai("Ngày nhận"));
        form.add(ngayNhan);
        form.add(phai("Ngày hoàn thành"));
        form.add(ngayXong);
        form.add(phai("Nhà cung cấp"));
        form.add(ncc);

        dsDong.setLayout(new BoxLayout(dsDong, BoxLayout.Y_AXIS));
        dsDong.setOpaque(false);
        dsDong.add(dong());
        JButton cong = new JButton("+");
        ViewTheme.nutChinh(cong);
        cong.addActionListener(e -> {
            dsDong.add(dong());
            dsDong.revalidate();
        });
        JPanel giua = new JPanel(new FlowLayout(FlowLayout.CENTER));
        giua.setOpaque(false);
        giua.add(cong);

        NutCongTac tiep = new NutCongTac(false);
        JButton boQua = new JButton("Bỏ qua");
        JButton luu = new JButton("Thêm phiếu implant");
        ViewTheme.nutXam(boQua);
        ViewTheme.nutChinh(luu);
        JButton baoCao = new JButton("Xem báo cáo implant");
        ViewTheme.nutLink(baoCao);
        baoCao.setForeground(ViewTheme.XANH_DAM);
        baoCao.addActionListener(e -> dieuHuong.mo("bc:implant"));
        boQua.addActionListener(e -> tinhTrang.setSelectedIndex(0));
        luu.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Đã thêm phiếu implant cho " + khach.getSelectedItem() + ".");
            if (!tiep.isSelected()) {
                dieuHuong.mo("bc:implant");
            }
        });

        JPanel chan = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        chan.setOpaque(false);
        chan.add(tiep);
        chan.add(new JLabel("Tôi muốn tiếp tục thêm phiếu implant"));
        chan.add(boQua);
        chan.add(luu);
        chan.add(baoCao);

        JPanel tieuCot = new JPanel(new GridLayout(1, 5, 8, 0));
        tieuCot.setOpaque(false);
        tieuCot.add(ViewTheme.chuPhu("Vật tư"));
        tieuCot.add(ViewTheme.chuPhu("Nhà sản xuất"));
        tieuCot.add(ViewTheme.chuPhu("SL"));
        tieuCot.add(ViewTheme.chuPhu("Ghi chú/Thông số"));
        tieuCot.add(ViewTheme.chuPhu("Bảo hành"));

        JPanel noi = new JPanel();
        noi.setLayout(new BoxLayout(noi, BoxLayout.Y_AXIS));
        noi.setOpaque(false);
        form.setAlignmentX(LEFT_ALIGNMENT);
        tieuCot.setAlignmentX(LEFT_ALIGNMENT);
        dsDong.setAlignmentX(LEFT_ALIGNMENT);
        giua.setAlignmentX(LEFT_ALIGNMENT);
        chan.setAlignmentX(LEFT_ALIGNMENT);
        noi.add(form);
        noi.add(Box.createVerticalStrut(10));
        noi.add(tieuCot);
        noi.add(dsDong);
        noi.add(giua);
        noi.add(Box.createVerticalStrut(12));
        noi.add(chan);
        the.add(noi, BorderLayout.CENTER);
        setLayout(new BorderLayout());
        add(the, BorderLayout.NORTH);
    }

    private JPanel dong() {
        JPanel p = new JPanel(new GridLayout(1, 6, 8, 0));
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(4, 0, 4, 0));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        JComboBox<String> vt = new JComboBox<String>(new String[]{"TRU IMPLANT", "Abutment", "Mão sứ"});
        JComboBox<String> nsx = new JComboBox<String>(new String[]{"STRAUMANN", "Osstem"});
        JTextField sl = o("1");
        JTextField gc = o("360");
        JComboBox<String> bh = new JComboBox<String>(new String[]{"6 tháng", "12 tháng", "24 tháng"});
        ViewTheme.vienHop(vt);
        ViewTheme.vienHop(nsx);
        ViewTheme.vienHop(bh);
        JButton xoa = new JButton("Xóa");
        ViewTheme.nutDo(xoa);
        xoa.addActionListener(e -> {
            if (dsDong.getComponentCount() > 1) {
                dsDong.remove(p);
                dsDong.revalidate();
                dsDong.repaint();
            }
        });
        p.add(vt);
        p.add(nsx);
        p.add(sl);
        p.add(gc);
        p.add(bh);
        p.add(xoa);
        return p;
    }

    private JTextField o(String giaTri) {
        JTextField t = new JTextField(giaTri);
        ViewTheme.vienNhap(t);
        return t;
    }

    private JLabel phai(String chu) {
        JLabel lb = new JLabel(chu, JLabel.RIGHT);
        lb.setFont(ViewTheme.FONT);
        return lb;
    }
}
