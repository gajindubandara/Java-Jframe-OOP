package data;

import java.util.ArrayList;

import business.Category;

public interface ICategory {

	int addCategory(Category category);

	int deleteCategory(int categoryId);

	int updateCategory(Category category);

	Category getCategory(int categoryId);

	Category getByCategory(String categoryName);

	ArrayList<Category> getAll();

}
