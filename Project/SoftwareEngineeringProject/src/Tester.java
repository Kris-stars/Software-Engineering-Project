
public class Tester {
    public static void main(String[] args) {
//        double area = 5.5;
//        String cust = "1111222233334443";
//        String docket = "1111222233334424";
//
//        if (Bill.isValidArea(area) && Bill.isValidCustomerNum(cust)
//                && Bill.isValidDocketNum(docket)) {
//            try {
//                BillDAO dao = new BillDAO();
//                dao.insert(new Bill(area, cust, docket));
//                System.out.println("Saved! Rows in DB: " + dao.getAll().size());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Invalid input");
//        }
    	
    	String pubId = "1111111111111111";
        String orderNum = "2222222222222222";
        String cust = "1234567890123456";

        try {
            // 1. Save the publication FIRST (the order needs it to exist)
            Publications pub = new Publications(pubId, "Daily", 2.50);
            PublicationsDAO pubDao = new PublicationsDAO();
            pubDao.insert(pub);
            System.out.println("Publication saved! Rows in DB: " + pubDao.getAll().size());

            // 2. Create the order (OrderBook checks it is valid)
            OrderBook book = new OrderBook();
            if (book.createOrder(orderNum, cust, pub)) {
                OrderBookDAO orderDao = new OrderBookDAO();
                orderDao.insert(book.getOrder(orderNum));
                System.out.println("Order saved! Rows in DB: " + orderDao.getAll().size());
            } else {
                System.out.println("Invalid order");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


