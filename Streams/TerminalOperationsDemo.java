package Streams;

import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import java.util.stream.IntStream;


record Seat(char rowMarker, int seatNumber, boolean isReserved) {
    Seat(char rowMarker, int seatNumber) {
          this(rowMarker, seatNumber, new Random().nextBoolean());
//        this(rowMarker, seatNumber, true);
//        this(rowMarker, seatNumber, false);
    }
}
public class TerminalOperationsDemo {
    public static void main(String[] args) {

        var result = IntStream.iterate(0, i -> i<=1000, i -> i + 10)
                .summaryStatistics();

        System.out.println(result);

        var result2 = IntStream.iterate(0, i -> i<=1000, i -> i + 3)
                .summaryStatistics();

        System.out.println(result2);

        var leapYearData = IntStream.iterate(2000, i -> i <= 2025, i-> i +1)
                .filter(i -> i%4 == 0)
                .peek(System.out::println)
                .summaryStatistics();

        System.out.println("Leap Year Data : " + leapYearData);


        Seat[] seats = new Seat[100];
        Arrays.setAll(seats, i -> new Seat((char)('A' + i/10), i % 10 + 1));

       // Arrays.asList(seats).forEach(System.out::println);

        long reservationCount = Arrays
                .stream(seats)
                .filter(Seat::isReserved)
                .count();

        System.out.println("Reservation Count : " + reservationCount);

        boolean hasBookings = Arrays
                .stream(seats)
                .anyMatch(Seat::isReserved);

        System.out.println("Has Bookings : " + hasBookings);

        boolean fullyBooked = Arrays
                .stream(seats)
                .allMatch(Seat::isReserved);

        System.out.println("Fully Booked : " + fullyBooked);

        boolean eventWashedOut = Arrays
                .stream(seats)
                .noneMatch(Seat::isReserved);

        System.out.println("Event Washed Out : " + eventWashedOut);

        // the collect() termainal operation returns a mutable list
        List<Seat> reservedSeats = Arrays.stream(seats)
                .filter(Seat::isReserved)
                        .collect(Collectors.toList());
        

        System.out.println("Reserved Seats using collector() : " + reservedSeats);


        // the toList() terminal operation returns an immutable list
        List<Seat> reservedSeats1 = Arrays.stream(seats)
                .filter(Seat::isReserved)
                .toList();

        System.out.println("Reserved Seats using toList(): " + reservedSeats1);


        var unreservedSeats = Arrays.stream(seats)
                .filter(s -> !s.isReserved())
//                .toArray(); // this returns an array of object
                .toArray(size -> new Seat[size]);  // this returns an array of the given type

        System.out.println("UnReserved Seats : " + Arrays.asList(unreservedSeats));


        Set<Seat> reservedSeatsHashSet = Arrays.stream(seats)
                .filter(Seat::isReserved)
//                .sorted() // sorted is redundant as hashSet dont contain sorted value
                .collect(Collectors.toSet());

        System.out.println("Reserved Seats HashSet : " + reservedSeatsHashSet);


        // this reduce method returns an Optional
        Optional<String> allSeatNumbers = Arrays.stream(seats)
                .filter(Seat::isReserved)
                .map(s -> s.rowMarker() + "-" + s.seatNumber())
                .reduce((s,v) -> s + ", " + v);

        System.out.println(allSeatNumbers.orElse(""));


        // this reduce method returns an Optional
        String allSeats = Arrays.stream(seats)
                .filter(Seat::isReserved)
                .map(s -> s.rowMarker() + "-" + s.seatNumber())
                .reduce("", (s,v) -> s + ", " + v);

        System.out.println(allSeats);

        Optional<String> allSeatNumbers2 = Arrays.stream(seats)
                .filter(Seat::isReserved)
                .map(s -> s.rowMarker() + "-" + s.seatNumber())
                .reduce((a,b) -> String.join(",", a, b));


        System.out.println(allSeatNumbers2);

        List<Student> students = new ArrayList<>(List.of(

                new Student("Gaurav", 21, "IN"),
                new Student("Rishabh", 21, "US"),
                new Student("Ravi", 20, "AU"),
                new Student("Saumya", 25, "IN"),
                new Student("Saurabh", 24, "AU"),
                new Student("Rahul", 24, "IN"),
                new Student("Virat", 22, "AU")));


        Optional<Student> student = students.stream()
                .filter(s -> s.age <= 14)
                .findAny();

        System.out.println(student);


        students.stream()
                .filter(s -> s.age <= 24)
    //            .filter(s -> s.age <= 14)
                .findAny()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Not Found"));


        students.stream()
                //            .filter(s -> s.age <= 24)
                .filter(s -> s.age <= 24)
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Not Found"));

        students.stream()
                //            .filter(s -> s.age <= 24)
                .filter(s -> s.age <= 24)
                //.sorted((o1, o2) -> o1.age.compareTo(o2.age))
                .sorted((Comparator.comparing(Student::getAge)))
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Not Found"));


        //            .filter(s -> s.age <= 24)
        //.sorted((o1, o2) -> o1.age.compareTo(o2.age))
        students.stream()
                //            .filter(s -> s.age <= 24)
                .filter(s -> s.age <= 24)
                .min(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Not Found"));


        students.stream()
                //            .filter(s -> s.age <= 24)
                .filter(s -> s.age <= 24)
                //.sorted((o1, o2) -> o1.age.compareTo(o2.age))
                .sorted((Comparator.comparing(Student::getAge).reversed()))
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Not Found"));


        //            .filter(s -> s.age <= 24)
        //.sorted((o1, o2) -> o1.age.compareTo(o2.age))
        students.stream()
                //            .filter(s -> s.age <= 24)
                .filter(s -> s.age <= 24)
                .max(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Not Found"));

        students.stream()
                .filter(s -> s.getAge() <= 24)
                .mapToInt(Student::getAge)
                .average()
                .ifPresentOrElse((avg) -> System.out.println("Average age under 24 is : " + avg),
                        () -> System.out.println("Not Found"));


        var mappedStudents = students.stream()
                .collect(Collectors.groupingBy(Student::getAge));

        mappedStudents.forEach((k,v) -> System.out.println(k + " : " + v));

        var youngerStudents = students.stream()
                .collect(groupingBy(Student::getCountry,
                        filtering(s -> s.getAge() <= 23, toList())));


        youngerStudents.forEach((k,v) -> System.out.println(k + " : " + v));

    }


}

class Student {
    String name;
    Integer age;

    String country;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public Student(String name, Integer age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                '}';
    }
}
