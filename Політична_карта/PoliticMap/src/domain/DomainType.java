package domain;

import java.io.Serializable;

public abstract class DomainType implements Serializable {

	private static final long serialVersionUID = -7873357187538188983L;

	protected Integer id;

	/**
	 * ќтримати значенн€ пол€ id.
	 * 
	 * @return отримати значенн€ пол€.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * ¬становити значенн€ пол€ id.
	 * 
	 * @param id
	 *            нове значенн€ дл€ пол€ id.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
