public class Docket extends OrderBook{
	
	private boolean stock;
	private String docketnum;
	private double area;
	private String customerNum;
	
	public boolean isStock() {
		return stock;
	}
	public void setStock(boolean stock) {
		this.stock = stock;
	}
	public String getDocketnum() {
		return docketnum;
	}
	public void setDocketnum(String docketnum) {
		this.docketnum = docketnum;
	}
	
	//added by Denis
	public double getArea() {
		return area;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	// 
	
	public Docket(boolean stock, String docketnum, double area, long address,
            String customerNum, String[] publications) {
  super();
  this.stock = stock;
  this.docketnum = docketnum;
  this.area = area;
  this.customerNum = customerNum;
}

public Docket(double area) {
  super();
  this.area = area;
}

public Docket(double area, String docketnum) {
  super();
  this.area = area;
  this.docketnum = docketnum;
}

public Docket(double area, String customerNum, String docketnum) {
  super();
  this.area = area;
  this.customerNum = customerNum;
  this.docketnum = docketnum;
}
	

}
