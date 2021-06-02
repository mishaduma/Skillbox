import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class LinkedPurchaseList implements Serializable {

    @EmbeddedId
    private Id id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @OneToOne
    @JoinColumn(name = "student_name", updatable = false, insertable = false)
    private Student student;

    @OneToOne
    @JoinColumn(name = "course_name", updatable = false, insertable = false)
    private Course course;

    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class Id implements Serializable {

        public Id() {}

        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;
    }
}