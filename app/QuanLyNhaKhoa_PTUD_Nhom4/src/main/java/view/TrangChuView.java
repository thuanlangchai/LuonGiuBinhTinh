package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.net.URL;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TrangChuView extends JFrame {

	private final String tenDangNhap;
	private final ImageIcon nen;

	public TrangChuView(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap == null || tenDangNhap.isBlank() ? "Nhóm 4" : tenDangNhap;
		this.nen = napNen();
		setTitle("Hệ Thống Quản Lý Nha Khoa Nhóm 4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1180, 700);
		setMinimumSize(new Dimension(960, 600));
		setLocationRelativeTo(null);
		setContentPane(new NenPanel());
	}

	private ImageIcon napNen() {
		URL url = TrangChuView.class.getResource("/nen-rang.jpg");
		if (url == null) {
			return null;
		}
		ImageIcon icon = new ImageIcon(url);
		if (icon.getIconWidth() <= 0) {
			return null;
		}
		return icon;
	}

	private void vaoTrangDau() {
		dispose();
		new QuanLyView(tenDangNhap).setVisible(true);
	}

	private JPanel pnlTaoThe() {
		JPanel pnlBoc = new JPanel(new GridBagLayout());
		pnlBoc.setOpaque(false);
		pnlBoc.setPreferredSize(new Dimension(500, 0));
		pnlBoc.setBorder(new EmptyBorder(36, 8, 36, 36));

		JPanel pnlThe = new TheTrong();
		pnlThe.setLayout(new BoxLayout(pnlThe, BoxLayout.Y_AXIS));
		pnlThe.setBorder(new EmptyBorder(34, 34, 30, 34));
		pnlThe.setMaximumSize(new Dimension(430, 560));

		JLabel lblNhom = lblNhan("NHÓM 4", new Font("Segoe UI", Font.BOLD, 13), ViewTheme.XANH);
		JLabel lblTieuDe = lblNhan("<html>Hệ Thống<br>Quản Lý Nha Khoa</html>", new Font("Segoe UI", Font.BOLD, 34),
				new Color(28, 42, 58));
		JLabel lblXinChao = lblNhan("Xin chào, " + tenDangNhap, new Font("Segoe UI", Font.PLAIN, 16),
				ViewTheme.CHU_NHAT);
		JLabel lblMoTa = lblNhan(
				"<html>Quản lý lịch hẹn, khách hàng, điều trị,<br>kho thuốc và thu chi tại phòng khám.</html>",
				ViewTheme.FONT, ViewTheme.CHU);

		JButton btnVaoTrang = new JButton("Vào trang đầu");
		ViewTheme.btnNutChinh(btnVaoTrang);
		btnVaoTrang.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnVaoTrang.setAlignmentX(LEFT_ALIGNMENT);
		btnVaoTrang.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
		btnVaoTrang.addActionListener(e -> vaoTrangDau());

		pnlThe.add(lblNhom);
		pnlThe.add(Box.createVerticalStrut(12));
		pnlThe.add(lblTieuDe);
		pnlThe.add(Box.createVerticalStrut(10));
		pnlThe.add(lblXinChao);
		pnlThe.add(Box.createVerticalStrut(16));
		pnlThe.add(lblMoTa);
		pnlThe.add(Box.createVerticalStrut(20));
		pnlThe.add(pnlTaoDongChiTiet("Lịch hẹn", "Khách hàng", "Điều trị"));
		pnlThe.add(Box.createVerticalStrut(8));
		pnlThe.add(pnlTaoDongChiTiet("Kho thuốc", "Thu chi", "Báo cáo"));
		pnlThe.add(Box.createVerticalStrut(26));
		pnlThe.add(btnVaoTrang);

		pnlBoc.add(pnlThe);
		getRootPane().setDefaultButton(btnVaoTrang);
		return pnlBoc;
	}

	private JPanel pnlTaoDongChiTiet(String a, String b, String c) {
		JPanel pnlDong = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
		pnlDong.setOpaque(false);
		pnlDong.setAlignmentX(LEFT_ALIGNMENT);
		pnlDong.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		pnlDong.add(theNho(a));
		pnlDong.add(theNho(b));
		pnlDong.add(theNho(c));
		return pnlDong;
	}

	private JLabel theNho(String chu) {
		JLabel lbl = new JLabel(chu);
		lbl.setFont(ViewTheme.FONT_NHO);
		lbl.setForeground(ViewTheme.XANH_DAM);
		lbl.setOpaque(true);
		lbl.setBackground(ViewTheme.XANH_DUONG_NHAT);
		lbl.setBorder(new EmptyBorder(4, 10, 4, 10));
		return lbl;
	}

	private JLabel lblNhan(String chu, Font font, Color mau) {
		JLabel lbl = new JLabel(chu);
		lbl.setFont(font);
		lbl.setForeground(mau);
		lbl.setAlignmentX(LEFT_ALIGNMENT);
		return lbl;
	}

	private final class NenPanel extends JPanel {

		private NenPanel() {
			setLayout(new BorderLayout());
			setBackground(new Color(214, 232, 242));
			add(pnlTaoThe(), BorderLayout.EAST);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (nen == null) {
				return;
			}
			int iw = nen.getIconWidth();
			int ih = nen.getIconHeight();
			if (iw <= 0 || ih <= 0) {
				return;
			}
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			int rong = getWidth();
			int cao = getHeight();
			double tiLe = Math.max(rong / (double) iw, cao / (double) ih);
			int dw = (int) Math.ceil(iw * tiLe);
			int dh = (int) Math.ceil(ih * tiLe);
			Image anh = nen.getImage();
			g2.drawImage(anh, (rong - dw) / 2, (cao - dh) / 2, dw, dh, this);
			g2.dispose();
		}
	}

	private static final class TheTrong extends JPanel {

		private TheTrong() {
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(new Color(255, 255, 255, 238));
			g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 22, 22);
			g2.setColor(ViewTheme.XANH);
			g2.fillRoundRect(0, 18, 6, getHeight() - 36, 6, 6);
			g2.dispose();
		}
	}
}
