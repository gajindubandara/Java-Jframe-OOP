package data;

import java.util.ArrayList;
import business.Cashier;

public interface ICashier {

	int add(Cashier ob);
	int delete(int id);
	int update(Cashier ob);
	Cashier get(int id);
//	Cashier getLogin(String username);
	ArrayList<Cashier> getAll();
	int updatepw(Cashier ob);
	
	
}