package user;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.Book;
import business.Category;
import business.Stock;
import data.BookDB;
import data.CategoryDB;
import data.StockDB;

public class ManageBooksUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtIsbn;
	private JTextField txtAuthor;
	private JTextField txtDate;
	private JTextField txtPrice;
	protected JComboBox cCategory;
	protected JLabel GetAllText;
	private JTextField txtName;

	private BookDB bDB;
	private CategoryDB cDB;
	private StockDB sDB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageBooksUI frame = new ManageBooksUI();
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
	public ManageBooksUI() {
		setTitle("City Bookshop - Manage Books");
		setResizable(false);
		setBounds(100, 100, 454, 454);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		BookDB bDB = new BookDB();
		CategoryDB cDB = new CategoryDB();
		StockDB sDB = new StockDB();

		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(49, 107, 71, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Book ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(49, 77, 71, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Author");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(49, 137, 71, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(49, 197, 71, 14);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("Category");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(49, 227, 71, 14);
		contentPane.add(lblNewLabel_2_1);

		txtID = new JTextField();
		txtID.setBounds(153, 72, 222, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtIsbn = new JTextField();
		txtIsbn.setColumns(10);
		txtIsbn.setBounds(153, 102, 222, 20);
		contentPane.add(txtIsbn);

		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(153, 132, 222, 20);
		contentPane.add(txtAuthor);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(153, 192, 222, 20);
		contentPane.add(txtPrice);

		JComboBox cCategory = new JComboBox();
		cCategory.setBounds(153, 222, 222, 22);
		contentPane.add(cCategory);

		try {
			ArrayList<Category> cList = cDB.getAll();
			for (Category c : cList) {
				String name = c.getCategoryName();
				cCategory.addItem(name);
			}

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Validating the text fields
				if (checkValid()) {
					int id = Integer.valueOf(txtID.getText());
					String name = txtName.getText();
					String isbn = txtIsbn.getText();
					String author = txtAuthor.getText();
					Date bdate = Date.valueOf(txtDate.getText());
					String price = txtPrice.getText();
					String category = String.valueOf(cCategory.getSelectedItem());

					// Getting the Category by name
					Category c = cDB.getByCategory(category);
					if (c != null) {
						int categoryId = c.getCategoryId();
						Book b = new Book(id, name, isbn, author, bdate, price, categoryId);

						// Adding the book
						int result = bDB.addBook(b);
						if (result == 1) {

							// Creating an empty stock for the new book
							String noOfBooks = "0";
							Stock s = new Stock(id, noOfBooks);
							sDB.addStock(s);

							JOptionPane.showMessageDialog(null, "New Record is added", "Alert",
									JOptionPane.INFORMATION_MESSAGE);
							txtID.setText("");
							txtName.setText("");
							txtIsbn.setText("");
							txtAuthor.setText("");
							txtDate.setText("");
							txtPrice.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "New Record is not added", "Alert",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(40, 281, 168, 40);
		contentPane.add(btnAdd);
		Image add = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnAdd.setIcon(new ImageIcon(add));

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting the user Id from a JOptionPane Input dialog
					int id = Integer.valueOf(JOptionPane.showInputDialog("Enter the Book ID"));

					JFrame f = new JFrame();
					int a = JOptionPane.showConfirmDialog(f, "Are you sure you want to contine?");

					if (a == JOptionPane.YES_OPTION) {
						f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

						// Deleting a book
						int result = bDB.deleteBook(id);
						if (result == 1) {

							// Remove the stock data on the above book
							sDB.deleteStock(id);
							JOptionPane.showMessageDialog(null, "The Book is deleted", "Alert",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null,
									"No book found for the entered ID.Please check the ID Again!", "Alert",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Book ID cannot be null", "Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(40, 343, 168, 40);
		contentPane.add(btnDelete);
		Image del = new ImageIcon(this.getClass().getResource("/del.png")).getImage();
		btnDelete.setIcon(new ImageIcon(del));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBounds(218, 343, 168, 40);
		contentPane.add(btnCancel);
		Image cancel = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(cancel));

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Published Date");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_1.setBounds(49, 167, 94, 14);
		contentPane.add(lblNewLabel_1_1_1_1);

		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(153, 162, 222, 20);
		contentPane.add(txtDate);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(153, 41, 222, 20);
		contentPane.add(txtName);

		JLabel lblNewLabel_1_2 = new JLabel("Name");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(49, 46, 71, 14);
		contentPane.add(lblNewLabel_1_2);

		JButton btnUF = new JButton("Find & Update");
		JButton btnU = new JButton("Update");
		btnUF.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting the user Id from a JOptionPane Input dialog
					int id = Integer.valueOf(JOptionPane.showInputDialog("Enter the Book ID"));

					// Getting the book form the database
					Book b = bDB.getBook(id);
					if (b != null) {
						txtName.setText(b.getName());
						txtID.setText(String.valueOf(b.getBookID()));
						txtID.setEnabled(false);
						txtIsbn.setText(b.getIsbn());
						txtAuthor.setText(b.getAuthor());
						txtDate.setText(String.valueOf(b.getDate()));
						txtPrice.setText(b.getPrice());
						btnUF.setVisible(false);
						btnU.setVisible(true);
						btnAdd.setEnabled(false);
						btnDelete.setEnabled(false);

						// Getting the category from the database
						Category c = cDB.getCategory(b.getCategory());
						if (c != null) {
							cCategory.setSelectedItem(c.getCategoryName());
						}
					} else {
						JOptionPane.showMessageDialog(null, "No book for this ID number", "Alert",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Book ID cannot be null", "Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUF.setBounds(218, 281, 168, 40);
		contentPane.add(btnUF);
		Image find = new ImageIcon(this.getClass().getResource("/find.png")).getImage();
		btnUF.setIcon(new ImageIcon(find));

		btnU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Validating the text fields
				if (checkValid()) {
					int id = Integer.valueOf(txtID.getText());
					String name = txtName.getText();
					String isbn = txtIsbn.getText();
					String author = txtAuthor.getText();
					Date bdate = Date.valueOf(txtDate.getText());
					String price = txtPrice.getText();
					String category = String.valueOf(cCategory.getSelectedItem());
					System.out.println(category);

					// Getting the category from the database
					Category c = cDB.getByCategory(category);
					if (c != null) {
						int categoryId = c.getCategoryId();
						System.out.println(categoryId);
						Book b = new Book(id, name, isbn, author, bdate, price, categoryId);

						// Updating the book
						int result = bDB.updateBook(b);
						if (result == 1) {
							JOptionPane.showMessageDialog(null, "The Book is updated", "Alert",
									JOptionPane.INFORMATION_MESSAGE);
							txtID.setText("");
							txtID.setEnabled(true);
							txtName.setText("");
							txtIsbn.setText("");
							txtAuthor.setText("");
							txtDate.setText("");
							txtPrice.setText("");
							btnU.setVisible(false);
							btnUF.setVisible(true);
							btnAdd.setEnabled(true);
							btnDelete.setEnabled(true);
						} else {
							JOptionPane.showMessageDialog(null, "The Book is not updated", "Alert",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnU.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnU.setBounds(218, 281, 168, 40);
		contentPane.add(btnU);
		Image up = new ImageIcon(this.getClass().getResource("/update.png")).getImage();
		btnU.setIcon(new ImageIcon(up));

		JLabel bgMb = new JLabel("");
		bgMb.setBounds(0, 0, 438, 415);
		contentPane.add(bgMb);
		Image bg = new ImageIcon(this.getClass().getResource("/bgMb.jpg")).getImage();
		bgMb.setIcon(new ImageIcon(bg));

	}

	private boolean checkValid() {
		if (txtName.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Book name cannot be blank");
			return false;
		}
		if (txtID.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Book ID cannot be blank");
			return false;
		}
		try {
			int id = Integer.valueOf(txtID.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Book ID must be numeric", "Alert", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (txtIsbn.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "ISBN number cannot be blank");
			return false;
		}

		if (txtAuthor.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Author cannot be blank");
			return false;
		}
		if (txtDate.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Author cannot be blank");
			return false;
		}
		try {
			Date date = Date.valueOf(txtDate.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Published date must be in YYYY-MM-DD format", "Alert",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (txtPrice.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Price cannot be blank");
			return false;
		}

		try {
			int price = Integer.valueOf(txtPrice.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Price must be numeric: Ex- 100", "Alert", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		return true;
	}
}
