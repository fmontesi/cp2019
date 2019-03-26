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
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinOccurrencesStream
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

		ForkJoinPool pool = new ForkJoinPool( 4 );

		ForkJoinTask<Map<String,Integer>> task =
			pool.submit( () ->
//		ForkJoinPool.commonPool().submit( () -> // This is default used by Java for parallel streams
		{
			return paths.stream().parallel()
			.map( path -> computeOccurrences( path ) )
			.reduce(
				new HashMap<>(),
				( m1, m2 ) -> aggregate( m1, m2 )
			);
		} );
		try {
			task.get();
		} catch( ExecutionException | InterruptedException e ) {}
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
