
public class OrderBook extends CustormerBook{
	
	private String[] publictions;

	public String[] getPublictions() {
		return publictions;
	}

	public void setPublictions(String[] publictions) {
		this.publictions = publictions;
	}

	public OrderBook(int custormernum, String[] publictions) {
		super(custormernum);
		this.publictions = publictions;
	}
	
	public OrderBook(double area, long address) {
		super(area,address);
		
	}

	public OrderBook(double area, long address, int custormernum, String[] publictions) {
		super(address,custormernum,area);
		this.publictions = publictions;
	}

	public OrderBook(double area) {
		super(area);
		
	}
	
	
	

}
