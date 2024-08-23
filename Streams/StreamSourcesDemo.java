package Streams;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSourcesDemo {
    public static void main(String[] args) {

        Random random = new Random();
        Stream.generate(() -> random.nextInt(2))
                .limit(10)
                .forEach(i -> System.out.print(i + " "));

        System.out.println("\n"+"-".repeat(10) + " The below Stream prints numbers from 1 to 25 " + "-".repeat(10));

        Stream.iterate(1, n -> n+1)
                .limit(25)
                .forEach(i -> System.out.print(i + " "));

        // the below code is optimal than the above one Stream does autoboxing and unboxing internally so it takes more time
        System.out.println("\n"+"-".repeat(10) + " The below Stream prints numbers from 1 to 25 " + "-".repeat(10));
        IntStream.iterate(1, n-> n+1)
                .limit(25)
                .forEach(i -> System.out.print(i + " "));

        System.out.println("\n"+"-".repeat(10) + " The below Stream prints first 25 prime numbers " + "-".repeat(10));

        IntStream.iterate(1, n -> n+1)
                .limit(25)
                .filter(StreamDemo::isPrime)
                .forEach(i -> System.out.print(i + " "));

        System.out.println();

        // this iterate produces an infinite stream
        IntStream.iterate(1, n -> n+1)
                .filter(StreamDemo::isPrime)
                .limit(25)
                .forEach(i -> System.out.print(i + " "));

        // this iterate produces an finite stream
        System.out.println();
        IntStream.iterate(1, n -> n<=25, n -> n+1)
                .filter(StreamDemo::isPrime)
                .forEach(i -> System.out.print(i + " "));

        System.out.println();

        // the below stream iterates till the specified ranged excluded
        IntStream.range(1, 100)
                .filter(StreamDemo::isPrime)
                .forEach(i -> System.out.print(i + " "));

        System.out.println();

        // the below stream iterates till the specified ranged included
        IntStream.rangeClosed(1,100)
                .filter(StreamDemo::isPrime)
                .forEach(i -> System.out.print(i + " "));


    }
}
