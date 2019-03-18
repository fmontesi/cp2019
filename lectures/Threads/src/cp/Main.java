package cp;

import cp.threads.OccurrencesStream;

public class Main
{
	// Executor.submit(Callable<T>)
	// Executor.cached
	// Executor.single
	// Stream
	// Stream ForkJoinPool
	// URLs
	public static void main( String[] args )
	{
//		doAndMeasure( ExecutorCallableFun::main );
//		doAndMeasure( ExecutorCallable::main );
//		doAndMeasure( SynchronizedMap::main );
//		doAndMeasure( SynchronizedMap2::main );
//		doAndMeasure( SynchronizedMap3::main );
//		doAndMeasure( SynchronizedMap4::main );
//		doAndMeasure( SynchronizedMap5::main );
//		doAndMeasure( SynchronizedMapExecutor::main );
		doAndMeasure( OccurrencesStream::main );
	}
	
	private static void doAndMeasure( Runnable runnable )
	{
		long t1 = System.currentTimeMillis();
		runnable.run();
		System.out.println(
			"Elapsed time: " +
			(System.currentTimeMillis() - t1 )
			+ "ms"
		);
	}
}
