package Concurrency.ThreadProblems;

import java.io.File;

public class DeadLock {
	public static void main(String[] args) {

		File resourceA = new File("inputData.csv");
		File resourceB = new File("OutputData.json");


		Thread threadA = new Thread(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " attempting to lock resourceA (csv)");

			synchronized (resourceA) {
				try {
					// doing some operation on th resourceA
					Thread.sleep(1000);;
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(threadName + " Next attempting to lock resourceB (json), " +
						"still has lock on resourceA (csv)");

				synchronized (resourceB) {
					System.out.println(threadName + " also has a lock on resourceB (json)");
				}
				System.out.println(threadName + " has release lock on resourceB (json)");
			}
			System.out.println(threadName + " has released resourceA (csv)");
		}, "THREAD-A");

		Thread threadB = new Thread(() -> {
			/*
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " attempting to lock resourceB (json)");

			synchronized (resourceB) {
				System.out.println(threadName + " has lock on resourceB (json)");

				try{
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(threadName + " Next attempting to lock resourceA (csv) " +
						"still has lock on resourceB (json)");
				synchronized (resourceA) {
					System.out.println(threadName + " has lock on resourceA (csv)");
				}
				System.out.println(threadName + " has released lock on resourceA (csv)");
			}
			System.out.println(threadName + " has released the lock on resourceB (json)");
			 */

			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " attempting to lock resourceA (csv)");

			synchronized (resourceA) {
				try {
					// doing some operation on th resourceA
					Thread.sleep(1000);;
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(threadName + " Next attempting to lock resourceB (json), " +
						"still has lock on resourceA (csv)");

				synchronized (resourceB) {
					System.out.println(threadName + " also has a lock on resourceB (json)");
				}
				System.out.println(threadName + " has release lock on resourceB (json)");
			}
			System.out.println(threadName + " has released resourceA (csv)");

		}, "THREAD-B");

		threadA.start();
		threadB.start();

		try{
			threadA.join();
			threadB.join();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}

		// The above code will get stuck in a deadlock as the threadA has locked the resourceA and is trying
		// to lock the resourceB while threadB has locked the resourceB and is trying to lock the resourceA

		// both these threads will be stuck waiting for each other to release the resources.

		// we can fix the above issue by acquiring locks on the resources in the same order -- see threadB
	}
}
