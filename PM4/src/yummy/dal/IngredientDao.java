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


public class IngredientDao {
    protected ConnectionManager connectionManager;

    private static IngredientDao instance = null;
    protected IngredientDao() {
        connectionManager = new ConnectionManager();
    }
    public static IngredientDao getInstance() {
        if (instance == null) {
            instance = new IngredientDao();
        }
        return instance;
    }

    public static Ingredient converter(ResultSet results) {
        if (results == null) return null;
        try {
            return new Ingredient(
                results.getInt(1),
                results.getString(2)
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Ingredient create(Ingredient ingredient) throws SQLException {
        String insertForm = "INSERT INTO Ingredient(id,name) VALUES(?,?);";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

        GenericDao.setInt(statement, 1, ingredient.getId());
        GenericDao.setString(statement, 2, ingredient.getName());

        GenericDao.genericCreate(connectionManager, statement);

        return ingredient;
    }

    public Ingredient getByName(String name) throws SQLException {
        String selectForm = "SELECT id,name FROM Ingredient WHERE name=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        GenericDao.setString(selectStmt, 1, name);

        return GenericDao.genericGet(connectionManager, selectStmt, IngredientDao::converter);
    }

    public Ingredient updateName(Ingredient ingredient, String name) throws SQLException {
        String updateForm = "UPDATE Ingredient SET name=? WHERE id=?;";

        PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
        GenericDao.setString(updateStmt, 1, name);
        GenericDao.setInt(updateStmt, 2, ingredient.getId());

        GenericDao.genericUpdate(connectionManager, updateStmt);

        // Update before returning to the caller.
        ingredient.setName(name);
        return ingredient;
    }

    public Ingredient delete(Ingredient ingredient) throws SQLException {
        String deleteForm = "DELETE FROM Ingredient WHERE id=?;";

        PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
        
        GenericDao.setInt(deleteStmt, 1, ingredient.getId());

        GenericDao.genericDelete(connectionManager, deleteStmt);

        return null;
    }

}
