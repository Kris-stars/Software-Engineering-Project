import java.util.ArrayList;
import java.util.List;

public class OrderBook {
    // constants
    public static final int CUSTOMER_NUM_LENGTH = 16;
    public static final int ORDER_NUM_LENGTH = 16;

    // order class
    public static class Order {
        private final String orderNum;
        private String customerNum;
        private Publications publication;

        public Order(String orderNum, String customerNum, Publications publication) {
            this.orderNum = orderNum;
            this.customerNum = customerNum;
            this.publication = publication;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public String getCustomerNum() {
            return customerNum;
        }

        public Publications getPublication() {
            return publication;
        }
    }

    private final List<Order> orders = new ArrayList<>();

    // validation methods
    public static boolean isValidCustomerNum(String customerNum) {
        return customerNum != null && customerNum.length() == CUSTOMER_NUM_LENGTH;
    }

    public static boolean isValidOrderNum(String orderNum) {
        return orderNum != null && orderNum.length() == ORDER_NUM_LENGTH;
    }

    public static boolean isValidPublication(Publications publication) {
        return publication != null;
    }

    // CRUD methods
    public boolean createOrder(String orderNum, String customerNum, Publications publication) {
        if (!isValidOrderNum(orderNum) || !isValidCustomerNum(customerNum)
                || !isValidPublication(publication) || getOrder(orderNum) != null)
            return false;
        orders.add(new Order(orderNum, customerNum, publication));
        return true;
    }

    public Order getOrder(String orderNum) {
        if (orderNum == null)
            return null;
        for (Order order : orders)
            if (order.getOrderNum().equals(orderNum))
                return order;
        return null;
    }

    public Order updateOrder(String orderNum, String newCustomerNum, Publications newPublication) {
        if (!isValidOrderNum(orderNum) || !isValidCustomerNum(newCustomerNum)
                || !isValidPublication(newPublication))
            return null;
        Order order = getOrder(orderNum);
        if (order == null)
            return null;
        order.customerNum = newCustomerNum;
        order.publication = newPublication;
        return order;
    }

    public int getOrderCount() {
        return orders.size();
    }

    public boolean deleteOrder(String orderNum) {
        if (orderNum == null)
            return false;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNum().equals(orderNum)) {
                orders.remove(i);
                return true;
            }
        }
        return false;
    }
}