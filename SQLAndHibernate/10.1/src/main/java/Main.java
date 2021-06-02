import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?serverTimezone=UTC";
        String user = "root";
        String pass = "test";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT name, MONTH(subscription_date) AS month, COUNT(MONTH(subscription_date)) AS purchase " +
                                                            "FROM Courses JOIN Subscriptions ON Courses.id = Subscriptions.course_id " +
                                                            "GROUP BY name, month ORDER BY name, month");
            while (resultSet.next()) {
                String courseName = resultSet.getString("name");
                String month = resultSet.getString("month");
                String purchase = resultSet.getString("purchase");
                System.out.println(courseName + " | месяц - " + month + " | покупок - " + purchase);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}