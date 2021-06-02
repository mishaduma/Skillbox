import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class PurchaseList implements Serializable {

    @EmbeddedId
    private Id id;

    @Column(name = "student_name", updatable = false, insertable = false)
    private String studentName;

    @Column(name = "course_name", updatable = false, insertable = false)
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Embeddable
    @EqualsAndHashCode
    @ToString
    public static class Id implements Serializable{

        static final long serialVersionUID = 1L;

        @Column(name = "student_name")
        private String studentName;

        @Column(name = "course_name")
        private String courseName;
    }
}