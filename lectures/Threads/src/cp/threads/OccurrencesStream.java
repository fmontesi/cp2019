package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OccurrencesStream
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

		// Option 1: shared ConcurrentHashMap
//		Map<String, Integer> occurrences = new ConcurrentHashMap<>();
//
//		Stream<Map<String, Integer>> s
//			= paths.stream().parallel().map( path -> computeOccurrences( path ) );
//		s.forEach( localOccurrences -> {
//			localOccurrences.forEach( ( word, localTimes ) -> {
//				occurrences.compute( word, ( w, globalTimes ) -> {
//					if ( globalTimes == null ) {
//						return localTimes;
//					} else {
//						return globalTimes + localTimes;
//					}
//				} );
//			} );
//		} );

		// Option 2: no need for ConcurrentHashMap,
		// becase the accumulation of results runs sequentially
//		Map<String, Integer> occurrences = new HashMap<>();
//		
//		Stream<Map<String, Integer>> s
//			= paths.stream().parallel().map( path -> computeOccurrences( path ) );
//
//		Map<String, Integer> results[] = s.toArray( Map[]::new );
//		for( Map<String, Integer> localOccurrences : results ) {
//			localOccurrences.forEach( ( word, times ) -> {
//				if ( occurrences.containsKey( word ) ) {
//					occurrences.put( word, occurrences.get( word ) + times );
//				} else {
//					occurrences.put( word, times );
//				}
//			} );
//		}
		
		paths.stream().parallel()
			.map( path -> computeOccurrences( path ) )
			.reduce(
				new HashMap<>(),
				( m1, m2 ) -> aggregate( m1, m2 )
			);
	}
	
	private static Map<String, Integer> aggregate(
		Map<String, Integer> m1,
		Map<String, Integer> m2 )
	{
		Map<String, Integer> result = new HashMap<>( m1 );
		m2.forEach( ( word, times ) ->
			result.put( word,
				result.containsKey( word ) ?
					result.get( word ) + times
					: times
			)
			// Does the same as:
//			if ( result.containsKey( word ) ) {
//				result.put( word, result.get( word ) + times );
//			} else {
//				result.put( word, times );
//			}
		);
		return result;
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
