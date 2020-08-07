package yummy.model;

public class Category {

    protected Integer id;
    protected String name;
    protected Integer foodGroupId;
    
    public Category(Integer id, String name,Integer foodGroupId) {
        this.id = id;
        this.name = name;
        this.foodGroupId = foodGroupId;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }

    public Category(Integer id) {
        this.id = id;
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

	public Integer getFoodGroupId() {
		return foodGroupId;
	}

	public void setFoodGroupId(Integer foodGroupId) {
		this.foodGroupId = foodGroupId;
	}

    
}
