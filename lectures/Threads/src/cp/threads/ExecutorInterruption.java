package cp.threads;

public class ExecutorInterruption
{
	/*
	Some strategies for interruption:
	
		- A shared (thread-safe!) variable is checked by
		  all tasks whenever they take a step.
			while( keepRun && hasNext() ) {
				// Your code here..
			}
	
		- Interrupt the executor.
			ExecutorService executor = Executors.newCachedThreadPool();
			// Submit some tasks here...
			executor.shutdownNow(); // As soon as you find what you're looking for
			executor.awaitTermination( 1, TimeUnit.DAYS ); // If you want to let current tasks terminate
	
		- Poison pill.
			See BlockingQueue.java
	*/
}
