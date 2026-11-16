import java.util.Scanner;
import java.sql.SQLException;
public class BillCLI {
	
	 public static void main(String[] args) {

		 Scanner scanner = new Scanner(System.in);

	        System.out.println("=== Create New Bill ===");

	        System.out.print("Area (1-13): ");
	        String areaInput = scanner.nextLine();

	        System.out.print("Customer Number (16 characters): ");
	        String customerNum = scanner.nextLine();

	        System.out.print("Docket Number (16 characters): ");
	        String docketnum = scanner.nextLine();

	        try {

	            double area = Double.parseDouble(areaInput);

	            Bill bill = new Bill(area, customerNum, docketnum);

	            BillDAO dao = new BillDAO();

	            dao.insert(bill);

	            System.out.println("Bill saved successfully.");

	        } catch (IllegalArgumentException e) {

	            System.out.println("Invalid input: " + e.getMessage());

	        } catch (SQLException e) {

	            System.out.println("Database error: " + e.getMessage());

	        }

	        scanner.close();
	    }

}
