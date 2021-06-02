import java.io.PrintWriter;
import java.util.ArrayList;

public class Loader {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        ArrayList<Thread> threads = new ArrayList<>();
        int part = 10;
        int startRegionCode = 1;
        int stopRegionCode = 0;
        for (int threadNum = 1; threadNum <= 10; threadNum++) {
            PrintWriter writer = new PrintWriter("res/numbers" + String.valueOf(threadNum) + ".txt");

            stopRegionCode += part;
            int finalStartRegionCode = startRegionCode;
            int finalStopRegionCode = stopRegionCode;

            threads.add(new Thread(() -> {
                for (int regionCode = finalStartRegionCode; regionCode < finalStopRegionCode; regionCode++) {
                    StringBuilder builder = new StringBuilder();
                    for (int number = 1; number < 1000; number++) {
                        for (char firstLetter : letters) {
                            for (char secondLetter : letters) {
                                for (char thirdLetter : letters) {
                                    builder.append(firstLetter);
                                    builder.append(padNumber(number, 3));
                                    builder.append(secondLetter);
                                    builder.append(thirdLetter);
                                    builder.append(padNumber(regionCode, 2));
                                    builder.append("\n");
                                }
                            }
                        }
                    }
                    writer.write(builder.toString());
                }
                writer.flush();
                writer.close();
            }));
            startRegionCode = stopRegionCode + 1;
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder builder = new StringBuilder();
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            builder.append("0");
        }
        builder.append(numberStr);
        return builder.toString();
    }
}
