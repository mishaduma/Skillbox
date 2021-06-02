public class Manager implements Employee {
    double rand = 8000 * Math.random();
    private int basicSalary = 80000 + (int) rand;
    private int sales = 200000 + (int) rand;
    private int salary = basicSalary + sales / 20;
    private Company company;

    public Manager(Company company) {
        this.company = company;
    }

    @Override
    public int getMonthSalary() {
        return salary;
    }

    public int getSales() {
        return sales;
    }
}