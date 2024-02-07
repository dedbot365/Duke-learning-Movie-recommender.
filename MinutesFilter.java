
/**
 * The MinutesFilter class implements the Filter interface to filter movies based on their duration
 * falling within a specified range of minutes.
 * 
 * Example usage:
 * MinutesFilter filter = new MinutesFilter(90, 120);
 * ArrayList<String> filteredMovies = MovieDatabase.filterBy(filter);
 * 
 * This class provides a method to check if a movie satisfies the filter criteria based on its duration.
 * It implements the satisfies method defined in the Filter interface to determine if a movie's duration
 * falls within the specified range.
 * 
 * The constructor of MinutesFilter initializes the minimum and maximum duration values for filtering.
 * 
 * @author Subigyan Paudel
 * @version 2024-02-07
 */

public class MinutesFilter implements Filter {
    private int min;
    private int max;
    
    
    public MinutesFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max;
    }
}
