public class Operator implements Employee {
    private double rand = 5000 * Math.random();
    private int salary = 50000 + (int) rand;
    private Company company;

    public Operator(Company company) {
        this.company = company;
    }

    @Override
    public int getMonthSalary() {
        return salary;
    }

    public int getSales() {
        return 0;
    }
}