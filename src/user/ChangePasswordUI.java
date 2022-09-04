package user;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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
	private JTextField txtOPW;

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
		setTitle("Change Password");
		setResizable(false);
		setBounds(100, 100, 391, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		uDB  = new UserDB();
		
		JLabel lblNewLabel = new JLabel("New Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(55, 108, 106, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUserId.setBounds(55, 36, 106, 14);
		contentPane.add(lblUserId);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblConfirmPassword.setBounds(55, 142, 106, 14);
		contentPane.add(lblConfirmPassword);
		
		txtUID = new JTextField();
		txtUID.setBounds(188, 34, 121, 20);
		contentPane.add(txtUID);
		txtUID.setColumns(10);
		
		txtPW = new JPasswordField();
		txtPW.setBounds(188, 106, 121, 20);
		contentPane.add(txtPW);
		
		txtCPW = new JPasswordField();
		txtCPW.setBounds(188, 140, 121, 20);
		contentPane.add(txtCPW);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (checkValid()) {
				String name="";
				String address="";
				String num="";
				String un = "";
				String type = "";
				int uid =Integer.valueOf(txtUID.getText());	
				String password =txtPW.getText();
				String cpassword= txtCPW.getText();
				String oldpassword = txtOPW.getText();
				
		
				User user =uDB.get(uid);
				
				if(password.equals(cpassword)) {
					String newHashPW =pwdHash.getMd5(password);
					String oldHashPW =pwdHash.getMd5(oldpassword);
					
					System.out.println(user.getPassword());
					System.out.println(user.getUserID());
					System.out.println(oldpassword);
					System.out.println(uid);

						if(user.getPassword().equals(oldHashPW) && Integer.valueOf(user.getUserID()).equals(uid)) {
							User userPW =new User(uid,name,address,num,un,newHashPW,type);
							int result=uDB.updatepw(userPW);
							if(result>0) {
								 JOptionPane.showMessageDialog(null,"Password is updated","Alert",JOptionPane.WARNING_MESSAGE);   
								txtOPW.setText("");
								txtUID.setText("");
								txtPW.setText("");
								txtCPW.setText("");
							}else {
								 JOptionPane.showMessageDialog(null,"Password is not updated","Alert",JOptionPane.WARNING_MESSAGE); 
							}	
							
						}else {
							 JOptionPane.showMessageDialog(null,"Username and Old password dose not match","Alert",JOptionPane.WARNING_MESSAGE);
						}	

				}else {
					JOptionPane.showMessageDialog(null, "The passwords dose not match","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
			}
		});
		btnUpdate.setBounds(55, 197, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(220, 197, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOldPassword.setBounds(55, 73, 106, 14);
		contentPane.add(lblOldPassword);
		
		txtOPW = new JTextField();
		txtOPW.setColumns(10);
		txtOPW.setBounds(188, 65, 121, 20);
		contentPane.add(txtOPW);
	}
	private boolean checkValid() {
		if (txtUID.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "User ID cannot be blank");
			return false;
		}
	
		try {
			int id = Integer.valueOf(txtUID.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "User ID must be numeric");
			return false;
		}
		if (txtOPW.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Old password cannot be blank");
			return false;
		}

		if (txtPW.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Password cannot be blank");
			return false;
		}
		
		if (txtCPW.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "confirm password cannot be blank");
			return false;
		}
		

		return true;
	}
}
