package Bank;

public abstract class Client {
    private int count;

    protected Client(int count) {
        this.count = count;
    }

    public void putMoney(int money) {
        this.count = count + money;
    }

    public void takeMoney(int money) {
        if (money > count) {
            System.out.println("Недостаточно средств на счёте.");
        } else {
            this.count = count - money;
        }
    }

    public void showCount() {
        System.out.println("Остаток на счёте: " + count);
    }
}