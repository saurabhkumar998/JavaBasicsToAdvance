package Streams;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntermediateOperationsDemo {
    public static void main(String[] args) {

        IntStream.iterate((int) 'A', i-> i <= (int) 'z', i -> i+1)
                .filter(Character::isAlphabetic)
               // .filter(i -> Character.toUpperCase(i) > 'E')
                // .skip(3)
                //.dropWhile(i -> Character.toUpperCase(i) <= 'E')
                //.takeWhile(i -> i < 'a')
                .map(Character::toUpperCase)
                .distinct()
                .forEach(i -> System.out.printf("%c ", i));


        System.out.println();

        Random random = new Random();
        Stream.generate(() -> random.nextInt((int) 'A', (int) 'Z' + 1))
                .limit(50)
                .distinct()
                .sorted()
                .forEach(s -> System.out.printf("%c ", s));


    }
}
