import java.util.List;
import java.util.Map;

public class Metro {
    private List<Line> lines;
    private Map<String, List<String>> stations;
    private List<List<Station>> connection;

    public Metro(List<Line> lines, Map<String, List<String>> stations, List<List<Station>> connection) {
        this.lines = lines;
        this.stations = stations;
        this.connection = connection;
    }

    public List<Line> getLines() {
        return lines;
    }
}