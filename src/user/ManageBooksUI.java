package user;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

import business.Book;
import data.BookDB;


public class ManageBooksUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtIsbn;
	private JTextField txtAuthor;
	private JTextField txtDate;
	private JTextField txtPrice;
	protected JComboBox cMajor;
	protected JLabel GetAllText;
	private DefaultTableModel tblModel;
	private JTextField txtName;
	
	private BookDB bDB;
	
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
		setTitle("Manage Books");
		setResizable(false);
		setBounds(100, 100, 450, 361);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		BookDB bDB = new BookDB();

		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(49, 107, 71, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Book ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(49, 77, 71, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Author");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(49, 137, 71, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(49, 197, 71, 14);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_2_1 = new JLabel("Category");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(49, 227, 71, 14);
		contentPane.add(lblNewLabel_2_1);

		txtID = new JTextField();
		txtID.setBounds(153, 72, 136, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtIsbn = new JTextField();
		txtIsbn.setColumns(10);
		txtIsbn.setBounds(153, 102, 136, 20);
		contentPane.add(txtIsbn);

		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(153, 132, 136, 20);
		contentPane.add(txtAuthor);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(153, 192, 136, 20);
		contentPane.add(txtPrice);

	

		JComboBox cCategory = new JComboBox();
		cCategory.setModel(new DefaultComboBoxModel(new String[] { "IT", "Engineering", "Science" }));
		cCategory.setBounds(153, 222, 136, 22);
		contentPane.add(cCategory);


		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkValid()) {
					int id=Integer.valueOf(txtID.getText());
					String name = txtName.getText();
					String isbn = txtIsbn.getText();
					String author = txtAuthor.getText();
					Date bdate =Date.valueOf(txtDate.getText());
					String price = txtPrice.getText();

					Book b = new Book(id,name,isbn,author,bdate,price);
					int result = bDB.add(b);

					if (result == 1) {
						JOptionPane.showMessageDialog(null, "New Record is added");
						txtID.setText("");
						txtName.setText("");
						txtIsbn.setText("");
						txtAuthor.setText("");
						txtDate.setText("");
						txtPrice.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "New Record is not added");
					}
				}

			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(27, 255, 85, 21);
		contentPane.add(btnAdd);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf( JOptionPane.showInputDialog("Enter the Book ID"));
				int result = bDB.delete(id);
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "The Book is deleted");
				} else {
					JOptionPane.showMessageDialog(null, "The Book is not deleted");
				}

			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(122, 255, 85, 21);
		contentPane.add(btnDelete);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkValid()) {
					int id=Integer.valueOf(txtID.getText());
					String name = txtName.getText();
					String isbn = txtIsbn.getText();
					String author = txtAuthor.getText();
					Date bdate =Date.valueOf(txtDate.getText());
					String price = txtPrice.getText();

					Book b = new Book(id,name,isbn,author,bdate,price);
					int result = bDB.update(b);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "The Book is updated");
						txtID.setText("");
						txtID.setEnabled(true);
						txtName.setText("");
						txtIsbn.setText("");
						txtAuthor.setText("");
						txtDate.setText("");
						txtPrice.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "The Book is not updated");
					}
				}

			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(217, 255, 85, 21);
		contentPane.add(btnUpdate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(312, 255, 85, 21);
		contentPane.add(btnCancel);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf(JOptionPane.showInputDialog("Enter the Book ID"));
				Book b = bDB.get(id);
				if (b != null) {
					txtName.setText(b.getName());
					txtID.setText(String.valueOf(b.getBookID()));
					txtID.setEnabled(false);
					txtIsbn.setText(b.getIsbn());
					txtAuthor.setText(b.getAuthor());
					txtDate.setText(String.valueOf(b.getDate()));
					txtPrice.setText(b.getPrice());
					
//					cMajor.setSelectedItem(b.getMajor());
				}else {
					JOptionPane.showMessageDialog(null, "No book for this ID number");
				}
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFind.setBounds(174, 290, 85, 21);
		contentPane.add(btnFind);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Published Date");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(49, 167, 94, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(153, 162, 136, 20);
		contentPane.add(txtDate);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(153, 41, 136, 20);
		contentPane.add(txtName);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(49, 46, 71, 14);
		contentPane.add(lblNewLabel_1_2);

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
			JOptionPane.showMessageDialog(this, "Book ID must be numeric");
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
		try {
			Date date = Date.valueOf(txtDate.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Date of birth must be in YYYY-MM-DD format");
			return false;
		}

		if (txtPrice.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Price cannot be blank");
			return false;
		}
		
		try {
			int price = Integer.valueOf(txtPrice.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Price must be numeric: Ex- 100");
			return false;
		}

		return true;
	}
}
