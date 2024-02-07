/**
 * The MovieRunnerSimilarRatingsOptimizedBysubu class provides a method to print similar ratings
 * for a specific user using an optimized version of the FourthRatingsOptimizedBysubu class.
 * This class demonstrates the recommendation system by printing the top similar ratings for a user.
 * It also contains a main method to test the functionality of the class.
 * 
 * Example usage:
 * MovieRunnerSimilarRatingsOptimizedBysubu mra = new MovieRunnerSimilarRatingsOptimizedBysubu();
 * mra.printSimilarRatings();
 * 
 * This class calculates and prints similar ratings for a specified user, utilizing an optimized version
 * of the FourthRatings algorithm. It prints the found ratings along with their corresponding movies.
 * 
 * The main method demonstrates the usage of this class by initializing movie and rater databases,
 * printing their sizes, and executing the printSimilarRatings method, measuring the execution time.
 * 
 * @author Subigyan Paudel
 * @version 2024-02-07
 */

import java.util.ArrayList;

public class MovieRunnerSimilarRatingsOptimizedBysubu {
    public MovieRunnerSimilarRatingsOptimizedBysubu() {
    }

    public void printSimilarRatings() {
        FourthRatingsOptimizedBysubu tr = new FourthRatingsOptimizedBysubu();
        ArrayList<Rating> ratingList = tr.getSimilarRatings("65", 20, 5);
        System.out.println("Found ratings for movies : " + ratingList.size());

        for(int i = 0; i < 3; ++i) {
            System.out.printf("%-10.2f%s%n", ((Rating)ratingList.get(i)).getValue(), MovieDatabase.getTitle(((Rating)ratingList.get(i)).getItem()));
        }

    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatingsOptimizedBysubu mra = new MovieRunnerSimilarRatingsOptimizedBysubu();
        System.out.println("DUKE:   \"sum of (similar rating(i) *rating of the movie(i))/count of the raters\"");
        System.out.println("WEI XU: \"sum of (similar rating(i) *rating of the movie(i))/ sum of the similar rating(i)\", will achieve better results.");
        System.out.println("-----------The FOLLOWING RESULTS are Algorithm by DUKE -------------");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Movie size (# of movie in list) : " + MovieDatabase.size());
        System.out.println("Rater size (# of ppl who rates) : " + RaterDatabase.size());
        System.out.println("---------------Test: printSimilarRatings()----------------");
        double start1 = (double)System.nanoTime();
        mra.printSimilarRatings();
        double duration1 = ((double)System.nanoTime() - start1) / 1.0E9;
        System.out.println("---------------Duration = " + duration1 + "s-------------");
    }
}
