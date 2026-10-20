
public class Tester {
    public static void main(String[] args) {
        double area = 5.5;
        String cust = "1111222233334443";
        String docket = "1111222233334424";

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


