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


public class FoodIngredientLineDao {
    protected ConnectionManager connectionManager;

    private static FoodIngredientLineDao instance = null;
    protected FoodIngredientLineDao() {
        connectionManager = new ConnectionManager();
    }
    public static FoodIngredientLineDao getInstance() {
        if (instance == null) {
            instance = new FoodIngredientLineDao();
        }
        return instance;
    }

    public static FoodIngredientLine converter(ResultSet results) {
        if (results == null) return null;
        try {
            return new FoodIngredientLine(
                
                results.getInt(1),
                results.getInt(2),
                results.getString(3)
               
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

 // create statement 
    
    public FoodIngredientLine create(FoodIngredientLine foodingredientline) throws SQLException {
        String insertForm = "INSERT INTO IngredientLine(id,foodId,ingredientLineId) VALUES(?,?,?);";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

        statement.setInt(1, foodingredientline.getId());
        statement.setInt(2, foodingredientline.getFoodId());
        statement.setString(3, foodingredientline.getIngredientLineId());
        
        
        GenericDao.genericCreate(connectionManager, statement);

        return foodingredientline;
    }
// read statement of getByid
    
    public FoodIngredientLine getByid(int id) throws SQLException {
        String selectForm = "SELECT id,foodId,ingredientLineId FROM FoodIngredientLine WHERE id=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        selectStmt.setInt(1, id);

        return GenericDao.genericGet(connectionManager, selectStmt, FoodIngredientLineDao::converter);
    }

//update statement
    
    public FoodIngredientLine updatefoodId(FoodIngredientLine foodingredientline, int newfoodId) throws SQLException {
        String updateForm = "UPDATE FoodIngredientLine SET foodId=? WHERE id=?;";

        PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
        updateStmt.setInt(1, newfoodId); 
        updateStmt.setInt(2, foodingredientline.getId());
        updateStmt.setString(3, foodingredientline.getIngredientLineId());


        GenericDao.genericUpdate(connectionManager, updateStmt);

        // Update before returning to the caller.
        foodingredientline.setFoodId(newfoodId);
        return foodingredientline;
    }

//delete statement
    
    public FoodIngredientLine delete(FoodIngredientLine foodingredientline) throws SQLException {
        String deleteForm = "DELETE FROM FoodIngredientLine WHERE id=?;";

        PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
        deleteStmt.setInt(1, foodingredientline.getId());

        GenericDao.genericDelete(connectionManager, deleteStmt);

        return null;
    }

}
