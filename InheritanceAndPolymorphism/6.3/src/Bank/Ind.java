package Bank;

public class Ind extends Client {
    private int tax;

    public Ind(int count) {
        super(count);
    }

    @Override
    public void putMoney(int money) {
        if (money < 1000) {
            tax = money / 100;
        } else {
            tax = money / 200;
        }
        money -= tax;
        super.putMoney(money);
        System.out.println("Комиссия составила: " + tax);
    }
}