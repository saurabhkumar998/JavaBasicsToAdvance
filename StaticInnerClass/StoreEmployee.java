package StaticInnerClass;

import java.util.Comparator;

public class StoreEmployee extends Employee{
    String store;

    public StoreEmployee() {

    }
    public StoreEmployee(String store) {
        this.store = store;
    }

    public StoreEmployee(Integer employeeId, String name, Integer yearStarted, String store) {
        super(employeeId, name, yearStarted);
        this.store = store;
    }

    @Override
    public String toString() {
        return "%-8s%s".formatted(store, super.toString());
    }

    public class StoreEmployeeComparator<T extends StoreEmployee> implements Comparator<StoreEmployee> {

        @Override
        public int compare(StoreEmployee o1, StoreEmployee o2) {
            int result = o1.store.compareTo(o2.store);
            if(result == 0) {
                return new Employee.EmployeeComparator<Employee>("yearStarted").compare(o1,o2);
            }

            return result;
        }
    }
}
