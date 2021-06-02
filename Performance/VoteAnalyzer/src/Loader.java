import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Loader {
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static HashMap<Integer, WorkTime> voteStationWorkTimes = new HashMap<>();
    private static HashMap<Voter, Integer> voterCounts = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-1572M.xml";

        long start = System.currentTimeMillis();

        StaxParser parser = new StaxParser();
        parser.parse(fileName);
        System.out.println("Parsing duration: " + (System.currentTimeMillis() - start) + "ms");

        for (Voter voter : parser.getVoterCounts().keySet()) {
            DBConnection.countVoter(voter.getName(), birthDayFormat.format(voter.getBirthDay()), parser.getVoterCounts().get(voter).intValue());
        }

        DBConnection.executeMultiInsert();
        DBConnection.printVoterCounts();

        /*SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);
        handler.printDuplicatedVoters();*/
    }
}