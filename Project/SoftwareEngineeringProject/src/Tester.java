
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
    	
//    	String pubId = "1111111111111112";
//        String orderNum = "2222222222222223";
//        String cust = "1234567890123457";
//
//        try {
//            // 1. Save the publication FIRST (the order needs it to exist)
//            Publications pub = new Publications(pubId, "Daily", 2.50);
//            PublicationsDAO pubDao = new PublicationsDAO();
//            pubDao.insert(pub);
//            System.out.println("Publication saved! Rows in DB: " + pubDao.getAll().size());
//
//            // 2. Create the order (OrderBook checks it is valid)
//            OrderBook book = new OrderBook();
//            if (book.createOrder(orderNum, cust, pub)) {
//                OrderBookDAO orderDao = new OrderBookDAO();
//                orderDao.insert(book.getOrder(orderNum));
//                System.out.println("Order saved! Rows in DB: " + orderDao.getAll().size());
//            } else {
//                System.out.println("Invalid order");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    	  String customerId = "1234567890123457";
          String customerFirstName = "John";
          String customerSurname = "Smith";
          String customerAddress = "Main Street";
          String customerPhone = "0871234567";

          String pubId = "1111111111111112";
          String frequency = "Daily";
          double price = 2.50;

          String docketNum = "1111222233334444";
          double area = 5.5;
          boolean stock = true;

          String orderNum = "2222222222222223";


          try {

              
              //  CUSTOMER BOOK
             

              System.out.println("=== CUSTOMER BOOK ===");

              CustomerBookDAO customerDao = new CustomerBookDAO();

              CustomerBook customer = new CustomerBook(
                  customerId,
                  customerFirstName,
                  customerSurname,
                  customerAddress,
                  customerPhone
              );

              // Only insert if customer does not exist
              if (customerDao.getById(customerId) == null) {

                  customerDao.insert(customer);
                  System.out.println("Customer saved!");

              } else {
                  System.out.println("Customer already exists.");
              }

              System.out.println("Customers in DB:");

              for (CustomerBook c : customerDao.getAll()) {
                  System.out.println(
                      c.getCustomerID() + " | "
                      + c.getCustomerFirstName() + " "
                      + c.getCustomerSurname() + " | "
                      + c.getCustomerPhoneNumber()
                  );
              }


              
              // PUBLICATIONS
             

              System.out.println("\n=== PUBLICATIONS ===");

              PublicationsDAO pubDao = new PublicationsDAO();

              Publications pub = new Publications(
                  pubId,
                  frequency,
                  price
              );

              if (pubDao.getById(pubId) == null) {

                  pubDao.insert(pub);
                  System.out.println("Publication saved!");

              } else {
                  System.out.println("Publication already exists.");
              }

              System.out.println("Publications in DB:");

              for (Publications p : pubDao.getAll()) {
                  System.out.println(
                      p.getPublicationsID() + " | "
                      + p.getFrequency() + " | "
                      + p.getPrice()
                  );
              }


             
              // DOCKET
            

              System.out.println("\n=== DOCKET ===");

              DocketDAO docketDao = new DocketDAO();

              Docket docket = new Docket(
                  area,
                  customerId,
                  docketNum
              );

              docket.setStock(stock);

              if (docketDao.getByDocketNum(docketNum) == null) {

                  docketDao.insert(docket);
                  System.out.println("Docket saved!");

              } else {
                  System.out.println("Docket already exists.");
              }

              System.out.println("Dockets in DB:");

              for (Docket d : docketDao.getAll()) {
                  System.out.println(
                      d.getDocketnum() + " | "
                      + d.getCustomerNum() + " | "
                      + d.getArea() + " | "
                      + d.isStock()
                  );
              }


            
              // ORDER BOOK
         

              System.out.println("\n=== ORDER BOOK ===");

              OrderBookDAO orderDao = new OrderBookDAO();

              OrderBook book = new OrderBook();

              if (orderDao.getByOrderNum(orderNum) == null) {

                  if (book.createOrder(orderNum, customerId, pub)) {

                      orderDao.insert(book.getOrder(orderNum));
                      System.out.println("Order saved!");

                  } else {
                      System.out.println("Invalid order.");
                  }

              } else {
                  System.out.println("Order already exists.");
              }

              System.out.println("Orders in DB:");

              for (OrderBook.Order o : orderDao.getAll()) {
                  System.out.println(
                      o.getOrderNum() + " | "
                      + o.getCustomerNum() + " | "
                      + o.getPublication().getPublicationsID()
                  );
              }


              System.out.println("\n=== FINISHED ===");
              System.out.println("Information stored in MySQL.");

          } catch (Exception e) {

              e.printStackTrace();
          }
    }
}


