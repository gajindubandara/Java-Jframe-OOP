package data;

import java.util.ArrayList;

import business.Stock;

public interface IStock {
	int addStock(Stock stock);

	int deleteStock(int sId);

	int updateStock(Stock stock);

	Stock getStock(int sId);

	ArrayList<Stock> getAll();
}
