package data;

import java.sql.*;
import java.util.ArrayList;

import business.Cashier;
import business.Login;

public class CashierDB implements ICashier {
	private Connection con;
	
	public CashierDB() {
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
	public int add(Cashier ob) {
		String insert = "INSERT INTO cashier(name, address,num, username, password) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setString(1, ob.getName());
			ps.setString(2,ob.getAddress());
			ps.setString(3,ob.getNumber());
			ps.setString(4,ob.getUsername());
			ps.setString(4,ob.getPassword());
			
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
		String delete = "DELETE FROM cahsier WHERE username=?";
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
	public int update(Cashier ob) {
		String update = "UPDATE cashier set name=?, address=?,num=?, password=?, WHERE username=? ";

		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, ob.getName());
			ps.setString(2, ob.getAddress());
			ps.setString(3, ob.getNumber());
			ps.setString(4, ob.getPassword());
			ps.setString(5, ob.getUsername());


			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	@Override
	public Cashier get(String username) {
		Cashier c = null;
		String select = "SELECT * FROM cashier WHERE username=?";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID=rs.getInt("ID");
				String name =rs.getString("name");
				String address =rs.getString("address");
				String num =rs.getString("num");
				String userN =rs.getString("username");
				String password =rs.getString("password");
			
				c = new Cashier(ID,name,address,num,userN,password);
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
	public ArrayList<Cashier> getAll() {
		ArrayList<Cashier> CList = new ArrayList<Cashier>();
		String select = "SELECT * FROM cashier";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ID=rs.getInt("ID");
				String name =rs.getString("name");
				String address =rs.getString("address");
				String num =rs.getString("num");
				String userN =rs.getString("username");
				String password =rs.getString("password");
				Cashier c = new Cashier(ID,name,address,num,userN,password);
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

}
