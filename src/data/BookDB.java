package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import business.Book;

public class BookDB implements IBook {
	private Connection con;

	public BookDB() {
		SetConnection check = new SetConnection();
		con = check.SetConnection();
	}

	// Adding a new book to the database
	@Override
	public int addBook(Book ob) {
		String insert = "INSERT INTO book( name, isbn,author, date, price,ID,category) VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setString(1, ob.getName());
			ps.setString(2, ob.getIsbn());
			ps.setString(3, ob.getAuthor());
			ps.setDate(4, ob.getDate());
			ps.setString(5, ob.getPrice());
			ps.setInt(6, ob.getBookID());
			ps.setInt(7, ob.getCategory());
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "There is an existing Book for the above ID. Please check again!",
						"Alert", JOptionPane.ERROR_MESSAGE);
				return 0;
			}
			return 0;
		}
	}

	// Deleting book from the database
	@Override
	public int deleteBook(int bookId) {
		String delete = "DELETE FROM book WHERE ID=?";
		try {
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setInt(1, bookId);
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	// Updating a book in the database
	@Override
	public int updateBook(Book ob) {
		String update = "UPDATE book set name=?, isbn=?,author=?, date=?, price=?,category=? WHERE ID=? ";

		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, ob.getName());
			ps.setString(2, ob.getIsbn());
			ps.setString(3, ob.getAuthor());
			ps.setDate(4, ob.getDate());
			ps.setString(5, ob.getPrice());
			ps.setInt(6, ob.getCategory());
			ps.setInt(7, ob.getBookID());
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	// Getting a book from the database
	@Override
	public Book getBook(int id) {
		Book b = null;
		String select = "SELECT * FROM book WHERE ID=?";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID = rs.getInt("ID");
				String name = rs.getString("name");
				String isbn = rs.getString("isbn");
				String author = rs.getString("author");
				Date rdate = rs.getDate("date");
				String price = rs.getString("price");
				int category = rs.getInt("category");

				b = new Book(ID, name, isbn, author, rdate, price, category);
			}
			rs.close();
			ps.close();
			return b;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	// Getting all the books from the database
	@Override
	public ArrayList<Book> getAll() {
		ArrayList<Book> BList = new ArrayList<Book>();
		String select = "SELECT * FROM book";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String name = rs.getString("name");
				String isbn = rs.getString("isbn");
				String author = rs.getString("author");
				Date rdate = rs.getDate("date");
				String price = rs.getString("price");
				int category = rs.getInt("category");
				Book c = new Book(ID, name, isbn, author, rdate, price, category);
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

	// getting a book from the database by the book name
	@Override
	public Book getBookByName(String name) {
		Book b = null;
		String select = "SELECT * FROM book WHERE name=?";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID = rs.getInt("ID");
				String bookName = rs.getString("name");
				String isbn = rs.getString("isbn");
				String author = rs.getString("author");
				Date rdate = rs.getDate("date");
				String price = rs.getString("price");
				int category = rs.getInt("category");

				b = new Book(ID, bookName, isbn, author, rdate, price, category);
			}
			rs.close();
			ps.close();
			return b;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
