package data;

import java.util.ArrayList;

import business.Book;

public interface IBook {
	int addBook(Book book);

	int deleteBook(int bookId);

	int updateBook(Book book);

	Book getBook(int id);

	Book getBookByName(String name);

	ArrayList<Book> getAll();
}
