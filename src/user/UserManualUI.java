package user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;

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
import javax.swing.JTextArea;
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
import java.awt.TextArea;
import java.awt.ScrollPane;
import java.awt.Dimension;
import java.awt.Cursor;

public class UserManualUI extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tblModel;
	private UserDB uDB;
	private JTextField txtPrice;
	private JButton btnPrice;
	private JTextField txtCategory;
	private JButton btncategory;
	private JLabel lblNewLabel;
	private JLabel bgView;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManualUI frame = new UserManualUI();
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
	public UserManualUI() {
		setResizable(false);
		setTitle("City Bookshop - User Manual");
		setBounds(100, 100, 1024, 638);
		
		UserDB uDB = new UserDB();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tblModel = new DefaultTableModel();
		Image all = new ImageIcon(this.getClass().getResource("/viewA.png")).getImage();
		Image imgName = new ImageIcon(this.getClass().getResource("/name.png")).getImage();
		
		lblNewLabel = new JLabel("User Manual");
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
		btnCancel.setBounds(824, 548, 159, 40);
		contentPane.add(btnCancel);
		Image cancel = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(cancel));
		
		TextArea textArea = new TextArea();
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textArea.setEditable(false);
		textArea.setFont(new Font("Tahoma", Font.BOLD, 13));
		textArea.setText(
						"This is the application's user guide."+"\r\n"+
						""+ "\r\n"+
						"To view the manual for each process, use the buttons on the left.");
		textArea.setBounds(243, 89, 731, 438);
		contentPane.add(textArea);
		
		JButton btnMb = new JButton("Managing Books");
		btnMb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("manage books");
			}
		});
		btnMb.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMb.setBounds(23, 138, 168, 23);
		contentPane.add(btnMb);
		
		JButton btnVb = new JButton("View Books");
		btnVb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("view books");
			}
		});
		btnVb.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVb.setBounds(23, 170, 168, 23);
		contentPane.add(btnVb);
		
		JButton btnMa = new JButton("Manage Accounts");
		btnMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("manage accounts");
			}
		});
		btnMa.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMa.setBounds(23, 382, 168, 23);
		contentPane.add(btnMa);
		
		JButton btnVa = new JButton("View Accounts");
		btnVa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("view accounts");
			}
		});
		btnVa.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVa.setBounds(23, 416, 168, 23);
		contentPane.add(btnVa);
		
		JButton btnCp = new JButton("Change Password");
		btnCp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("chnage pw");
			}
		});
		btnCp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCp.setBounds(23, 309, 168, 23);
		contentPane.add(btnCp);
		
		JButton btnMc = new JButton("Manage Categories");
		btnMc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("manage cat");
			}
		});
		btnMc.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMc.setBounds(23, 238, 168, 23);
		contentPane.add(btnMc);
		
		
		bgView = new JLabel("Managing Books");
		bgView.setBounds(0, 0, 1008, 599);
		contentPane.add(bgView);
		Image bgV = new ImageIcon(this.getClass().getResource("/bgViewBA.jpg")).getImage();
		bgView.setIcon(new ImageIcon(bgV));
		
		
		
		
		
		

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
				
				if(LoginStatus.type.equals("Cashier")) {
					btnMa.setVisible(false);
					btnVa.setVisible(false);
				}
		
			}

}
