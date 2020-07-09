package restaurant.model;

public class FoodIngredientLine {
    
    protected int id;
    protected int foodId;
    protected String ingredientLineId;

    public FoodIngredientLine(int id, int foodId, String ingredientLineId) {
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

    public String getIngredientLineId() {
        return ingredientLineId;
    }

    public void setIngredientLineId(String ingredientLineId) {
        this.ingredientLineId = ingredientLineId;
    }

}
