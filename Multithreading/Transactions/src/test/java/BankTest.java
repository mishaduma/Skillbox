import junit.framework.TestCase;

import java.util.HashMap;

public class BankTest extends TestCase {

    HashMap<String, Account> accounts = new HashMap<String, Account>();

    @Override
    protected void setUp() throws Exception {
        for (int i = 0; i < 1000; i++){
            String accNumber = String.valueOf(i);
            long money = 500000;
            Account acc = new Account();
            acc.setAccNumber(accNumber);
            acc.setMoney(money);
            accounts.put(accNumber, acc);
        }
    }

    public void testTransfer() throws InterruptedException {
        Bank bank = new Bank(accounts);
        bank.transfer("6", "8", 40000);
        long actual6 = bank.getBalance("6");
        long expected6 = 460000;
        long actual8 = bank.getBalance("8");
        long expected8 = 540000;
        assertEquals(expected6, actual6);
        assertEquals(expected8, actual8);
    }

    public void testGetBalance(){
        Bank bank = new Bank(accounts);
        long actual = bank.getBalance("1");
        long expected = 500000;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
