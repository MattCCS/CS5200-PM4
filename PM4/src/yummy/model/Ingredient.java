package yummy.model;

public class Ingredient {

    protected int id;
    protected String name;
    
    public Ingredient(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ingredient [id=" + id + ", name=" + name + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
