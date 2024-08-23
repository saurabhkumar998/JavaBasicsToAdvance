package CollectionFramework.SetDemo;

import java.util.*;

class Person implements Comparable<Person> {
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        int result = this.age.compareTo(o.age);
        return (result == 1) ? this.name.compareTo(o.name) : result;
    }
}

public class SetDemo {
    public static void main(String[] args) {

        Set<Person> hashSet = new HashSet<>(List.of(
                new Person("Saurabh", 24),
                new Person("Virat", 35),
                new Person("Gaurav", 21),
                new Person("Saumya", 25)
        ));

        System.out.println(hashSet);

        Set<Person> linkedHashSet = new LinkedHashSet<>(List.of(
                new Person("Saurabh", 24),
                new Person("Virat", 35),
                new Person("Gaurav", 21),
                new Person("Saumya", 25)
        ));

        System.out.println(linkedHashSet);

        Set<Person> treeSet = new TreeSet<>(List.of(
                new Person("Saurabh", 24),
                new Person("Virat", 35),
                new Person("Gaurav", 21),
                new Person("Saumya", 25),
                new Person("Stark", 25)
        ));

        System.out.println(treeSet);

        treeSet.forEach(s -> System.out.println(s.name));

        for (Person person : treeSet) {
            if(person.name == "Virat") {
                person.name = "Virat Kohli";
            }
            System.out.println(person.name);
        }

        System.out.println(treeSet);
    }
}
