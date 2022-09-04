package data;

import java.util.ArrayList;

import business.Book;

public interface IBook {
	int addBook(Book book);
	int deleteBook(int bookId);
	int updateBook(Book book);
	Book get(int id);
	ArrayList<Book> getAll();
}


//addBook(Book book);
//deleteBook(Book book);
//updateBook(Book book);

