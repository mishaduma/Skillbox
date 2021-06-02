import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Source {

    private String name;

    public Source(String name){
        this.name = name;
    }

    public Collection<Source> getChildren() {
        Collection<Source> children = new HashSet<>();
        Set<String> childNames = new HashSet<>();
        try {
            Document doc = Jsoup.connect(name).get();
            Elements elements = doc.getElementsByTag("a");
            elements.stream()
                    .map(element -> element.absUrl("href"))
                    .filter(element -> element.contains(name))
                    .map(element -> element.substring(0, element.lastIndexOf("/") + 1))
                    .filter(element -> element.length() > name.length())
                    .forEach(childNames::add);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (String child : childNames){
            children.add(new Source(child));
        }
        return children;
    }

    public String getName() {
        return name;
    }
}
