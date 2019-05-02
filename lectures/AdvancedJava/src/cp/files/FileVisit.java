package cp.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileVisit
{
	public static void main()
	{
		final Path p = Paths.get( "." );
		streams( p );
		visit( p );
	}
	
	public static void streams( Path p )
	{
		if ( Files.isDirectory( p ) ) {
			try {
				Stream< Path > stream = Files.walk( p );
				stream.forEach( System.out::println );
			} catch( IOException e ) {
				e.printStackTrace();
			}
		}
	}
	
	public static void visit( Path p )
	{
		System.out.println( p );
		if ( Files.isDirectory( p ) ) {
			File dir = p.toFile();
			File[] subFiles = dir.listFiles();
			for( File subFile : subFiles ) {
				visit( subFile.toPath() );
			}
		}
	}
}
