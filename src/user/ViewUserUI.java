package user;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import business.User;
import data.UserDB;

public class ViewUserUI extends JFrame {

	private JPanel contentPane;
	private JTable booktbl;
	private DefaultTableModel tblModel;
	private JTextField txtName;
	private JButton btnViewAll;
	private JLabel lblNewLabel;
	private JLabel bgView;

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
		setBounds(100, 100, 1024, 638);

		UserDB uDB = new UserDB();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 187, 960, 343);
		contentPane.add(scrollPane);

		booktbl = new JTable();
		booktbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		booktbl.setBounds(32, 121, 937, 514);
		scrollPane.setViewportView(booktbl);

		tblModel = new DefaultTableModel();
		booktbl.setModel(tblModel);

		txtName = new JTextField();
		txtName.setBounds(325, 76, 349, 27);
		contentPane.add(txtName);
		txtName.setColumns(10);

		btnViewAll = new JButton("View All Users");
		btnViewAll.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Getting all the books and display them in a table
				try {
					ArrayList<User> bList = uDB.getAll();
					tblModel.setRowCount(0);
					for (User u : bList) {
						int id = u.getUserID();
						String name = u.getName();
						String address = u.getAddress();
						String num = u.getNumber();
						String email = u.getEmail();
						String type = u.getType();

						tblModel.addRow(new Object[] { id, name, address, num, email, type });
						btnViewAll.setEnabled(false);
					}
				} catch (Exception ex) {
					System.err.println(ex.getMessage());
				}
			}
		});
		btnViewAll.setBounds(23, 548, 159, 40);
		contentPane.add(btnViewAll);
		Image all = new ImageIcon(this.getClass().getResource("/viewA.png")).getImage();
		btnViewAll.setIcon(new ImageIcon(all));

		JButton btnName = new JButton("Search By Name");
		btnName.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Bname = txtName.getText();
				ArrayList<User> UserList = uDB.getAll();
				tblModel.setRowCount(0);
				for (User u : UserList) {

					// Getting the user with the entered name
					if (u.getName().equalsIgnoreCase(Bname)) {
						int id = u.getUserID();
						String name = u.getName();
						String address = u.getAddress();
						String num = u.getNumber();
						String email = u.getEmail();
						String type = u.getType();

						// Display the user in the table
						tblModel.addRow(new Object[] { id, name, address, num, email, type });
					}
				}
				// Check the if the result is available
				if (tblModel.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "No user found");

					// Getting all the books and display them in a table
					try {
						ArrayList<User> bList = uDB.getAll();
						tblModel.setRowCount(0);
						for (User u : bList) {
							int id = u.getUserID();
							String name = u.getName();
							String address = u.getAddress();
							String num = u.getNumber();
							String email = u.getEmail();
							String type = u.getType();

							tblModel.addRow(new Object[] { id, name, address, num, email, type });
						}
					} catch (Exception ex) {
						System.err.println(ex.getMessage());
					}
				}
				txtName.setText("");
				btnViewAll.setEnabled(true);
			}
		});
		btnName.setBounds(410, 114, 192, 34);
		contentPane.add(btnName);
		Image imgName = new ImageIcon(this.getClass().getResource("/name.png")).getImage();
		btnName.setIcon(new ImageIcon(imgName));

		lblNewLabel = new JLabel("User Accounts");
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

		bgView = new JLabel("");
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

		// Getting all the books and display them in a table
		try {
			ArrayList<User> bList = uDB.getAll();
			tblModel.setRowCount(0);
			for (User u : bList) {
				int id = u.getUserID();
				String name = u.getName();
				String address = u.getAddress();
				String num = u.getNumber();
				String email = u.getEmail();
				String type = u.getType();

				tblModel.addRow(new Object[] { id, name, address, num, email, type });
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
