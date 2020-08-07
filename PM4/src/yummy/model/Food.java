package yummy.model;

public class Food {

	protected Integer id;
	protected String name;
	protected Integer categoryId;

	public Food(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Food(Integer id, String name, Integer categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", categoryId=" + categoryId + "]";
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}
