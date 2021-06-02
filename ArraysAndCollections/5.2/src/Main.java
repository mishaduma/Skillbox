import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> todoList = new ArrayList<>() {{
            add("Первое дело");
            add("Второе дело");
            add("Третье дело");
        }};
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите команду: ");
            String input = scanner.nextLine() + " ";
            if (input.substring(0, input.indexOf(" ")).equals("LIST")) {
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println(i + " - " + todoList.get(i));
                }
                System.out.println("Выполнено.");
            }
            if (input.substring(0, input.indexOf(" ")).equals("ADD")) {
                if (input.replaceAll("[^0-9]", "").matches("[0-9]")) {
                    int indexOfAdd = Integer.parseInt(input.replaceAll("[^0-9^-]", ""));
                    if (indexOfAdd >= todoList.size()) {
                        todoList.add(input.substring(input.indexOf(" ", input.indexOf(" ") + 1)).trim());
                        System.out.println("Выполнено.");
                    } else if (indexOfAdd < 0) {
                        System.out.println("Неверный индекс!");
                    } else {
                        todoList.add(indexOfAdd, input.substring(input.indexOf(" ", input.indexOf(" ") + 1)).trim());
                        System.out.println("Выполнено.");
                    }
                } else {
                    todoList.add(input.substring(input.indexOf(" ")).trim());
                    System.out.println("Выполнено.");
                }
            }
            if (input.substring(0, input.indexOf(" ")).equals("EDIT")) {
                int indexOfEdit = Integer.parseInt(input.replaceAll("[^0-9^-]", ""));
                if (indexOfEdit >= todoList.size() || indexOfEdit < 0) {
                    System.out.println("Неверный индекс!");
                } else {
                    todoList.remove(indexOfEdit);
                    todoList.add(indexOfEdit, input.substring(input.indexOf(" ", input.indexOf(" ") + 1)).trim());
                    System.out.println("Выполнено.");
                }
            }
            if (input.substring(0, input.indexOf(" ")).equals("DELETE")) {
                int indexOfDelete = Integer.parseInt(input.replaceAll("[^0-9^-]", ""));
                if (indexOfDelete >= todoList.size() || indexOfDelete < 0) {
                    System.out.println("Неверный индекс!");
                } else {
                    todoList.remove(indexOfDelete);
                    System.out.println("Выполнено.");
                }
            }
        }
    }
}