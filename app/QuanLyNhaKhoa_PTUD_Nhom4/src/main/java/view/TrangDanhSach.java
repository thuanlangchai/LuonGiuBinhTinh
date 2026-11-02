package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import view.model.CauHinhTrang;

public class TrangDanhSach extends JPanel {

	private final JPanel pnlThe = new JPanel(new BorderLayout(0, 8));
	private final JLabel lblTieuDe = ViewTheme.lblTieuDeTrang("Danh sách");
	private final JButton btnThem = new JButton("Thêm");
	private final ONhap txtTim = new ONhap("Tìm...", 28);
	private final JLabel lblGhiChu = new JLabel(
			"<html><span style='color:#6c757d'>Bộ lọc yêu thích: Không có bộ lọc đã được yêu thích. Khi lưu bộ lọc, đánh dấu vào tùy chọn \"yêu thích\" để bộ lọc xuất hiện ở đây cho mọi người.</span></html>");
	private final JPanel pnlBoLocThem = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
	private final JLabel lblDem = new JLabel(" ");
	private final JComboBox<String> cmbKichThuoc = new JComboBox<String>(new String[] { "10", "15", "25", "50" });
	private final JPanel pnlPhanTrang = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
	private final JTable tbl = new JTable();
	private DefaultTableModel model;
	private TableRowSorter<DefaultTableModel> sapXep;
	private CauHinhTrang cauHinh;
	private int trang = 1;
	private boolean dangNap;
	private JComboBox<String> cmbNhom;
	private JComboBox<String> cmbVaiTro;

