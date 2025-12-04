package spring.umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.member.enums.Address;
import spring.umc.domain.member.enums.Sex;
import spring.umc.domain.member.enums.SocialType;
import spring.umc.global.auth.enums.Role;
import spring.umc.global.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "social_email", unique = true, length = 100, nullable = true)
    private String socialEmail;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false, length = 10)
    private Sex sex;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(name = "address", nullable = false, length = 20)
    private Address address;

    @Column(name = "address_detail", length = 255)
    private String addressDetail;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Long point = 0L;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", length = 20, nullable = true)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, unique = true)
    private String email;
}
