import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "data/src";
        String dstFolder = "data/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int cores = Runtime.getRuntime().availableProcessors();
        int part = files.length / cores;

        List<ImageResizer> resizers = new ArrayList<ImageResizer>();

        for (int i = 0; i < cores; i++) {
            File[] threadFiles = new File[part];
            System.arraycopy(files, part * i, threadFiles, 0, threadFiles.length);
            resizers.add(i, new ImageResizer(threadFiles, newWidth, dstFolder, start));
            new Thread(resizers.get(i)).start();
        }
    }
}
