package yummy.model;

public class Category {

    protected Integer id;
    protected String name;
    protected Integer foodgroupid;
    
    public Category(Integer id, String name,Integer foodgroupid) {
        this.id = id;
        this.name = name;
        this.foodgroupid = foodgroupid;
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

	public Integer getFoodgroupid() {
		return foodgroupid;
	}

	public void setFoodgroupid(Integer foodgroupid) {
		this.foodgroupid = foodgroupid;
	}
}
