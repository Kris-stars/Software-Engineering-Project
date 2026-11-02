import java.util.ArrayList;

public class Docket extends OrderBook{
	
	private boolean stock;
	private String docketnum;
	private double area;
	private String customerNum;
	
	public static final int DOCKET_NUM_LENGTH = 16;
	public static final double MIN_AREA = 1;
	public static final double MAX_AREA = 13;
	
	
	// setters & getters
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
	
	public double getArea() {
		return area;
	}
	
	public void setArea(double area) {

		if (area >= MIN_AREA && area <= MAX_AREA) {
	    this.area = area;
	    	
	    }
	}
	
	public String getCustomerNum() {
		return customerNum;
	}
	//
	
	// check validation
	public static boolean isValidArea(double area) {
	    return area >= MIN_AREA && area <= MAX_AREA;
	}
	
	public static boolean isValidDocketNum(String docketNum) {
		return docketNum != null && docketNum.length() == DOCKET_NUM_LENGTH;
	}
	
	//
	
	
	
	// create
	public Docket(boolean stock, String docketnum, double area, long address,
            String customerNum, String[] publications) {
			  this.stock = stock;
			  this.docketnum = docketnum;
			  this.area = area;
			  this.customerNum = customerNum;
			}

	public Docket(double area) {
	  this.area = area;
	}

	public Docket(double area, String docketnum) {
	  this.area = area;
	  this.docketnum = docketnum;
	}

	public Docket(double area, String customerNum, String docketnum) {
	  this.area = area;
	  this.customerNum = customerNum;
	  this.docketnum = docketnum;
	}
	//
	
	// read
	public Docket read(String docketnum, ArrayList<Docket> dockets) {

	    if (docketnum == null || docketnum.length() != 16) {
	        return null;
	    }

	    for (Docket docket : dockets) {

	        if (docket.getDocketnum().equals(docketnum)) {
	            return docket;
	        }
	    }

	    return null;
	}
	//
	
	// update
		public Docket update(String docketnum, double newArea,ArrayList<Docket> dockets) {
			
			if (docketnum == null || docketnum.length() != 16) {
				return null;
			}
			
			if (newArea < MIN_AREA || newArea > MAX_AREA) {
				return null;
			}
			
			for (Docket docket : dockets) {
			
			if (docket.getDocketnum().equals(docketnum)) {
			
			   docket.setArea(newArea);
			
			   return docket;
			}
		}
		
		
		return null;
	}

}
