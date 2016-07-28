package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Krainu;

public class KrainaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2677658636846257452L;

	private String[] columns = new String[] { "Назва країни", "Устрій", "Кількість населення, тис. чол.",
			"Площа, тис. кв.км.", "Опис", "Рівень розвитку", "Розмір ВВП, млн.$" };

	private final List<Krainu> krainus;

	public KrainaTableModel(List<Krainu> krainus) {
		this.krainus = krainus;
	}

	public void addKrainu(Krainu krainu) {
		krainus.add(krainu);
		fireTableRowsInserted(0, krainus.size());
	}

	public Krainu getRowKrainu(int rowIndex) {
		return krainus.get(rowIndex);
	}

	public void removeRow(int rowIndex) {
		krainus.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void refreshUpdatedTable() {
		fireTableRowsUpdated(0, krainus.size());
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Krainu g = krainus.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return g.getNazva();
		case 1:
			if (g.getYstriu() == Krainu.MONARH) {
				return "Монархія";
			} else if (g.getYstriu() == Krainu.RESPYBLIKA) {
				return "Республіка";
			} else if (g.getYstriu() == Krainu.FEDERACIJA) {
				return "Федерація";
			} else if (g.getYstriu() == Krainu.INSHE) {
				return "Інше";
			}
		case 2:
			return Integer.toString(g.getK_nasel());
		case 3:
			return Float.toString(g.getPloshcha());

		case 4:
			return g.getOpus();
		case 5:
			if (g.getRozvutok() == Krainu.ROZVUNYTA) {
				return "Розвинута країна";
			} else if (g.getRozvutok() == Krainu.ROZVUVAETSJA) {
				return "Країна, яка розвивається";
			} else if (g.getRozvutok() == Krainu.TRETIU_SVIT) {
				return "Країна третього світу";
			}

		case 6:
			return Float.toString(g.getVvp());

		}
		return "";
	}

	public int getRowCount() {
		return krainus.size();
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