public class TopManager implements Employee {
    private double rand = 10000 * Math.random();
    private int basicSalary = 100000 + (int) rand;
    private int salary;
    private Company company;

    public TopManager(Company company) {
        this.company = company;
    }

    @Override
    public int getMonthSalary() {
        if (company.getIncome() > 10000000) {
            salary = basicSalary / 2 * 5;
        } else {
            salary = basicSalary;
        }
        return salary;
    }
}