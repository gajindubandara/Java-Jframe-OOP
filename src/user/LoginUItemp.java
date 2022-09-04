package user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import user.LoginCUI;

public class LoginUItemp extends JFrame {

	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUItemp frame = new LoginUItemp();
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
	public LoginUItemp() {
		setTitle("City Bookshop");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 232);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoginAsA = new JButton("Login as a Manager");
		btnLoginAsA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLoginAsA.setBounds(21, 92, 147, 52);
		contentPane.add(btnLoginAsA);
		
		JButton btnNewButton_1_1 = new JButton("Login as a Cashier");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginCUI lc =new LoginCUI();
				lc.setVisible(true); 
				setVisible(false);
			}
		});
		btnNewButton_1_1.setBounds(178, 92, 147, 52);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel = new JLabel("Welcome !");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(119, 11, 113, 48);
		contentPane.add(lblNewLabel);
		
		JLabel txtCon = new JLabel("");
		txtCon.setBounds(188, 179, 161, 14);
		contentPane.add(txtCon);
		
	}
}
