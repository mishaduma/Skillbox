import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Parser {
    public static List<Line> lines = new LinkedList<>();
    public static Map<String, List<String>> stations = new TreeMap<>();
    public static List<List<Station>> connection = new ArrayList<>();

    static void parseConnections(Elements cols, String stationName) {
        List<String> connectionsDescription = cols.get(3).children().eachAttr("title");
        List<String> connectionsCount = cols.get(3).children().eachText();
        String lineId = cols.get(0).children().eachText().get(0);
        List<Station> list = new ArrayList<>();
        Station station = new Station(lineId, stationName);
        for (List<Station> connections : connection){
            for (Station st : connections){
                if (st.getLineId().matches(station.getLineId()) && st.getName().matches(station.getName())){
                    return;
                }
            }
        }
        list.add(station);
        for (int i = 0; i < connectionsCount.size(); i++) {
            List<String> listOfStations = stations.get(connectionsCount.get(i));
            String connectionName = connectionsDescription.get(i);
            String connectedStationName = null;
            for (String s : listOfStations) {
                if (connectionName.contains(s)) {
                    connectedStationName = s;
                }
            }
            list.add(new Station(connectionsCount.get(i), connectedStationName));
        }
        connection.add(list);
    }

    static void parseLines(String lineName, List<String> lineNumber) {
        Line line = new Line(lineNumber.get(0), lineName);
        if (!lines.contains(line)) {
            lines.add(line);
        }
    }

    static void parseStation(String stationName, List<String> lineNumber, List<String> connectionsLineName) {
        String lineId = lineNumber.get(0);
        if (!stations.containsKey(lineId)) {
            stations.put(lineId, new ArrayList<>());
        }
        stations.get(lineId).add(stationName);

        if (connectionsLineName.size() == 2) {
            if (!stations.containsKey(lineNumber.get(1)))
                stations.put(lineNumber.get(1), new ArrayList<>());
            else stations.get(lineNumber.get(1)).add(stationName);
        }
    }

    static String parseFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> sb.append(line).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}