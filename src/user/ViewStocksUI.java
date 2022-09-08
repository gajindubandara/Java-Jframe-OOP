package user;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import business.Book;
import business.Stock;
import data.BookDB;
import data.StockDB;

public class ViewStocksUI extends JFrame {

	private JPanel contentPane;
	private JTable booktbl;
	private DefaultTableModel tblModel;
	private JTextField txtName;
	private StockDB sDB;
	private BookDB bDB;
	private JTextField txtPrice;
	private JTextField txtCategory;
	private JButton btnViewAll;
	private JLabel lblNewLabel;
	private JLabel bgView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStocksUI frame = new ViewStocksUI();
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
	public ViewStocksUI() {
		setResizable(false);
		setTitle("City Bookshop - Book Stocks");
		setBounds(100, 100, 560, 526);

		StockDB sDB = new StockDB();
		BookDB bDB = new BookDB();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 187, 464, 210);
		contentPane.add(scrollPane);

		booktbl = new JTable();
		booktbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		booktbl.setBounds(32, 121, 937, 514);
		scrollPane.setViewportView(booktbl);

		tblModel = new DefaultTableModel();
		booktbl.setModel(tblModel);

		txtName = new JTextField();
		txtName.setBounds(97, 65, 349, 27);
		contentPane.add(txtName);
		txtName.setColumns(10);

		btnViewAll = new JButton("View All Stocks");
		btnViewAll.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Getting all the stocks and display them in a table
				try {
					ArrayList<Stock> stockList = sDB.getAll();
					tblModel.setRowCount(0);
					for (Stock s : stockList) {
						int id = s.getsId();
						String amount = s.getsAmount();

						Book b = bDB.getBook(id);
						String bName = b.getName();

						tblModel.addRow(new Object[] { id, bName, amount });
					}
				} catch (Exception ex) {
					System.err.println(ex.getMessage());
				}
			}
		});
		btnViewAll.setBounds(23, 436, 163, 40);
		contentPane.add(btnViewAll);
		Image all = new ImageIcon(this.getClass().getResource("/viewA.png")).getImage();
		btnViewAll.setIcon(new ImageIcon(all));

		JButton btnName = new JButton("Search By Book ID");
		btnName.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Bid = Integer.valueOf(txtName.getText());
				ArrayList<Stock> stockList = sDB.getAll();
				Book book = bDB.getBook(Bid);

				tblModel.setRowCount(0);
				for (Stock u : stockList) {

					// Getting the stocks with the entered id
					if (u.getsId() == Bid) {
						int id = u.getsId();
						String amount = u.getsAmount();
						String bName = book.getName();

						// Display the stock on the entered id
						tblModel.addRow(new Object[] { id, bName, amount });
					}
				}
				txtName.setText("");
				btnViewAll.setEnabled(true);
			}
		});
		btnName.setBounds(179, 116, 192, 34);
		contentPane.add(btnName);
		Image imgName = new ImageIcon(this.getClass().getResource("/name.png")).getImage();
		btnName.setIcon(new ImageIcon(imgName));

		lblNewLabel = new JLabel(" Books Stocks");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 524, 43);
		contentPane.add(lblNewLabel);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(371, 436, 163, 40);
		contentPane.add(btnCancel);
		Image cancel = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(cancel));

		bgView = new JLabel("");
		bgView.setBounds(0, 0, 544, 487);
		contentPane.add(bgView);
		Image bgVs = new ImageIcon(this.getClass().getResource("/bgMedium.jpg")).getImage();
		bgView.setIcon(new ImageIcon(bgVs));

		tblModel.addColumn("ID");
		tblModel.addColumn("Name");
		tblModel.addColumn("Amount");

		// Getting all the books and display them in a table
		try {
			ArrayList<Stock> stockList = sDB.getAll();
			tblModel.setRowCount(0);
			for (Stock s : stockList) {
				int id = s.getsId();
				String amount = s.getsAmount();

				Book b = bDB.getBook(id);
				String bName = b.getName();

				tblModel.addRow(new Object[] { id, bName, amount });
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
