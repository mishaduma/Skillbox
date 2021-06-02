import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StaxParser {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static HashMap<Voter, Integer> voterCounts;

    public StaxParser() {
        voterCounts = new HashMap<>();
    }

    public void parse(String path) {
        Voter voter = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "voter":
                            Attribute name = startElement.getAttributeByName(new QName("name"));
                            if (name != null && voter == null) {
                                Date birthDay = birthDayFormat.parse(startElement.getAttributeByName(new QName("birthDay")).getValue());
                                voter = new Voter(name.getValue(), birthDay);
                            }
                            break;
                        case "visit":
                            nextEvent = reader.nextEvent();
                            if (voter != null) {
                                int count = voterCounts.getOrDefault(voter, 0);
                                voterCounts.put(voter, count + 1);
                            }
                            break;
                    }
                }
                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();
                    if (endElement.getName()
                            .getLocalPart()
                            .equals("voter")) {
                        voter = null;
                    }
                }
            }
        } catch (XMLStreamException xse) {
            System.out.println("XMLStreamException");
            xse.printStackTrace();
        } catch (FileNotFoundException | ParseException fnfepe) {
            System.out.println("FileNotFoundException");
            fnfepe.printStackTrace();
        }
    }

    public HashMap<Voter, Integer> getVoterCounts() {
        return voterCounts;
    }
}