package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Materuk;

/**
 * Цей клас містить методи та константи необхідні для роботи із таблицею
 * материків. В програмі рядок із таблиці Materuk представлений класом
 * domain.Materuk. Використовує клас DataAccessUtil для доступу до бази даних.
 */
public class MaterukDao {
	/**
	 * Добавляє новий рядок в таблицю Materuk використовуючи дані із обєкта
	 * materuk.
	 * 
	 * @param materuk
	 *            обєкт, що містить інформацію про материк, що повинна бути
	 *            добавлена в базу даних.
	 * @return ключ добавленого рядка.
	 * @throws Exception
	 *             помилка при добавлені рядків.
	 */
	public int ins(Materuk materuk) throws Exception {
		// Отримуємо підключення до бази даних
		Connection connection = DataAccessUtil.createConnection();
		// Підготовлюємо щаблон SQL запиту на виконання
		PreparedStatement statement = connection.prepareStatement(
				"insert into materuk " + "(nazva, ploshcha, k_naselennja, k_krain, opus) values (?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);

		try {
			// Встановлюємо відповідні параметри запиту
			// В даному випадку число - перший параметр - це індекс ? в запиті
			// (починаючи з 1),
			// а другий параметр - це значення, що буде підставлено в шаблон
			// замість ?
			statement.setString(1, materuk.getNazva());
			statement.setFloat(2, materuk.getPloshcha());
			statement.setInt(3, materuk.getK_nasel());
			statement.setInt(4, materuk.getK_krain());
			statement.setString(5, materuk.getOpus());

			// виконуємо запит на обновлення бази даних
			statement.executeUpdate();
			// повертаємо ключ нового рядка
			return DataAccessUtil.getNewRowKey(statement);
		} finally {// закриваємо підключення до бази даних у будь-якому випадку
			DataAccessUtil.close(connection);
		}
	}

	/**
	 * Обновляємо значення вже існуючого рядка. В даному випадку поле id обєкта
	 * materuk повинно бути встановлене.
	 * 
	 * @param materuk
	 *            обєкт із новою інформацією про материк.
	 * @throws Exception
	 *             помилка виконання обновлення.
	 */
	public void upd(Materuk materuk) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement("update materuk "
				+ "set nazva = ?, ploshcha = ?, k_naselennja = ?, k_krain = ?, opus = ? " + "where materukId = ?");

		try {
			statement.setString(1, materuk.getNazva());
			statement.setFloat(2, materuk.getPloshcha());
			statement.setInt(3, materuk.getK_nasel());
			statement.setInt(4, materuk.getK_krain());
			statement.setString(5, materuk.getOpus());
			statement.setInt(6, materuk.getId());

			statement.executeUpdate();
		} finally {
			DataAccessUtil.close(connection); // закриваємо підключення до бази
												// даних у будь-якому випадку
		}
	}

	/**
	 * Видаляємо рядок із інформацією про материк. Видаляється рядок із ключем
	 * рівним materukId
	 * 
	 * @param materukId
	 *            ключ рядка, що потрібно видалити.
	 * @throws Exception
	 *             помилка виконання видалення.
	 */
	public void del(int materukId) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement("delete from materuk where materukId = ?");

		try {
			statement.setInt(1, materukId);
			statement.executeUpdate();
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	/**
	 * Знаходить та повертає інформаціює про материк із вказаним ключем. Якщо
	 * материк не знайдений, то повертається null.
	 */
	public Materuk findById(int materukId) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement("select * " + "from materuk where materukId = ?");

		try {
			statement.setInt(1, materukId);
			ResultSet rs = statement.executeQuery();// executeQuery() повертає
													// множину знайдених рядків.
			// Якщо є хоч один рядок, то переходимо на нього, зчитуємо і
			// повертаємо у вигляді
			// нового обєкта класу Materuk
			if (rs.next()) {
				return getMaterukFromRow(rs);
			}
			return null;// повертаємо null, якщо рядок із вказаним ключем не
						// знайдений
		} finally {
			DataAccessUtil.close(connection);// закриваємо підключення до бази
												// даних у будь-якому випадку
		}
	}

	/**
	 * Повертає список всіх знайдених материків. Якщо жодного материка не
	 * знайдено, то повертається пустий список.
	 * 
	 * @return список знайдених материків.
	 * @throws Exception
	 *             помилка при пошуку всіх материків.
	 */
	public List<Materuk> findAll() throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement("select * from materuk ");

		try {
			ResultSet rs = statement.executeQuery(); // executeQuery() повертає
														// множину знайдених
														// рядків.
			// Створюю новий список, куди буду добавляти обєкти із інформацією
			// про материки
			List<Materuk> result = new ArrayList<Materuk>();
			while (rs.next()) {// Для кожного поверненого запитом рядка...
				// ... отримує новий обєкт Materuk із відповідним значенням
				// та добавляю до списку
				result.add(getMaterukFromRow(rs));
			}
			return result; // повертаю список знайдених материків
		} finally {
			DataAccessUtil.close(connection);// закриваємо підключення до бази
												// даних у будь-якому випадку
		}
	}

	/**
	 * Виконує зчитування рядка із бази даних, та повертає обєкт класу Materuk
	 * із відповідними даними.
	 * 
	 * @param rs
	 *            множина рядків із бази даних
	 * @return новий обєкт класу Materuk із даними рядка, отриманого в
	 *         результаті запиту.
	 * @throws Exception
	 *             помилка зчитування рядка.
	 */
	private static Materuk getMaterukFromRow(ResultSet rs) throws Exception {
		Materuk materuk = new Materuk();
		materuk.setId(rs.getInt(1));
		materuk.setNazva(rs.getString(2));
		materuk.setPloshcha(rs.getFloat(3));
		materuk.setK_nasel(rs.getInt(4));
		materuk.setK_krain(rs.getInt(5));
		materuk.setOpus(rs.getString(6));

		return materuk;
	}
}
