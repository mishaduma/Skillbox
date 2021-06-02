import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.skillbox.airport.Flight.Type.DEPARTURE;

public class Main {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";
    public static final long HOUR = 3600 * 1000;

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();

        staff.stream()
                .filter(e -> e.getWorkStart().toString().trim().substring(e.getWorkStart().toString().trim().lastIndexOf(" ")).equals(" 2017"))
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(System.out::println);

        Airport airport = Airport.getInstance();

        Date date = new Date(System.currentTimeMillis());
        Date twoHours = new Date(System.currentTimeMillis() + 2 * HOUR);

        airport.getTerminals().stream()
                .map(e -> e.getFlights())
                .flatMap(n -> n.stream())
                .filter(s -> s.getType().equals(DEPARTURE))
                .filter(k -> k.getDate().after(date))
                .filter(n -> n.getDate().before(twoHours))
                .forEach(System.out::println);
    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}