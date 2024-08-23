package Concurrency.Volatile;

import java.util.concurrent.TimeUnit;

public class CachedData {
	//private boolean flag = false;

	// we have used the volatile keyword because each thread has its own copy of the object from the heap memory (called thread cache)
	// the OS may take some time to save the changes from the thread's memory to the heap memory, if we try accessing
	// this value from other thread, it may or may not reflect there
	// the volatile keyword ensures that the value of this variable is always read from and written to the main memory (heap memory)
	private volatile boolean flag = false;
	public void toggleFlag() {
		flag = !flag;
	}

	public boolean isReady() {
		return flag;
	}

	public static void main(String[] args) {

		CachedData example = new CachedData();

		Thread writerThread = new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			example.toggleFlag();

			System.out.println("A. Flag set to : " + example.isReady());
		});

		Thread readerThread = new Thread(() -> {
			while(!example.isReady()){
				// Busy wait until flag is not ready
			}
			System.out.println("B. Flag is : " + example.isReady());
		});

		writerThread.start();
		readerThread.start();
	}
}



