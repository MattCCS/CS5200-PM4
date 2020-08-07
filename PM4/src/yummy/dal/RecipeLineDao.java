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


public class RecipeLineDao {
    protected ConnectionManager connectionManager;

    private static RecipeLineDao instance = null;
    protected RecipeLineDao() {
        connectionManager = new ConnectionManager();
    }
    public static RecipeLineDao getInstance() {
        if (instance == null) {
            instance = new RecipeLineDao();
        }
        return instance;
    }

    public static RecipeLine converter(ResultSet results) {
        if (results == null) return null;
        try {
            return new RecipeLine(
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

    public RecipeLine create(RecipeLine recipeLine) throws SQLException {
        String insertForm = "INSERT INTO RecipeLine(id,amountNumerator,amountDenominator,unitOfMeasurement,foodId,recipeId,description) VALUES(?,?,?,?,?,?,?);";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

        GenericDao.setString(statement, 1, recipeLine.getId());
        GenericDao.setInt(statement, 2, recipeLine.getAmountNumerator());
        GenericDao.setInt(statement, 3, recipeLine.getAmountDenominator());
        GenericDao.setString(statement, 4, recipeLine.getUnitOfMeasurement());
        GenericDao.setInt(statement, 5, recipeLine.getFoodId());
        GenericDao.setInt(statement, 6, recipeLine.getRecipeId());
        GenericDao.setString(statement, 7, recipeLine.getDescription());
        
        GenericDao.genericCreate(connectionManager, statement);

        return recipeLine;
    }

    public RecipeLine getById(String id) throws SQLException {
        String selectForm = "SELECT id,amountNumerator,amountDenominator,unitOfMeasurement,foodId,recipeId,description FROM RecipeLine WHERE id=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        
        GenericDao.setString(selectStmt, 1, id);

        return GenericDao.genericGet(connectionManager, selectStmt, RecipeLineDao::converter);
    }

    public RecipeLine updateUnitOfMeasurement(RecipeLine recipeLine, String newUnitOfMeasurement) throws SQLException {
        String updateForm = "UPDATE RecipeLine SET unitOfMeasurement=? WHERE id=?;";

        PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
        GenericDao.setString(updateStmt, 1, newUnitOfMeasurement);
        GenericDao.setString(updateStmt, 2, recipeLine.getId());

        GenericDao.genericUpdate(connectionManager, updateStmt);

        // Update before returning to the caller.
        recipeLine.setUnitOfMeasurement(newUnitOfMeasurement);
        return recipeLine;
    }

    public RecipeLine delete(RecipeLine recipeLine) throws SQLException {
        String deleteForm = "DELETE FROM RecipeLine WHERE id=?;";

        PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
        GenericDao.setString(deleteStmt, 1, recipeLine.getId());

        GenericDao.genericDelete(connectionManager, deleteStmt);

        return null;
    }

}
