import java.util.*;
import java.util.stream.Collectors;

// Employee class
class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return id + ": " + name + " (" + department + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee e = (Employee) o;
        return id == e.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

// Generic class
class DataSorter<T extends Comparable<T>> {

    public List<T> sortList(List<T> list) {
        Collections.sort(list);
        return list;
    }

    public List<T> sortList(List<T> list, Comparator<? super T> comparator) {
        list.sort(comparator);
        return list;
    }
}

// Main class
public class CollectionsDemo {
    public static void main(String[] args) {

        // List
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(101, "Alice", "Engineering"));
        list.add(new Employee(103, "Bob", "Marketing"));
        list.add(new Employee(102, "Charlie", "HR"));

        System.out.println("Original List:");
        list.forEach(System.out::println);

        // Set
        Set<Employee> set = new HashSet<>(list);
        set.add(new Employee(101, "Alice", "Engineering")); // duplicate

        System.out.println("\nSet Size (unique): " + set.size());

        // Map
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : list) {
            map.put(e.getId(), e);
        }

        System.out.println("\nEmployee with ID 102: " + map.get(102));

        // Sorting
        DataSorter<Employee> sorter = new DataSorter<>();

        System.out.println("\nSorted by ID:");
        sorter.sortList(new ArrayList<>(list)).forEach(System.out::println);

        System.out.println("\nSorted by Name:");
        sorter.sortList(new ArrayList<>(list),
                Comparator.comparing(Employee::getName))
                .forEach(System.out::println);

        // Stream API
        Map<String, List<Employee>> grouped =
                list.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("\nEmployees by Department:");
        grouped.forEach((dept, emps) ->
                System.out.println(dept + ": " + emps.size()));
    }
}
