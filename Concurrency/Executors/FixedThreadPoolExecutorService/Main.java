package Concurrency.Executors.FixedThreadPoolExecutorService;

import Concurrency.Executors.ThreadColor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class ColorThreadFactory implements ThreadFactory {
	private String threadName;
	private int colorValue = 1;
	public ColorThreadFactory(ThreadColor color) {
		this.threadName = color.name();
	}

	public ColorThreadFactory() {
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		String name = threadName;
		if(name == null) {
			name = ThreadColor.values()[colorValue].name();
		}

		if(++colorValue > (ThreadColor.values().length-1)) {
			colorValue = 1;
		}

		thread.setName(name);
		return thread;
	}
}

public class Main {
	public static void main(String[] args) {
		int count = 6;
		var multiExecutor = Executors.newFixedThreadPool(count, new ColorThreadFactory());

		for(int i=0; i<count; i++) {
			multiExecutor.execute(Main::countdown);
		}
		multiExecutor.shutdown();
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

		for(int i=20; i>0; i--) {
			System.out.println(color + " " + threadName.replace("ANSI_", "") + " " + i);
		}
	}
}
