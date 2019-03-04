package cp.threads;

// Three loops counting with int

import java.util.concurrent.atomic.AtomicInteger;


public class AtomicIntegerExample
{
	private static AtomicInteger counter = new AtomicInteger( 0 );
	private static AtomicInteger two = new AtomicInteger( 0 );
	
	public static void main()
	{
		Thread t1 = new Thread( () -> {
			for( int i = 0; i < 1_000; i++ ) {
				if ( counter.addAndGet( 1 ) % 2 == 0 ) {
					two.addAndGet( 1 );
				}
			}
		});
		Thread t2 = new Thread( () -> {
			for( int i = 0; i < 1_000; i++ ) {
				if ( counter.addAndGet( 1 ) % 2 == 0 ) {
					two.addAndGet( 1 );
				}
			}
		});
		Thread t3 = new Thread( () -> {
			for( int i = 0; i < 1_000; i++ ) {
				if ( counter.addAndGet( 1 ) % 2 == 0 ) {
					two.addAndGet( 1 );
				}
			}
		});
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
		System.out.println( "Counter: " + counter.get() );
		System.out.println( "Two: " + two.get() );
	}
}
