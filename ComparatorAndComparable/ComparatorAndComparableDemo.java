package ComparatorAndComparable;

import java.util.*;

class Student implements Comparable<Student> {
    Integer age;
    String name;

    public Student(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                "}";
    }

    @Override
    public int compareTo(Student o) {
        return this.age - o.age;
        //return this.name.compareTo(o.name);
    }
}
public class ComparatorAndComparableDemo {
    public static void main(String[] args) {

        List<Integer> numberList = new ArrayList<>(List.of(3,6,4,23,7,77,55,223,775,4,45,7));
        System.out.println(numberList);
        Collections.sort(numberList);
        //numberList.sort(null);
        System.out.println(numberList);
        Collections.sort(numberList.reversed());
        System.out.println(numberList);

        List<Student> students = new ArrayList<>(List.of(new Student(12, "Saurabh"),
                new Student(18, "Saumya"),
                new Student(10, "Gaurav")));

        System.out.println(students);

        Comparator<Student> studentComparator = new Comparator<>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.name.compareTo(o2.name);
            }
        };

      //  Collections.sort(students, studentComparator);
      //  students.sort(studentComparator);
      //  students.sort(null);
      //  students.sort((a,b) -> a.name.compareTo(b.name));
        students.sort((a,b) -> a.age-b.age);
        System.out.println(students);
    }
}
