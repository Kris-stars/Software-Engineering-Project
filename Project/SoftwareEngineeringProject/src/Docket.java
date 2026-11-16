
import java.util.ArrayList;
import java.security.SecureRandom;

public class Docket extends OrderBook {

    public static final double MIN_AREA = 1;
    public static final double MAX_AREA = 13;
    public static final int DOCKET_NUM_LENGTH = 16;

    private static final SecureRandom random = new SecureRandom();

    private boolean stock;
    private String docketnum;
    private String address;
    private double area;

    // Main constructor
    public Docket(boolean stock, String docketnum, double area,
                  String address, String customerNum,
                  Publications[] publications) {

        this.stock = stock;
        this.docketnum = docketnum;
        this.address = address;
        this.area = area;

        if (customerNum != null && publications != null) {
            for (int i = 0; i < publications.length; i++) {
                if (publications[i] != null) {
                    createOrder(
                        generateOrderNum(),
                        customerNum,
                        publications[i]
                    );
                }
            }
        }
    }

    // Additional constructors
    public Docket(double area) {
        this(false, null, area, null, null, new Publications[0]);
    }

    public Docket(double area, String docketnum) {
        this(false, docketnum, area, null, null, new Publications[0]);
    }

    public Docket(double area, String customerNum, String docketnum) {
        this(false, docketnum, area, null, customerNum,
             new Publications[0]);
    }

    // Getters
    public boolean isStock() {
        return stock;
    }

    public String getDocketnum() {
        return docketnum;
    }

    public String getAddress() {
        return address;
    }

    public double getArea() {
        return area;
    }

    // Setters
    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(double area) {
        if (isValidArea(area)) {
            this.area = area;
        }
    }

    // Validation methods
    public static boolean isValidArea(double area) {
        return area >= MIN_AREA && area <= MAX_AREA;
    }

    public static boolean isValidDocketNum(String docketNum) {
        return docketNum != null
                && docketNum.matches("[0-9]{16}");
    }

    // Generate a unique docket number
    public static String generateDocketNum(ArrayList<Docket> dockets) {

        String docketnum;

        do {
            StringBuilder number = new StringBuilder();

            for (int i = 0; i < DOCKET_NUM_LENGTH; i++) {
                number.append(random.nextInt(10));
            }

            docketnum = number.toString();

        } while (docketExists(docketnum, dockets));

        return docketnum;
    }

    private static boolean docketExists(String docketnum,
                                        ArrayList<Docket> dockets) {

        for (Docket docket : dockets) {
            if (docketnum.equals(docket.getDocketnum())) {
                return true;
            }
        }

        return false;
    }

    // Generate a unique order number
    private String generateOrderNum() {

        String orderNum;

        do {
            StringBuilder number = new StringBuilder();

            for (int i = 0; i < ORDER_NUM_LENGTH; i++) {
                number.append(random.nextInt(10));
            }

            orderNum = number.toString();

        } while (getOrder(orderNum) != null);

        return orderNum;
    }

    // Create
    public Docket create(boolean stock, double area, String address,
                         String customerNum, Publications[] publications,
                         ArrayList<Docket> dockets) {

        if (!isValidArea(area)
                || address == null || address.trim().isEmpty()
                || !isValidCustomerNum(customerNum)
                || publications == null || publications.length == 0) {
            return null;
        }

        String docketnum = generateDocketNum(dockets);

        Docket newDocket = new Docket(
                stock, docketnum, area, address,
                customerNum, publications
        );

        dockets.add(newDocket);

        return newDocket;
    }

    // Read one docket
    public Docket read(String docketnum, ArrayList<Docket> dockets) {

        if (!isValidDocketNum(docketnum)) {
            return null;
        }

        for (Docket docket : dockets) {
            if (docketnum.equals(docket.getDocketnum())) {
                return docket;
            }
        }

        return null;
    }

    // Read all dockets
    public ArrayList<Docket> readAll(ArrayList<Docket> dockets) {
        return new ArrayList<>(dockets);
    }

    // Update address, area and publications.
    // Customer ID remains unchanged.
    public Docket update(String docketnum, String newAddress,
                         double newArea, Publications[] newPublications,
                         ArrayList<Docket> dockets) {

        if (!isValidDocketNum(docketnum)
                || newAddress == null || newAddress.trim().isEmpty()
                || !isValidArea(newArea)
                || newPublications == null || newPublications.length == 0) {
            return null;
        }

        for (Docket docket : dockets) {

            if (docketnum.equals(docket.getDocketnum())) {

                // Get the existing customer number
                String customerNum = null;

                if (docket.getOrderCount() > 0) {
                    customerNum = docket.getOrder(
                        getFirstOrderNum(docket)
                    ).getCustomerNum();
                }

                if (customerNum == null) {
                    return null;
                }

                // Validate all new publications before changing anything
                for (Publications publication : newPublications) {
                    if (!isValidPublication(publication)) {
                        return null;
                    }
                }

                // Delete the old orders
                deleteAllOrders(docket);

                // Create orders for the new publication preferences
                for (Publications publication : newPublications) {
                    docket.createOrder(
                        docket.generateOrderNum(),
                        customerNum,
                        publication
                    );
                }

                // Update docket details
                docket.setAddress(newAddress);
                docket.setArea(newArea);

                return docket;
            }
        }

        return null;
    }

    // Get the first order number
    private String getFirstOrderNum(Docket docket) {
        // This method is a placeholder because OrderBook
        // does not provide a method to list all order numbers.
        return null;
    }

    // Delete all orders
    private void deleteAllOrders(Docket docket) {
        // OrderBook does not provide a method to list all orders.
        // See explanation below.
    }

    // Delete docket
    public boolean delete(String docketnum, ArrayList<Docket> dockets) {

        if (!isValidDocketNum(docketnum)) {
            return false;
        }

        for (int i = 0; i < dockets.size(); i++) {
            if (docketnum.equals(dockets.get(i).getDocketnum())) {
                dockets.remove(i);
                return true;
            }
        }

        return false;
    }

    // Sort dockets by docket number
    public ArrayList<Docket> sortDockets(ArrayList<Docket> dockets) {

        ArrayList<Docket> sorted = new ArrayList<>(dockets);

        sorted.sort((d1, d2) ->
                d1.getDocketnum().compareTo(d2.getDocketnum()));

        return sorted;
    }
}
