//package project.capstone.studyPal.data.models;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalTime;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity
//@Builder
//public class MyToken {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String token;
//    @OneToOne(fetch = FetchType.EAGER)
//    private AppUser user;
//    private final LocalTime createdAt = LocalTime.now();
//    private final LocalTime expiryTime = createdAt.plusMinutes(30L);
//}
