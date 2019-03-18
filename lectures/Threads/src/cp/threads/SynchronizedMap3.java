package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedMap3
{
	private static class WordCounter implements Runnable {
		private final Path path;
		private final Map<String, Integer> localOccurrences = new HashMap<>();
		
		public WordCounter( Path path )
		{
			this.path = path;
		}
		
		public void run()
		{
			try {
				Files.lines( path ).forEach( line -> {
					for( String word : line.split( " " ) ) {
						if ( localOccurrences.containsKey( word ) ) {
							localOccurrences.put( word, localOccurrences.get( word ) + 1 );
						} else {
							localOccurrences.put( word, 1 );
						}
					}
				} );
			} catch( IOException e ) {
				e.printStackTrace();
			}
		}
		
		public Map<String, Integer> localOccurrences()
		{
			return localOccurrences;
		}
	}
	
	public static void main()
	{
		List<Path> paths = new ArrayList<>();
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		
		List<WordCounter> counters = new ArrayList<>();
		List<Thread> workers = new ArrayList<>();
		for( Path path : paths ) {
			WordCounter counter = new WordCounter( path );
			counters.add( counter );
			workers.add( new Thread( counter ) );
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
		
		// word -> number of times it appears
		Map<String, Integer> occurrences = new HashMap<>();
		
		for( WordCounter counter : counters ) {
			counter.localOccurrences().forEach( ( word, times ) -> {
				if( occurrences.containsKey( word ) ) {
					occurrences.put( word, occurrences.get( word ) + times );
				} else {
					occurrences.put( word, times );
				}
			} );
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
