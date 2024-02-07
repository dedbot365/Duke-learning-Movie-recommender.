
/**
 * The SecondRatings class represents a system for analyzing movie ratings.
 * It loads movie and rater data from CSV files and provides methods to 
 * calculate average ratings for movies and retrieve movie information.
 * 
 * Example usage:
 * SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
 * int movieSize = sr.getMovieSize();
 * int raterSize = sr.getRaterSize();
 * ArrayList<Rating> averageRatings = sr.getAverageRatings(2);
 * String movieTitle = sr.getTitle("0790636");
 * String movieID = sr.getID("Interstellar");
 * 
 * This class provides methods to get the total number of movies, 
 * the total number of raters, calculate average ratings for movies with 
 * a minimum number of raters, get the title of a movie by its ID, 
 * and get the ID of a movie by its title.
 * 
 * @author Subigyan Paudel
 * @version 2024-02-07
 */


import java.util.ArrayList;


public class SecondRatings {
    
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    //---------------todo: why only above won't compile???-------------
    
    public SecondRatings(String movieFile, String ratingFile) {
        FirstRatings a = new FirstRatings();
        myMovies = a.loadMovies(movieFile);
        myRaters = a.loadRaters(ratingFile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    //private helper method
    private double getAverageByID(String movieID, int minimalRaters) {
        int count = 0;
        double total = 0;
        for (Rater i : myRaters) {
            // if (i.hasRating(movieID)) {
            double rating = i.getRating(movieID);
            if (rating != -1) {
                count++;
                total += rating;
                // System.out.println(count + " : " + "id = " + i.getID() + " rating " + rating + " ave " + total);
            }
        }
        //System.out.println("Movie ID = " + movieID + " : " + count + " : " + total + " : " + total / count);
        if (count >= minimalRaters) return total / count;
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        for (Movie i : myMovies) {
            double ave = getAverageByID(i.getID(), minimalRaters);
            if (ave > 0)
                ratingList.add(new Rating(i.getID(), ave));//item is string id?
        }
        return ratingList;
    }
    
    public String getTitle(String movieID) {
        for (Movie i : myMovies) {
            if (i.getID().equals(movieID)) {
                return i.getTitle();
            }
        }
        return "The Movie ID was not found!";
    }
    
    public String getID(String title) {
        for (Movie i : myMovies) {
            if (i.getTitle().equals(title)) {
                return i.getID();
            }
        }
        return "NO SUCH TITLE";
    }
    
    public static void main(String[] args) {
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        //System.out.println(sr.getAverageByID("0790636", 2));
        System.out.println("---------------test-------------");
        System.out.println(sr.getAverageRatings(2));
        //[[6414, 0.0], [68646, 0.0], [113277, 0.0], [1798709, 8.25], [790636, 0.0]]
    }
    
}
