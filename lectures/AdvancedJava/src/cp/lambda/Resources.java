package cp.lambda;

// Files.lines

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// try-with-resources
public class Resources
{
	public static void main()
	{
		Path path = Paths.get( "/home/fmontesi/myfile.txt" );
		try {
			Files.lines( path )
				.map( line -> line.length() )
				.forEach( System.out::println );

			Files.lines( path )
				.forEach( line -> System.out.println( line.length() ) );
			
			int total = Files.lines( path )
				.mapToInt( line -> line.length() )
				.sum();
			System.out.println( "Total: " + total );
			
			Files.lines( path )
				.filter( line -> line.length() > 10 )
				.forEach( System.out::println );
			
			try( BufferedReader reader = Files.newBufferedReader( path ) ) {
				// Do something with the reader
				reader.read();
			} catch( IOException e ) {
				// Do whatever you want with the exception
			}
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
