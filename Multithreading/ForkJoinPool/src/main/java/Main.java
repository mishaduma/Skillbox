import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args) {
        final String NAME = "https://skillbox.ru/";
        final String TRG = "data/sitemap.txt";

        Source source = new Source(NAME);

        String taskResult = new ForkJoinPool().invoke(new Tasker(source));

        String [] splitedResult = taskResult.split(" ");

        Set<String> resultSet = new TreeSet<>();

        for (String elem : splitedResult){
            resultSet.add(elem);
        }

        String stringToWrite = "";

        for (String elem : resultSet){
            String prefix = "";
            int prefixSize = elem.split("/").length - NAME.split("/").length;
            for (int i = 0; i < prefixSize; i++){
                prefix += "\t";
            }
            stringToWrite += prefix + elem + "\n";
        }

        try {
            Path path = Paths.get(TRG);
            if (!Files.exists(path)){
                Files.createDirectory(Paths.get("data"));
            }
            Files.write(path, stringToWrite.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
