package spring.umc.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.common.entity.BaseEntity;

@Entity
@Table(name = "user_address")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAddress extends BaseEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Setter
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 10, nullable = false)
    private String postalCode;

    @Column(length = 50, nullable = false)
    private String baseAddress;

    @Column(length = 50)
    private String detailAddress;

    public UserAddress(String postalCode, String baseAddress, String detailAddress) {
        this.postalCode = postalCode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
    }

}
