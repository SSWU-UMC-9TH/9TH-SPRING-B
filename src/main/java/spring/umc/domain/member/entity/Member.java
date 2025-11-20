package spring.umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.member.entity.mapping.MemberFood;
import spring.umc.domain.member.entity.mapping.MemberTerm;
import spring.umc.domain.member.enums.Gender;
import spring.umc.domain.member.enums.ProviderType;
import spring.umc.domain.member.enums.Status;
import spring.umc.domain.mission.entity.mapping.MemberMission;
import spring.umc.global.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "gender", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "phone_verified", nullable = false)
    @Builder.Default
    private Boolean phoneVerified = false;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "provider_type", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(name = "provider_uid", length = 100, nullable = false)
    private String providerUid;

    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Address address;

    // 양방향 매핑
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();

    public void setAddress(Address address) {
        this.address = address;
        if (address != null) {
            address.setMember(this);
        }
    }
}
