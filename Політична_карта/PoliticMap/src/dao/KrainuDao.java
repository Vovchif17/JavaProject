package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Krainu;

public class KrainuDao {

	public int ins(Krainu krainu) throws Exception {

		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement(
				"insert into krainu (nazva, ystriu, k_nasel, ploshcha, opus, rozvutok, vvp, materukkrainuId ) "
						+ "values (?, ?, ?, ?, ?, ?, ?,?)",
				Statement.RETURN_GENERATED_KEYS);

		try {
			statement.setString(1, krainu.getNazva());
			statement.setInt(2, krainu.getYstriu());
			statement.setInt(3, krainu.getK_nasel());
			statement.setFloat(4, krainu.getPloshcha());
			statement.setString(5, krainu.getOpus());
			statement.setInt(6, krainu.getRozvutok());
			statement.setFloat(7, krainu.getVvp());
			statement.setInt(8, krainu.getMaterukkrainuId());

			statement.executeUpdate();

			return DataAccessUtil.getNewRowKey(statement);
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	public void upd(Krainu krainu) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement("update krainu "
				+ "set nazva = ?, ystriu = ?, k_nasel = ?, ploshcha = ?, opus = ?, rozvutok = ?, vvp = ?, materukkrainuId = ? "
				+ "where krainuId = ?");

		try {
			statement.setString(1, krainu.getNazva());
			statement.setInt(2, krainu.getYstriu());
			statement.setInt(3, krainu.getK_nasel());
			statement.setFloat(4, krainu.getPloshcha());
			statement.setString(5, krainu.getOpus());
			statement.setInt(6, krainu.getRozvutok());
			statement.setFloat(7, krainu.getVvp());
			statement.setInt(8, krainu.getMaterukkrainuId());
			statement.setInt(9, krainu.getId());

			statement.executeUpdate();
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	public void del(int krainuId) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement("delete from krainu where krainuId = ?");

		try {
			statement.setInt(1, krainuId);
			statement.executeUpdate();
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	public Krainu findById(int krainuId) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection.prepareStatement("select * " + "from from krainu where krainuId = ?");

		try {
			statement.setInt(1, krainuId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return getKrainuFromRow(rs);
			}
			return null;
		} finally {
			DataAccessUtil.close(connection);
		}
	}

public List<Krainu> findByKrainu(int boxId) throws Exception {
		Connection connection = DataAccessUtil.createConnection();
		PreparedStatement statement = connection
				.prepareStatement("select * " + "from krainu where materukkrainuId = ?");

		try {
			statement.setInt(1, boxId);
			ResultSet rs = statement.executeQuery();
			List<Krainu> result = new ArrayList<Krainu>();
			while (rs.next()) {
				result.add(getKrainuFromRow(rs));
			}
			return result;
		} finally {
			DataAccessUtil.close(connection);
		}
	}

	private Krainu getKrainuFromRow(ResultSet rs) throws Exception {
		Krainu krainu = new Krainu();
		krainu.setId(rs.getInt(1));
		krainu.setNazva(rs.getString(2));
		krainu.setYstriu(rs.getInt(3));
		krainu.setK_nasel(rs.getInt(4));
		krainu.setPloshcha(rs.getFloat(5));
		krainu.setOpus(rs.getString(6));
		krainu.setRozvutok(rs.getInt(7));
		krainu.setVvp(rs.getFloat(8));
		krainu.setMaterukkrainuId(rs.getInt(9));
		return krainu;
	}

}
