package generics;

public class StringConsumer implements BoxConsumer<String>
{
	@Override
	public void consume( String content )
	{
		System.out.println( content );
	}
}
