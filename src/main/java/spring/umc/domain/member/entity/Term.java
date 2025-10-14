package spring.umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.member.enums.TermType;
import spring.umc.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(
        name = "term",
        uniqueConstraints = @UniqueConstraint(columnNames = {"term_type","version"})
)
public class Term extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "term_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermType termType;

    @Column(name = "version", length = 32, nullable = false)
    private String version;

    @Column(name = "content_url",length = 500, nullable = false)
    private String contentUrl;

    @Column(name = "is_required", nullable = false)
    private Boolean isRequired;
}
