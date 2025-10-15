package spring.umc.domain.member.entity.mapping;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.member.entity.Member;
import spring.umc.domain.member.entity.Term;
import spring.umc.domain.member.enums.ConsentStatus;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(
        name = "member_term",
        uniqueConstraints = @UniqueConstraint(columnNames = {"member_id","term_id"})
)
public class MemberTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status",length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private ConsentStatus status;

    @Column(name = "consented_at", nullable = false)
    private LocalDateTime consentedAt;

    @Column(name = "revoked_at")
    private LocalDateTime revokedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id", nullable = false)
    private Term term;

}
