package cp.threads;

public class BusyWait3
{
	private static int counter = 0;
	private static volatile int b = 1;
	
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
			for( int i = 0; i < 10; i++ ) {
				if ( b == 1 ) {
					counter++;
					b = 2;
				} else {
//					System.out.println( "t1 wastes time" );
					i--;
				}
			}
		});
		Thread t2 = new Thread( () -> {
			for( int i = 0; i < 10; i++ ) {
				if ( b == 2 ) {
					counter++;
					b = 3;
				} else {
//					System.out.println( "t2 wastes time" );
					i--;
				}
			}
		});
		Thread t3 = new Thread( () -> {
			for( int i = 0; i < 10; i++ ) {
				if ( b == 3 ) {
					counter++;
					b = 1;
				} else {
//					System.out.println( "t3 wastes time" );
					i--;
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
