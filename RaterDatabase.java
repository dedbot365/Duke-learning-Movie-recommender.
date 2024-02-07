//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class RaterDatabase {
    private static HashMap<String, Rater> ourRaters;

    public RaterDatabase() {
    }

    private static void initialize() {
        if (ourRaters == null) {
            ourRaters = new HashMap();
        }

    }

    public static void initialize(String filename) {
        if (ourRaters == null) {
            ourRaters = new HashMap();
            addRatings("data/" + filename);
        }

    }

    public static void addRatings(String filename) {
        initialize();
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();
        Iterator var3 = csvp.iterator();

        while(var3.hasNext()) {
            CSVRecord rec = (CSVRecord)var3.next();
            String id = rec.get("rater_id");
            String item = rec.get("movie_id");
            String rating = rec.get("rating");
            addRaterRating(id, item, Double.parseDouble(rating));
        }

    }

    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize();
        Rater rater = null;
        if (ourRaters.containsKey(raterID)) {
            rater = (Rater)ourRaters.get(raterID);
        } else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID, rater);
        }

        ((Rater)rater).addRating(movieID, rating);
    }

    public static Rater getRater(String id) {
        initialize();
        return (Rater)ourRaters.get(id);
    }

    public static ArrayList<Rater> getRaters() {
        initialize();
        ArrayList<Rater> list = new ArrayList(ourRaters.values());
        return list;
    }

    public static int size() {
        return ourRaters.size();
    }
}
