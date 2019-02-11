package cp.lambda;

// Lambda box

import cp.generics.Box;
import cp.generics.BoxConsumer;
import java.util.ArrayList;
import java.util.List;

// Autoboxing
// Running time
// print time
// doAndMeasure
// %2
// BufferedReader
// try-with-resource
public class Lambda
{
	private static class OpenBox<T> {
		public T content;
	}
	
	public static void main()
	{
		forLoops();
	}
	
	private static void doAndMeasure( Runnable runnable )
	{
		long initialTime = System.currentTimeMillis();
		runnable.run();
		String elapsedTime = (System.currentTimeMillis() - initialTime) + "ms";
		System.out.println( elapsedTime );
	}
	
	private static void forLoops()
	{
		final List<Integer> listRef = new ArrayList<>();
		
		for( int i = 0; i < 100_000; i++ ) {
			listRef.add( i );
		}
		
		doAndMeasure( () -> forIndex( listRef ) );
		doAndMeasure( () -> forEach( listRef ) );
		doAndMeasure( () -> forStream( listRef ) );
		divisibleByTwo( listRef );
	}
	
	private static long forIndex( List<Integer> listRef )
	{
		long total = 0L;
		for( int i = 0; i < listRef.size(); i++ ) {
			total += listRef.get( i );
		}
		return total;
	}
	
	private static long forEach( List<Integer> listRef )
	{
		long total = 0L;
		for( int i : listRef ) {
			total += i;
		}
		return total;
	}
	
	private static long forStream( List<Integer> listRef )
	{
		final OpenBox<Long> total = new OpenBox<>();
		total.content = 0L;
		listRef.forEach( i -> total.content += i );
		return total.content;
	}
	
	private static void divisibleByTwo( List<Integer> listRef )
	{
		listRef.stream().map( i -> (i%2) == 0 ).forEach( System.out::println );
//		
//		List<Boolean> result = new ArrayList<>();
//		for( Integer i : listRef ) {
//			boolean b = ( i % 2 ) == 0;
//			result.add( b );
//		}
//		
//		result.forEach( System.out::println );
	}
	
	private static void mainLambda()
	{
		Box<String> box = new Box<>( "Hello, world!" );
		
		// Cumbersome way
		box.enter( new BoxConsumer<String>() {
			@Override
			public void consume( String s ) {
				System.out.println( s );
			}
		});
		
		BoxConsumer<String> consumerAnonymous = new BoxConsumer<String>() {
			@Override
			public void consume( String s ) {
				System.out.println( s );
			}
		};
		
		BoxConsumer<String> lambdaConsumer = s -> System.out.println( s );
		BoxConsumer<String> lambdaConsumer2 = ( s ) -> { System.out.println( s ); };
		BoxConsumer<String> lambdaConsumer3 = System.out::println;
		
		box.enter( consumerAnonymous );
		box.enter( lambdaConsumer );
		box.enter( lambdaConsumer2 );
		box.enter( lambdaConsumer3 );
		
		box.enter(
			s -> System.out.println( s ) // Lambda expression
		);
	}
	
	private static void main2()
	{
		Box<Integer> box = new Box<>( 2 );
		BoxConsumer<Integer> lambdaConsumer = s -> System.out.println( s );
		BoxConsumer<Integer> lambdaConsumer2 = ( s ) -> { System.out.println( s ); };
		
		
		box.enter( lambdaConsumer );
		
		box.enter(
			s -> System.out.println( s ) // Lambda expression
		);
	}
}
