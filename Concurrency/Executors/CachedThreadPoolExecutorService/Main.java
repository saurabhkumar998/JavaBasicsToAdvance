package Concurrency.Executors.CachedThreadPoolExecutorService;

import Concurrency.Executors.ThreadColor;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.*;

public class Main {

	public static void main(String[] args) {
		var multiExecutor = Executors.newCachedThreadPool();
		List<Callable<Integer>> taskList = List.of(
				new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						return Main.sum(1, 10, 1, "red");
					}
				},
				// the below code is shorter version of the above code - here we are using lambda expression instead of an anonymous class
		//		() -> Main.sum(1, 10, 1, "red"),
				() -> Main.sum(10, 100, 10, "blue"),
				() -> Main.sum(2, 20, 2, "green")
		);

		try{
			// the invokeAll() method takes a list of callable and returns a list of Future object
			var results = multiExecutor.invokeAll(taskList);
			for(var result : results) {
				// there is a possibility that the task is still being executed hence we are giving a timeout of 500 ms, if the task completes within this time then we get the response or else we return to the main thread
				System.out.println(result.get(500, TimeUnit.MILLISECONDS));
			}
		}
		catch (InterruptedException | TimeoutException | ExecutionException e) {
			throw new RuntimeException(e);
		}
		finally {
			multiExecutor.shutdown();
		}
	}
	public static void cachedmain(String[] args) {
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
