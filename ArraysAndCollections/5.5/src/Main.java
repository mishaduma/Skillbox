import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String[] chars = {"A", "B", "C", "E", "H", "K", "M", "O", "P", "T", "X", "Y"};
        for (int region = 1; region < 198; region++) {
            for (int i = 0; i < chars.length; i++) {
                for (int i2 = 0; i2 < chars.length; i2++) {
                    for (int i3 = 0; i3 < chars.length; i3++) {
                        for (int j = 1; j < 10; j++) {
                            list.add(chars[i] + j + j + j + chars[i2] + chars[i3] + region);
                        }
                    }
                }
            }
        }
        HashSet<String> setHash = new HashSet<>();
        setHash.addAll(list);
        TreeSet<String> setTree = new TreeSet<>();
        setTree.addAll(list);
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.print("Введите номер: ");
            String input = scanner.nextLine();
            //прямой перебор
            long start = System.currentTimeMillis();
            if (list.contains(input)) {
                System.out.print("Найдено! ");
            } else {
                System.out.print("Не найдено! ");
            }
            long duration = System.currentTimeMillis() - start;
            System.out.println("Прямой перебор: " + duration);
            //бинарный поиск
            Collections.sort(list);
            long start1 = System.currentTimeMillis();
            int index = Collections.binarySearch(list, input);
            if (index >= 0) {
                System.out.print("Найдено! ");
            } else {
                System.out.print("Не найдено! ");
            }
            long duration1 = System.currentTimeMillis() - start1;
            System.out.println("Бинарный поиск: " + duration1);
            //HashSet
            long start2 = System.currentTimeMillis();
            if (setHash.contains(input)) {
                System.out.print("Найдено! ");
            } else {
                System.out.print("Не найдено! ");
            }
            long duration2 = System.currentTimeMillis() - start2;
            System.out.println("HashSet: " + duration2);
            //TreeSet
            long start3 = System.currentTimeMillis();
            if (setTree.contains(input)) {
                System.out.print("Найдено! ");
            } else {
                System.out.print("Не найдено! ");
            }
            long duration3 = System.currentTimeMillis() - start3;
            System.out.println("TreeSet: " + duration3);
        }
    }
}