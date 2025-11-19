package spring.umc.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.common.entity.BaseEntity;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

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

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(length = 50)
    private String providerId;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAddress userAddress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTerms> userTermsList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserFood> userFoodList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList;

    @Builder
    public User(String nickname, Gender gender, LocalDate birth) {
        this.nickname = nickname;
        this.gender = gender;
        this.birth = birth;
    }

    // 주소는 따로 저장
    public void updateAddress(UserAddress address) {
        this.userAddress = address;
        address.setUser(this);
    }

}