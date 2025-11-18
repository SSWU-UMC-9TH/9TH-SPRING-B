package spring.umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "legal_dong", length = 200, nullable = false)
    private String legalDong;

    @Column(name = "legal_dong_code",columnDefinition = "CHAR(10)", nullable = false)
    private String legalDongCode;

}
