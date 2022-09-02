package business;

public class Cashier {
	private int cashierID;
	private String name;
	private String address;
	private String number;
	private String username;
	private String password;
	/**
	 * 
	 */
	public Cashier() {
		// TODO Auto-generated constructor stub
	}
	public Cashier(int cashierID,String name,String address,String number,String username,String password) {
		this.cashierID=cashierID;
		this.name=name;
		this.address=address;
		this.number=number;
		this.username=username;
		this.password=password;
	}
	public int getCashierID() {
		return cashierID;
	}
	public void setCashierID(int cashierID) {
		this.cashierID = cashierID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
