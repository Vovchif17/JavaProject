package gui;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTable;

import domain.Materuk;

public class FormForKrainuData {
	public JButton btNew;
	public JButton btEdit;
	public JButton btDelete;
	public JButton btPrint;
	public JButton btClose;
	public JTable krainuTable;
	public KrainuDialogForm krainuDialogForm;
	public KrainaTableModel krainaTableModel;
	public Materuk materuks;
	public JMenuItem add;
	public JMenuItem upd;
	public JMenuItem del;
	public JMenuItem print;
	public JMenuItem onClose;

	public FormForKrainuData(KrainuDialogForm krainuDialogForm) {
		this.krainuDialogForm = krainuDialogForm;
	}
}