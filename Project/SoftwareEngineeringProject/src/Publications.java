
import java.util.List;

public class Publications {

    public static final int PUBLICATIONS_ID_LENGTH = 16;

    public static final double MIN_PRICE = 0.01;

    private final String publicationsID;

    private String frequency;

    private double price;


    // CREATE
    public Publications(String publicationsID, String frequency, double price) {

        if (!isValidPublicationsID(publicationsID))
            throw new IllegalArgumentException("Invalid Publications ID");

        if (!isValidFrequency(frequency))
            throw new IllegalArgumentException("Invalid Frequency");

        if (!isValidPrice(price))
            throw new IllegalArgumentException("Invalid Price");

        this.publicationsID = publicationsID;

        this.frequency = frequency;

        this.price = price;
    }


    // VALIDATION
    public static boolean isValidPublicationsID(String publicationsID) {

        return publicationsID != null && publicationsID.length() == PUBLICATIONS_ID_LENGTH;
    }

    public static boolean isValidFrequency(String frequency) {

        return frequency != null && (frequency.equalsIgnoreCase("Daily")
                || frequency.equalsIgnoreCase("Weekly")
                || frequency.equalsIgnoreCase("Monthly"));
    }

    public static boolean isValidPrice(double price) {

        return Double.isFinite(price) && price >= MIN_PRICE;
    }


    // GETTERS
    public String getPublicationsID() { return publicationsID; }

    public String getFrequency() { return frequency; }

    public double getPrice() { return price; }


    // READ
    public static Publications read(String id, List<Publications> publications) {

        if (!isValidPublicationsID(id) || publications == null) { return null; }

        for (Publications publication : publications) {

            if (publication != null && id.equals(publication.getPublicationsID())) {
                return publication;
            }
        }

        return null;
    }


    // UPDATE
    public static Publications update(String id, String newFrequency, double newPrice,
                                      List<Publications> publications) {

        if (!isValidFrequency(newFrequency) || !isValidPrice(newPrice)) { return null; }

        Publications publication = read(id, publications);

        if (publication == null) { return null; }

        publication.frequency = newFrequency;

        publication.price = newPrice;

        return publication;
    }


    // DELETE
    public static boolean delete(String id, List<Publications> publications) {

        // Check that the ID is valid and the list exists.
        if (!isValidPublicationsID(id) || publications == null) { return false; }

        // Search for the publication using the existing READ method.
        Publications publication = read(id, publications);

        // If no matching publication exists, deletion cannot happen.
        if (publication == null) { return false; }

        // Remove the matching publication from the list.
        // This changes the original list, not the publication object itself.
        publications.remove(publication);

        // Return true to confirm the publication was removed.
        return true;
    }
}
