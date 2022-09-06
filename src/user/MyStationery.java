package user;

import java.awt.event.*;
import java.sql.Date;
import data.BookDB;

import javax.swing.*;

import business.Book;
import business.Book;

import java.util.*;

//itvoyagers.in
public class MyStationery extends JFrame implements ActionListener
{
	private BookDB bDB;
    HashMap<String, String> product_and_price;
    
    DefaultListModel<String> product_name_bill, product_quantity_bill,product_price_bill;
    
    JLabel product_name, price_per_unit, quantity_label, price_of_product, price_per_unit_label, total, total_amount, bill_product, bill_quantity, bill_price;
    JComboBox product_list, quantity_list;
    JList product_bill, product_quantity, product_price;
    JButton add_product;
    
    
    public MyStationery()
    {
        product_and_price = new HashMap<String, String>();
        
//		try {
//			ArrayList<Book> bList = bDB.getAll();
////			tblModel.setRowCount(0);
//			for (Book b : bList) {
//				int ID = b.getBookID();
//				String name =b.getName();
//				String isbn =b.getIsbn();
//				String author=b.getAuthor();
//				Date date=b.getDate();
//				String price="Rs."+b.getPrice()+".00/-";
//						
//
////				tblModel.addRow(new Object[] { ID,name,isbn,author,date,price});
//				 product_and_price.put(name, price);
//			}
//		}
//		catch(Exception ex) {
//			System.err.println(ex.getMessage());
//			
//		}
//        
        product_and_price.put("Book", "80");
        product_and_price.put("Pen", "10");
        product_and_price.put("Pencil", "7");
        product_and_price.put("Marker", "40");
        product_and_price.put("Eraser", "5");
        
//itvoyagers.in        
        product_name_bill = new DefaultListModel<>();
        product_price_bill = new DefaultListModel<>();
        product_quantity_bill = new DefaultListModel<>();
        
        product_name = new JLabel("Product Name");
        quantity_label = new JLabel("Quantity");
        price_per_unit = new JLabel("Price Per Unit(₹)");
        price_per_unit_label = new JLabel("80");
        
        product_list = new JComboBox();
        quantity_list = new JComboBox();
                
        product_and_price.forEach((key,value)->
        {
            product_list.addItem(key);
        });
        
        for(int i=1; i<=10; i++)
        {
            quantity_list.addItem(Integer.toString(i));
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////   
        bill_product = new JLabel("Item");
        bill_quantity = new JLabel("Quantity");
        bill_price = new JLabel("Cost");
        
        product_bill = new JList(product_name_bill);
        product_price = new JList(product_price_bill);
        product_quantity = new JList(product_quantity_bill);
        
        add_product = new JButton("Add Product");
        
        total = new JLabel("Total Amount : ");
        total_amount = new JLabel();
        
        product_name.setBounds(10, 30, 100, 20);
        price_per_unit.setBounds(160, 30, 100, 20);
        quantity_label.setBounds(310, 30, 100, 20);
        price_per_unit_label.setBounds(160, 50, 100, 20);
        
        product_list.setBounds(10, 50, 100, 20);
        quantity_list.setBounds(310, 50, 100, 20);
//itvoyagers.in       
        add_product.setBounds(10, 80, 400, 50);
        
        bill_product.setBounds(10, 150, 150, 20);
        bill_quantity.setBounds(170, 150, 90, 20);
        bill_price.setBounds(270, 150, 140, 20);
        
        product_bill.setBounds(10, 175, 150, 400);
        product_quantity.setBounds(170, 175, 90, 400);
        product_price.setBounds(270, 175, 140, 400);
        
        total.setBounds(140, 575, 200, 20);
        total_amount.setBounds(310, 575, 90, 20);
                
        add(product_name);
        add(price_per_unit);
        add(quantity_label);
        
        add(price_per_unit_label);
        add(product_list);
        add(quantity_list);
        
        add(add_product);
 //itvoyagers.in       
        add(bill_product);
        add(bill_quantity);
        add(bill_price);
        
        add(product_bill);
        add(product_quantity);
        add(product_price);
        
        add(total);
        add(total_amount);
        
        product_list.addActionListener(this);
        add_product.addActionListener(this);
        
        setSize(435,650);
        setLayout(null);
        setVisible(true);
        setTitle("My Stationery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 //itvoyagers.in   
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==add_product)
        {
        	//Adding items to the list
            product_name_bill.addElement((String) product_list.getSelectedItem());
            product_quantity_bill.addElement((String) quantity_list.getSelectedItem());
            int unit_price = Integer.parseInt(product_and_price.get(product_list.getSelectedItem()));
            int product_quantity = Integer.parseInt((String) quantity_list.getSelectedItem());
            int total_for_product = unit_price*product_quantity; 
            int total_cost = 0;
            product_price_bill.addElement(Integer.toString(total_for_product));
            
            for(int i=0;i<product_price_bill.getSize();i++)
            {
                total_cost += Integer.parseInt(product_price_bill.getElementAt(i));
            }
            
            total_amount.setText(Integer.toString(total_cost));
        }
        else if(e.getSource()==product_list)
        {
            price_per_unit_label.setText(product_and_price.get(product_list.getSelectedItem()));
        }
    }
//itvoyagers.in
    public static void main(String[] args)
    {
        MyStationery ms = new MyStationery();
    }
}