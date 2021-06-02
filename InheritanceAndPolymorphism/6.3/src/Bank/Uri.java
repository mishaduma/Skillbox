package Bank;

public class Uri extends Client {
    public Uri(int count) {
        super(count);
    }

    @Override
    public void takeMoney(int money) {
        int tax = money / 100;
        money += tax;
        super.takeMoney(money);
        System.out.println("Комиссия составила: " + tax);
    }
}