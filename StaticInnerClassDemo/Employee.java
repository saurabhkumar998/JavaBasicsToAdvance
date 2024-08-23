package StaticInnerClassDemo;

import java.util.Comparator;

public class Employee {

    // the below comparator class is nested inner class. the advantage of having a nested inner
    // class is that it can access the private members of the parent class
    public static class EmployeeComparator <T extends Employee> implements Comparator<Employee> {

        String sortType;

        public EmployeeComparator() {
            this("name");
        }

        public EmployeeComparator(String sortType) {
            this.sortType = sortType;
        }

        @Override
        public int compare(Employee o1, Employee o2) {
            if(sortType.equalsIgnoreCase("yearStarted")) {
                return o1.yearStarted-o2.yearStarted;
            }

            return o1.getName().compareTo(o2.getName());
        }
    }
    String name;
    Integer employeeId;
    Integer yearStarted;


    public Employee() {
    }

    public Employee(Integer employeeId, String name, Integer yearStarted) {
        this.name = name;
        this.employeeId = employeeId;
        this.yearStarted = yearStarted;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%d %-8s %d".formatted(employeeId, name, yearStarted);
    }
}
