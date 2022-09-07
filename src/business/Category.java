package business;

import java.sql.Date;

public class Category {
	private int categoryId;
	private String categoryName;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public Category(int categoryId, String categoryName) {
		this.categoryId=categoryId;
		this.categoryName=categoryName;

	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
