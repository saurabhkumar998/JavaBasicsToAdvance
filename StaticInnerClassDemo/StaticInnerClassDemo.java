package StaticInnerClassDemo;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class StaticInnerClassDemo {
    public static void main(String[] args) {


        List<Employee> employeeList = new ArrayList<Employee>(List.of(
                new Employee(101, "Saurabh", 2021),
                new Employee(102, "Saumya", 2020),
                new Employee(103, "Gaurav", 2022)
        ));

        for(var e : employeeList) {
            System.out.println(e);
        }
        System.out.println();

//        var comparator = new EmployeeComparator<Employee>();
//        employeeList.sort(comparator);

        employeeList.sort(new Employee.EmployeeComparator<Employee>("yearStarted").reversed());

        for(var e : employeeList) {
            System.out.println(e);
        }

        System.out.println("\nStore Members\n");

        List<StoreEmployee> storeEmployees = new ArrayList<StoreEmployee>(List.of(
                new StoreEmployee(1001, "Joe", 2015, "Walmart"),
                new StoreEmployee(1004, "Donald", 2017, "Macys"),
                new StoreEmployee(1003, "Jack", 2025, "Walmart")
        ));

        for(var e : storeEmployees) {
            System.out.println(e);
        }

        System.out.println();

        var storeEmpComparator = new StoreEmployee.EmployeeComparator<StoreEmployee>();
        storeEmployees.sort(storeEmpComparator);

        for(var e : storeEmployees) {
            System.out.println(e);
        }
        System.out.println(

        );

       // var genericEmployee = new StoreEmployee();
       // var storeEmployeeComparator = genericEmployee.new StoreEmployeeComparator<StoreEmployee>();
        // the above code also works
        // to create an instance of the inner class, we must have an instance of the enclosing class.
        // from that instance you call .new, followed by the inner class name and parenthesis like below
        var storeEmployeeComparator = new StoreEmployee().new StoreEmployeeComparator<StoreEmployee>();
        storeEmployees.sort(storeEmployeeComparator);

        for(var e : storeEmployees) {
            System.out.println(e);
        }

        System.out.println();

        addPigLatinName(storeEmployees);

    }

    public static void addPigLatinName(List<? extends StoreEmployee> list) {
        class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee> {

            private String pigLatinName;
            private Employee originalInstance;


            public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
                this.pigLatinName = pigLatinName;
                this.originalInstance = originalInstance;
            }

            @Override
            public String toString() {
                return originalInstance.toString()+ " " + pigLatinName;
            }

            @Override
            public int compareTo(DecoratedEmployee o) {
                return pigLatinName.compareTo(o.pigLatinName);
            }
        }

        List<DecoratedEmployee> newList = new ArrayList<>(list.size());

        for(var employee : list) {
            String name = employee.getName();
            String pigLatin = name.substring(1) + name.charAt(0) + "ay";
            newList.add(new DecoratedEmployee(pigLatin, employee));
        }

        newList.sort(null);

        for(var e : newList) {
            System.out.println(e);
        }
    }
}
