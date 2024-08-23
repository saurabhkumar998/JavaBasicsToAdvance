package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternAndMatcherDemo {
    public static void main(String[] args) {

        String sentence = "I like motorcycles.";

        boolean matched = Pattern.matches("[A-Z].*[.]", sentence);
        System.out.println("Matched : " + matched);


        Pattern pattern = Pattern.compile("[A-Z].*[.]");
        Matcher matcher = pattern.matcher(sentence);
        System.out.println("Matched : " + matcher.matches());

        System.out.println("Sentence length : " + sentence.length());
        System.out.println("Matcher ending index : " + matcher.end());



        String htmlSnippet = """
                <H1>My Heading</H1>
                <h2>Sub-heading</h2>
                <p>This is a paragraph about something.</p>
                <p>This is another paragraph about something else.</p>
                <h3>Summary</h3>
                """;

        Pattern htmlPattern = Pattern.compile("<[hH](?<level>\\d)>(.*)</[hH]\\d>");
        Matcher htmlMatcher = htmlPattern.matcher(htmlSnippet);

        while(htmlMatcher.find())
        {
//            System.out.println("group : " + htmlMatcher.group());
//            System.out.println("group0 : " + htmlMatcher.group(0));
//            System.out.println("group1: " + htmlMatcher.group(1));
//            System.out.println("group2: " + htmlMatcher.group(2));
//            System.out.println("group1 with level name: " + htmlMatcher.group("level"));

            System.out.println(htmlMatcher.group("level") +
                    " " + htmlMatcher.group(2));
        }

        htmlMatcher.reset();

        htmlMatcher.results().forEach(mr -> System.out.println(mr.group(1) + " " + mr.group(2)));

        String tabbedText = """
                group1	group2	group3
                1	2	3
                a	b	c
                """;

        tabbedText.lines()
                .flatMap(s -> Pattern.compile("\\t").splitAsStream(s))
                .forEach(System.out::println);

		htmlMatcher.reset();

		String updatedSnippet = htmlMatcher.replaceFirst("First Header");
		System.out.println("-".repeat(20));
		System.out.println(updatedSnippet);
		System.out.println(htmlMatcher.start() + " : " + htmlMatcher.end());
    }
}
