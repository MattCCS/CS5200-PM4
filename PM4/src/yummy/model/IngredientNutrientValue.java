package yummy.model;

public class IngredientNutrientValue {

    protected Integer id;
    protected Integer ingredientId;
    protected Integer nutrientCodeId;
    protected Integer nutrientValue;
    protected String nutrientValueUnit;

    public IngredientNutrientValue(Integer id, Integer ingredientId, Integer nutrientCodeId, Integer nutrientValue, String nutrientValueUnit) {
        super();
        this.id = id;
        this.ingredientId = ingredientId;
        this.nutrientCodeId = nutrientCodeId;
        this.nutrientValue = nutrientValue;
        this.nutrientValueUnit = nutrientValueUnit;
    }

    @Override
    public String toString() {
        return "IngredientNutrientValue [id=" + id + ", ingredientId=" + ingredientId + ", nutrientCodeId=" + nutrientCodeId
                + ", nutrientValue=" + nutrientValue + ", nutrientValueUnit=" + nutrientValueUnit + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
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
