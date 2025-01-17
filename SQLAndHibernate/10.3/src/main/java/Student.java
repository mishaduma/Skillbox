import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Data
@ToString(exclude = {"students", "subscriptions"})
@NoArgsConstructor
@Entity
@Table(name = "Students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String name;

    private int age;

    @Column(name = "registration_date")
    private Date registrationDate;
}