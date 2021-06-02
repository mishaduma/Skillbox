import Bank.Fiz;
import Bank.Ind;
import Bank.Uri;

public class Main {
    public static void main(String[] args) {
        Fiz one = new Fiz(10000);
        one.putMoney(3000);
        one.takeMoney(20000);
        one.showCount();

        Uri two = new Uri(10000);
        two.putMoney(3000);
        two.takeMoney(5000);
        two.showCount();

        Ind three = new Ind(10000);
        three.putMoney(800);
        three.putMoney(1000);
        three.showCount();
    }
}