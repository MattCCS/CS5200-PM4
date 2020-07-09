package yummy.model;

public class IngredientLine {

	protected String id;
	protected int amountNumerator;
	protected int amountDenominator;
	protected String unitOfMeasurement;
	protected int foodId;
	protected int ingredientId;
	protected String description;

	public IngredientLine(String id, int amountNumerator, int amountDenominator, String unitOfMeasurement) {
		super();
		this.id = id;
		this.amountNumerator = amountNumerator;
		this.amountDenominator = amountDenominator;
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public IngredientLine(String id, int amountNumerator, int amountDenominator, String unitOfMeasurement, int foodId,
			int ingredientId, String description) {
		super();
		this.id = id;
		this.amountNumerator = amountNumerator;
		this.amountDenominator = amountDenominator;
		this.unitOfMeasurement = unitOfMeasurement;
		this.foodId = foodId;
		this.ingredientId = ingredientId;
		this.description = description;
	}

	@Override
	public String toString() {
		return "IngredientLine [id=" + id + ", amountNumerator=" + amountNumerator + ", amountDenominator="
				+ amountDenominator + ", unitOfMeasurement=" + unitOfMeasurement + ", foodId=" + foodId
				+ ", ingredientId=" + ingredientId + ", description=" + description + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmountNumerator() {
		return amountNumerator;
	}

	public void setAmountNumerator(int amountNumerator) {
		this.amountNumerator = amountNumerator;
	}

	public int getAmountDenominator() {
		return amountDenominator;
	}

	public void setAmountDenominator(int amountDenominator) {
		this.amountDenominator = amountDenominator;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
