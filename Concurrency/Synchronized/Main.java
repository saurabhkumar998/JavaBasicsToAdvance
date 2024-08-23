package Concurrency.Synchronized;

public class Main {
	public static void main(String[] args) {
		BankAccount companyAccount = new BankAccount(10000);

		Thread thread1 = new Thread(() -> {companyAccount.withdraw(2500);});
		Thread thread2 = new Thread(() -> {companyAccount.deposit(5000);});
		Thread thread3 = new Thread(() -> {companyAccount.withdraw(2500);});

		thread1.start();
		thread2.start();
		thread3.start();

		try {
			thread1.join();
			thread2.join();
			thread3.join();
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Final Balance : " + companyAccount.getBalance());
	}
}
