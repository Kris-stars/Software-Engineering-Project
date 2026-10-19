
public class Tester {
    public static void main(String[] args) {
        double area = 5.5;
        String cust = "1234567890123456";
        String docket = "6543210987654321";

        if (Bill.isValidArea(area) && Bill.isValidCustomerNum(cust)
                && Bill.isValidDocketNum(docket)) {
            try {
                BillDAO dao = new BillDAO();
                dao.insert(new Bill(area, cust, docket));
                System.out.println("Saved! Rows in DB: " + dao.getAll().size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid input");
        }
    }
}

}
