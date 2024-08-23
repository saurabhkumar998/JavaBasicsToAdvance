package Concurrency.Threads;

public class JoinDemo {
	public static void main(String[] args) {
		Runnable runnable = () -> {
			for(int i=1; i<=10; i++) {
				System.out.println(" 1 ");
				try{
					Thread.sleep(1000);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}

		};

		Thread thread1 = new Thread(runnable);

		thread1.start();
		try {
			// the below code will pause the execution of the main thread (for indefinite time) until the above thread finishes execution
			//thread.join();
			// the below code will wait for 3 sec if the above thread is still not completed it will resume the main thread again
			thread1.join(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Resuming main thread after thread1 is executed or the threshold for the join is reached.");


		Thread thread2 = new Thread(() -> {
			for(int i=1; i<=10; i++) {
				System.out.println(" 2 ");
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		});

		thread2.start();
	}
}
