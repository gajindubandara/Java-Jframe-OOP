package data;

import java.util.ArrayList;

import business.Book;

public interface IBook {
	int add(Book ob);
	int delete(int id);
	int update(Book ob);
	Book get(int id);
	ArrayList<Book> getAll();
}
