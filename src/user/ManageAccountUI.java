package user;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.User;
import data.UserDB;
import data.pwdHash;

public class ManageAccountUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtAddress;
	private JTextField txtNum;
	private JTextField txtEm;
	protected JLabel GetAllText;
	private JTextField txtName;

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
		setTitle("City Bookshop - Manage Accounts");
		setResizable(false);
		setBounds(100, 100, 472, 472);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		UserDB uDB = new UserDB();

		JLabel lblNewLabel = new JLabel("Address");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(55, 145, 71, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(55, 115, 71, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Contact Number");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(55, 175, 103, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(55, 235, 71, 14);
		contentPane.add(lblNewLabel_1_1_1);

		txtID = new JTextField();
		txtID.setBounds(179, 109, 223, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(179, 140, 223, 20);
		contentPane.add(txtAddress);

		txtNum = new JTextField();
		txtNum.setColumns(10);
		txtNum.setBounds(179, 171, 223, 20);
		contentPane.add(txtNum);

		JRadioButton rCashier = new JRadioButton("Cashier");
		rCashier.setSelected(true);
		rCashier.setBounds(179, 48, 85, 23);
		contentPane.add(rCashier);

		JRadioButton rManager = new JRadioButton("Manager");
		rManager.setBounds(280, 48, 94, 23);
		contentPane.add(rManager);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rCashier);
		bg.add(rManager);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Email");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_1.setBounds(55, 205, 94, 14);
		contentPane.add(lblNewLabel_1_1_1_1);

		txtEm = new JTextField();
		txtEm.setColumns(10);
		txtEm.setBounds(179, 199, 223, 20);
		contentPane.add(txtEm);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(179, 78, 223, 20);
		contentPane.add(txtName);

		JLabel lblNewLabel_1_2 = new JLabel("Name");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(55, 84, 71, 14);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Confirm Password");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1_2.setBounds(55, 265, 118, 14);
		contentPane.add(lblNewLabel_1_1_1_2);

		txtPW = new JPasswordField();
		txtPW.setBounds(179, 233, 223, 20);
		contentPane.add(txtPW);

		txtCPW = new JPasswordField();
		txtCPW.setBounds(179, 263, 223, 20);
		contentPane.add(txtCPW);

		JLabel lblNewLabel_1_2_1 = new JLabel("Account Type");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2_1.setBounds(55, 52, 94, 14);
		contentPane.add(lblNewLabel_1_2_1);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Validating the text fields
				if (checkValid()) {
					int id = Integer.valueOf(txtID.getText());
					String type = "";
					if (rCashier.isSelected()) {
						type = "Cashier";
					} else {
						type = "Manager";
					}
					String name = txtName.getText();
					String address = txtAddress.getText();
					String num = txtNum.getText();
					String em = txtEm.getText();
					String pw = txtPW.getText();
					String cpw = txtCPW.getText();

					// Hashing the entered password
					String hashPW = pwdHash.getMd5(pw);

					// Checking the password matches the confirm password
					if (pw.equals(cpw)) {
						User user = new User(id, name, address, num, em, hashPW, type);

						// Adding user to the database
						int result = uDB.addUser(user);
						if (result == 1) {
							JOptionPane.showMessageDialog(null, "New user account created", "Alert",
									JOptionPane.INFORMATION_MESSAGE);
							txtID.setText("");
							txtName.setText("");
							txtAddress.setText("");
							txtNum.setText("");
							txtEm.setText("");
							txtPW.setText("");
							txtCPW.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "The account has not been created", "Alert",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "The passwords dose not match", "Alert",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(44, 310, 168, 40);
		contentPane.add(btnAdd);
		Image add = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnAdd.setIcon(new ImageIcon(add));

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting the user Id from a JOptionPane Input dialog
					int id = Integer.valueOf(JOptionPane.showInputDialog("Enter the User ID"));
					JFrame f = new JFrame();
					int a = JOptionPane.showConfirmDialog(f, "Are you sure you want to contine?");

					if (a == JOptionPane.YES_OPTION) {
						// Deleting the user
						int result = uDB.deleteUser(id);
						if (result == 1) {
							JOptionPane.showMessageDialog(null, "The User is deleted", "Alert",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null,
									"No user found for the entered ID.Please check the ID Again!", "Alert",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "User ID cannot be null", "Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(44, 366, 168, 40);
		contentPane.add(btnDelete);
		Image del = new ImageIcon(this.getClass().getResource("/del.png")).getImage();
		btnDelete.setIcon(new ImageIcon(del));

		JButton btnUF = new JButton("Find & Update");
		JButton btnU = new JButton("Update");
		btnUF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Getting the user Id from a JOptionPane Input dialog
					int id = Integer.valueOf(JOptionPane.showInputDialog("Enter the User ID"));

					// Getting the user form the database
					User user = uDB.getUser(id);
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
						btnUF.setVisible(false);
						btnU.setVisible(true);
						btnAdd.setEnabled(false);
						btnDelete.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "No User account for this ID number", "Alert",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "User ID cannot be null", "Alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUF.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUF.setBounds(242, 310, 168, 40);
		contentPane.add(btnUF);
		Image find = new ImageIcon(this.getClass().getResource("/find.png")).getImage();
		btnUF.setIcon(new ImageIcon(find));

		btnU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Validating the text fields
				if (checkValid()) {
					String type = "";
					if (rCashier.isSelected()) {
						type = "Cashier";
					} else {
						type = "Manager";
					}
					int id = Integer.valueOf(txtID.getText());
					String name = txtName.getText();
					String address = txtAddress.getText();
					String num = txtNum.getText();
					String em = txtEm.getText();
					String pw = txtPW.getText();

					User c = new User(id, name, address, num, em, pw, type);

					// Updating the user
					int result = uDB.updateUser(c);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "The User is updated", "Alert",
								JOptionPane.INFORMATION_MESSAGE);
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
						btnU.setVisible(false);
						btnUF.setVisible(true);
						btnAdd.setEnabled(true);
						btnDelete.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "The User is not updated", "Alert",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnU.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnU.setBounds(242, 310, 168, 40);
		contentPane.add(btnU);
		Image up = new ImageIcon(this.getClass().getResource("/update.png")).getImage();
		btnU.setIcon(new ImageIcon(up));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBounds(243, 366, 168, 40);
		contentPane.add(btnCancel);
		Image cancel = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(cancel));

		JLabel bgMa = new JLabel("");
		bgMa.setBounds(0, 0, 456, 433);
		contentPane.add(bgMa);
		Image bgMAccount = new ImageIcon(this.getClass().getResource("/bgMa.jpg")).getImage();
		bgMa.setIcon(new ImageIcon(bgMAccount));

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
			JOptionPane.showMessageDialog(this, "User ID must be numeric", "Alert", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (txtAddress.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Address cannot be blank");
			return false;
		}

		if (txtNum.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Contact Number cannot be blank");
			return false;
		}
		try {
			int id = Integer.valueOf(txtNum.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Contact Number must be numeric", "Alert", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (txtEm.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Email cannot be blank");
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
