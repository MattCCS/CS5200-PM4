package restaurant.model;

public class Food {

	protected int id;
	protected String name;
	protected int categoryId;

	public Food(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Food(int id, String name, int categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", categoryId=" + categoryId + "]";
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

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
