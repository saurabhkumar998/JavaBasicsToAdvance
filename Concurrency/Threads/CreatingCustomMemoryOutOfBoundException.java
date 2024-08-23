package Concurrency.Threads;

public class CreatingCustomMemoryOutOfBoundException {
	public static void main(String[] args) {

		while(true) {
			new Animal();
		}
	}
}

class Animal {
	String name;
}