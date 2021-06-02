package Banking;

import java.time.LocalDate;

public class Deposit extends Account {
    private LocalDate putDate;
    private LocalDate getDate;

    public Deposit(int count) {
        super(count);
    }

    @Override
    public void putMoney(int money) {
        super.putMoney(money);
        this.putDate = LocalDate.now();
        this.getDate = putDate.plusMonths(1);
        System.out.println("Снять средства можно будет " + getDate);
    }

    @Override
    public void getMoney(int money) {
        LocalDate now = LocalDate.now();
        if (now.isAfter(getDate)) {
            super.getMoney(money);
        } else {
            System.out.println("Снятие средств невозможно! Не прошёл месяц с момента последнего внесения.");
        }
    }
}