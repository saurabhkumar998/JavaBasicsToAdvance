package Concurrency.Threads;

public class RunningThreads {
	public static void main(String[] args) {

		System.out.println("Main Thread Running");

		try {
			System.out.println("Main Thread paused for 1 second.");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		Thread thread = new Thread(() -> {

			String tname = Thread.currentThread().getName();
			System.out.println(tname + " should take 10 dots to run.");
			for(int i=1; i<=10; i++) {
				System.out.print(". ");
				try {
					Thread.sleep(500);
				}
				catch (Exception e) {
					System.out.println("\nWhoops!! " + tname + " interrupted.");
					return;
				}
			}
			System.out.println("\n" + tname + " Completed.");
		});

		System.out.println(thread.getName() + " starting");

		thread.start();

		System.out.println("Main Thread would continue here.");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		thread.interrupt();
	}
}
