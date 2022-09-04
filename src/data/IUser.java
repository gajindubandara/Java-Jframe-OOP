package data;
import business.User;
import java.util.ArrayList;

public interface IUser {

		int addUser(User user);
		int deleteUser(int id);
		int updateUser(User user);
		User get(int id);
		ArrayList<User> getAll();
		int updatepw(User ob);
}
