import java.nio.file.Path;
import java.util.List;

public interface Stats
{
  /**
   * Returns the number of times that a number was found (in any files).
   */
  public int occurrences( int number );
  
  /**
   * Returns the list of files that contain at least one line
   * whose counter (computed as in methods m1 and m2) of numbers above
   * parameter threshold is strictly above (>) parameter min.
   *
   */
  public List< Path > atLeastDifferent( int threshold, int min );
  
  /**
   * Returns the number that was found the most times.
   */
  public int mostFrequent();
  
  /**
   * Returns the number that was found the least times.
   */
  public int leastFrequent();
}