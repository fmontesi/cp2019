package generics;

/**
 *
 * @author fmontesi
 */
public class Generics
{
	private static class IntegerConsumer implements BoxConsumer<Integer>
	{
		@Override
		public void consume( Integer content )
		{
			System.out.println( "The integer is " + content );
		}
	}
	
	private static class AlternativeStringConsumer implements BoxConsumer<String>
	{
		@Override
		public void consume( String content )
		{
			System.out.println( content );
		}
	}
	
	public static void main( String[] args )
	{
		// Same as: Box<String> box = new Box<String>( "Hello" );
		Box<String> box = new Box<>( "Hello" );
		Box<Integer> box2 = new Box<>( 2 );
		
//		System.out.println( box.content() );
		
		BoxConsumer<String> myConsumer = new StringConsumer();
		box.enter( myConsumer );
		// Does the same as above
		box.enter( new AlternativeStringConsumer() );
		
		// Does the same as above
		box.enter( new BoxConsumer<String>() {
			@Override
			public void consume( String content )
			{
				System.out.println( content );
			}
		});
		
		box2.enter( new IntegerConsumer() );
	}
}
