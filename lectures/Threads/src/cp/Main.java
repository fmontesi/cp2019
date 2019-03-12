package cp;

import cp.threads.SynchronizedMap;

public class Main
{
	// doAndMeasure
	// contention: where to synchronize?
	// Map Reduce
	// ConcurrentHashMap
	// Fork join pool
	public static void main( String[] args )
	{
		doAndMeasure( SynchronizedMap::main );
//		doAndMeasure( SynchronizedMap2::main );
//		doAndMeasure( SynchronizedMap3::main );
//		doAndMeasure( SynchronizedMap4::main );
//		doAndMeasure( SynchronizedMap5::main );
//		doAndMeasure( SynchronizedMapExecutor::main );
//		doAndMeasure( OccurrenceStream::main );
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
