//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MovieDatabase {
    private static HashMap<String, Movie> ourMovies;

    public MovieDatabase() {
    }

    public static void initialize(String moviefile) {
        if (ourMovies == null) {
            ourMovies = new HashMap();
            loadMovies("data/" + moviefile);
        }

    }

    private static void initialize() {
        if (ourMovies == null) {
            ourMovies = new HashMap();
            loadMovies("data/ratedmoviesfull.csv");
        }

    }

    private static void loadMovies(String filename) {
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> list = fr.loadMovies(filename);
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            Movie m = (Movie)var3.next();
            ourMovies.put(m.getID(), m);
        }

    }

    public static boolean containsID(String id) {
        initialize();
        return ourMovies.containsKey(id);
    }

    public static int getYear(String id) {
        initialize();
        return ((Movie)ourMovies.get(id)).getYear();
    }

    public static String getGenres(String id) {
        initialize();
        return ((Movie)ourMovies.get(id)).getGenres();
    }

    public static String getTitle(String id) {
        initialize();
        return ((Movie)ourMovies.get(id)).getTitle();
    }

    public static Movie getMovie(String id) {
        initialize();
        return (Movie)ourMovies.get(id);
    }

    public static String getPoster(String id) {
        initialize();
        return ((Movie)ourMovies.get(id)).getPoster();
    }

    public static int getMinutes(String id) {
        initialize();
        return ((Movie)ourMovies.get(id)).getMinutes();
    }

    public static String getCountry(String id) {
        initialize();
        return ((Movie)ourMovies.get(id)).getCountry();
    }

    public static String getDirector(String id) {
        initialize();
        return ((Movie)ourMovies.get(id)).getDirector();
    }

    public static int size() {
        return ourMovies.size();
    }

    public static ArrayList<String> filterBy(Filter f) {
        initialize();
        ArrayList<String> list = new ArrayList();
        Iterator var2 = ourMovies.keySet().iterator();

        while(var2.hasNext()) {
            String id = (String)var2.next();
            if (f.satisfies(id)) {
                list.add(id);
            }
        }

        return list;
    }
}
