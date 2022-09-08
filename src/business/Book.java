package business;

import java.sql.Date;

public class Book {
	private int bookID;
	private String name;
	private String isbn;
	private String author;
	private Date date;
	private String price;
	private int category;

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(int bookID, String name, String isbn, String author, Date date, String price, int category) {
		this.bookID = bookID;
		this.name = name;
		this.isbn = isbn;
		this.author = author;
		this.date = date;
		this.price = price;
		this.category = category;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

}
