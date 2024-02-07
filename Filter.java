/**
 * The Filter interface represents a filter used to select items based on certain criteria.
 * 
 * This interface declares a single method, satisfies, which takes a String parameter
 * representing an item and returns true if the item satisfies the filter criteria,
 * and false otherwise.
 * 
 * Implementing classes provide specific filter criteria and logic within the satisfies method.
 * 
 * @author Subigyan Paudel
 * @version 2024-02-07
 */


public interface Filter {
    boolean satisfies(String var1);
}
