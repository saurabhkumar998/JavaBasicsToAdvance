package Concurrency.Threads;

import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		var currentThread = Thread.currentThread();
		System.out.println(currentThread.getClass().getName());

		System.out.println(currentThread);

		currentThread.setName("MainGuy");
		currentThread.setPriority(Thread.MAX_PRIORITY);

		printThreadState(currentThread);

		CustomThread customThread = new CustomThread();
		customThread.start();
		//customThread.run();

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 8; i++) {
					System.out.print(" 2 ");

					try {
						TimeUnit.MILLISECONDS.sleep(250);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		Thread myThread = new Thread(runnable);
		myThread.start();

		Runnable runnable2 = () -> {
			for (int i = 1; i <= 8; i++) {
				System.out.print(" 3 ");

				try {
					TimeUnit.MILLISECONDS.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		Thread myThread2 = new Thread(runnable2);
		myThread2.start();

		for (int i = 1; i <= 3; i++) {
			System.out.print(" 0 ");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	public static void printThreadState(Thread thread) {
		System.out.println("-".repeat(20));
		System.out.println("Thread Id : " + thread.getId());
		System.out.println("Thread Name : " + thread.getName());
		System.out.println("Thread Priority : " + thread.getPriority());
		System.out.println("Thread State : " + thread.getState());
		System.out.println("Thread Group : " + thread.getThreadGroup());
		System.out.println("Thread Is Alive : " + thread.isAlive());
		System.out.println("-".repeat(20));
	}
}
