package business;

public class Stock {
	private int sId;
	private String sAmount;

	public Stock() {
		// TODO Auto-generated constructor stub
	}

	public Stock(int sId, String sAmount) {
		this.sId = sId;
		this.sAmount = sAmount;

	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsAmount() {
		return sAmount;
	}

	public void setsAmount(String sAmount) {
		this.sAmount = sAmount;
	}

}
