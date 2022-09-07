package user;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import business.Book;
import business.Stock;
import data.BookDB;
import data.StockDB;

public class BillUI extends JFrame {

	private JPanel contentPane;
	private BookDB bDB;
	private StockDB sDB;
	private JLabel bgView;

	HashMap<String, String> product_and_price;

	private JTable tblBill;
	private DefaultTableModel tblModel;
	int amount = 0;
	int product_quantity = 0;
	int id = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillUI frame = new BillUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BillUI() {
		setResizable(false);
		setTitle("City Bookshop - Book List");
		setBounds(100, 100, 560, 526);

		BookDB bDB = new BookDB();
		StockDB sDB = new StockDB();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 187, 431, 175);
		contentPane.add(scrollPane);

		tblBill = new JTable();
		tblBill.setBounds(63, 334, 549, 175);
		scrollPane.setViewportView(tblBill);

		tblModel = new DefaultTableModel();
		scrollPane.setViewportView(tblBill);
		tblBill.setModel(tblModel);

		tblModel.addColumn("Book");
		tblModel.addColumn("Quantity");
		tblModel.addColumn("Price");

		JLabel lblNewLabel_1 = new JLabel("Total:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(61, 386, 89, 23);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setBounds(287, 41, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel bookPrice = new JLabel("");
		bookPrice.setBounds(308, 66, 113, 22);
		contentPane.add(bookPrice);

		JLabel lblNewLabel_2_1 = new JLabel("Product name");
		lblNewLabel_2_1.setBounds(40, 41, 181, 14);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Quantity");
		lblNewLabel_2_1_1.setBounds(452, 41, 46, 14);
		contentPane.add(lblNewLabel_2_1_1);

		JComboBox books = new JComboBox();
		books.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookPrice.setText(product_and_price.get(books.getSelectedItem()));
			}
		});
		books.setBounds(40, 66, 206, 22);
		contentPane.add(books);

		JComboBox bookQty = new JComboBox();
		bookQty.setBounds(452, 66, 56, 22);
		contentPane.add(bookQty);

		JLabel total_amount = new JLabel("");
		total_amount.setFont(new Font("Tahoma", Font.BOLD, 20));
		total_amount.setBounds(128, 386, 194, 23);
		contentPane.add(total_amount);

		product_and_price = new HashMap<String, String>();

		try {
			ArrayList<Book> bList = bDB.getAll();
			for (Book b : bList) {
				String name = b.getName();
				String price = b.getPrice();
				int bookId = b.getBookID();

				product_and_price.put(name, price);
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());

		}

		product_and_price.forEach((key, value) -> {
			books.addItem(key);
		});
		for (int i = 1; i <= 10; i++) {
			bookQty.addItem(Integer.toString(i));
		}

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			Integer[] price_list = {};
			List<Integer> arrayPrice = new ArrayList<Integer>(Arrays.asList(price_list));

			public void actionPerformed(ActionEvent e) {
				String book = (String) books.getSelectedItem();
				String qty = (String) bookQty.getSelectedItem();
				int unit_price = Integer.valueOf(bookPrice.getText());
				product_quantity = Integer.parseInt((String) bookQty.getSelectedItem());

				Book b = bDB.getBookByName(book);
				id = b.getBookID();
				Stock stock = sDB.getStock(id);
				amount = Integer.valueOf(stock.getsAmount());

				if (amount >= product_quantity) {

					int total_for_product = unit_price * product_quantity;
					int total_cost = 0;

					tblModel.addRow(new Object[] { book, qty, total_for_product });
					arrayPrice.add(total_for_product);
					price_list = arrayPrice.toArray(price_list);

					for (int counter = 0; counter < arrayPrice.size(); counter++) {
						int price = arrayPrice.get(counter);
						total_cost = total_cost + price;
					}
					total_amount.setText("Rs." + String.valueOf(total_cost) + ".00/-");
					bookQty.setSelectedIndex(0);

				} else {

					JOptionPane.showMessageDialog(null, "There aren't enough books in stock", "Alert",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAdd.setBounds(187, 121, 168, 40);
		contentPane.add(btnAdd);
		Image add = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnAdd.setIcon(new ImageIcon(add));

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentStock = String.valueOf(amount - product_quantity);
				Stock s = new Stock(id, currentStock);
				int result = sDB.updateStock(s);
				JOptionPane.showMessageDialog(null, "Purchase Successful!", "Alert", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
			}
		});
		btnOk.setBounds(389, 373, 103, 40);
		contentPane.add(btnOk);
		Image ok = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		btnOk.setIcon(new ImageIcon(ok));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBounds(187, 436, 168, 40);
		contentPane.add(btnCancel);
		Image cancel = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(cancel));

		JLabel lblNewLabel = new JLabel("Rs.");
		lblNewLabel.setBounds(287, 66, 18, 22);
		contentPane.add(lblNewLabel);

		bgView = new JLabel("");
		bgView.setBounds(0, 0, 1008, 599);
		contentPane.add(bgView);
		Image bgBill = new ImageIcon(this.getClass().getResource("/bgMedium.jpg")).getImage();
		bgView.setIcon(new ImageIcon(bgBill));
	}
}
