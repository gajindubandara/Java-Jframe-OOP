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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

import business.User;
import data.UserDB;
import data.pwdHash;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;


public class ManageAccountUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtAddress;
	private JTextField txtNum;
	private JTextField txtEm;
	protected JComboBox cMajor;
	protected JLabel GetAllText;
	private DefaultTableModel tblModel;
	private JTextField txtName;
	
	private UserDB uDB;
	private JPasswordField txtPW;
	private JPasswordField txtCPW;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageAccountUI frame = new ManageAccountUI();
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
	public ManageAccountUI() {
		setTitle("Manage Accounts");
		setResizable(false);
		setBounds(100, 100, 450, 416);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		UserDB uDB = new UserDB();

		JLabel lblNewLabel = new JLabel("Address");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(49, 145, 71, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(49, 115, 71, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Contact Number");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(49, 175, 94, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(49, 235, 71, 14);
		contentPane.add(lblNewLabel_1_1_1);

		txtID = new JTextField();
		txtID.setBounds(153, 109, 223, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(153, 139, 223, 20);
		contentPane.add(txtAddress);

		txtNum = new JTextField();
		txtNum.setColumns(10);
		txtNum.setBounds(153, 169, 223, 20);
		contentPane.add(txtNum);


		
		
		JRadioButton rCashier = new JRadioButton("Cashier");
		rCashier.setSelected(true);
		rCashier.setBounds(153, 48, 85, 23);
		contentPane.add(rCashier);

		JRadioButton rManager = new JRadioButton("Manager");
		rManager.setBounds(254, 48, 94, 23);
		contentPane.add(rManager);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rCashier);
		bg.add(rManager);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Email");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(49, 205, 94, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		txtEm = new JTextField();
		txtEm.setColumns(10);
		txtEm.setBounds(153, 199, 223, 20);
		contentPane.add(txtEm);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(153, 78, 223, 20);
		contentPane.add(txtName);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(49, 84, 71, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Confirm Password");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1_1_2.setBounds(49, 265, 103, 14);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		txtPW = new JPasswordField();
		txtPW.setBounds(153, 233, 223, 20);
		contentPane.add(txtPW);
		
		txtCPW = new JPasswordField();
		txtCPW.setBounds(153, 263, 223, 20);
		contentPane.add(txtCPW);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Account Type");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_2_1.setBounds(49, 52, 94, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkValid()) {
					int id=Integer.valueOf(txtID.getText());
					String type ="";
					if (rCashier.isSelected()) {
						type = "Cashier";
					} else {
						type = "Manager";
					}
					String name = txtName.getText();
					String address= txtAddress.getText();
					String num = txtNum.getText();
					String em = txtEm.getText();
					String pw = txtPW.getText();
					String cpw= txtCPW.getText();
					
					String hashPW =pwdHash.getMd5(pw);

					if(pw.equals(cpw)){
						User user = new User(id,name,address,num,em,hashPW,type);
						int result = uDB.add(user);

						if (result == 1) {
							JOptionPane.showMessageDialog(null, "New user account created");
							txtID.setText("");
							txtName.setText("");
							txtAddress.setText("");
							txtNum.setText("");
							txtEm.setText("");
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
				int result = uDB.delete(id);
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
					String type ="";
					if (rCashier.isSelected()) {
						type = "Cashier";
					} else {
						type = "Manager";
					}
					int id=Integer.valueOf(txtID.getText());
					String name = txtName.getText();
					String address = txtAddress.getText();
					String num = txtNum.getText();
					String em =txtEm.getText();
					String pw =txtPW.getText();


					User c = new User(id,name,address,num,em,pw,type);
					int result = uDB.update(c);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "The User is updated");
						txtID.setText("");
						txtName.setText("");
						txtAddress.setText("");
						txtNum.setText("");
						txtEm.setText("");
						txtPW.setText("");
						txtCPW.setText("");
						txtPW.setEnabled(true);
						txtID.setEnabled(true);
						txtCPW.setEnabled(true);
						rCashier.setSelected(true);
						rManager.setEnabled(true);
						rCashier.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "The User is not updated");
					}
				}

			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(217, 310, 85, 21);
		contentPane.add(btnUpdate);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf(JOptionPane.showInputDialog("Enter the User ID"));
				User user = uDB.get(id);
				if (user != null) {
					if (user.getType().equals("Cashier")) {
						rCashier.setSelected(true);

					} else {
						rManager.setSelected(true);
					}
					rCashier.setEnabled(false);
					rManager.setEnabled(false);
					txtName.setText(user.getName());
					txtID.setText(String.valueOf(user.getUserID()));
					txtID.setEnabled(false);
					txtAddress.setText(user.getAddress());
					txtNum.setText(user.getNumber());
					txtEm.setText(user.getEmail());
					txtPW.setText(user.getPassword());
					txtPW.setEnabled(false);
					txtCPW.setText(user.getPassword());
					txtCPW.setEnabled(false);
					
				}else {
					JOptionPane.showMessageDialog(null, "No User account for this ID number");
				}
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFind.setBounds(174, 345, 85, 21);
		contentPane.add(btnFind);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.setBounds(312, 310, 85, 21);
		contentPane.add(btnCancel);

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
		if (txtEm.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Username cannot be blank");
			return false;
		}

		if (txtPW.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Password cannot be blank");
			return false;
		}
		if (txtCPW.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Confirm the password");
			return false;
		}

		return true;
	}
}
