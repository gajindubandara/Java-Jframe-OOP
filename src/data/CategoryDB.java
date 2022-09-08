package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import business.Category;

public class CategoryDB implements ICategory {
	private Connection con;

	public CategoryDB() {
		SetConnection check = new SetConnection();
		con = check.SetConnection();
	}

	// Adding a new category to the database
	@Override
	public int addCategory(Category category) {
		String insert = "INSERT INTO category(category) VALUES (?)";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setString(1, category.getCategoryName());
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "There is an existing Category. Please check again!", "Alert",
						JOptionPane.ERROR_MESSAGE);
				return 0;
			}
			return 0;
		}
	}

	// Deleting a category from the database
	@Override
	public int deleteCategory(int categoryId) {
		String delete = "DELETE FROM category WHERE ID=?";
		try {
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setInt(1, categoryId);
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	// Updating a category in the database
	@Override
	public int updateCategory(Category category) {
		String update = "UPDATE category set category=? WHERE ID=? ";

		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, category.getCategoryName());
			ps.setInt(2, category.getCategoryId());

			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	// Getting a category from the database
	@Override
	public Category getCategory(int categoryId) {
		Category c = null;
		String select = "SELECT * FROM category WHERE ID=?";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID = rs.getInt("ID");
				String category = rs.getString("category");

				c = new Category(ID, category);
			}
			rs.close();
			ps.close();
			return c;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	// Getting all categories form the database
	@Override
	public ArrayList<Category> getAll() {
		ArrayList<Category> cList = new ArrayList<Category>();
		String select = "SELECT * FROM category";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String category = rs.getString("category");

				Category c = new Category(ID, category);
				cList.add(c);
			}
			rs.close();
			ps.close();
			return cList;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	// Getting a category by the category name
	@Override
	public Category getByCategory(String categoryName) {
		Category c = null;
		String select = "SELECT * FROM category WHERE category=?";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ps.setString(1, categoryName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID = rs.getInt("ID");
				String category = rs.getString("category");

				c = new Category(ID, category);
			}
			rs.close();
			ps.close();
			return c;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

}
