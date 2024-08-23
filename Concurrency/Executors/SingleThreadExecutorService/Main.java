package Concurrency.Executors.SingleThreadExecutorService;

import Concurrency.Executors.ThreadColor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

class ColorThreadFactory implements ThreadFactory {
	private String threadName;

	public ColorThreadFactory(ThreadColor color) {
		this.threadName = color.name();
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setName(threadName);
		return thread;
	}
}

public class Main {

	public static void main(String[] args) {

		/*
		var defaultExecutor = Executors.newSingleThreadExecutor();
		defaultExecutor.execute(Main::countdown);
		defaultExecutor.shutdown();
		*/

		var blueExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_BLUE));
		// both the below tasks will be done sequentially because a single thread is running them
		blueExecutor.execute(Main::countdown);
		blueExecutor.execute(Main::countdown);
		blueExecutor.shutdown();



		boolean isDone = false;

		try {
			isDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		if(isDone) {
			System.out.println("Blue Finished, Starting Yellow");
			var yellowExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_YELLOW));
			yellowExecutor.execute(Main::countdown);
			yellowExecutor.shutdown();

			try {
				isDone = yellowExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

			if(isDone) {
				System.out.println("Yellow Finished, Starting Red");
				var redExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_RED));
				redExecutor.execute(Main::countdown);
				redExecutor.shutdown();

				try {
					isDone = redExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

				if(isDone) {
					System.out.println("All processes completed.");
				}
			}
		}

	}
	public static void notmain(String[] args) {

		Thread blue = new Thread(
				Main::countdown, ThreadColor.ANSI_BLUE.name());

		Thread yellow = new Thread(
				Main::countdown, ThreadColor.ANSI_YELLOW.name());

		Thread red = new Thread(
				Main::countdown, ThreadColor.ANSI_RED.name());

		blue.start();

		try {
			blue.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		yellow.start();

		try {
			yellow.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		red.start();

		try {
			red.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	/*
		try {
			blue.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		try {
			yellow.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		try {
			red.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	*/

	}

	public static void countdown() {
		String threadName = Thread.currentThread().getName();
		ThreadColor threadColor = ThreadColor.ANSI_RESET;

		try {
			threadColor = ThreadColor.valueOf(threadName.toUpperCase());
		}
		catch (IllegalArgumentException ignore) {
		}

		String color = threadColor.color();

		for(int i=20; i>=0; i--) {
			System.out.println(color + " " + threadName.replace("ANSI_", "") + " " + i);
		}
	}
}
