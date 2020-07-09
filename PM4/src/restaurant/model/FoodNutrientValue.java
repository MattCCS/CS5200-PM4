package restaurant.model;

public class FoodNutrientValue {

	protected int id;
	protected int foodId;
	protected int nutrientCodeId;
	protected int nutrientValue;
	protected String nutrientValueUnit;

	public FoodNutrientValue(int id, int foodId, int nutrientCodeId, int nutrientValue, String nutrientValueUnit) {
		super();
		this.id = id;
		this.foodId = foodId;
		this.nutrientCodeId = nutrientCodeId;
		this.nutrientValue = nutrientValue;
		this.nutrientValueUnit = nutrientValueUnit;
	}

	@Override
	public String toString() {
		return "FoodNutrientValue [id=" + id + ", foodId=" + foodId + ", nutrientCodeId=" + nutrientCodeId
				+ ", nutrientValue=" + nutrientValue + ", nutrientValueUnit=" + nutrientValueUnit + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getNutrientCodeId() {
		return nutrientCodeId;
	}

	public void setNutrientCodeId(int nutrientCodeId) {
		this.nutrientCodeId = nutrientCodeId;
	}

	public int getNutrientValue() {
		return nutrientValue;
	}

	public void setNutrientValue(int nutrientValue) {
		this.nutrientValue = nutrientValue;
	}

	public String getNutrientValueUnit() {
		return nutrientValueUnit;
	}

	public void setNutrientValueUnit(String nutrientValueUnit) {
		this.nutrientValueUnit = nutrientValueUnit;
	}

}
