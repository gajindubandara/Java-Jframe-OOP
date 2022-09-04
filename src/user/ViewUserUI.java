package user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import business.User;
import data.UserDB;


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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ViewUserUI extends JFrame {

	private JPanel contentPane;
	private JTable booktbl;
	private DefaultTableModel tblModel;
	private JTextField txtName;
	private UserDB uDB;
	private JTextField txtPrice;
	private JButton btnPrice;
	private JTextField txtCategory;
	private JButton btncategory;
	private JButton btnViewAll;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUserUI frame = new ViewUserUI();
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
	public ViewUserUI() {
		setResizable(false);
		setTitle("City Bookshop - User List");
		setBounds(100, 100, 1024, 600);
		
		UserDB uDB = new UserDB();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 187, 960, 308);
		contentPane.add(scrollPane);
		
		booktbl = new JTable();
		booktbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		booktbl.setBounds(32, 121, 937, 514);
		scrollPane.setViewportView(booktbl);
		
		tblModel = new DefaultTableModel();
		booktbl.setModel(tblModel);
		
		txtName = new JTextField();
		txtName.setBounds(271, 70, 254, 34);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		btnViewAll = new JButton("View All Users");
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<User> bList = uDB.getAll();
					tblModel.setRowCount(0);
					for (User u : bList) {
						int id = u.getUserID();
						String name =u.getName();
						String address =u.getAddress();
						String num =u.getNumber();
						String email=u.getEmail();
						String type=u.getType();
						

						tblModel.addRow(new Object[] { id,name,address,num,email,type});
						 btnViewAll.setEnabled(false);
					}
				}
				catch(Exception ex) {
					System.err.println(ex.getMessage());
					
				}
			}
		});
		btnViewAll.setBounds(23, 516, 159, 34);
		contentPane.add(btnViewAll);
		Image all = new ImageIcon(this.getClass().getResource("/viewA.png")).getImage();
		btnViewAll.setIcon(new ImageIcon(all));
		

		
		JButton btnName = new JButton("Search By Name");
		btnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Bname=txtName.getText();
				ArrayList<User> UserList=uDB.getAll();
				tblModel.setRowCount(0);
				 for(User u:UserList) {
					 if( u.getName().equalsIgnoreCase(Bname)) {
						
								int id = u.getUserID();
								String name =u.getName();
								String address =u.getAddress();
								String num =u.getNumber();
								String email=u.getEmail();
								String type=u.getType();
								

							tblModel.addRow(new Object[] { id,name,address,num,email,type});
					
						
					 }
					   
				   }
				 txtName.setText("");
				 btnViewAll.setEnabled(true);
			}
		});
		btnName.setBounds(555, 70, 173, 34);
		contentPane.add(btnName);
		Image imgName = new ImageIcon(this.getClass().getResource("/name.png")).getImage();
		btnName.setIcon(new ImageIcon(imgName));
		
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
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(824, 516, 159, 34);
		contentPane.add(btnCancel);
		Image cancel = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(cancel));

		tblModel.addColumn("ID");
		tblModel.addColumn("Name");
		tblModel.addColumn("Address");
		tblModel.addColumn("Number");
		tblModel.addColumn("Email");
		tblModel.addColumn("Account Type");
		
				
				try {
					ArrayList<User> bList = uDB.getAll();
					tblModel.setRowCount(0);
					for (User u : bList) {
						int id = u.getUserID();
						String name =u.getName();
						String address =u.getAddress();
						String num =u.getNumber();
						String email=u.getEmail();
						String type=u.getType();
						

						tblModel.addRow(new Object[] { id,name,address,num,email,type});
					}
				}
				catch(Exception ex) {
					System.err.println(ex.getMessage());
					
				}
		
			}
	
	private boolean checkValid() {
		try {
			int Price = Integer.valueOf(txtPrice.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Price must be numeric","Alert",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
}
