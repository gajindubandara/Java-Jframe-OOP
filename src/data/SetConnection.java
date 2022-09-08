package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import user.ConnectionStatus;

public class SetConnection {
	private Connection con;

	public Connection SetConnection() {
		// Setting connection to the database
		try {
			String url = "jdbc:mysql://localhost:3306/bookshop";
			String user = "root";
			String password = "";
			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				ConnectionStatus.message = "Database connected successfully";
				ConnectionStatus.status = true;
			} else {
			}
			return con;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			ConnectionStatus.message = "Database connection failed";
			ConnectionStatus.status = false;
			return null;
		}
	}
}
