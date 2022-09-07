package data;

import java.util.ArrayList;

import business.User;

public interface IUser {

	int addUser(User user);

	int deleteUser(int id);

	int updateUser(User user);

	User getUser(int id);

	ArrayList<User> getAll();

	int updateUserPw(User ob);
}
