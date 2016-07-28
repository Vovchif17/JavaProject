package domain;

import java.io.Serializable;

/**
 * Клас, що представляє інформацію про Материк. Містить набір полів. Кожне поле
 * представляє собою відповідний стовпчик таблиці Материк.
 */
public class Materuk extends DomainType implements Serializable {

	private static final long serialVersionUID = -1400278242161089165L;
	// поле що містить первинний ключ
	private String nazva;
	private float ploshcha;
	private int k_nasel;
	private int k_krain;
	private String opus;

	public String getNazva() {
		return nazva;
	}

	public void setNazva(String nazva) {
		this.nazva = nazva;
	}

	public float getPloshcha() {
		return ploshcha;
	}

	public void setPloshcha(float ploshcha) {
		this.ploshcha = ploshcha;
	}

	public int getK_nasel() {
		return k_nasel;
	}

	public void setK_nasel(int k_nasel) {
		this.k_nasel = k_nasel;
	}

	public int getK_krain() {
		return k_krain;
	}

	public void setK_krain(int k_krain) {
		this.k_krain = k_krain;
	}

	public String getOpus() {
		return opus;
	}

	public void setOpus(String opus) {
		this.opus = opus;
	}

}
