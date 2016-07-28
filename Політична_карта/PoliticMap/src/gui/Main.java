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
 * Основна форма програми. Відображає список материків та кнопок для роботи із
 * материками.
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
			if (JOptionPane.showConfirmDialog(data.onClose, "Підтвердіть вихід з програми!") == JOptionPane.YES_OPTION)
				System.exit(0);

		}
	}

	MainData data = new MainData(new MaterukDialogForm());

	// головне меню
	void MainMenu() {
		Color colorMenu = (Color.DARK_GRAY);
		Font fontMenu = new Font("Courier New", Font.BOLD, 15);
		JMenuBar MenuBar = new JMenuBar();

		JMenu mFile = new JMenu("Меню");
		mFile.setForeground(Color.BLACK);
		mFile.setFont(fontMenu);
		MenuBar.setBackground(colorMenu);

		ImageIcon icon = createIcon("/img/new.png");
		data.add = new JMenuItem("Додати запис", icon);
		data.add.setToolTipText("Виконати додавання нового материка до бази даних");
		data.add.setFont(fontMenu);
		data.add.setBackground(Color.WHITE);
		data.add.addActionListener(this);
		mFile.add(data.add);

		ImageIcon icon1 = createIcon("/img/update.png");
		data.upd = new JMenuItem("Внести зміни", icon1);
		data.upd.setToolTipText("Внести зміни у інформацію про уже внесеного до бази даних материка");
		data.upd.setFont(fontMenu);
		data.upd.setBackground(Color.WHITE);
		data.upd.addActionListener(this);
		mFile.add(data.upd);

		ImageIcon icon2 = createIcon("/img/delete.png");
		data.del = new JMenuItem("Видалити запис", icon2);
		data.del.setToolTipText("Виконати видалення інформації про материк у базі даних");
		data.del.setFont(fontMenu);
		data.del.setBackground(Color.WHITE);
		data.del.addActionListener(this);
		mFile.add(data.del);

		ImageIcon icon3 = createIcon("/img/print.png");
		data.print = new JMenuItem("Вивести на друк", icon3);
		data.print.setToolTipText("Вивести на принтер інформацію з бази даних");
		data.print.setFont(fontMenu);
		data.print.setBackground(Color.WHITE);
		data.print.addActionListener(this);
		mFile.add(data.print);

		mFile.addSeparator();

		ImageIcon icon4 = createIcon("/img/open.png");
		data.open = new JMenuItem("Країни материка", icon4);
		data.open.setToolTipText("Відкрити таблицю країн, які знаходяться на материку");
		data.open.setFont(fontMenu);
		data.open.setBackground(Color.WHITE);
		data.open.addActionListener(this);
		mFile.add(data.open);

		mFile.addSeparator();

		ImageIcon icon5 = createIcon("/img/close.png");
		data.onClose = new JMenuItem("Закрити програму", icon5);
		data.onClose.setToolTipText("Вихід з програми");
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
			System.err.println("Файл з такою назвою не знайдено: " + path);
			return null;
		}
	}

	// ---------------------------------------------------------
	public Main() {
		super();
		setTitle("Політична карта");// задаю заголовк форми
		setLocationByPlatform(true);

		MainMenu();

		data.btClose = new JButton("Закрити");// створюю кнопку для закриття
												// форми
		data.btNew = new JButton("Додати");// створюю кнопку для добавлення
											// нового
											// материка
		data.btEdit = new JButton("Редагувати");// створюю кнопку для обновлення
		// материка
		data.btDelete = new JButton("Видалити");// створюю кнопку для видалення
		data.btOpen = new JButton("Країни материка");// створюю кнопку для
														// відкриття
		// форми країн
		data.btOpen.setForeground(Color.RED);
		data.btPrint = new JButton("Вивести на друк");// створюю кнопку для
														// виведення
														// на друк

		data.materukTableModel = getTableModel();// отримую набір даних про
													// материки,
		// необхідний для відображення в
		// таблиці
		data.materuksTable = new JTable(data.materukTableModel);// створюю
																// таблицю для
		// відображення
		// материків
		data.materuksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// вказую
		// що
		// можна
		// виділяти
		// в
		// таблиці
		// тільки
		// 1
		// рядок
		// за
		// раз
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

		JPanel mainPanel = new JPanel(); // створюю основну панель форми

		JPanel btPanel = new JPanel(new GridLayout(8, 2, 5, 10));// створюю
																	// панель
																	// для
																	// кнопочок
		btPanel.add(data.btNew);// добавляю кнопку добавлення материка на форму
		btPanel.add(data.btEdit);// ...аналогічно для інших кнопочок
		btPanel.add(data.btDelete);
		btPanel.add(data.btPrint);
		btPanel.add(data.btOpen);
		btPanel.add(data.btClose);
		btPanel.setBackground(Color.DARK_GRAY);
		Border northBorder = BorderFactory.createLineBorder(Color.YELLOW, 2);
		btPanel.setBorder(northBorder);
		// btPanel.setOpaque(false);
		mainPanel.add(btPanel);

		JScrollPane scrollPane = new JScrollPane(data.materuksTable);// поміщаю
																		// його
		// в панельку
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		tablePanel.setBorder(new BevelBorder(BevelBorder.RAISED));
		tablePanel.add(scrollPane, BorderLayout.WEST);// поміщаю таблицю на
														// форму
		tablePanel.setOpaque(false);

		mainPanel.add(tablePanel, BorderLayout.WEST);
		mainPanel.setBackground(Color.DARK_GRAY);
		getRootPane().setDefaultButton(data.btOpen);
		setSize(950, 380);// розмір форми
		setResizable(false);// вкзазую, що розмір форми змінити буде неможливо
		getContentPane().add(new JScrollPane(mainPanel), BorderLayout.CENTER);
		getContentPane().add(btPanel, BorderLayout.EAST);

		// при натисканні на кнопку добавлення материка, викликатиметься
		// наступний метод
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

				if (JOptionPane.showConfirmDialog(data.onClose, "Вийти з програми?") == JOptionPane.YES_OPTION)
					System.exit(0);

			}
		});
		/**
		 * Реакція на подвійний клік мишею. Відкривається метод upd(); для
		 * редагування інформації про материк
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
	 * Заповнює список всіх материків та підготовлює новий екземпляр класу
	 * MaterukTableModel, для того, щоб ці материки можна було відобразити в
	 * таблиці в головній формі програми.
	 * 
	 * @return список материків для відображення в таблиці на основній формі
	 *         програми.
	 */
	private MaterukTableModel getTableModel() {
		try {
			MaterukDao dao = new MaterukDao();
			final List<Materuk> materuks = dao.findAll();

			return new MaterukTableModel(materuks);
		} catch (Exception e) { // виводить на консоль інформацію про помилку
			e.printStackTrace(); // показує діалогове вікно із помилкою
									// користувачеві
			JOptionPane.showMessageDialog(this, e.getMessage(), "Помилка при одержанні даних:",
					JOptionPane.ERROR_MESSAGE);
		} // повертає пустий список материків
		return new MaterukTableModel(new ArrayList<Materuk>(0));
	}

	private void print() {
		try {
			MessageFormat headerFormat = new MessageFormat("Сторінка {0}");
			MessageFormat footerFormat = new MessageFormat("- {0} -");
			data.materuksTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
		} catch (PrinterException pe) {
			JOptionPane.showMessageDialog(this, pe.getMessage(), "Помилка при виведенні на друк:",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void open() throws Exception {
		int index = data.materuksTable.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(Main.this, "Необхідно вибрати запис", "Попередження!", JOptionPane.OK_OPTION);
			return;
		}

		Integer id = Integer.parseInt((String) (data.materuksTable.getValueAt(index, 0)));
		MaterukDao dao = new MaterukDao();
		Materuk materuk = dao.findById(id);
		FormForKrainu formForKraina = new FormForKrainu(materuk);
		formForKraina.setVisible(true);
	}

	/**
	 * Відкриває форму для добавлення нового материка, та добавляє нового
	 * материка до таблиці в головній формі.
	 */
	private void add() {
		// обновляє поля для вводу у формі
		// та показує її
		data.materukDialogForm.setMateruk(new Materuk());
		data.materukDialogForm.setVisible(true);
		if (data.materukDialogForm.getMateruk().getId() != null) {
			// якщо материк був добавлений успушно, то добавляє його також до
			// таблиці в онсновній формі.
			data.materukTableModel.addMateruk(data.materukDialogForm.getMateruk());
		}
	}

	/**
	 * Відкриває форму для редагування материка.
	 */
	private void upd() {
		// Отримує індекс виділеного рядка, і якщо він рівний -1, то виходить
		// із підпрограми. Індекс -1 означає, що жодний рядок не є виділений
		int index = data.materuksTable.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(Main.this, "Необхідно виділити запис в списку ", "Увага!",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// отримує материк для виділеного рядка, і якщо він встановлений то
		// пробуємо його редагувати
		Materuk materuk = data.materukTableModel.getRowMateruk(index);
		if (materuk != null) {
			// встановлює значення материка, що потрібно відредагувати
			data.materukDialogForm.setMateruk(materuk);
			// відображає форму для редагування материка
			data.materukDialogForm.setVisible(true);
			// якщо редагування було виконано успішно, то обновляє інформацію в
			// таблиці
			data.materukTableModel.refreshUpdatedTable();
		}
	}

	/**
	 * Видаляє виділеного в таблиці материка
	 */
	private void del() {
		if (JOptionPane.showConfirmDialog(Main.this, "Підтвердіть видалення запису!", "Знищення дани про материк",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			// Отримує індекс виділеного рядка, і якщо він рівний -1, то
			// виходить
			// із підпрограми. Індекс -1 означає, що жодний рядок не є виділений
			int index = data.materuksTable.getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(Main.this, "Необхідно виділити запис в списку ", "Увага!",
						JOptionPane.ERROR_MESSAGE);
				return;

			}

			try { // отримує материк для виділеного рядка, і якщо він
					// встановлений то пробує його видалити
				Materuk g = data.materukTableModel.getRowMateruk(index);
				if (g != null) {

					MaterukDao dao = new MaterukDao();
					dao.del(g.getId());// викликає метод, що повинен видалити
										// материк по вказаному ключу
					data.materukTableModel.removeRow(index); // після цього
					// відбувається
					// видалення рядка із
					// таблиці
				}
			} catch (Exception e) { // показує діалогове вікно із помилкою
									// користувачеві
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
