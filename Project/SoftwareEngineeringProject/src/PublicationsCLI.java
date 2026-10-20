import java.util.Scanner;

public class PublicationsCLI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Create New Publication ===");

        System.out.print("Publication ID (16 characters): ");
        String id = scanner.nextLine();

        System.out.print("Frequency (Daily/Weekly/Monthly): ");
        String frequency = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        try {
            Publications publication =
                new Publications(id, frequency, price);

            PublicationsDAO dao = new PublicationsDAO();

            if (dao.addPublication(publication)) {
                System.out.println("Publication saved successfully.");
            } else {
                System.out.println("Failed to save publication.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }

        scanner.close();
    }
}
