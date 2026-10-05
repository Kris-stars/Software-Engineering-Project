
public class Docket extends OrderBook{
	
	private boolean stock;
	private int docketnum;
	
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

	
	public Docket(boolean stock,int docketnum,long area, long address,int custormernum, String[] publictions) {
		super(area,address, custormernum,publictions);
		this.stock = stock;
		this.docketnum = docketnum;
	}
	
	public Docket(long area) {
		super(area);	
	
	}
	
	public Docket(long area,int docketnum) {
		super(area);	
		this.docketnum = docketnum;
	}
	public Docket(long area,int custormernum,int docketnum) {
		super(area,custormernum);	
		this.docketnum = docketnum;
	}
	

}
