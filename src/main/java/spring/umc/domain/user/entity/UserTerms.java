package spring.umc.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.terms.entity.Terms;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_terms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserTermsId.class)
public class UserTerms {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "terms_id")
    private Long termsId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("termsId")
    @JoinColumn(name = "terms_id")
    private Terms terms;

    @Column(nullable = false)
    private Boolean agreed;

    private LocalDateTime agreedAt;
}
