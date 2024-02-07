/**
 * The RecommendationRunner class implements the Recommender interface
 * to provide recommendations for a web user based on movie ratings.
 * 
 * Example usage:
 * RecommendationRunner runner = new RecommendationRunner();
 * ArrayList<String> itemsToRate = runner.getItemsToRate();
 * runner.printRecommendationsFor("webRaterID");
 * 
 * This class selects a set of movies for the user to rate using the getItemsToRate method.
 * It then generates recommendations for the user based on their ratings using the printRecommendationsFor method.
 * 
 * The getItemsToRate method selects random movies from the MovieDatabase for the user to rate.
 * 
 * The printRecommendationsFor method initializes the movie and rater databases, computes similarity ratings
 * using the FourthRatingsOptimizedBysubu class, and generates recommendations for the user.
 * It prints the recommendations in an HTML table format with movie details including poster, title, rating, genre, and country.
 * 
 * @author [Author's Name]
 * @version [Version Number]
 */

/**
 * The RecommendationRunner class implements the Recommender interface
 * to provide recommendations for a web user based on movie ratings.
 * 
 * Example usage:
 * RecommendationRunner runner = new RecommendationRunner();
 * ArrayList<String> itemsToRate = runner.getItemsToRate();
 * runner.printRecommendationsFor("webRaterID");
 * 
 * This class selects a set of movies for the user to rate using the getItemsToRate method.
 * It then generates recommendations for the user based on their ratings using the printRecommendationsFor method.
 * 
 * The getItemsToRate method selects random movies from the MovieDatabase for the user to rate.
 * 
 * The printRecommendationsFor method initializes the movie and rater databases, computes similarity ratings
 * using the FourthRatingsOptimizedBysubu class, and generates recommendations for the user.
 * It prints the recommendations in an HTML table format with movie details including poster, title, rating, genre, and country.
 * 
 * @author Subigyan Paudel
 * @version 2024-07-02
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RecommendationRunner implements Recommender {
    public RecommendationRunner() {
    }

    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movieToBeRate = new ArrayList();
        ArrayList<String> movieID = MovieDatabase.filterBy(new TrueFilter());

        for(int i = 0; movieToBeRate.size() < 10; ++i) {
            Random ran = new Random();
            int random = ran.nextInt(movieID.size());
            if (!movieToBeRate.contains(movieID.get(random))) {
                movieToBeRate.add((String)movieID.get(random));
            }
        }

        return movieToBeRate;
    }

    public void printRecommendationsFor(String webRaterID) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatingsOptimizedBysubu fr = new FourthRatingsOptimizedBysubu();
        ArrayList<Rating> ratingList = fr.getSimilarRatings(webRaterID, 20, 5);
        if (ratingList.size() == 0) {
            System.out.println("<h2>Sorry, there are no movie recommend for you based on your rating!</h2>");
        } else {
            ArrayList<String> movieToBeRate = this.getItemsToRate();
            ArrayList<Rating> outID = new ArrayList();
            int count = 0;

            int rank;
            for(rank = 0; outID.size() + count != ratingList.size() && outID.size() < 10; ++rank) {
                if (!movieToBeRate.contains(((Rating)ratingList.get(rank)).getItem())) {
                    outID.add((Rating)ratingList.get(rank));
                } else {
                    ++count;
                }
            }

            System.out.println("outid size = " + outID.size());
            System.out.println("<style>");
            System.out.println("h2,h3{");
            System.out.println("  text-align: center;");
            System.out.println("  height: 50px;");
            System.out.println("  line-height: 50px;");
            System.out.println("  font-family: Arial, Helvetica, sans- serif;");
            System.out.println("  background-color: black;");
            System.out.println("   color:  #ff6600 }");
            System.out.println(" table {");
            System.out.println("   border-collapse: collapse;");
            System.out.println("   margin: auto;}");
            System.out.println("table, th, td {");
            System.out.println("    border: 2px solid white;");
            System.out.println("    font-size: 15px;");
            System.out.println("    padding: 2px 6px 2px 6px; }");
            System.out.println(" td img{");
            System.out.println("    display: block;");
            System.out.println("    margin-left: auto;");
            System.out.println("    margin-right: auto; }");
            System.out.println("th {");
            System.out.println("    height: 40px;");
            System.out.println("    font-size: 18px;");
            System.out.println("  background-color: black;");
            System.out.println(" color: white;");
            System.out.println("text-align: center; }");
            System.out.println(" tr:nth-child(even) {");
            System.out.println("     background-color: #f2f2f2; }");
            System.out.println("  tr:nth-child(odd) {");
            System.out.println("background-color: #cccccc; }");
            System.out.println(" tr:hover {");
            System.out.println(" background-color: #666666; ");
            System.out.println("  color:white;}");
            System.out.println("table td:first-child {");
            System.out.println(" text-align: center; }");
            System.out.println(" tr {");
            System.out.println(" font-family: Arial, Helvetica, sans-serif; }");
            System.out.println(".rating{");
            System.out.println("    color:#ff6600;");
            System.out.println("    padding: 0px 10px;");
            System.out.println("   font-weight: bold; }");
            System.out.println("</style>");
           
            System.out.println("<table id = \"rater\">");
            System.out.println("<tr>");
            System.out.println("<th>Rank</th>");
            System.out.println("<th>Poster</th>");
            System.out.println("<th>Title & Rating</th>");
            System.out.println("<th>Genre</th>");
            System.out.println("<th>Country</th>");
            System.out.println("</tr>");
            rank = 1;

            for(Iterator var8 = outID.iterator(); var8.hasNext(); ++rank) {
                Rating i = (Rating)var8.next();
                System.out.println("<tr><td>" + rank + "</td><td><img src = \"" + MovieDatabase.getPoster(i.getItem()) + "\" width=\"50\" height=\"70\"></td> <td>" + MovieDatabase.getYear(i.getItem()) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" + i.getItem() + "\">" + MovieDatabase.getTitle(i.getItem()) + "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;" + String.format("%.1f", i.getValue()) + "/10</td><td>" + MovieDatabase.getGenres(i.getItem()) + "</td><td>" + MovieDatabase.getCountry(i.getItem()) + "</td></tr> ");
            }
        }

        System.out.println("</table>");
        
    }
}
