import static org.junit.Assert.*;
import org.junit.Test;

public class OrderBookTest {

    // TC01
    // Test Objective: Accept customer number of 16 characters
    // Input(s): Customer: 1234567890123456
    // Expected Output(s): Accepted
    @Test
    public void customerNum_16chars_valid() {
        assertTrue(OrderBook.isValidCustomerNum("1234567890123456"));
    }

    // TC02
    // Test Objective: Reject short customer number
    // Input(s): Customer: 123456789012345
    // Expected Output(s): Rejected
    @Test
    public void customerNum_15chars_invalid() {
        assertFalse(OrderBook.isValidCustomerNum("123456789012345"));
    }

    // TC03
    // Test Objective: Reject long customer number
    // Input(s): Customer: 12345678901234567
    // Expected Output(s): Rejected
    @Test
    public void customerNum_17chars_invalid() {
        assertFalse(OrderBook.isValidCustomerNum("12345678901234567"));
    }

    // TC04
    // Test Objective: Reject empty customer number
    // Input(s): Customer: ""
    // Expected Output(s): Rejected
    @Test
    public void customerNum_empty_invalid() {
        assertFalse(OrderBook.isValidCustomerNum(""));
    }

    // TC05
    // Test Objective: Reject null customer number
    // Input(s): Customer: null
    // Expected Output(s): Rejected
    @Test
    public void customerNum_null_invalid() {
        assertFalse(OrderBook.isValidCustomerNum(null));
    }

    // TC06
    // Test Objective: Accept order number of 16 characters
    // Input(s): Order: ORDR123456789012
    // Expected Output(s): Accepted
    @Test
    public void orderNum_16chars_valid() {
        assertTrue(OrderBook.isValidOrderNum("ORDR123456789012"));
    }

    // TC07
    // Test Objective: Reject short order number
    // Input(s): Order: ORDR12345678901
    // Expected Output(s): Rejected
    @Test
    public void orderNum_15chars_invalid() {
        assertFalse(OrderBook.isValidOrderNum("ORDR12345678901"));
    }

    // TC08
    // Test Objective: Reject long order number
    // Input(s): Order: ORDR1234567890123
    // Expected Output(s): Rejected
    @Test
    public void orderNum_17chars_invalid() {
        assertFalse(OrderBook.isValidOrderNum("ORDR1234567890123"));
    }

    // TC09
    // Test Objective: Reject empty order number
    // Input(s): Order: ""
    // Expected Output(s): Rejected
    @Test
    public void orderNum_empty_invalid() {
        assertFalse(OrderBook.isValidOrderNum(""));
    }

    // TC10
    // Test Objective: Reject null order number
    // Input(s): Order: null
    // Expected Output(s): Rejected
    @Test
    public void orderNum_null_invalid() {
        assertFalse(OrderBook.isValidOrderNum(null));
    }

    // TC11
    // Test Objective: Accept non-null publication
    // Input(s): Valid Publications object
    // Expected Output(s): Accepted
    @Test
    public void publication_valid() {
        assertTrue(OrderBook.isValidPublication(new Publications("1234567890123456", "Daily", 2.50)));
    }

    // TC12
    // Test Objective: Reject null publication
    // Input(s): Publication: null
    // Expected Output(s): Rejected
    @Test
    public void publication_null_invalid() {
        assertFalse(OrderBook.isValidPublication(null));
    }

    // TC13
    // Test Objective: Create and store valid order
    // Input(s): Valid order/customer/publication
    // Expected Output(s): Created; count 1
    @Test
    public void createOrder_valid() {
        OrderBook book = new OrderBook();
        assertTrue(book.createOrder("ORDR123456789012", "1234567890123456", new Publications("1234567890123456", "Daily", 2.50)));
        assertEquals(1, book.getOrderCount());
    }

    // TC14
    // Test Objective: Reject order with invalid customer
    // Input(s): 15-char customer
    // Expected Output(s): Rejected; count 0
    @Test
    public void createOrder_invalidCustomer() {
        OrderBook book = new OrderBook();
        assertFalse(book.createOrder("ORDR123456789012", "123456789012345", new Publications("1234567890123456", "Daily", 2.50)));
        assertEquals(0, book.getOrderCount());
    }

    // TC15
    // Test Objective: Reject order with invalid order ID
    // Input(s): 15-char order
    // Expected Output(s): Rejected; count 0
    @Test
    public void createOrder_invalidOrderNum() {
        OrderBook book = new OrderBook();
        assertFalse(book.createOrder("ORDR12345678901", "1234567890123456", new Publications("1234567890123456", "Daily", 2.50)));
        assertEquals(0, book.getOrderCount());
    }

    // TC16
    // Test Objective: Reject order with no publication
    // Input(s): Publication null
    // Expected Output(s): Rejected; count 0
    @Test
    public void createOrder_nullPublication() {
        OrderBook book = new OrderBook();
        assertFalse(book.createOrder("ORDR123456789012", "1234567890123456", null));
        assertEquals(0, book.getOrderCount());
    }

    // TC17
    // Test Objective: Reject duplicate order number
    // Input(s): Same order twice
    // Expected Output(s): Second rejected; count 1
    @Test
    public void createOrder_duplicate() {
        OrderBook book = new OrderBook();
        Publications pub = new Publications("1234567890123456", "Daily", 2.50);
        assertTrue(book.createOrder("ORDR123456789012", "1234567890123456", pub));
        assertFalse(book.createOrder("ORDR123456789012", "1234567890123456", pub));
        assertEquals(1, book.getOrderCount());
    }

    // TC18
    // Test Objective: Verify order stores correct customer and publication
    // Input(s): Valid order/customer/publication
    // Expected Output(s): Correct associations
    @Test
    public void createOrder_storesReferences() {
        OrderBook book = new OrderBook();
        Publications pub = new Publications("1234567890123456", "Daily", 2.50);
        assertTrue(book.createOrder("ORDR123456789012", "1234567890123456", pub));
        OrderBook.Order order = book.getOrder("ORDR123456789012");
        assertNotNull(order);
        assertEquals("1234567890123456", order.getCustomerNum());
        assertSame(pub, order.getPublication());
    }

    // TC19
    // Test Objective: Unknown order cannot be retrieved
    // Input(s): Unknown 16-char order
    // Expected Output(s): Null returned
    @Test
    public void createOrder_missing() {
        OrderBook book = new OrderBook();
        assertNull(book.getOrder("MISS123456789012"));
    }
}
