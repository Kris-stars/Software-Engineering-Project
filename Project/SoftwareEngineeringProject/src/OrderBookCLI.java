import java.util.Scanner;

public class OrderBookCLI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Create New Order ===");

        System.out.print("Order Number: ");
        String orderNum = scanner.nextLine();

        System.out.print("Customer Number: ");
        String customerNum = scanner.nextLine();

        System.out.print("Publication ID: ");
        String publicationID = scanner.nextLine();

        System.out.print("Frequency: ");
        String frequency = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        try {
            Publications publication =
                new Publications(publicationID, frequency, price);

            OrderBook book = new OrderBook();

            if (book.createOrder(orderNum, customerNum, publication)) {

                OrderBookDAO dao = new OrderBookDAO();

                if (dao.addOrder(book.getOrder(orderNum))) {
                    System.out.println("Order saved successfully.");
                } else {
                    System.out.println("Failed to save order.");
                }

            } else {
                System.out.println("Invalid order details.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }

        scanner.close();
    }
}