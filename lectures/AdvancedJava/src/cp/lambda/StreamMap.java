package cp.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamMap
{
	public static void main()
	{
		List<String> list = new ArrayList<>();
		list.add( "hello" );
		list.add( "hi" );
		list.add( "high" );
		list.add( "rock bottom" );
		
		Stream<String> stream = list.stream();
		
//		Stream<Integer> lengthStream = stream.map( s -> s.length() );
//		lengthStream.forEach( System.out::println );

		// Does the same as the previous two commented lines:
//		stream.map( s -> s.length() ).forEach( System.out::println );

//		Integer[] a = stream.map( s -> s.length() ).toArray( Integer[]::new );
//		
//		// Does the same as:
//		for( String s : list ) {
//			int length = s.length();
//			System.out.println( length );
//		}

		// This does the same
//			stream.map( s -> s.length() )
		// as this:
//			stream.map( String::length );

		int result = stream.map( s -> s.length() ).reduce(
			0,
			( total, length ) -> total + length
		);
		System.out.println( result );
		
		// The above does the same as:
		
//		int result = 0;
//		for( String s : list ) {
//			result += s.length();
//		}
//		System.out.println( result );
	}
}
