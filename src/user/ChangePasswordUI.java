package user;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.User;
import data.UserDB;
import data.pwdHash;

public class ChangePasswordUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUID;
	private JPasswordField txtPW;
	private JPasswordField txtCPW;
	private UserDB uDB;
	private JPasswordField txtOPW;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordUI frame = new ChangePasswordUI();
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
	public ChangePasswordUI() {
		setTitle("City Bookshop - Change Password");
		setResizable(false);
		setBounds(100, 100, 391, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		uDB = new UserDB();

		JLabel lblNewLabel = new JLabel("New Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(42, 106, 106, 14);
		contentPane.add(lblNewLabel);

		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUserId.setBounds(42, 34, 106, 14);
		contentPane.add(lblUserId);

		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConfirmPassword.setBounds(42, 140, 123, 14);
		contentPane.add(lblConfirmPassword);

		txtUID = new JTextField();
		txtUID.setBounds(188, 34, 142, 20);
		contentPane.add(txtUID);
		txtUID.setColumns(10);

		txtPW = new JPasswordField();
		txtPW.setBounds(188, 104, 142, 20);
		contentPane.add(txtPW);

		txtCPW = new JPasswordField();
		txtCPW.setBounds(188, 138, 142, 20);
		contentPane.add(txtCPW);

		JButton btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Validating the text fields
				if (checkValid()) {
					String name = "";
					String address = "";
					String num = "";
					String un = "";
					String type = "";
					int uid = Integer.valueOf(txtUID.getText());
					String password = txtPW.getText();
					String cpassword = txtCPW.getText();
					String oldpassword = txtOPW.getText();

					User user = uDB.getUser(uid);

					// Check if the password matches the confirm password
					if (password.equals(cpassword)) {
						// Hashing the entered passwords
						String newHashPW = pwdHash.getMd5(password);
						String oldHashPW = pwdHash.getMd5(oldpassword);

						// Check if the entered old password matches the password from the database
						if (user.getPassword().equals(oldHashPW) && Integer.valueOf(user.getUserID()).equals(uid)) {
							User userPW = new User(uid, name, address, num, un, newHashPW, type);
							int result = uDB.updateUserPw(userPW);
							if (result > 0) {
								JOptionPane.showMessageDialog(null, "Password is updated", "Alert",
										JOptionPane.INFORMATION_MESSAGE);
								txtOPW.setText("");
								txtUID.setText("");
								txtPW.setText("");
								txtCPW.setText("");
							} else {
								JOptionPane.showMessageDialog(null, "Password is not updated", "Alert",
										JOptionPane.ERROR_MESSAGE);
							}

						} else {
							JOptionPane.showMessageDialog(null, "User ID and current password dose not match", "Alert",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null, "The new password & confirm password does not match",
								"Alert", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnOk.setBounds(42, 197, 120, 40);
		contentPane.add(btnOk);
		Image up = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		btnOk.setIcon(new ImageIcon(up));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(210, 197, 120, 40);
		contentPane.add(btnCancel);
		Image cancel = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(cancel));

		JLabel lblOldPassword = new JLabel("Current Password");
		lblOldPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOldPassword.setBounds(42, 71, 123, 14);
		contentPane.add(lblOldPassword);

		txtOPW = new JPasswordField();
		txtOPW.setBounds(188, 69, 142, 20);
		contentPane.add(txtOPW);

		JLabel bgCp = new JLabel("");
		bgCp.setBounds(0, 0, 375, 256);
		contentPane.add(bgCp);
		Image bgCpw = new ImageIcon(this.getClass().getResource("/bgCp.jpg")).getImage();
		bgCp.setIcon(new ImageIcon(bgCpw));

	}

	private boolean checkValid() {
		if (txtUID.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "User ID cannot be blank");
			return false;
		}

		try {
			int id = Integer.valueOf(txtUID.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "User ID must be numeric", "Alert", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (txtOPW.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Current password cannot be blank");
			return false;
		}

		if (txtPW.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Password cannot be blank");
			return false;
		}

		if (txtCPW.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Confirm password cannot be blank");
			return false;
		}

		return true;
	}
}
