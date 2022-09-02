package user;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.Login;
import data.LoginDB;

public class CategoryUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUID;
	private JButton btnAdd; 
	private LoginDB logDB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryUI frame = new CategoryUI();
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
	public CategoryUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 232);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		logDB = new LoginDB();
		
		JLabel lblNewLabel = new JLabel("Category name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(47, 70, 101, 23);
		contentPane.add(lblNewLabel);
		
		txtUID = new JTextField();
		txtUID.setBounds(160, 73, 129, 20);
		contentPane.add(txtUID);
		txtUID.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
//				String userID=txtUID.getText();
//				String password=txtPWRD.getText();
//				Login log =logDB.get(userID); 
//				
//				if(log!=null&& password.equals(log.getPassword())) {
//					LoginStatus.userID =log.getUserID();
//					MainUI mUI=new MainUI();
//					mUI.setVisible(true); 
//					setVisible(false);
//				}else {
//					JOptionPane.showMessageDialog(null, "Incorrect User ID or Password");
//				}
			}
		});
		btnAdd.setBounds(61, 138, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(181, 138, 89, 23);
		contentPane.add(btnCancel);
	}
}
