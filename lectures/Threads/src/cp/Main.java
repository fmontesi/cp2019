package cp;

import cp.threads.MultiDownloaderFindAny;

public class Main
{
	// Mind map of concurrency strategies
	
	// MultiDownloader findAny
	// MultiDownloader save (Files::copy)
	// MultiDownloader find PDFs "href=\"(.*?)\"" (flatMap)
	// MultiDownloader asks for confirmation (producer consumer)
	// MultiDownloader stop
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
//		doAndMeasure( OccurrencesStream::main );
//		doAndMeasure( SingleDownloader::main );
//		doAndMeasure( MultiDownloader::main );
//		doAndMeasure( MultiDownloaderPrettierForkJoin::main );
		doAndMeasure( MultiDownloaderFindAny::main );
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
