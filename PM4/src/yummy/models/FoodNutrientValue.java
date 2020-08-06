package yummy.model;

public class FoodNutrientValue {

	protected Integer id;
	protected Integer foodId;
	protected Integer nutrientCodeId;
	protected Integer nutrientValue;
	protected String nutrientValueUnit;

	public FoodNutrientValue(Integer id, Integer foodId, Integer nutrientCodeId, Integer nutrientValue, String nutrientValueUnit) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public Integer getNutrientCodeId() {
		return nutrientCodeId;
	}

	public void setNutrientCodeId(Integer nutrientCodeId) {
		this.nutrientCodeId = nutrientCodeId;
	}

	public Integer getNutrientValue() {
		return nutrientValue;
	}

	public void setNutrientValue(Integer nutrientValue) {
		this.nutrientValue = nutrientValue;
	}

	public String getNutrientValueUnit() {
		return nutrientValueUnit;
	}

	public void setNutrientValueUnit(String nutrientValueUnit) {
		this.nutrientValueUnit = nutrientValueUnit;
	}

}
