import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.readAllLines;

public class Main {
    public static void main(String[] args) {

        double inMoney;
        double outMoney;
        ArrayList<String> description = new ArrayList<>();
        ArrayList<String> income = new ArrayList<>();
        ArrayList<String> outcome = new ArrayList<>();
        Map<String, Double> expenses = new HashMap();

        try {
            List<String> lines = readAllLines(Paths.get("D:/Git/GitLab/java_basics/09_FilesAndNetwork/files/movementList.csv"));
            for (String line : lines) {
                String[] fragments = line.split(",", 8);
                if (fragments.length != 8) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                description.add(fragments[5]);
                income.add(fragments[6]);
                outcome.add(fragments[7]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        inMoney = income.stream()
                .filter(s -> s.matches("[0-9]+,?[0-9]*"))
                .mapToDouble(s -> Double.parseDouble(s.replaceAll("^[0-9],", "").trim()))
                .sum();

        outMoney = outcome.stream()
                .filter(s -> s.matches("\"?[0-9]+,?[0-9]*\"?"))
                .map(s -> s.replaceAll("\"", ""))
                .mapToDouble(s -> Double.parseDouble(s.replaceAll(",", ".")))
                .sum();

        for (int i = 1; i < description.size(); i++) {
            String name;
            Double money = 0.0;
            String[] str = description.get(i).split("\\s{4,}");
            name = str[1];
            for (int j = 0; j < description.size(); j++) {
                if (description.get(j).contains(name)) {
                    money += Double.parseDouble(outcome.get(j)
                            .replaceAll("\"", "")
                            .replaceAll(",", "."));
                }
                expenses.put(name, money);
            }
        }
        System.out.println("Разбивка расходов: ");
        expenses.entrySet().forEach(e -> {
            System.out.println(e.getKey() + " - " + e.getValue());
        });

        System.out.println("Общий приход: " + inMoney + " руб." + "\n"
                + "Общий расход: " + outMoney + " руб." + "\n");
    }
}