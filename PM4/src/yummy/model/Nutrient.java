package yummy.model;

public class Nutrient {

    protected int nutrientCodeId;
    protected String name;
    
    public Nutrient(int nutrientCodeId, String name) {
        this.nutrientCodeId = nutrientCodeId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Nutrient [nutrientCodeId=" + nutrientCodeId + ", name=" + name + "]";
    }

    public int getNutrientCodeId() {
        return nutrientCodeId;
    }

    public void setNutrientCodeId(int nutrientCodeId) {
        this.nutrientCodeId = nutrientCodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
