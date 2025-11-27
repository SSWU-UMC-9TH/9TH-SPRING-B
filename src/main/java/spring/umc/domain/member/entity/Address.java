package spring.umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.global.entity.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "address")
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "postal_code", length = 20, nullable = false)
    private String postalCode;

    @Column(name = "road_address", length = 300, nullable = false)
    private String roadAddress;

    @Column(name = "address_detail", length = 200, nullable = true)
    private String addressDetail;

    @Column(name = "full_address", length = 500, nullable = false)
    private String fullAddress;

    @Column(name = "legal_dong", length = 200, nullable = false)
    private String legalDong;

    @Column(name = "legal_dong_code", columnDefinition = "CHAR(10)", nullable = false)
    private String legalDongCode;

    @Column(name = "longitude", precision = 10, scale = 7, nullable = false)
    private BigDecimal longitude;

    @Column(name = "latitude", precision = 10, scale = 7, nullable = false)
    private BigDecimal latitude;

    @Column(name = "geocoded_at", nullable = false)
    private LocalDateTime geocodedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }
}
