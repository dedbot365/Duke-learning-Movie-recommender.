/**
 * The EfficientRater class represents a rater with efficient storage of ratings using HashMap.
 * 
 * Example usage:
 * EfficientRater rater = new EfficientRater("123");
 * rater.addRating("tt1234567", 8.5);
 * boolean hasRating = rater.hasRating("tt1234567");
 * String id = rater.getID();
 * double rating = rater.getRating("tt1234567");
 * int numRatings = rater.numRatings();
 * ArrayList<String> itemsRated = rater.getItemsRated();
 * 
 * This class provides methods to add a rating, check if a rater has rated a particular item, 
 * retrieve the ID of the rater, retrieve the rating of a particular item, 
 * get the total number of ratings made by the rater, and get a list of items rated by the rater.
 * 
 * @author Subigyan Paudel
 * @version 2024-02-07
 */


import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        this.myID = id;
        this.myRatings = new HashMap();
    }

    public void addRating(String item, double rating) {
        this.myRatings.put(item, new Rating(item, rating));
    }

    public boolean hasRating(String item) {
        return this.myRatings.containsKey(item);
    }

    public String getID() {
        return this.myID;
    }

    public double getRating(String item) {
        return this.myRatings.containsKey(item) ? ((Rating)this.myRatings.get(item)).getValue() : -1.0;
    }

    public int numRatings() {
        return this.myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList(this.myRatings.keySet());
        return list;
    }
}
