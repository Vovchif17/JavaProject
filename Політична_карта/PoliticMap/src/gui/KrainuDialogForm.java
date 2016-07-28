package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.KrainuDao;
import domain.Krainu;

public class KrainuDialogForm extends JDialog {

	private static final String MONARH_ = "Монархія";
	private static final String RESPYBLIKA_ = "Республіка";
	private static final String FEDERACIJA_ = "Федерація";
	private static final String INSHE_ = "Інше";

	private static final String ROZVUNYTA_ = "Розвинута країна";
	private static final String ROZVUVAETSJA_ = "Країна, яка розвивається";
	private static final String TRETIU_SVIT_ = "Країна третього світу";

	private final static int LIVA_MEZHA_MITKUPOLJA = 10;
	private final static int SHURUNA_MITKU_POLJA = 160;
	private final static int SHURUNA_POLJA = 200;
	private static final long serialVersionUID = -7265530307974489903L;

	private Krainu krainu;

	private JTextField nazvaText = new JTextField();
	@SuppressWarnings("rawtypes")
	private JComboBox ystriuList;
	private JTextField k_naselText = new JTextField();
	private JTextField ploshchaText = new JTextField();
	private JTextField opusText = new JTextField();
	@SuppressWarnings("rawtypes")
	private JComboBox rozvutokList;

	private JTextField vvpText = new JTextField();;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public KrainuDialogForm() {
		setTitle("Новий запис");
		setSize(570, 350);
		setModal(true);
		setResizable(false);
		setLocationByPlatform(true);

		ystriuList = new JComboBox(new Object[] { MONARH_, RESPYBLIKA_, FEDERACIJA_, INSHE_ });
		ystriuList.setEditable(false);

		rozvutokList = new JComboBox(new Object[] { ROZVUNYTA_, ROZVUVAETSJA_, TRETIU_SVIT_ });
		rozvutokList.setEditable(false);

		JPanel DialogMainPanel = new JPanel();
		DialogMainPanel.setBackground(Color.DARK_GRAY);
		DialogMainPanel.setLayout(null);

		JLabel l = new JLabel("Назва країни:", SwingConstants.RIGHT);
		l.setBounds(LIVA_MEZHA_MITKUPOLJA, 10, SHURUNA_MITKU_POLJA, 30);
		DialogMainPanel.add(l);
		nazvaText.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + 10, 10, SHURUNA_POLJA, 30);
		l.setForeground(Color.ORANGE);
		DialogMainPanel.add(nazvaText);

