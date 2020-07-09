package restaurant.model;

public class IngredientNutrientValue {

    protected int id;
    protected int ingredientId;
    protected int nutrientCodeId;
    protected int nutrientValue;
    protected String nutrientValueUnit;

    public IngredientNutrientValue(int id, int ingredientId, int nutrientCodeId, int nutrientValue, String nutrientValueUnit) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
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
