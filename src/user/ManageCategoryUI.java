package user;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import business.Category;
import data.CategoryDB;

public class ManageCategoryUI extends JFrame {

	private JPanel contentPane;
	protected JComboBox cMajor;
	protected JLabel GetAllText;
	private DefaultTableModel tblModel;
	private JTextField txtCName;

	private CategoryDB cDB;
	private JTable tblCat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCategoryUI frame = new ManageCategoryUI();
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
	public ManageCategoryUI() {
		setTitle("City Bookshop - Manage Category");
		setResizable(false);
		setBounds(100, 100, 454, 454);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		CategoryDB cDB = new CategoryDB();

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkValid()) {
					int id = 0;
					String name = txtCName.getText();
					Category c = new Category(id, name);
					int result = cDB.addCategory(c);

					if (result == 1) {
						JOptionPane.showMessageDialog(null, "New Record is added", "Alert",
								JOptionPane.INFORMATION_MESSAGE);
						txtCName.setText("");
						try {
							ArrayList<Category> cList = cDB.getAll();
							tblModel.setRowCount(0);
							for (Category category : cList) {
								int cID = category.getCategoryId();
								String cName = category.getCategoryName();
								tblModel.addRow(new Object[] { cID, cName });
							}
						} catch (Exception ex) {
							System.err.println(ex.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(null, "New Record is not added", "Alert",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(49, 86, 168, 40);
		contentPane.add(btnAdd);
		Image add = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnAdd.setIcon(new ImageIcon(add));

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf(JOptionPane.showInputDialog("Enter the Book ID"));

				JFrame f = new JFrame();
				int a = JOptionPane.showConfirmDialog(f,
						"This category may include books. If you remove the category, the books will no longer have one."
								+ "Are you sure you want to delete it?");

				if (a == JOptionPane.YES_OPTION) {
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					int result = cDB.deleteCategory(id);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Category is deleted", "Alert",
								JOptionPane.INFORMATION_MESSAGE);
						try {
							ArrayList<Category> cList = cDB.getAll();
							tblModel.setRowCount(0);
							for (Category category : cList) {
								int cID = category.getCategoryId();
								String cName = category.getCategoryName();
								tblModel.addRow(new Object[] { cID, cName });
							}
						} catch (Exception ex) {
							System.err.println(ex.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Category is not deleted", "Alert",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(49, 148, 168, 40);
		contentPane.add(btnDelete);
		Image del = new ImageIcon(this.getClass().getResource("/del.png")).getImage();
		btnDelete.setIcon(new ImageIcon(del));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBounds(227, 148, 168, 40);
		contentPane.add(btnCancel);
		Image cancel = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(cancel));

		txtCName = new JTextField();
		txtCName.setColumns(10);
		txtCName.setBounds(168, 41, 207, 20);
		contentPane.add(txtCName);

		JLabel lblNewLabel_1_2 = new JLabel("Category Name");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(49, 46, 94, 14);
		contentPane.add(lblNewLabel_1_2);

		JLabel txtID = new JLabel("");
		txtID.setBounds(385, 44, 25, 14);
		contentPane.add(txtID);
		txtID.setVisible(false);

		JButton btnUF = new JButton("Find & Update");
		btnUF.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.valueOf(JOptionPane.showInputDialog("Enter the Book ID"));
				Category c = cDB.getCategory(id);
				if (c != null) {
					txtCName.setText(c.getCategoryName());
					txtID.setText(String.valueOf(c.getCategoryId()));
					btnUF.setVisible(false);

				} else {
					JOptionPane.showMessageDialog(null, "No book for this ID number", "Alert",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnUF.setBounds(227, 86, 168, 40);
		contentPane.add(btnUF);
		Image find = new ImageIcon(this.getClass().getResource("/find.png")).getImage();
		btnUF.setIcon(new ImageIcon(find));

		JButton btnU = new JButton("Update");
		btnU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkValid()) {
					int id = Integer.valueOf(txtID.getText());
					String name = txtCName.getText();
					Category c = new Category(id, name);
					int result = cDB.updateCategory(c);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "The Category is updated", "Alert",
								JOptionPane.INFORMATION_MESSAGE);
						txtCName.setText("");
						btnU.setVisible(false);
						btnUF.setVisible(true);
						try {
							ArrayList<Category> cList = cDB.getAll();
							tblModel.setRowCount(0);
							for (Category category : cList) {
								int cID = category.getCategoryId();
								String cName = category.getCategoryName();
								tblModel.addRow(new Object[] { cID, cName });
							}
						} catch (Exception ex) {
							System.err.println(ex.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(null, "The Category is not updated", "Alert",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnU.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnU.setBounds(227, 86, 168, 40);
		contentPane.add(btnU);
		Image up = new ImageIcon(this.getClass().getResource("/update.png")).getImage();
		btnU.setIcon(new ImageIcon(up));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 210, 340, 179);
		contentPane.add(scrollPane);

		tblCat = new JTable();
		tblCat.setBounds(49, 210, 340, 179);
		scrollPane.setViewportView(tblCat);

		tblModel = new DefaultTableModel();
		scrollPane.setViewportView(tblCat);
		tblCat.setModel(tblModel);

		tblModel.addColumn("ID");
		tblModel.addColumn("Category");

		JLabel bgMb = new JLabel("");
		bgMb.setBounds(0, 0, 438, 415);
		contentPane.add(bgMb);
		Image bg = new ImageIcon(this.getClass().getResource("/bgMb.jpg")).getImage();
		bgMb.setIcon(new ImageIcon(bg));

		try {
			ArrayList<Category> cList = cDB.getAll();
			tblModel.setRowCount(0);
			for (Category c : cList) {
				int ID = c.getCategoryId();
				String name = c.getCategoryName();
				tblModel.addRow(new Object[] { ID, name });
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

	}

	private boolean checkValid() {
		if (txtCName.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Book name cannot be blank");
			return false;
		}
		try {
			int id = Integer.valueOf(txtCName.getText());
			JOptionPane.showMessageDialog(this, "Category name must not be in numeric format", "Alert",
					JOptionPane.WARNING_MESSAGE);
			return false;
		} catch (Exception e) {

		}

		return true;
	}
}
