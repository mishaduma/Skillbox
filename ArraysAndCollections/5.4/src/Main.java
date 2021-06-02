import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<String, String> phoneBook = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.print("Введите имя или номер: ");
            String input = scanner.nextLine();
            if (input.equals("LIST")) {
                printMap(phoneBook);
                continue;
            }
            if (input.matches("\\D+")) {
                if (phoneBook.containsKey(input)) {
                    System.out.println(input + " - " + phoneBook.get(input));
                } else {
                    System.out.print("Введите номер: ");
                    String number = scanner.nextLine();
                    if (phoneBook.containsValue(number)) {
                        System.out.println("Номер уже занят!");
                        continue;
                    }
                    phoneBook.put(input, number);
                }
            }
            if (input.matches("\\d+")) {
                input.replaceAll("[^0-9]", "");
                if (phoneBook.containsValue(input)) {
                    for (String note : phoneBook.keySet()) {
                        if (phoneBook.get(note).equals(input)) {
                            System.out.println(note + " - " + input);
                        }
                    }
                } else {
                    System.out.print("Введите имя: ");
                    String name = scanner.nextLine();
                    phoneBook.put(name, input);
                }
            }
        }
    }

    private static void printMap(Map<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println(key + " - " + map.get(key));
        }
    }
}