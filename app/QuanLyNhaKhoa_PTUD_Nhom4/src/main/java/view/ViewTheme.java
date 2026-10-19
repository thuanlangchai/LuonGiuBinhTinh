package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.BiConsumer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public final class ViewTheme {

    private ViewTheme() {
    }

    public static final Color XANH = new Color(14, 165, 219);
    public static final Color XANH_DAM = new Color(10, 132, 184);
    public static final Color NEN = new Color(14, 165, 219);
    public static final Color TRANG = Color.WHITE;
    public static final Color NEN_TIM = new Color(245, 247, 249);
    public static final Color VIEN = new Color(222, 226, 230);
    public static final Color CHU = new Color(52, 58, 64);
    public static final Color CHU_NHAT = new Color(108, 117, 125);
    public static final Color XANH_LA = new Color(92, 184, 92);
    public static final Color CAM = new Color(240, 150, 40);
    public static final Color DO = new Color(217, 83, 79);
    public static final Color TIM = new Color(186, 104, 200);
    public static final Color VANG = new Color(240, 173, 78);
    public static final Color XANH_DUONG_NHAT = new Color(232, 244, 252);

    public static final Font FONT = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_DAM = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_NHO = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_TRANG = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FONT_SO = new Font("Segoe UI", Font.BOLD, 18);

    private static final DateTimeFormatter F_BAY_GIO = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm:ss a", Locale.ENGLISH);
    private static final DecimalFormat TIEN;

    static {
        DecimalFormatSymbols kyHieu = new DecimalFormatSymbols(Locale.US);
        TIEN = new DecimalFormat("#,##0", kyHieu);
    }

    public static void caiDat() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        UIManager.put("OptionPane.messageFont", FONT);
        UIManager.put("OptionPane.buttonFont", FONT);
    }

    public static String tien(long so) {
        return TIEN.format(so);
    }

    public static String bayGio() {
        return LocalDateTime.now().format(F_BAY_GIO);
    }

    public static JLabel tieuDeTrang(String chu) {
        JLabel lb = new JLabel(chu);
        lb.setFont(FONT_TRANG);
        lb.setForeground(CHU);
        return lb;
    }

    public static JLabel tieuDeMuc(String chu) {
        JLabel lb = new JLabel(chu);
        lb.setFont(FONT_DAM);
        lb.setForeground(new Color(46, 160, 80));
        return lb;
    }

    public static JLabel chuPhu(String chu) {
        JLabel lb = new JLabel(chu);
        lb.setFont(FONT);
        lb.setForeground(CHU_NHAT);
        return lb;
    }

    public static void vienNhap(JComponent c) {
        c.setFont(FONT);
        c.setForeground(CHU);
        c.setBackground(TRANG);
        c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(VIEN),
                new EmptyBorder(6, 8, 6, 8)));
    }

    public static void vienHop(JComboBox<?> c) {
        c.setFont(FONT);
        c.setBackground(TRANG);
        c.setPreferredSize(new Dimension(160, 32));
    }

    public static void nutMau(JButton b, Color nen, Color chu) {
        b.setUI(new BasicButtonUI());
        b.setBackground(nen);
        b.setForeground(chu);
        b.setFont(FONT_DAM);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(8, 14, 8, 14));
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.putClientProperty("nenGoc", nen);
        if (b.getClientProperty("daHover") == null) {
            b.putClientProperty("daHover", Boolean.TRUE);
            b.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    Color goc = (Color) b.getClientProperty("nenGoc");
                    if (goc != null) {
                        b.setBackground(goc.brighter());
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    Color goc = (Color) b.getClientProperty("nenGoc");
                    if (goc != null) {
                        b.setBackground(goc);
                    }
                }
            });
        }
    }

    public static void nutChinh(JButton b) {
        nutMau(b, XANH, TRANG);
    }

    public static void nutPhu(JButton b) {
        nutMau(b, DO, TRANG);
    }

    public static void nutXanh(JButton b) {
        nutMau(b, XANH_LA, TRANG);
    }

    public static void nutCam(JButton b) {
        nutMau(b, CAM, TRANG);
    }

    public static void nutXam(JButton b) {
        nutMau(b, new Color(226, 230, 234), CHU);
    }

    public static void nutDo(JButton b) {
        nutMau(b, DO, TRANG);
    }

    public static void nutTrong(JButton b) {
        b.setUI(new BasicButtonUI());
        b.setContentAreaFilled(false);
        b.setOpaque(false);
        b.setBorder(new EmptyBorder(8, 10, 8, 10));
        b.setForeground(TRANG);
        b.setFont(FONT_DAM);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void nutLink(JButton b) {
        b.setUI(new BasicButtonUI());
        b.setContentAreaFilled(false);
        b.setOpaque(false);
        b.setBorder(new EmptyBorder(4, 8, 4, 8));
        b.setForeground(CHU);
        b.setFont(FONT);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static DefaultTableModel taoModel(String[] cot, boolean chon, boolean thaoTac) {
        int them = (chon ? 1 : 0) + (thaoTac ? 1 : 0);
        String[] dayDu = new String[cot.length + them];
        int i = 0;
        if (chon) {
            dayDu[i++] = "";
        }
        for (String c : cot) {
            dayDu[i++] = c;
        }
        if (thaoTac) {
            dayDu[i] = "Thao tác";
        }
        return new DefaultTableModel(dayDu, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return chon && column == 0;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                if (chon && column == 0) {
                    return Boolean.class;
                }
                return String.class;
            }
        };
    }

    public static Object[] dongBang(Object[] duLieu, boolean chon, boolean thaoTac) {
        int them = (chon ? 1 : 0) + (thaoTac ? 1 : 0);
        Object[] dong = new Object[duLieu.length + them];
        int i = 0;
        if (chon) {
            dong[i++] = Boolean.FALSE;
        }
        for (Object o : duLieu) {
            dong[i++] = o == null ? "" : String.valueOf(o);
        }
        if (thaoTac) {
            dong[i] = "Sửa      Xem      Xóa";
        }
        return dong;
    }

    public static void trangTriBang(JTable bang) {
        bang.setFont(FONT);
        bang.setRowHeight(36);
        bang.setForeground(CHU);
        bang.setGridColor(VIEN);
        bang.setShowHorizontalLines(true);
        bang.setShowVerticalLines(false);
        bang.setSelectionBackground(XANH_DUONG_NHAT);
        bang.setSelectionForeground(CHU);
        bang.setFillsViewportHeight(true);
        bang.setAutoCreateRowSorter(false);
        JTableHeader dau = bang.getTableHeader();
        dau.setReorderingAllowed(false);
        dau.setPreferredSize(new Dimension(0, 36));
        dau.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lb = (JLabel) super.getTableCellRendererComponent(table, value, false, false, row, column);
                lb.setBackground(NEN_TIM);
                lb.setForeground(CHU_NHAT);
                lb.setFont(FONT_DAM);
                lb.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, VIEN));
                lb.setHorizontalAlignment(SwingConstants.LEFT);
                return lb;
            }
        });
        bang.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String tenCot = table.getColumnName(column);
                String chu = value == null ? "" : value.toString();
                if (!isSelected) {
                    c.setBackground(TRANG);
                }
                if ("Thao tác".equals(tenCot)) {
                    c.setForeground(XANH_DAM);
                    if (c instanceof JLabel) {
                        ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                    }
                } else if (c instanceof JLabel) {
                    ((JLabel) c).setHorizontalAlignment(SwingConstants.LEFT);
                    if ("Hoạt động".equals(chu) || "Đã chi trả".equals(chu) || "Có".equals(chu) || "Đã duyệt".equals(chu)) {
                        c.setForeground(new Color(40, 150, 70));
                    } else if ("Mới".equals(chu) || "Đang điều trị".equals(chu)) {
                        c.setForeground(XANH_DAM);
                    } else if ("Hủy bỏ".equals(chu) || "Không".equals(chu)) {
                        c.setForeground(DO);
                    } else {
                        c.setForeground(CHU);
                    }
                }
                return c;
            }
        });
        if (bang.getColumnCount() > 0 && "".equals(bang.getColumnName(0))) {
            bang.getColumnModel().getColumn(0).setMaxWidth(46);
            bang.getColumnModel().getColumn(0).setPreferredWidth(46);
        }
        int cuoi = bang.getColumnCount() - 1;
        if (cuoi >= 0 && "Thao tác".equals(bang.getColumnName(cuoi))) {
            bang.getColumnModel().getColumn(cuoi).setPreferredWidth(160);
            bang.getColumnModel().getColumn(cuoi).setMaxWidth(180);
        }
        for (int i = 0; i < bang.getColumnCount(); i++) {
            if (i == 0 && "".equals(bang.getColumnName(0))) {
                continue;
            }
            if ("Thao tác".equals(bang.getColumnName(i))) {
                continue;
            }
            bang.getColumnModel().getColumn(i).setPreferredWidth(140);
        }
        if (bang.getColumnCount() <= 7) {
            bang.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        } else {
            bang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
    }

    public static void ganThaoTac(JTable bang, BiConsumer<String, Integer> xuLy) {
        bang.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int cot = bang.columnAtPoint(e.getPoint());
                int hang = bang.rowAtPoint(e.getPoint());
                if (hang < 0 || cot < 0) {
                    return;
                }
                if (!"Thao tác".equals(bang.getColumnName(cot))) {
                    return;
                }
                int rong = bang.getCellRect(hang, cot, true).width;
                int x = e.getX() - bang.getCellRect(hang, cot, true).x;
                String lenh = "Sửa";
                if (x > rong * 2 / 3) {
                    lenh = "Xóa";
                } else if (x > rong / 3) {
                    lenh = "Xem";
                }
                int modelRow = bang.convertRowIndexToModel(hang);
                xuLy.accept(lenh, modelRow);
            }
        });
    }

    public static String demDong(int tu, int den, int tong) {
        if (tong <= 0) {
            return "Hiển thị 0 tới 0 của 0 kết quả, kết quả trên 1 trang";
        }
        return "Hiển thị " + tu + " tới " + den + " của " + tong + " kết quả, kết quả trên 1 trang";
    }
}
