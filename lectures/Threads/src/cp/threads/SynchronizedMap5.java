package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SynchronizedMap5
{	
	public static void main()
	{
		List<Path> paths = new ArrayList<>();
		
		paths.add( Paths.get( "/home/fmontesi/text1.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text2.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text3.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text4.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text1.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text2.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text3.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text4.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text1.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text2.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text3.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text4.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text1.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text2.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text3.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text4.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text1.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text2.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text3.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text4.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text1.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text2.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text3.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text4.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text1.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text2.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text3.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text4.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text1.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text2.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text3.txt" ) );
		paths.add( Paths.get( "/home/fmontesi/text4.txt" ) );
		
		// word -> number of times it appears
		Map<String, Integer> occurrences = new ConcurrentHashMap<>();
		
		final int cores = Runtime.getRuntime().availableProcessors();
		final int limit = (int)Math.ceil( paths.size() / (double)cores );
		List<Thread> workers = new ArrayList<>();
		for( int i = 0; i < cores; i++ ) {
			List<Path> localPaths = new ArrayList<>( limit );
			for( int j = 0; j < limit && !paths.isEmpty(); j++ ) {
				localPaths.add( paths.remove( 0 ) );
			}
			workers.add( new Thread( () -> {
				computeOccurrences( localPaths, occurrences );
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
	
	private static void computeOccurrences( List<Path> paths, Map<String, Integer> occurrences )
	{
		paths.forEach( path -> {
			try {
				Files.lines( path ).forEach(
					line -> {
						for( String word : line.split( " " ) ) {
							occurrences.compute( word, ( w, times ) -> {
								if ( times == null ) {
									return 1;
								} else {
									return times + 1;
								}
							} );
	//						synchronized( occurrences ) {
	//							if ( occurrences.containsKey( word ) ) {
	//								occurrences.put( word, occurrences.get( word ) + 1 );
	//							} else {
	//								occurrences.put( word, 1 );
	//							}
	//						}
						}
					}
				);
			} catch( IOException e ) {
				e.printStackTrace();
			}
		});
	}
}
