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
import java.util.HashMap;
import java.awt.event.InputEvent;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JComboBox;

public class BillUI extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tblModel;
	private BookDB bDB;
	private JLabel bgView;
	
	HashMap<String, String> product_and_price;
    
    DefaultListModel<String> product_name_bill, product_quantity_bill,product_price_bill;

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
		setBounds(100, 100, 715, 641);
		
		BookDB bDB = new BookDB();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		  
//		
//		bgView = new JLabel("");
//		bgView.setBounds(0, 0, 1008, 599);
//		contentPane.add(bgView);
//		Image bgV = new ImageIcon(this.getClass().getResource("/bgViewBA.jpg")).getImage();
//		bgView.setIcon(new ImageIcon(bgV));
		
		JList product_bill = new JList();
		product_bill.setBounds(54, 220, 175, 297);
		contentPane.add(product_bill);
		
		JLabel lblNewLabel = new JLabel("Item");
		lblNewLabel.setBounds(54, 205, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(252, 205, 46, 14);
		contentPane.add(lblQty);
		
		JList product_quantity = new JList();
		product_quantity.setBounds(252, 220, 175, 297);
		contentPane.add(product_quantity);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cost");
		lblNewLabel_1_1.setBounds(452, 205, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JList product_price = new JList();
		product_price.setBounds(452, 220, 175, 297);
		contentPane.add(product_price);
		
		JLabel lblNewLabel_1 = new JLabel("Total:");
		lblNewLabel_1.setBounds(310, 550, 46, 14);
		contentPane.add(lblNewLabel_1);
		

		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setBounds(287, 41, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel price_per_unit_label = new JLabel("Rs.");
		price_per_unit_label.setBounds(287, 66, 69, 14);
		contentPane.add(price_per_unit_label);
		
		JLabel lblNewLabel_2_1 = new JLabel("Product name");
		lblNewLabel_2_1.setBounds(54, 41, 105, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Quantity");
		lblNewLabel_2_1_1.setBounds(503, 41, 46, 14);
		contentPane.add(lblNewLabel_2_1_1);
		
		JComboBox product_list = new JComboBox();
		product_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price_per_unit_label.setText(product_and_price.get(product_list.getSelectedItem()));
			}
		});
		product_list.setBounds(54, 66, 132, 22);
		contentPane.add(product_list);
		
		JComboBox quantity_list = new JComboBox();
		quantity_list.setBounds(459, 66, 132, 22);
		contentPane.add(quantity_list);
		
		JLabel total_amount = new JLabel("amount");
		total_amount.setBounds(427, 550, 71, 14);
		contentPane.add(total_amount);
		
//		tblModel.addColumn("ID");
//		tblModel.addColumn("Name");
//		tblModel.addColumn("ISBN");
//		tblModel.addColumn("Author");
//		tblModel.addColumn("Date");
//		tblModel.addColumn("Price");
		

		  product_and_price = new HashMap<String, String>();

		  
		  
		  try {
				ArrayList<Book> bList = bDB.getAll();
//				tblModel.setRowCount(0);
				for (Book b : bList) {
					int ID = b.getBookID();
					String name =b.getName();
					String isbn =b.getIsbn();
					String author=b.getAuthor();
					Date date=b.getDate();
					String price=b.getPrice();
							
	
//					tblModel.addRow(new Object[] { ID,name,isbn,author,date,price});
					 product_and_price.put(name, price);
				}
			}
			catch(Exception ex) {
				System.err.println(ex.getMessage());
				
			}
		  
		  
		  
		  product_and_price.forEach((key,value)->
	        {
	            product_list.addItem(key);
	        });
		  for(int i=1; i<=10; i++)
	        {
	            quantity_list.addItem(Integer.toString(i));
	        }
		  
	
		  
			JButton add_product = new JButton("Add");
			add_product.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 product_name_bill.addElement((String) product_list.getSelectedItem());
					 System.out.println(product_list.getSelectedItem());
			            product_quantity_bill.addElement((String) quantity_list.getSelectedItem());
			            int unit_price = Integer.parseInt(product_and_price.get(product_list.getSelectedItem()));
			            System.out.println(unit_price);
			            int product_quantity = Integer.parseInt((String) quantity_list.getSelectedItem());
			            int total_for_product = unit_price*product_quantity; 
			            int total_cost = 0;
			            product_price_bill.addElement(Integer.toString(total_for_product));
			            
			            for(int i=0;i<product_price_bill.getSize();i++)
			            {
			                total_cost += Integer.parseInt(product_price_bill.getElementAt(i));
			            }
			            
			            total_amount.setText(Integer.toString(total_cost));
				}
			});
			add_product.setBounds(299, 157, 89, 23);
			contentPane.add(add_product);
		  
		  
		  
		
			}
}
