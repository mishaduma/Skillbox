import java.util.HashMap;

public class CustomerStorage {
    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        String[] components = data.split("\\s+");

        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong format! Correct format: \n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }

        if (!components[0].matches("[A-ZА-Я][a-zа-я]+")) {
            throw new IllegalArgumentException("Wrong name format! Correct format: \n" + "Василий");
        }

        if (!components[1].matches("[A-ZА-Я][a-zа-я]+")) {
            throw new IllegalArgumentException("Wrong surname format! Correct format: \n" + "Петров");
        }

        if (!components[3].matches("\\+\\d{11}")) {
            throw new IllegalArgumentException("Wrong phone number format! Correct format: \n" + "+79215637722");
        }

        if (!components[2].matches(".+@.+\\..{2,}")) {
            throw new IllegalArgumentException("Wrong e-mail format! Correct format: \n" + "vasily.petrov@gmail.com");
        }

        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        if (storage.size() < 1) {
            throw new IllegalArgumentException("List is empty!");
        }
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        String[] names = name.split("\\s+");
        if (names.length != 2) {
            throw new IllegalArgumentException("Wrong name format! Correct format: \n" + "Василий Петров");
        }
        if (!storage.containsKey(name)) {
            throw new IllegalArgumentException("Wrong name! " + name + " is not in this list!");
        }
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}