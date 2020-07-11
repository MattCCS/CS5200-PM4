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


public class FoodNutrientValueDao {
    protected ConnectionManager connectionManager;

    private static FoodNutrientValueDao instance = null;
    protected FoodNutrientValueDao() {
        connectionManager = new ConnectionManager();
    }
    public static FoodNutrientValueDao getInstance() {
        if (instance == null) {
            instance = new FoodNutrientValueDao();
        }
        return instance;
    }

    public static FoodNutrientValue converter(ResultSet results) {
        if (results == null) return null;
        try {
            return new FoodNutrientValue(
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

    public FoodNutrientValue create(FoodNutrientValue foodNutrientValue) throws SQLException {
        String insertForm = "INSERT INTO FoodNutrientValue(id,foodId,nutrientCodeId,nutrientValue,nutrientValueUnit) VALUES(?,?,?,?,?);";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

        statement.setInt(1, foodNutrientValue.getId());
        statement.setInt(2, foodNutrientValue.getFoodId());
        statement.setInt(3, foodNutrientValue.getNutrientCodeId());
        statement.setInt(4, foodNutrientValue.getNutrientValue());
        statement.setString(5, foodNutrientValue.getNutrientValueUnit());

        GenericDao.genericCreate(connectionManager, statement);

        return foodNutrientValue;
    }

    public FoodNutrientValue getById(int id) throws SQLException {
        String selectForm = "SELECT id,foodId,nutrientCodeId,nutrientValue,nutrientValueUnit FROM FoodNutrientValue WHERE id=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        selectStmt.setInt(1, id);

        return GenericDao.genericGet(connectionManager, selectStmt, FoodNutrientValueDao::converter);
    }

    public FoodNutrientValue updateNutrientValue(FoodNutrientValue foodNutrientValue, int newNutrientValue) throws SQLException {
        String updateForm = "UPDATE Nutrient SET nutrientValue=? WHERE id=?;";

        PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);

        updateStmt.setInt(1, newNutrientValue);
        updateStmt.setInt(2, foodNutrientValue.getId());

        GenericDao.genericUpdate(connectionManager, updateStmt);

        // Update before returning to the caller.
        foodNutrientValue.setNutrientValue(newNutrientValue);
        return foodNutrientValue;
    }

    public FoodNutrientValue delete(FoodNutrientValue foodNutrientValue) throws SQLException {
        String deleteForm = "DELETE FROM FoodNutrientValue WHERE id=?;";

        PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
        deleteStmt.setInt(1, foodNutrientValue.getId());

        GenericDao.genericDelete(connectionManager, deleteStmt);

        return null;
    }

}
