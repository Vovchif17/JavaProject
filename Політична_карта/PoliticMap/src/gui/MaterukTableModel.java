package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Materuk;

public class MaterukTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2677658636846257452L;

	private String[] columns = new String[] { "ID", "Назва материка", "Загальна площа, млн. кв.км",
			"Кількість населення, млн. чол.", "К-сть країн", "Опис" };

	private List<Materuk> materuks;

	public MaterukTableModel(List<Materuk> materuks) {
		this.materuks = materuks;
	}

	public void addMateruk(Materuk materuk) {
		materuks.add(materuk);
		fireTableRowsInserted(0, materuks.size());
	}

	public Materuk getRowMateruk(int rowIndex) {
		return materuks.get(rowIndex);
	}

	public void removeRow(int rowIndex) {
		materuks.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void refreshUpdatedTable() {
		fireTableRowsUpdated(0, materuks.size());
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Materuk g = materuks.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return Integer.toString(g.getId());
		case 1:
			return g.getNazva();
		case 2:
			return Float.toString(g.getPloshcha());
		case 3:
			return Integer.toString(g.getK_nasel());
		case 4:
			return Integer.toString(g.getK_krain());
		case 5:
			return g.getOpus();

		}
		return "";
	}

	public int getRowCount() {
		return materuks.size();
	}

	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	public int getColumnCount() {
		return columns.length;
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}
}