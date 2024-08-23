package LambdaExpressionFunctionalInterfaceAndMethodReference;

import java.util.ArrayList;
import java.util.List;

public class LambdaExpressionDemo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of(
                "Alpha", "Bravo", "Charlie", "Delta"
        ));

        list.forEach((String s) -> System.out.println(s));
        list.forEach((var s) -> System.out.println(s));
        list.forEach((s) -> System.out.println(s));
        list.forEach(s -> System.out.println(s));
        // we've replaced the lambda expression with method reference
        list.forEach(System.out::println);

        System.out.println("-".repeat(10));

        list.forEach(s -> {
            String prefix = "nato";
            char first = s.charAt(0);
            System.out.println(prefix + " " + s + " means " + first);

            // the below is not a good practice, a variable in a lambda expression should be final or effective final
            //prefix = "anything";
        });

        System.out.println("-".repeat(20));

        list.removeIf(s -> s.equalsIgnoreCase("alpha"));
        list.forEach(s -> System.out.println(s));

        list.removeIf(s -> s.startsWith("De"));

        System.out.println("-".repeat(20));

        list.forEach(s -> System.out.println(s));

        list.replaceAll(s -> s.charAt(0) + " - " + s.toUpperCase());

        list.forEach(s -> System.out.println(s));

    }
}
