import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Счётчик грузовиков
        int trucksCount = 0;
        // Вместимость грузовиков
        int trucksCapacity = 12;
        // Счётчик контейнеров
        int contCount = 0;
        // Вместимость контейнеров
        int contCapacity = 27;
        // Счётчик ящиков
        int boxCount = 0;
        // Счётчик заполняемости контейнеров
        int contLoad = 0;
        // Счётчик заполняемости грузовиков
        int trucksLoad = 0;

        System.out.println("Введите количество ящиков:");
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();

        while (boxCount < userInput) {
            trucksCount++;
            System.out.println("Грузовик " + trucksCount + ":");
            while (trucksLoad < trucksCapacity) {
                if (boxCount == userInput) {
                    break;
                } else {
                    contCount++;
                    trucksLoad++;
                    System.out.println("Контейнер " + contCount + ":");
                    while (contLoad < contCapacity) {
                        if (boxCount == userInput) {
                            break;
                        } else {
                            boxCount++;
                            contLoad++;
                            System.out.println("Ящик " + boxCount);
                        }
                    }
                    contLoad = 0;
                }
            }
            trucksLoad = 0;
        }
        System.out.println("Всего грузовиков: " + trucksCount);
        System.out.println("Всего контейнеров: " + contCount);
    }
}