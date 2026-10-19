public class Docket extends OrderBook{
	
	private boolean stock;
	private int docketnum;
	private double area;
	private int customerNum;
	
	public boolean isStock() {
		return stock;
	}
	public void setStock(boolean stock) {
		this.stock = stock;
	}
	public int getDocketnum() {
		return docketnum;
	}
	public void setDocketnum(int docketnum) {
		this.docketnum = docketnum;
	}
	
	//added by Denis
	public double getArea() {
		return area;
	}
	public int getCustomerNum() {
		return customerNum;
	}
	// 
	
	public Docket(boolean stock, int docketnum, double area, long address, int custormernum, String[] publictions) {
		super();
		this.stock = stock;
		this.docketnum = docketnum;
	}
	
	public Docket(double area) {
		super();	
	
	}
	
	public Docket(double area,int docketnum) {
		super();	
		this.docketnum = docketnum;
	}
	public Docket(double area,int custormernum,int docketnum) {
		super();	
		this.docketnum = docketnum;
	}
	

}
