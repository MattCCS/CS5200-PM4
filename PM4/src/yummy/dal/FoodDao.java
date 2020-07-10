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

 // create statement 
    
    public Food create(Food food) throws SQLException {
        String insertForm = "INSERT INTO Food(id,name,categoryId) VALUES(?,?,?);";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

        statement.setInt(1, food.getId());
        statement.setString(2, food.getName());
        statement.setInt(3, food.getCategoryId());
        
        
        GenericDao.genericCreate(connectionManager, statement);

        return food;
    }
// read statement of getByid
    
    public Food getByid(int id) throws SQLException {
        String selectForm = "SELECT id,name,categoryId FROM Food WHERE id=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        selectStmt.setInt(1, id);

        return GenericDao.genericGet(connectionManager, selectStmt, FoodDao::converter);
    }

//update statement
    
    public Food updatename(Food food, String newname) throws SQLException {
        String updateForm = "UPDATE Food SET name=? WHERE id=?;";

        PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
        updateStmt.setString(1, newname); 
        updateStmt.setInt(2, food.getId());
        updateStmt.setInt(3, food.getCategoryId());


        GenericDao.genericUpdate(connectionManager, updateStmt);

        // Update before returning to the caller.
        food.setName(newname);
        return food;
    }

//delete statement
    
    public Food delete(Food food) throws SQLException {
        String deleteForm = "DELETE FROM Food WHERE id=?;";

        PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
        deleteStmt.setInt(1, food.getId());

        GenericDao.genericDelete(connectionManager, deleteStmt);

        return null;
    }

}
