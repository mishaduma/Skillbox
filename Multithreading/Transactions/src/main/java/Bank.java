import java.util.HashMap;
import java.util.Random;

public class Bank {
    private final HashMap<String, Account> accounts;
    private final Random random = new Random();

    public Bank(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account accFrom = accounts.get(fromAccountNum);
        Account accTo = accounts.get(toAccountNum);
        if (accFrom.isBlocked()) {
            System.out.println("Account " + accFrom.getAccNumber() + " is blocked! Transfer denied!");
            return;
        }
        if (accTo.isBlocked()) {
            System.out.println("Account " + accTo.getAccNumber() + " is blocked! Transfer denied!");
            return;
        }
        synchronized (Integer.parseInt(accFrom.getAccNumber()) < Integer.parseInt(accTo.getAccNumber()) ? accFrom : accTo) {
            synchronized (Integer.parseInt(accFrom.getAccNumber()) > Integer.parseInt(accTo.getAccNumber()) ? accFrom : accTo) {
                accFrom.setMoney(accFrom.getMoney() - amount);
                accTo.setMoney(accTo.getMoney() + amount);

                if (amount > 50000) {
                    boolean status = isFraud(fromAccountNum, toAccountNum, amount);
                    accFrom.setBlocked(status);
                    accTo.setBlocked(status);
                }
            }
        }
    }
    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        return account.getMoney();
    }
}
