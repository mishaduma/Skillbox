import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.addToSet;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        MongoCollection<Document> shops = database.getCollection("shops");
        MongoCollection<Document> products = database.getCollection("products");
        shops.drop();
        products.drop();

        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.println("Введите команду:");
            String input = scanner.nextLine();
            if (input.contains("ДОБАВИТЬ_МАГАЗИН") && input.split(" ").length == 2) {
                Document shop = new Document()
                        .append("Name", input.split(" ")[1])
                        .append("Products", Arrays.asList());
                shops.insertOne(shop);
                System.out.println("Добавлен магазин " + input.split(" ")[1]);
            }
            if (input.contains("ДОБАВИТЬ_ТОВАР") && input.split(" ").length == 3) {
                Document product = new Document()
                        .append("Name", input.split(" ")[1])
                        .append("Price", Integer.valueOf(input.split(" ")[2]));
                products.insertOne(product);
                System.out.println("Добавлен товар " + input.split(" ")[1] + " по цене " + input.split(" ")[2] + " руб.");
            }
            if (input.contains("ВЫСТАВИТЬ_ТОВАР") && input.split(" ").length == 3) {
                shops.updateOne(shops.find(eq("Name", input.split(" ")[2])).first(), addToSet("Products", input.split(" ")[1]));
                System.out.println("Выставлен товар " + input.split(" ")[1] + " в магазине " + input.split(" ")[2]);
            }
            if (input.equals("СТАТИСТИКА_ТОВАРОВ")) {
                System.out.println("Общее количество товаров: ");
                shops.aggregate(Arrays.asList(Aggregates.lookup("products", "Products", "Name", "productsList"),
                        Aggregates.unwind("$productsList"),
                        Aggregates.group("$Name", Accumulators.sum("productsList", 1))))
                        .forEach((Consumer<Document>) System.out::println);

                System.out.println("Средняя цена товаров: ");
                shops.aggregate(Arrays.asList(Aggregates.lookup("products", "Products", "Name", "productsList"),
                        Aggregates.unwind("$productsList"),
                        Aggregates.group("$Name", Accumulators.avg("avgPrice", "$productsList.Price"))))
                        .forEach((Consumer<Document>) System.out::println);

                System.out.println("Самый дорогой товар: ");
                shops.aggregate(Arrays.asList(Aggregates.lookup("products", "Products", "Name", "productsList"),
                        Aggregates.unwind("$productsList"),
                        Aggregates.group("$Name", Accumulators.max("maxPrice", "$productsList.Price"))))
                        .forEach((Consumer<Document>) System.out::println);
                System.out.println("Самый дешёвый товар: ");
                shops.aggregate(Arrays.asList(Aggregates.lookup("products", "Products", "Name", "productsList"),
                        Aggregates.unwind("$productsList"),
                        Aggregates.group("$Name", Accumulators.min("minPrice", "$productsList.Price"))))
                        .forEach((Consumer<Document>) System.out::println);

                System.out.println("Количество товаров дешевле 100 руб.: ");
                shops.aggregate(Arrays.asList(Aggregates.lookup("products", "Products", "Name", "productsList"),
                        Aggregates.unwind("$productsList"),
                        Aggregates.match(lt("productsList.Price", 100)),
                        Aggregates.group("$Name", Accumulators.sum("productsList", 1))))
                        .forEach((Consumer<Document>) System.out::println);
            }
        }
    }
}
