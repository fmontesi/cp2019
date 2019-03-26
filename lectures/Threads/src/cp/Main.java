package cp;

import cp.threads.SingleDownloader;

public class Main
{
	// Executor.submit(Callable<T>)
	// Executor.cached
	// Executor.single
	// Stream
	// Stream ForkJoinPool
	// URLs
	// Mind map of concurrency strategies
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
		// doAndMeasure( OccurrencesStream::main );
		doAndMeasure(SingleDownloader::main );
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
