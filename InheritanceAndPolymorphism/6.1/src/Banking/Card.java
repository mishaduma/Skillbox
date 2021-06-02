package Banking;

public class Card extends Account {
    public Card(int count) {
        super(count);
    }

    @Override
    public void putMoney(int money) {
        super.putMoney(money);
    }

    @Override
    public void getMoney(int money) {
        super.getMoney(money);
        int tax = money / 100;
        super.setCount(this.getCount() - tax);
        System.out.println("Комиссия составила: " + tax);
    }
}