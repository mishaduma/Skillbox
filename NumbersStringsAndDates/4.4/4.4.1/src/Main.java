public class Main {
    public static void main(String[] args) {
        String text = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZабвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

        for (int x = 0; x < text.length(); x++) {
            char c = text.charAt(x);
            int code = (int) c;
            System.out.println(c + " " + code);
        }
    }
}