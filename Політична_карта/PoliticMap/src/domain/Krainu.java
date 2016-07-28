package domain;

import java.io.Serializable;

public class Krainu extends DomainType implements Serializable {

	private static final long serialVersionUID = -7868315400447626215L;
	public static final int MONARH = 1;
	public static final int RESPYBLIKA = 2;
	public static final int FEDERACIJA = 3;
	public static final int INSHE = 4;

	public static final int ROZVUNYTA = 1;
	public static final int ROZVUVAETSJA = 2;
	public static final int TRETIU_SVIT = 3;

	private String nazva;
	private int ystriu;
	private int k_nasel;
	private float ploshcha;
	private String opus;
	private int rozvutok;
	private float vvp;
	private int materukkrainuId;

	public String getNazva() {
		return nazva;
	}

	public void setNazva(String nazva) {
		this.nazva = nazva;
	}

	public int getYstriu() {
		return ystriu;
	}

	public void setYstriu(int ystriu) {
		this.ystriu = ystriu;
	}

	public int getK_nasel() {
		return k_nasel;
	}

	public void setK_nasel(int k_nasel) {
		this.k_nasel = k_nasel;
	}

	public float getPloshcha() {
		return ploshcha;
	}

	public void setPloshcha(float ploshcha) {
		this.ploshcha = ploshcha;
	}

	public String getOpus() {
		return opus;
	}

	public void setOpus(String opus) {
		this.opus = opus;
	}

	public int getRozvutok() {
		return rozvutok;
	}

	public void setRozvutok(int rozvutok) {
		this.rozvutok = rozvutok;
	}

	public float getVvp() {
		return vvp;
	}

	public void setVvp(float vvp) {
		this.vvp = vvp;
	}

	public int getMaterukkrainuId() {
		return materukkrainuId;
	}

	public void setMaterukkrainuId(int materukkrainuId) {
		this.materukkrainuId = materukkrainuId;
	}

}
