package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import dao.MaterukDao;
import domain.Materuk;

/**
 * ������� ����� ��������. ³������� ������ �������� �� ������ ��� ������ ��
 * ����������.
 */

public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = -1286577455252661221L;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == data.add) {
			add();
		} else if (e.getSource() == data.upd) {
			upd();
		} else if (e.getSource() == data.del) {
			del();
		} else if (e.getSource() == data.print) {
			print();
		} else if (e.getSource() == data.open) {
			try {
				open();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == data.onClose) {
			if (JOptionPane.showConfirmDialog(data.onClose, "ϳ�������� ����� � ��������!") == JOptionPane.YES_OPTION)
				System.exit(0);

		}
	}

	MainData data = new MainData(new MaterukDialogForm());

	// ������� ����
	void MainMenu() {
		Color colorMenu = (Color.DARK_GRAY);
		Font fontMenu = new Font("Courier New", Font.BOLD, 15);
		JMenuBar MenuBar = new JMenuBar();

		JMenu mFile = new JMenu("����");
		mFile.setForeground(Color.BLACK);
		mFile.setFont(fontMenu);
		MenuBar.setBackground(colorMenu);

		ImageIcon icon = createIcon("/img/new.png");
		data.add = new JMenuItem("������ �����", icon);
		data.add.setToolTipText("�������� ��������� ������ �������� �� ���� �����");
		data.add.setFont(fontMenu);
		data.add.setBackground(Color.WHITE);
		data.add.addActionListener(this);
		mFile.add(data.add);

		ImageIcon icon1 = createIcon("/img/update.png");
		data.upd = new JMenuItem("������ ����", icon1);
		data.upd.setToolTipText("������ ���� � ���������� ��� ��� ��������� �� ���� ����� ��������");
		data.upd.setFont(fontMenu);
		data.upd.setBackground(Color.WHITE);
		data.upd.addActionListener(this);
		mFile.add(data.upd);

		ImageIcon icon2 = createIcon("/img/delete.png");
		data.del = new JMenuItem("�������� �����", icon2);
		data.del.setToolTipText("�������� ��������� ���������� ��� ������� � ��� �����");
		data.del.setFont(fontMenu);
		data.del.setBackground(Color.WHITE);
		data.del.addActionListener(this);
		mFile.add(data.del);

		ImageIcon icon3 = createIcon("/img/print.png");
		data.print = new JMenuItem("������� �� ����", icon3);
		data.print.setToolTipText("������� �� ������� ���������� � ���� �����");
		data.print.setFont(fontMenu);
		data.print.setBackground(Color.WHITE);
		data.print.addActionListener(this);
		mFile.add(data.print);

		mFile.addSeparator();

		ImageIcon icon4 = createIcon("/img/open.png");
		data.open = new JMenuItem("����� ��������", icon4);
		data.open.setToolTipText("³������ ������� ����, �� ����������� �� ��������");
		data.open.setFont(fontMenu);
		data.open.setBackground(Color.WHITE);
		data.open.addActionListener(this);
		mFile.add(data.open);

		mFile.addSeparator();

		ImageIcon icon5 = createIcon("/img/close.png");
		data.onClose = new JMenuItem("������� ��������", icon5);
		data.onClose.setToolTipText("����� � ��������");
		data.onClose.setBackground(Color.WHITE);
		data.onClose.setFont(fontMenu);
		data.onClose.addActionListener((ActionListener) this);
		mFile.add(data.onClose);

		MenuBar.add(mFile);
		setJMenuBar(MenuBar);

	}

	// ---------------------------------------------------------
	protected static ImageIcon createIcon(String path) {
		URL imgURL = Main.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("���� � ����� ������ �� ��������: " + path);
			return null;
		}
	}

	// ---------------------------------------------------------
	public Main() {
		super();
		setTitle("�������� �����");// ����� �������� �����
		setLocationByPlatform(true);

		MainMenu();

		data.btClose = new JButton("�������");// ������� ������ ��� ��������
												// �����
		data.btNew = new JButton("������");// ������� ������ ��� ����������
											// ������
											// ��������
		data.btEdit = new JButton("����������");// ������� ������ ��� ����������
		// ��������
		data.btDelete = new JButton("��������");// ������� ������ ��� ���������
		data.btOpen = new JButton("����� ��������");// ������� ������ ���
														// ��������
		// ����� ����
		data.btOpen.setForeground(Color.RED);
		data.btPrint = new JButton("������� �� ����");// ������� ������ ���
														// ���������
														// �� ����

		data.materukTableModel = getTableModel();// ������� ���� ����� ���
													// ��������,
		// ���������� ��� ����������� �
		// �������
		data.materuksTable = new JTable(data.materukTableModel);// �������
																// ������� ���
		// �����������
		// ��������
		data.materuksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// ������
		// ��
		// �����
		// �������
		// �
		// �������
		// �����
		// 1
		// �����
		// ��
		// ���
		data.materuksTable.setPreferredScrollableViewportSize(new Dimension(1000, 260));
		data.materuksTable.getTableHeader().setFont(new Font("Courier New", Font.BOLD, 13));
		data.materuksTable.getTableHeader().setForeground(Color.BLACK);
		data.materuksTable.getTableHeader().setBackground(Color.ORANGE);
		data.materuksTable.getColumnModel().getColumn(0).setMinWidth(30);
		data.materuksTable.getColumnModel().getColumn(0).setMaxWidth(30);
		data.materuksTable.getColumnModel().getColumn(1).setMinWidth(120);
		data.materuksTable.getColumnModel().getColumn(2).setMinWidth(80);
		data.materuksTable.getColumnModel().getColumn(3).setMinWidth(90);
		data.materuksTable.getColumnModel().getColumn(4).setMinWidth(60);
		data.materuksTable.getColumnModel().getColumn(5).setMinWidth(300);

		data.materuksTable.setGridColor(Color.DARK_GRAY);
		data.materuksTable.setRowHeight(25);
		Font FontGrid = new Font(Font.MONOSPACED, Font.PLAIN, 14);
		data.materuksTable.setForeground(Color.BLACK);
		data.materuksTable.setFont(FontGrid);

		JPanel mainPanel = new JPanel(); // ������� ������� ������ �����

		JPanel btPanel = new JPanel(new GridLayout(8, 2, 5, 10));// �������
																	// ������
																	// ���
																	// ��������
		btPanel.add(data.btNew);// �������� ������ ���������� �������� �� �����
		btPanel.add(data.btEdit);// ...��������� ��� ����� ��������
		btPanel.add(data.btDelete);
		btPanel.add(data.btPrint);
		btPanel.add(data.btOpen);
		btPanel.add(data.btClose);
		btPanel.setBackground(Color.DARK_GRAY);
		Border northBorder = BorderFactory.createLineBorder(Color.YELLOW, 2);
		btPanel.setBorder(northBorder);
		// btPanel.setOpaque(false);
		mainPanel.add(btPanel);

		JScrollPane scrollPane = new JScrollPane(data.materuksTable);// ������
																		// ����
		// � ��������
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.setBorder(new BevelBorder(BevelBorder.RAISED));
		tablePanel.add(scrollPane, BorderLayout.WEST);// ������ ������� ��
														// �����
		tablePanel.setOpaque(false);

		mainPanel.add(tablePanel, BorderLayout.WEST);
		mainPanel.setBackground(Color.DARK_GRAY);
		getRootPane().setDefaultButton(data.btOpen);
		setSize(950, 380);// ����� �����
		setResizable(false);// �������, �� ����� ����� ������ ���� ���������
		getContentPane().add(new JScrollPane(mainPanel), BorderLayout.CENTER);
		getContentPane().add(btPanel, BorderLayout.EAST);

		// ��� ��������� �� ������ ���������� ��������, ���������������
		// ��������� �����
		data.btNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});

		data.btEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upd();
			}
		});

		data.btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				del();
			}
		});

		data.btPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print();
			}
		});

		data.btOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					open();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});

		data.btClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(data.onClose, "����� � ��������?") == JOptionPane.YES_OPTION)
					System.exit(0);

			}
		});
		/**
		 * ������� �� �������� ��� �����. ³���������� ����� upd(); ���
		 * ����������� ���������� ��� �������
		 */

		data.materuksTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					upd();
				}
			}
		});

	}

	/**
	 * �������� ������ ��� �������� �� ���������� ����� ��������� �����
	 * MaterukTableModel, ��� ����, ��� �� �������� ����� ���� ���������� �
	 * ������� � ������� ���� ��������.
	 * 
	 * @return ������ �������� ��� ����������� � ������� �� ������� ����
	 *         ��������.
	 */
	private MaterukTableModel getTableModel() {
		try {
			MaterukDao dao = new MaterukDao();
			final List<Materuk> materuks = dao.findAll();

			return new MaterukTableModel(materuks);
		} catch (Exception e) { // �������� �� ������� ���������� ��� �������
			e.printStackTrace(); // ������ �������� ���� �� ��������
									// ������������
			JOptionPane.showMessageDialog(this, e.getMessage(), "������� ��� �������� �����:",
					JOptionPane.ERROR_MESSAGE);
		} // ������� ������ ������ ��������
		return new MaterukTableModel(new ArrayList<Materuk>(0));
	}

	private void print() {
		try {
			MessageFormat headerFormat = new MessageFormat("������� {0}");
			MessageFormat footerFormat = new MessageFormat("- {0} -");
			data.materuksTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
		} catch (PrinterException pe) {
			JOptionPane.showMessageDialog(this, pe.getMessage(), "������� ��� �������� �� ����:",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void open() throws Exception {
		int index = data.materuksTable.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(Main.this, "��������� ������� �����", "������������!", JOptionPane.OK_OPTION);
			return;
		}

		Integer id = Integer.parseInt((String) (data.materuksTable.getValueAt(index, 0)));
		MaterukDao dao = new MaterukDao();
		Materuk materuk = dao.findById(id);
		FormForKrainu formForKraina = new FormForKrainu(materuk);
		formForKraina.setVisible(true);
	}

	/**
	 * ³������ ����� ��� ���������� ������ ��������, �� �������� ������
	 * �������� �� ������� � ������� ����.
	 */
	private void add() {
		// �������� ���� ��� ����� � ����
		// �� ������ ��
		data.materukDialogForm.setMateruk(new Materuk());
		data.materukDialogForm.setVisible(true);
		if (data.materukDialogForm.getMateruk().getId() != null) {
			// ���� ������� ��� ���������� �������, �� �������� ���� ����� ��
			// ������� � �������� ����.
			data.materukTableModel.addMateruk(data.materukDialogForm.getMateruk());
		}
	}

	/**
	 * ³������ ����� ��� ����������� ��������.
	 */
	private void upd() {
		// ������ ������ ��������� �����, � ���� �� ����� -1, �� ��������
		// �� ����������. ������ -1 ������, �� ������ ����� �� � ��������
		int index = data.materuksTable.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(Main.this, "��������� ������� ����� � ������ ", "�����!",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// ������ ������� ��� ��������� �����, � ���� �� ������������ ��
		// ������� ���� ����������
		Materuk materuk = data.materukTableModel.getRowMateruk(index);
		if (materuk != null) {
			// ���������� �������� ��������, �� ������� ������������
			data.materukDialogForm.setMateruk(materuk);
			// �������� ����� ��� ����������� ��������
			data.materukDialogForm.setVisible(true);
			// ���� ����������� ���� �������� ������, �� �������� ���������� �
			// �������
			data.materukTableModel.refreshUpdatedTable();
		}
	}

	/**
	 * ������� ��������� � ������� ��������
	 */
	private void del() {
		if (JOptionPane.showConfirmDialog(Main.this, "ϳ�������� ��������� ������!", "�������� ���� ��� �������",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			// ������ ������ ��������� �����, � ���� �� ����� -1, ��
			// ��������
			// �� ����������. ������ -1 ������, �� ������ ����� �� � ��������
			int index = data.materuksTable.getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(Main.this, "��������� ������� ����� � ������ ", "�����!",
						JOptionPane.ERROR_MESSAGE);
				return;

			}

			try { // ������ ������� ��� ��������� �����, � ���� ��
					// ������������ �� ����� ���� ��������
				Materuk g = data.materukTableModel.getRowMateruk(index);
				if (g != null) {

					MaterukDao dao = new MaterukDao();
					dao.del(g.getId());// ������� �����, �� ������� ��������
										// ������� �� ��������� �����
					data.materukTableModel.removeRow(index); // ���� �����
					// ����������
					// ��������� ����� ��
					// �������
				}
			} catch (Exception e) { // ������ �������� ���� �� ��������
									// ������������
				JOptionPane.showMessageDialog(Main.this, e.getMessage());
			}
		}

	}

	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		Main main = new Main();
		main.setVisible(true);
		URL imgURL = Main.class.getResource("/img/icon.png");
		Image icon = Toolkit.getDefaultToolkit().getImage(imgURL);
		main.setIconImage(icon);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
