package cp.threads;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Blocking
{	
	public static void main()
	{
		final Path p = Paths.get( "." );
		BlockingDeque<Path> deque = new LinkedBlockingDeque<>();
		
		Thread consumer = new Thread( () -> {
			while( true ) {
				Path path = deque.poll();
				if ( path == null ) {
					break;
				}
				System.out.println( path );
//				Files.lines( p ).anyMatch( l -> l.length() > 10 );
			}
		} );
		consumer.start();
		
		Thread producer = new Thread( () -> {
			visit( p, deque );
			deque.addLast( Path.of( "poison" ) );
		} );
		producer.start();
		
		try {
			producer.join();
			consumer.join();
		} catch( InterruptedException e ) {}
	}
	
	public static void visit( Path p, BlockingDeque<Path> deque )
	{
		if ( Files.isDirectory( p ) ) {
			File dir = p.toFile();
			File[] subFiles = dir.listFiles();
			for( File subFile : subFiles ) {
				visit( subFile.toPath(), deque );
			}
		} else {
			deque.addLast( p );
		}
	}
}
