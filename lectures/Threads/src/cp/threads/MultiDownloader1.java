package cp.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiDownloader1
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
		
//		int bigResult =
		urls.stream().parallel().map( url -> {
			try {
				InputStream is = url.openStream();
				return new BufferedReader( new InputStreamReader( is ) );
			} catch( IOException e ) {
				return null;
			}
		} ).flatMap( reader -> {
			return reader.lines();
		} ).filter( line -> line.contains( ".pdf" ) )
		.forEach( line -> {
			Pattern p = Pattern.compile( "href=\"(.*?)\"" );
			Matcher m = p.matcher( line );
			if ( m.find() ) {
				m.group( 1 );
			}
			System.out.println( m.group( 1 ) );
		} );
//		System.out.println( "Result " + bigResult );
	}
}
