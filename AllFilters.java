/**
 * The AllFilters class represents a filter that combines multiple filters
 * and checks whether an item satisfies all of them.
 * 
 * Example usage:
 * AllFilters allFilters = new AllFilters();
 * GenreFilter genreFilter = new GenreFilter("Comedy");
 * YearAfterFilter yearAfterFilter = new YearAfterFilter(2000);
 * allFilters.addFilter(genreFilter);
 * allFilters.addFilter(yearAfterFilter);
 * boolean satisfiesAllFilters = allFilters.satisfies("movieID");
 * 
 * This class provides methods to add filters, check if an item satisfies
 * all filters, and combine multiple filters into one.
 * 
 * @author Subigyan Paudel
 * @version 2024-02-07
 */


import java.util.ArrayList;



public class AllFilters implements Filter {
    ArrayList<Filter> filters;
    
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    @Override
    public boolean satisfies(String id) {
        for (Filter f : filters) {
            if (!f.satisfies(id)) {
                return false;
            }
        }
        return true;
    }
    
}
