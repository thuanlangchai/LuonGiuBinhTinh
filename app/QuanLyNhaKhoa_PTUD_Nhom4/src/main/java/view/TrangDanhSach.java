package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class TrangDanhSach extends JPanel {

    private final JPanel the = new JPanel(new BorderLayout(0, 8));
    private final JLabel tieuDe = ViewTheme.tieuDeTrang("Danh sách");
    private final JButton nutThem = new JButton("Thêm");
    private final ONhap tim = new ONhap("Tìm...", 28);
    private final JLabel ghiChu = new JLabel("<html><span style='color:#6c757d'>Bộ lọc yêu thích: Không có bộ lọc đã được yêu thích. Khi lưu bộ lọc, đánh dấu vào tùy chọn \"yêu thích\" để bộ lọc xuất hiện ở đây cho mọi người.</span></html>");
    private final JPanel boLocThem = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
    private final JLabel dem = new JLabel(" ");
    private final JComboBox<String> kichThuoc = new JComboBox<String>(new String[]{"10", "15", "25", "50"});
    private final JPanel phanTrang = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
    private final JTable bang = new JTable();
    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> sapXep;
    private CauHinhTrang cauHinh;
    private int trang = 1;
    private boolean dangNap;
    private JComboBox<String> cboNhom;
    private JComboBox<String> cboVaiTro;

    public TrangDanhSach() {
        super(new BorderLayout());
        setOpaque(false);
        setBorder(new EmptyBorder(6, 6, 16, 6));
        the.setBackground(ViewTheme.TRANG);
        the.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)),
                new EmptyBorder(12, 14, 12, 14)));
        add(the, BorderLayout.NORTH);

        ViewTheme.nutXanh(nutThem);
        nutThem.addActionListener(e -> moForm(null, -1));

        JPanel dau = new JPanel(new BorderLayout());
        dau.setOpaque(false);
        dau.add(tieuDe, BorderLayout.WEST);
        JPanel phai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        phai.setOpaque(false);
        phai.add(nutThem);
        dau.add(phai, BorderLayout.EAST);

        JPanel vungTim = new JPanel(new BorderLayout(0, 6));
        vungTim.setBackground(ViewTheme.NEN_TIM);
        vungTim.setBorder(new EmptyBorder(10, 12, 10, 12));
        JLabel lbTim = ViewTheme.chuPhu("Tìm kiếm");
        JPanel dongTim = new JPanel(new BorderLayout(8, 0));
        dongTim.setOpaque(false);
        tim.setPreferredSize(new Dimension(280, 34));
        dongTim.add(tim, BorderLayout.CENTER);
        JButton nangCao = new JButton("Hiển thị tìm nâng cao");
        ViewTheme.nutLink(nangCao);
        nangCao.setForeground(ViewTheme.XANH_DAM);
        boLocThem.setOpaque(false);
        boLocThem.setVisible(false);
        nangCao.addActionListener(e -> {
            boLocThem.setVisible(!boLocThem.isVisible());
            nangCao.setText(boLocThem.isVisible() ? "Ẩn tìm nâng cao" : "Hiển thị tìm nâng cao");
            revalidate();
        });
        JPanel phaiTim = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        phaiTim.setOpaque(false);
        phaiTim.add(nangCao);
        JPanel trenTim = new JPanel(new BorderLayout());
        trenTim.setOpaque(false);
        trenTim.add(lbTim, BorderLayout.NORTH);
        JPanel giuaTim = new JPanel(new BorderLayout());
        giuaTim.setOpaque(false);
        giuaTim.add(dongTim, BorderLayout.CENTER);
        giuaTim.add(phaiTim, BorderLayout.EAST);
        trenTim.add(giuaTim, BorderLayout.CENTER);
        vungTim.add(trenTim, BorderLayout.NORTH);
        vungTim.add(ghiChu, BorderLayout.CENTER);
        vungTim.add(boLocThem, BorderLayout.SOUTH);

        tim.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                doiTim();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                doiTim();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                doiTim();
            }
        });

        JScrollPane cuon = new JScrollPane(bang);
        ViewTheme.vienNhap(cuon);
        cuon.setPreferredSize(new Dimension(200, 420));
        cuon.getViewport().setBackground(ViewTheme.TRANG);

        dem.setFont(ViewTheme.FONT_NHO);
        dem.setForeground(ViewTheme.CHU_NHAT);
        ViewTheme.vienHop(kichThuoc);
        kichThuoc.addActionListener(e -> {
            trang = 1;
            loc();
        });

        JPanel chan = new JPanel(new BorderLayout());
        chan.setOpaque(false);
        JPanel trai = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        trai.setOpaque(false);
        trai.add(dem);
        trai.add(kichThuoc);
        chan.add(trai, BorderLayout.WEST);
        phanTrang.setOpaque(false);
        chan.add(phanTrang, BorderLayout.EAST);

        the.add(dau, BorderLayout.NORTH);
        JPanel giua = new JPanel(new BorderLayout(0, 8));
        giua.setOpaque(false);
        giua.add(vungTim, BorderLayout.NORTH);
        giua.add(cuon, BorderLayout.CENTER);
        the.add(giua, BorderLayout.CENTER);
        the.add(chan, BorderLayout.SOUTH);

        ViewTheme.ganThaoTac(bang, (lenh, hang) -> xuLy(lenh, hang));
        bang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && bang.rowAtPoint(e.getPoint()) >= 0
                        && !"Thao tác".equals(bang.getColumnName(bang.columnAtPoint(e.getPoint())))) {
                    int hang = bang.convertRowIndexToModel(bang.rowAtPoint(e.getPoint()));
                    moForm(docDong(hang), hang);
                }
            }
        });
    }

    public void nap(CauHinhTrang c) {
        dangNap = true;
        this.cauHinh = c;
        this.trang = 1;
        tieuDe.setText(c.tieuDe);
        nutThem.setText(c.nutThem);
        tim.setText("");
        tim.datGoiY(c.goiY);
        model = ViewTheme.taoModel(c.cot, true, true);
        for (Object[] dong : c.duLieu) {
            model.addRow(ViewTheme.dongBang(dong, true, true));
        }
        bang.setModel(model);
        sapXep = new TableRowSorter<DefaultTableModel>(model);
        bang.setRowSorter(sapXep);
        ViewTheme.trangTriBang(bang);
        dungBoLoc();
        dangNap = false;
        loc();
    }

    private void dungBoLoc() {
        boLocThem.removeAll();
        cboNhom = null;
        cboVaiTro = null;
        if (cauHinh == null) {
            return;
        }
        if ("dichvu".equals(cauHinh.ma)) {
            cboNhom = new JComboBox<String>();
            cboNhom.addItem("Tất cả nhóm");
            for (String n : DuLieuMau.NHOM_DV) {
                cboNhom.addItem(n);
            }
            ViewTheme.vienHop(cboNhom);
            JTextField ngay = new JTextField("Ngày cập nhật");
            ViewTheme.vienNhap(ngay);
            ngay.setPreferredSize(new Dimension(180, 32));
            boLocThem.add(cboNhom);
            boLocThem.add(ngay);
            cboNhom.addActionListener(e -> {
                trang = 1;
                loc();
            });
        } else if ("nhanvien".equals(cauHinh.ma)) {
            cboVaiTro = new JComboBox<String>(new String[]{"Tất cả vai trò", "Bác sĩ", "Phụ tá", "Lễ tân", "Tư vấn", "Sale"});
            ViewTheme.vienHop(cboVaiTro);
            boLocThem.add(cboVaiTro);
            boLocThem.add(new JLabel("Kích hoạt"));
            cboVaiTro.addActionListener(e -> {
                trang = 1;
                loc();
            });
            boLocThem.setVisible(true);
        } else if ("kiemkho".equals(cauHinh.ma)) {
            JTextField ngay = new JTextField("Ngày tạo");
            ViewTheme.vienNhap(ngay);
            ngay.setPreferredSize(new Dimension(180, 32));
            boLocThem.add(ngay);
        }
    }

    private void doiTim() {
        if (dangNap) {
            return;
        }
        trang = 1;
        loc();
    }

    private void loc() {
        if (model == null || sapXep == null || dangNap) {
            return;
        }
        String tu = tim.getText().trim().toLowerCase();
        List<Integer> khop = new ArrayList<Integer>();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (!khopDong(i, tu)) {
                continue;
            }
            khop.add(i);
        }
        int size = Integer.parseInt(String.valueOf(kichThuoc.getSelectedItem()));
        int soTrang = Math.max(1, (khop.size() + size - 1) / size);
        if (trang > soTrang) {
            trang = soTrang;
        }
        int tuIdx = (trang - 1) * size;
        int denIdx = Math.min(khop.size(), tuIdx + size);
        List<Integer> hien = khop.subList(tuIdx, denIdx);
        sapXep.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                return hien.contains(entry.getIdentifier());
            }
        });
        int hienTu = khop.isEmpty() ? 0 : tuIdx + 1;
        int hienDen = denIdx;
        dem.setText(ViewTheme.demDong(hienTu, hienDen, khop.size()));
        vePhanTrang(soTrang);
        int cao = Math.min(460, 48 + Math.max(2, hien.size()) * 36);
        bang.getParent().getParent().setPreferredSize(new Dimension(200, Math.max(160, cao)));
        revalidate();
    }

    private boolean khopDong(int hang, String tu) {
        boolean coChu = tu.isEmpty();
        if (!coChu) {
            for (int c = 1; c < model.getColumnCount() - 1; c++) {
                Object v = model.getValueAt(hang, c);
                if (v != null && v.toString().toLowerCase().contains(tu)) {
                    coChu = true;
                    break;
                }
            }
        }
        if (!coChu) {
            return false;
        }
        if (cboNhom != null && cboNhom.getSelectedIndex() > 0) {
            Object v = model.getValueAt(hang, 1);
            if (v == null || !cboNhom.getSelectedItem().equals(v.toString())) {
                return false;
            }
        }
        if (cboVaiTro != null && cboVaiTro.getSelectedIndex() > 0) {
            int cot = timCot("Vai trò");
            if (cot >= 0) {
                Object v = model.getValueAt(hang, cot);
                if (v == null || !cboVaiTro.getSelectedItem().equals(v.toString())) {
                    return false;
                }
            }
        }
        return true;
    }

    private int timCot(String ten) {
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (ten.equals(model.getColumnName(i))) {
                return i;
            }
        }
        return -1;
    }

    private void vePhanTrang(int soTrang) {
        phanTrang.removeAll();
        JButton dau = nutTrang("«", 1);
        JButton truoc = nutTrang("‹", Math.max(1, trang - 1));
        phanTrang.add(dau);
        phanTrang.add(truoc);
        for (int i = 1; i <= soTrang && i <= 6; i++) {
            JButton n = nutTrang(String.valueOf(i), i);
            if (i == trang) {
                n.setBackground(ViewTheme.XANH);
                n.setForeground(ViewTheme.TRANG);
            }
            phanTrang.add(n);
        }
        phanTrang.add(nutTrang("›", Math.min(soTrang, trang + 1)));
        phanTrang.add(nutTrang("»", soTrang));
        phanTrang.revalidate();
        phanTrang.repaint();
    }

    private JButton nutTrang(String chu, int toi) {
        JButton b = new JButton(chu);
        ViewTheme.nutXam(b);
        b.addActionListener(e -> {
            trang = toi;
            loc();
        });
        return b;
    }

    private void xuLy(String lenh, int hang) {
        if ("Xóa".equals(lenh)) {
            int chon = JOptionPane.showConfirmDialog(this, "Xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                model.removeRow(hang);
                loc();
            }
            return;
        }
        if ("Xem".equals(lenh)) {
            JOptionPane.showMessageDialog(this, noiDung(hang), "Chi tiết", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        moForm(docDong(hang), hang);
    }

    private String noiDung(int hang) {
        StringBuilder sb = new StringBuilder();
        for (int c = 1; c < model.getColumnCount() - 1; c++) {
            sb.append(model.getColumnName(c)).append(": ").append(model.getValueAt(hang, c)).append("\n");
        }
        return sb.toString();
    }

    private Object[] docDong(int hang) {
        int n = cauHinh.cot.length;
        Object[] dong = new Object[n];
        for (int i = 0; i < n; i++) {
            dong[i] = model.getValueAt(hang, i + 1);
        }
        return dong;
    }

    private void moForm(Object[] cu, int hang) {
        if (cauHinh == null) {
            return;
        }
        BieuMau.mo(this, cauHinh, cu, dong -> {
            if (hang < 0) {
                model.addRow(ViewTheme.dongBang(dong, true, true));
            } else if (hang < model.getRowCount()) {
                for (int i = 0; i < dong.length && i + 1 < model.getColumnCount() - 1; i++) {
                    model.setValueAt(dong[i], hang, i + 1);
                }
            }
            loc();
        });
    }
}
