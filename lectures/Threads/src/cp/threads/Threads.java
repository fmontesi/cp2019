package cp.threads;

// Three loops counting with int

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Threads
{
	public static void main()
	{
		List<Path> filenames = new ArrayList<>();
		filenames.add( Paths.get( "/home/fmontesi/text1.txt" ) );
		filenames.add( Paths.get( "/home/fmontesi/text2.txt" ) );
		filenames.add( Paths.get( "/home/fmontesi/text3.txt" ) );
		filenames.add( Paths.get( "/home/fmontesi/text4.txt" ) );
		
		// word -> number of times it appears
		Map<String, Integer> occurrences = new HashMap<>();
		
		List<Thread> workers = new ArrayList<>();
		for( Path path : filenames ) {
			workers.add( new Thread( () -> {
				computeOccurrences( path, occurrences );
			}));
		}
		
		for( Thread t : workers ) {
			t.start();
		}
		
		for( Thread t : workers ) {
			try {
				t.join();
			} catch( InterruptedException e ) {
				e.printStackTrace();
			}
		}
		
		for( String word : occurrences.keySet() ) {
			System.out.println( word + ": " + occurrences.get( word ) );
		}
	}
	
	private static void computeOccurrences( Path path, Map<String, Integer> occurrences )
	{
		try {
			Files.lines( path ).forEach(
				line -> {
					for( String word : line.split( " " ) ) {
						synchronized( occurrences ) {
							if ( occurrences.containsKey( word ) ) {
								occurrences.put( word, occurrences.get( word ) + 1 );
							} else {
								occurrences.put( word, 1 );
							}
						}
					}
				}
			);
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
