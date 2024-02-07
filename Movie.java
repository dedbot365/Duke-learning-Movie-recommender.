/**
 * The Movie class represents a movie with various attributes such as ID, title, year, genres, director, country, poster URL, and duration.
 * 
 * Example usage:
 * Movie movie = new Movie("tt1234567", "The Shawshank Redemption", "1994", "Drama");
 * String id = movie.getID();
 * String title = movie.getTitle();
 * int year = movie.getYear();
 * String genres = movie.getGenres();
 * String country = movie.getCountry();
 * String director = movie.getDirector();
 * String poster = movie.getPoster();
 * int minutes = movie.getMinutes();
 * 
 * This class provides constructors to create a Movie object with different sets of attributes, and methods to access the attributes of a movie and a toString method for string representation of a movie.
 * 
 * @author Subigyan Paudel
 * @version 2024-02-07
 */


public class Movie {
    private String id;
    private String title;
    private int year;
    private String genres;
    private String director;
    private String country;
    private String poster;
    private int minutes;

    public Movie(String anID, String aTitle, String aYear, String theGenres) {
        this.id = anID.trim();
        this.title = aTitle.trim();
        this.year = Integer.parseInt(aYear.trim());
        this.genres = theGenres;
    }

    public Movie(String anID, String aTitle, String aYear, String theGenres, String aDirector, String aCountry, String aPoster, int theMinutes) {
        this.id = anID.trim();
        this.title = aTitle.trim();
        this.year = Integer.parseInt(aYear.trim());
        this.genres = theGenres;
        this.director = aDirector;
        this.country = aCountry;
        this.poster = aPoster;
        this.minutes = theMinutes;
    }

    public String getID() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public String getGenres() {
        return this.genres;
    }

    public String getCountry() {
        return this.country;
    }

    public String getDirector() {
        return this.director;
    }

    public String getPoster() {
        return this.poster;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public String toString() {
        String result = "Movie [id=" + this.id + ", title=" + this.title + ", year=" + this.year;
        result = result + ", genres= " + this.genres + "]";
        return result;
    }
}
