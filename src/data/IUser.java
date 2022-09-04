package data;
import business.User;
import java.util.ArrayList;

public interface IUser {

		int add(User ob);
		int delete(int id);
		int update(User ob);
		User get(int id);
		ArrayList<User> getAll();
		int updatepw(User ob);
}
