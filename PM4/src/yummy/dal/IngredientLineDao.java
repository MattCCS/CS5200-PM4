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


public class IngredientLineDao {
    protected ConnectionManager connectionManager;

    private static IngredientLineDao instance = null;
    protected IngredientLineDao() {
        connectionManager = new ConnectionManager();
    }
    public static IngredientLineDao getInstance() {
        if (instance == null) {
            instance = new IngredientLineDao();
        }
        return instance;
    }

    public static IngredientLine converter(ResultSet results) {
        if (results == null) return null;
        try {
            return new IngredientLine(
                results.getString(1),
                results.getInt(2),
                results.getInt(3),
                results.getString(4),
                results.getInt(5),
                results.getInt(6),
                results.getString(7)
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

 // create statement 
    
    public IngredientLine create(IngredientLine ingredientline) throws SQLException {
        String insertForm = "INSERT INTO IngredientLine(id,amountNumerator,amountDenominator,unitOfMeasurement,foodId,ingredientId,description) VALUES(?,?,?,?,?,?,?);";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

        statement.setString(1, ingredientline.getId());
        statement.setInt(2, ingredientline.getAmountNumerator());
        statement.setInt(3, ingredientline.getAmountDenominator());
        statement.setString(4, ingredientline.getUnitOfMeasurement());
        statement.setInt(5, ingredientline.getFoodId());
        statement.setInt(6, ingredientline.getIngredientId());
        statement.setString(7, ingredientline.getDescription());
        
        GenericDao.genericCreate(connectionManager, statement);

        return ingredientline;
    }
// read statement of getByid
    
    public IngredientLine getByid(String id) throws SQLException {
        String selectForm = "SELECT id,amountNumerator,amountDenominator,unitOfMeasurement,foodId,ingredientId,description FROM IngredientLine WHERE id=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        selectStmt.setString(1, id);

        return GenericDao.genericGet(connectionManager, selectStmt, IngredientLineDao::converter);
    }

//update statement
    
    public IngredientLine updateUnitOfMeasurement(IngredientLine ingredientline, String newunitOfMeasurement) throws SQLException {
        String updateForm = "UPDATE IngredientLine SET unitOfMeasurement=? WHERE id=?;";

        PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
        updateStmt.setString(1, newunitOfMeasurement);
        updateStmt.setString(2, ingredientline.getId()); 
        updateStmt.setInt(3, ingredientline.getAmountNumerator());
        updateStmt.setInt(4, ingredientline.getAmountDenominator());
        updateStmt.setInt(5, ingredientline.getFoodId());
        updateStmt.setInt(6, ingredientline.getIngredientId());
        updateStmt.setString(7, ingredientline.getDescription());

        GenericDao.genericUpdate(connectionManager, updateStmt);

        // Update before returning to the caller.
        ingredientline.setUnitOfMeasurement(newunitOfMeasurement);
        return ingredientline;
    }

//delete statement
    
    public IngredientLine delete(IngredientLine ingredientline) throws SQLException {
        String deleteForm = "DELETE FROM IngredientLine WHERE id=?;";

        PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
        deleteStmt.setString(1, ingredientline.getId());

        GenericDao.genericDelete(connectionManager, deleteStmt);

        return null;
    }

}
