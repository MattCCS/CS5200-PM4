package yummy.model;

public class Ingredient {

    protected Integer id;
    protected String name;
    
    public Ingredient(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ingredient [id=" + id + ", name=" + name + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
