package cp.threads;

// Create a thread
// Create two threads that print on screen
// Two loops counting
// Busy-wait counter with boolean
// Three loops counting with int
// Atomic counter
// Fibonacci
public class Threads
{
	private static int counter = 0;
	private static boolean b = true;
	
	public static void main()
	{
		/*
		 READ COUNTER as X           READ COUNTER as X
		 X + 1 as RESULT             X + 1 as RESULT
		 STORE RESULT in COUNTER     STORE RESULT in COUNTER
		*/
		
		/*
		 READ COUNTER as 3           READ COUNTER as 3
		 3 + 1 as RESULT             3 + 1 as RESULT
		 STORE 4 in COUNTER			 STORE 4 in COUNTER
		*/
		
		Thread t1 = new Thread( () -> {
			for( int i = 0; i < 100_000_000; i++ ) {
				if ( b ) {
					counter++;
					b = false;
				} else {
					i--;
				}
			}
		});
		Thread t2 = new Thread( () -> {
			for( int i = 0; i < 100_000_000; i++ ) {
				if ( !b ) {
					counter++;
					b = true;
				} else {
					i--;
				}
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
		System.out.println( counter );
	}
	
	private static void twoThreadsPrinting()
	{
		Thread t1 = new Thread( () -> {
			System.out.println( "HELLO from t1" );
		});
		Thread t2 = new Thread( () -> {
			System.out.println( "HELLO from t2" );
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}
}
