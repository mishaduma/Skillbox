import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите номер телефона: ");
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        String clearNumber = number.substring(number.indexOf("9")).replaceAll("[^0-9]", "");
        System.out.println("+7 " + clearNumber.substring(0, 3) + " " + clearNumber.substring(3, 6) + "-" + clearNumber.substring(6, 8) + "-" + clearNumber.substring(8, 10));
    }
}