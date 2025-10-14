package spring.umc.domain.member.entity.mapping;

import jakarta.persistence.*;
import lombok.*;
import spring.umc.domain.member.entity.Food;
import spring.umc.domain.member.entity.Member;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(
        name = "member_food",
        uniqueConstraints = @UniqueConstraint(columnNames = {"member_id","food_id"})
)
public class MemberFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

}
