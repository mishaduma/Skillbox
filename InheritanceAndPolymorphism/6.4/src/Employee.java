public interface Employee extends Comparable<Employee> {
    int getMonthSalary();

    default int getSales() {
        return 0;
    }

    default int compareTo(Employee employee) {
        return employee.getMonthSalary() - getMonthSalary();
    }
}