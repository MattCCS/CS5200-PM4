package yummy.dal;

import java.util.List;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import yummy.model.Category;

public class CustomQueriesDao {
	protected ConnectionManager connectionManager;

	private static CustomQueriesDao instance = null;

	protected CustomQueriesDao() {
		connectionManager = new ConnectionManager();
	}

	public static CustomQueriesDao getInstance() {
		if (instance == null) {
			instance = new CustomQueriesDao();
		}
		return instance;
	}

	public List<List<String>> showIngredients(Integer recipeId) throws SQLException {
		String selectForm = "select\n"
				+ "    concat(rl.amountNumerator, \"/\", rl.amountDenominator) as amount,\n"
				+ "    rl.unitOfMeasurement, f.name, rl.description\n" + "from recipe r\n"
				+ "join recipeline rl on rl.recipeid = r.id\n" + "join food f on rl.foodid = f.id\n"
				+ "where r.id = ?;";

		PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
		GenericDao.setInt(selectStmt, 1, recipeId);
		selectStmt.execute();

		ResultSet resultSet = selectStmt.getResultSet();
		List<List<String>> results = new ArrayList<>();
		while (resultSet.next()) {
			ArrayList<String> row = new ArrayList<>();
			row.add(resultSet.getString(1));
			row.add(resultSet.getString(2));
			row.add(resultSet.getString(3));
			row.add(resultSet.getString(4));
			results.add(row);
		}
		return results;
	}

	public List<List<String>> whatRecipesUseFood(String foodName) throws SQLException {
		String selectForm = "select distinct r.id, r.name\n" + 
				"from food f\n" + 
				"join recipeline rl on f.id = rl.foodid\n" + 
				"join recipe r on rl.recipeid = r.id\n" + 
				"where f.name = ?;";

		PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
		GenericDao.setString(selectStmt, 1, foodName);
		selectStmt.execute();

		ResultSet resultSet = selectStmt.getResultSet();
		List<List<String>> results = new ArrayList<>();
		while (resultSet.next()) {
			ArrayList<String> row = new ArrayList<>();
			row.add(resultSet.getString(1));
			row.add(resultSet.getString(2));
			results.add(row);
		}
		return results;
	}

	public List<List<String>> whatRecipesExcludeCategory(String categoryName) throws SQLException {
		String selectForm = "select distinct r.id, r.name\n" + 
				"from recipe r\n" + 
				"join recipeline rl on rl.recipeid = r.id\n" + 
				"join food f on rl.foodid = f.id\n" + 
				"join category c on f.categoryId = c.id\n" + 
				"where r.id not in (\n" + 
				"    select r.id from recipe r\n" + 
				"    join recipeline rl on rl.recipeid = r.id\n" + 
				"    join food f on rl.foodid = f.id\n" + 
				"    join category c on f.categoryId = c.id\n" + 
				"    where c.name in (?))";

		PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
		GenericDao.setString(selectStmt, 1, categoryName);
		selectStmt.execute();

		ResultSet resultSet = selectStmt.getResultSet();
		List<List<String>> results = new ArrayList<>();
		while (resultSet.next()) {
			ArrayList<String> row = new ArrayList<>();
			row.add(resultSet.getString(1));
			row.add(resultSet.getString(2));
			results.add(row);
		}
		return results;
	}

	public List<List<String>> showScaledIngredientsForRecipe(String recipeName, int scaleUp, int scaleDown) throws SQLException {
		String selectForm = "select r.name,\n" + 
				"            rl.amountNumerator, rl.amountDenominator,\n" + 
				"            rl.unitOfMeasurement, f.name, rl.description\n" + 
				"        from recipe r\n" + 
				"        join recipeline rl on rl.recipeid = r.id\n" + 
				"        join food f on rl.foodId = f.id\n" + 
				"        where r.name = ?";

		PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
		GenericDao.setString(selectStmt, 1, recipeName);
		selectStmt.execute();

		ResultSet resultSet = selectStmt.getResultSet();
		List<List<String>> results = new ArrayList<>();
		while (resultSet.next()) {
			ArrayList<String> row = new ArrayList<>();
			row.add(Integer.toString(resultSet.getInt(2) * scaleUp));
			row.add(Integer.toString(resultSet.getInt(3) * scaleDown));
			row.add(resultSet.getString(4));
			row.add(resultSet.getString(5));
			row.add(resultSet.getString(6));
			results.add(row);
		}
		return results;
	}

	public List<List<String>> whatRecipesUseIngredients(String ingredients) throws SQLException {
		List<String> ingredientsList = List.of(ingredients.split(","));
		String params = "?,".repeat(ingredientsList.size());
		params = params.substring(0, params.length() - 1);

		String selectForm = "select recipeid, r.name from (\n" + 
				"    select recipeid,\n" + 
				"        sum(case when fname in (" + params + ") then 1 else 0 end) as count\n" + 
				"    from (\n" + 
				"        select distinct rl.recipeid, rl.foodid, f.name as fname\n" + 
				"        from recipeline rl\n" + 
				"        join food f on f.id = rl.foodid\n" + 
				"    ) uniqueRecipeIngredients group by recipeid\n" + 
				") include\n" + 
				"join recipe r on r.id = recipeid\n" + 
				"where count >= ?";

		PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
		for (int i = 0; i < ingredientsList.size(); i++) {
			GenericDao.setString(selectStmt, i + 1, ingredientsList.get(i).strip());
		}
		GenericDao.setInt(selectStmt, ingredientsList.size() + 1, ingredientsList.size());
		selectStmt.execute();

		ResultSet resultSet = selectStmt.getResultSet();
		List<List<String>> results = new ArrayList<>();
		while (resultSet.next()) {
			ArrayList<String> row = new ArrayList<>();
			row.add(resultSet.getString(1));
			row.add(resultSet.getString(2));
			results.add(row);
		}
		return results;
	}

