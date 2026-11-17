import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class OrderBookCLI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }

    public static void run(Scanner scanner) {
        OrderBookDAO dao = new OrderBookDAO();
        PublicationsDAO publicationsDAO = new PublicationsDAO();
        CustomerBookDAO customersDAO = new CustomerBookDAO();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Order Book Menu ===");
            System.out.println("1. Add Order");
            System.out.println("2. View All Orders");
            System.out.println("3. Search Order by Number");
            System.out.println("4. Update Order");
            System.out.println("5. Delete Order");
            System.out.println("0. Exit Order Book Menu");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1": addOrder(scanner, dao, publicationsDAO, customersDAO); break;
                    case "2": viewAllOrders(dao); break;
                    case "3": searchOrder(scanner, dao); break;
                    case "4": updateOrder(scanner, dao, publicationsDAO, customersDAO); break;
                    case "5": deleteOrder(scanner, dao); break;
                    case "0": running = false; break;
                    default: System.out.println("Invalid choice. Enter 0-5.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
        System.out.println("Leaving Order Book Menu.");
    }
    
    private static void addOrder(Scanner scanner, OrderBookDAO dao,
                                 PublicationsDAO publicationsDAO,
                                 CustomerBookDAO customersDAO) throws SQLException {
        System.out.println("\n=== Add Order ===");
        String orderNum = readNumber(scanner, "Order number (16 characters): ", true);
        if (dao.getByOrderNum(orderNum) != null) {
            System.out.println("An order with that number already exists.");
            return;
        }
        String customerNum = readNumber(scanner, "Customer number (16 characters): ", false);
        if (customersDAO.getById(customerNum) == null) {
            System.out.println("Customer not found. Add the customer first.");
            return;
        }
        Publications publication = readExistingPublication(scanner, publicationsDAO);
        if (publication == null) return;

        OrderBook.Order order = new OrderBook.Order(orderNum, customerNum, publication);
        dao.insert(order);
        System.out.println("Order saved successfully.");
    }

    private static void viewAllOrders(OrderBookDAO dao) throws SQLException {
        System.out.println("\n=== All Orders ===");
        List<OrderBook.Order> orders = dao.getAll();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        for (OrderBook.Order order : orders) printOrder(order);
    }

    private static void searchOrder(Scanner scanner, OrderBookDAO dao) throws SQLException {
        System.out.println("\n=== Search Order ===");
        String orderNum = readNumber(scanner, "Order number (16 characters): ", true);
        OrderBook.Order order = dao.getByOrderNum(orderNum);
        if (order == null) System.out.println("Order not found.");
        else printOrder(order);
    }

    private static void updateOrder(Scanner scanner, OrderBookDAO dao,
                                    PublicationsDAO publicationsDAO,
                                    CustomerBookDAO customersDAO) throws SQLException {
        System.out.println("\n=== Update Order ===");
        String orderNum = readNumber(scanner, "Order number (16 characters): ", true);
        OrderBook.Order existing = dao.getByOrderNum(orderNum);
        if (existing == null) {
            System.out.println("Order not found.");
            return;
        }
        System.out.println("Current order:");
        printOrder(existing);
        System.out.println("Enter the new customer and publication:");
        String customerNum = readNumber(scanner, "Customer number (16 characters): ", false);
        if (customersDAO.getById(customerNum) == null) {
            System.out.println("Customer not found. Update cancelled.");
            return;
        }
        Publications publication = readExistingPublication(scanner, publicationsDAO);
        if (publication == null) return;

        OrderBook.Order updated = new OrderBook.Order(orderNum, customerNum, publication);
        if (dao.update(updated)) System.out.println("Order updated successfully.");
        else System.out.println("Order could not be updated.");
    }

    private static void deleteOrder(Scanner scanner, OrderBookDAO dao) throws SQLException {
        System.out.println("\n=== Delete Order ===");
        String orderNum = readNumber(scanner, "Order number (16 characters): ", true);
        OrderBook.Order existing = dao.getByOrderNum(orderNum);
        if (existing == null) {
            System.out.println("Order not found.");
            return;
        }
        printOrder(existing);
        System.out.print("Delete this order (Yes/No): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("yes")) {
            System.out.println("Deletion cancelled.");
            return;
        }
        if (dao.delete(orderNum)) System.out.println("Order deleted successfully.");
        else System.out.println("Order could not be deleted.");
    }

    private static String readNumber(Scanner scanner, String prompt, boolean isOrder) {
        while (true) {
            System.out.print(prompt);
            String number = scanner.nextLine().trim();
            boolean valid = isOrder ? OrderBook.isValidOrderNum(number)
                                    : OrderBook.isValidCustomerNum(number);
            if (valid) return number;
            System.out.println("Number must be exactly 16 characters.");
        }
    }

    private static Publications readExistingPublication(Scanner scanner,
                                                         PublicationsDAO publicationsDAO) throws SQLException {
        System.out.print("Publication ID (16 characters): ");
        String id = scanner.nextLine().trim();
        if (!Publications.isValidPublicationsID(id)) {
            System.out.println("Publication ID must be exactly 16 characters.");
            return null;
        }
        Publications publication = publicationsDAO.getById(id);
        if (publication == null) System.out.println("Publication not found. Add the publication first.");
        return publication;
    }

    private static void printOrder(OrderBook.Order order) {
        System.out.println("------------------------------");
        System.out.println("Order number: " + order.getOrderNum());
        System.out.println("Customer number: " + order.getCustomerNum());
        System.out.println("Publication ID: " + order.getPublication().getPublicationsID());
        System.out.println("Frequency: " + order.getPublication().getFrequency());
        System.out.printf("Price: %.2f%n", order.getPublication().getPrice());
    }
}