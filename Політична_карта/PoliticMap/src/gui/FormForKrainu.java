package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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

import dao.KrainuDao;
import domain.Krainu;
import domain.Materuk;

public class FormForKrainu extends JDialog implements ActionListener {

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
		} else if (e.getSource() == data.onClose) {
			if (JOptionPane.showConfirmDialog(data.onClose, "Закрити активне вікно?") == JOptionPane.YES_OPTION)
				onClose();
		}
	}

	FormForKrainuData data = new FormForKrainuData(new KrainuDialogForm());

	void GolovneMenu() {
		Color colorMenu = (Color.DARK_GRAY);
		Font fontMenu = new Font("Courier New", Font.BOLD, 15);
		JMenuBar MenuBar = new JMenuBar();

		JMenu mFile = new JMenu("Меню");
		mFile.setForeground(Color.BLACK);
		mFile.setFont(fontMenu);
		MenuBar.setBackground(colorMenu);

		ImageIcon icon = Main.createIcon("/img/new.png");
		data.add = new JMenuItem("Додати запис", icon);
		data.add.setToolTipText("Виконати додавання нової країни до бази даних");
		data.add.setFont(fontMenu);
		data.add.setBackground(Color.WHITE);
		data.add.addActionListener(this);
		mFile.add(data.add);

		ImageIcon icon1 = Main.createIcon("/img/update.png");
		data.upd = new JMenuItem("Внести зміни", icon1);
		data.upd.setToolTipText("Внести зміни у інформацію про уже внесеної до бази даних країни");
		data.upd.setFont(fontMenu);
		data.upd.setBackground(Color.WHITE);
		data.upd.addActionListener(this);
		mFile.add(data.upd);

		ImageIcon icon2 = Main.createIcon("/img/delete.png");
		data.del = new JMenuItem("Видалити запис", icon2);
		data.del.setToolTipText("Виконати видалення інформації про країну у базі даних");
		data.del.setFont(fontMenu);
		data.del.setBackground(Color.WHITE);
		data.del.addActionListener(this);
		mFile.add(data.del);

		ImageIcon icon3 = Main.createIcon("/img/print.png");
		data.print = new JMenuItem("Вивести на друк", icon3);
		data.print.setToolTipText("Вивести на принтер інформацію з бази даних");
		data.print.setFont(fontMenu);
		data.print.setBackground(Color.WHITE);
		data.print.addActionListener(this);
		mFile.add(data.print);

		mFile.addSeparator();
		ImageIcon icon4 = Main.createIcon("/img/home.png");
		data.onClose = new JMenuItem("Головна форма", icon4);
		data.onClose.setToolTipText("Перехід до головної форми");
		data.onClose.setFont(fontMenu);
		data.onClose.setBackground(Color.WHITE);
		data.onClose.addActionListener(this);
		mFile.add(data.onClose);

		MenuBar.add(mFile);
		setJMenuBar(MenuBar);

	}

	public FormForKrainu(Materuk g) throws IOException {
		this.data.materuks = g;
		setTitle("Країни, які розташовані на материку:  " + g.getNazva());
		setLocationByPlatform(true);
		GolovneMenu();

		data.btClose = new JButton("Закрити вікно");
		data.btNew = new JButton("Додати");
		data.btEdit = new JButton("Редагувати");
		data.btDelete = new JButton("Видалити");
		data.btPrint = new JButton("Вивести на друк");

		data.krainaTableModel = getTableModel(g);
		data.krainuTable = new JTable(data.krainaTableModel);

		data.krainuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		data.krainuTable.setPreferredScrollableViewportSize(new Dimension(1000, 400));
		data.krainuTable.getTableHeader().setFont(new Font("Courier New", Font.BOLD, 14));
		data.krainuTable.getTableHeader().setForeground(Color.BLACK);
		data.krainuTable.getTableHeader().setBackground(Color.ORANGE);
		data.krainuTable.getColumnModel().getColumn(0).setMinWidth(200);
		data.krainuTable.getColumnModel().getColumn(1).setMinWidth(80);
		data.krainuTable.getColumnModel().getColumn(2).setMinWidth(60);
		data.krainuTable.getColumnModel().getColumn(3).setMinWidth(60);
		data.krainuTable.getColumnModel().getColumn(4).setMinWidth(240);
		data.krainuTable.getColumnModel().getColumn(5).setMinWidth(100);
		data.krainuTable.setGridColor(Color.DARK_GRAY);
		data.krainuTable.setRowHeight(25);
		Font FontGrid = new Font(Font.MONOSPACED, Font.PLAIN, 14);
		data.krainuTable.setForeground(Color.BLACK);
		data.krainuTable.setFont(FontGrid);

		JPanel mainPanel = new JPanel();

		JPanel btPanel = new JPanel(new GridLayout(10, 2, 5, 10));
		btPanel.add(data.btNew);
		btPanel.add(data.btEdit);
		btPanel.add(data.btDelete);
		btPanel.add(data.btPrint);
		btPanel.add(data.btClose);
		// btPanel.setOpaque(false);
		btPanel.setBackground(Color.DARK_GRAY);
		Border northBorder = BorderFactory.createLineBorder(Color.YELLOW, 2);
		btPanel.setBorder(northBorder);
		mainPanel.add(btPanel);

		JScrollPane scrollPane = new JScrollPane(data.krainuTable);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		tablePanel.add(scrollPane, BorderLayout.WEST);
		tablePanel.setOpaque(false);

		mainPanel.add(tablePanel, BorderLayout.WEST);
		mainPanel.setBackground(Color.DARK_GRAY);
		getRootPane().setDefaultButton(data.btClose);
		setSize(900, 480);
		setResizable(false);
		getContentPane().add(new JScrollPane(mainPanel), BorderLayout.CENTER);
		getContentPane().add(btPanel, BorderLayout.EAST);

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
		data.btClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(data.btClose,
						"Перейти до головного вікна програми?") == JOptionPane.YES_OPTION)
					onClose();
			}
		});

		data.krainuTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					upd();
				}
			}
		});

	}

	private KrainaTableModel getTableModel(Materuk g) {
		try {
			KrainuDao dao = new KrainuDao();
			final List<Krainu> krainus = dao.findByKrainu(g.getId());

			return new KrainaTableModel(krainus);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Помилка при одержанні даних:",
					JOptionPane.ERROR_MESSAGE);
		}
		return new KrainaTableModel(new ArrayList<Krainu>(0));
	}

	private void onClose() {
		dispose();
	}

	private void add() {
		Krainu krainu = new Krainu();
		krainu.setMaterukkrainuId(this.data.materuks.getId());
		data.krainuDialogForm.setKrainu(krainu);
		data.krainuDialogForm.setVisible(true);
		if (data.krainuDialogForm.getKrainu().getId() != null) {
			data.krainaTableModel.addKrainu(data.krainuDialogForm.getKrainu());
		}
	}

	private void upd() {
		int index = data.krainuTable.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(FormForKrainu.this, "Необхідно виділити запис в списку ", "Увага!",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Krainu krainu = data.krainaTableModel.getRowKrainu(index);
		if (krainu != null) {
			data.krainuDialogForm.setKrainu(krainu);
			data.krainuDialogForm.setVisible(true);
			data.krainaTableModel.refreshUpdatedTable();
		}
	}

	private void del() {
		if (JOptionPane.showConfirmDialog(FormForKrainu.this, "Ви впевнені, що хочете видалити інформацію про країну?",
				"Видалення запису про країну", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			int index = data.krainuTable.getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(FormForKrainu.this, "Необхідно виділити запис в списку ", "Увага!",
						JOptionPane.ERROR_MESSAGE);
				return;

			}

			try {
				Krainu g = data.krainaTableModel.getRowKrainu(index);
				if (g != null) {
					KrainuDao dao = new KrainuDao();
					dao.del(g.getId());
					data.krainaTableModel.removeRow(index);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(FormForKrainu.this, e.getMessage());
			}
		}
	}

	private void print() {
		try {
			MessageFormat headerFormat = new MessageFormat("Сторінка {0}");
			MessageFormat footerFormat = new MessageFormat("- {0} -");
			data.krainuTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
		} catch (PrinterException pe) {
			JOptionPane.showMessageDialog(this, pe.getMessage(), "Помилка при виведенні на друк:",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
