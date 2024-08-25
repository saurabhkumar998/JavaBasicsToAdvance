package Concurrency.Executors.SchedulingTasks.ScheduledThreadPoolExecutor;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Main {
	public static void main(String[] args) {
		var dtf = DateTimeFormatter.ofLocalizedDateTime(
				FormatStyle.MEDIUM,
				FormatStyle.LONG
		);

		Callable<ZonedDateTime> waitThenDoIt = () -> {
			ZonedDateTime zdt = null;
			try {
				TimeUnit.SECONDS.sleep(2);
				zdt = ZonedDateTime.now();
			}
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return zdt;
		};

		var threadPool = Executors.newFixedThreadPool(4);

		List<Callable<ZonedDateTime>> list = Collections.nCopies(4, waitThenDoIt);

		try{
			System.out.println("------>" + ZonedDateTime.now().format(dtf));
			List<Future<ZonedDateTime>> futureList = threadPool.invokeAll(list);

			for(Future<ZonedDateTime> result : futureList) {
				try{
					System.out.println(result.get(1, TimeUnit.SECONDS).format(dtf));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch (InterruptedException e){
			throw new RuntimeException(e);
		}
		finally {
			threadPool.shutdown();
		}


		/*
		System.out.println("----> " + ZonedDateTime.now().format(dtf));
		ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(4);
		for(int i=0; i<4; i++) {
			//scheduledThreadPoolExecutor.schedule(() -> System.out.println(ZonedDateTime.now().format(dtf)), 2, TimeUnit.SECONDS);
			scheduledThreadPoolExecutor
					.schedule(() -> System.out.println(ZonedDateTime.now().format(dtf)), 2 * (i+1), TimeUnit.SECONDS);
		}

		scheduledThreadPoolExecutor.shutdown();

		 */

		System.out.println("----> " + ZonedDateTime.now().format(dtf));

		ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(4);

		var scheduledTask = scheduledThreadPoolExecutor
				.scheduleWithFixedDelay(
						() -> System.out.println(ZonedDateTime.now().format(dtf)),
						2, 2, TimeUnit.SECONDS);

		long time = System.currentTimeMillis();

		while(!scheduledTask.isDone()) {
			try {
				TimeUnit.SECONDS.sleep(2);
				if((System.currentTimeMillis()-time)/1000 > 10) {
					scheduledTask.cancel(true);
				}
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		scheduledThreadPoolExecutor.shutdown();
	}
}
