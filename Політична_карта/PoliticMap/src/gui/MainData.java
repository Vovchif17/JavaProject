package gui;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTable;

public class MainData {
	/**
	 * Кнопка для добавлення материка.
	 */
	public JButton btNew;
	/**
	 * Кнопка для обновлення материків.
	 */
	public JButton btEdit;
	/**
	 * Кнопка для видалення материка.
	 */
	public JButton btDelete;
	public JButton btOpen;
	public JButton btPrint;
	/**
	 * Кнопка для закриття форми.
	 */
	public JButton btClose;
	/**
	 * Таблиця із материками.
	 */
	public JTable materuksTable;
	/**
	 * Форма, що використовуватиметься для добавлення нового материка, або
	 * редагування існуючого.
	 */
	public MaterukDialogForm materukDialogForm;
	/**
	 * Набір даних про материки, що повинен відображатися в таблиці
	 * materuksTable
	 */
	public MaterukTableModel materukTableModel;
	public JMenuItem add;
	public JMenuItem upd;
	public JMenuItem del;
	public JMenuItem print;
	public JMenuItem open;
	public JMenuItem onClose;

	public MainData(MaterukDialogForm materukDialogForm) {
		this.materukDialogForm = materukDialogForm;
	}
}