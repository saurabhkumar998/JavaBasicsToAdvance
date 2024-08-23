package IteratorAndListIterator;

import java.sql.SQLOutput;
import java.util.*;

public class LinkedListAndIteratorDemo {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        System.out.println(list.getClass().getName());
        var list2 = new LinkedList<String>();
        System.out.println(list2.getClass().getName());

        // arraylist methods
        list.add("Gurgaon");
        list.add("Mumbai");
        list.add("Mysore");
        list.add("Delhi");
        list.add("Punjab");
        list.add("Amritsar");
        list.add("Phagwara");
        list.remove(1);
        list.addFirst("Siwan");
        list.addLast("Gaya");
        list.add("Kashi");
        System.out.println(list);
        System.out.println(list.indexOf("Kashi"));
        System.out.println(list.lastIndexOf("Kashi"));
        System.out.println(list.element());

        //System.out.println(list);
        list.remove();
        list.removeFirst();
        list.removeLast();
        //System.out.println(list);

        // Queue/Deque methods
        list.offer("Gopalganj");
        list.offerFirst("Chennai");
        list.offerLast("Mangalore");
        list.pollFirst();
        list.pollLast();

        // Stack methods
        list.push("Bangalore");
        //System.out.println(list);
        list.pop();
        System.out.println(list);


        // Iterator
        LinkedList<String> newList = new LinkedList<>();
        newList.add("City1");
        newList.add("City2");
        newList.add("City3");
        newList.add("City4");
        newList.add("City5");

//        var iterator = newList.iterator();
//
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//

        // ListIterator
        ListIterator<String> listIterator = newList.listIterator();
        // Overloaded method of iterator, the iterator start frm the first index
        //ListIterator<String> iterator = list.listIterator(1);

        System.out.println("\n".repeat(1));
        while(listIterator.hasNext()) {
            String city = listIterator.next();
            if(city.equals("City2")) {
                listIterator.add("City999");
            }
            else if(city.equals("City3")) {
                listIterator.remove();
            }
        }

        System.out.println(newList);

        System.out.println("\n".repeat(1));

        while(listIterator.hasPrevious()) {

            System.out.println(listIterator.previous());
        }

    }
}
