/**
 * The Rating class represents a movie rating given by a rater.
 * It implements the Comparable interface to allow comparison of ratings based on their values.
 * 
 * Example usage:
 * Rating rating = new Rating("movieID", 8.5);
 * String movieID = rating.getItem();
 * double value = rating.getValue();
 * int comparison = rating.compareTo(anotherRating);
 * 
 * This class provides methods to access the item (movie ID) and value (rating) of a rating,
 * as well as a toString method for string representation of a rating.
 * 
 * The compareTo method compares two ratings based on their values.
 * 
 * @author Subigyan Paudel
 * @version 2024-07-02
 */


public class Rating implements Comparable<Rating> {
    private String item;
    private double value;

    public Rating(String anItem, double aValue) {
        this.item = anItem;
        this.value = aValue;
    }

    public String getItem() {
        return this.item;
    }

    public double getValue() {
        return this.value;
    }

    public String toString() {
        return "[" + this.getItem() + ", " + this.getValue() + "]";
    }

    public int compareTo(Rating other) {
        if (this.value < other.value) {
            return -1;
        } else {
            return this.value > other.value ? 1 : 0;
        }
    }
}
