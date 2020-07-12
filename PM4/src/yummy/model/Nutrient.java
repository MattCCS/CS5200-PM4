package yummy.model;

public class Nutrient {

    protected Integer nutrientCodeId;
    protected String name;
    
    public Nutrient(Integer nutrientCodeId, String name) {
        this.nutrientCodeId = nutrientCodeId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Nutrient [nutrientCodeId=" + nutrientCodeId + ", name=" + name + "]";
    }

    public Integer getNutrientCodeId() {
        return nutrientCodeId;
    }

    public void setNutrientCodeId(Integer nutrientCodeId) {
        this.nutrientCodeId = nutrientCodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
