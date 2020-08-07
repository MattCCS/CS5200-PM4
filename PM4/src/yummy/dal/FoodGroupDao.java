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


public class FoodGroupDao {
	protected ConnectionManager connectionManager;

	private static FoodGroupDao instance = null;
	protected FoodGroupDao() {
		connectionManager = new ConnectionManager();
	}
	public static FoodGroupDao getInstance() {
		if (instance == null) {
			instance = new FoodGroupDao();
		}
		return instance;
	}

	public static FoodGroup converter(ResultSet results) {
		if (results == null) return null;
		try {
			return new FoodGroup(
				results.getInt(1),
				results.getString(2)
			);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public FoodGroup create(FoodGroup foodgroup) throws SQLException {
		String insertForm = "INSERT INTO FoodGroup(id,name) VALUES(?,?);";

		PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

		GenericDao.setInt(statement, 1, foodgroup.getId());
		GenericDao.setString(statement, 2, foodgroup.getName());

		GenericDao.genericCreate(connectionManager, statement);

		return foodgroup;
	}

	public FoodGroup getById(Integer id) throws SQLException {
		String selectForm = "SELECT id,name FROM FoodGroup WHERE id=?;";

		PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
		GenericDao.setInt(selectStmt, 1, id);

		return GenericDao.genericGet(connectionManager, selectStmt, FoodGroupDao::converter);
	}

	public FoodGroup getByName(String name) throws SQLException {
		String selectForm = "SELECT id,name FROM FoodGroup WHERE name=?;";

		PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
		GenericDao.setString(selectStmt, 1, name);

		return GenericDao.genericGet(connectionManager, selectStmt, FoodGroupDao::converter);
	}

	public FoodGroup updateName(FoodGroup foodgroup, String name) throws SQLException {
		String updateForm = "UPDATE FoodGroup SET name=? WHERE id=?;";

		PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
		GenericDao.setString(updateStmt, 1, name);
		GenericDao.setInt(updateStmt, 2, foodgroup.getId());

		GenericDao.genericUpdate(connectionManager, updateStmt);

		// Update before returning to the caller.
		foodgroup.setName(name);
		return foodgroup;
	}

	public FoodGroup delete(FoodGroup foodgroup) throws SQLException {
		String deleteForm = "DELETE FROM FoodGroup WHERE id=?;";

		PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
		GenericDao.setInt(deleteStmt, 1, foodgroup.getId());

		GenericDao.genericDelete(connectionManager, deleteStmt);

		return null;
	}

}
