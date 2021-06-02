import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        final String WEB_SRC = "https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";
        final String FILE_PATH = "src\\main\\resources\\map.json";
        int rowsToSkip = 1;

        try {
            Document doc = Jsoup.connect(WEB_SRC).get();
            for (int tableNumber = 3; tableNumber < 6; tableNumber++) {
                Element table = doc.select("table").get(tableNumber);
                Elements rows = table.select("tr");
                rows.stream().skip(rowsToSkip).forEach(row -> {
                    Elements cols = row.select("td");
                    String stationName = cols.get(1).child(0).text();
                    String lineName = cols.get(0).child(1).attr("title");
                    List<String> lineNumber = cols.get(0).children().eachText();
                    List<String> connectionsLineName = cols.get(0).children().eachAttr("title");
                    Parser.parseStation(stationName, lineNumber, connectionsLineName);
                    Parser.parseLines(lineName, lineNumber);
                });
                rowsToSkip = 2;
            }

            rowsToSkip = 1;
            for (int tableNumber = 3; tableNumber < 6; tableNumber++) {
                Element table = doc.select("table").get(tableNumber);
                Elements rows = table.select("tr");
                rows.stream().skip(rowsToSkip).forEach(row -> {
                    Elements cols = row.select("td");
                    String stationName = cols.get(1).child(0).text();
                    List<String> connectionsNumber = cols.get(3).children().eachText();
                    if (connectionsNumber.size() != 0) Parser.parseConnections(cols, stationName);
                });
                rowsToSkip = 2;
            }

            Gson GSON = new GsonBuilder().setPrettyPrinting().create();

            Metro metro = new Metro(Parser.lines, Parser.stations, Parser.connection);
            try (FileWriter file = new FileWriter(FILE_PATH)) {
                file.write(GSON.toJson(metro));
            }

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(Parser.parseFile(FILE_PATH));

            Map<String, List<String>> stations = (Map<String, List<String>>) jsonObject.get("stations");
            for (String lineId : stations.keySet()) {
                JSONArray stationsArray = (JSONArray) stations.get(lineId);
                for (Line line : metro.getLines()) {
                    if (line.getId().equals(lineId)) {
                        System.out.println("Линия " + lineId + " " + line.getName() + " - станций: " + stationsArray.size());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}