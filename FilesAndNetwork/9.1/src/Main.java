import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        int i;
        String[] fileSizes = {"bytes", "Kb", "Mb", "Gb", "Tb"};

        try {
            double size = Files.walk(Paths.get("data"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .map(file -> file.length())
                    .reduce((f1, f2) -> f1 + f2)
                    .get();

            for(i = 0; i < fileSizes.length; i++){
                if (size < 1024){
                    break;
                }
                size /= 1024;
            }
            System.out.printf("%8.2f", size);
            System.out.println(" " + fileSizes[i]);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}