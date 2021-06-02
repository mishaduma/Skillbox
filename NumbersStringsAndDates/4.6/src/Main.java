import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите дату своего рождения (в формате дд.мм.гггг): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] birthday = input.split("\\.");
        int[] birthDate = new int[birthday.length];
        for (int i = 0; i < birthDate.length; i++) {
            birthDate[i] = Integer.parseInt(birthday[i]);
        }
        Calendar c = Calendar.getInstance();
        Locale rus = new Locale("ru", "RU");
        SimpleDateFormat dayOfWeek = new SimpleDateFormat("EEEE", rus);
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat todayYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        c.set(birthDate[2], birthDate[1] - 1, birthDate[0]);
        int age = Integer.parseInt(todayYear.format(currentDate.getTime())) - birthDate[2];
        {
            for (int i = 0; i <= age; ) {
                while (c.getTimeInMillis() < currentDate.getTimeInMillis()) {
                    System.out.println(i + " - " + date.format(c.getTime()) + " - " + dayOfWeek.format(c.getTime()));
                    c.add(Calendar.YEAR, 1);
                    i++;
                }
                break;
            }
        }
    }
}