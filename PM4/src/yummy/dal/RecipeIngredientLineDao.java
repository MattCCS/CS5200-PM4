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


public class RecipeIngredientLineDao {
    protected ConnectionManager connectionManager;

    private static RecipeIngredientLineDao instance = null;
    protected RecipeIngredientLineDao() {
        connectionManager = new ConnectionManager();
    }
    public static RecipeIngredientLineDao getInstance() {
        if (instance == null) {
            instance = new RecipeIngredientLineDao();
        }
        return instance;
    }

    public static RecipeIngredientLine converter(ResultSet results) {
        if (results == null) return null;
        try {
            return new RecipeIngredientLine(
                results.getInt(1),
                results.getInt(2),
                results.getString(3)
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public RecipeIngredientLine create(RecipeIngredientLine recipeIngredientLine) throws SQLException {
        String insertForm = "INSERT INTO IngredientLine(id,recipeId,ingredientLineId) VALUES(?,?,?);";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

        statement.setInt(1, recipeIngredientLine.getId());
        statement.setInt(2, recipeIngredientLine.getRecipeId());
        statement.setString(3, recipeIngredientLine.getIngredientLineId());

        GenericDao.genericCreate(connectionManager, statement);

        return recipeIngredientLine;
    }

    public RecipeIngredientLine getById(int id) throws SQLException {
        String selectForm = "SELECT id,recipeId,ingredientLineId FROM RecipeIngredientLine WHERE id=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        selectStmt.setInt(1, id);

        return GenericDao.genericGet(connectionManager, selectStmt, RecipeIngredientLineDao::converter);
    }

    public RecipeIngredientLine updateRecipeId(RecipeIngredientLine recipeIngredientLine, int newRecipeId) throws SQLException {
        String updateForm = "UPDATE RecipeIngredientLine SET recipeId=? WHERE id=?;";

        PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
        updateStmt.setInt(1, newRecipeId); 
        updateStmt.setInt(2, recipeIngredientLine.getId());

        GenericDao.genericUpdate(connectionManager, updateStmt);

        // Update before returning to the caller.
        recipeIngredientLine.setRecipeId(newRecipeId);
        return recipeIngredientLine;
    }

    public RecipeIngredientLine delete(RecipeIngredientLine recipeIngredientLine) throws SQLException {
        String deleteForm = "DELETE FROM RecipeIngredientLine WHERE id=?;";

        PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
        deleteStmt.setInt(1, recipeIngredientLine.getId());

        GenericDao.genericDelete(connectionManager, deleteStmt);

        return null;
    }

}
