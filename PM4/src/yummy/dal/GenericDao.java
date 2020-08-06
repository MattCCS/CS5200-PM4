package yummy.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.function.Function;

import yummy.model.*;


public class GenericDao {

	public static void setNull(PreparedStatement statement, Integer index) throws SQLException {
		statement.setNull(index, java.sql.Types.NULL);
	}

	public static void setInt(PreparedStatement statement, Integer index, Integer value) throws SQLException {
		if (value == null) {
			setNull(statement, index);
		} else {
			statement.setInt(index, value);
		}
	}

	public static void setString(PreparedStatement statement, Integer index, String value) throws SQLException {
		if (value == null) {
			setNull(statement, index);
		} else {
			statement.setString(index, value);
		}
	}

	public static void genericCreate(ConnectionManager connectionManager, PreparedStatement statement) throws SQLException {
		genericExecute(connectionManager, statement);
	}

	public static <T> T genericGet(ConnectionManager connectionManager, PreparedStatement statement, Function<ResultSet, T> converter) throws SQLException {
		Connection connection = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			results = statement.executeQuery();
			if (results.next()) {
				return converter.apply(results);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (results != null) {
				results.close();
			}
		}
	}

	public static <T> List<T> genericGetAll(ConnectionManager connectionManager, PreparedStatement statement, Function<ResultSet, T> converter) throws SQLException {
		Connection connection = null;
		ResultSet results = null;
		List<T> resultList = new ArrayList<T>();
		try {
			connection = connectionManager.getConnection();
			results = statement.executeQuery();
			while (results.next()) {
				resultList.add(converter.apply(results));
			}
			return resultList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (results != null) {
				results.close();
			}
		}
	}

	public static void genericUpdate(ConnectionManager connectionManager, PreparedStatement statement) throws SQLException {
		genericExecute(connectionManager, statement);
	}
	
	public static void genericDelete(ConnectionManager connectionManager, PreparedStatement statement) throws SQLException {
		genericExecute(connectionManager, statement);
	}

	public static void genericExecute(ConnectionManager connectionManager, PreparedStatement statement) throws SQLException {
		Connection connection = null;
		try {
			connection = connectionManager.getConnection();
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
	}

}
