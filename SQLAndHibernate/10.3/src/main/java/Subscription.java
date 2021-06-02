import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString(exclude = {"students", "subscriptions"})
@NoArgsConstructor
@Entity
@Table(name = "Subscriptions")
public class Subscription implements Serializable {

    @EmbeddedId
    private Id id;

    @OneToOne
    @JoinColumn(name = "student_id", updatable = false, insertable = false)
    private Student student;

    @OneToOne
    @JoinColumn(name = "course_id", updatable = false, insertable = false)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class Id implements Serializable{

        public Id(){}

        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;
    }
}