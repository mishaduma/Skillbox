import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        List<PurchaseList> purchaseLists = session.createQuery("from PurchaseList", PurchaseList.class).getResultList();

        Transaction transaction = session.beginTransaction();

        purchaseLists.stream().forEach(purchaseList -> {
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            Student student = session.createQuery("from Student where name = \"" + purchaseList.getStudentName() + "\"", Student.class).getSingleResult();
            Course course = session.createQuery("from Course where name = \"" + purchaseList.getCourseName() + "\"", Course.class).getSingleResult();
            LinkedPurchaseList.Id id = new LinkedPurchaseList.Id(student.getId(), course.getId());
            linkedPurchaseList.setId(id);
            linkedPurchaseList.setStudentName(student.getName());
            linkedPurchaseList.setCourseName(course.getName());
            linkedPurchaseList.setPrice(purchaseList.getPrice());
            linkedPurchaseList.setSubscriptionDate(purchaseList.getSubscriptionDate());
            session.save(linkedPurchaseList);
        });

        transaction.commit();
        sessionFactory.close();
    }
}