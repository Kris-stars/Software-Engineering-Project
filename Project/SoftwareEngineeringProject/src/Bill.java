
import java.util.List;
public class Bill extends Docket {
	
	
	public static final int CUSTOMER_NUM_LENGTH = 16;
	public static final int DOCKET_NUM_LENGTH = 16;
 
	public Bill(double area, String custormernum, String docketnum) {
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
	
	 // CREATE
    public static Bill create(double area, String customerNum,
                              String docketnum, List<Bill> bills) {

        if (!isValidArea(area)
                || !isValidCustomerNum(customerNum)
                || !isValidDocketNum(docketnum)
                || bills == null) {
            return null;
        }

        if (read(docketnum, bills) != null) {
            return null;
        }

        Bill bill = new Bill(area, customerNum, docketnum);

        bills.add(bill);

        return bill;
    }

    // READ
    public static Bill read(String docketnum, List<Bill> bills) {

        if (!isValidDocketNum(docketnum) || bills == null) {
            return null;
        }

        for (Bill bill : bills) {

            if (bill != null
                    && bill.getDocketnum().equals(docketnum)) {

                return bill;
            }
        }

        return null;
    }

    // UPDATE
    public static Bill update(String docketnum, double newArea,
                              String newCustomerNum, List<Bill> bills) {

        if (!isValidDocketNum(docketnum)
                || !isValidArea(newArea)
                || !isValidCustomerNum(newCustomerNum)
                || bills == null) {
            return null;
        }

        for (int i = 0; i < bills.size(); i++) {

            Bill bill = bills.get(i);

            if (bill != null
                    && bill.getDocketnum().equals(docketnum)) {

                Bill updatedBill = new Bill(
                    newArea, newCustomerNum, docketnum
                );

                bills.set(i, updatedBill);

                return updatedBill;
            }
        }

        return null;
    }

    // DELETE
    public static boolean delete(String docketnum, List<Bill> bills) {

        if (!isValidDocketNum(docketnum) || bills == null) {
            return false;
        }

        Bill bill = read(docketnum, bills);

        if (bill == null) {
            return false;
        }

        return bills.remove(bill);
    }
	

}
