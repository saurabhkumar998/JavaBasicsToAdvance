package StreamDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapDemo {
    public static void main(String[] args) {

        List<List<Integer>> nestedList = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));

        List<Integer> flattenedList = nestedList.stream()
                .flatMap(List::stream)
                .toList();

        System.out.println("NestedList : " + nestedList);
        System.out.println("FlattenedList : " + flattenedList);


        List<String> words = Arrays.asList("hello world", "java stream");
        List<String> extractedWords = words.stream()
                .flatMap(s -> Stream.of(s.split(" ")))
                .toList();

        System.out.println("Extracted Words : " + extractedWords);

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> combinedList = Stream.of(list1, list2)
                .flatMap(List::stream)
                .toList();

        System.out.println("Combined List : " + combinedList);


    }
}