	public TrangDanhSach() {
		super(new BorderLayout());
		setOpaque(false);
		setBorder(new EmptyBorder(6, 6, 16, 6));
		pnlThe.setBackground(ViewTheme.TRANG);
		pnlThe.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)),
				new EmptyBorder(12, 14, 12, 14)));
		add(pnlThe, BorderLayout.NORTH);

		ViewTheme.btnNutXanh(btnThem);
		btnThem.addActionListener(e -> moForm(null, -1));

		JPanel pnlDau = new JPanel(new BorderLayout());
		pnlDau.setOpaque(false);
		pnlDau.add(lblTieuDe, BorderLayout.WEST);
		JPanel pnlPhai = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		pnlPhai.setOpaque(false);
		pnlPhai.add(btnThem);
		pnlDau.add(pnlPhai, BorderLayout.EAST);

		JPanel pnlVungTim = new JPanel(new BorderLayout(0, 6));
		pnlVungTim.setBackground(ViewTheme.NEN_TIM);
		pnlVungTim.setBorder(new EmptyBorder(10, 12, 10, 12));
		JLabel lblTim = ViewTheme.lblChuPhu("Tìm kiếm");
		JPanel pnlDongTim = new JPanel(new BorderLayout(8, 0));
		pnlDongTim.setOpaque(false);
		txtTim.setPreferredSize(new Dimension(280, 34));
		pnlDongTim.add(txtTim, BorderLayout.CENTER);
		JButton btnNangCao = new JButton("Hiển thị tìm nâng cao");
		ViewTheme.btnNutLink(btnNangCao);
		btnNangCao.setForeground(ViewTheme.XANH_DAM);
		pnlBoLocThem.setOpaque(false);
		pnlBoLocThem.setVisible(false);
		btnNangCao.addActionListener(e -> {
			pnlBoLocThem.setVisible(!pnlBoLocThem.isVisible());
			btnNangCao.setText(pnlBoLocThem.isVisible() ? "Ẩn tìm nâng cao" : "Hiển thị tìm nâng cao");
			revalidate();
		});
		JPanel pnlPhaiTim = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		pnlPhaiTim.setOpaque(false);
		pnlPhaiTim.add(btnNangCao);
		JPanel pnlTrenTim = new JPanel(new BorderLayout());
		pnlTrenTim.setOpaque(false);
		pnlTrenTim.add(lblTim, BorderLayout.NORTH);
		JPanel pnlGiuaTim = new JPanel(new BorderLayout());
		pnlGiuaTim.setOpaque(false);
		pnlGiuaTim.add(pnlDongTim, BorderLayout.CENTER);
		pnlGiuaTim.add(pnlPhaiTim, BorderLayout.EAST);
		pnlTrenTim.add(pnlGiuaTim, BorderLayout.CENTER);
		pnlVungTim.add(pnlTrenTim, BorderLayout.NORTH);
		pnlVungTim.add(lblGhiChu, BorderLayout.CENTER);
		pnlVungTim.add(pnlBoLocThem, BorderLayout.SOUTH);

		txtTim.getDocument().addDocumentListener(new DocumentListener() {
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

		JScrollPane scr = new JScrollPane(tbl);
		ViewTheme.vienNhap(scr);
		scr.setPreferredSize(new Dimension(200, 420));
		scr.getViewport().setBackground(ViewTheme.TRANG);

		lblDem.setFont(ViewTheme.FONT_NHO);
		lblDem.setForeground(ViewTheme.CHU_NHAT);
		ViewTheme.cmbVienHop(cmbKichThuoc);
		cmbKichThuoc.addActionListener(e -> {
			trang = 1;
			loc();
		});

		JPanel pnlChan = new JPanel(new BorderLayout());
		pnlChan.setOpaque(false);
		JPanel pnlTrai = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
		pnlTrai.setOpaque(false);
		pnlTrai.add(lblDem);
		pnlTrai.add(cmbKichThuoc);
		pnlChan.add(pnlTrai, BorderLayout.WEST);
		pnlPhanTrang.setOpaque(false);
		pnlChan.add(pnlPhanTrang, BorderLayout.EAST);

		pnlThe.add(pnlDau, BorderLayout.NORTH);
		JPanel pnlGiua = new JPanel(new BorderLayout(0, 8));
		pnlGiua.setOpaque(false);
		pnlGiua.add(pnlVungTim, BorderLayout.NORTH);
		pnlGiua.add(scr, BorderLayout.CENTER);
		pnlThe.add(pnlGiua, BorderLayout.CENTER);
		pnlThe.add(pnlChan, BorderLayout.SOUTH);

		ViewTheme.ganThaoTac(tbl, (lenh, hang) -> xuLy(lenh, hang));
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && tbl.rowAtPoint(e.getPoint()) >= 0
						&& !"Thao tác".equals(tbl.getColumnName(tbl.columnAtPoint(e.getPoint())))) {
					int hang = tbl.convertRowIndexToModel(tbl.rowAtPoint(e.getPoint()));
					moForm(docDong(hang), hang);
				}
			}
		});
	}

	public void nap(CauHinhTrang c) {
		dangNap = true;
		this.cauHinh = c;
		this.trang = 1;
		lblTieuDe.setText(c.tieuDe);
		btnThem.setText(c.nutThem);
		txtTim.setText("");
		txtTim.datGoiY(c.goiY);
		model = ViewTheme.taoModel(c.cot, true, true);
		for (Object[] dong : c.duLieu) {
			model.addRow(ViewTheme.dongBang(dong, true, true));
		}
		tbl.setModel(model);
		sapXep = new TableRowSorter<DefaultTableModel>(model);
		tbl.setRowSorter(sapXep);
		ViewTheme.trangTriBang(tbl);
		dungBoLoc();
		dangNap = false;
		loc();
	}

	private void dungBoLoc() {
		pnlBoLocThem.removeAll();
		cmbNhom = null;
		cmbVaiTro = null;
		if (cauHinh == null) {
			return;
		}
		if ("dichvu".equals(cauHinh.ma)) {
			cmbNhom = new JComboBox<String>();
			cmbNhom.addItem("Tất cả nhóm");
			for (String n : DuLieuMau.dsNhomDv()) {
				cmbNhom.addItem(n);
			}
			ViewTheme.cmbVienHop(cmbNhom);
			JTextField txtNgay = new JTextField("Ngày cập nhật");
			ViewTheme.vienNhap(txtNgay);
			txtNgay.setPreferredSize(new Dimension(180, 32));
			pnlBoLocThem.add(cmbNhom);
			pnlBoLocThem.add(txtNgay);
			cmbNhom.addActionListener(e -> {
				trang = 1;
				loc();
			});
		} else if ("nhanvien".equals(cauHinh.ma)) {
			cmbVaiTro = new JComboBox<String>(
					new String[] { "Tất cả vai trò", "Bác sĩ", "Phụ tá", "Lễ tân", "Tư vấn", "Sale" });
			ViewTheme.cmbVienHop(cmbVaiTro);
			pnlBoLocThem.add(cmbVaiTro);
			pnlBoLocThem.add(new JLabel("Kích hoạt"));
			cmbVaiTro.addActionListener(e -> {
				trang = 1;
				loc();
			});
			pnlBoLocThem.setVisible(true);
		} else if ("kiemkho".equals(cauHinh.ma)) {
			JTextField txtNgay = new JTextField("Ngày tạo");
			ViewTheme.vienNhap(txtNgay);
			txtNgay.setPreferredSize(new Dimension(180, 32));
			pnlBoLocThem.add(txtNgay);
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
		String tu = txtTim.getText().trim().toLowerCase();
		List<Integer> khop = new ArrayList<Integer>();
		for (int i = 0; i < model.getRowCount(); i++) {
			if (!khopDong(i, tu)) {
				continue;
			}
			khop.add(i);
		}
		int size = Integer.parseInt(String.valueOf(cmbKichThuoc.getSelectedItem()));
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
		lblDem.setText(ViewTheme.demDong(hienTu, hienDen, khop.size()));
		vePhanTrang(soTrang);
		int cao = Math.min(460, 48 + Math.max(2, hien.size()) * 36);
		tbl.getParent().getParent().setPreferredSize(new Dimension(200, Math.max(160, cao)));
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
		if (cmbNhom != null && cmbNhom.getSelectedIndex() > 0) {
			int cotNhom = timCot("Nhóm dịch vụ");
			Object v = model.getValueAt(hang, cotNhom >= 0 ? cotNhom : 1);
			if (v == null || !cmbNhom.getSelectedItem().equals(v.toString())) {
				return false;
			}
		}
		if (cmbVaiTro != null && cmbVaiTro.getSelectedIndex() > 0) {
			int cot = timCot("Vai trò");
			if (cot >= 0) {
				Object v = model.getValueAt(hang, cot);
				if (v == null || !cmbVaiTro.getSelectedItem().equals(v.toString())) {
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
		pnlPhanTrang.removeAll();
		JButton btnDau = btnNutTrang("«", 1);
		JButton btnTruoc = btnNutTrang("‹", Math.max(1, trang - 1));
		pnlPhanTrang.add(btnDau);
		pnlPhanTrang.add(btnTruoc);
		for (int i = 1; i <= soTrang && i <= 6; i++) {
			JButton n = btnNutTrang(String.valueOf(i), i);
			if (i == trang) {
				n.setBackground(ViewTheme.XANH);
				n.setForeground(ViewTheme.TRANG);
			}
			pnlPhanTrang.add(n);
		}
		pnlPhanTrang.add(btnNutTrang("›", Math.min(soTrang, trang + 1)));
		pnlPhanTrang.add(btnNutTrang("»", soTrang));
		pnlPhanTrang.revalidate();
		pnlPhanTrang.repaint();
	}

	private JButton btnNutTrang(String chu, int toi) {
		JButton btn = new JButton(chu);
		ViewTheme.btnNutXam(btn);
		btn.addActionListener(e -> {
			trang = toi;
			loc();
		});
		return btn;
	}

	private void xuLy(String lenh, int hang) {
		if ("Xóa".equals(lenh)) {
			int chon = JOptionPane.showConfirmDialog(this, "Xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
			if (chon == JOptionPane.YES_OPTION) {
				Object[] dong = docDong(hang);
				if (!DanhMucDb.xoa(cauHinh.ma, dong)) {
					JOptionPane.showMessageDialog(this, "Không xóa được trên database.");
					return;
				}
				nap(DanhMucDb.lay(cauHinh.ma));
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
			boolean them = hang < 0;
			if (!DanhMucDb.luu(cauHinh.ma, dong, them)) {
				JOptionPane.showMessageDialog(this, "Không lưu được lên database.");
				return;
			}
			nap(DanhMucDb.lay(cauHinh.ma));
		});
	}
}
