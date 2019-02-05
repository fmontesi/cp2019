package generics;

public class Box <T>
{
	private final T content;
	
	public Box( T content )
	{
		this.content = content;
	}
	
	public T content()
	{
		return content;
	}
	
	public void enter( BoxConsumer<T> consumer )
	{
		consumer.consume( content );
	}
}
