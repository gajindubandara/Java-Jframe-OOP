package user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import business.Book;
import data.BookDB;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.InputEvent;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

public class ViewBooksUI extends JFrame {

	private JPanel contentPane;
	private JTable booktbl;
	private DefaultTableModel tblModel;
	private JTextField txtName;
	private BookDB bDB;
	private JTextField txtPrice;
	private JButton btnPrice;
	private JTextField txtCategory;
	private JButton btncategory;
	private JButton btnVAll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBooksUI frame = new ViewBooksUI();
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
	public ViewBooksUI() {
		setResizable(false);
		setTitle("View all Books");
		setBounds(100, 100, 1024, 600);
		
		BookDB bDB = new BookDB();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 160, 960, 353);
		contentPane.add(scrollPane);
		
		booktbl = new JTable();
		booktbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		booktbl.setBounds(32, 121, 937, 514);
		scrollPane.setViewportView(booktbl);
		
		tblModel = new DefaultTableModel();
		booktbl.setModel(tblModel);
		
		txtName = new JTextField();
		txtName.setBounds(39, 64, 132, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		
		
		JButton btnName = new JButton("Search By Name");
		btnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Bname=txtName.getText();
				ArrayList<Book> BookList=bDB.getAll();
				tblModel.setRowCount(0);
				 for(Book b:BookList) {
					 if( b.getName().equalsIgnoreCase(Bname)) {
						 int Bid=b.getBookID();
						 String name = b.getName();
						 String isbn = b.getIsbn();
						 String author = b.getAuthor();
						 Date bdate =b.getDate();
						 String price ="Rs."+b.getPrice()+".00/-";
					
						   tblModel.addRow(new Object[] {Bid,name,isbn,author,bdate,price}); 
						 txtName.setText("");
						 btnVAll.setEnabled(true);
					 }
					   
				   }
			}
		});
		btnName.setBounds(181, 63, 132, 23);
		contentPane.add(btnName);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(333, 65, 132, 20);
		contentPane.add(txtPrice);
		
		btnPrice = new JButton("Search By Price");
		btnPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Price=txtPrice.getText();
				ArrayList<Book> BookList=bDB.getAll();
				tblModel.setRowCount(0);
				 for(Book b:BookList) {
					 if( b.getPrice().equals(Price)) {
						 int Bid=b.getBookID();
						 String name = b.getName();
						 String isbn = b.getIsbn();
						 String author = b.getAuthor();
						 Date bdate =b.getDate();
						 String price ="Rs."+b.getPrice()+".00/-";
					
						   tblModel.addRow(new Object[] {Bid,name,isbn,author,bdate,price}); 
						 txtName.setText("");
						 btnVAll.setEnabled(true);
					 }
					   
				   }
				
			}
		});
		btnPrice.setBounds(475, 64, 132, 23);
		contentPane.add(btnPrice);
		
		txtCategory = new JTextField();
		txtCategory.setColumns(10);
		txtCategory.setBounds(648, 65, 132, 20);
		contentPane.add(txtCategory);
		
		btncategory = new JButton("Search By Category");
		btncategory.setBounds(790, 64, 146, 23);
		contentPane.add(btncategory);
		
		btnVAll = new JButton("View All Books");
		btnVAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Book> bList = bDB.getAll();
					tblModel.setRowCount(0);
					for (Book b : bList) {
						int ID = b.getBookID();
						String name =b.getName();
						String isbn =b.getIsbn();
						String author=b.getAuthor();
						Date date=b.getDate();
						String price="Rs."+b.getPrice()+".00/-";
								

						tblModel.addRow(new Object[] { ID,name,isbn,author,date,price});
						btnVAll.setEnabled(false);
					}
				}
				catch(Exception ex) {
					System.err.println(ex.getMessage());
					
				}
			}
		});
		btnVAll.setEnabled(false);
		btnVAll.setBounds(425, 115, 132, 34);
		contentPane.add(btnVAll);

		tblModel.addColumn("ID");
		tblModel.addColumn("Name");
		tblModel.addColumn("ISBN");
		tblModel.addColumn("Author");
		tblModel.addColumn("Date");
		tblModel.addColumn("Price");
		
				
				try {
					ArrayList<Book> bList = bDB.getAll();
					tblModel.setRowCount(0);
					for (Book b : bList) {
						int ID = b.getBookID();
						String name =b.getName();
						String isbn =b.getIsbn();
						String author=b.getAuthor();
						Date date=b.getDate();
						String price="Rs."+b.getPrice()+".00/-";
								

						tblModel.addRow(new Object[] { ID,name,isbn,author,date,price});
					}
				}
				catch(Exception ex) {
					System.err.println(ex.getMessage());
					
				}
		
			}
}
