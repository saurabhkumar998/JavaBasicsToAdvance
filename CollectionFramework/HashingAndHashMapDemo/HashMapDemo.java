package CollectionFramework.HashingAndHashMapDemo;

import java.util.*;

public class HashMapDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Map<String, Integer> map = new HashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();

        // this will throw ConcurrentModificationException
        // because we are updating the map after the iterator is initialized
        //map.put("four", 4);

        while(it.hasNext()) {
            System.out.println(it.next());
        }

        // Traversing a HashMap
        Set<Map.Entry<String, Integer>> entrySet =  map.entrySet();
        for(Map.Entry<String, Integer> e : entrySet) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }

        var entrySet2 =  map.entrySet();
        for(var e : entrySet) {
            System.out.println(e.getKey() + " : " + e.getValue());
        }

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        map.forEach((k, v) -> System.out.println(k + " : " + v));

        map.forEach((k,v) -> {
           map.put(k, v*2);
        });
        System.out.println(map);


    }
}
