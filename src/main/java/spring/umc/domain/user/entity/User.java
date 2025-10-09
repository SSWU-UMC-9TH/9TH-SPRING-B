package spring.umc.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 10)
    private String nickname;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 25)
    private String phoneNumber;

    private Boolean phoneVerified;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender = Gender.PRIVATE;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private Integer point = 0;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(length = 50)
    private String providerId;

    // ✅ 연관 관계
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAddress userAddress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTerms> userTermsList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserFood> userFoodList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList;
}