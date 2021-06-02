import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Creating accounts and bank
        HashMap<String, Account> accounts = new HashMap<String, Account>();
        for (int i = 1; i <= 100; i++) {
            String accNumber = String.valueOf(i);
            long money = Math.round(500000 * Math.random());
            Account acc = new Account();
            acc.setAccNumber(accNumber);
            acc.setMoney(money);
            accounts.put(accNumber, acc);
        }
        Bank bank = new Bank(accounts);

        //Check bank balance before transfers
        long beforeBalance = 0;
        for (int j = 1; j <= accounts.size(); j++) {
            beforeBalance += bank.getBalance(String.valueOf(j));
        }
        System.out.println("Bank balance before transfers: " + beforeBalance);

        ArrayList<Thread> threads = new ArrayList<>();
        for (int k = 0; k < 10; k++) {
            int threadNum = k + 1;
            threads.add(new Thread(() -> {
                long start = System.currentTimeMillis();
                int anyNumber = (int) Math.round(99 * Math.random());
                String accFrom = String.valueOf(anyNumber);
                String accTo = String.valueOf(anyNumber + 1);
                long amount = (long) (60000 * Math.random());
                try {
                    bank.transfer(accFrom, accTo, amount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread " + threadNum + " ends in: " + (System.currentTimeMillis() - start) + " ms");
            }));
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }

        //Check bank balance after transfers
        long afterBalance = 0;
        for (int n = 1; n <= accounts.size(); n++) {
            afterBalance += bank.getBalance(String.valueOf(n));
        }
        System.out.println("Bank balance after transfers: " + afterBalance);
    }
}