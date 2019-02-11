package cp.generics;

@FunctionalInterface
public interface BoxConsumer<T>
{
	public void consume( T content );
}
