
/**
 * The GenreFilter class implements the Filter interface to filter movies based on their genre.
 * 
 * Example usage:
 * GenreFilter filter = new GenreFilter("Comedy");
 * ArrayList<String> filteredMovies = MovieDatabase.filterBy(filter);
 * 
 * This class provides a method to check if a movie satisfies the filter criteria based on its genre.
 * It implements the satisfies method defined in the Filter interface to determine if a movie's genre
 * matches the specified genre.
 * 
 * The constructor of GenreFilter initializes the genre value to be used for filtering.
 * 
 * @author Subigyan Paudel
 * @version 2024-02-07
 */

public class GenreFilter implements Filter {
    private String genre;
    
    public GenreFilter(String genre) {
        this.genre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(genre);
    }
}
