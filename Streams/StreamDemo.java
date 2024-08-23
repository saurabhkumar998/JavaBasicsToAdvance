package Streams;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("A", "B", "C", "D", "E", "F", "G", "H"));

        // the elements in this list reference the original list from which it was created
        List<String> subList = list.subList(0,4);

        System.out.println(list);
        System.out.println(subList);

        System.out.println("-".repeat(20));

        subList.set(1,"X");

        System.out.println(list);
        System.out.println(subList);

        System.out.println("".repeat(20));

        List<String> subList2 = new ArrayList<>(list.subList(0,4));
        subList2.set(0, "Z");

        System.out.println(list);
        System.out.println(subList2);

        var tempStream = list.stream()
                .limit(4)
                .filter(s -> !s.isBlank())
                .map(s -> s + "ABC");
//                .forEach(s -> System.out.println(s));

        tempStream.forEach(s -> System.out.println(s));

        //the below code will throw error as the stream has already been closed(a terminal operation was added earlier)
        //tempStream.forEach(s -> System.out.println(s));

        String[] strings = {"One", "Two", "Three"};

        Arrays.stream(strings)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        Stream.of(strings)
                .sorted()
                .forEach(System.out::println);

        Stream.of("Four", "Five", "Six")
                .map(String::toUpperCase)
                .forEach(System.out::println);


        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());
   //             .forEach(System.out::println);

        var secondStream = Stream.of("Four", "Five", "Six")
                .map(String::toUpperCase);
        //        .forEach(System.out::println);


        System.out.println("-".repeat(10) + " Concatenating two streams and performing intermediate operation on them " + "-".repeat(10));

        Stream.concat(secondStream, firstStream)
                .map(s -> s.charAt(0) + " - " + s)
                .forEach(System.out::println);

        // ############### Stream Sources ##############


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

    public static boolean isPrime(int n) {
        if(n<=2) {
            return (n==2);
        }

        for(int i=2; i<n; i++) {
            if(n%i == 0) {
                return false;
            }
        }

        return true;
    }


}
