import java.util.*;

public class Company {
    private int income;
    protected List<Employee> employees = new ArrayList<>();

    protected void setIncome(int income) {
        this.income = income;
    }

    protected int getIncome() {
        return income;
    }

    protected void hire(Employee e) {
        employees.add(e);
        income += e.getSales();
    }

    protected void hireAll(ArrayList<Employee> list) {
        employees.addAll(list);
        for (Employee worker : list) {
            income += worker.getSales();
        }
    }

    protected void fire(int index) {
        income -= employees.get(index).getSales();
        employees.remove(index);
    }

    protected List<Employee> getSalaryStaff(int count, boolean top) {
        if (count > employees.size()) {
            System.out.println("Введённое число превышает количество сотрудников. В данный момент в компании " + employees.size() + " сотрудников.");
            return null;
        } else {
            if (top == true) {
                Collections.sort(employees);
            } else {
                Collections.sort(employees, Collections.reverseOrder());
            }
            List<Employee> salaryStaff = employees.subList(0, count);
            return salaryStaff;
        }
    }
}