	public List<List<String>> showCalories(Integer recipeId) throws SQLException {
		String selectForm = "select r.id as rid, r.name,\n" + 
				"floor(sum(fnv.nutrientValue *\n" + 
				"    (case\n" + 
				"        when rl.unitOfMeasurement in ('g', 'grams') then (rl.amountNumerator / rl.amountDenominator / 100)\n" + 
				"        when rl.unitOfMeasurement in ('C', 'C.', 'cup', 'cup(s)', 'cups') then (rl.amountNumerator / rl.amountDenominator / 0.5)\n" + 
				"        when rl.unitOfMeasurement in ('ounce', 'ounces', 'oz', 'oz.') then (rl.amountNumerator / rl.amountDenominator / 3.5274)\n" + 
				"        when rl.unitOfMeasurement in ('lb', 'lb.', 'pound', 'pounds') then (rl.amountNumerator / rl.amountDenominator / 0.220462)\n" + 
				"        when rl.unitOfMeasurement in ('tablespoon', 'tablespoon(s)', 'tablespoons', 'TBS', 'Tbs.', 'Tbsp', 'tbsp.') then (rl.amountNumerator / rl.amountDenominator / 6.67)\n" + 
				"        when rl.unitOfMeasurement in ('teaspoon', 'teaspoon(s)', 'teaspoons', 'tsp', 'tsp.') then (rl.amountNumerator / rl.amountDenominator / 20)\n" + 
				"        else 1\n" + 
				"    end)\n" + 
				")) as calories,\n" + 
				"fnv.nutrientValueUnit from recipe r\n" + 
				"join recipeline rl on rl.recipeid = r.id\n" + 
				"join food f on rl.foodid = f.id\n" + 
				"join foodnutrientvalue fnv on rl.foodid = fnv.foodid\n" + 
				"join nutrient n on fnv.nutrientCodeId = n.nutrientCodeId\n" + 
				"where n.nutrientCodeId = 208 and r.id = ?\n" + 
				"group by r.name, fnv.nutrientValueUnit;";

		PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
		GenericDao.setInt(selectStmt, 1, recipeId);
		selectStmt.execute();

		ResultSet resultSet = selectStmt.getResultSet();
		List<List<String>> results = new ArrayList<>();
		while (resultSet.next()) {
			ArrayList<String> row = new ArrayList<>();
			row.add(resultSet.getString(1));
			row.add(resultSet.getString(2));
			row.add(resultSet.getString(3));
			row.add(resultSet.getString(4));
			results.add(row);
		}
		return results;
	}

	public List<List<String>> showAllergens(Integer recipeId) throws SQLException {
		String selectForm = "select rid,\n" + 
				"    rname,\n" + 
				"    sum(case when fg = 'Milk' then 1 else 0 end) as 'Milk',\n" + 
				"    sum(case when fg = 'Fish' then 1 else 0 end) as 'Fish',\n" + 
				"    sum(case when fg = 'Shellfish' then 1 else 0 end) as 'Shellfish',\n" + 
				"    sum(case when fg = 'Egg' then 1 else 0 end) as 'Egg',\n" + 
				"    sum(case when fg = 'Nuts' then 1 else 0 end) as 'Nuts',\n" + 
				"    sum(case when fg = 'Soy' then 1 else 0 end) as 'Soy',\n" + 
				"    sum(case when fg = 'Wheat' then 1 else 0 end) as 'Wheat'\n" + 
				"from (\n" + 
				"    select r.id as rid, r.name as rname, f.name, c.name as cname, c.id as cid,\n" + 
				"        (case\n" + 
				"            when c.id < 2000 then 'Milk'\n" + 
				"            when c.id in (2402, 3006) then 'Fish'\n" + 
				"            when c.id = 2404 then 'Shellfish'\n" + 
				"            when c.id = 2502 then 'Egg'\n" + 
				"            when c.id = 2804 then 'Nuts'\n" + 
				"            when c.id in (2806, 3404, 8404) then 'Soy'\n" + 
				"            when c.id in (3204, 3206, 3208, 3602) then 'Wheat'\n" + 
				"            when (c.id >= 4000 and c.id <= 4604) then 'Wheat'\n" + 
				"            when c.id = 5802 then 'Milk'\n" + 
				"            when (c.id >= 8000 and c.id <= 8009) then 'Milk'\n" + 
				"            else NULL\n" + 
				"        end) as fg\n" + 
				"    from recipe r\n" + 
				"    join recipeline rl on rl.recipeid = r.id\n" + 
				"    join food f on rl.foodid = f.id\n" + 
				"    left join category c on f.categoryId = c.id\n" + 
				"    where r.id = ?\n" + 
				") recipeAllergens group by rid;";

		PreparedStatement selectStmt = connectionManager.getConnection().prepareStatement(selectForm);
		GenericDao.setInt(selectStmt, 1, recipeId);
		selectStmt.execute();

		ResultSet resultSet = selectStmt.getResultSet();
		List<List<String>> results = new ArrayList<>();
		while (resultSet.next()) {
			ArrayList<String> row = new ArrayList<>();
			row.add(resultSet.getString(1));
			row.add(resultSet.getString(2));
			row.add(resultSet.getString(3));
			row.add(resultSet.getString(4));
			row.add(resultSet.getString(5));
			row.add(resultSet.getString(6));
			row.add(resultSet.getString(7));
			row.add(resultSet.getString(8));
			row.add(resultSet.getString(9));
			results.add(row);
		}
		return results;
	}

}
