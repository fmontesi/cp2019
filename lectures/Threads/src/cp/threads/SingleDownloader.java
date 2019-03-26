/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class SingleDownloader
{
	public static void main()
	{
		try {
			URL url = new URL( "https://www.fabriziomontesi.com/teaching.html" );
			InputStream is = url.openStream();
			BufferedReader reader = new BufferedReader( new InputStreamReader( is ) );
			
			int result = reader.lines()
				// Map each line to the number of times 't' appears in that line
				.map( line -> {
					Integer counter = 0;
					for( int i = 0; i < line.length(); i++ ) {
						if ( line.charAt( i ) == 't' ) {
							counter++;
						}
					}
					return counter;
				} )
				// Reduce to the total for all lines
				.reduce( 0, (n1,n2) -> n1 + n2 );
			System.out.println( "Result " + result );
		} catch( MalformedURLException e ) {
			e.printStackTrace();
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
