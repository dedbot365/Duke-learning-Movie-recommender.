//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;

public interface Rater {
    void addRating(String var1, double var2);

    boolean hasRating(String var1);

    String getID();

    double getRating(String var1);

    int numRatings();

    ArrayList<String> getItemsRated();
}
