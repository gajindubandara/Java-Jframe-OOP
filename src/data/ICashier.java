package data;

import java.util.ArrayList;
import business.Cashier;

public interface ICashier {

	int add(Cashier ob);
	int delete(String username);
	int update(Cashier ob);
	Cashier get(String username);
	ArrayList<Cashier> getAll();
	
}