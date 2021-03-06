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


public class NutrientDao {
    protected ConnectionManager connectionManager;

    private static NutrientDao instance = null;
    protected NutrientDao() {
        connectionManager = new ConnectionManager();
    }
    public static NutrientDao getInstance() {
        if (instance == null) {
            instance = new NutrientDao();
        }
        return instance;
    }

    public static Nutrient converter(ResultSet results) {
        if (results == null) return null;
        try {
            return new Nutrient(
                results.getInt(1),
                results.getString(2)
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Nutrient create(Nutrient nutrient) throws SQLException {
        String insertForm = "INSERT INTO Nutrient(nutrientCodeId,name) VALUES(?,?);";

        PreparedStatement statement = connectionManager.getConnection().prepareStatement(insertForm);

        GenericDao.setInt(statement, 1, nutrient.getNutrientCodeId());
        GenericDao.setString(statement, 2, nutrient.getName());

        GenericDao.genericCreate(connectionManager, statement);

        return nutrient;
    }

    public Nutrient getById(Integer id) throws SQLException {
        String selectForm = "SELECT nutrientCodeId,name FROM Nutrient WHERE nutrientCodeId=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        GenericDao.setInt(selectStmt, 1, id);

        return GenericDao.genericGet(connectionManager, selectStmt, NutrientDao::converter);
    }

    public Nutrient getByName(String name) throws SQLException {
        String selectForm = "SELECT nutrientCodeId,name FROM Nutrient WHERE name=?;";

        PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
        GenericDao.setString(selectStmt, 1, name);

        return GenericDao.genericGet(connectionManager, selectStmt, NutrientDao::converter);
    }

    public Nutrient updateName(Nutrient nutrient, String name) throws SQLException {
        String updateForm = "UPDATE Nutrient SET name=? WHERE nutrientCodeId=?;";

        PreparedStatement updateStmt = connectionManager.getConnection().prepareStatement(updateForm);
        GenericDao.setString(updateStmt, 1, name);
        GenericDao.setInt(updateStmt, 2, nutrient.getNutrientCodeId());

        GenericDao.genericUpdate(connectionManager, updateStmt);

        // Update before returning to the caller.
        nutrient.setName(name);
        return nutrient;
    }

    public Nutrient delete(Nutrient nutrient) throws SQLException {
        String deleteForm = "DELETE FROM Nutrient WHERE nutrientCodeId=?;";

        PreparedStatement deleteStmt = connectionManager.getConnection().prepareStatement(deleteForm);
        GenericDao.setInt(deleteStmt, 1, nutrient.getNutrientCodeId());

        GenericDao.genericDelete(connectionManager, deleteStmt);

        return null;
    }

}
