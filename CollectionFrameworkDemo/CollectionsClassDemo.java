package CollectionFrameworkDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsClassDemo {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

        System.out.println(list);

        Collections.fill(list, 1);

        System.out.println(list);

        List<Integer> list2 = new ArrayList<>(Collections.nCopies(10, 23));

        System.out.println(list2);

        //list2.addAll(list);

        //System.out.println(list2);

        Collections.copy(list2, list);

        System.out.println(list2);

        Collections.shuffle(list2);
        System.out.println(list2);

        Collections.reverse(list2);
        System.out.println(list2);

       // Collections.sort(list2);
        System.out.println(list2);

        List<Integer> list3 = new ArrayList<>(list2.subList(3,6));

        System.out.println(list3);

        Collections.shuffle(list2);

        int indexOfSublist = Collections.indexOfSubList(list2, list3);

        System.out.println(indexOfSublist);

        System.out.println(list2.containsAll(list3));

        System.out.println(Collections.disjoint(list2, list3));
        System.out.println(Collections.disjoint(list2, List.of(99,98,77)));

        System.out.println(list2);
        Collections.sort(list2);
        System.out.println(list2);

        System.out.println(Collections.binarySearch(list2, 1));

        List<Integer> list4 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));

        Collections.rotate(list4, 2);
        Collections.rotate(list4, -4);

        System.out.println(list4);

        // swaps the element at index 0 with element at index 3
        Collections.swap(list4, 0, 3);
        System.out.println(list4);

        

    }
}
