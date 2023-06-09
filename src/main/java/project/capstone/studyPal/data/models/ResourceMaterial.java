package project.capstone.studyPal.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.CreationTimestamp;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ResourceMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(length = 10_000)
    private String description;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> authors;
    private String link;
    @CreationTimestamp
    private final LocalDateTime dateAdded = LocalDateTime.now();

}
