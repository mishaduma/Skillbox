public class Main {
    public static void main(String[] args) {
        System.out.println(sumDigits(222));
    }

    public static int sumDigits(Integer number) {
        //@TODO: write code here
        int result = 0;
        int n = number.toString().length();
        while (n > 0) {
            result = result + Character.getNumericValue(number.toString().charAt(n - 1));
            n--;
        }
        return result;
    }
}