package restaurant.tools;

import restaurant.dal.*;
import restaurant.model.*;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;


public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		RecipeDao recipeDao = RecipeDao.getInstance();

		// INSERT.
		Recipe newRecipe1 = recipeDao.create(new Recipe(10000001, "Recipe 1"));
		Recipe newRecipe2 = recipeDao.create(new Recipe(10000002, "Recipe 2"));

		// UPDATE.
		recipeDao.updateName(newRecipe2, "Recipe 2a");

		// READ.
		Recipe recipe = recipeDao.getByName("Recipe 1");
		System.out.format("Reading recipe: %s \n", recipe.toString());
		// for	(Recipe recipe1 : recipeDao.getRecipesByUserName("mcotton")) {
		// 	System.out.format("  Listing recipes: %s \n", recipe1.toString());
		// }

		// DELETE.
		recipeDao.delete(recipeDao.getByName("Recipe 2a"));
		recipeDao.delete(recipeDao.getByName("Recipe 1"));
	}
}
