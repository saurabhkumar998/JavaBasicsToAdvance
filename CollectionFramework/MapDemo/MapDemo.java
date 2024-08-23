package CollectionFramework.MapDemo;

import java.util.*;

class Person {
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
}

public class MapDemo {
    public static void main(String[] args) {

        Map<Integer, Person> hashMap = new HashMap<>();

        hashMap.put(1232, new Person("Saurabh", 24));
        hashMap.put(8433, new Person("Saumya", 25));
        hashMap.put(43322, new Person("Gaurav", 21));
        hashMap.put(18, new Person("Virat", 35));
        hashMap.put(null, new Person("Suresh Raina", 35));
        hashMap.put(null, null);

        System.out.println(hashMap);

        Map<Integer, Person> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put(1232, new Person("Saurabh", 24));
        linkedHashMap.put(8433, new Person("Saumya", 25));
        linkedHashMap.put(43322, new Person("Gaurav", 21));
        linkedHashMap.put(18, new Person("Virat", 35));
        linkedHashMap.put(null, new Person("Dhoni", 42));
        linkedHashMap.put(null, null);

        System.out.println(linkedHashMap);

        Map<Integer, Person> treeMap = new TreeMap<>();

        treeMap.put(1232, new Person("Saurabh", 24));
        treeMap.put(8433, new Person("Saumya", 25));
        treeMap.put(43322, new Person("Gaurav", 21));
        treeMap.put(18, new Person("Virat", 35));
        treeMap.put(7, new Person("Thala", 42));
        treeMap.put(12, null);

        System.out.println(treeMap);

        Map<Integer, Person> treeMap2 = new TreeMap<>((a,b) -> {
            if(a == null) {
                return b;
            }else if(b == null) {
                return a;
            }else {
                return a-b;
            }
        });

        treeMap2.put(1232, new Person("Saurabh", 24));
        treeMap2.put(8433, new Person("Saumya", 25));
        treeMap2.put(43322, new Person("Gaurav", 21));
        treeMap2.put(18, new Person("Virat", 35));
        treeMap2.put(7, new Person("Thala", 42));
        treeMap2.put(null, null);

        System.out.println("Printing the treeMap2 : ");
        System.out.println(treeMap2);
    }

}
