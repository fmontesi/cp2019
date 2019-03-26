package cp.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MultiDownloader
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
		} catch( MalformedURLException e ) {
			e.printStackTrace();
		}

		int bigResult = urls.stream().parallel().map( url -> {
			try {
				InputStream is = url.openStream();
				return new BufferedReader( new InputStreamReader( is ) );
			} catch( IOException e ) {
				return null;
			}
		} ).map( reader -> {
			int result = reader.lines().map( line -> {
				Integer counter = 0;
				for( int i = 0; i < line.length(); i++ ) {
					if ( line.charAt( i ) == 't' ) {
						counter++;
					}
				}
				return counter;
			} ).reduce( 0, ( n1, n2 ) -> n1 + n2 );
			return result;
		} ).reduce( 0, ( n1, n2 ) -> n1 + n2 );
		System.out.println( "Result " + bigResult );
	}
}
