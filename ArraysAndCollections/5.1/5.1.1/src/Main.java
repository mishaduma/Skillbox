public class Main {
    public static void main(String[] args) {
        String text = "Каждый охотник желает знать, где сидит фазан";
        String[] colorWords = text.split(",?\\s+");
        String[] reverse = new String[colorWords.length];
        int l = colorWords.length - 1;
        for (String word : colorWords) {
            reverse[l] = word;
            l--;
        }
        for (int i = 0; i < reverse.length; i++) {
            System.out.println(reverse[i]);
        }
    }
}