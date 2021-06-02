import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Course course = session.createQuery("from Course where id = 4", Course.class).getSingleResult();

        Student student = session.createQuery("from Student where id = 3", Student.class).getSingleResult();

        Subscription.Id id = new Subscription.Id(1, 10);
        Subscription subscription = session.get(Subscription.class, id);

        Teacher teacher = session.createQuery("from Teacher where id = 5", Teacher.class).getSingleResult();

        System.out.println(course.getStudents().get(1).getName() + " | "
                + student.getAge() + " | "
                + subscription.getSubscriptionDate() + " | "
                + teacher.getSalary());

        sessionFactory.close();
    }
}