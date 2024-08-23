package HashCodeAndEqualsDemo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashCodeAndEqualsDemo {
    public static void main(String[] args) {
        String aText = "Hello";
        String bText = "Hello";
        String cText = String.join("l", "He", "lo");
        String dText = "He".concat("llo");
        String etext = new String("Hello");
        String fText = "hello";

        List<String> hellos = new ArrayList<>(List.of(aText, bText, cText, dText, etext, fText));

        hellos.forEach(s -> System.out.println(s + " - " + s.hashCode()));

        Set<String> set = new HashSet<>(hellos);

        System.out.println(set);
    }
}
