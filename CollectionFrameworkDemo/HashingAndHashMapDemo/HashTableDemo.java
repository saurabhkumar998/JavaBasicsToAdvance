package CollectionFrameworkDemo.HashingAndHashMapDemo;

import java.util.Enumeration;
import java.util.Hashtable;

public class HashTableDemo {

    public static void main(String[] args) {
        Hashtable<String, Integer> hashtable = new Hashtable<>();

        hashtable.put("five", 5);
        hashtable.put("six", 6);
        hashtable.put("seven", 7);

        System.out.println(hashtable);

        // Iterating the keys using Enumeration
        Enumeration<String> keyEnumeration = hashtable.keys();
        while (keyEnumeration.hasMoreElements()) {
            System.out.println(keyEnumeration.nextElement());
        }

        // Iterating the values using Enumeration
        Enumeration<Integer> valueEnumeration = hashtable.elements();
        while(valueEnumeration.hasMoreElements()) {
            System.out.println(valueEnumeration.nextElement());
        }

        // Iterating using keySet()
        for (String key : hashtable.keySet()) {
            Integer value = hashtable.get(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }



    }
}
