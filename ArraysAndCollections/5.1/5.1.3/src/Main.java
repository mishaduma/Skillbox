import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите высоту фигуры: ");
        Scanner s = new Scanner(System.in);
        int input = Integer.parseInt(s.nextLine());
        String[][] krest = new String[input][input];
        for (int i = 0; i < krest.length; i++) {
            for (int j = 0; j < krest.length; j++) {
                if (i == j || i == krest.length - 1 - j) {
                    krest[i][j] = "X";
                } else {
                    krest[i][j] = " ";
                }
            }
        }
        for (int k = 0; k < krest.length; k++) {
            for (String out : krest[k]) {
                System.out.print(out);
            }
            System.out.println();
        }
    }
}