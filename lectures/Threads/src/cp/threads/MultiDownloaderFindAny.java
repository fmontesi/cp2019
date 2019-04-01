package cp.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiDownloaderFindAny
{
	public static void main()
	{
		List< URL> urls = new ArrayList<>();
		try {
			urls.add( new URL( "https://www.fabriziomontesi.com/teaching.html" ) );
			urls.add( new URL( "https://www.fabriziomontesi.com/teaching/cp-2019/index.html" ) );
			urls.add( new URL( "http://historiaccfl.webatu.com/LCF/courses.html" ) );
			urls.add( new URL( "http://historiaccfl.webatu.com/LCF/hate/ihatetoshiba.html" ) );
			urls.add( new URL( "http://historiaccfl.webatu.com/LCF/music.html" ) );
			urls.add( new URL( "http://historiaccfl.webatu.com/" ) );
			urls.add( new URL( "https://www.sdu.dk" ) );
			urls.add( new URL( "https://imada.sdu.dk" ) );
			urls.add( new URL( "https://concurrency.sdu.dk" ) );
			urls.add( new URL( "https://www.java.com" ) );
			urls.add( new URL( "https://www.wikipedia.org" ) );
		} catch( MalformedURLException e ) {
			e.printStackTrace();
		}
		
		ForkJoinPool pool = new ForkJoinPool( urls.size() );
		try {
			pool.submit( () ->
				urls.stream().parallel()
					.map( MultiDownloaderFindAny::urlToBufferedReader )
					.flatMap( reader -> reader.lines() )
					.filter( MultiDownloaderFindAny::containsToshiba )
					.forEach( System.out::println )
			).get();
		} catch( Exception e ) {
			e.printStackTrace();
		}
//				.reduce(
//					new ArrayList<>(),
//					( l1, l2 ) -> {
//						List<String> result = new ArrayList<>();
//						result.addAll( l1 );
//						result.addAll( l2 );
//						return result;
//					}
//				);
//
//		try {
//			System.out.println( "Result " + bigResult.get() );
//		} catch( InterruptedException | ExecutionException e ) {
//			e.printStackTrace();
//		}
	}
	
	private static BufferedReader urlToBufferedReader( URL url )
	{
		try {
			InputStream is = url.openStream();
			return new BufferedReader( new InputStreamReader( is ) );
		} catch( IOException e ) {
			e.printStackTrace();
			StringReader reader = new StringReader( "" );
			return new BufferedReader( reader );
		}
	}
	
	private static boolean containsToshiba( String line )
	{
		Pattern pattern = Pattern.compile( "(T|t)(O|o)(S|s)(H|h)(I|i)(B|b)(A|a)" );
		Matcher matcher = pattern.matcher( line );
		return matcher.find();
	}
}
