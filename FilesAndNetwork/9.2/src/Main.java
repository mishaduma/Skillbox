import org.apache.commons.io.FileUtils;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        File src = new File("data");
        File trg = new File("data-copy");

        try {
            FileUtils.copyDirectory(src, trg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /*Example example = new Example();

        try {
            example.copy(src, trg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         */
    }
}