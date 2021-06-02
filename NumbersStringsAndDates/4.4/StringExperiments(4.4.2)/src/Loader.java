public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        // преобразование Васиного заработка в целочисленное значение
        int a = Integer.parseInt(text.substring(text.indexOf(" ", text.indexOf(" ") + 1), text.indexOf(" ", text.indexOf(" ", text.indexOf(" ") + 1) + 1)).trim());

        // преобразование Петиного заработка в целочисленное значение
        int b = Integer.parseInt(text.substring(text.indexOf("-") + 1, text.indexOf(" ", (text.indexOf("-") + 1) + 1)).trim());

        // преобразование Машиного заработка в целочисленное значение
        int c = Integer.parseInt(text.substring(text.lastIndexOf("-") + 1, text.lastIndexOf(" ")).trim());

        System.out.println("Сумма заработка друзей - " + (a + b + c) + " \u20BD");
    }
}