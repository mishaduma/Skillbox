import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> links = new ArrayList<>();
        URL url;
        URLConnection conn;
        Path path = Paths.get("data");

        try {
            Document doc = Jsoup.connect("https://lenta.ru/").get();
            Elements img = doc.getElementsByTag("img");
            for (Element el : img) {
                links.add(el.absUrl("src"));
            }

            for (int i = 0; i < links.size(); i++) {
                url = new URL(links.get(i));
                conn = url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                int n = i + 1;

                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                }
                FileOutputStream fos = new FileOutputStream(new File("data/image" + n + ".jpg"));

                int ch;
                while ((ch = bis.read()) != -1) {
                    fos.write(ch);
                }
                bis.close();
                fos.flush();
                fos.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}