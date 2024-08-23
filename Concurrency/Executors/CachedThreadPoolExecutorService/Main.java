package Concurrency.Executors.CachedThreadPoolExecutorService;

import Concurrency.Executors.ThreadColor;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		var multiExecutor = Executors.newCachedThreadPool();

		try{
			// execute() method takes a runnable and doesn't return anything
			/*
			multiExecutor.execute(() -> sum(1,10,1,"red"));
			multiExecutor.execute(() -> sum(10,100,10,"blue"));
			multiExecutor.execute(() -> sum(2,20,2,"green"));
			 */

			// the submit() method can take a runnable as well as a callable and returns a Future instance
			var redValue = multiExecutor.submit(() -> sum(1,10,1,"red"));
			var blueValue = multiExecutor.submit(() -> sum(10,100,10,"blue"));
			var greenValue = multiExecutor.submit(() -> sum(2,20,2,"green"));

			try{
				System.out.println(redValue.get(500, TimeUnit.SECONDS));
				System.out.println(blueValue.get(500, TimeUnit.SECONDS));
				System.out.println(greenValue.get(500, TimeUnit.SECONDS));
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		finally {
			multiExecutor.shutdown();
		}

	}


	public static int sum(int start, int end, int delta, String colorString) {
		var threadColor = Concurrency.Executors.ThreadColor.ANSI_RESET;
		try {
			threadColor = ThreadColor.valueOf("ANSI_" + colorString.toUpperCase());
		}
		catch (IllegalArgumentException ignore) {

		}

		String color = threadColor.color();
		int sum = 0;
		for(int i=start; i<=end; i+=delta) {
			sum += i;
		}
		System.out.println(color + Thread.currentThread().getName() + ", " + colorString + " " + sum);
		return sum;
	}
}
