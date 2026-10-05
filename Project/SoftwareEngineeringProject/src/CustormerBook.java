
public class CustormerBook {
	
	private String name;
	private Long address;
	private int custormernum;
	private long area;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAddress() {
		return address;
	}
	public void setAddress(Long address) {
		this.address = address;
	}
	public int getCustormernum() {
		return custormernum;
	}
	public void setCustormernum(int custormernum) {
		this.custormernum = custormernum;
	}
	public long getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public CustormerBook(String name, long address, int custormernum, long area) {
		super();
		this.name = name;
		this.address = address;
		this.custormernum = custormernum;
		this.area = area;
	}

	public CustormerBook(long address, int custormernum, long area) {
		this.address = address;
		this.custormernum = custormernum;
		this.area = area;
	}
	public CustormerBook(int custormernum) {
		super();
		this.custormernum = custormernum;
	}
	public CustormerBook(long area,long address) {
		super();
		this.area = area;
		this.address = address;
	}

	public CustormerBook(long area) {
		super();
		this.area = area;
	}
	
	

}
