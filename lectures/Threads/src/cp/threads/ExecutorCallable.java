package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorCallable
{	
	public static void main()
	{
		List<Path> paths = new ArrayList<>();
		
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		paths.add( Paths.get( "text1.txt" ) );
		paths.add( Paths.get( "text2.txt" ) );
		paths.add( Paths.get( "text3.txt" ) );
		paths.add( Paths.get( "text4.txt" ) );
		
		final List<Future<Map<String,Integer>>> results = new ArrayList<>();
		
		final int cores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool( cores );
		
		for( Path path : paths ) {
			results.add( executor.submit(
				() -> computeOccurrences( path )
			) );
		}
		
		try {
			executor.shutdown();
			executor.awaitTermination( 1L, TimeUnit.DAYS );
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
		
		// word -> number of times it appears
		Map<String, Integer> occurrences = new HashMap<>();
		for( Future<Map<String, Integer>> fut : results ) {
			try {
				Map<String, Integer> localOccurrences = fut.get();
				localOccurrences.forEach( ( word, times ) -> {
					if ( occurrences.containsKey( word ) ) {
						occurrences.put( word, occurrences.get( word ) + times );
					} else {
						occurrences.put( word, times );
					}
				} );
			} catch( InterruptedException | ExecutionException e ) {
				e.printStackTrace();
			}
		}
//		
//		for( String word : occurrences.keySet() ) {
//			 System.out.println( word + ": " + occurrences.get( word ) );
//		}
	}
	
	private static Map<String, Integer> computeOccurrences( Path path )
	{
		Map<String, Integer> localOccurrences = new HashMap<>();
		try {
			Files.lines( path ).forEach(
				line -> {
					for( String word : line.split( " " ) ) {
						localOccurrences.compute( word, ( w, times ) -> {
							if ( times == null ) {
								return 1;
							} else {
								return times + 1;
							}
						} );
					}
				}
			);
		} catch( IOException e ) {
			e.printStackTrace();
		}
		return localOccurrences;
	}
}
