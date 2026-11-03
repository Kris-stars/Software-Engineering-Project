import java.util.List;

public class Publications {
    public static final int PUBLICATIONS_ID_LENGTH = 16;
    public static final double MIN_PRICE = 0.01;

    private final String publicationsID;
    private String frequency;
    private double price;

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

    public static boolean isValidPublicationsID(String publicationsID) {
        return publicationsID != null && publicationsID.length() == PUBLICATIONS_ID_LENGTH;
    }

    public static boolean isValidFrequency(String frequency) {
        return frequency != null && (frequency.equalsIgnoreCase("Daily") || frequency.equalsIgnoreCase("Weekly") || frequency.equalsIgnoreCase("Monthly"));
    }

    public static boolean isValidPrice(double price) {
        return Double.isFinite(price) && price >= MIN_PRICE;
    }

    public String getPublicationsID() { return publicationsID; }
    public String getFrequency() { return frequency; }
    public double getPrice() { return price; }

    public static Publications read(String id, List<Publications> publications) {
        if (!isValidPublicationsID(id) || publications == null) { return null; }
        for (Publications publication : publications) 
        {
            if (publication != null && id.equals(publication.getPublicationsID())) { return publication; }
        }
        return null;
    }

    public static Publications update(String id, String newFrequency, double newPrice, List<Publications> publications) {
        if (!isValidFrequency(newFrequency) || !isValidPrice(newPrice)) { return null; }
        Publications publication = read(id, publications);
        if (publication == null) { return null; }
        publication.frequency = newFrequency;
        publication.price = newPrice;
        return publication;
    }