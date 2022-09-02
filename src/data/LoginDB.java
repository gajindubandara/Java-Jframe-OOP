package data;

import java.sql.*;
import java.util.ArrayList;
import business.Login;
import business.Book;

public class LoginDB implements ILogin {
	private Connection con;

	/**
	 * @param con
	 */
	public LoginDB() {
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
	public int add(Login ob) {
		String insert = "INSERT INTO login(password,username) VALUES (?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setString(1, ob.getUsername());
			ps.setString(2, ob.getPassword());

			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public int delete(String username) {
		String delete = "DELETE FROM login WHERE username=?";
		try {
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setString(1, username);
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public int update(Login ob) {
		String update = "UPDATE login set password=? WHERE username=? ";

		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, ob.getPassword());
			ps.setString(2, ob.getUsername());

			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public Login get(String username) {
		Login log = null;
		String select = "SELECT * FROM login WHERE username=?";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String userN = rs.getString("username");
				String password = rs.getString("password");
				log = new Login(userN,password);
			}
			rs.close();
			ps.close();
			return log;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public ArrayList<Login> getAll() {
		ArrayList<Login> logList = new ArrayList<Login>();
		String select = "SELECT * FROM login";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				Login log = new Login(username,password);
				logList.add(log);
			}
			rs.close();
			ps.close();
			return logList;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

}
