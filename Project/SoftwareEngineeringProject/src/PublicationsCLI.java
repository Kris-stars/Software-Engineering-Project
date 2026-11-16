import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PublicationsCLI 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        run(scanner);
        // Not closing System.in, another CLI might need it
    }
    
    public static void run(Scanner scanner)
    {
        PublicationsDAO dao = new PublicationsDAO();
        boolean running = true;

        while (running)
        {
            System.out.println("\n=== Publications Menu ===");
            System.out.println("1. Add Publication");
            System.out.println("2. View All Publications");
            System.out.println("3. Search Publications by ID");
            System.out.println("4. Update Publication");
            System.out.println("5. Delete Publication");
            System.out.println("0. Exit Publications Menu");
            System.out.print("Enter Choice:");

            String choice = scanner.nextLine().trim();
            try
            {
                switch (choice)
                {
                    case "1": addPublication(scanner, dao); break;
                    case "2": viewAllPublications(dao); break;
                    case "3": searchPublication(scanner, dao); break;
                    case "4": updatePublication(scanner, dao); break;
                    case "5": deletePublication(scanner, dao); break;
                    case "0": running = false; break;
                    default: System.out.println("Invalid choice. Enter 0-5");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input: " + e.getMessage());
            }
        }
        System.out.println("Leaving Publications Menu.");
    }

    private static void addPublication(Scanner scanner, PublicationsDAO dao) throws SQLException
    {
        System.out.println("\n=== Add Publication ===");
        String id = readId(scanner);
        if (dao.getById(id) != null)
        {
            System.out.println("A publication with that ID already exists.");
            return;
        }
        String frequency = readFrequency(scanner);
        double price = readPrice(scanner);
        Publications publication = new Publications(id, frequency, price);
        dao.insert(publication);
        System.out.println("Publication saved succesfully");
    }

    private static void viewAllPublications(PublicationsDAO dao) throws SQLException
    {
        System.out.println("\n=== All Publications ===");
        List<Publications> publications = dao.getAll();
        if (publications.isEmpty())
        {
            System.out.println("No publications found.");
            return;
        }
        for (Publications publication : publications) { printPublication(publication); }
    }

    private static void searchPublication(Scanner scanner, PublicationsDAO dao) throws SQLException
    {
        System.out.println("\n=== Search Publication===");
        Publications publication = dao.getById(readId(scanner));
        if (publication == null)
        {
            System.out.println("Publication not found.");
        } else
        {
            printPublication(publication);
        }
    }

    private static void updatePublication(Scanner scanner, PublicationsDAO dao) throws SQLException
    {
        System.out.println("\n=== Update Publication ===");
        String id = readId(scanner);
        Publications existing = dao.getById(id);
        if (existing == null)
        {
            System.out.println("Publication not found.");
            return;
        }
        System.out.println("Current Publication:");
        printPublication(existing);
        System.out.println("Enter the new values: ");
        String frequency = readFrequency(scanner);
        double price = readPrice(scanner);
        Publications updated = new Publications(id, frequency, price);
        if (dao.update(updated))
        {
            System.out.println("Publication updated successfully.");
        } else
        {
            System.out.println("Publication could not be updated.");
        }
    }

    private static void deletePublication(Scanner scanner, PublicationsDAO dao) throws SQLException
    {
        System.out.println("\n=== Delete Publication ===");
        String id = readId(scanner);
        Publications existing = dao.getById(id);
        if (existing == null)
        {
            System.out.println("Publication not found.");
            return;
        }
        printPublication(existing);
        System.out.print("Delete this publication (Yes/No): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("yes"))
        {
            System.out.println("Deletion Cancelled.");
            return;
        }
        if (dao.delete(id))
        {
            System.out.println("Publication Deleted Successfully.");
        } else
        {
            System.out.println("Publication could not be deleted.");
        }
    }

    private static String readId(Scanner scanner)
    {
        while (true)
        {
            System.out.print("Publication ID (16 characters): ");
            String id = scanner.nextLine().trim();
            if (Publications.isValidPublicationsID(id))  { return id; }
            System.out.println("ID must be exactly 16 characters.");
        }
    }

    private static String readFrequency(Scanner scanner)
    {
        while (true)
        {
            System.out.print("Frequency (Daily/Weekly/Monthly): ");
            String frequency = scanner.nextLine().trim();
            if (Publications.isValidFrequency(frequency)) { return frequency; }
            System.out.println("Frequency must be Daily, Weekly or Monthly.");
        }
    }

    private static double readPrice(Scanner scanner)
    {
        while (true) 
        { 
            System.out.print("Price: ");
            String input = scanner.nextLine().trim();
            try
            {
                double price = Double.parseDouble(input);
                if (Publications.isValidPrice(price)) { return price; }
            } catch (NumberFormatException e)
            {
                System.out.println("Not a numeric , please retry");
            }
            System.out.println("Enter a valid price of at least 0.01.");
        }
    }

    private static void printPublication(Publications publication)
    {
        System.out.println("------------------------------");
        System.out.println("ID: " + publication.getPublicationsID());
        System.out.println("Frequency: " + publication.getFrequency());
        System.out.printf("Price: %.2f%n", publication.getPrice());
    }
}
