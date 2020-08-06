package yummy.model;

public class RecipeLine {

	protected String id;
	protected Integer amountNumerator;
	protected Integer amountDenominator;
	protected String unitOfMeasurement;
	protected Integer foodId;
	protected Integer recipeId;
	protected String description;

	public RecipeLine(String id, Integer amountNumerator, Integer amountDenominator, String unitOfMeasurement, Integer foodId,
			Integer recipeId, String description) {
		super();
		this.id = id;
		this.amountNumerator = amountNumerator;
		this.amountDenominator = amountDenominator;
		this.unitOfMeasurement = unitOfMeasurement;
		this.foodId = foodId;
		this.recipeId = recipeId;
		this.description = description;
	}

	@Override
	public String toString() {
		return "IngredientLine [id=" + id + ", amountNumerator=" + amountNumerator + ", amountDenominator="
				+ amountDenominator + ", unitOfMeasurement=" + unitOfMeasurement + ", foodId=" + foodId
				+ ", ingredientId=" + recipeId + ", description=" + description + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAmountNumerator() {
		return amountNumerator;
	}

	public void setAmountNumerator(Integer amountNumerator) {
		this.amountNumerator = amountNumerator;
	}

	public Integer getAmountDenominator() {
		return amountDenominator;
	}

	public void setAmountDenominator(Integer amountDenominator) {
		this.amountDenominator = amountDenominator;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

}
