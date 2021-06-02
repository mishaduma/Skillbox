import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите Ф. И. О.: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine() + "   ";

        int x1 = input.indexOf(" ");// индекс первого пробела
        int x2 = input.indexOf((" "), x1 + 1);// индекс второго пробела
        int x3 = input.indexOf((" "), x2 + 1);// индекс третьего пробела

        String surname = input.substring(0, x1).trim();
        String name = input.substring(x1, x2).trim();
        String secondName = input.substring(x2, x3).trim();
        System.out.println("Фамилия: " + surname + "\n" + "Имя: " + name + "\n" + "Отчество: " + secondName);
    }
}