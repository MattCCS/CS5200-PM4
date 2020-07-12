package yummy.model;

public class RecipeIngredientLine {
	
	protected Integer id;
	protected Integer recipeId;
	protected String ingredientLineId;

	public RecipeIngredientLine(Integer id, Integer recipeId, String ingredientLineId) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public String getIngredientLineId() {
		return ingredientLineId;
	}

	public void setIngredientLineId(String ingredientLineId) {
		this.ingredientLineId = ingredientLineId;
	}

}
