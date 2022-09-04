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

import business.Cashier;
import data.CashierDB;
import javax.swing.JPasswordField;


public class NewCashierAccountUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtAddress;
	private JTextField txtNum;
	private JTextField txtUN;
	protected JComboBox cMajor;
	protected JLabel GetAllText;
	private DefaultTableModel tblModel;
	private JTextField txtName;
	
	private CashierDB cDB;
	private JPasswordField txtPW;
	private JPasswordField txtCPW;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCashierAccountUI frame = new NewCashierAccountUI();
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
	public NewCashierAccountUI() {
		setTitle("New Cashier Account");
		setResizable(false);
		setBounds(100, 100, 450, 416);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		CashierDB cDB = new CashierDB();

		JLabel lblNewLabel = new JLabel("Address");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(49, 107, 71, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(49, 77, 71, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Contact Number");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(49, 137, 94, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(49, 197, 71, 14);
		contentPane.add(lblNewLabel_1_1_1);

		txtID = new JTextField();
		txtID.setBounds(153, 71, 223, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(153, 101, 223, 20);
		contentPane.add(txtAddress);

		txtNum = new JTextField();
		txtNum.setColumns(10);
		txtNum.setBounds(153, 131, 223, 20);
		contentPane.add(txtNum);


		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkValid()) {
					int id=Integer.valueOf(txtID.getText());
					String name = txtName.getText();
					String address= txtAddress.getText();
					String num = txtNum.getText();
					String un = txtUN.getText();
					String pw = txtPW.getText();
					String cpw= txtCPW.getText();
					if(pw.equals(cpw)) {
						Cashier c = new Cashier(id,name,address,num,un,pw);
						int result = cDB.add(c);

						if (result == 1) {
							JOptionPane.showMessageDialog(null, "New user account created");
							txtID.setText("");
							txtName.setText("");
							txtAddress.setText("");
							txtNum.setText("");
							txtUN.setText("");
							txtPW.setText("");
							txtCPW.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "The account has not been created");
						}
					}else {
						JOptionPane.showMessageDialog(null, "The passwords dose not match");
					}

				}

			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(27, 310, 85, 21);
		contentPane.add(btnAdd);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf( JOptionPane.showInputDialog("Enter the User ID"));
				int result = cDB.delete(id);
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "The User is deleted");
				} else {
					JOptionPane.showMessageDialog(null, "The User is not deleted");
				}

			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(122, 310, 85, 21);
		contentPane.add(btnDelete);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkValid()) {
					int id=Integer.valueOf(txtID.getText());
					String name = txtName.getText();
					String address = txtAddress.getText();
					String num = txtNum.getText();
					String un =txtUN.getText();
					String pw =txtPW.getText();


					Cashier c = new Cashier(id,name,address,num,un,pw);
					int result = cDB.update(c);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "The User is updated");
						txtID.setText("");
						txtName.setText("");
						txtAddress.setText("");
						txtNum.setText("");
						txtUN.setText("");
						txtPW.setText("");
						txtCPW.setText("");
						txtPW.setEnabled(true);
						txtID.setEnabled(true);
						txtCPW.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "The User is not updated");
					}
				}

			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(217, 310, 85, 21);
		contentPane.add(btnUpdate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(312, 310, 85, 21);
		contentPane.add(btnCancel);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf(JOptionPane.showInputDialog("Enter the User ID"));
				Cashier c = cDB.get(id);
				if (c != null) {
					txtName.setText(c.getName());
					txtID.setText(String.valueOf(c.getCashierID()));
					txtID.setEnabled(false);
					txtAddress.setText(c.getAddress());
					txtNum.setText(c.getNumber());
					txtUN.setText(c.getUsername());
					txtPW.setText(c.getPassword());
					txtPW.setEnabled(false);
					txtCPW.setText(c.getPassword());
					txtCPW.setEnabled(false);
					
				}else {
					JOptionPane.showMessageDialog(null, "No User account for this ID number");
				}
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFind.setBounds(174, 345, 85, 21);
		contentPane.add(btnFind);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("UserName");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(49, 167, 94, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		txtUN = new JTextField();
		txtUN.setColumns(10);
		txtUN.setBounds(153, 161, 223, 20);
		contentPane.add(txtUN);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(153, 40, 223, 20);
		contentPane.add(txtName);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(49, 46, 71, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Confirm Password");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1_2.setBounds(49, 227, 103, 14);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		txtPW = new JPasswordField();
		txtPW.setBounds(153, 195, 223, 20);
		contentPane.add(txtPW);
		
		txtCPW = new JPasswordField();
		txtCPW.setBounds(153, 225, 223, 20);
		contentPane.add(txtCPW);

	}

	private boolean checkValid() {
		if (txtName.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "User name cannot be blank");
			return false;
		}
		if (txtID.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "User ID cannot be blank");
			return false;
		}
		try {
			int id = Integer.valueOf(txtID.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "User ID must be numeric");
			return false;
		}
		if (txtAddress.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Address number cannot be blank");
			return false;
		}

		if (txtNum.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Contact Number cannot be blank");
			return false;
		}
		try {
			int id = Integer.valueOf(txtNum.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Contact Number must be numeric");
			return false;
		}
		if (txtUN.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Username cannot be blank");
			return false;
		}
//		try {
//			Date date = Date.valueOf(txtUN.getText());
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(this, "Date of birth must be in YYYY-MM-DD format");
//			return false;
//		}

		if (txtPW.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Password cannot be blank");
			return false;
		}
		if (txtCPW.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Confirm the password");
			return false;
		}
//		if (txtPrice.getText().equals("")) {
//			JOptionPane.showMessageDialog(this, "Price cannot be blank");
//			return false;
//		}
		
//		try {
//			int price = Integer.valueOf(txtPrice.getText());
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(this, "Price must be numeric: Ex- 100");
//			return false;
//		}

		return true;
	}
}
