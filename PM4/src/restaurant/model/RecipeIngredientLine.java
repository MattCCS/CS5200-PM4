package restaurant.model;

public class RecipeIngredientLine {
	
	protected int id;
	protected int recipeId;
	protected String ingredientLineId;

	public RecipeIngredientLine(int id, int recipeId, String ingredientLineId) {
		super();
		this.id = id;
		this.recipeId = recipeId;
		this.ingredientLineId = ingredientLineId;
	}

	@Override
	public String toString() {
		return "RecipeIngredientLine [id=" + id + ", recipeId=" + recipeId + ", ingredientLineId=" + ingredientLineId
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String getIngredientLineId() {
		return ingredientLineId;
	}

	public void setIngredientLineId(String ingredientLineId) {
		this.ingredientLineId = ingredientLineId;
	}

}
