package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import business.Category;
import user.ConnectionStatus;

public class CategoryDB implements ICategory {
	private Connection con;

	public CategoryDB() {
		try {
			String url = "jdbc:mysql://localhost:3306/bookshop";
			String user = "root";
			String password = "";
			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				System.out.println("Database connected successfully");
				ConnectionStatus.message = "Database connected successfully";
				ConnectionStatus.status = true;

			} else {
				System.out.println("Database connection failed");

			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			ConnectionStatus.message = "Database connection failed";
			ConnectionStatus.status = false;
		}
	}

	@Override
	public int addCategory(Category category) {
		String insert = "INSERT INTO category(category) VALUES (?)";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
//			ps.setInt(1, category.getCategoryId());
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
