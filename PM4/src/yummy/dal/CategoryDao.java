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


public class CategoryDao {
    protected ConnectionManager connectionManager;

    private static CategoryDao instance = null;
    protected CategoryDao() {
        connectionManager = new ConnectionManager();
    }
    public static CategoryDao getInstance() {
        if (instance == null) {
            instance = new CategoryDao();
        }
        return instance;
    }

    public static Category converter(ResultSet results) {
        if (results == null) return null;
        try {
            return new Category(
                results.getInt(1),
                results.getString(2),
                results.getInt(3)
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Category create(Category category) throws SQLException {
        String insertForm = "INSERT INTO Category(id,name,foodgroupid) VALUES(?,?,?);";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

        GenericDao.setInt(statement, 1, category.getId());
        GenericDao.setString(statement, 2, category.getName());
        GenericDao.setInt(statement, 3, category.getFoodgroupid());
        GenericDao.genericCreate(connectionManager, statement);

        return category;
    }

    public Category getByName(String name) throws SQLException {
        String selectForm = "SELECT id,name,foodgroupid FROM Category WHERE name=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        GenericDao.setString(selectStmt, 1, name);

        return GenericDao.genericGet(connectionManager, selectStmt, CategoryDao::converter);
    }

    public Category updateName(Category category, String name) throws SQLException {
        String updateForm = "UPDATE Category SET name=? WHERE id=?;";

        PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
        GenericDao.setString(updateStmt, 1, name);
        GenericDao.setInt(updateStmt, 2, category.getId());
        
        GenericDao.genericUpdate(connectionManager, updateStmt);

        // Update before returning to the caller.
        category.setName(name);
        return category;
    }

    public Category delete(Category category) throws SQLException {
        String deleteForm = "DELETE FROM Category WHERE id=?;";

        PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
        GenericDao.setInt(deleteStmt, 1, category.getId());

        GenericDao.genericDelete(connectionManager, deleteStmt);

        return null;
    }

}
