package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import business.User;

public class UserDB implements IUser {
	private Connection con;

	public UserDB() {
		SetConnection check = new SetConnection();
		con = check.SetConnection();
	}

	// Adding a new user to the database
	@Override
	public int addUser(User ob) {
		String insert = "INSERT INTO user(ID,name, address,num, email, password,type) VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setInt(1, ob.getUserID());
			ps.setString(2, ob.getName());
			ps.setString(3, ob.getAddress());
			ps.setString(4, ob.getNumber());
			ps.setString(5, ob.getEmail());
			ps.setString(6, ob.getPassword());
			ps.setString(7, ob.getType());

			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "There is an existing User for the above ID. Please check again!",
						"Alert", JOptionPane.ERROR_MESSAGE);
				return 0;
			}
			return 0;
		}
	}

	// deleting user from the database
	@Override
	public int deleteUser(int id) {
		String delete = "DELETE FROM user WHERE ID=?";
		try {
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	// Updating user in the database
	@Override
	public int updateUser(User ob) {
		String update = "UPDATE user set name=?, address=?,num=?,email=?, password=?,type=? WHERE ID=? ";
		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, ob.getName());
			ps.setString(2, ob.getAddress());
			ps.setString(3, ob.getNumber());
			ps.setString(4, ob.getEmail());
			ps.setString(5, ob.getPassword());
			ps.setString(6, ob.getType());
			ps.setInt(7, ob.getUserID());

			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	// Getting a user from the database
	@Override
	public User getUser(int id) {
		User c = null;
		String select = "SELECT * FROM user WHERE ID=?";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID = rs.getInt("ID");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String num = rs.getString("num");
				String userN = rs.getString("email");
				String password = rs.getString("password");
				String type = rs.getString("type");

				c = new User(ID, name, address, num, userN, password, type);
			}
			rs.close();
			ps.close();
			return c;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	// Getting all users from the database
	@Override
	public ArrayList<User> getAll() {
		ArrayList<User> CList = new ArrayList<User>();
		String select = "SELECT * FROM user";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String num = rs.getString("num");
				String userN = rs.getString("email");
				String password = rs.getString("password");
				String type = rs.getString("type");
				User c = new User(ID, name, address, num, userN, password, type);
				CList.add(c);
			}
			rs.close();
			ps.close();
			return CList;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	// Updating the user password
	@Override
	public int updateUserPw(User ob) {
		String update = "UPDATE user set  password=? WHERE ID=?  ";
		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, ob.getPassword());
			ps.setInt(2, ob.getUserID());

			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (NullPointerException | SQLException e) {
			System.out.println("NullPointerException thrown!");
			JOptionPane.showMessageDialog(null, "no Id");
			return 0;
		}
	}
}
