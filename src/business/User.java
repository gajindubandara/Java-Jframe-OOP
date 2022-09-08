package business;

public class User {
	private int userID;
	private String name;
	private String address;
	private String number;
	private String email;
	private String password;
	private String type;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int userID, String name, String address, String number, String email, String password, String type) {
		this.userID = userID;
		this.name = name;
		this.address = address;
		this.number = number;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
