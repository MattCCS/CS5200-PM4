package yummy.model;

public class FoodIngredientLine {
    
    protected Integer id;
    protected Integer foodId;
    protected String ingredientLineId;

    public FoodIngredientLine(Integer id, Integer foodId, String ingredientLineId) {
        super();
        this.id = id;
        this.foodId = foodId;
        this.ingredientLineId = ingredientLineId;
    }

    @Override
    public String toString() {
        return "FoodIngredientLine [id=" + id + ", foodId=" + foodId + ", ingredientLineId=" + ingredientLineId
                + "]";
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

    public String getIngredientLineId() {
        return ingredientLineId;
    }

    public void setIngredientLineId(String ingredientLineId) {
        this.ingredientLineId = ingredientLineId;
    }

}
