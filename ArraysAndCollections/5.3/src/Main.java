import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<String> emails = new HashSet<>();
        emails.add("mishaduma88@yandex.ru");
        emails.add("doom8mike@gmail.com");
        while (true) {
            System.out.print("Введите команду: ");
            String input = scanner.nextLine();
            if (input.contains("LIST")) {
                for (String email : emails) {
                    System.out.println(email);
                }
                System.out.println("Выполнено.");
            }
            if (input.contains("ADD")) {
                if (input.replaceAll("ADD\\s+", "").matches("\\w+@\\w+\\.[a-z]{2,}")) {
                    emails.add(input.replaceAll("ADD\\s+", ""));
                    System.out.println("Выполнено.");
                } else {
                    System.out.println("Неверный формат e-mail!");
                }
            }
        }
    }
}