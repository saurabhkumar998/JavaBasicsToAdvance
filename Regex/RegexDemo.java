package Regex;

import java.util.Arrays;
import java.util.Scanner;

public class RegexDemo {
    public static void main(String[] args) {

        String helloWorld = "%s %s".formatted("Hello", "World");
        String helloWorld2 = String.format("%s %s", "Hello", "World");
        String helloWorld3 = RegexDemo.format("Testing %s %s example", "Hello", "World");

        System.out.println("Using String's formatted method : " + helloWorld);
        System.out.println("Using String.format method : " + helloWorld2);
        System.out.println("Using custom format method : " + helloWorld3);


        String testString = "Anyone can learn the abc's , 123's of regular expressions";
        String replacement = "(-)";

        String[] patterns = {
                "abc",
                "a|b|c",
                "123",
                "A",
                "[abc]",
                "[123]",
                "[A]",
                "ab|bc",
                "[a-z]",
                "[0-9]",
                "[A-Z]",
                "[a-zA-Z]",
                "[a-zA-Z]*",
                "[0-9]*",
                "[0-9]+",
                "[A-Z]*",
                "[0-9]{2}",
                "[a-zA-Z]*$",
                "^[a-zA-Z]{3}",
                "[aA]ny\\b"
        };

        for(String pattern : patterns) {
            String output = testString.replaceFirst(pattern, replacement);
            System.out.println(pattern + " -> " + output);
        }

        System.out.println("-".repeat(20));


        // ####################  Methods which uses regex  #####################

        String paragraph = """
                Lorem Ipsum is simply dummy, ipsum text of the printing and typesetting industry.
                Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.
                when an unknown printer took a galley of type and scrambled it to make a type specimen book.
                It has survived not only five centuries,
                but also the leap into electronic typesetting.
                remaining essentially unchanged.
                """;

        String[] lines = paragraph.split("\n");
        System.out.println("This Paragraph has " + lines.length + " lines");

        String[] lines2 = paragraph.split("\\R");
        System.out.println("This Paragraph has " + lines2.length + " lines");

        String[] words = paragraph.split("\\s");
        System.out.println("This Paragraph has " + words.length + " words");

        System.out.println(paragraph.replaceAll("[a-zA-Z]+orem", "[TEST]"));


        Scanner scanner = new Scanner(paragraph);
        // this is a default java delimiter for a paragraph/string
        System.out.println("Default Scanner Delimiter : " + scanner.delimiter());

        scanner.useDelimiter("\\R");
/**
        while(scanner.hasNext()) {
            System.out.println(scanner.next());
        }
*/

/**
        // this works similar to the above foreach code
        scanner.tokens()
                        .forEach(System.out::println);
*/
/*
        scanner.tokens()
                        .map(s ->  s + " - " + Arrays.stream(s.split("\\s")).count())
                                .forEach(System.out::println);
*/

        System.out.println(scanner.findInLine("[a-zA-Z]+psum"));
        System.out.println(scanner.findInLine("[a-zA-Z]+psum"));

        scanner.close();
    }

    public static String format(String regex, String... args) {
        int index = 0;
        while(regex.matches(".*%s.*")) {
            regex = regex.replaceFirst("%s", args[index++]);
        }

        return regex;
    }
}
