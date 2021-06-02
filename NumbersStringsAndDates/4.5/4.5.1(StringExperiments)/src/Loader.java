public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        String[] money = text.split(",");
        for (int i = 0; i < money.length; i++) {
            money[i] = money[i].replaceAll("[^0-9]", "");
        }
        int cash[] = new int[money.length];
        for (int i = 0; i < cash.length; i++) {
            cash[i] = Integer.parseInt(money[i]);
        }
        int total = 0;
        for (int num : cash) {
            total = total + num;
        }
        System.out.println("Сумма заработков: " + total + " \u20BD");
    }
}