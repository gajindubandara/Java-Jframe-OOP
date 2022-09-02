package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

import business.Book;
import business.Cashier;

public class BookDB implements IBook {
	private Connection con;

	public BookDB() {
		try {
			String url = "jdbc:mysql://localhost:3306/bookshop";
			String user = "root";
			String password = "";
			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				System.out.println("Database connected successfully");

			} else {
				System.out.println("Database connection failed");
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	public int add(Book ob) {
		String insert = "INSERT INTO book( name, isbn,author, date, price,ID) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setString(1, ob.getName());
			ps.setString(2,ob.getIsbn());
			ps.setString(3,ob.getAuthor());
			ps.setDate(4,ob.getDate());
			ps.setString(5,ob.getPrice());
			ps.setInt(6,ob.getBookID());
			
			
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public int delete(int id) {
		String delete = "DELETE FROM book WHERE ID=?";
		try {
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public int update(Book ob) {
		String update = "UPDATE book set name=?, isbn=?,author=?, date=?, price=? WHERE ID=? ";

		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, ob.getName());
			ps.setString(2, ob.getIsbn());
			ps.setString(3, ob.getAuthor());
			ps.setDate(4, ob.getDate());
			ps.setString(5,ob.getPrice());
			ps.setInt(6, ob.getBookID());


			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public Book get(int id) {
		Book b = null;
		String select = "SELECT * FROM book WHERE ID=?";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID=rs.getInt("ID");
				String name =rs.getString("name");
				String isbn =rs.getString("isbn");
				String author =rs.getString("author");
				Date rdate =rs.getDate("date");
				String price =rs.getString("price");
			
				b = new Book(ID,name,isbn,author,rdate,price);
			}
			rs.close();
			ps.close();
			return b;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<Book> getAll() {
		ArrayList<Book> BList = new ArrayList<Book>();
		String select = "SELECT * FROM book";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ID=rs.getInt("ID");
				String name =rs.getString("name");
				String isbn =rs.getString("isbn");
				String author =rs.getString("author");
				Date rdate =rs.getDate("date");
				String price =rs.getString("price");
				Book c = new Book(ID,name,isbn,author,rdate,price);
				BList.add(c);
			}
			rs.close();
			ps.close();
			return BList;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	

}
