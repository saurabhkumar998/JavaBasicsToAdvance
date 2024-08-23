package Concurrency.MultipleThreads;

public class Main {
	public static void main(String[] args) {
		for (ThreadColor color : ThreadColor.values()) {
			color.printColorName();
		}
	}
}
