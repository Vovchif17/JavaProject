package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Materuk;

/**
 * ��� ���� ������ ������ �� ��������� �������� ��� ������ �� ��������
 * ��������. � ������� ����� �� ������� Materuk ������������� ������
 * domain.Materuk. ����������� ���� DataAccessUtil ��� ������� �� ���� �����.
 */
public class MaterukDao {
	/**
	 * �������� ����� ����� � ������� Materuk �������������� ��� �� �����
	 * materuk.
	 * 
	 * @param materuk
	 *            ����, �� ������ ���������� ��� �������, �� ������� ����
	 *            ��������� � ���� �����.
	 * @return ���� ����������� �����.
	 * @throws Exception
	 *             ������� ��� �������� �����.
	 */
	public int ins(Materuk materuk) throws Exception {
		// �������� ���������� �� ���� �����
		Connection connection = DataAccessUtil.createConnection();
		// ϳ����������� ������ SQL ������ �� ���������
		PreparedStatement statement = connection.prepareStatement(
				"insert into materuk " + "(nazva, ploshcha, k_naselennja, k_krain, opus) values (?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);

		try {
			// ������������ ������� ��������� ������
			// � ������ ������� ����� - ������ �������� - �� ������ ? � �����
			// (��������� � 1),
			// � ������ �������� - �� ��������, �� ���� ���������� � ������
			// ������ ?
			statement.setString(1, materuk.getNazva());
			statement.setFloat(2, materuk.getPloshcha());
			statement.setInt(3, materuk.getK_nasel());
			statement.setInt(4, materuk.getK_krain());
			statement.setString(5, materuk.getOpus());

			// �������� ����� �� ���������� ���� �����
			statement.executeUpdate();
			// ��������� ���� ������ �����
			return DataAccessUtil.getNewRowKey(statement);
		} finally {// ��������� ���������� �� ���� ����� � ����-����� �������
			DataAccessUtil.close(connection);
		}
	}

	/**
	 * ���������� �������� ��� ��������� �����. � ������ ������� ���� id �����
	 * materuk ������� ���� �����������.
	 * 
	 * @param materuk
	 *            ���� �� ����� ����������� ��� �������.
	 * @throws Exception
	 *             ������� ��������� ����������.
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
			DataAccessUtil.close(connection); // ��������� ���������� �� ����
												// ����� � ����-����� �������
		}
	}

	/**
	 * ��������� ����� �� ����������� ��� �������. ����������� ����� �� ������
	 * ����� materukId
	 * 
	 * @param materukId
	 *            ���� �����, �� ������� ��������.
	 * @throws Exception
	 *             ������� ��������� ���������.
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
	 * ��������� �� ������� ����������� ��� ������� �� �������� ������. ����
	 * ������� �� ���������, �� ����������� null.
	 */
	public Materuk findById(int materukId) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement("select * " + "from materuk where materukId = ?");

		try {
			statement.setInt(1, materukId);
			ResultSet rs = statement.executeQuery();// executeQuery() �������
													// ������� ��������� �����.
			// ���� � ��� ���� �����, �� ���������� �� �����, ������� �
			// ��������� � ������
			// ������ ����� ����� Materuk
			if (rs.next()) {
				return getMaterukFromRow(rs);
			}
			return null;// ��������� null, ���� ����� �� �������� ������ ��
						// ���������
		} finally {
			DataAccessUtil.close(connection);// ��������� ���������� �� ����
												// ����� � ����-����� �������
		}
	}

	/**
	 * ������� ������ ��� ��������� ��������. ���� ������� �������� ��
	 * ��������, �� ����������� ������ ������.
	 * 
	 * @return ������ ��������� ��������.
	 * @throws Exception
	 *             ������� ��� ������ ��� ��������.
	 */
	public List<Materuk> findAll() throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement("select * from materuk ");

		try {
			ResultSet rs = statement.executeQuery(); // executeQuery() �������
														// ������� ���������
														// �����.
			// ������� ����� ������, ���� ���� ��������� ����� �� �����������
			// ��� ��������
			List<Materuk> result = new ArrayList<Materuk>();
			while (rs.next()) {// ��� ������� ����������� ������� �����...
				// ... ������ ����� ���� Materuk �� ��������� ���������
				// �� �������� �� ������
				result.add(getMaterukFromRow(rs));
			}
			return result; // �������� ������ ��������� ��������
		} finally {
			DataAccessUtil.close(connection);// ��������� ���������� �� ����
												// ����� � ����-����� �������
		}
	}

	/**
	 * ������ ���������� ����� �� ���� �����, �� ������� ���� ����� Materuk
	 * �� ���������� ������.
	 * 
	 * @param rs
	 *            ������� ����� �� ���� �����
	 * @return ����� ���� ����� Materuk �� ������ �����, ���������� �
	 *         ��������� ������.
	 * @throws Exception
	 *             ������� ���������� �����.
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
