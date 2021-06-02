package Banking;

public class Account {
    private int count;

    protected Account(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void putMoney(int money) {
        this.count = count + money;
        System.out.println("Внесена сумма: " + money);
    }

    public void getMoney(int money) {
        this.count = count - money;
        System.out.println("Снята сумма: " + money);
    }

    public void showCount() {
        System.out.println("Остаток на счёте: " + count);
    }
}