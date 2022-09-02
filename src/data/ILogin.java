package data;

import java.util.ArrayList;

import business.Login;

public interface ILogin {
	int add(Login ob);
	int delete(String username);
	int update(Login ob);
	Login get(String username);
	ArrayList<Login> getAll();
}
