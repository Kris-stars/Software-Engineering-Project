
public class Bill extends Docket {
	
	public static final double MIN_AREA = 1;
	public static final double MAX_AREA = 13;
	public static final int CUSTOMER_NUM_LENGTH = 16;
	public static final int DOCKET_NUM_LENGTH = 16;
 
	public Bill(double area, int custormernum, int docketnum) {
		super(area, custormernum, docketnum);
	}

	public static boolean isValidArea(double area) {
		return area >= MIN_AREA && area <= MAX_AREA;
	}

	public static boolean isValidCustomerNum(String customerNum) {
		return customerNum != null && customerNum.length() == CUSTOMER_NUM_LENGTH;
	}

	public static boolean isValidDocketNum(String docketNum) {
		return docketNum != null && docketNum.length() == DOCKET_NUM_LENGTH;
	}

}
