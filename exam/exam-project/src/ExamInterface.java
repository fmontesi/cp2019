import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * (Note: If you're doing the re-exam of the 5 ECTS version
 * of Concurrent Programming, you do not have to implement this method.)
 * 
 * This method gets a stream of paths (the parameter paths), where each
 * path points to a ".dat" file (you must check that the path is valid though).
 * 
 */
public interface ExamInterface
{
	/**
	 * This method recursively visits a directory to find all the text files
	 * contained in it and its subdirectories.
	 * 
	 * You must consider only files ending with a ".txt" suffix. You are
	 * guaranteed that they will be text files.
	 * 
	 * You can assume that each text file contains a (non-empty) comma-separated
	 * sequence of numbers. For example: 100,200,34,25. There won't be any new
	 * lines, spaces, etc., and the sequence never ends with a comma. You are
	 * guaranteed that each number will be at least or equal to 0 (zero), i.e.,
	 * no negative numbers.
	 * 
	 * The search is recursive: if the directory contains subdirectories, these
	 * are also searched and so on so forth (until there are no more
	 * subdirectories).
	 * 
	 * This method returns a list of results. The list contains a result for
	 * each text file that you find. Each {@link Result} stores the path of its
	 * text file and the result of the arithmetic mean of the numbers found
	 * inside of the text file (rounded down, e.g., with Math.floor).
	 * 
	 * @param dir the directory to search
	 * @return a list of results ({@link Result}), each giving the arithmetic mean 
	 * found in a file.
	 */
	public List< Result > m1( Path dir );
	
	/**
	 * This method recursively visits a directory ({@link path}) for text files
	 * with suffix ".dat" (notice that it is different than the one before)
	 * contained in it and its subdirectories.
	 * 
	 * You must consider only files ending with a .dat suffix. You are
	 * guaranteed that they will be text files.
	 * 
	 * Each .dat file contains some lines of text, separated by the newline
	 * character "\n". You can assume that each line contains a (non-empty)
	 * comma-separated sequence of numbers. For example: 100,200,34,25
	 * 
	 * For each line, this method:
	 *
	 * 1. divides the sequence of numbers into two halves (if the count of
	 *    numbers in the sequence is odd, then the first half must contain the
	 *    middle value);
	 *
	 * 2. calculates the sum (total) of each half;
	 *
	 * 3. and finds the absolute difference between the totals of the two
	 *    halves.
	 *
	 * If the difference of a sequence is greater or equal (>=) than a parameter
	 * {@link min}, the method must return as soon as possible (without waiting to
	 * analyse also the other files). The return value is a result that
	 * contains:
	 *	- path: the path to the text file that contains the line that respects
	 *	  the condition;
	 *  - number: the line number, starting from 1 (e.g., 1 if it is the first
	 *    line, 3 if it is the third, etc.)
	 */
	public Result m2( Path dir, int min );
	
	/**
	 * Computes overall statistics about the occurrences of numbers in a stream of files.
	 * 
	 * Each path in the stream will be either for a .txt or a .dat file, which you can assume
	 * will have exactly the same format as described for m1 and m2.
	 * 
	 * This method looks at all the .txt and .dat files in the stream
	 * and returns a {@link Stats} object containing the statistics of interest.
	 * See the documentation of {@link Stats}.
	 */
	public Stats m3( Stream<Path> dir );
}