package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.MaterukDao;
import domain.Materuk;

public class MaterukDialogForm extends JDialog {
	private final static int LIVA_MEZHA_MITKUPOLJA = 10;
	private final static int SHURUNA_MITKU_POLJA = 160;
	private final static int SHURUNA_POLJA = 200;

	private static final long serialVersionUID = -7265530307974489903L;

	private Materuk materuk;

	private JTextField nazvaText = new JTextField();
	private JTextField ploshchaText = new JTextField();
	private JTextField k_naselText = new JTextField();
	private JTextField k_krainText = new JTextField();
	private JTextField opusText = new JTextField();

	public MaterukDialogForm() {

		setTitle("Новий запис");
		setSize(590, 250);
		setModal(true);
		setResizable(false);
		setLocationByPlatform(true);

		JPanel DialogMainPanel = new JPanel();
		DialogMainPanel.setBackground(Color.DARK_GRAY);
		DialogMainPanel.setLayout(null);

		JLabel l = new JLabel("Назва материка:", SwingConstants.RIGHT);
		l.setBounds(LIVA_MEZHA_MITKUPOLJA, 10, SHURUNA_MITKU_POLJA, 30);
		DialogMainPanel.add(l);
		nazvaText.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + 10, 10, SHURUNA_POLJA, 30);
		l.setForeground(Color.ORANGE);
		DialogMainPanel.add(nazvaText);

		l = new JLabel("Загальна площа, млн. кв.км:", SwingConstants.RIGHT);
		l.setBounds(LIVA_MEZHA_MITKUPOLJA, 50, SHURUNA_MITKU_POLJA, 30);
		DialogMainPanel.add(l);
		ploshchaText.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + 10, 50, SHURUNA_POLJA - 100, 30);
		l.setForeground(Color.ORANGE);
		DialogMainPanel.add(ploshchaText);

		l = new JLabel("К-сть населення, млн. чол.:", SwingConstants.RIGHT);
		l.setBounds(LIVA_MEZHA_MITKUPOLJA, 90, SHURUNA_MITKU_POLJA, 30);
		DialogMainPanel.add(l);
		k_naselText.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + 10, 90, SHURUNA_POLJA - 100, 30);
		l.setForeground(Color.ORANGE);
		DialogMainPanel.add(k_naselText);

		l = new JLabel("К-сть країн:", SwingConstants.RIGHT);
		l.setBounds(LIVA_MEZHA_MITKUPOLJA, 130, SHURUNA_MITKU_POLJA, 30);
		DialogMainPanel.add(l);
		k_krainText.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + 10, 130, SHURUNA_POLJA - 100, 30);
		l.setForeground(Color.ORANGE);
		DialogMainPanel.add(k_krainText);

		l = new JLabel("Опис:", SwingConstants.RIGHT);
		l.setBounds(LIVA_MEZHA_MITKUPOLJA, 170, SHURUNA_MITKU_POLJA, 30);
		DialogMainPanel.add(l);
		opusText.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + 10, 170, SHURUNA_POLJA + 180, 30);
		l.setForeground(Color.ORANGE);
		DialogMainPanel.add(opusText);

		ImageIcon icon1 = Main.createIcon("/img/OK.png");
		JButton btnOk = new JButton("Так", icon1);
		btnOk.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + SHURUNA_POLJA + 10 + 40, 10, 120, 45);

		DialogMainPanel.add(btnOk);
		ImageIcon icon2 = Main.createIcon("/img/Cancel.png");
		JButton btnCancel = new JButton("Відміна", icon2);
		btnCancel.setBounds(LIVA_MEZHA_MITKUPOLJA + SHURUNA_MITKU_POLJA + SHURUNA_POLJA + 10 + 40, 60, 120, 45);
		DialogMainPanel.add(btnCancel);

		Container c = getContentPane();
		c.add(DialogMainPanel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveMateruk();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelSave();
			}
		});
	}

	public Materuk getMateruk() {
		return materuk;
	}

	public void setMateruk(Materuk materuk) {
		this.materuk = materuk;
		nazvaText.setText(materuk.getNazva());
		ploshchaText.setText(Float.toString(materuk.getPloshcha()));
		k_naselText.setText(Integer.toString(materuk.getK_nasel()));
		k_krainText.setText(Integer.toString(materuk.getK_krain()));
		opusText.setText(materuk.getOpus());

	}

	private void saveMateruk() {
		try {
			materuk.setNazva(nazvaText.getText());
			materuk.setPloshcha(Float.parseFloat(ploshchaText.getText()));
			materuk.setK_nasel(Integer.parseInt(k_naselText.getText()));
			materuk.setK_krain(Integer.parseInt(k_krainText.getText()));
			materuk.setOpus(opusText.getText());

			if (materuk.getId() == null) {

				int newId = new MaterukDao().ins(materuk);
				materuk.setId(newId);
			} else {
				new MaterukDao().upd(materuk);
			}
			this.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Помилка при збереженні інформації про материк:",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cancelSave() {
		this.setVisible(false);
	}

}
