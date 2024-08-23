package ComparableDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Student implements Comparable<Student> {
    String name;

    public Student() {

    }

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.name);
    }
}
public class ComparableDemo {
    public static void main(String[] args) {

        Student[] students = {new Student("Saurabh"), new Student("Atul"),new Student("Virat")};

        System.out.println(Arrays.toString(students));

        Arrays.sort(students);

        System.out.println(Arrays.toString(students));

    }
}
