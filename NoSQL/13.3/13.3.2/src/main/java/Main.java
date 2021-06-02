import au.com.bytecode.opencsv.CSVReader;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

import org.bson.Document;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws IOException {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> collection = database.getCollection("students");
        collection.drop();

        List<String[]> allRows = new CSVReader(new FileReader("src/main/resources/mongo.csv"), ',', '"', 0).readAll();
        for (String[] row : allRows) {
            Document document = new Document()
                    .append("Name", row[0].replaceAll("\\[", ""))
                    .append("Age", row[1])
                    .append("Courses", row[2].replaceAll("]", ""));
            collection.insertOne(document);
        }

        System.out.println("Общее количество студентов в базе: " + collection.countDocuments());

        List<Document> queryResult = new ArrayList<>();
        collection.find(gt("Age", "40")).forEach((Consumer<Document>) doc -> queryResult.add(doc));
        System.out.println("Количество студентов старше 40 лет: " + queryResult.size());

        System.out.println("Имя самого молодого студента: ");
        collection.find(eq("Age", collection.find().sort(new BasicDBObject("Age", 1)).first().get("Age")))
                        .forEach((Consumer<Document>) doc -> System.out.println(doc.get("Name")));

        System.out.println("Список курсов самого старого студента: ");
        collection.find(eq("Age", collection.find().sort(new BasicDBObject("Age", -1)).first().get("Age")))
                .forEach((Consumer<Document>) doc -> System.out.println(doc.get("Name") + " - " + doc.get("Courses")));
    }
}
