import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString(exclude = {"students", "subscriptions"})
@NoArgsConstructor
@Entity
@Table(name = "Purchaselist")
public class PurchaseList implements Serializable {

    @EmbeddedId
    private Id id;

    @OneToOne
    @JoinColumn(name = "student_name", updatable = false, insertable = false)
    private Student student;

    @OneToOne
    @JoinColumn(name = "course_name", updatable = false, insertable = false)
    private Course course;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Embeddable
    @EqualsAndHashCode
    public static class Id implements Serializable{

        public Id(){}

        @Column(name = "student_name")
        private String studentName;

        @Column(name = "course_name")
        private String courseName;
    }
}