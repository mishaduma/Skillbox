public class Main {
    public static void main(String[] args) {
        // Создаём компанию comp
        Company comp = new Company();
        // Нанимаем 180 операторов
        for (int i = 0; i < 180; i++) {
            comp.hire(new Operator(comp));
        }
        // Нанимаем 80 менеджеров
        for (int i = 0; i < 80; i++) {
            comp.hire(new Manager(comp));
        }
        // Нанимаем 10 топ-менеджеров
        for (int i = 0; i < 10; i++) {
            comp.hire(new TopManager(comp));
        }
        System.out.println("Доход компании: " + comp.getIncome() + " руб.");
        // Выводим список самых высоких зарплат в компании
        System.out.println("Список зарплат по убыванию:");
        for (Employee worker : comp.getSalaryStaff(15, true)) {
            System.out.println(worker.getMonthSalary() + " руб.");
        }
        // Выводим список самых низких зарплат в компании
        System.out.println("Список зарплат по возрастанию:");
        for (Employee worker : comp.getSalaryStaff(30, false)) {
            System.out.println(worker.getMonthSalary() + " руб.");
        }
        // Увольняем 50% сотрудников
        for (int i = comp.employees.size() - 1; i > 0; i -= 2) {
            comp.fire(i);
        }
        System.out.println("Уволили 50% сотрудников");
        System.out.println("Доход компании: " + comp.getIncome() + " руб.");
        // Выводим список самых высоких зарплат в компании
        System.out.println("Список зарплат по убыванию:");
        for (Employee worker : comp.getSalaryStaff(15, true)) {
            System.out.println(worker.getMonthSalary() + " руб.");
        }
        // Выводим список самых низких зарплат в компании
        System.out.println("Список зарплат по возрастанию:");
        for (Employee worker : comp.getSalaryStaff(30, false)) {
            System.out.println(worker.getMonthSalary() + " руб.");
        }
    }
}