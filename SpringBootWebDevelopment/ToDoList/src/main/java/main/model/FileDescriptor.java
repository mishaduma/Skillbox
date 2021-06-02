package main.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class FileDescriptor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String originalFileName;
    private String fileName;
    private String ext;
    private Long size;
    private LocalDateTime createdTs;
}
