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


public class RecipeDao {
	protected ConnectionManager connectionManager;

	private static RecipeDao instance = null;
	protected RecipeDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecipeDao getInstance() {
		if (instance == null) {
			instance = new RecipeDao();
		}
		return instance;
	}

	public static Recipe converter(ResultSet results) {
		if (results == null) return null;
		try {
			return new Recipe(
				results.getInt(1),
				results.getString(2)
			);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Recipe create(Recipe recipe) throws SQLException {
		String insertForm = "INSERT INTO Recipe(id,name) VALUES(?,?);";

		PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

		GenericDao.setInt(statement, 1, recipe.getId());
		GenericDao.setString(statement, 2, recipe.getName());

		GenericDao.genericCreate(connectionManager, statement);

		return recipe;
	}

	public Recipe getByName(String name) throws SQLException {
		String selectForm = "SELECT id,name FROM Recipe WHERE name=?;";

		PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
		GenericDao.setString(selectStmt, 1, name);

		return GenericDao.genericGet(connectionManager, selectStmt, RecipeDao::converter);
	}

	public Recipe updateName(Recipe recipe, String name) throws SQLException {
		String updateForm = "UPDATE Recipe SET name=? WHERE id=?;";

		PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
		GenericDao.setString(updateStmt, 1, name);
		GenericDao.setInt(updateStmt, 2, recipe.getId());

		GenericDao.genericUpdate(connectionManager, updateStmt);

		// Update before returning to the caller.
		recipe.setName(name);
		return recipe;
	}

	public Recipe delete(Recipe recipe) throws SQLException {
		String deleteForm = "DELETE FROM Recipe WHERE id=?;";

		PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
		GenericDao.setInt(deleteStmt, 1, recipe.getId());

		GenericDao.genericDelete(connectionManager, deleteStmt);

		return null;
	}

}
