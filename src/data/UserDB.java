package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import business.Cashier;
import business.User;

public class UserDB implements IUser {
private Connection con;
	
	
	public UserDB() {
		try {
			String url = "jdbc:mysql://localhost:3306/bookshop";
			String user = "root";
			String password = "";
			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				System.out.println("Database connected successfully");

			} else {
				System.out.println("Database connection failed");
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}


	@Override
	public int add(User ob) {
		String insert = "INSERT INTO user(ID,name, address,num, email, password,type) VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setInt(1,ob.getUserID());
			ps.setString(2, ob.getName());
			ps.setString(3,ob.getAddress());
			ps.setString(4,ob.getNumber());
			ps.setString(5,ob.getEmail());
			ps.setString(6,ob.getPassword());
			ps.setString(7,ob.getType());
			
			
			
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "There is an existing User for the above ID. Please check again!");
				return 0;
		    } 
			return 0;
		}
	}


	@Override
	public int delete(int id) {
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


	@Override
	public int update(User ob) {
		String update = "UPDATE user set name=?, address=?,num=?,email=?, password=?,type=? WHERE ID=? ";		
		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, ob.getName());
			ps.setString(2, ob.getAddress());
			ps.setString(3, ob.getNumber());
			ps.setString(4, ob.getPassword());
			ps.setString(5, ob.getEmail());
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


	@Override
	public User get(int id) {
		User c = null;
		String select = "SELECT * FROM user WHERE ID=?";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID=rs.getInt("ID");
				String name =rs.getString("name");
				String address =rs.getString("address");
				String num =rs.getString("num");
				String userN =rs.getString("email");
				String password =rs.getString("password");
				String type =rs.getString("type");
			
				c = new User(ID,name,address,num,userN,password,type);
			}
			rs.close();
			ps.close();
			return c;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}


	@Override
	public ArrayList<User> getAll() {
		ArrayList<User> CList = new ArrayList<User>();
		String select = "SELECT * FROM user";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ID=rs.getInt("ID");
				String name =rs.getString("name");
				String address =rs.getString("address");
				String num =rs.getString("num");
				String userN =rs.getString("email");
				String password =rs.getString("password");
				String type=rs.getString("type");
				User c = new User(ID,name,address,num,userN,password,type);
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


	@Override
	public int updatepw(User ob) {
		String update = "UPDATE user set  password=? WHERE ID=?  ";		
		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, ob.getPassword());
			ps.setInt(2, ob.getUserID());
			


			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch(NullPointerException | SQLException e) {
			System.out.println("NullPointerException thrown!");
			JOptionPane.showMessageDialog(null, "no Id");
			return 0;
		}
		}

	


}
