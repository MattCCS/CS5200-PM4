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


public class FoodDao {
    protected ConnectionManager connectionManager;

    private static FoodDao instance = null;
    protected FoodDao() {
        connectionManager = new ConnectionManager();
    }
    public static FoodDao getInstance() {
        if (instance == null) {
            instance = new FoodDao();
        }
        return instance;
    }

    public static Food converter(ResultSet results) {
        if (results == null) return null;
        try {
            return new Food(
                results.getInt(1),
                results.getString(2),
                results.getInt(3)
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Food create(Food food) throws SQLException {
        String insertForm = "INSERT INTO Food(id,name,categoryId) VALUES(?,?,?);";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

        GenericDao.setInt(statement, 1, food.getId());
        GenericDao.setString(statement, 2, food.getName());
        GenericDao.setInt(statement, 3, food.getCategoryId());

        GenericDao.genericCreate(connectionManager, statement);

        return food;
    }

    public Food getById(Integer id) throws SQLException {
        String selectForm = "SELECT id,name,categoryId FROM Food WHERE id=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        GenericDao.setInt(selectStmt, 1, id);

        return GenericDao.genericGet(connectionManager, selectStmt, FoodDao::converter);
    }

    public Food getByName(String name) throws SQLException {
        String selectForm = "SELECT id,name,categoryId FROM Food WHERE name=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        GenericDao.setString(selectStmt, 1, name);

        return GenericDao.genericGet(connectionManager, selectStmt, FoodDao::converter);
    }

    public Food updateName(Food food, String newName) throws SQLException {
        String updateForm = "UPDATE Food SET name=? WHERE id=?;";

        PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
        GenericDao.setString(updateStmt, 1, newName); 
        GenericDao.setInt(updateStmt, 2, food.getId());

        GenericDao.genericUpdate(connectionManager, updateStmt);

        // Update before returning to the caller.
        food.setName(newName);
        return food;
    }

    public Food delete(Food food) throws SQLException {
        String deleteForm = "DELETE FROM Food WHERE id=?;";

        PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
        GenericDao.setInt(deleteStmt, 1, food.getId());

        GenericDao.genericDelete(connectionManager, deleteStmt);

        return null;
    }

}
