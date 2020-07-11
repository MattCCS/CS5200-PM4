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

    public IngredientLine create(IngredientLine ingredientLine) throws SQLException {
        String insertForm = "INSERT INTO IngredientLine(id,amountNumerator,amountDenominator,unitOfMeasurement,foodId,ingredientId,description) VALUES(?,?,?,?,?,?,?);";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

        statement.setString(1, ingredientLine.getId());
        statement.setInt(2, ingredientLine.getAmountNumerator());
        statement.setInt(3, ingredientLine.getAmountDenominator());
        statement.setString(4, ingredientLine.getUnitOfMeasurement());
        statement.setInt(5, ingredientLine.getFoodId());
        statement.setInt(6, ingredientLine.getIngredientId());
        statement.setString(7, ingredientLine.getDescription());
        
        GenericDao.genericCreate(connectionManager, statement);

        return ingredientLine;
    }

    public IngredientLine getById(String id) throws SQLException {
        String selectForm = "SELECT id,amountNumerator,amountDenominator,unitOfMeasurement,foodId,ingredientId,description FROM IngredientLine WHERE id=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        selectStmt.setString(1, id);

        return GenericDao.genericGet(connectionManager, selectStmt, IngredientLineDao::converter);
    }

    public IngredientLine updateUnitOfMeasurement(IngredientLine ingredientLine, String newUnitOfMeasurement) throws SQLException {
        String updateForm = "UPDATE IngredientLine SET unitOfMeasurement=? WHERE id=?;";

        PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
        updateStmt.setString(1, newUnitOfMeasurement);
        updateStmt.setString(2, ingredientLine.getId());

        GenericDao.genericUpdate(connectionManager, updateStmt);

        // Update before returning to the caller.
        ingredientLine.setUnitOfMeasurement(newUnitOfMeasurement);
        return ingredientLine;
    }

    public IngredientLine delete(IngredientLine ingredientLine) throws SQLException {
        String deleteForm = "DELETE FROM IngredientLine WHERE id=?;";

        PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
        deleteStmt.setString(1, ingredientLine.getId());

        GenericDao.genericDelete(connectionManager, deleteStmt);

        return null;
    }

}
