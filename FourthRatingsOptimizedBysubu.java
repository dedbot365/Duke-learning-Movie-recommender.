
/**
 * The FourthRatingsOptimizedBysubu class provides methods to compute similarity ratings between raters
 * and generate weighted average movie ratings using the similarity ratings.
 * 
 * Example usage:
 * FourthRatingsOptimizedBysubu ratings = new FourthRatingsOptimizedBysubu();
 * ArrayList<Rating> similarRatings = ratings.getSimilarRatings("2", 3, 0);
 * 
 * This class contains methods to compute similarity ratings between a specified rater and others,
 * as well as methods to generate weighted average movie ratings using these similarity ratings.
 * 
 * The dotProduct method computes the dot product of ratings for movies that both raters have rated,
 * and the getSimilarities method calculates similarity ratings for each rater in the RaterDatabase.
 * 
 * The getSimilarRatings method returns a list of movies and their weighted average ratings,
 * considering only the top numSimilarRaters with positive ratings and movies with at least minimalRaters ratings.
 * 
 * The getSimilarRatingsByFilter method extends the functionality of getSimilarRatings
 * by allowing the use of a filter to further refine the selection of movies.
 * 
 * The main method serves as a test case demonstrating the functionality of the class
 * by initializing movie and rater databases and calling the getSimilarRatings method.
 * 
 * @author Subigyan Paudel
 * @version 2024-07-02
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class FourthRatingsOptimizedBysubu {
    public FourthRatingsOptimizedBysubu() {
    }

    private double dotProduct(Rater me, Rater r) {
        double dp = 0.0;
        ArrayList<String> memovieid = me.getItemsRated();
        Iterator var6 = memovieid.iterator();

        while(var6.hasNext()) {
            String id = (String)var6.next();
            if (r.getItemsRated().contains(id)) {
                dp += (me.getRating(id) - 5.0) * (r.getRating(id) - 5.0);
            }
        }

        return dp;
    }

    private ArrayList<Rating> getSimilarities(String raterId) {
        ArrayList<Rating> simiList = new ArrayList();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Iterator var4 = raters.iterator();

        while(var4.hasNext()) {
            Rater r = (Rater)var4.next();
            if (!r.getID().equals(raterId)) {
                double dotProduct = this.dotProduct(RaterDatabase.getRater(raterId), r);
                if (dotProduct > 0.0) {
                    simiList.add(new Rating(r.getID(), dotProduct));
                }
            }
        }

        Collections.sort(simiList);
        Collections.reverse(simiList);
        return simiList;
    }

    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> ratingList = new ArrayList();
        ArrayList<String> movidIDByTopSimilar = new ArrayList();
        ArrayList<Rating> simiList1 = this.getSimilarities(raterID);
        int num = simiList1.size();
        if (numSimilarRaters > num) {
            numSimilarRaters = num;
        }

        String j;
        for(int i = 0; i < numSimilarRaters; ++i) {
            j = ((Rating)simiList1.get(i)).getItem();
            ArrayList<String> movieRated1 = RaterDatabase.getRater(j).getItemsRated();
            Iterator var11 = movieRated1.iterator();

            while(var11.hasNext()) {
                String movieID = (String)var11.next();
                if (!movidIDByTopSimilar.contains(movieID)) {
                    movidIDByTopSimilar.add(movieID);
                }
            }
        }

        Iterator var20 = movidIDByTopSimilar.iterator();

        while(var20.hasNext()) {
            j = (String)var20.next();
            double ave = 0.0;
            ArrayList<Rating> simiList = this.getSimilarities(raterID);
            int count = 0;
            double total = 0.0;
            int simiweighttotal = 0;

            for(int i = 0; i < numSimilarRaters; ++i) {
                double rating = RaterDatabase.getRater(((Rating)simiList.get(i)).getItem()).getRating(j);
                if (rating != -1.0) {
                    ++count;
                    total += rating * ((Rating)simiList.get(i)).getValue();
                    simiweighttotal = (int)((double)simiweighttotal + ((Rating)simiList.get(i)).getValue());
                }
            }

            if (count >= minimalRaters) {
                ave = total / (double)simiweighttotal;
            }

            if (ave > 0.0) {
                ratingList.add(new Rating(j, ave));
            }
        }

        Collections.sort(ratingList);
        Collections.reverse(ratingList);
        return ratingList;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String raterID, int numSimilarRaters, int minimalRaters, Filter f) {
        ArrayList<Rating> ratingList = new ArrayList();
        ArrayList<String> movidIDByTopSimilar = new ArrayList();
        ArrayList<Rating> simiList1 = this.getSimilarities(raterID);

        String j;
        for(int i = 0; i < numSimilarRaters; ++i) {
            j = ((Rating)simiList1.get(i)).getItem();
            ArrayList<String> movieRated1 = RaterDatabase.getRater(j).getItemsRated();
            Iterator var11 = movieRated1.iterator();

            while(var11.hasNext()) {
                String movieID = (String)var11.next();
                if (!movidIDByTopSimilar.contains(movieID)) {
                    movidIDByTopSimilar.add(movieID);
                }
            }
        }

        Iterator var21 = movidIDByTopSimilar.iterator();

        while(true) {
            do {
                if (!var21.hasNext()) {
                    Collections.sort(ratingList);
                    Collections.reverse(ratingList);
                    return ratingList;
                }

                j = (String)var21.next();
            } while(!f.satisfies(j));

            double ave = 0.0;
            ArrayList<Rating> simiList = this.getSimilarities(raterID);
            int count = 0;
            double total = 0.0;
            double simiweighttotal = 0.0;

            for(int i = 0; i < numSimilarRaters; ++i) {
                double rating = RaterDatabase.getRater(((Rating)simiList.get(i)).getItem()).getRating(j);
                if (rating != -1.0) {
                    ++count;
                    total += rating * ((Rating)simiList.get(i)).getValue();
                    simiweighttotal += ((Rating)simiList.get(i)).getValue();
                }
            }

            if (count >= minimalRaters) {
                ave = total / simiweighttotal;
            }

            if (ave > 0.0) {
                ratingList.add(new Rating(j, ave));
            }
        }
    }

    public static void main(String[] args) {
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");
        FourthRatingsOptimizedBysubu sr = new FourthRatingsOptimizedBysubu();
        System.out.println("---------------test-------------");
        System.out.println(sr.getSimilarRatings("2", 3, 0));
    }
}
