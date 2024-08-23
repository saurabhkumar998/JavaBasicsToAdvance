package LambdaExpressionFunctionalInterfaceAndMethodReference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LambdaExpressionDemo {

    record Person(String firstName, String lastName) {
        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(List.of(
                new Person("Virat","Kohli"),
                new Person("Akay","Kohli"),
                new Person("Rohit", "Sharma"),
                new Person("Ravindra", "Jadeja"),
                new Person("Jasprit", "Bumrah")
        ));

        // ######## 1 #########
        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName.compareTo(o2.lastName);
            }
        };

        System.out.println(people);

        people.sort(comparator);

        System.out.println(people);

        // ######## 2 ##########

        people.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.firstName.compareTo(o2.firstName);
            }
        });

        System.out.println(people);

        // ######## 3 ########
        people.sort((o1, o2) -> o1.firstName.compareTo(o2.firstName));

        System.out.println(people);


        // here we have created an enhanced comparator which is extending the Comparator interface,
        // since the EnhancedComparator interface has two abstract method, one is its own and other is Comparator's compare method
        // it is not a functional interface (two abstract methods) hence lambda expression is inapplicable
        interface EnhancedComparator<T> extends Comparator<T> {
            int secondLevel(T o1, T o2);
        }

        var mixedComparator = new EnhancedComparator<Person>() {
            @Override
            public int secondLevel(Person o1, Person o2) {
               return o1.firstName.compareTo(o2.firstName);
            }

            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.lastName.compareTo(o2.lastName);
                return (result == 0) ? secondLevel(o1,o2) : result;
            }
        };

        people.sort(mixedComparator);

        System.out.println(people);

    }
}
