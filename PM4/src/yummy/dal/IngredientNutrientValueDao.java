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


public class IngredientNutrientValueDao {
    protected ConnectionManager connectionManager;

    private static IngredientNutrientValueDao instance = null;
    protected IngredientNutrientValueDao() {
        connectionManager = new ConnectionManager();
    }
    public static IngredientNutrientValueDao getInstance() {
        if (instance == null) {
            instance = new IngredientNutrientValueDao();
        }
        return instance;
    }

    public static IngredientNutrientValue converter(ResultSet results) {
        if (results == null) return null;
        try {
            return new IngredientNutrientValue(
                results.getInt(1),
                results.getInt(2),
                results.getInt(3),
                results.getInt(4),
                results.getString(5)
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
//create statement
    public IngredientNutrientValue create(IngredientNutrientValue ingredientNutrientValue) throws SQLException {
        String insertForm = "INSERT INTO IngredientNutrientValue(id,ingredientId, nutrientCodeId,nutrientValue,nutrientValueUnit) VALUES(?,?,?,?,?);";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

        statement.setInt(1, ingredientNutrientValue.getId());
        statement.setInt(2, ingredientNutrientValue.getIngredientId());
        statement.setInt(3, ingredientNutrientValue.getNutrientCodeId());
        statement.setInt(4, ingredientNutrientValue.getNutrientValue());
        statement.setString(5, ingredientNutrientValue.getNutrientValueUnit());

        GenericDao.genericCreate(connectionManager, statement);

        return ingredientNutrientValue;
    }

//read getbyid statement
    
    public IngredientNutrientValue getbyid(int id) throws SQLException {
        String selectForm = "SELECT id,ingredientId, nutrientCodeId,nutrientValue,nutrientValueUnit FROM FoodNutrientValue WHERE id=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        selectStmt.setInt(1, id);

        return GenericDao.genericGet(connectionManager, selectStmt, IngredientNutrientValueDao::converter);
    }

//Update nutrientValue statement    
    
    public IngredientNutrientValue updatenutrientValue(IngredientNutrientValue ingredientNutrientValue, int newnutrientValue) throws SQLException {
        String updateForm = "UPDATE Nutrient SET nutrientValue=? WHERE id=?;";
        PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
        updateStmt.setInt(1, newnutrientValue);
        updateStmt.setInt(2, ingredientNutrientValue.getId());
        updateStmt.setInt(3, ingredientNutrientValue.getIngredientId());
        updateStmt.setInt(4, ingredientNutrientValue.getNutrientCodeId());
        updateStmt.setString(5, ingredientNutrientValue.getNutrientValueUnit());
        GenericDao.genericUpdate(connectionManager, updateStmt);

        // Update before returning to the caller.
        ingredientNutrientValue.setNutrientValue(newnutrientValue);
        return ingredientNutrientValue;
    }

//delete statement    
    
    public IngredientNutrientValue delete(IngredientNutrientValue ingredientNutrientValue) throws SQLException {
        String deleteForm = "DELETE FROM IngredientNutrientValue WHERE id=?;";

        PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
        deleteStmt.setInt(1, ingredientNutrientValue.getId());

        GenericDao.genericDelete(connectionManager, deleteStmt);

        return null;
    }

}