		l = new JLabel("Устрій:", SwingConstants.RIGHT);
		l.setBounds(LIVA_MEZHA_MITKUPOLJA, 50, SHURUNA_MITKU_POLJA, 30);
		DialogMainPanel.add(l);
		ystriuList.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + 10, 50, SHURUNA_POLJA - 50, 30);
		l.setForeground(Color.ORANGE);
		DialogMainPanel.add(ystriuList);

		l = new JLabel("Кількість населення, тис. чол.:", SwingConstants.RIGHT);
		l.setBounds(LIVA_MEZHA_MITKUPOLJA, 90, SHURUNA_MITKU_POLJA, 30);
		DialogMainPanel.add(l);
		k_naselText.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + 10, 90, SHURUNA_POLJA - 100, 30);
		l.setForeground(Color.ORANGE);
		DialogMainPanel.add(k_naselText);

		l = new JLabel("Площа, тис. кв.км.:", SwingConstants.RIGHT);
		l.setBounds(LIVA_MEZHA_MITKUPOLJA, 130, SHURUNA_MITKU_POLJA, 30);
		DialogMainPanel.add(l);
		ploshchaText.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + 10, 130, SHURUNA_POLJA - 100, 30);
		l.setForeground(Color.ORANGE);
		DialogMainPanel.add(ploshchaText);

		l = new JLabel("Опис:", SwingConstants.RIGHT);
		l.setBounds(LIVA_MEZHA_MITKUPOLJA, 170, SHURUNA_MITKU_POLJA, 30);
		DialogMainPanel.add(l);
		opusText.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + 10, 170, SHURUNA_POLJA + 160, 30);
		l.setForeground(Color.ORANGE);
		DialogMainPanel.add(opusText);

		l = new JLabel("Рівень розвитку:", SwingConstants.RIGHT);
		l.setBounds(LIVA_MEZHA_MITKUPOLJA, 210, SHURUNA_MITKU_POLJA, 30);
		DialogMainPanel.add(l);
		rozvutokList.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + 10, 210, SHURUNA_POLJA, 30);
		l.setForeground(Color.ORANGE);
		DialogMainPanel.add(rozvutokList);

		l = new JLabel("Розмір ВВП, млн.$:", SwingConstants.RIGHT);
		l.setBounds(LIVA_MEZHA_MITKUPOLJA, 250, SHURUNA_MITKU_POLJA, 30);
		DialogMainPanel.add(l);
		vvpText.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + 10, 250, SHURUNA_POLJA - 100, 30);
		l.setForeground(Color.ORANGE);
		DialogMainPanel.add(vvpText);

		ImageIcon icon1 = Main.createIcon("/img/OK.png");
		JButton btnOk = new JButton("Так", icon1);
		btnOk.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + SHURUNA_POLJA + 10 + 30, 10, 120, 45);
		DialogMainPanel.add(btnOk);

		ImageIcon icon2 = Main.createIcon("/img/Cancel.png");
		JButton btnCancel = new JButton("Відміна", icon2);
		btnCancel.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + SHURUNA_POLJA + 10 + 30, 70, 120, 45);
		DialogMainPanel.add(btnCancel);

		Container c = getContentPane();
		c.add(DialogMainPanel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveKrainu();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelSave();
			}
		});
	}

	public Krainu getKrainu() {
		return krainu;
	}

	public void setKrainu(Krainu krainu) {
		this.krainu = krainu;
		nazvaText.setText(krainu.getNazva());

		if (krainu.getYstriu() == Krainu.MONARH) {
			ystriuList.setSelectedItem(MONARH_);
		} else if (krainu.getYstriu() == Krainu.RESPYBLIKA) {
			ystriuList.setSelectedItem(RESPYBLIKA_);
		} else if (krainu.getYstriu() == Krainu.FEDERACIJA) {
			ystriuList.setSelectedItem(FEDERACIJA_);
		} else if (krainu.getYstriu() == Krainu.INSHE) {
			ystriuList.setSelectedItem(INSHE_);
		}

		k_naselText.setText(Integer.toString(krainu.getK_nasel()));
		ploshchaText.setText(Float.toString(krainu.getPloshcha()));
		opusText.setText(krainu.getOpus());

		if (krainu.getRozvutok() == Krainu.ROZVUNYTA) {
			rozvutokList.setSelectedItem(ROZVUNYTA_);
		} else if (krainu.getRozvutok() == Krainu.ROZVUVAETSJA) {
			rozvutokList.setSelectedItem(ROZVUVAETSJA_);
		} else if (krainu.getRozvutok() == Krainu.TRETIU_SVIT) {
			rozvutokList.setSelectedItem(TRETIU_SVIT_);
		}

		vvpText.setText(Float.toString(krainu.getVvp()));
	}

	private void saveKrainu() {
		try {
			krainu.setNazva(nazvaText.getText());

			if (ystriuList.getSelectedItem().equals(MONARH_)) {
				krainu.setYstriu(Krainu.MONARH);
			} else if (ystriuList.getSelectedItem().equals(RESPYBLIKA_)) {
				krainu.setYstriu(Krainu.RESPYBLIKA);
			} else if (ystriuList.getSelectedItem().equals(FEDERACIJA_)) {
				krainu.setYstriu(Krainu.FEDERACIJA);
			} else if (ystriuList.getSelectedItem().equals(INSHE_)) {
				krainu.setYstriu(Krainu.INSHE);
			}

			krainu.setK_nasel(Integer.parseInt(k_naselText.getText()));
			krainu.setPloshcha(Float.parseFloat(ploshchaText.getText()));
			krainu.setOpus(opusText.getText());

			if (rozvutokList.getSelectedItem().equals(ROZVUNYTA_)) {
				krainu.setRozvutok(Krainu.ROZVUNYTA);
			} else if (rozvutokList.getSelectedItem().equals(ROZVUVAETSJA_)) {
				krainu.setRozvutok(Krainu.ROZVUVAETSJA);
			} else if (rozvutokList.getSelectedItem().equals(TRETIU_SVIT_)) {
				krainu.setRozvutok(Krainu.TRETIU_SVIT);
			}

			krainu.setVvp(Float.parseFloat(vvpText.getText()));

			if (krainu.getId() == null) {
				int newId = new KrainuDao().ins(krainu);
				krainu.setId(newId);
			} else {
				new KrainuDao().upd(krainu);
			}
			this.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Помилка при збереженні країни:",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cancelSave() {
		this.setVisible(false);
	}

}
