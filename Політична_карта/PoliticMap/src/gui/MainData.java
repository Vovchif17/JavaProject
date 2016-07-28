package gui;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTable;

public class MainData {
	/**
	 * ������ ��� ���������� ��������.
	 */
	public JButton btNew;
	/**
	 * ������ ��� ���������� ��������.
	 */
	public JButton btEdit;
	/**
	 * ������ ��� ��������� ��������.
	 */
	public JButton btDelete;
	public JButton btOpen;
	public JButton btPrint;
	/**
	 * ������ ��� �������� �����.
	 */
	public JButton btClose;
	/**
	 * ������� �� ����������.
	 */
	public JTable materuksTable;
	/**
	 * �����, �� ��������������������� ��� ���������� ������ ��������, ���
	 * ����������� ���������.
	 */
	public MaterukDialogForm materukDialogForm;
	/**
	 * ���� ����� ��� ��������, �� ������� ������������ � �������
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