package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedMap2
{
	public static void main()
	{
		List<Path> paths = new ArrayList<>();
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		
		// word -> number of times it appears
		Map<String, Integer> occurrences = new HashMap<>();
		
		List<Thread> workers = new ArrayList<>();
		for( Path path : paths ) {
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
		
		/* for( String word : occurrences.keySet() ) {
			System.out.println( word + ": " + occurrences.get( word ) );
		} */
	}
	
	private static void computeOccurrences( Path path, Map<String, Integer> occurrences )
	{
		try {
			String[] lines = Files.lines( path ).toArray( String[]::new );
			List<String> words = new ArrayList<>();
			for( String line : lines ) {
				for( String word : line.split( " " ) ) {
					words.add( word );
				}
			}
			for( String word : words ) {
				synchronized( occurrences ) {
					if ( occurrences.containsKey( word ) ) {
						occurrences.put( word, occurrences.get( word ) + 1 );
					} else {
						occurrences.put( word, 1 );
					}
				}
			}
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
