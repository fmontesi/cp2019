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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class MultiDownloaderPrettierForkJoin
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
		Future<Integer> bigResult = pool.submit( () ->
			urls.stream().parallel()
				.map(MultiDownloaderPrettierForkJoin::urlToBufferedReader )
				.map(MultiDownloaderPrettierForkJoin::bufferedReaderToTs )
				.reduce( 0, Integer::sum )
		);
		try {
			System.out.println( "Result " + bigResult.get() );
		} catch( InterruptedException | ExecutionException e ) {
			e.printStackTrace();
		}
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
	
	private static int bufferedReaderToTs( BufferedReader reader )
	{
		return reader.lines()
			.map( line -> {
				int counter = 0;
				for( int i = 0; i < line.length(); i++ ) {
					if ( line.charAt( i ) == 't' ) {
						counter++;
					}
				}
				return counter;
			} ).reduce( 0, ( n1, n2 ) -> n1 + n2 );
	}
}
