package Concurrency.ParallelStream.Advance;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Person(String firstName, String lastName, int age) {
	private final static String[] firsts = {"Able", "Bob", "Charlie", "Donna", "Eve", "Fred"};
	private final static String[] lasts = {"Norton", "OHara", "Petersen", "Quincy", "Richardson", "Smith"};

	private final static Random random = new Random();

	public Person() {
		this(firsts[random.nextInt(firsts.length)], lasts[random.nextInt(lasts.length)], random.nextInt(18, 100));
	}

	@Override
	public String toString() {
		return "%s, %s (%d)".formatted(lastName, firstName, age);
	}
}
public class Main {
	public static void main(String[] args) {

		var persons = Stream.generate(Person::new)
				.limit(10)
				.sorted(Comparator.comparing(Person::lastName))
				.toArray();

		for(var person : persons) {
			System.out.println(person);
		}

		System.out.println("-".repeat(20));


		//Stream.generate(Person::new)
		Arrays.stream(persons)
				.limit(10)
				.parallel()
//				.sorted(Comparator.comparing(Person::lastName))
				.forEachOrdered(System.out::println);

		System.out.println("-".repeat(20));

		int sum = IntStream.range(1,101)
				.reduce(0, Integer::sum);

		System.out.println("The Sum of the numbers is : "+ sum);

		String humptyDumpty = """
    		Humpty Dumpty sat on a wall,
   			Humpty Dumpty had a great fall,
   			All the king's horses and all the king's men,
   			Couldn't put Humpty together again.
			""";

		var words = new Scanner(humptyDumpty).tokens().toList();
		words.forEach(System.out::println);
		System.out.println("-".repeat(20));

		var backTogetherAgain = words
				.parallelStream()
						.collect(Collectors.joining(" "));
//				.reduce(
//						new StringJoiner(" "),
//						StringJoiner::add,
//						StringJoiner::merge
//				);

		System.out.println(backTogetherAgain);

		Map<String, Long> lastNameCounts = Stream
				.generate(Person::new)
				.limit(10000)
				.parallel()
//				.collect(Collectors.groupingBy(
//						Person::lastName,
//						Collectors.counting()
//				));
				.collect(Collectors.groupingByConcurrent(
						Person::lastName,
						Collectors.counting()
				));

		lastNameCounts.entrySet().forEach(System.out::println);

		int totalsum = lastNameCounts.values().stream().mapToInt(v -> v.intValue())
				.sum();

		System.out.println("Total = " + totalsum);

		System.out.println(lastNameCounts.getClass().getName());

		//var lastCounts = new TreeMap<String, Long>();
		var lastCounts = new ConcurrentSkipListMap<String, Long>();
		//var lastCounts = Collections.synchronizedMap(new TreeMap<String, Long>());
		Stream.generate(Person::new)
				.limit(10000)
				.parallel()
				.forEach((person) -> lastCounts.merge(person.lastName(), 1L, Long::sum));

		System.out.println(lastCounts);

		int total = 0;
		for (long count : lastCounts.values()) {
			total += count;
		}
		System.out.println("Total = " + total);

		System.out.println(lastCounts.getClass().getName());


	}

}
