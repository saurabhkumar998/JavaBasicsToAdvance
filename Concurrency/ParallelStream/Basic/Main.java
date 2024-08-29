package Concurrency.ParallelStream.Basic;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		int numberLength = 100_000_000;
		long[] numbers = new Random().longs(numberLength, 1, numberLength).toArray();

		// Calculating Average of 100_000 numbers serially
		long start = System.nanoTime();
		double averageSerial = Arrays.stream(numbers).average().orElseThrow();
		long elapsedSerial = System.nanoTime() - start;

		System.out.printf("Ave = %.2f , Elapsed = %d nanos or %.2f ms%n", averageSerial, elapsedSerial, elapsedSerial/1000000.0);

		// Calculating Average of 100_000 numbers in parallel
		start = System.nanoTime();
		double averageParallel = Arrays.stream(numbers).parallel().average().orElseThrow();
		long elapsedParallel = System.nanoTime() - start;

		System.out.printf("Ave = %.2f , Elapsed = %d nanos or %.2f ms%n", averageParallel, elapsedParallel, elapsedParallel/1000000.0);


		long delta = 0;
		int iterations = 25;

		for(int i=0; i<iterations; i++) {

			// Calculating Average of 100_000 numbers serially
			long start1 = System.nanoTime();
			double averageSerial1 = Arrays.stream(numbers).average().orElseThrow();
			long elapsedSerial1 = System.nanoTime() - start1;

			// Calculating Average of 100_000 numbers in parallel
			start1 = System.nanoTime();
			double averageParallel1 = Arrays.stream(numbers).parallel().average().orElseThrow();
			long elapsedParallel1 = System.nanoTime() - start1;

			delta += elapsedSerial1 - elapsedParallel1;
		}

		System.out.printf("Parallel took [%d] nanos or [%.2f] ms faster on average.%n",
				delta/iterations , delta/1000000.0/iterations);
	}
}
