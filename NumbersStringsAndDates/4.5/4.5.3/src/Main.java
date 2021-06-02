import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите Ф. И. О.: ");
        Scanner scanner = new Scanner(System.in);
        String[] input = (scanner.nextLine() + " - -").split("\\s");
        String[] form = {"Фамилия: ", "Имя: ", "Отчество: "};
        for (int i = 0; i < form.length; i++) {
            System.out.println(form[i] + input[i].replaceAll("-", ""));
        }
    }
}