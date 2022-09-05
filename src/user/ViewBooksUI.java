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
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.InputEvent;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ViewBooksUI extends JFrame {

	private JPanel contentPane;
	private JTable booktbl;
	private DefaultTableModel tblModel;
	private JTextField txtSearch;
	private BookDB bDB;
	private JButton btnPrice;
	private JButton btncategory;
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
		setTitle("City Bookshop - Book List");
		setBounds(100, 100, 1024, 638);
		
		BookDB bDB = new BookDB();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 209, 960, 324);
		contentPane.add(scrollPane);
		
		booktbl = new JTable();
		booktbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		booktbl.setBounds(32, 121, 937, 514);
		scrollPane.setViewportView(booktbl);
		
		tblModel = new DefaultTableModel();
		booktbl.setModel(tblModel);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSearch.setBounds(329, 89, 349, 27);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		btnViewAll = new JButton("View All Books");
		btnViewAll.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewAll.addActionListener(new ActionListener() {
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
						btnViewAll.setEnabled(false);
					}
				}
				catch(Exception ex) {
					System.err.println(ex.getMessage());
					
				}
			}
		});
		btnViewAll.setBounds(23, 549, 159, 40);
		contentPane.add(btnViewAll);
		Image all = new ImageIcon(this.getClass().getResource("/viewB.png")).getImage();
		btnViewAll.setIcon(new ImageIcon(all));

		
		JButton btnName = new JButton("Search By Name");
		btnName.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Bname=txtSearch.getText();
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
						
					 }
					   
				   }
				 txtSearch.setText("");
				 btnViewAll.setEnabled(true);
			}
		});
		btnName.setBounds(179, 138, 192, 34);
		contentPane.add(btnName);
		Image imgName = new ImageIcon(this.getClass().getResource("/name.png")).getImage();
		btnName.setIcon(new ImageIcon(imgName));
		
		btnPrice = new JButton("Search By Price");
		btnPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Price=txtSearch.getText();
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
					 }					
				   }
				 txtSearch.setText("");
				 btnViewAll.setEnabled(true);
				
			}
		});
		btnPrice.setBounds(407, 138, 192, 34);
		contentPane.add(btnPrice);
		Image imgPrice = new ImageIcon(this.getClass().getResource("/price.png")).getImage();
		btnPrice.setIcon(new ImageIcon(imgPrice));
		
		btncategory = new JButton("Search By Category");
		btncategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		btncategory.setBounds(635, 138, 192, 34);
		contentPane.add(btncategory);
		Image cat = new ImageIcon(this.getClass().getResource("/category.png")).getImage();
		btncategory.setIcon(new ImageIcon(cat));
		
		
		lblNewLabel = new JLabel("Book List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(23, 11, 960, 43);
		contentPane.add(lblNewLabel);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(824, 549, 159, 40);
		contentPane.add(btnCancel);
		Image cancel = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(cancel));

		
		bgView = new JLabel("");
		bgView.setBounds(0, 0, 1008, 599);
		contentPane.add(bgView);
		Image bgV = new ImageIcon(this.getClass().getResource("/bgViewBA.jpg")).getImage();
		bgView.setIcon(new ImageIcon(bgV));
		
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
