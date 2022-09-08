package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import business.Stock;

public class StockDB implements IStock {
	private Connection con;

	public StockDB() {
		SetConnection check = new SetConnection();
		con = check.SetConnection();
	}

	// Adding a new stock to the database
	@Override
	public int addStock(Stock stock) {
		String insert = "INSERT INTO stock(ID,stock) VALUES (?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setInt(1, stock.getsId());
			ps.setString(2, stock.getsAmount());
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "There is an existing stock for this ID. Please check again!",
						"Alert", JOptionPane.ERROR_MESSAGE);
				return 0;
			}
			return 0;
		}
	}

	// Deleting a stock from the database
	@Override
	public int deleteStock(int sId) {
		String delete = "DELETE FROM stock WHERE ID=?";
		try {
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setInt(1, sId);
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	// Updating the stock on the database
	@Override
	public int updateStock(Stock stock) {
		String update = "UPDATE stock set stock=? WHERE ID=? ";
		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, stock.getsAmount());
			ps.setInt(2, stock.getsId());

			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

	// Getting the stock from the database
	@Override
	public Stock getStock(int sId) {
		Stock s = null;
		String select = "SELECT * FROM stock WHERE ID=?";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ps.setInt(1, sId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ID = rs.getInt("ID");
				String stock = rs.getString("stock");

				s = new Stock(ID, stock);
			}
			rs.close();
			ps.close();
			return s;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	// Getting all stock from the database
	@Override
	public ArrayList<Stock> getAll() {
		ArrayList<Stock> cList = new ArrayList<Stock>();
		String select = "SELECT * FROM stock";
		try {
			PreparedStatement ps = con.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ID = rs.getInt("ID");
				String stock = rs.getString("stock");

				Stock s = new Stock(ID, stock);
				cList.add(s);
			}
			rs.close();
			ps.close();
			return cList;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

}
